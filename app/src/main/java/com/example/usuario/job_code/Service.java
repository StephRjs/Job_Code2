/*Universidad de Costa Rica
Informática Empresarial
IF7201 - Gestión de Proyectos
Prof. MAP. Verny Fernández
Proyecto Job Code
Estudiantes:
Paula Alvarez Barrantes – B40301
Alejandra Anchía Pérez - B30388
César Bolaños Brenes - B31030
Stephanie Rojas Alfaro – A54827
I Ciclo, 2017

Clase: Service
Clase lógica que se encarga de captar los datos ingresados por la empresa y de guardarlos en la base de datos en el tipo de Student Services
 por medio del consumo de un Web Service. Utiliza la función de volley y se encarga de mapear (diccionario de los parámetros).*/
package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Llamados a las librerias necesarias para el funcionamiento de volley
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
/*Llamados a las librerías de JSon necesarias para el manejo de datos que se les envía y devuelven los
Web Services*/
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Service extends AppCompatActivity {

    private Button post;
    private String companyName;
    private EditText description, dueDate, place, contact;
    private ProgressDialog progress;
    private String random;

    /**
     * Método que se encarga de sobre-escribir la construccioón y validación de la parte gráfica del
     * sistema para la creación de la publicación de un nuevo servicio para estudiantes.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        post = (Button) findViewById(R.id.btPost);
        companyName = getIntent().getExtras().getString("companyName");
        description = (EditText) findViewById(R.id.ed_txDescriptionS);
        dueDate = (EditText) findViewById(R.id.ed_txDueDateS);
        place = (EditText) findViewById(R.id.ed_txDepartament);
        contact = (EditText) findViewById(R.id.ed_txEmailS);
        post = (Button) findViewById(R.id.btn_post);

        progress= new ProgressDialog(this);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (description.getText().length() == 0||dueDate.getText().length() == 0||place.getText().length() == 0||contact.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Debe de completar todos los datos",
                            Toast.LENGTH_LONG).show();
                }else {
                    if ((!contact.getText().toString().contains("@")) || (!contact.getText().toString().contains("."))) {
                        Toast.makeText(getApplicationContext(), "El formato del correo es incorrecto debe contener @",
                                Toast.LENGTH_LONG).show();

                    } else {
                        if (v == post)
                            sendPost();
                        Intent next = new Intent(Service.this, Main.class);
                        startActivity(next);
                    }
                }
            }
        });
    }
    /*Método para envíar un Post, captura los datos ingresados por la empresa, quita los espacios
  * innecesarios y conforma una solicitud al web service con los datos necesarios para enviarlos
  * @param
  * */
    private void sendPost(){
        final String company = companyName.trim().replace(" ", "*");
        final String descript = description.getText().toString().trim().replace(" ", "*");
        final String dueDat = dueDate.getText().toString().trim();
        final String place_postion_softType = place.getText().toString().trim().replace(" ", "*");
        final String email = contact.getText().toString().trim();

        random = random();

        CodeEmailTask task = new CodeEmailTask();
        task.execute();

        progress.setMessage("Cargando datos...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTER+"companyName="+company+"&description="+descript+
                "&email="+email+"&dueDate="+dueDat+"&IDTypePost=3&Place_Position_SoftType="+place_postion_softType+"&code="+random, new Response.Listener<String>() {
            /**Método para sobre-escribir el método onResponse, comprueba que el servicio provea de una respuesta
             * que se encuentre disponible.
             * @param response
          */
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),json.getString("message")+"Información agregada correctamente", Toast.LENGTH_LONG).show();
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            /**Método para sobre-escribir el método que escucha los errores, la presencia de un error para
             *  informar al cliente.
             *  @param error
         */
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Toast.makeText(getApplicationContext(),error.getMessage()+"Se ha producido un error", Toast.LENGTH_LONG).show();
            }
        }){
            /**Método para sobre-escribir el método que realiza el mapa de parámetros, con el fin de generar
             * un diccionario de todos los datos que se ingresan.
             * @param
            */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("companyName", company);
                params.put("description", descript);
                params.put("email", email);
                params.put("dueDate", dueDat);
                params.put("IDTypePost", "3");
                params.put("Place_Position_SoftType", place_postion_softType);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }


    private String random() {
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(9000);
        System.out.println(Integer.toString(n));
        return Integer.toString(n);
    }


    private class CodeEmailTask extends AsyncTask<Void, Integer, Boolean> {

        protected Boolean doInBackground(Void... params) {
            sendEmail();

            return true;
        }

        private String expirationDate(){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = dueDate.getText().toString().trim().replace("/", "-");
            String expirationDate = "";
            try {

                Date date = formatter.parse(dateInString);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_YEAR, 3);
                expirationDate = formatter.format(calendar.getTime());

            }catch (ParseException e) {
                e.printStackTrace();
            }

            return expirationDate;
        }

        private void sendEmail() {
            String importantDate = expirationDate();
            try {
                GMailSender sender = new GMailSender("jobcode00@gmail.com", "jobcode1201");
                sender.sendMail("Código de Verficación - JobCode",
                        "Estimado usuario:\n\n\nUsted ha recibido este correo gracias a que registró una publicación en nuestra APP "
                                + "móvil JobCode.\n\n"+"Su código de verificación es: " + random + "\nMediante este código usted podrá tener acceso "
                                + "a información sobre los estudiantes registrados, y así poder contactarles en caso de requerir sus servicios "
                                + "profesionales."+"\n\n\nLa fecha de expiración de su anuncio será: "+ importantDate +"\n\n\nGracias por usar nuestra APP!\n\nJobCode@2017",
                        "Admin@JobCode",
                        contact.getText().toString());
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        }

        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPreExecute() {
        }


        protected void onPostExecute(Boolean result) {
        }


        protected void onCancelled() {
        }
    }
}


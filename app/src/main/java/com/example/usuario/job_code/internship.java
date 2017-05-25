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

Clase: internship
Clase lógica que se encarga de captar los datos ingresados por la empresa y de guardarlos en la base de datos en el tipo de internship
 por medio del consumo de un Web Service. Utiliza la función de volley y se encarga de mapear (diccionario de los parámetros).*/
package com.example.usuario.job_code;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.HashMap;
import java.util.Map;

public class internship extends AppCompatActivity {

   private Button post;
   private String companyName;
   private EditText description, dueDate, position, contact;
   private ProgressDialog progress;

    /**
     * Método que se encarga de sobre-escribir la construccioón y validación de la parte gráfica del
     * sistema para la creación de la publicación de una nueva pasantía.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);
        post = (Button) findViewById(R.id.btPost);
        companyName = getIntent().getExtras().getString("companyName");
        description = (EditText) findViewById(R.id.ed_txDescription);
        dueDate = (EditText) findViewById(R.id.ed_txDueDate);
        position = (EditText) findViewById(R.id.ed_txPosition);
        contact = (EditText) findViewById(R.id.ed_txEmail);
        post = (Button) findViewById(R.id.btPost);

        progress= new ProgressDialog(this);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == post)
                sendPost();
                Intent next = new Intent(internship.this, Publish.class);
                startActivity(next);
            }
        });
    }

    /*Método para envíar un Post, captura los datos ingresados por la empresa, quita los espacios
    * innecesarios y conforma una solicitud al web service con los datos necesarios para enviarlos
    * @param
    * */
    private void sendPost(){
        final String company = companyName.trim();
        final String descript = description.getText().toString().trim();
        final String dueDat = dueDate.getText().toString().trim();
        final String posit = position.getText().toString().trim();
        final String email = contact.getText().toString().trim();
        progress.setMessage("Cargando datos...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTER+"companyName="+company+"&description="+descript+
                "&email="+email+"&dueDate="+dueDat+"&IDTypePost=1&Place_Position_SoftType="+posit, new Response.Listener<String>() {
            /**Método para sobre-escribir el método onResponse, comprueba que el servicio provea de una respuesta
            *que se encuentre disponible.
            * @param response
           */
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try{
                JSONObject json = new JSONObject(response);
                Toast.makeText(getApplicationContext(),json.getString("message")+"", Toast.LENGTH_LONG).show();
            }catch(JSONException e){
                    e.printStackTrace();
            }
            }

        },new Response.ErrorListener() {
            /**Método para sobre-escribir el método que escucha los errores, la presencia de un error para
             *informar al cliente.
             * @param
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Toast.makeText(getApplicationContext(),error.getMessage()+"Se ha producido un error", Toast.LENGTH_LONG).show();
            }
        }){
            /**Método para sobre-escribir el método que realiza el mapa de parámetros, con el fin de generar
            *un diccionario de todos los datos que se ingresan.
           * @param
          */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("companyName", company);
                params.put("description", descript);
                params.put("email", email);
                params.put("dueDate", dueDat);
                params.put("IDTypePost", "1");
                params.put("Place_Position_SoftType", posit);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }



}

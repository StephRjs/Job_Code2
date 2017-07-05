package com.example.usuario.job_code;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
/*Llamados a las librerías de JSon necesarias para el manejo de datos que se les envía y devuelven los
Web Services*/
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class Login extends AppCompatActivity {

    private Button Login,signUp;
    private EditText Username, Password;
    private TextView ForgotPassword;
    private ProgressDialog progress;
    private boolean loginStatus = true;
    URL url =null;
    HttpURLConnection client= null;
    String  nombre;
    String apellido1 ;
    String apellido2;
    String Cellphone  ;
    String CurrentAddress ;
    String Experience ;
    String PrimarySkill ;
    String MoreSkills ;
    String email;
    String password;

    /**
     * Método que se encarga de sobre-escribir la construccioón y validación de la parte gráfica del
     * sistema para el ingreso de la aplicacion.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = (Button) findViewById(R.id.bLogin);

        ForgotPassword = (TextView) findViewById(R.id.forgotPassword);
        Username = (EditText) findViewById(R.id.edUser);
        Password = (EditText) findViewById(R.id.edPassword);
        Login = (Button) findViewById(R.id.bLogin);
        progress= new ProgressDialog(this);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Username.getText().length() == 0||Password.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Debe de completar todos los datos",
                            Toast.LENGTH_LONG).show();
                }else {
                    if ((!Username.getText().toString().contains("@"))  || (!Username.getText().toString().contains("."))) {
                        Toast.makeText(getApplicationContext(), "El formato del correo es incorrecto debe contener @",
                                Toast.LENGTH_LONG).show();


                    } else {

                        if(v == Login)

                       sendPost();


                    }
                }
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == ForgotPassword) {
                    Intent next = new Intent(Login.this, PasswordRecovery.class);
                    startActivity(next);
                }
            }
        });
    }


    private void sendPost(){

        final String Usernam = Username.getText().toString().trim();
        final String Passwo = Password.getText().toString().trim();

         StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTERLoginEx +"email="+Usernam +
                "&password="+Passwo, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                progress.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),json.getString("name"), Toast.LENGTH_LONG).show();

                    if(json.getString("name")!= null ||  json.getString("lastname1")!= null || json.getString("lastname2")!= null || json.getString("cellphone")!= null    ) {


                        nombre = json.getString("name");
                        apellido1 = json.getString("lastname1");
                        apellido2 = json.getString("lastname2");
                        Cellphone = json.getString("cellphone");
                        CurrentAddress = json.getString("currentAddress");
                        Experience = json.getString("experience");
                        PrimarySkill = json.getString("primarySkill");
                        MoreSkills = json.getString("moreSkills");
                        email = json.getString("email");
                        password = Passwo;

                        Intent next = new Intent(Login.this, StudentMenu.class);
                        next.putExtra("nombre",nombre );
                        next.putExtra("primerApellido",apellido1);
                        next.putExtra("segundoApellido",apellido2);
                        next.putExtra("Cellphone",Cellphone);
                        next.putExtra("CurrentAddress",CurrentAddress);
                        next.putExtra("Experience",Experience);
                        next.putExtra("PrimarySkill",PrimarySkill);
                        next.putExtra("MoreSkills",MoreSkills);
                        next.putExtra("email",email);
                        next.putExtra("password",password);
                        startActivity(next);

                    }

                }catch(JSONException e){
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Toast.makeText(getApplicationContext(),error.getMessage()+"", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", Usernam);
                params.put("password", Passwo);

                return params;

            }
        };


        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);



}
}


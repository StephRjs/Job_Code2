/*Universidad de Costa Rica
Informática Empresarial
IF7201 - Gestión de Proyectos
Prof. MAP. Verny Fernández
Proyecto Job Code
Estudiantes:
Paula Álvarez Barrantes – B40301
Alejandra Anchía Pérez - B30388
César Bolaños Brenes - B31030
Stephanie Rojas Alfaro – A54827
I Ciclo, 2017

Clase: internship
Clase lógica que se encarga de captar los datos ingresados por la empresa y de guardarlos en la base de datos por medio del consumo
de un Web Service. Utiliza la función de volley y se encarga de mapear (diccionario de los parámetros).*/
package com.example.usuario.job_code;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class internship extends AppCompatActivity {

   private Button post;
   private String companyName;
   private EditText description, dueDate, position, contact;
   private ProgressDialog progress;

    URL url =null;
    HttpURLConnection client= null;

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
            }
        });
    }

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
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try{
                JSONObject json = new JSONObject(response);
                Toast.makeText(getApplicationContext(),json.getString("message"), Toast.LENGTH_LONG).show();
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

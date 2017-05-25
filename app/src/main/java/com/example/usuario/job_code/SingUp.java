package com.example.usuario.job_code;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class SingUp extends AppCompatActivity {
    EditText Nombre,Primerapellido,Segundoapellido,Email,Password;
    Button Register, createProfile;
    private ProgressDialog progress;

    URL url =null;
    HttpURLConnection client= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        Register = (Button) findViewById(R.id.bRegister);
        Nombre = (EditText) findViewById(R.id.etNombre);
        Primerapellido = (EditText) findViewById(R.id.etApellido1);
        Segundoapellido = (EditText) findViewById(R.id.etApellido2);
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Register = (Button) findViewById(R.id.bRegister);
        createProfile = (Button) findViewById(R.id.bCrearPerfil);
        progress= new ProgressDialog(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Register)
                    sendPost();
                /*Intent next = new Intent(SingUp.this, CreateProfile.class);
                startActivity(next);*/
            }
        });
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s_in = new Intent(SingUp.this, CreateProfile.class);
                startActivity(s_in);
            }
        });
    }

    private void sendPost(){

        final String Nombr = Nombre.getText().toString().trim();
        final String Primerapellid = Primerapellido.getText().toString().trim();
        final String Segundoapellid = Segundoapellido.getText().toString().trim();
        final String Emai = Email.getText().toString().trim();
        final String Passwor = Password.getText().toString().trim();
        progress.setMessage("Cargando datos...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTERSin +"email="+Emai+"&password="+Passwor+
                "&name="+Nombr+"&lastname1="+Primerapellid+"&lastname2="+Segundoapellid, new Response.Listener<String>() {
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
                params.put("email", Emai);
                params.put("password", Passwor);
                params.put("name", Nombr);
                params.put("lastname1", Primerapellid);
                params.put("lastname2", Segundoapellid);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }

}

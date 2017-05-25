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



public class Login extends AppCompatActivity {

    private Button Login;
    private EditText Username, Password;
    private ProgressDialog progress;
private boolean loginStatus = true;
    URL url =null;
    HttpURLConnection client= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = (Button) findViewById(R.id.bLogin);

        Username = (EditText) findViewById(R.id.edUser);
        Password = (EditText) findViewById(R.id.edPassword);
        Login = (Button) findViewById(R.id.bLogin);

        progress= new ProgressDialog(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Login)
                    sendPost();
            }
        });

    }

    private void sendPost(){

        final String Usernam = Username.getText().toString().trim();
        final String Passwo = Password.getText().toString().trim();

        progress.setMessage("Cargando datos...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTERLoginEx+"email="+Usernam+
                "&password="+Passwo, new Response.Listener<String>() {
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
                params.put("email", Usernam);
                params.put("password", Passwo);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }

}


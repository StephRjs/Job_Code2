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
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PasswordRecovery extends AppCompatActivity {

    private Button Send;
    private EditText Email;
    private ProgressDialog progress;
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        Send = (Button) findViewById(R.id.bSend);
        Email = (EditText) findViewById(R.id.edEmail);

        progress= new ProgressDialog(this);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().length() == 0 || (!Email.getText().toString().contains("@"))  || (!Email.getText().toString().contains("."))) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar un correo electrónico válido",
                            Toast.LENGTH_LONG).show();
                }else {
                    if(v == Send)
                        sendEmail();
                }
            }
        });
    }

    public void sendEmail() {
        final String email = Email.getText().toString().trim();
        newPassword = getPassword();

        progress.setMessage("Cargando...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_FORGOTPASS +"email="+email+"&password="+newPassword
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),json.getString("message"), Toast.LENGTH_LONG).show();
                    if(json.getString("email")!= null){
                        Toast.makeText(getApplicationContext(),"Correo enviado correctamente!", Toast.LENGTH_LONG).show();
                        EmailTask task = new EmailTask();
                        task.execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ha ocurrido un error. Intente de nuevo!",
                                Toast.LENGTH_LONG).show();
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
                params.put("email", email);
                params.put("password", newPassword);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }

    private String getPassword() {
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(9000);
        return Integer.toString(n);
    }



    private class EmailTask extends AsyncTask<Void, Integer, Boolean> {

        protected Boolean doInBackground(Void... params) {
            sendEmail();

            return true;
        }


        private void sendEmail() {
            try {
                GMailSender sender = new GMailSender("jobcode00@gmail.com", "jobcode1201");
                sender.sendMail("Cambio de contraseña - JobCode",
                        "Estimado usuario:\n\n\nUsted ha recibido este correo debido a que solicitó un cambio de contraseña en nuestra APP "
                                + "móvil JobCode.\n\n"+"Su nueva contraseña de ingreso es: " + newPassword + "\n\nEn caso de no haber solicitado "
                                + "cambio de contraseña, de igual manera se le solicita que realice el cambio debido a situaciones de seguridad. "
                                + "\n\n\nGracias por usar nuestra APP!\n\nJobCode@2017",
                        "Admin@JobCode",
                        Email.getText().toString());
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

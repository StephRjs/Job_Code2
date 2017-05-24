package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Service extends AppCompatActivity {

    private Button post;
    private String companyName;
    private EditText description, dueDate, place, contact;
    private ProgressDialog progress;

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
                if(v == post)
                    sendPost();
                Intent next = new Intent(Service.this, Publish.class);
                startActivity(next);
            }
        });
    }

    private void sendPost(){
        final String company = companyName.trim();
        final String descript = description.getText().toString().trim();
        final String dueDat = dueDate.getText().toString().trim();
        final String place_postion_softType = place.getText().toString().trim();
        final String email = contact.getText().toString().trim();
        progress.setMessage("Cargando datos...");
        progress.show();
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_REGISTER+"companyName="+company+"&description="+descript+
                "&email="+email+"&dueDate="+dueDat+"&IDTypePost=3&Place_Position_SoftType="+place_postion_softType, new Response.Listener<String>() {
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
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Toast.makeText(getApplicationContext(),error.getMessage()+"Se ha producido un error", Toast.LENGTH_LONG).show();
            }
        }){
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

}


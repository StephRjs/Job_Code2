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
import java.util.Random;

public class CompanyMenu extends AppCompatActivity {

    private Button Post;
    private Button SeeStudents;
    private EditText RandomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_menu);

        Post = (Button) findViewById(R.id.bPost);
        SeeStudents = (Button) findViewById(R.id.bSeeStudents);
        RandomCode = (EditText) findViewById(R.id.edRandomCode);

        SeeStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RandomCode.getText().length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Debe de ingresar su codigo aleatorio",
                            Toast.LENGTH_LONG).show();
                }else {
                    if(v == SeeStudents)
                        loginRandomCode();
                }
            }
        });


        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(v == Post) {
                        Intent next = new Intent(CompanyMenu.this, Publish.class);
                        startActivity(next);
                }
            }
        });
    }


    public void loginRandomCode() {
        final String randomCode = RandomCode.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.GET, Constants.URLRandomCode +"code="+randomCode
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject json = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),json.getString("message"), Toast.LENGTH_LONG).show();
                    if(json.getString("code")!= null){
                        Intent next = new Intent(CompanyMenu.this, ListStudents.class);
                        startActivity(next);
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
                Toast.makeText(getApplicationContext(),"No se encuentra el dato", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("RandomCode", randomCode);
                return params;

            }
        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }
}

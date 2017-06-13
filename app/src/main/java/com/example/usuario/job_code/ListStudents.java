package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alvar on 6/11/2017.
 */

public class ListStudents extends AppCompatActivity {
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liststudents);
        ListView list = (ListView)findViewById(android.R.id.list);


        String[] cars = {"car1", "car2", "car3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(list.getContext(),android.R.layout.simple_list_item_1, cars);
        list.setAdapter(adapter);
        progress.setMessage("Cargando datos...");
        progress.show();
        final StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_AllStudents,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                   // File file = new File (json.getInputStream());
                    //InputStream f = new FileInputStream(file);
                    Student s = new Student();
                    //s.readJsonStream(f);

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


        };
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);
    }


}

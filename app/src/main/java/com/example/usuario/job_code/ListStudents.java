package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alvar on 6/11/2017.
 */

public class ListStudents extends AppCompatActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liststudents);

        String url = "http://jobcode.azurewebsites.net/Login.svc/allstudents?";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                String email;
                String name;
                String lastname1;
                String lastname2;
                String primarySkill;
                String cellphone;

                ListView lis = (ListView)findViewById(R.id.list);
                List <String> l = new ArrayList<String>();


                try {
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject row = jsonObject.getJSONObject(i);
                        email = row.getString("email");
                        name = row.getString("name");
                        lastname1 = row.getString("lastname1");
                        lastname2 = row.getString("lastname2");
                        primarySkill = row.getString("primarySkill");
                       cellphone = row.getString("cellphone");
                        l.add("Nombre: " + name + " " + lastname1 + " " + lastname2 + ".\nHabilidad Principal: "+ primarySkill + ".  \nEmail: " + email + ". \nTeléfono: " + cellphone);

                    }
                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, l);
                    lis.setAdapter (ad);


                }catch (JSONException e){
                    e.printStackTrace();
                    System.out.println(e.toString());
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                Toast.makeText(getApplicationContext(),error.getMessage()+"", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);

    }


}

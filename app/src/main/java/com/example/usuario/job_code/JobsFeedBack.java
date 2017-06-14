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

public class JobsFeedBack extends AppCompatActivity {
    private Button bt_singIn;
    private Button bt_setUp;
    private ProgressDialog progress;
    private Spinner spin_types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_feed_back);
        bt_setUp = (Button)findViewById(R.id.bt_setUp);
        bt_singIn= (Button)findViewById(R.id.bt_singin);
        spin_types= (Spinner)findViewById(R.id.spin_types);
        spin_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                getAllPost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        bt_singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(JobsFeedBack.this, Login.class);
                startActivity(next);
            }
        });
        bt_setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(JobsFeedBack.this, SingUp.class);
                startActivity(next);
            }
        });}

    public void getAllPost(){
        String url = Constants.URL_GETALLPOST;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                String companyName;
                String postType;
                String position;

                ListView jobs = (ListView)findViewById(R.id.lv_jobs);
                List<String> l = new ArrayList<String>();
                String type = spin_types.getSelectedItem().toString();
                try {
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject row = jsonObject.getJSONObject(i);
                        companyName = row.getString("companyName");
                        companyName= companyName.replace('*',' ');
                        postType = row.getString("postType");
                        position = row.getString("placePositionSoftType");
                        position = position.replace('*',' ');
                        if((postType.equals("Internship")) && (type.equals("Pasantías"))){
                            postType = "Pasantía";
                            l.add(companyName + "\nTipo de oferta: " + postType + "\nPosición: "+ position);
                            }else {
                            if((postType.equals("Project")) && (type.equals("Proyectos"))){
                                postType= "Proyecto";
                                l.add(companyName + "\nTipo de oferta: " + postType + "\nSoftware: "+ position);
                            }else {
                                if ((postType.equals("Service")) && (type.equals("Servicios Estudiantiles"))){
                                    postType = "Servicio estudiantil";
                                    l.add(companyName + "\nTipo de oferta: " + postType + "\nLugar: " + position);
                                }else{
                                    if(type.equals("Filtrar")){
                                        if(postType.equals("Internship")) {
                                            postType = "Pasantía";
                                        l.add(companyName + "\nTipo de oferta: " + postType + "\nPosición: "+ position);
                                    }else {
                                        if(postType.equals("Project")){
                                            postType= "Proyecto";
                                            l.add(companyName + "\nTipo de oferta: " + postType + "\nSoftware: "+ position);
                                        }else {
                                            if (postType.equals("Service")){
                                                postType = "Servicio estudiantil";
                                                l.add(companyName + "\nTipo de oferta: " + postType + "\nLugar: " + position);
                                            }
                                        }
                                    }
                                }
                                }
                            }
                        }



                    }
                    ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, l);
                    jobs.setAdapter (ad);

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





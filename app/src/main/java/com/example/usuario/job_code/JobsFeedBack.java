package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_feed_back);
        bt_setUp = (Button)findViewById(R.id.bt_setUp);
        bt_singIn= (Button)findViewById(R.id.bt_singin);
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
        });

        String url = Constants.URL_GETALLPOST;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                String companyName;
                String description;
                String postType;


                ListView jobs = (ListView)findViewById(R.id.lv_jobs);
                List<String> l = new ArrayList<String>();


                try {
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject row = jsonObject.getJSONObject(i);
                        companyName = row.getString("companyName");
                        companyName= companyName.replace('*',' ');
                        description = row.getString("description");
                        description = description.replace('*',' ');
                        postType = row.getString("postType");
                        if(postType.equals("internship")){
                            postType = "Pasantía";
                        }else {
                            if(postType.equals("project")){
                                postType= "Proyecto";
                            }else{
                                postType= "Servicio estudiantil";
                            }
                        }

                        l.add(companyName + "\nTipo de oferta: " + postType + "\nDescripción: "+ description);

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


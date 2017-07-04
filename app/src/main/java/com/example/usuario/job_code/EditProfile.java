package com.example.usuario.job_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
       /* JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constants.URL_REGISTERLoginEx +"email="+Usernam+
                "&password="+Passwo,  new Response.Listener<JSONArray>() {

            public void onResponse(JSONArray jsonObject) {
                String nombre;
                String Apellido1;
                String Apellido2;
                String Cellphone;
                String CurrentAddress;
                String Experience;
                String PrimarySkill;
                String MoreSkills;
                for (int i = 0; i < jsonObject.length(); i++) {
                    JSONObject row = null;
                    try {
                        row = jsonObject.getJSONObject(i);
                        nombre = row.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
    }*/


    }
}

package com.example.usuario.job_code;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    private EditText Nombre, Primerapellido, Segundoapellido, Telefono,Direccionactual,Principalhabilidad,Otrashabilidades,
            Experiencia;
    private TextView Email;
    private Button Actualizar;
    String password;
    String email2;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        progress= new ProgressDialog(this);


        Nombre =  (EditText) findViewById(R.id.etNombreEdit);
        Primerapellido = (EditText) findViewById(R.id.etApellido1Edit);
        Segundoapellido = (EditText) findViewById(R.id.etApellido2Edit);
        Telefono = (EditText) findViewById(R.id.etCellphoneEdit);
        Direccionactual = (EditText) findViewById(R.id.etCurrentAddressEdit);
        Principalhabilidad = (EditText) findViewById(R.id.etPrimarySkillEdit);
        Otrashabilidades = (EditText) findViewById(R.id.etMoreSkillEdit);
        Experiencia = (EditText) findViewById(R.id.etExperienceEdit);
        Email = (TextView) findViewById(R.id.etEmailEdit);
        Actualizar = (Button) findViewById(R.id.bActualizarEdit);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        Nombre.setText(extras.getString("nombre"));
        Primerapellido.setText(extras.getString("primerApellido"));
        Segundoapellido.setText(extras.getString("segundoApellido"));
        Telefono.setText(extras.getString("Cellphone"));
        Direccionactual.setText(extras.getString("CurrentAddress"));
        Principalhabilidad.setText(extras.getString("PrimarySkill"));
        Experiencia.setText(extras.getString("Experience"));
        Otrashabilidades.setText(extras.getString("MoreSkills"));
        email2= extras.getString("email");
        Email.setText(email2);
        password = extras.getString("password") ;

        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nombre.getText().length() == 0||Primerapellido.getText().length() == 0 || Segundoapellido.getText().length() == 0
                        ||Telefono.getText().length() == 0 ||Direccionactual.getText().length() == 0 ||Principalhabilidad.getText().length() == 0
                        ||Experiencia.getText().length() == 0 ||Otrashabilidades.getText().length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Debe de completar todos los datos",
                            Toast.LENGTH_LONG).show();
                }else {


                        if(v == Actualizar)
                            sendPost();


                    }

            }
        });

    }

    private void sendPost(){

        final String  nombre = Nombre.getText().toString().trim();
        final String apellido1 = Primerapellido.getText().toString().trim();
        final String apellido2 = Segundoapellido.getText().toString().trim();
        final String Cellphone = Telefono.getText().toString().trim() ;
        final String CurrentAddress = Direccionactual.getText().toString().trim() ;
        final String Experience = Experiencia.getText().toString().trim();
        final String PrimarySkill = Principalhabilidad.getText().toString().trim();
        final String MoreSkills = Otrashabilidades.getText().toString().trim() ;


            StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_UpdateStudent+"email="+email2+"&password="+password+
                    "&name="+nombre+"&lastname1="+apellido1+"&lastname2="+apellido2+"&cellphone="+Cellphone+"&currentAddress="+CurrentAddress+"&primarySkill="+PrimarySkill+
                    "&moreSkills="+MoreSkills+"&experience="+Experience, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                progress.dismiss();
                try{

                    JSONObject json = new JSONObject(response);
                    if(json.getString("name")!= null ||  json.getString("lastname1")!= null || json.getString("lastname2")!= null || json.getString("cellphone")!= null    ) {

                        Toast.makeText(getApplicationContext(),"Se modificaron los datos con éxito", Toast.LENGTH_LONG).show();

                        Intent next = new Intent(EditProfile.this, JobsFeedBack.class);
                        startActivity(next);
                        
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
                params.put("email", email2);
                params.put("password", password);
                params.put("name", nombre);
                params.put("lastname1", apellido1);
                params.put("lastname2", apellido2);
                params.put("cellphone", Cellphone);
                params.put("currentAddress", CurrentAddress);
                params.put("primarySkill", PrimarySkill);
                params.put("moreSkills", MoreSkills);
                params.put("experience", Experience);
                return params;

            }
        };


        RequestQueue rQ = Volley.newRequestQueue(this);
        rQ.add(request);



    }
}

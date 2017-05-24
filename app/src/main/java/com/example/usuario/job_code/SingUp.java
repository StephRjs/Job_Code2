package com.example.usuario.job_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class SingUp extends AppCompatActivity {
 EditText nombre,apellido1,apellido2,Email,Password;
    Button Registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
    }

}

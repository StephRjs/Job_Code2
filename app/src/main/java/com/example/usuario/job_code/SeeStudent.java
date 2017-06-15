package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SeeStudent extends AppCompatActivity {
    TextView texto;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_student);
        texto = (TextView) findViewById(R.id.txNombre);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int dato = extras.getInt("posicion");
        ArrayList<String> miListaStu = (ArrayList<String>) getIntent().getStringArrayListExtra("miListaStu");
        texto.setText(miListaStu.get(dato));


    }
}

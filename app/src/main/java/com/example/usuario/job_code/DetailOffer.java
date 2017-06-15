package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailOffer extends AppCompatActivity {
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer);
        texto = (TextView) findViewById(R.id.txNombre);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int dato = extras.getInt("posicion");
        ArrayList<String> lista = (ArrayList<String>) getIntent().getStringArrayListExtra("miLista");
        texto.setText(lista.get(dato));

    }
}

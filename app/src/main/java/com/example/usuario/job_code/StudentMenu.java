package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentMenu extends AppCompatActivity {
    private Button bEditarPerfil;
    private Button bSeeStudents;
    private Button bOfertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        bEditarPerfil = (Button)findViewById(R.id.bEditarPerfil);
        bSeeStudents = (Button)findViewById(R.id.bSeeStudents);
        bOfertas = (Button)findViewById(R.id.bOfertas);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final String Nombre= extras.getString("nombre");
        final String Primerapellido= extras.getString("primerApellido");
        final String Segundoapellido=extras.getString("segundoApellido");
        final String Telefono = extras.getString("Cellphone");
        final String Direccionactual = extras.getString("CurrentAddress");
        final String Principalhabilidad = extras.getString("PrimarySkill");
        final String Experiencia = extras.getString("Experience");
        final String Otrashabilidades = extras.getString("MoreSkills");
        final String email2= extras.getString("email");
        final String password = extras.getString("password") ;


        bEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(StudentMenu.this, EditProfile.class);

                next.putExtra("nombre",Nombre );
                next.putExtra("primerApellido",Primerapellido);
                next.putExtra("segundoApellido",Segundoapellido);
                next.putExtra("Cellphone",Telefono);
                next.putExtra("CurrentAddress",Direccionactual);
                next.putExtra("Experience",Principalhabilidad);
                next.putExtra("PrimarySkill",Experiencia);
                next.putExtra("MoreSkills",Otrashabilidades);
                next.putExtra("email",email2);
                next.putExtra("password",password);
                startActivity(next);

            }
        });
        bSeeStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(StudentMenu.this, ListStudents.class);
                startActivity(next);

            }
        });
        bOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(StudentMenu.this, JobsFeedBack.class);
                startActivity(next);

            }
        });
    }
}

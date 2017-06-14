/*Universidad de Costa Rica
Informática Empresarial
IF7201 - Gestión de Proyectos
Prof. MAP. Verny Fernández
Proyecto Job Code
Estudiantes:
Paula Alvarez Barrantes – B40301
Alejandra Anchía Pérez - B30388
César Bolaños Brenes - B31030
Stephanie Rojas Alfaro – A54827
I Ciclo, 2017

Clase: Publish
Clase lógica que se encarga de captar los datos ingresados por la empresa y almacenarlos con el fin de saber
la página a la que se les debe de redirigir según el post que deseen realizar.*/
package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Publish extends AppCompatActivity {
    Button next, sign_in;
    EditText companyName;
    String company;
private RadioButton option1, option2, option3;
    /**
     * Método que se encarga de la construccioón y validación de la parte gráfica del
     * sistema para hacer la publicación de ofertas.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        companyName=(EditText)findViewById(R.id.txtCompanyName);
        company= companyName.getText().toString();
        next= (Button)findViewById(R.id.btNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (companyName.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Debe de indicar el nombre de la empresa",
                            Toast.LENGTH_LONG).show();
                }else {
                    option1 = (RadioButton) findViewById(R.id.internship);
                    if (option1.isChecked()) {
                        Intent next = new Intent(Publish.this, internship.class);
                        next.putExtra("companyName", companyName.getText() + "");
                        startActivity(next);
                    } else {
                        option2 = (RadioButton) findViewById(R.id.service);
                        if (option2.isChecked()) {
                            Intent next = new Intent(Publish.this, Service.class);
                            next.putExtra("companyName", companyName.getText() + "");
                            startActivity(next);
                        }
                        option3 = (RadioButton) findViewById(R.id.project);
                        if (option3.isChecked()) {
                            Intent next = new Intent(Publish.this, Project.class);
                            next.putExtra("companyName", companyName.getText() + "");
                            startActivity(next);
                        }

                    }
                }
            }
        });
           }

}

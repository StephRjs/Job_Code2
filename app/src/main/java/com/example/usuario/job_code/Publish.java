package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Publish extends AppCompatActivity {
    Button next;
    EditText companyName;
    String company;
private RadioButton option1, option2, option3;
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
                if (companyName.getText().equals("") || companyName.getText().equals(null))
                    companyName.setText("Escriba el nombre de la compañia");
                else {
                    option1 = (RadioButton) findViewById(R.id.internship);
                    if (option1.isChecked()) {
                        Intent next = new Intent(Publish.this, internship.class);
                        next.putExtra("companyName", companyName.getText()+"");
                        startActivity(next);
                    } else {
                        option2 = (RadioButton) findViewById(R.id.service);
                        if (option2.isChecked()) {
                            Intent next = new Intent(Publish.this, Service.class);
                            startActivity(next);
                        }
                        option3 = (RadioButton) findViewById(R.id.project);
                        if (option3.isChecked()) {
                            Intent next = new Intent(Publish.this, Project.class);
                            next.putExtra("parametro", "string");
                            startActivity(next);
                        }

                    }
                }
            }
        });
    }

}

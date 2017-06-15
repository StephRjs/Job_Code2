package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    private Button bt_student;
    private Button bt_company;
    private Button bt_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_company = (Button)findViewById(R.id.bt_company);
        bt_student = (Button)findViewById(R.id.bt_student);
        bt_list = (Button)findViewById(R.id.bt_list);
        bt_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Main.this, Publish.class);
                startActivity(next);
            }
        });
        bt_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Main.this, JobsFeedBack.class);
                startActivity(next);
            }
        });

        bt_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Main.this, ListStudents.class);
                startActivity(next);
            }
        });
    }
}

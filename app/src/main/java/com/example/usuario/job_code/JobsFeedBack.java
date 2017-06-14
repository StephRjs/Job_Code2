package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobsFeedBack extends AppCompatActivity {
    Button bt_singIn;
    Button bt_setUp;

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
    }
}

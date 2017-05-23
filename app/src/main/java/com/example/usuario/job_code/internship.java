package com.example.usuario.job_code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class internship extends AppCompatActivity {
    Button post;
    EditText companyName, description, dueDate, position, contact;
    URL url =null;
    HttpURLConnection client= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);
        post = (Button) findViewById(R.id.btPost);
        companyName = (EditText) findViewById(R.id.txtCompanyName);
        description = (EditText) findViewById(R.id.ed_txDescription);
        dueDate = (EditText) findViewById(R.id.ed_txDueDate);
        position = (EditText) findViewById(R.id.ed_txPosition);
        contact = (EditText) findViewById(R.id.ed_txEmail);
        post = (Button) findViewById(R.id.btPost);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
                Intent next = new Intent(internship.this, Publish.class);
                startActivity(next);
            }
        });
    }


    public void sendPost(){

        try{
            System.out.println("entro en el try");
            System.out.println(this.dueDate+"   " +this.companyName);
            url = new URL("http://jobcode.azurewebsites.net/Post.svc/addPost?companyName="+this.companyName+
                    "&description="+this.description+"&email="+this.contact+"&dueDate="+this.dueDate+"&IDTypePost=1&Place_Position_SoftType="+this.position);
            client = (HttpURLConnection)url.openConnection();

            client.setDoOutput(true);
            OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
            outputPost.flush();
            outputPost.close();

        }catch (Exception e){
            System.out.println("cayo en el cath");
            System.out.println(e);
        }
        finally {
            if(client != null)
                client.disconnect();
        }
    }
}

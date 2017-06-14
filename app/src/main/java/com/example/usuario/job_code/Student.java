package com.example.usuario.job_code;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;
import android.util.JsonReader;
import java.io.IOException;
/**
 * Created by alvar on 6/11/2017.
 */

public class Student {

    private String name;

    private String lastname1;

    private String lastname2;

    private String primarySkill;

    private String email;


    public Student (){

    }

    public Student(String name, String lastname1, String lastname2, String primarySkill, String email) {
        this.name = name;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.primarySkill = primarySkill;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname1(String lastname1) {
        this.lastname1 = lastname1;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getName() {
        return name;
    }

    public String getLastname1() {
        return lastname1;
    }

    public String getLastname2() {
        return lastname2;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Student> readJsonStream(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return readArrayStudent(reader);
        } finally {
            reader.close();
        }

    }

    public List readArrayStudent(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList students = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            students.add(readStudent(reader));
        }
        reader.endArray();
        return students;
    }

    public Student readStudent(JsonReader reader) throws IOException {
        String name = null;
        String lastname1 = null;
        String lastname2 = null;
        String primarySkill = null;
        String email = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String doc = reader.nextName();
            switch (doc) {
                case "name":
                    name = reader.nextString();
                    break;
                case "lastname1":
                    lastname1 = reader.nextString();
                    break;
                case "lastname2":
                    lastname2 = reader.nextString();
                    break;
                case "primarySkill":
                    primarySkill = reader.nextString();
                    break;
                case "email":
                    email = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Student(name, lastname1, lastname2, primarySkill, email);
    }
}

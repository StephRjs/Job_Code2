package com.example.usuario.job_code;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 28/06/2017.
 */

public class recieve  extends FirebaseInstanceIdService{

    public static final String TAG = "JOB_CODE";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Token: " + token);

        enviarTokenAlServidor(token);
    }

    private void enviarTokenAlServidor(String token) {
        Toast.makeText(getApplicationContext(),token , Toast.LENGTH_LONG).show();
    }
}
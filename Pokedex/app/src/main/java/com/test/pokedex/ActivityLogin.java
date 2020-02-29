package com.test.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class ActivityLogin extends AppCompatActivity {

    private static String TAG = "LOGIN";
    private Context context = this;
    private String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manageIntent(((ActivityLogin)context).getIntent());

        System.out.println(TAG + " " + username);
        Log.i(TAG,password);

    }

    public void manageIntent(Intent intent){
        if(intent != null){
            username = intent.getStringExtra("USERNAME");
            password = intent.getStringExtra("PASSWORD");
        }
    }

}

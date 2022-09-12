package com.example.localkamcom.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.localkamcom.Get_People.Get_Workers;
import com.example.localkamcom.Image_Upload.image;
import com.example.localkamcom.R;
import com.example.localkamcom.SignUp.SignUp_Login;

public class Splash extends AppCompatActivity {
    private static int TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, image.class);
                //SignUp_Login
                startActivity(intent);
                fileList();
                finish();
            }
        },TIME_OUT);
    }
}
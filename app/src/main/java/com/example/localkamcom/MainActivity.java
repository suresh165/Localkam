package com.example.localkamcom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.localkamcom.Get_People.Get_Workers;
import com.example.localkamcom.Hire_People.Hire;

public class MainActivity extends AppCompatActivity {
    CardView cardViewhire,cardViewejob;
    ProgressBar main_progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViewhire = findViewById(R.id.cardViewhire);

        cardViewejob = findViewById(R.id.cardViewejob);

        cardViewhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Get_Workers.class));
                Toast.makeText(MainActivity.this, "Welcome To Hire", Toast.LENGTH_SHORT).show();
            }
        });

        cardViewejob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Hire.class));
                Toast.makeText(MainActivity.this, "Welcome to Job", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
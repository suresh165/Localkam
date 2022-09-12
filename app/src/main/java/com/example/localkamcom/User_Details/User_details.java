package com.example.localkamcom.User_Details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.localkamcom.R;

public class User_details extends AppCompatActivity {
    TextView U_Username,U_phone,U_amount,U_age,U_qualification,U_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        U_Username = findViewById(R.id.U_Username);
        U_phone = findViewById(R.id.U_phone);
        U_amount = findViewById(R.id.U_amount);
        U_age = findViewById(R.id.U_age);
        U_qualification = findViewById(R.id.U_qualification);
        U_address = findViewById(R.id.U_address);

        U_Username.setText(getIntent().getStringExtra("name"));
        U_phone.setText(getIntent().getStringExtra("phone"));
        U_amount.setText(getIntent().getStringExtra("amount"));
        U_age.setText(getIntent().getStringExtra("age"));
        U_qualification.setText(getIntent().getStringExtra("expertise"));
        U_address.setText(getIntent().getStringExtra("address"));
    }
}
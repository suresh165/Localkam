package com.example.localkamcom.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localkamcom.API.api;
import com.example.localkamcom.Hire_People.Hire;
import com.example.localkamcom.MainActivity;
import com.example.localkamcom.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp_Login extends AppCompatActivity {

    private EditText etMobile, etName;
    private String mobile, name;
    private String url = "http://192.168.43.94:8080/api/localkam/";
    private Button btnRegister;
    private ProgressBar login_progress_bar;
    CheckBox checkBox;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__login);

//        mobile = name = "";
        etMobile = findViewById(R.id.phone_number_text);
        etName = findViewById(R.id.name_text);
        btnRegister = findViewById(R.id.generate_btn);
        login_progress_bar = findViewById(R.id.login_progress_bar);
        checkBox = findViewById(R.id.check_ok);
        textView = findViewById(R.id.check_txt);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");

        if (checkbox.equals("true")){
            Intent intent = new Intent(SignUp_Login.this,MainActivity.class);
            startActivity(intent);
        }else if (!checkbox.equals("false")){
            Toast.makeText(this, "Please Sign in", Toast.LENGTH_SHORT).show();
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(SignUp_Login.this, "Checked", Toast.LENGTH_SHORT).show();

                }else if (!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(SignUp_Login.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void process() {
        mobile = etMobile.getText().toString().trim();
        name = etName.getText().toString().trim();

        if (name.length()<4) {
            etName.setError("name is required minimum = 4 Characters");
        } else  if (mobile.length() < 10) {
            etMobile.setError("Mobile number Must be >= 10 Characters");
        } else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api myapi = retrofit.create(api.class);
            Call<model_login> call = myapi.adddata(mobile, name);

            call.enqueue(new Callback<model_login>() {
                @Override
                public void onResponse(Call<model_login> call, retrofit2.Response<model_login> response) {
                    etMobile.setText("");
                    etName.setText("");

                }

                @Override
                public void onFailure(Call<model_login> call, Throwable t) {

                }
            });
            login_progress_bar.setVisibility(View.VISIBLE);
            startActivity(new Intent(SignUp_Login.this, MainActivity.class));
            finish();
            Toast.makeText(SignUp_Login.this, "Thnak You for Register", Toast.LENGTH_SHORT).show();
        }
    }

}
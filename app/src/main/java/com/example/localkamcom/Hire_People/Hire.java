package com.example.localkamcom.Hire_People;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.localkamcom.Get_People.Get_Workers;
import com.example.localkamcom.MainActivity;
import com.example.localkamcom.R;
import com.example.localkamcom.API.api;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hire extends AppCompatActivity {
    EditText et_name, et_phone, et_age, t_amount, et_expertise,et_address;
    Button bt_save;
    ImageView img_map;
    private final String url = "http://192.168.43.94:8080/api/localkam/";
    FusedLocationProviderClient fusedLocationProviderClient;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        progressBar = findViewById(R.id.save_progress_bar);

        et_name = (EditText) findViewById(R.id.et_name_text);
        et_phone = (EditText) findViewById(R.id.et_phone_number);
        et_age = (EditText) findViewById(R.id.et_age);
        t_amount = (EditText) findViewById(R.id.et_amount);
        et_expertise = (EditText) findViewById(R.id.et_expertise);
        et_address = (EditText) findViewById(R.id.et_address);
        bt_save = (Button) findViewById(R.id.upload_Data);
        img_map = (ImageView) findViewById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processed();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Hire.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

                    location_fatch();
                else
                    ActivityCompat.requestPermissions(Hire.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

            }
        });
    }

    private void location_fatch() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(Hire.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);
                        et_address.setText(addressList.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Hire.this, "Location has been on", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void processed() {
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String age = et_age.getText().toString();
        String amount = t_amount.getText().toString();
        String expertise = et_expertise.getText().toString();
        String address = et_address.getText().toString();

//        validation
        if (TextUtils.isEmpty(name)){
            et_name.setError("Name is Required.");
        }else if (TextUtils.isEmpty(phone)){
            et_phone.setError("Phone is Required.");
        }else if (phone.length()<10){
            et_phone.setError("Mobile number Must be >= 10 Characters");
        } else if (TextUtils.isEmpty(age)){
            et_age.setError("Age is Required.");
        }else if (TextUtils.isEmpty(amount)) {
            t_amount.setError("Amount is Required.");

//        }else if (TextUtils.isEmpty(expertise)) {
//            et_expertise.setError("expertise is Required.");
//        }
        }else if (TextUtils.isEmpty(address)) {
            et_address.setError("Address is Required.");
        }else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api hirapi = retrofit.create(api.class);
            Call<hir_model> call = hirapi.hirData(name, phone, age, amount, expertise, address);

            call.enqueue(new Callback<hir_model>() {
                @Override
                public void onResponse(Call<hir_model> call, retrofit2.Response<hir_model> response) {
                    et_name.setText("");
                    et_phone.setText("");
                    et_age.setText("");
                    t_amount.setText("");
                    et_expertise.setText("");
                    et_address.setText("");

                }

                @Override
                public void onFailure(Call<hir_model> call, Throwable t) {
                }
            });
            startActivity(new Intent(Hire.this, MainActivity.class));
            Toast.makeText(Hire.this, "Thank You for Register", Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}
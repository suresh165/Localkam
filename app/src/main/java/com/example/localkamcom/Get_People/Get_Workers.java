package com.example.localkamcom.Get_People;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.localkamcom.Hire_People.hir_model;
import com.example.localkamcom.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Get_Workers extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter_get adapter_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__workers);
        recyclerView = findViewById(R.id.recycler_get_data);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //space count
        space_recyclerView space_item = new space_recyclerView(10);
        recyclerView.addItemDecoration(space_item);

        processdata();
    }

    private void processdata() {
        Call<List<hir_model>> retrive_data = api_controler
                .getInstance()
                .getapi()
                .getdata();

        retrive_data.enqueue(new Callback<List<hir_model>>() {
            @Override
            public void onResponse(Call<List<hir_model>> call, Response<List<hir_model>> response) {
                List<hir_model> data = response.body();
//item click getApplicationContext())
                adapter_get = new adapter_get(data,getApplicationContext());
                recyclerView.setAdapter(adapter_get);
                Toast.makeText(Get_Workers.this, "working", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<hir_model>> call, Throwable t) {

            }
        });
    }

}
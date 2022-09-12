package com.example.localkamcom.Get_People;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localkamcom.Hire_People.hir_model;
import com.example.localkamcom.R;
import com.example.localkamcom.User_Details.User_details;

import java.util.List;

import retrofit2.Callback;

public class adapter_get extends RecyclerView.Adapter<adapter_get.viewHolder> {
    List<hir_model> getData;
    Context context;

    public adapter_get(List<hir_model> getData, Context context) {
        this.getData = getData;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_data,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final hir_model temp = getData.get(position);

        holder.name.setText(getData.get(position).getName());
        holder.phone.setText(getData.get(position).getPhone());
        holder.age.setText(getData.get(position).getAge());
        holder.amount.setText(getData.get(position).getAmount());
        holder.expertise.setText(getData.get(position).getExpertise());
        holder.address.setText(getData.get(position).getAddress());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, User_details.class);
                intent.putExtra("name",temp.getName());
                intent.putExtra("phone",temp.getPhone());
                intent.putExtra("age",temp.getAge());
                intent.putExtra("amount",temp.getAmount());
                intent.putExtra("expertise",temp.getExpertise());
                intent.putExtra("address",temp.getAddress());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getData.size();    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,age,amount,expertise,address;
        CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.t_name);
            phone = itemView.findViewById(R.id.t_phone);
            age = itemView.findViewById(R.id.t_age);
            amount = itemView.findViewById(R.id.t_amount);
            expertise = itemView.findViewById(R.id.t_qualification);
            address = itemView.findViewById(R.id.t_address);
            cardView = itemView.findViewById(R.id.cardView_click);
        }
    }
}

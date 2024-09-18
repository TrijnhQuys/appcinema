package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.MainActivity_PhimCuaRap;
import com.heliwr.appbanvexemphim.Model.ALLRAP;
import com.heliwr.appbanvexemphim.R;

import java.util.ArrayList;

public class RV_ADAPTER_ALLRAP extends RecyclerView.Adapter<VIEWHOLDER_ALLRAP>{
    Context context;
    ArrayList<ALLRAP> data;

    public RV_ADAPTER_ALLRAP(Context context, ArrayList<ALLRAP> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_ALLRAP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_allrap, null);
        return new VIEWHOLDER_ALLRAP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_ALLRAP holder, int position) {
        ALLRAP allrap = data.get(position);
        holder.tvTenRap.setText(allrap.tenrap);
        holder.tvDiaChiRap.setText(allrap.diachi);
        holder.tvPhone.setText(allrap.phone);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_PhimCuaRap.class);
                intent.putExtra("phimcuarap", allrap);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void searchDatList(ArrayList<ALLRAP> searchList){
        data=searchList;
        notifyDataSetChanged();
    }
}

class VIEWHOLDER_ALLRAP extends RecyclerView.ViewHolder {
    TextView tvTenRap, tvDiaChiRap, tvPhone;

    public VIEWHOLDER_ALLRAP(@NonNull View itemView) {
        super(itemView);
        tvTenRap = itemView.findViewById(R.id.tvTenRap);
        tvDiaChiRap = itemView.findViewById(R.id.tvDiaChiRap);
        tvPhone = itemView.findViewById(R.id.tvPhone);
    }
}

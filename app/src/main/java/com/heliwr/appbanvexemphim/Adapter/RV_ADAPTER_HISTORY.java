package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.MainActivityChiTietHistory;
import com.heliwr.appbanvexemphim.Model.HISTORY;
import com.heliwr.appbanvexemphim.R;

import java.util.ArrayList;

public class RV_ADAPTER_HISTORY extends RecyclerView.Adapter<VIEWHOLDER_HISTORY> {
    Context context;
    ArrayList<HISTORY> data;

    public RV_ADAPTER_HISTORY(Context context, ArrayList<HISTORY> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_HISTORY onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VIEWHOLDER_HISTORY(LayoutInflater.from(context).inflate(R.layout.layout_1dong_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_HISTORY holder, int position) {
    HISTORY history=data.get(position);
    holder.tvtenphimhistory.setText(history.namefilm);
    holder.tvtenraphistory.setText(history.namerap);
    holder.tvngayxemhistory.setText(history.dateview);
    holder.tvgioxemhistory.setText(history.timestartwatching);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, MainActivityChiTietHistory.class);
            intent.putExtra("history", history);
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class VIEWHOLDER_HISTORY extends RecyclerView.ViewHolder{
    TextView tvtenphimhistory, tvtenraphistory, tvngayxemhistory, tvgioxemhistory;
    public VIEWHOLDER_HISTORY(@NonNull View itemView) {
        super(itemView);
        tvtenphimhistory=itemView.findViewById(R.id.tvtenphimhistory);
        tvgioxemhistory=itemView.findViewById(R.id.tvgioxemhistory);
        tvtenraphistory=itemView.findViewById(R.id.tvtenraphistory);
        tvngayxemhistory=itemView.findViewById(R.id.tvngaychieuhistory);
    }
}
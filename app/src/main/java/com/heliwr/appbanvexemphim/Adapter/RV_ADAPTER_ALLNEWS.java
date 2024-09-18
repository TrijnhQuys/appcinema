package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.MainActivity_ChiTietNews;
import com.heliwr.appbanvexemphim.Model.ALLNEWS;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ADAPTER_ALLNEWS extends RecyclerView.Adapter<VIEWHOLDER_ALLNEWS> {
    Context context;
    ArrayList<ALLNEWS>data;

    public RV_ADAPTER_ALLNEWS(Context context, ArrayList<ALLNEWS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_ALLNEWS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_1dong_news, null);
        return new VIEWHOLDER_ALLNEWS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_ALLNEWS holder, int position) {
        ALLNEWS allnews=data.get(position);
        holder.tvTitle.setText(allnews.tentin);
        holder.tvtomtat.setText(allnews.tomtat);
        holder.imgnew.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urlImgNews+allnews.img).into(holder.imgnew);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ChiTietNews.class);
                intent.putExtra("chitietnews", allnews);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class VIEWHOLDER_ALLNEWS extends RecyclerView.ViewHolder{
        ImageView imgnew;
        TextView tvTitle, tvtomtat;
    public VIEWHOLDER_ALLNEWS(@NonNull View itemView) {
        super(itemView);
        imgnew=itemView.findViewById(R.id.imgnews);
        tvTitle=itemView.findViewById(R.id.tvTitle);
        tvtomtat=itemView.findViewById(R.id.tvtomtat);
    }
}

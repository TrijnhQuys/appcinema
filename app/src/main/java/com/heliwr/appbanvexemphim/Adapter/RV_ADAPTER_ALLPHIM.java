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

import com.heliwr.appbanvexemphim.MainActivity_ChiTietPhim;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ADAPTER_ALLPHIM extends RecyclerView.Adapter<VIEWHOLDER_ALLPHIM> {
    Context context;
    ArrayList<ALLPHIM>data;

    public RV_ADAPTER_ALLPHIM(Context context, ArrayList<ALLPHIM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_ALLPHIM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VIEWHOLDER_ALLPHIM(LayoutInflater.from(context).inflate(R.layout.layout_1dong_phimsapchieu, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_ALLPHIM holder, int position) {
        ALLPHIM allphim=data.get(position);
        holder.texttenphim.setText(allphim.tenphim);
        holder.texttheloai.setText(allphim.theloai);
        holder.textkhoichieu.setText(allphim.khoichieu);
        holder.textthoiluong.setText(allphim.thoiluong);
        holder.imgphim.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urrImgAllPhim+allphim.imgphim).into(holder.imgphim);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ChiTietPhim.class);
                intent.putExtra("phim", allphim);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class VIEWHOLDER_ALLPHIM extends RecyclerView.ViewHolder{
    ImageView imgphim;
    TextView texttenphim, texttheloai, textthoiluong, textkhoichieu;

    public VIEWHOLDER_ALLPHIM(@NonNull View itemView) {
        super(itemView);
        imgphim =itemView.findViewById(R.id.imgphimsapchieu);
        texttenphim=itemView.findViewById(R.id.texttenphimsapchieu);
        texttheloai=itemView.findViewById(R.id.texttheloaisapchieu);
        textthoiluong=itemView.findViewById(R.id.textthoiluongsapchieu);
        textkhoichieu=itemView.findViewById(R.id.textkhoichieusapchieu);
    }
}

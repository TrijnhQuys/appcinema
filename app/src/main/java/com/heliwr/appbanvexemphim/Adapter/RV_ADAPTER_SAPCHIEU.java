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
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ADAPTER_SAPCHIEU extends RecyclerView.Adapter<VIEWHOLDER_SAPCHIEU> {
    Context context;
    ArrayList<PHIMSAPCHIEU>data;

    public RV_ADAPTER_SAPCHIEU(Context context, ArrayList<PHIMSAPCHIEU> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_SAPCHIEU onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VIEWHOLDER_SAPCHIEU(LayoutInflater.from(context).inflate(R.layout.layout_1dong_phimsapchieu, null)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_SAPCHIEU holder, int position) {
    PHIMSAPCHIEU phimsapchieu=data.get(position);
    holder.texttenphim.setText(phimsapchieu.tenphim);
    holder.texttheloai.setText(phimsapchieu.theloai);
    holder.textkhoichieu.setText(phimsapchieu.khoichieu);
    holder.textthoiluong.setText(phimsapchieu.thoiluong);
    holder.imgphim.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urrImgAllPhim+phimsapchieu.imgphim).into(holder.imgphim);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_ChiTietPhim.class);
                intent.putExtra("phimsapchieu", phimsapchieu);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class VIEWHOLDER_SAPCHIEU extends RecyclerView.ViewHolder{
    ImageView imgphim;
    TextView texttenphim, texttheloai, textthoiluong, textkhoichieu;
    public VIEWHOLDER_SAPCHIEU(@NonNull View itemView) {
        super(itemView);
        imgphim =itemView.findViewById(R.id.imgphimsapchieu);
        texttenphim=itemView.findViewById(R.id.texttenphimsapchieu);
        texttheloai=itemView.findViewById(R.id.texttheloaisapchieu);
        textthoiluong=itemView.findViewById(R.id.textthoiluongsapchieu);
        textkhoichieu=itemView.findViewById(R.id.textkhoichieusapchieu);
    }
}

package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.MainActivity_TheLoai;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.THELOAIPHIM;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ADAPTER_THELOAI extends RecyclerView.Adapter<VIEWHOLDER_THELOAI> {
    Context context;
    ArrayList<THELOAIPHIM> data;

    public RV_ADAPTER_THELOAI(Context context, ArrayList<THELOAIPHIM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_THELOAI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_theloaiphim, null);
        return new VIEWHOLDER_THELOAI(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_THELOAI holder, int position) {
        THELOAIPHIM theloai = data.get(position);
        holder.texttheloai.setText(theloai.tentheloai);
        holder.imgtheloai.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urlImgTheLoai + theloai.hinhtheloai).into(holder.imgtheloai);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_TheLoai.class);
                intent.putExtra("theloaiObject", theloai);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class VIEWHOLDER_THELOAI extends RecyclerView.ViewHolder {
    ImageView imgtheloai;
    TextView texttheloai;

    public VIEWHOLDER_THELOAI(@NonNull View itemView) {
        super(itemView);
        imgtheloai = itemView.findViewById(R.id.imgtheloai);
        texttheloai = itemView.findViewById(R.id.texttheloai);
    }
}


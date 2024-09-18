package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.MainActivity_ChiTietPhim;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.ALLRAP;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RVSEARCH_ADAPTER extends RecyclerView.Adapter<SEARCHVIEWHOLDER> implements Filterable{
    Context context;
    ArrayList<ALLPHIM> data;
    ArrayList<ALLPHIM> filterData;
    public RVSEARCH_ADAPTER(Context context, ArrayList<ALLPHIM> data) {
        this.context = context;
        this.data = data;
        this.filterData=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public SEARCHVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_phimsapchieu, null);
        return new SEARCHVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SEARCHVIEWHOLDER holder, int position) {
        ALLPHIM allphim=filterData.get(position);
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
        return filterData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            //được ghi đè để thực hiện quá trình lọc dữ liệu dựa trên ràng buộc
            protected FilterResults performFiltering(CharSequence constraint) {

                String query = constraint.toString().toLowerCase();//chuyen doi thanh chu thuong, thanh chuoi ky tu
                ArrayList<ALLPHIM> filteredList = new ArrayList<>();

                if (query.isEmpty()) {
                    filteredList.addAll(data);
                } else {
                    for (ALLPHIM room : data) {
                        if (room.tenphim.toLowerCase().contains(query)) {
                            filteredList.add(room);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterData.clear();
                filterData.addAll((ArrayList<ALLPHIM>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
class SEARCHVIEWHOLDER extends RecyclerView.ViewHolder{
    ImageView imgphim;
    TextView texttenphim, texttheloai, textthoiluong, textkhoichieu;

    public SEARCHVIEWHOLDER(@NonNull View itemView) {
        super(itemView);
        imgphim =itemView.findViewById(R.id.imgphimsapchieu);
        texttenphim=itemView.findViewById(R.id.texttenphimsapchieu);
        texttheloai=itemView.findViewById(R.id.texttheloaisapchieu);
        textthoiluong=itemView.findViewById(R.id.textthoiluongsapchieu);
        textkhoichieu=itemView.findViewById(R.id.textkhoichieusapchieu);
    }
}

package com.heliwr.appbanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.EventBus.TinhTongEvent;
import com.heliwr.appbanvexemphim.Interface.IImageClickListenner;
import com.heliwr.appbanvexemphim.Model.ALLRAP;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.THUCAN;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RV_ADAPTER_CHONTHUCAN extends RecyclerView.Adapter<VIEWHOLDER_CHONTHUCAN> {
    Context context;
    ArrayList<THUCAN> data;

    public RV_ADAPTER_CHONTHUCAN(Context context, ArrayList<THUCAN> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_CHONTHUCAN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VIEWHOLDER_CHONTHUCAN(LayoutInflater.from(context).inflate(R.layout.layout_1dong_thucan, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_CHONTHUCAN holder, int position) {
        THUCAN thucan=data.get(position);
        holder.tvTenthucan.setText(thucan.getTenthucan());
        holder.tvMota.setText(thucan.mota);
        holder.tvSoluong.setText(thucan.soluong+"");
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(thucan.getGia());
        holder.tvGia.setText(formattedPrice);
        holder.imgthucan.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urlImgThucAn+thucan.imgthucan).into(holder.imgthucan);
        holder.setClickListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if (giatri==1){
                    if (data.get(pos).getSoluong()>0){
                        int soluongmoi=data.get(pos).getSoluong()-1;
                        data.get(pos).setSoluong(soluongmoi);
                    }
                } else if (giatri==2) {
                    if (data.get(pos).getSoluong()<11){
                        int soluongmoi=data.get(pos).getSoluong()+1;
                        data.get(pos).setSoluong(soluongmoi);
                    }
                }
                holder.tvSoluong.setText(data.get(pos).getSoluong()+"");
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                long gia = data.get(pos).getSoluong() * data.get(pos).getGia();
                String formattedPrice2 = decimalFormat.format(gia);
                holder.tvtonggiathucan.setText(formattedPrice2);
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class VIEWHOLDER_CHONTHUCAN extends RecyclerView.ViewHolder implements  View.OnClickListener{
    ImageView imgthucan, imgtang, imggiam;
    TextView tvTenthucan, tvMota, tvGia, tvSoluong, tvtonggiathucan;
    IImageClickListenner clickListenner;

    public VIEWHOLDER_CHONTHUCAN(@NonNull View itemView) {
        super(itemView);
        imgthucan=itemView.findViewById(R.id.imgthucan);
        tvTenthucan=itemView.findViewById(R.id.tvtenthucan);
        tvMota=itemView.findViewById(R.id.tvmota);
        tvGia=itemView.findViewById(R.id.tvgia);
        imgtang=itemView.findViewById(R.id.imgtang);
        imggiam=itemView.findViewById(R.id.imggiam);
        tvSoluong=itemView.findViewById(R.id.tvsoluong);
        tvtonggiathucan=itemView.findViewById(R.id.tvtonggiathucan);

        //EVENT CLICK
        imgtang.setOnClickListener(this);
        imggiam.setOnClickListener(this);
    }
    public void setClickListenner(IImageClickListenner clickListenner) {
        this.clickListenner = clickListenner;
    }

    @Override
    public void onClick(View v) {
        if (v == imggiam){
            clickListenner.onImageClick(v, getAdapterPosition(), 1);
            //1 TRU
        }else if (v ==imgtang){
            clickListenner.onImageClick(v, getAdapterPosition(), 2);
        }
    }
}

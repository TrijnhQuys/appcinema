package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.heliwr.appbanvexemphim.Model.ALLNEWS;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.squareup.picasso.Picasso;

public class MainActivity_ChiTietNews extends AppCompatActivity {
    ImageView imgnew;
    TextView tvNoiDung, tvTitle;
    ALLNEWS allnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_news);
        imgnew=findViewById(R.id.imgchitietNews);
        tvNoiDung=findViewById(R.id.tvchitietNoiDung);
        tvTitle=findViewById(R.id.tvTitleChiTietNew);

        Intent intent=getIntent();
        allnews= (ALLNEWS) intent.getSerializableExtra("chitietnews");

        if (allnews!=null){
            Picasso.get().load(SERVER.urlImgNews+allnews.img).into(imgnew);
            tvNoiDung.setText(allnews.noidung);
            tvTitle.setText(allnews.tentin);
        }
    }
}
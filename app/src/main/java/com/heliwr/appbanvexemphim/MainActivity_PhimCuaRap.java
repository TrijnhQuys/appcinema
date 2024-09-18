package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter.ViewPagerAdapterPhimCuaRap;
import com.heliwr.appbanvexemphim.Model.ALLRAP;

public class MainActivity_PhimCuaRap extends AppCompatActivity {
    TextView tvTenRap, tvPhone, tvDiaChi;
    ALLRAP allrap;
    static TabLayout tabLayoutPhimCuaRap;
    ViewPager2 viewPager2PhimCuaRap;
    ViewPagerAdapterPhimCuaRap viewPagerAdapterPhimCuaRap;
    private static String tenRap, selecttime;
    RadioButton btntim1, btntime2, btntime3, btntime4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phim_cua_rap);
        anhxa();
        Intent intent=getIntent();
        allrap=(ALLRAP) intent.getSerializableExtra("phimcuarap");
        if (allrap!=null){
            tvTenRap.setText(allrap.tenrap);
            tenRap=allrap.tenrap;
            tvDiaChi.setText(allrap.diachi);
            tvPhone.setText(allrap.phone);
        }
        btntim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecttime = btntim1.getText().toString();
            }
        });

        btntime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecttime = btntime2.getText().toString();
            }
        });

        btntime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecttime = btntime3.getText().toString();
            }
        });

        btntime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecttime = btntime4.getText().toString();
            }
        });

        TabLayout.Tab tabngay1 = tabLayoutPhimCuaRap.newTab();
        tabngay1.setText(Html.fromHtml("Hôm Nay<br><small><small>10/5</small></small>"));
        tabLayoutPhimCuaRap.addTab(tabngay1);

        TabLayout.Tab tabngay2 = tabLayoutPhimCuaRap.newTab();
        tabngay2.setText(Html.fromHtml("Thứ 7<br><small><small>11/5</small></small>"));
        tabLayoutPhimCuaRap.addTab(tabngay2);

        TabLayout.Tab tabngay3=tabLayoutPhimCuaRap.newTab();
        tabngay3.setText(Html.fromHtml("CN<br><small><small>12/5</small></small>"));
        tabLayoutPhimCuaRap.addTab(tabngay3);

        TabLayout.Tab tabngay4=tabLayoutPhimCuaRap.newTab();
        tabngay4.setText(Html.fromHtml("Thứ 2<br><small><small>13/5</small></small>"));
        tabLayoutPhimCuaRap.addTab(tabngay4);

        viewPagerAdapterPhimCuaRap=new ViewPagerAdapterPhimCuaRap(this);
        viewPager2PhimCuaRap.setAdapter(viewPagerAdapterPhimCuaRap);

        tabLayoutPhimCuaRap.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2PhimCuaRap.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager2PhimCuaRap.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayoutPhimCuaRap.getTabAt(position).select();
            }
        });

    }
    public static String setTenRap(){
        String tenRap1 = tenRap;
        return tenRap1;
    }
    public static String setTime(){
        String timeselected=selecttime;
        return timeselected;
    }
    public static boolean isTabSelected(int position) {
        TabLayout.Tab selectedTab = tabLayoutPhimCuaRap.getTabAt(position);
        return selectedTab != null && selectedTab.isSelected();
    }
    public void anhxa(){
        tvTenRap=findViewById(R.id.tvTenRap);
        tvDiaChi=findViewById(R.id.tvDiaChiRap);
        tvPhone=findViewById(R.id.tvPhone);
        tabLayoutPhimCuaRap=findViewById(R.id.tabLayoutPhimCuaRap);
        viewPager2PhimCuaRap=findViewById(R.id.viewPager2PhimCuaRap);
        btntim1=findViewById(R.id.btntime1);
        btntime2=findViewById(R.id.btntime2);
        btntime3=findViewById(R.id.btntime3);
        btntime4=findViewById(R.id.btntime4);
    }
}
package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter.ViewPagerAdapterChiTiet;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.UserManager;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity_ChiTietPhim extends AppCompatActivity {
    ImageView imgchitiet;
    TextView tvtenphimchitiet, tvthoiluong, tvngaychieu, tvquocgia, tvnhasanxuat, tvtheloai, tvdaodien, tvdienvien;
    AppCompatButton btndatvechitiet;
    ALLPHIM allphim;
    PHIMDANGCHIEU phimdangchieu;
    PHIMSAPCHIEU phimsapchieu;
    TabLayout tabLayoutChiTiet;
    ViewPager2 viewPager2TrailerChitiet;
    ViewPagerAdapterChiTiet viewPagerAdapterChiTiet;
    LinearLayout containerDialofLayoutAccoount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_phim);
        anhxa();
        viewPagerAdapterChiTiet = new ViewPagerAdapterChiTiet(this);
        viewPager2TrailerChitiet.setAdapter(viewPagerAdapterChiTiet);

        tabLayoutChiTiet.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2TrailerChitiet.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2TrailerChitiet.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayoutChiTiet.getTabAt(position).select();
            }
        });

        // Lấy ngày hiện tại
//        Calendar calendar = Calendar.getInstance();
//        Date customDate = calendar.getTime();


        // Đặt ngày tự muốn
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024); // Năm
        calendar.set(Calendar.MONTH, Calendar.MAY); // Tháng
        calendar.set(Calendar.DAY_OF_MONTH, 10); // Ngày

        Date customDate = calendar.getTime();
        Date currentDate = new Date();

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Intent intent = getIntent();

        boolean UserLoggedIn = UserManager.getuserloggedin(MainActivity_ChiTietPhim.this);

//        Toast.makeText(this, "Login: " + UserLoggedIn, Toast.LENGTH_SHORT).show();
        phimdangchieu = (PHIMDANGCHIEU) intent.getSerializableExtra("phimdangchieu");
        allphim = (ALLPHIM) intent.getSerializableExtra("phim");
        phimsapchieu = (PHIMSAPCHIEU) intent.getSerializableExtra("phimsapchieu");

        if (phimdangchieu != null) {
            Picasso.get().load(SERVER.urrImgAllPhim + phimdangchieu.imgphim).into(imgchitiet);
            tvtenphimchitiet.setText(phimdangchieu.tenphim);
            tvthoiluong.setText(phimdangchieu.thoiluong);
            tvquocgia.setText(phimdangchieu.quocgia);
            tvngaychieu.setText(phimdangchieu.khoichieu);
            tvtheloai.setText(phimdangchieu.theloai);
            tvnhasanxuat.setText(phimdangchieu.nhasanxuat);
            tvdaodien.setText(phimdangchieu.daodien);
            tvdienvien.setText(phimdangchieu.dienvien);
        }
        if (allphim != null) {
            Picasso.get().load(SERVER.urrImgAllPhim + allphim.imgphim).into(imgchitiet);
            tvtenphimchitiet.setText(allphim.tenphim);
            tvthoiluong.setText(allphim.thoiluong);
            tvquocgia.setText(allphim.quocgia);
            tvngaychieu.setText(allphim.khoichieu);
            tvtheloai.setText(allphim.theloai);
            tvnhasanxuat.setText(allphim.nhasanxuat);
            tvdaodien.setText(allphim.daodien);
            tvdienvien.setText(allphim.dienvien);
        }
        if (phimsapchieu != null) {
            Picasso.get().load(SERVER.urrImgAllPhim + phimsapchieu.imgphim).into(imgchitiet);
            tvtenphimchitiet.setText(phimsapchieu.tenphim);
            tvthoiluong.setText(phimsapchieu.thoiluong);
            tvquocgia.setText(phimsapchieu.quocgia);
            tvngaychieu.setText(phimsapchieu.khoichieu);
            tvtheloai.setText(phimsapchieu.theloai);
            tvnhasanxuat.setText(phimsapchieu.nhasanxuat);
            tvdaodien.setText(phimsapchieu.daodien);
            tvdienvien.setText(phimsapchieu.dienvien);
        }
        btndatvechitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date batdauDate=null;
                if (allphim != null&&allphim.kethuc!=null&&allphim.khoichieu!=null) {
                    try {
                        batdauDate=dateFormat.parse(allphim.khoichieu);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (UserLoggedIn) {
                    if (phimsapchieu != null) {
                        Toast.makeText(MainActivity_ChiTietPhim.this, "Chưa có lịch chiếu cho phim này", Toast.LENGTH_SHORT).show();
                    } else if (batdauDate!=null&&batdauDate.after(customDate)){
                        Toast.makeText(MainActivity_ChiTietPhim.this, "Chưa có lịch chiếu cho phim này", Toast.LENGTH_SHORT).show();
                    } else{
                        Intent intent = new Intent(MainActivity_ChiTietPhim.this, MainActivityChonRap.class);
                        intent.putExtra("datallphim", allphim);
                        intent.putExtra("datphimdangchieu", phimdangchieu);
                        startActivity(intent);
                    }
                } else {
                    showLoginDialogAccount();
                }
            }
        });

    }
    private void showLoginDialogAccount() {
        containerDialofLayoutAccoount = findViewById(R.id.containerDialogLayoutAccount);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_ChiTietPhim.this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, containerDialofLayoutAccoount);
        builder.setView(view);
        TextView tvTitle1 = view.findViewById(R.id.tvTitle1);
        TextView tvTitle2 = view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES = view.findViewById(R.id.btnYES);
        AppCompatButton btnNO = view.findViewById(R.id.btnNO);
        AlertDialog alertDialog = builder.create();
        tvTitle1.setText("BẠN CHƯA ĐĂNG NHẬP");
        tvTitle2.setText("Bạn chưa đăng nhập, đăng nhập để có thể đặt vé xem phim");
        btnYES.setText("YES");
        btnNO.setText("NO");
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_ChiTietPhim.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void anhxa() {
        imgchitiet = findViewById(R.id.imgphimchitiet);
        tvtenphimchitiet = findViewById(R.id.tvtenphimchitiet);
        tvthoiluong = findViewById(R.id.tvthoigianchieu);
        tvquocgia = findViewById(R.id.tvquocgia);
        tvngaychieu = findViewById(R.id.tvngaychieu);
        tvtheloai = findViewById(R.id.tvtheloai);
        tvnhasanxuat = findViewById(R.id.tvnhasanxuat);
        tvdaodien = findViewById(R.id.tvdaodien);
        tvdienvien = findViewById(R.id.tvdienvien);

        tabLayoutChiTiet = findViewById(R.id.tabLayoutChiTiet);
        viewPager2TrailerChitiet = findViewById(R.id.viewPager2TrailerChiTiet);

        btndatvechitiet = findViewById(R.id.btndatvechitiet);

    }
}
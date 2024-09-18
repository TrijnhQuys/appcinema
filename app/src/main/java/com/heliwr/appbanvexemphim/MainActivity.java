package com.heliwr.appbanvexemphim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_ACCOUNT;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_HOME;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_NEWS;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_THEATER;
import com.heliwr.appbanvexemphim.Model.UserManager;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FRAGMENT_HOME fragment_home = new FRAGMENT_HOME();
    FRAGMENT_ACCOUNT fragment_account = new FRAGMENT_ACCOUNT();
    FRAGMENT_NEWS fragment_news = new FRAGMENT_NEWS();
    FRAGMENT_THEATER fragment_theater = new FRAGMENT_THEATER();
    BottomNavigationView bottomNavigationView;
    AppCompatButton btnsearch;
    private boolean userLoggedIn;
    BadgeDrawable badgeDrawableHome, badgeDrawableNews, badgeDrawableTheater, badgeDrawableAccount;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        bottomNavigationView.setBackground(null);
        LoafFragment(new FRAGMENT_HOME());
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        userLoggedIn = intent.getBooleanExtra("userLoggedIn", false);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                switch (i) {
                    case R.id.mnhome:
                        LoafFragment(fragment_home);
                        if (badgeDrawableHome != null) {
                            badgeDrawableHome.setVisible(false);
                        }
                        ;
                        toolbar.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mntheater:
                        LoafFragment(fragment_theater);
                        if (badgeDrawableTheater != null) {
                            badgeDrawableTheater.setVisible(false);
                        }
                        ;
                        toolbar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Rạp Phim", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mnnews:
                        LoafFragment(fragment_news);
                        if (badgeDrawableNews != null) {
                            badgeDrawableNews.setVisible(false);
                        }
                        ;
                        toolbar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Tin Tức", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mnaccount:
                        if (userLoggedIn) {
                            LoafFragment(fragment_account);
                            if (badgeDrawableAccount != null) {
                                badgeDrawableAccount.setVisible(false);
                            }
                            ;
                            toolbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Tài Khoản", Toast.LENGTH_SHORT).show();
                        } else {
                           LoafFragment(fragment_account);
                        }
                        break;
                }
                // Hiển thị lại thanh Toolbar khi quay lại trang chủ
                if (i != R.id.mnhome) {
                    toolbar.setVisibility(View.GONE);
                }
                return true;
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivitySearch.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void anhxa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomnavigationview_main);
        toolbar = findViewById(R.id.toolbarrap);
        btnsearch = findViewById(R.id.search_main);
    }
    public void LoafFragment(Fragment f) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, f);
        fragmentTransaction.commit();
    }
}
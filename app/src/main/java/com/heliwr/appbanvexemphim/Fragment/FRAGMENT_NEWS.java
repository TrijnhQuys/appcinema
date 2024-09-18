package com.heliwr.appbanvexemphim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter.ViewPagerAdapterTinTuc;
import com.heliwr.appbanvexemphim.R;

public class FRAGMENT_NEWS extends Fragment {
    ViewPager2 viewPager2News;
    ViewPagerAdapterTinTuc viewPagerAdapterTinTuc;
    TabLayout tabLayoutTinTuc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        TabLayout.Tab tabBinhLuan= tabLayoutTinTuc.newTab();
        tabBinhLuan.setText("Bình Luận");
        tabLayoutTinTuc.addTab(tabBinhLuan);

        TabLayout.Tab tabDienAnh= tabLayoutTinTuc.newTab();
        tabDienAnh.setText("Điện Ảnh");
        tabLayoutTinTuc.addTab(tabDienAnh);
        TabLayout.Tab tabDienVien= tabLayoutTinTuc.newTab();
        tabDienVien.setText("Diễn Viên");
        tabLayoutTinTuc.addTab(tabDienVien);


        viewPagerAdapterTinTuc = new ViewPagerAdapterTinTuc(this);
        viewPager2News.setAdapter(viewPagerAdapterTinTuc);
        tabLayoutTinTuc.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            viewPager2News.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2News.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayoutTinTuc.getTabAt(position).select();
            }
        });
    }
    public void anhxa(View view){
        viewPager2News=view.findViewById(R.id.viewPager2TinTuc);
        tabLayoutTinTuc=view.findViewById(R.id.tabLayoutTinTuc);
    }
}

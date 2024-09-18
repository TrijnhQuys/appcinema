package com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_NEWS;
import com.heliwr.appbanvexemphim.Fragment.FragmentNews.BinhLuanFragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentNews.DienAnhFragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentNews.DienVienFragment;

public class ViewPagerAdapterTinTuc extends FragmentStateAdapter {
    public ViewPagerAdapterTinTuc(@NonNull FRAGMENT_NEWS fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new BinhLuanFragment();
            case 1:return new DienAnhFragment();
            case 2:return new DienVienFragment();
            default:return new BinhLuanFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

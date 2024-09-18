package com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.heliwr.appbanvexemphim.Fragment.FragmentPhim.DangChieuFragment;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_HOME;
import com.heliwr.appbanvexemphim.Fragment.FragmentPhim.SapChieuFragment;

public class ViewPagerAdapterHome extends FragmentStateAdapter {
    public ViewPagerAdapterHome(@NonNull FRAGMENT_HOME fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DangChieuFragment();
            case 1:
                return new SapChieuFragment();
            default:
                return new DangChieuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}

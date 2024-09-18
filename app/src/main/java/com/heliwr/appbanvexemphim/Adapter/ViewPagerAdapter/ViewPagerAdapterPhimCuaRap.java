package com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate1Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate2Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate3Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate4Fragment;

public class ViewPagerAdapterPhimCuaRap extends FragmentStateAdapter {
    public ViewPagerAdapterPhimCuaRap(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PhimDate1Fragment();
            case 1:
                return new PhimDate2Fragment();
            case 2:
                return new PhimDate3Fragment();
            case 3:
                return new PhimDate4Fragment();
            default:
                return new PhimDate1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

package com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.heliwr.appbanvexemphim.Fragment.FragmentChiTietPhim.MoTaFragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentChiTietPhim.TrailerFragment;

public class ViewPagerAdapterChiTiet extends FragmentStateAdapter {
    public ViewPagerAdapterChiTiet(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new MoTaFragment();
            case 1:return new TrailerFragment();
            default:return new MoTaFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

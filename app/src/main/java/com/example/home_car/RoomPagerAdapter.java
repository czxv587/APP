package com.example.home_car;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.home_car.DeviceFragment.BathroomFragment;
import com.example.home_car.DeviceFragment.KitchenFragment;
import com.example.home_car.DeviceFragment.LivingRoomFragment;

public class RoomPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 3;

    public RoomPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new KitchenFragment();
            case 1:
                return new LivingRoomFragment();
            case 2:
                return new BathroomFragment();
            default:
                return new KitchenFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}

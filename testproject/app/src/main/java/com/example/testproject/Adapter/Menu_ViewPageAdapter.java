package com.example.testproject.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.Fragment.MenuCoffeeFragment;
import com.example.testproject.Fragment.MenuSmoothieFragment;
import com.example.testproject.Fragment.MenuTeaFragment;

public class Menu_ViewPageAdapter extends FragmentStatePagerAdapter {


    public Menu_ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                return new MenuCoffeeFragment();

            case 1:
                return new MenuTeaFragment();

            case 2:
                return new MenuSmoothieFragment();

            default:return new MenuCoffeeFragment();
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Coffee";
                break;

            case 1:
                title = "Tea";
                break;

            case 2:
                title = "Smoothie";
                break;

        }
        return title;
    }
}

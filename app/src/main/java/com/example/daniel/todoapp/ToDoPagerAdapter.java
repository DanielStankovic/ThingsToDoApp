package com.example.daniel.todoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ToDoPagerAdapter extends FragmentPagerAdapter{

    static final int NUM_ITEMS = 2;
    static final String TAB_TITILES[] = new String[]{"Active", "Completed"};

    public ToDoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle args = new Bundle();
        ItemsPreviewFragment itemsPreviewFragment = new ItemsPreviewFragment();


        switch (position){
            case 0:
                args.putString("mode", "active");
                itemsPreviewFragment.setArguments(args);
                return itemsPreviewFragment;
            case 1:
                args.putString("mode", "completed");
                itemsPreviewFragment.setArguments(args);
                return itemsPreviewFragment;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITILES[position];
    }
}

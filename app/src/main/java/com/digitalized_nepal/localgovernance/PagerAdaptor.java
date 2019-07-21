package com.digitalized_nepal.localgovernance;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PagerAdaptor extends FragmentStatePagerAdapter {

    int tabCount;
    Context context;

    public PagerAdaptor(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new governmentupdates();
            case 1:
                return new display_problems();

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

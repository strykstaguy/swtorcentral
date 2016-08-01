package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlanetPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    //final int PAGE_COUNT = 3;
    //private static final String[] titles = { "Information ", "Datacrons", "Lore"};
    Context context;
 
    public PlanetPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
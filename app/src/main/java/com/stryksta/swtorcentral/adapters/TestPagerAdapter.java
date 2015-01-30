package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stryksta.swtorcentral.TestTab1;
import com.stryksta.swtorcentral.TestTab2;


public class TestPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private static final String[] titles = { "Information", "Companions"};
    Context context;

    public TestPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            // Open FragmentTab1.java
            case 0:
                TestTab1 fragmenttab1 = new TestTab1();
                return fragmenttab1;

            // Open FragmentTab2.java
            case 1:
                TestTab2 fragmenttab2 = new TestTab2();
                return fragmenttab2;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
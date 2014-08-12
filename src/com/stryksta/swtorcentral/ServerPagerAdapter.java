package com.stryksta.swtorcentral;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class ServerPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT = 2;
    private static final String[] titles = { "US Servers", "European Servers" };
    Context context;
 
    public ServerPagerAdapter(FragmentManager fm) {
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
        	ServerTab1 fragmenttab1 = new ServerTab1();
            return fragmenttab1;
 
            // Open FragmentTab2.java
        case 1:
        	ServerTab2 fragmenttab2 = new ServerTab2();
            return fragmenttab2;
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
    	return titles[position];
    }
}
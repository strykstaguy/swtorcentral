package com.stryksta.swtorcentral;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class PlanetPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT = 2;
    private static final String[] titles = { "Planet Information ", "Datacrons"};
    Context context;
 
    public PlanetPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
 
    @Override
    public Fragment getItem(int position) {
        switch (position) {
        case 0:
        	PlanetTab1 fragmenttab1 = new PlanetTab1();
            return fragmenttab1;
        case 1:
        	PlanetTab2 fragmenttab2 = new PlanetTab2();
            return fragmenttab2;
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
    	return titles[position];
    }
}
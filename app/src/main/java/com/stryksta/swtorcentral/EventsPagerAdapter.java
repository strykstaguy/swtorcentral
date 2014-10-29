package com.stryksta.swtorcentral;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class EventsPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT = 4;
    private static final String[] titles = { "Bounty Contract Week", "Relics Of The Gree", "Rakghoul Plague", "Life Day" };
    Context context;
 
    public EventsPagerAdapter(FragmentManager fm) {
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
        	EventsTab1 fragmenttab1 = new EventsTab1();
            return fragmenttab1;
        case 1:
        	EventsTab2 fragmenttab2 = new EventsTab2();
            return fragmenttab2;
        case 2:
        	EventsTab3 fragmenttab3 = new EventsTab3();
            return fragmenttab3;
        case 3:
        	EventsTab4 fragmenttab4 = new EventsTab4();
            return fragmenttab4;
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
    	return titles[position];
    }
}
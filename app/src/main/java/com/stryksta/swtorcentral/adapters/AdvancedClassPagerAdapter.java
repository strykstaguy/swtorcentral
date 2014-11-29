package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stryksta.swtorcentral.AdvancedClassTab1;
import com.stryksta.swtorcentral.AdvancedClassTab2;
import com.stryksta.swtorcentral.AdvancedClassTab3;

public class AdvancedClassPagerAdapter extends FragmentPagerAdapter {
 
    final int PAGE_COUNT = 3;
    private static final String[] titles = { "Information", "Companions", "Abilities" };
    Context context;
 
    public AdvancedClassPagerAdapter(FragmentManager fm) {
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
            AdvancedClassTab1 fragmenttab1 = new AdvancedClassTab1();
            return fragmenttab1;
 
            // Open FragmentTab2.java
        case 1:
        	AdvancedClassTab2 fragmenttab2 = new AdvancedClassTab2();
            return fragmenttab2;
 
            // Open FragmentTab3.java
        case 2:
        	AdvancedClassTab3 fragmenttab3 = new AdvancedClassTab3();
            return fragmenttab3;
        }
        return null;
    }
 
    @Override
    public CharSequence getPageTitle(int position) {
       // return tabtitles[position];
    	return titles[position];
    }
}
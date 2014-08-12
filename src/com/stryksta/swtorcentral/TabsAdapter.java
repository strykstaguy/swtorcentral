package com.stryksta.swtorcentral;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class TabsAdapter extends FragmentPagerAdapter
    implements android.app.ActionBar.TabListener, android.support.v4.view.ViewPager.OnPageChangeListener
{
    static final class TabInfo
    {

        private final Bundle args;
        private final Class<?> clss;



        TabInfo(Class<?> class1, Bundle bundle)
        {
            clss = class1;
            args = bundle;
        }
    }


    private final ActionBar mActionBar;
    private final Context mContext;
    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
    private final ViewPager mViewPager;

    public TabsAdapter(FragmentActivity fragmentactivity, ViewPager viewpager)
    {
        super(fragmentactivity.getSupportFragmentManager());
        mContext = fragmentactivity;
        mActionBar = fragmentactivity.getActionBar();
        mViewPager = viewpager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
    }

    public void addTab(android.app.ActionBar.Tab tab, Class<?> class1, Bundle bundle)
    {
        TabInfo tabinfo = new TabInfo(class1, bundle);
        tab.setTag(tabinfo);
        tab.setTabListener(this);
        mTabs.add(tabinfo);
        mActionBar.addTab(tab);
        notifyDataSetChanged();
    }

    public int getCount()
    {
        return mTabs.size();
    }

    public Fragment getItem(int i)
    {
        TabInfo tabinfo = (TabInfo)mTabs.get(i);
        return Fragment.instantiate(mContext, tabinfo.clss.getName(), tabinfo.args);
    }

    public void onPageScrollStateChanged(int i)
    {
    }

    public void onPageScrolled(int i, float f, int j)
    {
    }

    public void onPageSelected(int i)
    {
        mActionBar.setSelectedNavigationItem(i);
    }

    public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
    }

    public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction fragmenttransaction)
    {
    }
}

package com.stryksta.swtorcentral.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class FragmentUtils {
    public static void addFragmentsInActivity(Activity act,
                                              int containerId, Fragment fragment, String tag) {
        FragmentManager fm = act.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.add(containerId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    public static void removeFragmentsInActivity(Activity act,
                                                 int containerId, Fragment fragment) {
        FragmentManager fm = act.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.remove(fragment);
        ft.commit();
    }

    public static void removeFragmentsInActivity(Activity act,
                                                 int containerId, String tag) {
        FragmentManager fm = act.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.remove(fm.findFragmentByTag(tag));
        ft.commit();
    }

    public static void switchFragmentsInActivity(Activity act,
                                                 int containerId, Fragment newFragment, String tag) {
        FragmentManager fm = act.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.add(containerId, newFragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    public static boolean isFragmentVisible(Activity act, String tag) {
        Fragment myFragment = getFragmentByTag(act, tag);

        if (myFragment != null) {
            if (myFragment.isVisible()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isFragmentPresent(Activity act, String tag) {
        Fragment myFragment = getFragmentByTag(act, tag);

        if (myFragment != null) {
            return true;
        } else {
            return false;
        }
    }

    public static Fragment getFragmentByTag(Activity act, String tag) {
        return act.getFragmentManager().findFragmentByTag(tag);
    }
}

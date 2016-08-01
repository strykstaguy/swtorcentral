package com.stryksta.swtorcentral.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.R;


public class TestTab2 extends Fragment {
    View vw_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist. The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed. Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }

        vw_layout = inflater.inflate(R.layout.testtab2, container, false);

        // Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

        return vw_layout;
    }

}
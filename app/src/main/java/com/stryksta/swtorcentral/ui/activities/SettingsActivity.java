package com.stryksta.swtorcentral.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.stryksta.swtorcentral.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_activity);
        addPreferencesFromResource(R.xml.settings);
        Toolbar mActionBar = (Toolbar) findViewById(R.id.toolbar);
        mActionBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mActionBar.setTitle(getTitle());
        mActionBar.setClickable(true);

        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
    }
}
package com.stryksta.swtorcentral.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.views.timeline.TimelineType;
import com.stryksta.swtorcentral.ui.views.timeline.TimelineView;

public class TestActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(TestActivity.this, R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
        }

        getSupportActionBar().setTitle("Blank");

        TimelineView timeLineView = (TimelineView) findViewById(R.id.timeline);
        timeLineView.setTimelineType(TimelineType.LINE);

        TimelineView timelineRepublic = (TimelineView) findViewById(R.id.timelineRepublic);
        timelineRepublic.setTimelineType(TimelineType.LINE);

        TimelineView timelineRepublicsdfasdf = (TimelineView) findViewById(R.id.timelineRepublicsdfasdf);
        timelineRepublicsdfasdf.setTimelineType(TimelineType.LINE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
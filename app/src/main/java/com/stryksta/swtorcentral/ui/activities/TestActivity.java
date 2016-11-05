package com.stryksta.swtorcentral.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qozix.tileview.TileView;
import com.stryksta.swtorcentral.R;

import com.stryksta.swtorcentral.ui.views.TextViewLabel;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipCloud;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipListener;

public class TestActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextViewLabel txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main2);

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

/*
        TileView tileView = new TileView( this );
        tileView.setSize( 1024, 1024 );  // the original size of the untiled image
        tileView.addDetailLevel( 1f, "tiles/tython/256/tython_%d_%d.png", 256, 256);
        tileView.addDetailLevel( 0.500f, "tiles/tython/128/tython_%d_%d.png", 128, 128);
        tileView.setScaleLimits(0, 5);
        tileView.setShouldRenderWhilePanning( true );
        tileView.setScale( 0.5f );
        ((LinearLayout)findViewById(R.id.mapTython)).addView(tileView);
        //setContentView( tileView );
*/
        ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);
        chipCloud.addChip("Planets");
        chipCloud.addChip("Lore");
        chipCloud.addChip("Datacrons");
        chipCloud.addChip("Epic Enemies");
        chipCloud.addChip("Titles");
        chipCloud.addChip("Bestiary");
        chipCloud.addChip("Lost Knowledge");
        chipCloud.addChip("Persons of Note");
        chipCloud.addChip("Test 1");
        chipCloud.addChip("Test 2");
        chipCloud.addChip("Test 3");
        chipCloud.addChip("Test 4");
        chipCloud.addChip("Test 5");
        chipCloud.addChip("Test 6");
        chipCloud.addChip("Test 7");
        chipCloud.addChip("Test 8");
        chipCloud.addChip("Test 9");
        chipCloud.setChipListener(new ChipListener() {
            @Override
            public void chipSelected(int index, String text) {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void chipDeselected(int index) {

            }
        });

        /*
        txtLabel = (TextViewLabel) findViewById(R.id.txtLabel);
        txtLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Press!", Toast.LENGTH_SHORT).show();
            }
        });
        */
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
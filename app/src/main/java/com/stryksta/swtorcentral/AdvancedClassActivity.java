package com.stryksta.swtorcentral;

import java.util.ArrayList;

import android.database.Cursor;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.data.AdvancedClassItem;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.database.AdvancedClassesDatabase;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;
import com.stryksta.swtorcentral.util.database.DisciplinesDatabase;

public class AdvancedClassActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    //Companion Info from prev
    int advID;
    String advClassName;
    String clsName;
    String advDescription;
    String advRole;
    String advArmor;
    String advWeapons;
    String advPriAttribute;
    int advAdvanced_class_icon;
    String advApc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_class_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(AdvancedClassActivity.this, R.color.colorPrimary));
        }

        //get bundle info
        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            advID = bundle.getInt("advID");
            advClassName = bundle.getString("advClassName");
            clsName = bundle.getString("clsName");
            advDescription = bundle.getString("advDescription");
            advRole = bundle.getString("advRole");
            advArmor = bundle.getString("advArmor");
            advWeapons = bundle.getString("advWeapons");
            advPriAttribute = bundle.getString("advPriAttribute");
            advAdvanced_class_icon = bundle.getInt("advAdvanced_class_icon");
            advApc = bundle.getString("advApc");
        }

        //Set Advanced Class
        TextView advClass = (TextView) findViewById(R.id.advClass);
        advClass.setText(advClassName);

        TextView advMainClass = (TextView) findViewById(R.id.advMainClass);
        advMainClass.setText(clsName);

        TextView txtadvDescription = (TextView) findViewById(R.id.txtAdvDesc);
        txtadvDescription.setText(advDescription);

        // Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

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

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
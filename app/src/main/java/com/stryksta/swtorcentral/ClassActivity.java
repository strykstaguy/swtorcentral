package com.stryksta.swtorcentral;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


public class ClassActivity extends AppCompatActivity {
    String txtClassName;
    int clsFaction;
    int txtIcon;
    String txtDescription;
    String txtResource;
    String txtCombatRole;
    String txtStory;
    String txtAbilities;
    String txtEquipment;
    String txtApc;
    String txtNode;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            txtClassName = bundle.getString("txtClassName");
            clsFaction = bundle.getInt("clsFaction");
            txtIcon = bundle.getInt("txtIcon");
            txtDescription = bundle.getString("txtDescription");
            txtResource = bundle.getString("txtResource");
            txtCombatRole = bundle.getString("txtCombatRole");
            txtStory = bundle.getString("txtStory");
            txtAbilities = bundle.getString("txtAbilities");
            txtEquipment = bundle.getString("txtEquipment");
            txtApc = bundle.getString("txtApc");
            txtNode = bundle.getString("txtNode");
        }

        getSupportActionBar().setTitle(txtClassName);

        TextView txtViewClass = (TextView) findViewById(R.id.txtClass);
        txtViewClass.setText(txtClassName);

        TextView txtViewDescription = (TextView) findViewById(R.id.txtDescription);
        txtViewDescription.setText(txtDescription);

        TextView txtViewFaction = (TextView) findViewById(R.id.txtFaction);

        if (clsFaction == 1) {
            txtViewFaction.setText("Republic");
        } else {
            txtViewFaction.setText("Empire");
        }

        ImageView imgViewIcon = (ImageView) findViewById(R.id.imgClass);
        imgViewIcon.setImageResource(txtIcon);

        TextView txtViewStory = (TextView) findViewById(R.id.txtStory);
        //txtViewStory.setText(txtStory);
        txtViewStory.setText(Html.fromHtml(txtStory));

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
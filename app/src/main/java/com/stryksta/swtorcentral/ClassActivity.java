package com.stryksta.swtorcentral;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.data.AdvancedClassItem;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;

import java.util.ArrayList;


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
    private ClassesDatabase classDB;
    ArrayList<AdvancedClassItem> advClassItems;

    private CompanionDatabase companionDatabase;
    ArrayList<CompanionItem> companionItems;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private CompanionClassAdapter mRecycleAdapter;

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

        //set advanced classes
        classDB = new ClassesDatabase(ClassActivity.this);
        advClassItems = classDB.getAdvancedClasses(txtClassName);

        String advClass1 = advClassItems.get(0).getAdvClassName();
        int advClassIMG1 = advClassItems.get(0).getAdvAdvancedClassIcon();

        //Set Advanced Class 1 Text
        TextView txtViewAdvanced1 = (TextView) findViewById(R.id.txtAdvancedClass1);
        txtViewAdvanced1.setText(advClass1);

        //Set Advanced Class 1 Icon
        ImageButton imgViewAdvanced1 = (ImageButton) findViewById(R.id.imgAdvancedClass1);
        imgViewAdvanced1.setImageResource(advClassIMG1);
        imgViewAdvanced1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putInt("advID", advClassItems.get(0).getAdvancedID());
                bundle.putString("advClassName", advClassItems.get(0).getAdvClassName());
                bundle.putString("clsName", txtClassName);
                bundle.putString("advDescription", advClassItems.get(0).getAdvDescription());
                bundle.putString("advRole", advClassItems.get(0).getAdvRole());
                bundle.putString("advArmor", advClassItems.get(0).getAdvArmor());
                bundle.putString("advWeapons", advClassItems.get(0).advWeapons);
                bundle.putString("advPriAttribute", advClassItems.get(0).getAdvPriAttribute());
                bundle.putInt("advAdvanced_class_icon", advClassItems.get(0).getAdvAdvancedClassIcon());
                bundle.putString("advApc", advClassItems.get(0).getAdvApc());

                Intent intent = new Intent(ClassActivity.this, AdvancedClassActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        /************ Advanced Class 2 **********/

        String advClass2 = advClassItems.get(1).getAdvClassName();
        int advClassIMG2 = advClassItems.get(1).getAdvAdvancedClassIcon();

        //Set Advanced Class 2 Text
        TextView txtViewAdvanced2 = (TextView) findViewById(R.id.txtAdvancedClass2);
        txtViewAdvanced2.setText(advClass2);

        //Set Advanced Class 2 Icon
        ImageButton imgViewAdvanced2 = (ImageButton) findViewById(R.id.imgAdvancedClass2);
        imgViewAdvanced2.setImageResource(advClassIMG2);
        imgViewAdvanced2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putInt("advID", advClassItems.get(1).getAdvancedID());
                bundle.putString("advClassName", advClassItems.get(1).getAdvClassName());
                bundle.putString("advDescription", advClassItems.get(1).getAdvDescription());
                bundle.putString("advRole", advClassItems.get(1).getAdvRole());
                bundle.putString("advArmor", advClassItems.get(1).getAdvArmor());
                bundle.putString("advWeapons", advClassItems.get(1).advWeapons);
                bundle.putString("advPriAttribute", advClassItems.get(1).getAdvPriAttribute());
                bundle.putInt("advAdvanced_class_icon", advClassItems.get(1).getAdvAdvancedClassIcon());
                bundle.putString("advApc", advClassItems.get(1).getAdvApc());

                Intent intent = new Intent(ClassActivity.this, AdvancedClassActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        TextView txtViewStory = (TextView) findViewById(R.id.txtStory);
        //txtViewStory.setText(txtStory);
        //txtViewStory.setText(Html.fromHtml(txtStory));

        //Get Original COmpanions
        companionItems = new ArrayList<>();
        companionDatabase = new CompanionDatabase(ClassActivity.this);
        companionItems = companionDatabase.getOriginalCompanions(txtNode);
        companionDatabase.close();

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.companionsList);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(ClassActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        //Set Adapter
        mRecycleAdapter = new CompanionClassAdapter(companionItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
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
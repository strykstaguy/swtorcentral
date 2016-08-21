package com.stryksta.swtorcentral.ui.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.DisciplineItem;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;

public class AdvancedClassActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ClassesDatabase classDB;
    ArrayList<DisciplineItem> disciplineItems;

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

        //Set Discipline 3 Icon
        ImageView advImage = (ImageView) findViewById(R.id.advImage);
        advImage.setImageResource(advAdvanced_class_icon);

        //********************** Disciplines **********************//

        classDB = new ClassesDatabase(AdvancedClassActivity.this);
        disciplineItems = classDB.getDisciplines(advApc);

        //Set Discipline 1 *******************************************
        final String advDisciplineName1 = disciplineItems.get(0).getDisciplineName();
        final String advDisciplineType1 = disciplineItems.get(0).getType();
        final String advDisciplineAPC1 = disciplineItems.get(0).getAPC();
        final String advDisciplineDescription1 = disciplineItems.get(0).getDisciplineDescription();
        final Drawable advDisciplineIcon1 = getDisciplineIcon(advDisciplineType1);

        //Set Discipline 1 Icon
        ImageButton imgDiscipline1 = (ImageButton) findViewById(R.id.imgDisciplines1);
        imgDiscipline1.setImageDrawable(advDisciplineIcon1);
        imgDiscipline1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("advClassName", advClassName);
                bundle.putString("disName", advDisciplineName1);
                bundle.putString("disDescription", advDisciplineDescription1);
                bundle.putString("disIcon", advDisciplineType1);
                bundle.putString("disAPC", advDisciplineAPC1);

                Intent intent = new Intent(AdvancedClassActivity.this, DisciplineActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //Set Discipline 1 Text
        TextView txtViewDiscipline1 = (TextView) findViewById(R.id.txtDiscipline1);
        txtViewDiscipline1.setText(advDisciplineName1);

        //Set Discipline 2 *******************************************
        final String advDisciplineName2 = disciplineItems.get(1).getDisciplineName();
        String advDisciplineType2 = disciplineItems.get(1).getType();
        final String advDisciplineDescription2 = disciplineItems.get(1).getDisciplineDescription();
        Drawable advDisciplineIcon2 = getDisciplineIcon(advDisciplineType2);

        //Set Discipline 2 Icon
        ImageButton imgDiscipline2 = (ImageButton) findViewById(R.id.imgDisciplines2);
        imgDiscipline2.setImageDrawable(advDisciplineIcon2);
        imgDiscipline2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(AdvancedClassActivity.this)
                        .title(advDisciplineName2)
                        .content(advDisciplineDescription2)
                        .positiveText(R.string.positive)
                        .show();
            }
        });

        //Set Discipline 2 Text
        TextView txtViewDiscipline2 = (TextView) findViewById(R.id.txtDiscipline2);
        txtViewDiscipline2.setText(advDisciplineName2);

        //Set Discipline 3 *******************************************
        final String advDisciplineName3 = disciplineItems.get(2).getDisciplineName();
        String advDisciplineType3 = disciplineItems.get(2).getType();
        final String advDisciplineDescription3 = disciplineItems.get(2).getDisciplineDescription();
        Drawable advDisciplineIcon3 = getDisciplineIcon(advDisciplineType3);

        //Set Discipline 3 Icon
        ImageButton imgDiscipline3 = (ImageButton) findViewById(R.id.imgDisciplines3);
        imgDiscipline3.setImageDrawable(advDisciplineIcon3);
        imgDiscipline3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MaterialDialog.Builder(AdvancedClassActivity.this)
                        .title(advDisciplineName3)
                        .content(advDisciplineDescription3)
                        .positiveText(R.string.positive)
                        .show();
            }
        });

        //Set Discipline 3 Text
        TextView txtViewDiscipline3 = (TextView) findViewById(R.id.txtDiscipline3);
        txtViewDiscipline3.setText(advDisciplineName3);

        //Set Role
        TextView txtRoleText = (TextView) findViewById(R.id.txtRoleText);
        txtRoleText.setText(advRole);

        //Set Armor
        TextView txtArmorText = (TextView) findViewById(R.id.txtArmorText);
        txtArmorText.setText(advArmor);

        //Set Weapons
        TextView txtWeaponsText = (TextView) findViewById(R.id.txtWeaponsText);
        txtWeaponsText.setText(advWeapons);

        //Set Attribute
        TextView txtAttributeText = (TextView) findViewById(R.id.txtAttributeText);
        txtAttributeText.setText(advPriAttribute);

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

    public Drawable getDisciplineIcon(String disType) {
        Drawable disciplineIcon;
        switch(disType) {
            case "Tank":
                disciplineIcon = ContextCompat.getDrawable(AdvancedClassActivity.this, R.drawable.ic_tank);
                break;
            case "DPS":
                disciplineIcon = ContextCompat.getDrawable(AdvancedClassActivity.this, R.drawable.ic_damage);
                break;
            case "Healer":
                disciplineIcon = ContextCompat.getDrawable(AdvancedClassActivity.this, R.drawable.ic_heals);
                break;
            default:
                disciplineIcon = ContextCompat.getDrawable(AdvancedClassActivity.this, R.drawable.ic_heals);
        }
        return disciplineIcon;
    }
}
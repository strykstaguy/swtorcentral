package com.stryksta.swtorcentral;

import java.util.ArrayList;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.stryksta.swtorcentral.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.data.AdvancedClassItem;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.NonScrollListView;
import com.stryksta.swtorcentral.util.database.AdvancedClassesDatabase;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;
import com.stryksta.swtorcentral.util.database.CompanionGiftsDatabase;
import com.stryksta.swtorcentral.util.database.DisciplinesDatabase;

public class AdvancedClassActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitleView;

    //Companion Info from prev
    private int ClassPos;
    private int ClassID;
    private CompanionDatabase CompDP;
    private Cursor companions;
    private CompanionGiftsDatabase db2;

    ArrayList<CompanionItem> companionItems;


    //Advanced Class Information
    private AdvancedClassesDatabase advDB;
    ArrayList<AdvancedClassItem> advancedClassItems = new ArrayList<AdvancedClassItem>();
    private Cursor advancedClassesDB;

    //Disciplines
    private DisciplinesDatabase disciplinesDB;
    private Cursor disciplinesCursor;

    int actionBarHeight;

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
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        //get bundle info
        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            ClassPos = bundle.getInt("position");
            ClassID = bundle.getInt("class_id");
        }

        mTitleView = (TextView) findViewById(R.id.title);

        NonScrollListView companionsListView = (NonScrollListView) findViewById(R.id.companionsListView);

        companionItems = new ArrayList<CompanionItem>();


        CompDP = new CompanionDatabase(AdvancedClassActivity.this);
        db2 = new CompanionGiftsDatabase(AdvancedClassActivity.this);
        companions = CompDP.getCompanions(ClassID);
        if (companions.moveToFirst())
        {
            do
            {
                String txtName = companions.getString(companions.getColumnIndex("companion_name"));
                String txtRole = companions.getString(companions.getColumnIndex("role"));
                String txtBonus = companions.getString(companions.getColumnIndex("crew_skill_bonus"));
                String txtRomance = companions.getString(companions.getColumnIndex("romance"));
                String txtPrimaryStat = companions.getString(companions.getColumnIndex("primarystat"));
                String txtSecondaryStat = companions.getString(companions.getColumnIndex("secondarystat"));
                String txtPrimaryWeapon = companions.getString(companions.getColumnIndex("primaryweapon"));
                String txtSecondaryWeapon = companions.getString(companions.getColumnIndex("secondaryweapon"));
                String txtGender = companions.getString(companions.getColumnIndex("gender"));
                String txtRace = companions.getString(companions.getColumnIndex("race"));
                String txtFound = companions.getString(companions.getColumnIndex("found"));
                String txtArmor = companions.getString(companions.getColumnIndex("armor"));
                String txtDescription = companions.getString(companions.getColumnIndex("description"));
                String txtGifts = db2.getGifts(companions.getString(companions.getColumnIndex("companion_name")));

                CompanionItem item = new CompanionItem(txtName, txtRole, txtBonus, txtRomance, txtPrimaryStat, txtSecondaryStat, txtPrimaryWeapon, txtSecondaryWeapon, txtGifts, txtGender, txtRace, txtFound, txtArmor, txtDescription);
                companionItems.add(item);
            } while (companions.moveToNext());
        }

        CompanionClassAdapter companionsAdapter = new CompanionClassAdapter(AdvancedClassActivity.this, R.layout.companion_class_row, companionItems);
        companionsListView.setAdapter(companionsAdapter);

        //Advanced Classes Info
        advDB = new AdvancedClassesDatabase(AdvancedClassActivity.this);
        advancedClassesDB = advDB.getAdvancedClasses(ClassPos);

        if (advancedClassesDB.moveToFirst()) {

            do {
                int ID = advancedClassesDB.getInt(advancedClassesDB.getColumnIndex("_id"));
                int advClass_ID = advancedClassesDB.getInt(advancedClassesDB.getColumnIndex("class_id"));
                String advClass = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("class"));
                String advDescription = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("description"));
                String advRole = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("role"));
                String advArmor = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("armor"));
                String advWeapons = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("weapons"));
                String advPriAttribute = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("priattribute"));
                String advAdvanced_class_icon = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("advanced_class_icon"));
                String advApc = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("apc"));
                String advAdv_bg = advancedClassesDB.getString(advancedClassesDB.getColumnIndex("adv_bg"));
                int advClassBG = getResources().getIdentifier(advAdv_bg, "drawable", getPackageName());

                //Set Description
                TextView txtAdvDesc = (TextView) findViewById(R.id.txtAdvDesc);
                txtAdvDesc.setText(advDescription);

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

            } while (advancedClassesDB.moveToNext());
        }

        advancedClassesDB.close();
        advDB.close();

        //Disciplines
        disciplinesDB = new DisciplinesDatabase(AdvancedClassActivity.this);
        disciplinesCursor = disciplinesDB.getDisciplines(ClassPos);

        //Set Disciplines 1
        TextView txtDiscipline1 = (TextView) findViewById(R.id.txtDiscipline1);

        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(0);
            String disciplines = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            txtDiscipline1.setText(disciplines);
        }

        //Set Disciplines 2
        TextView txtDiscipline2 = (TextView) findViewById(R.id.txtDiscipline2);

        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(1);
            String disciplines = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            txtDiscipline2.setText(disciplines);
        }

        //Set Disciplines 3
        TextView txtDiscipline3 = (TextView) findViewById(R.id.txtDiscipline3);

        if (disciplinesCursor.getCount() > 0) {
            disciplinesCursor.moveToPosition(2);
            String disciplines = disciplinesCursor.getString(disciplinesCursor.getColumnIndex("name"));
            txtDiscipline3.setText(disciplines);
        }
        ////Set Title
        //setTitle(null);

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
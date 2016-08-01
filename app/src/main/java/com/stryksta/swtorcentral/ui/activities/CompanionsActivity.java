package com.stryksta.swtorcentral.ui.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.AbilitiesItem;
import com.stryksta.swtorcentral.ui.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.models.CompanionItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.Spanny;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CompanionsActivity extends AppCompatActivity {

    //Base Companions
    private CompanionDatabase baseCompanionDatabase;
    ArrayList<CompanionItem> baseCompanions;

    private RecyclerView baseRecyclerView;
    private GridLayoutManager baseLayoutManager;
    private CompanionClassAdapter baseRecycleAdapter;

    private String clsApc;
    private String clsName;
    private String txtNode;
    private String clsAbility;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companions_main);

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
        if (bundle != null) {
            clsApc = bundle.getString("clsApc");
            clsName = bundle.getString("clsName");
            clsAbility = bundle.getString("clsAbility");
            txtNode = bundle.getString("txtNode");
        }

        getSupportActionBar().setTitle(clsName + " Companions");

        TextView ablTitle = (TextView) findViewById(R.id.comTitle);
        ablTitle.setText("Companions");

        TextView ablSubtitle = (TextView) findViewById(R.id.comSubtitle);
        ablSubtitle.setText(clsName);

        //Get Base Companions
        baseCompanionDatabase = new CompanionDatabase(CompanionsActivity.this);
        baseCompanions = new ArrayList<>();
        baseCompanions = baseCompanionDatabase.getOriginalCompanions(txtNode);
        baseCompanionDatabase.close();

        //Set Base RecyclerView
        baseRecyclerView = (RecyclerView) findViewById(R.id.companionsList);

        if (baseRecyclerView != null) {
            baseLayoutManager = new GridLayoutManager(CompanionsActivity.this, 1, GridLayoutManager.VERTICAL, false);
            baseRecyclerView.setLayoutManager(baseLayoutManager);
        }

        //Set Base Adapter
        baseRecycleAdapter = new CompanionClassAdapter(baseCompanions);
        //baseRecyclerView.addItemDecoration(new DividerItemDecoration(CompanionsActivity.this, GridLayoutManager.VERTICAL));
        baseRecyclerView.setAdapter(baseRecycleAdapter);
        baseRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CompanionsActivity.this, baseRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                boolean wrapInScrollView = true;
                MaterialDialog dialog = new MaterialDialog.Builder(CompanionsActivity.this)
                        .title(baseCompanions.get(position).getName())
                        .customView(R.layout.companion_dialog, wrapInScrollView)
                        .positiveText(R.string.positive)
                        .show();

                View comView = dialog.getCustomView();
                TextView comDesc = (TextView) comView.findViewById(R.id.comDesc);
                TextView comGifts = (TextView) comView.findViewById(R.id.comGifts);

                String companionGifts = String.valueOf(baseCompanions.get(position).getGiftsUnRomanced());
                companionGifts = companionGifts.replaceAll(",", "\n");

                comDesc.setText(Html.fromHtml(baseCompanions.get(position).getDescription()));
                comGifts.setText(replaceGifts(companionGifts));
                //replaceGifts();
            }

            public void onItemLongClick(View view, int position) {

            }
        }));
        //Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

    }

    public String replaceGifts(String gifts) {
        HashMap<String, String> mapGifts = new HashMap<String, String>();

        //test
        mapGifts.put("itmGiftWeapon", "Weapon");
        mapGifts.put("itmGiftMilitary_Gear", "Military Gear");
        mapGifts.put("itmGiftCourting", "Courting");
        mapGifts.put("itmGiftLuxury", "Luxury");
        mapGifts.put("itmGiftTechnology", "Technology");
        mapGifts.put("itmGiftRepublic_Memorabilia", "Republic Memorabilia");
        mapGifts.put("itmGiftImperial_Memorabilia", "Imperial Memorabilia");
        mapGifts.put("itmGiftCultural_Artifact", "Cultural Artifact");
        mapGifts.put("itmGiftTrophy", "Trophy");
        mapGifts.put("itmGiftUnderworld_Good", "Underworld Good");
        mapGifts.put("itmGiftDelicacies", "Delicacies");
        mapGifts.put("itmGiftMaintenance", "Maintenance");
        mapGifts.put("chrCompanionGiftLove", "Gift Love");
        mapGifts.put("chrCompanionGiftFavorite", "Favorite");
        mapGifts.put("chrCompanionGiftLike", "Like");
        mapGifts.put("chrCompanionGiftIndifferent", "Indifferent");

        Iterator it = mapGifts.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();

            gifts = gifts.replaceAll(pair.getKey().toString(), pair.getValue().toString());

            //tv.setText(tv.getText() + "\n" + pair.getKey() +  " : " + pair.getValue());
            /*
            if(pair.getValue().equals("Blue")){
                it.remove();
            }
            */


        }

        return gifts;
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
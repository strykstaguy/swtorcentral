package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.CompanionDatabase;
import com.stryksta.swtorcentral.util.CompanionGiftsDatabase;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class AdvancedClassTab2 extends Fragment {
	private int ClassPos;
	private int ClassID;
    private Cursor datacrons;
    private CompanionDatabase db;
    private CompanionGiftsDatabase db2;
    ArrayList<CompanionItem> rowItems;
    GridView gridView;
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
		
        vw_layout = inflater.inflate(R.layout.advanced_class_tab2, container, false);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	ClassPos = bundle.getInt("position");
        	ClassID = bundle.getInt("class_id");
        	
        }
        
        rowItems = new ArrayList<CompanionItem>();
        
        db = new CompanionDatabase(getActivity());
        db2 = new CompanionGiftsDatabase(getActivity());
        datacrons = db.getCompanions(ClassID);
        if (datacrons.moveToFirst())
        {
            do
            {
            	String txtName = datacrons.getString(datacrons.getColumnIndex("companion_name"));
                String txtRole = datacrons.getString(datacrons.getColumnIndex("role"));
                String txtBonus = datacrons.getString(datacrons.getColumnIndex("crew_skill_bonus"));
                String txtRomance = datacrons.getString(datacrons.getColumnIndex("romance"));
                String txtPrimaryStat = datacrons.getString(datacrons.getColumnIndex("primarystat"));
                String txtSecondaryStat = datacrons.getString(datacrons.getColumnIndex("secondarystat"));
                String txtPrimaryWeapon = datacrons.getString(datacrons.getColumnIndex("primaryweapon"));
                String txtSecondaryWeapon = datacrons.getString(datacrons.getColumnIndex("secondaryweapon"));
                String txtGender = datacrons.getString(datacrons.getColumnIndex("gender"));
                String txtRace = datacrons.getString(datacrons.getColumnIndex("race"));
                String txtFound = datacrons.getString(datacrons.getColumnIndex("found"));
                String txtArmor = datacrons.getString(datacrons.getColumnIndex("armor"));
                String txtDescription = datacrons.getString(datacrons.getColumnIndex("description"));
                String txtGifts = db2.getGifts(datacrons.getString(datacrons.getColumnIndex("companion_name")));
                
                CompanionItem item = new CompanionItem(txtName, txtRole, txtBonus, txtRomance, txtPrimaryStat, txtSecondaryStat, txtPrimaryWeapon, txtSecondaryWeapon, txtGifts, txtGender, txtRace, txtFound, txtArmor, txtDescription);
                rowItems.add(item);
            } while (datacrons.moveToNext());
        }
        
        CompanionAdapter mAdapter = new CompanionAdapter(getActivity(), R.layout.advanced_class_row, rowItems);
		gridView = (GridView) vw_layout.findViewById(R.id.companionlist);
		gridView.setAdapter(mAdapter);
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}

}
package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.PlanetAdapter;
import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.util.database.DatacronDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PlanetTab2 extends Fragment {
	private ArrayList<DatacronItem> datacrons;
	private DatacronDatabase db;
	private String planetText;
	private String factionText;
	private String typeText;
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
		
        vw_layout = inflater.inflate(R.layout.planet_tab2, container, false);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	planetText = bundle.getString("planet");
        	factionText = bundle.getString("faction");
        	typeText = bundle.getString("type");
        }
        
        db = new DatacronDatabase(getActivity());
		//datacrons = db.getDatacronsPerPlanet(planetText, factionText);
		db.close();
		
		NonScrollListView itcItems = (NonScrollListView) vw_layout.findViewById(R.id.lstDatacron);
		
		PlanetAdapter adapter = new PlanetAdapter(getActivity(), R.layout.planet_tab2_row, android.R.id.text1, datacrons);
		itcItems.setAdapter(adapter);
  	  
		return vw_layout;
	}
}
package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.data.LoreItem;
import com.stryksta.swtorcentral.util.DatacronDatabase;
import com.stryksta.swtorcentral.util.LoreDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PlanetTab3 extends Fragment {
	private LoreDatabase db;
	private ArrayList<LoreItem> loreItems;
	private ArrayList<LoreItem> loreLocationItems;
	private ArrayList<LoreItem> lorePersonsItems;
	private ArrayList<LoreItem> loreTitlesItems;
	private ArrayList<LoreItem> loreGameRulesItems;
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
		
        vw_layout = inflater.inflate(R.layout.planet_tab3, container, false);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	planetText = bundle.getString("planet");
        	factionText = bundle.getString("faction");
        	typeText = bundle.getString("type");
        }
        
        if (planetText.equals("Republic Fleet")) {
        	planetText = "Fleet";
        } else if (planetText.equals("Imperial Fleet")){
        	planetText = "Fleet";
        }
        
        db = new LoreDatabase(getActivity());
        loreItems = db.getLore(planetText, factionText, "Lore");
        loreLocationItems = db.getLore(planetText, factionText, "Locations");
        lorePersonsItems = db.getLore(planetText, factionText, "Persons");
        loreTitlesItems = db.getLore(planetText, factionText, "Title");
        loreGameRulesItems = db.getLore(planetText, factionText, "Game Rules");
		db.close();
		
		//Lore
		NonScrollListView loreItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstLore);
		LoreAdapter loreadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreItems);
		loreItemsList.setAdapter(loreadapter);
		
		//Locations
		NonScrollListView loreLocationsItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstLocationsLore);
		LoreAdapter locationadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreLocationItems);
		loreLocationsItemsList.setAdapter(locationadapter);

		//Persons
		NonScrollListView lorePersonsItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstPersonsLore);
		LoreAdapter personsadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, lorePersonsItems);
		lorePersonsItemsList.setAdapter(personsadapter);
		
		//Titles
		NonScrollListView loreTitlesItemsList = (NonScrollListView) vw_layout.findViewById(R.id.listTitlesLore);
		LoreAdapter titleadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreTitlesItems);
		loreTitlesItemsList.setAdapter(titleadapter);
		
		//Game Rules
		NonScrollListView listGameRulesLore = (NonScrollListView) vw_layout.findViewById(R.id.listGameRulesLore);
		LoreAdapter gamerulesadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreGameRulesItems);
		listGameRulesLore.setAdapter(gamerulesadapter);
				
		return vw_layout;
	}
}
package com.stryksta.swtorcentral;

import java.util.ArrayList;
import com.stryksta.swtorcentral.adapters.LoreAdapter;
import com.stryksta.swtorcentral.data.LoreItem;
import com.stryksta.swtorcentral.util.LoreDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class PlanetTab3 extends Fragment {
	private LoreDatabase db;
	private ArrayList<LoreItem> loreItems;
	private ArrayList<LoreItem> loreLocationItems;
	private ArrayList<LoreItem> lorePersonsItems;
	private ArrayList<LoreItem> loreTitlesItems;
	private ArrayList<LoreItem> loreBeastItems;
	private ArrayList<LoreItem> loreGameRulesItems;
	private ArrayList<LoreItem> loreOrganizationsItems;
	
	LoreAdapter loreadapter;
	LoreAdapter locationadapter;
	LoreAdapter personsadapter;
	LoreAdapter titleadapter;
	LoreAdapter beastadapter;
	LoreAdapter gamerulesadapter;
	LoreAdapter organizationsadapter;
	
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
        loreBeastItems = db.getLore(planetText, factionText, "Beast");
        loreGameRulesItems = db.getLore(planetText, factionText, "Game Rules");
        loreOrganizationsItems = db.getLore(planetText, factionText, "Organizations");
		db.close();
		
		//Lore
		NonScrollListView loreItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstLore);
		loreadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreItems);
		loreItemsList.setAdapter(loreadapter);
		
		loreItemsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {

			    }});

		//Locations
		NonScrollListView loreLocationsItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstLocationsLore);
		locationadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreLocationItems);
		loreLocationsItemsList.setAdapter(locationadapter);

		loreLocationsItemsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			    {

			    }});

		//Persons
		NonScrollListView lorePersonsItemsList = (NonScrollListView) vw_layout.findViewById(R.id.lstPersonsLore);
		personsadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, lorePersonsItems);
		lorePersonsItemsList.setAdapter(personsadapter);
		
		lorePersonsItemsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {

			    }});
		
		//Titles
		NonScrollListView loreTitlesItemsList = (NonScrollListView) vw_layout.findViewById(R.id.listTitlesLore);
		titleadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreTitlesItems);
		loreTitlesItemsList.setAdapter(titleadapter);
		
		loreTitlesItemsList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {

			    }});
		
		//Beast
		NonScrollListView listBeastLore = (NonScrollListView) vw_layout.findViewById(R.id.listBeastLore);
		beastadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreBeastItems);
		listBeastLore.setAdapter(beastadapter);
		
		listBeastLore.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {

			    }});
		
		//Game Rules
		NonScrollListView listGameRulesLore = (NonScrollListView) vw_layout.findViewById(R.id.listGameRulesLore);
		TextView txtGameRulesLore = (TextView) vw_layout.findViewById(R.id.txtGameRulesLore);
		
		if (loreGameRulesItems.size() > 0) {
			gamerulesadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreGameRulesItems);
			listGameRulesLore.setAdapter(gamerulesadapter);

			listGameRulesLore.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,int position, long id)
				    {

				    }});
		} else {
			listGameRulesLore.setVisibility(View.GONE);
			txtGameRulesLore.setVisibility(View.GONE);
		}
		
		
		//Organizations
		NonScrollListView listOrganizationsLore = (NonScrollListView) vw_layout.findViewById(R.id.listOrganizationsLore);
		organizationsadapter = new LoreAdapter(getActivity(), R.layout.lore_row, android.R.id.text1, loreOrganizationsItems);
		listOrganizationsLore.setAdapter(organizationsadapter);

		listOrganizationsLore.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
				{

					}});

		return vw_layout;
	}
}
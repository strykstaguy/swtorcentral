package com.stryksta.swtorcentral;


import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Category4Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementsItem> achievements = new ArrayList<AchievementsItem>();
	AchievementItemsAdapter achievementAdapter;
	String Category1;
	String Category2;
	String Category3;
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
		
        vw_layout = inflater.inflate(R.layout.achievement_category_main, container, false);
        
       
        
        if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
        	Category2 = getArguments().getString("category2");
        	Category3 = getArguments().getString("category3");
        }
        
        
        getActivity().setTitle(Category3);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getAchievements(Category1, Category2, Category3);
        
        ListView achievementListView = (ListView) vw_layout.findViewById(R.id.achievementlistview);
		
        achievementAdapter = new AchievementItemsAdapter(getActivity(), achievements);
        achievementListView.setAdapter(achievementAdapter);
     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Achievements");
	}
}

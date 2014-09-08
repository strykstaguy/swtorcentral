package com.stryksta.swtorcentral;


import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.FragmentUtils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Category2Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementsItem> achievements = new ArrayList<AchievementsItem>();
	AchievementAdapter achievementAdapter;
	String Category1;
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
        }

        getActivity().setTitle(Category1);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory2(Category1);
        
        GridView achievementListView = (GridView) vw_layout.findViewById(R.id.achievementlistview);
		
        achievementAdapter = new AchievementAdapter(getActivity(), achievements, "category2");
        achievementListView.setAdapter(achievementAdapter);
        achievementListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Category3Fragment category3frag = new Category3Fragment();
				
				Bundle args = new Bundle();
		    	 args.putString("category1", achievementAdapter.getItem(position).getCategory1());
		    	 args.putString("category2", achievementAdapter.getItem(position).getCategory2());
		    	 category3frag.setArguments(args);
				
		    	 FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category3frag, "Category3");
			}});
        
     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Achievements");
	}
}

package com.stryksta.swtorcentral;


import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Category3Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementCategoryItem> achievements = new ArrayList<AchievementCategoryItem>();
	AchievementCategoryAdapter achievementAdapter;
	
	SessionManager session;
	String characterName;
	String characterID;
	String characterlegacy;
	
	String Category1;
	String Category2;
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
        
        session = new SessionManager(getActivity());
        
        //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        characterName = user.get(SessionManager.KEY_NAME);
        characterID = user.get(SessionManager.KEY_ID);
        characterlegacy = user.get(SessionManager.KEY_LEGACY);
        
        if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
        	Category2 = getArguments().getString("category2");
        }
        
        Log.d("SWTORCentral", Thread.currentThread().getName());
        
        getActivity().setTitle(Category2);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory3(characterID, characterlegacy, Category1, Category2);
        
        AutoMeasureGridView achievementListView = (AutoMeasureGridView) vw_layout.findViewById(R.id.achievementgridview);
		
        achievementAdapter = new AchievementCategoryAdapter(getActivity(), achievements);
        achievementListView.setAdapter(achievementAdapter);
        achievementListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Category4Fragment category4frag = new Category4Fragment();
				
				Bundle args = new Bundle();
		    	 args.putString("category1", Category1);
		    	 args.putString("category2", Category2);
		    	 args.putString("category3", achievementAdapter.getItem(position).getCategory());
		    	 category4frag.setArguments(args);
				
		    	 FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category4frag, "Category4");
			}});
        
     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle(Category1);
	}
}

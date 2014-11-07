package com.stryksta.swtorcentral;


import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SWTORProgressbar;
import com.stryksta.swtorcentral.util.SessionManager;
import com.stryksta.swtorcentral.util.TextProgressBar;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Category1Fragment extends Fragment {
	private AchievementsDatabase db;
	
	SessionManager session;
	String characterName;
	String characterID;
	String characterlegacy;
	
	ArrayList<AchievementCategoryItem> categories = new ArrayList<AchievementCategoryItem>();
	AchievementCategoryAdapter achievementAdapter;
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
        
        getActivity().setTitle("Achievements");
        
      //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        characterName = user.get(SessionManager.KEY_NAME);
        characterID = user.get(SessionManager.KEY_ID);
        characterlegacy = user.get(SessionManager.KEY_LEGACY);
        
        db = new AchievementsDatabase(getActivity());
        categories = db.getCategory1(characterID, characterlegacy);

        AutoMeasureGridView achievementListView = (AutoMeasureGridView) vw_layout.findViewById(R.id.achievementgridview);
        achievementAdapter = new AchievementCategoryAdapter(getActivity(), categories);
        achievementListView.setAdapter(achievementAdapter);
        achievementListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Category2Fragment category2frag = new Category2Fragment();
				
				Bundle args = new Bundle();
		    	 args.putString("category1", achievementAdapter.getItem(position).getCategory());
		    	 category2frag.setArguments(args);
				
		    	 FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category2frag, "Category2");

			}});
        
     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Achivements");
	}
	
	public void onResume() {
		super.onResume();
		//getActivity().getActionBar().setTitle("Achivements");
	}
}
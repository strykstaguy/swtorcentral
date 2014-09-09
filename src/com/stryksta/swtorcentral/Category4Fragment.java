package com.stryksta.swtorcentral;


import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class Category4Fragment extends Fragment {
	private AchievementsDatabase db;
	GridView achievementListView;
	ArrayList<AchievementsItem> achievements = new ArrayList<AchievementsItem>();
	AchievementItemsAdapter achievementAdapter;
	SessionManager session;
	Handler handler;
	String Category1;
	String Category2;
	String Category3;
	String characterName;
	String characterID;
	String characterlegacy;
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
		
        vw_layout = inflater.inflate(R.layout.achievement_item_main, container, false);
        
        session = new SessionManager(getActivity());
        
        handler = new Handler();
        
        if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
        	Category2 = getArguments().getString("category2");
        	Category3 = getArguments().getString("category3");
        }
        
        //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        characterName = user.get(SessionManager.KEY_NAME);
        characterID = user.get(SessionManager.KEY_ID);
        characterlegacy = user.get(SessionManager.KEY_LEGACY);

        getActivity().setTitle(Category3);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getAchievements(Category1, Category2, Category3, characterlegacy);
        
        achievementListView = (GridView) vw_layout.findViewById(R.id.achievementlistview);
        
        achievementAdapter = new AchievementItemsAdapter(getActivity(), achievements);
        achievementListView.setAdapter(achievementAdapter);
        
        achievementListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//
				//view.setBackground(getResources().getDrawable(R.drawable.card_selected_background));
				//view.setSelected(true);
				//view.setite
				//Toast.makeText(getActivity(), "Character ID: " + characterID + " Achievement ID: " + achievementAdapter.getItem(position).getAchievementID(), Toast.LENGTH_SHORT).show();
				if (achievementAdapter.getItem(position).getCompleted() == 0) {
					db.setCompleted(Integer.parseInt(characterID), achievementAdapter.getItem(position).getAchievementID(), characterlegacy);
				} else {
					db.removeCompleted(Integer.parseInt(characterID), achievementAdapter.getItem(position).getAchievementID(), characterlegacy);
				}
				
				updateItems();
				
				return false;
			}
        }); 
        
     	return vw_layout;
	}
	
	public void updateItems() {
		
		/*handler.postDelayed(new Runnable() {
		       public void run() {
		    	   
		       }
		     }, 500);*/
		
		db = new AchievementsDatabase(getActivity());
	    
	    achievementAdapter.setNotifyOnChange(false); 
	    achievements.clear();
        achievements = db.getAchievements(Category1, Category2, Category3, characterlegacy);
	    achievementAdapter.clear();
        achievementAdapter.addAll(achievements);
        
        //achievementAdapter = new AchievementItemsAdapter(getActivity(), achievements);
        //achievementListView.setAdapter(achievementAdapter);
        achievementAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Achievements");
	    db.close();
	}
}

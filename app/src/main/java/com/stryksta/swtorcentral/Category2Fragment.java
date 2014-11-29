package com.stryksta.swtorcentral;


import java.util.ArrayList;
import java.util.HashMap;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.adapters.AchievementCategoryAdapter;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Category2Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementCategoryItem> achievements = new ArrayList<AchievementCategoryItem>();
	AchievementCategoryAdapter achievementAdapter;
	AutoMeasureGridView achievementListView;
	
	SessionManager session;
	String characterName;
	String characterID;
	String characterlegacy;
	
	String Category1;
	View vw_layout;
	
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
        }

        getActivity().setTitle(Category1);
	  }
	 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		if (container == null) {
			return null;
		}
		
        vw_layout = inflater.inflate(R.layout.achievement_category_main, container, false);
        
        session = new SessionManager(getActivity());
        
      //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        characterName = user.get(SessionManager.KEY_NAME);
        characterID = user.get(SessionManager.KEY_ID);
        characterlegacy = user.get(SessionManager.KEY_LEGACY);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory2(characterID, characterlegacy, Category1);
        
        achievementListView = (AutoMeasureGridView) vw_layout.findViewById(R.id.achievementgridview);
        achievementAdapter = new AchievementCategoryAdapter(getActivity(), achievements);
        achievementAdapter.notifyDataSetChanged();
        achievementListView.invalidateViews();
        achievementListView.setAdapter(achievementAdapter);
        
        achievementListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Category3Fragment category3frag = new Category3Fragment();
				
				Bundle args = new Bundle();
		    	 args.putString("category1", Category1);
		    	 args.putString("category2", achievementAdapter.getItem(position).getCategory());
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

    @Override
    public void onResume() {
        super.onResume();
        new MaterialDialog.Builder(getActivity())
                .title("Category 2")
                .content("This is Category 2")
                .positiveText("OK")
                .show();
    }
}

package com.stryksta.swtorcentral;


import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Category2Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementsItem> achievements = new ArrayList<AchievementsItem>();
	AchievementAdapter achievementAdapter;
	AutoMeasureGridView achievementListView;
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
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory2(Category1);
        
        achievementListView = (AutoMeasureGridView) vw_layout.findViewById(R.id.achievementgridview);
        achievementAdapter = new AchievementAdapter(getActivity(), achievements, "category2");
        achievementAdapter.notifyDataSetChanged();
        achievementListView.invalidateViews();
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

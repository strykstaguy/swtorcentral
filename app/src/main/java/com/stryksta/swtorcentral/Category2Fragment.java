package com.stryksta.swtorcentral;


import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.adapters.AchievementCategoryAdapter;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Category2Fragment extends Fragment {
	private AchievementsDatabase db;
	ArrayList<AchievementCategoryItem> achievements = new ArrayList<AchievementCategoryItem>();

	private AchievementCategoryAdapter mRecycleAdapter;
	private GridLayoutManager mLayoutManager;
	private RecyclerView mRecyclerView;
	
	SessionManager session;
	String characterName;
	String characterID;
	String characterlegacy;
	
	String Category1;
	Integer Category1Completed;
	Integer Category1Total;

	View vw_layout;
	
	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
			Category1Completed = getArguments().getInt("category1_completed");
			Category1Total = getArguments().getInt("category1_total");
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

		//Set title/completed/total of category
		((AchievementActivity)getActivity()).setTitleText(Category1);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category1Completed) + "/" + String.valueOf(Category1Total));

        float total_completed = Category1Completed;
        float total = Category1Total;

        float progressValue = (float)total_completed/(float)total;
        ((AchievementActivity)getActivity()).setProgress(progressValue);

      //get user data from session
        HashMap<String, String> user = session.getUserDetails();
        characterName = user.get(SessionManager.KEY_NAME);
        characterID = user.get(SessionManager.KEY_ID);
        characterlegacy = user.get(SessionManager.KEY_LEGACY);
        
        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory2(Category1);

		mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.achievementgridview);

		if (mRecyclerView != null) {
			mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
			mRecyclerView.setLayoutManager(mLayoutManager);
		}


		//Set Adapter
		mRecycleAdapter = new AchievementCategoryAdapter(getActivity(), achievements);
		mRecyclerView.setAdapter(mRecycleAdapter);

		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			public void onItemClick(View view, int position) {
				Category3Fragment category3frag = new Category3Fragment();

				Bundle args = new Bundle();
				args.putString("category1", Category1);
				args.putString("category2", achievements.get(position).getCategory());
				args.putInt("category2_completed", achievements.get(position).getCompleted());
				args.putInt("category2_total", achievements.get(position).getTotal());
				category3frag.setArguments(args);

				FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category3frag, "Category3");
			}

			public void onItemLongClick(View view, int position) {

			}
		}));
        
     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
		//((AchievementActivity)getActivity()).setTitleText(Category1);
	}

	@Override
	public void onResume() {
		super.onResume();
		((AchievementActivity)getActivity()).setTitleText(Category1);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category1Completed) + "/" + String.valueOf(Category1Total));

		float total_completed = Category1Completed;
		float total = Category1Total;

		float progressValue = (float)total_completed/(float)total;
		((AchievementActivity)getActivity()).setProgress(progressValue);
	}
}

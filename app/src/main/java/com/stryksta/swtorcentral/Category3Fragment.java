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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Category3Fragment extends Fragment {
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
	String Category2;
	Integer Category2Completed;
	Integer Category2Total;

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
			Category2Completed = getArguments().getInt("category2_completed");
			Category2Total = getArguments().getInt("category2_total");
        }
        
        Log.d("SWTORCentral", Thread.currentThread().getName());
        
        getActivity().setTitle(Category2);

		//Set title/completed/total of category
		((AchievementActivity)getActivity()).setTitleText(Category2);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category2Completed) + "/" + String.valueOf(Category2Total));

		float total_completed = Category2Completed;
		float total = Category2Total;

		float progressValue = (float)total_completed/(float)total;
		((AchievementActivity)getActivity()).setProgress(progressValue);

        db = new AchievementsDatabase(getActivity());
        achievements = db.getCategory3(Category1, Category2);

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
				Category4Fragment category4frag = new Category4Fragment();

				Bundle args = new Bundle();
				args.putString("category1", Category1);
				args.putString("category2", Category2);
				args.putString("category3", achievements.get(position).getCategory());
				args.putInt("category3_completed", achievements.get(position).getCompleted());
				args.putInt("category3_total", achievements.get(position).getTotal());
				category4frag.setArguments(args);

				FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category4frag, "Category4");
			}

			public void onItemLongClick(View view, int position) {

			}
		}));

     	return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle(Category1);
	}

	@Override
	public void onResume() {
		super.onResume();
		((AchievementActivity)getActivity()).setTitleText(Category2);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category2Completed) + "/" + String.valueOf(Category2Total));

		float total_completed = Category2Completed;
		float total = Category2Total;

		float progressValue = (float)total_completed/(float)total;
		((AchievementActivity)getActivity()).setProgress(progressValue);
	}
}

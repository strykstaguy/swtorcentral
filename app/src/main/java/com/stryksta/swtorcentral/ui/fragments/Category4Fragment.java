package com.stryksta.swtorcentral.ui.fragments;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.AchievementItemAdapter;
import com.stryksta.swtorcentral.models.AchievementsItem;
import com.stryksta.swtorcentral.ui.activities.AchievementActivity;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Category4Fragment extends Fragment{
	private AchievementsDatabase db;
	ArrayList<AchievementsItem> achievements = new ArrayList<AchievementsItem>();
	private AchievementItemAdapter mRecycleAdapter;
	private GridLayoutManager mLayoutManager;
	private RecyclerView mRecyclerView;
	Handler handler;
	String Category1;
	String Category2;
	String Category3;
    HashMap<Integer,Integer> achCompletedandTotal;

	int Category4Completed;
    int Category4Total;
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
        
        handler = new Handler();
        
        if ( getArguments().getString("category1") != null ) {
        	Category1 = getArguments().getString("category1");
        	Category2 = getArguments().getString("category2");
        	Category3 = getArguments().getString("category3");
			Category4Completed = getArguments().getInt("category4_completed");
			Category4Total = getArguments().getInt("category4_total");
        }

		//Set title of category
		((AchievementActivity)getActivity()).setTitleText(Category3);

        getActivity().setTitle(Category3);

		//Set title/completed/total of category
		((AchievementActivity)getActivity()).setTitleText(Category2);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category4Completed) + "/" + String.valueOf(Category4Total));

		int progressValue = (int) Math.ceil((Category4Completed/Category4Total)* 100);
        //Log.d("Progress: ", String.valueOf(progressValue));
        //Log.d("Progress: ", String.valueOf(Category4Completed) + "/" + String.valueOf(Category4Total));
        ((AchievementActivity)getActivity()).setAchievementProgress(progressValue);

        db = new AchievementsDatabase(getActivity());
        achievements = db.getAchievements(Category1, Category2, Category3);

		mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.achievementItems);

		if (mRecyclerView != null) {
			mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
			mRecyclerView.setLayoutManager(mLayoutManager);
		}
		//Set Adapter
		mRecycleAdapter = new AchievementItemAdapter(achievements);
		mRecyclerView.setAdapter(mRecycleAdapter);


		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
		{
			public void onItemClick(View view, int position)
			{
			}

			public void onItemLongClick(View view, int position)
			{
				if (achievements.get(position).getCompleted() == 0) {
					db.setCompleted(achievements.get(position).getAchievementID());
				} else {
					db.removeCompleted(achievements.get(position).getAchievementID());
				}

				updateItems();
			}
		}));

     	return vw_layout;
	}
	
	public void updateItems() {

		db = new AchievementsDatabase(getActivity());

        //Update List to reflect new completed/incomplete items
	    achievements.clear();
        achievements = db.getAchievements(Category1, Category2, Category3);
		mRecycleAdapter.updateItems(achievements);
		mRecycleAdapter.notifyDataSetChanged();

        //Get new total and reflect
        achCompletedandTotal = db.getCompletedandTotal(Category1, Category2, Category3);

        int achCompleted;
        int achTotal;

        Map.Entry<Integer,Integer> entry = achCompletedandTotal.entrySet().iterator().next();
        achCompleted = entry.getKey();
        achTotal = entry.getValue();

        Log.d("New Progress: ", String.valueOf(achCompleted) + "/" + String.valueOf(achTotal));
        int progressValue = (int)(((double)achCompleted/(double)achTotal) * 100);
        ((AchievementActivity)getActivity()).setAchievementProgress(progressValue);
        ((AchievementActivity)getActivity()).setPoints(String.valueOf(achCompleted) + "/" + String.valueOf(achTotal));
		db.close();
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();

	}
}

package com.stryksta.swtorcentral;


import java.util.ArrayList;
import com.stryksta.swtorcentral.adapters.AchievementItemAdapter;
import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

	Integer Category3Completed;
	Integer Category3Total;
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
			Category3Completed = getArguments().getInt("category3_completed");
			Category3Total = getArguments().getInt("category3_total");
        }

		//Set title of category
		((AchievementActivity)getActivity()).setTitleText(Category3);

        getActivity().setTitle(Category3);

		//Set title/completed/total of category
		((AchievementActivity)getActivity()).setTitleText(Category2);
		((AchievementActivity)getActivity()).setPoints(String.valueOf(Category3Completed) + "/" + String.valueOf(Category3Total));

		int progressValue = (int) Math.ceil(((double)Category3Completed/(double)Category3Total)* 100);
		((AchievementActivity)getActivity()).setProgress(progressValue);

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

	    achievements.clear();
        achievements = db.getAchievements(Category1, Category2, Category3);

		mRecycleAdapter.updateItems(achievements);
		mRecycleAdapter.notifyDataSetChanged();

		db.close();
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();

	}
}

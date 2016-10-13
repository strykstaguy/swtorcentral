package com.stryksta.swtorcentral.ui.fragments;


import java.util.ArrayList;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.AchievementCategoryAdapter;
import com.stryksta.swtorcentral.models.AchievementCategoryItem;
import com.stryksta.swtorcentral.ui.activities.AchievementActivity;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AchievementsDatabase;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Category1Fragment extends Fragment{
	private AchievementsDatabase db;
	
	SessionManager session;
	String characterName;
	String characterID;
	String characterlegacy;
	
	ArrayList<AchievementCategoryItem> categories = new ArrayList<AchievementCategoryItem>();
	private AchievementCategoryAdapter mRecycleAdapter;
	private GridLayoutManager mLayoutManager;
	private RecyclerView mRecyclerView;
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

		//Set title of category
		((AchievementActivity)getActivity()).setTitleText("Achievements");

        db = new AchievementsDatabase(getActivity());
        categories = db.getCategory1();

		mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.achievementgridview);

		if (mRecyclerView != null) {
			mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
			//mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(10));
			mRecyclerView.setLayoutManager(mLayoutManager);
		}

		//Set Adapter
		mRecycleAdapter = new AchievementCategoryAdapter(getActivity(), categories);
        mRecyclerView.setNestedScrollingEnabled(false);
		mRecyclerView.setAdapter(mRecycleAdapter);

		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			public void onItemClick(View view, int position) {
				Category2Fragment category2frag = new Category2Fragment();

				Bundle args = new Bundle();
				args.putString("category1", categories.get(position).getCategory());
				args.putInt("category1_completed", categories.get(position).getCompleted());
				args.putInt("category1_total", categories.get(position).getTotal());
				args.putFloat("category1_progress", categories.get(position).getTotal());
				category2frag.setArguments(args);

				FragmentUtils.addFragmentsInActivity(getActivity(), R.id.achievementframe, category2frag, "Category2");
			}

			public void onItemLongClick(View view, int position) {

			}
		}));

		return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Achivements");
	}

	@Override
	public void onResume() {
		super.onResume();
		((AchievementActivity)getActivity()).setTitleText("Achievements");
	}
}

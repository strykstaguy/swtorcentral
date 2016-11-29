package com.stryksta.swtorcentral.ui.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.ClassItem;
import com.stryksta.swtorcentral.models.CodexCategoryItem;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.ui.activities.ClassActivity;
import com.stryksta.swtorcentral.ui.adapters.ClassesCategoryAdapter;
import com.stryksta.swtorcentral.ui.adapters.CodexCategoryAdapter;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;

public class CodexCategoryFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<CodexCategoryItem> cdxCategoryItems;
    private CodexCategoryAdapter mRecycleAdapter;

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
		
        vw_layout = inflater.inflate(R.layout.codex_category_main, container, false);

        getActivity().setTitle("Classes");

        //Set RecyclerView
        mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.codexCategoryList);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        codexDB = new CodexDatabase(getActivity());
        cdxCategoryItems = codexDB.getCategories();

        //Set Adapter
        mRecycleAdapter = new CodexCategoryAdapter(getActivity(), cdxCategoryItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                CodexDetailFragment codexDetailFragment = new CodexDetailFragment();

                Bundle args = new Bundle();
                args.putString("codexCategory", cdxCategoryItems.get(position).getCategory());
                codexDetailFragment.setArguments(args);

                FragmentUtils.addFragmentsInActivity(getActivity(), R.id.codexFrame, codexDetailFragment, "Codex Detail");
            }

            public void onItemLongClick(View view, int position) {

            }
        }));
		
		return vw_layout;
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	    //getActivity().getActionBar().setTitle("Previous Title");
	}
	
}

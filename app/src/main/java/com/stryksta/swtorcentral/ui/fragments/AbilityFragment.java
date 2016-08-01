package com.stryksta.swtorcentral.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.models.AbilitiesItem;
import com.stryksta.swtorcentral.util.DividerItemDecoration;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;

import java.util.ArrayList;


public class AbilityFragment extends Fragment {
    // Store instance variables
    private String clsName;
    private String clsApc;
    private int clsTabPage;

    //Abilities
    private AbilitiesDatabase abilitiesDatabase;
    ArrayList<AbilitiesItem> abilitiesItems;
    private RecyclerView aRecyclerView;
    private GridLayoutManager aLayoutManager;
    private AbilityDetailAdapter aRecycleAdapter;

	View vw_layout;

    // newInstance constructor for creating fragment with arguments
    public static AbilityFragment newInstance(int clsTabPage, String clsName, String clsApc) {
        AbilityFragment fragmentFirst = new AbilityFragment();
        Bundle args = new Bundle();
        args.putInt("clsTabPage", clsTabPage);
        args.putString("clsName", clsName);
        args.putString("clsApc", clsApc);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clsTabPage = getArguments().getInt("clsTabPage", 0);
        clsName = getArguments().getString("clsName");
        clsApc = getArguments().getString("clsApc");
    }

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

        vw_layout = inflater.inflate(R.layout.ability_tab, container, false);

		//TextView txtDescription = (TextView) vw_layout.findViewById(R.id.txtDescription);
		//txtDescription.setText(clsName);

        //Get Base Abilities
        abilitiesItems = new ArrayList<>();
        abilitiesDatabase = new AbilitiesDatabase(getActivity());
        abilitiesItems = abilitiesDatabase.getAbilities(clsApc);
        abilitiesDatabase.close();

        //Set RecyclerView
        aRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.abilitiesList);

        if (aRecyclerView != null) {
            aLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
            aRecyclerView.setLayoutManager(aLayoutManager);
        }

        //Set Adapter
        aRecycleAdapter = new AbilityDetailAdapter(abilitiesItems);
        aRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL));
        aRecyclerView.setAdapter(aRecycleAdapter);

		return vw_layout;
	}
}
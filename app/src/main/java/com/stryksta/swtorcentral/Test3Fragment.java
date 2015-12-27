package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.NonScrollListView;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;

import java.util.ArrayList;

public class Test3Fragment extends Fragment{

    private AbilitiesDatabase abilitiesDB;
    ArrayList<AbilitiesItem> playerAbilitiesItems;
    AbilityDetailAdapter playerAbilitiesAdapter;

    int advClassID;
    String advClassDesc;
    String advClassName;

    String ClassAPC;
    String advAPC;

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

        vw_layout = inflater.inflate(R.layout.test_main_abilities, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null ) {
            advClassID = getArguments().getInt("advClassID");
            advClassName = getArguments().getString("advClassName");
            advClassDesc = getArguments().getString("advClassDesc");

            ClassAPC = getArguments().getString("ClassAPC");
            advAPC = getArguments().getString("AdvAPC");
        }

        abilitiesDB = new AbilitiesDatabase(getActivity());
        playerAbilitiesItems = abilitiesDB.getPlayerAbilities();

        NonScrollListView lstPlayerAbilities = (NonScrollListView) vw_layout.findViewById(R.id.lstPlayerAbilities);
        playerAbilitiesAdapter = new AbilityDetailAdapter(getActivity(), playerAbilitiesItems);

        lstPlayerAbilities.setAdapter(playerAbilitiesAdapter);

        abilitiesDB.close();

     	return vw_layout;
	}
}

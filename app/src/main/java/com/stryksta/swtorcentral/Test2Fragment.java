package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.stryksta.swtorcentral.util.database.DisciplinesDatabase;

public class Test2Fragment extends Fragment{

    ListView disciplinesListView;
    private DisciplinesDatabase disciplinesDB;

    int advClassID;
    String advClassName1;

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

        vw_layout = inflater.inflate(R.layout.test_main_discplines, container, false);

        if ( getArguments().getString("category1") != null ) {
            advClassID = getArguments().getInt("advancedclass");
            //Category2 = getArguments().getString("category2");
        }
        //Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();

     	return vw_layout;
	}
}

package com.stryksta.swtorcentral.ui.fragments;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.util.database.PlanetDatabase;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlanetTab1 extends Fragment {
	private PlanetDatabase db;
	private String planetText;
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
		
        vw_layout = inflater.inflate(R.layout.planet_tab1, container, false);
        
        Bundle bundle = getActivity().getIntent().getExtras();
		
        if ( bundle != null ) {
        	planetText = bundle.getString("planet");
        }
        
        db = new PlanetDatabase(getActivity());
		String Description = db.PlanetDescription(planetText);
		TextView txtDescription = (TextView) vw_layout.findViewById(R.id.txtDescription);
		db.close();

		txtDescription.setText(Description);
		
		return vw_layout;
	}
}
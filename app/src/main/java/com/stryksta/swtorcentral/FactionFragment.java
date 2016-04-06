package com.stryksta.swtorcentral;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.stryksta.swtorcentral.util.ColorItem;

public class FactionFragment extends Fragment {
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
		
        vw_layout = inflater.inflate(R.layout.faction_main, container, false);
        
        getActivity().setTitle("Faction");

        //ColorItem layoutCheck = (ColorItem) vw_layout.findViewById(R.id.test_check);
        //layoutCheck.setActivated(true);
        //layoutCheck.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.swtor_blue));


		//Clickable Republic Logo
        CardView layoutRepublic = (CardView) vw_layout.findViewById(R.id.layoutRepublic);
     	layoutRepublic.setOnClickListener(new View.OnClickListener() {      
         public void onClick(View v) {
 	        Bundle bundle = new Bundle();
            bundle.putString("faction", "Republic");
            
            Intent intent = new Intent(getActivity(), ProgressionActivity.class);
            intent.putExtras(bundle);
	        startActivity(intent);
         }
        });
     	
     	//Clickable Empire Logo
        CardView layoutEmpire = (CardView) vw_layout.findViewById(R.id.layoutEmpire);
     	layoutEmpire.setOnClickListener(new View.OnClickListener() {      
         public void onClick(View v) {
        	 Bundle bundle = new Bundle();
             bundle.putString("faction", "Empire");
             
             Intent intent2 = new Intent(getActivity(), ProgressionActivity.class);
             intent2.putExtras(bundle);
 	        startActivity(intent2);
         }
        });
     	
     	return vw_layout;
	}
}

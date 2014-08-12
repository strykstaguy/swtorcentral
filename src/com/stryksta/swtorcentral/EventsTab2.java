package com.stryksta.swtorcentral;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EventsTab2 extends Fragment {
	private final String htmlText = "<p><i><b>LEVEL</b></i>: Levels 50 – 55<br><br>" + 
			"<i><b>MISSION</b></i>: Explore Ilum's contested area in the Western Ice Shelf to uncover the mysterious purpose of the Gray Secant, an enormous ancient Gree starship. Get ready to face a powerful opponent that awaits you at the center of this ancient vessel. Visit the in-game News Terminal located on Carrick Station or Vaiken Spacedock to get started on your adventure!<br><br>" + 
			"<i><b>FEATURED REWARDS</b></i>: <br><br>" + 
			"<ul>" + 
				"<li>NEWLY ADDED: Gree Digitization Cube (Gree-themed rest-and-recharge item)" + 
				"<li>NEWLY ADDED: Cyan Sphere (requires Snow Parcels from Life Day Event and Gray Helix Components from the Gree Event)" + 
				"<li>Reputation with the Gree Enclave</li>" + 
				"<li>Armor: White, Red, Blue Scalene Armor</li>" + 
				"<li>Weapons: Gray Helix Weapons, Gray Helix Lightsaber</li>" + 
				"<li>In-game pets: L1-L Defender, L1-L Scout, Miniature Gray Secant</li>" + 
				"<li>Mounts: Blue Sphere and Red Sphere Transport Enclosure Vehicles</li>" + 
				"<li>…and more!</li>" + 
			"</ul></p>";
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
		
        vw_layout = inflater.inflate(R.layout.events_tab2, container, false);
        
        TextView htmlTextView = (TextView) vw_layout.findViewById(R.id.textEventinfo);
        htmlTextView.setText(Html.fromHtml(htmlText));
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}

}
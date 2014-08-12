package com.stryksta.swtorcentral;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EventsTab3 extends Fragment {
	private final String htmlText = "<p><i><b>LEVEL</b></i>: Levels 25+ <br><br>" +
			"<i><b>MISSION</b></i>: Wide-spread infections have been reported near both House Organa and House Thul, while scattered reports of infection traceable to Alderaan are now flooding in from across the galaxy.  Quarantines have been put in place to restrict traffic of the plague off-world, but exceptions will be made for individuals with priority clearance.<br><br>" +
			"T.H.O.R.N. is recruiting qualified volunteer emergency responders to travel to affected areas to combat the spread of the Rakghoul plague. More information about the outbreak can be found by checking the News Terminals on the Fleet!</p>";
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
		
        vw_layout = inflater.inflate(R.layout.events_tab3, container, false);
        
        TextView htmlTextView = (TextView) vw_layout.findViewById(R.id.textEventinfo);
        htmlTextView.setText(Html.fromHtml(htmlText));
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}

}
package com.stryksta.swtorcentral;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class EventsTab4 extends Fragment {
	private final String htmlText = "<p><i><b>LEVEL</b></i>: Levels 10 – 55 <br><br>" +
			"<i><b>MISSION</b></i>: Celebrate Life Day with two weeks of snowball fights! Every snowball you throw during the Life Day Event has a chance to award you with a Snow-Covered Parcel. Collect and exchange your Snow-Covered Parcels for festive rewards at the “Master of Ceremonies” located in the Galactic Trade Market sector in the Imperial and Republic fleets. Snowball Bombs and Fireworks can be purchased for credits from the Master of Ceremonies. Watch out for those snowballs!<br><br>" + 
			"<i><b>FEATURED REWARDS:</b></i>: <br><br>" +
			"<ul>" +
				"<li>Mount: Sleigh I</li>" +
				"<li>Mount: Cyan Sphere (requires Snow Parcels from Life Day Event and Gray Helix Components from the Gree Event)</li>" +
				"<li>Achievements:</li><br><ul>" +
				"<li>Jolly Parcel Peddler (Legacy Title: Life Day Celebrant)</li>" +
				"<li>Merry Life Day To All!</li>" +
				"<li>To All A Good Night! (Legacy Title: A Special Snowflake)</li></ul>" +
				"<li>Fireworks: Life Day Tree</li>" +
				"<li>Life Day Snowball Bomb</li>	" +
			"</ul>" +
			"<br>" +
			"</p>";
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
		
        vw_layout = inflater.inflate(R.layout.events_tab4, container, false);
        
        TextView htmlTextView = (TextView) vw_layout.findViewById(R.id.textEventinfo);
        htmlTextView.setText(Html.fromHtml(htmlText));
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
     		
		return vw_layout;
	}

}
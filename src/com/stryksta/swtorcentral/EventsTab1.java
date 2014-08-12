package com.stryksta.swtorcentral;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stryksta.swtorcentral.data.ServerItem;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class EventsTab1 extends Fragment {

	GridView gridView;
	TextView settingsView;
	ArrayList<ServerItem> rowItems;
	View vw_layout;
	String prefnc;

	private final String htmlText = "<p><i><b>LEVEL</b></i>: Levels 15 – 55 <br><br>" + 
			"<i><b>MISSION</b></i>: For a limited time, the Bounty Brokers Association has opened its doors to anyone willing to take on their dangerous contracts, offering an opportunity for both seasoned and rookie hunters to prove their skills and bring crime syndicates and violent gangs to justice! <br><br>" + 
			"Each day, you can sign up for one standard \"Henchman\" contract and one high profile Kingpin Contract, and each may take you to a separate planet. Complete five standard contracts to unlock Kingpin missions with bigger loot! Head to the fleet now, invite your friends and visit representatives of the Association to get started on your contracts!<br><br>" + 

			"<i><b>TO GET YOU STARTED</b></i>: <br><br>" + 
			"<ol>" + 
			    "<li>1. Head to the fleet</li>" + 
			    "<li>2. Look for BBA representatives standing near carbonized bounties</li>" +
			    "<li>3. Head to the Cartel Bazaar to use the mission terminals</li>" +
			    "<li>4. Choose your mission, locate the target, kill or freeze it</li>" +
			    "<li>5. Collect great rewards!</li>" +
			    "<li>6. Repeat 5 times (1 a day per character) to unlock Kingpin missions!</li>" +
			"</ol>" +
			"<br>" +


			"<i><b>FEATURED REWARDS</b></i>: <br><br>" +
			"<ul>" +
			    "<li>Reputation with the Bounty Brokers Association</li>" +
			    "<li>Armor: Bounty Tracker’s Armor, Contract Hunter’s Armor</li>" +
			    "<li>Weapons: Assassin’s Bowcaster, Elite Tracker’s Bowcaster</li>" +
			    "<li>In-game pets: Lobelot, Swamp Kell Drake, Drink Server Probe</li>" +
			    "<li>BH-7X Custom Hunter Mount</li>" +
			    "<li>…and more!</li></p>" +
			"</ul>";

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
		
        vw_layout = inflater.inflate(R.layout.events_tab1, container, false);
        
        TextView htmlTextView = (TextView) vw_layout.findViewById(R.id.textEventinfo);
        htmlTextView.setText(Html.fromHtml(htmlText));

        if (MainActivity.isNetworkAvailable(getActivity())) {
        	
        	 new GetEventStatus().execute();
        } else {
            Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
        }
        
       
		
		return vw_layout;
	}
	
	private class GetEventStatus extends AsyncTask<Void, Void, Void> {
		String txtStatus;
		String txtDates;
		@Override
		protected Void doInBackground(Void... urls) {

			try {
				String URL = "http://www.swtor.com/info/in-game-events";
				Document doc = Jsoup.connect(URL).get();
				
				Elements USServers = doc.select("div.section h3");
				
				
				//txtStatus = USServers.text();
				txtDates = USServers.text();
				txtDates = txtDates.split("[\\(\\)]")[1];
						
				for(Element USServer: USServers) {
					txtStatus = USServer.child(0).text();
					/*if (txtStatus.contains("LIVE")) {
						txtStatus = "Live";
					}
					*/
				}
				
			} catch (Exception e) {
				if(e.getMessage() != null) {
				    Log.e("SWTORCentral", e.getMessage());
				}
			}
			
			// Debug the task thread name
			Log.d("SWTORCentral", Thread.currentThread().getName());
			return null;
		}

		protected void onPostExecute(Void result) {
			//String serverStatus = USServer.child(0).text();
			TextView txttitle = (TextView) vw_layout.findViewById(R.id.txtStatus);
			TextView txtdates = (TextView) vw_layout.findViewById(R.id.txtDates);
			
			/*
			if (txtStatus.equalsIgnoreCase("Live")) {
				txttitle.setTextColor(getResources().getColor(R.color.swtor_green));
			} else {
				txttitle.setTextColor(getResources().getColor(R.color.swtor_orange));
			}
			*/
			txttitle.setTextColor(getResources().getColor(R.color.swtor_orange));
			txttitle.setText(txtStatus);
			txtdates.setText(txtDates);
			
		}
	}
}
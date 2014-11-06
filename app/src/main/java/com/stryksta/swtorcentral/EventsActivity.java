package com.stryksta.swtorcentral;
 
import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.stryksta.swtorcentral.data.EventItem;
import com.stryksta.swtorcentral.util.MaterialProgress;
import com.stryksta.swtorcentral.util.Utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class EventsActivity extends FragmentActivity {
    GridView eventsGridView;
    ArrayList<EventItem> eventsItems;
    MaterialProgress pDialog;
    String eventTitle;
    String eventStatus;
    String eventStart;
    String eventEnd;
    String eventDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_main);

        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

        eventsGridView = (GridView) findViewById(R.id.event_list);

        if (MainActivity.isNetworkAvailable(EventsActivity.this)) {
            new GetEvents().execute();
        } else {
            Toast.makeText(EventsActivity.this, "Network is unavailable", Toast.LENGTH_LONG).show();
        }

     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }

    private class GetEvents extends AsyncTask<String, Void, ArrayList<EventItem>> {

        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            pDialog = new MaterialProgress(EventsActivity.this, "Events");
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(false);
            pDialog.show();

        }

        @Override
        protected ArrayList<EventItem> doInBackground(String... urls) {

            try {
                String URL = "http://www.swtor.com/info/in-game-events";
                eventsItems = new ArrayList<EventItem>();

                Document doc = Jsoup.connect(URL).get();
                Elements events = doc.select("div.section");

                for(Element event: events) {
                    //Get Header
                    Element masthead = event.select("h3").first();

                    //Convert to String
                    String h3Header = masthead.text();
                    String[] separated = h3Header.split(":");

                    eventStatus = Utility.toTitleCase(separated[0]);
                    //Log.e("SWTORCentral", "Status: " + Utility.toTitleCase(separated[0]));

                    //Remove last word and whitespace
                    String TitleTxt = separated[1].replace("BEGINS","");
                    TitleTxt.replaceAll("\\s+","");

                    eventTitle = Utility.toTitleCase(TitleTxt);
                    //Log.e("SWTORCentral", "Title: " + Utility.toTitleCase(TitleTxt));

                    //If online or coming soon, print the dates
                    if (!separated[0].equalsIgnoreCase("OFFLINE")) {
                        //Remove last word and whitespace

                        String beginsTxt = separated[2].replace("ENDS","");
                        beginsTxt.replaceAll("\\s+","");

                        String beforedate = beginsTxt.replaceAll("\\bAT\\b", "");

                        //get everything before "AT"
                        //String beforedate = Utility.toTitleCase(Utility.subStringBefore(beginsTxt, "AT"));

                        Log.e("SWTORCentral", "Begins: " + beforedate);

                        eventStart = beforedate;
                        eventEnd = separated[3];

                        //Log.e("SWTORCentral", "Begins: " + beginsTxt);
                        //Log.e("SWTORCentral", "Ends: " + separated[3]);
                    } else {
                        eventStart = "";
                        eventEnd = "";
                    }

                    //Get Header
                    Element description = event.select("div.desc").first();

                    //Convert to String
                    eventDescription = description.html();

                    //Log.e("SWTORCentral", "Descript: " + txtdescription);

                    //String eventStatus, String eventTitle, String eventStart, String eventEnd, String eventDescription

                    EventItem item = new EventItem(R.drawable.ic_action_serverup, eventStatus, eventTitle, eventStart, eventEnd, eventDescription);
                    eventsItems.add(item);
                }
            } catch (Exception e) {
                pDialog.dismiss();
                if(e.getMessage() != null) {
                    Log.e("SWTORCentral", e.getMessage());
                }
            }

            // Debug the task thread name
            Log.d("SWTORCentral", Thread.currentThread().getName());
            return null;
        }

        protected void onPostExecute(ArrayList<EventItem> result) {
            EventAdapter eventsAdapter = new EventAdapter(EventsActivity.this, R.layout.event_row, eventsItems);
            eventsGridView.setAdapter(eventsAdapter);

            pDialog.dismiss();
        }
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
}
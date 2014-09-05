package com.stryksta.swtorcentral;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.stryksta.swtorcentral.data.RssItem;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.RssReader;
import com.stryksta.swtorcentral.util.RssDatabaseHandler;


public class ReaderActivity extends Fragment {

	// A reference to the local object
	private RssAdapter adapter;
	private RssDatabaseHandler db;
	AutoMeasureGridView gridView;
	View vw_layout;
	ArrayList<RssItem> rssItems;
	
	
	// private final ArrayList<RssItem> fetch = new ArrayList<RssItem>();

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
		
        vw_layout = inflater.inflate(R.layout.reader_main, container, false);

        
        
        //if (MainActivity.isNetworkAvailable(getActivity())) {
        	GetRSSDataTask task = new GetRSSDataTask();
    		// Start download RSS task
    		task.execute("http://www.swtor.com/feed/news/all");
        //} else {
            //Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
            
        //}
        
		// Debug the thread name
		Log.d("SWTORCentral", Thread.currentThread().getName());
		
		return vw_layout;
	}

	private class GetRSSDataTask extends AsyncTask<String, Void, ArrayList<RssItem>> {
		@Override
		protected ArrayList<RssItem> doInBackground(String... urls) {

			// Debug the task thread name
			Log.d("SWTORCentral", Thread.currentThread().getName());

			try {
				// Create RSS reader
				RssReader rssReader = new RssReader(urls[0]);
				// Parse RSS, get items
				return rssReader.getItems();

			} catch (Exception e) {
				if(e.getMessage() != null) {
				    Log.e("SWTORCentral", e.getMessage());
				}
			}

			return null;
		}

		protected void onPostExecute(ArrayList<RssItem> result) {
					//Open Database
					db = new RssDatabaseHandler(getActivity());
			        db.open();
			    
			    //Add to database as long as results are not empty
			    if(null != result && !result.isEmpty()) { 
					for(RssItem item : result){
						db.insertNewsInfo(item);
					}
					
				}
					rssItems =  db.getNews();
					
					db.close();
					//End Add to Database
					
					// Get a ListView from main view
					gridView = (AutoMeasureGridView) vw_layout.findViewById(R.id.reader_list);
					adapter = new RssAdapter(getActivity(), android.R.layout.simple_list_item_1, rssItems);
					gridView.setAdapter(adapter);
					
					if (MainActivity.isNetworkAvailable(getActivity())) {
						
						gridView.setOnItemClickListener(new OnItemClickListener() {
							public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
							    {
									Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse( adapter.getItem(position).getLink()));
									startActivity(intent);
							    }});
						
					} else {
						Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
		            
					}
		    		
					
            
		}
		
	}
	
}
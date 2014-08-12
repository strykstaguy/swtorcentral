package com.stryksta.swtorcentral;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stryksta.swtorcentral.data.ServerItem;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class ServerTab1 extends Fragment {

	GridView gridView;
	TextView settingsView;
	ArrayList<ServerItem> rowItems;
	View vw_layout;
	String prefnc;

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
		
        vw_layout = inflater.inflate(R.layout.server_tab1, container, false);
        
        if (MainActivity.isNetworkAvailable(getActivity())) {
        	 new GetServerStatus().execute();
        } else {
            Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
        }
        
       
		
		return vw_layout;
	}
	
	private class GetServerStatus extends AsyncTask<String, Void, ArrayList<ServerItem>> {
		@Override
		protected ArrayList<ServerItem> doInBackground(String... urls) {

			try {
				String URL = "http://www.swtor.com/server-status";
				rowItems = new ArrayList<ServerItem>();
				Document doc = Jsoup.connect(URL).get();
				
				Elements USServers = doc.select("div#serverListUS div.serverBody:not(.serverMenu)");
				for(Element USServer: USServers) {
					String serverStatus = USServer.child(0).text();
					String serverName = USServer.child(1).text();
					String serverPop = USServer.child(2).text();
					int serverStatusIMG = R.drawable.navigation_down;
					//ServerItem mItem = new ServerItem(R.drawable.navigation_up, serverName, serverPop);
					//int serverIcon, String serverRegion, String serverName,  String serverStatus
					
					if (!serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.navigation_up;
		            }
					
					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
					rowItems.add(item);
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

		protected void onPostExecute(ArrayList<ServerItem> result) {
			ServerAdapter mAdapter = new ServerAdapter(getActivity(), R.layout.server_row, rowItems);
			gridView = (GridView) vw_layout.findViewById(R.id.uslist);
			gridView.setAdapter(mAdapter);
		}
	}
}
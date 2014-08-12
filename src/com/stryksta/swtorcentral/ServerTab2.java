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
import android.widget.Toast;


public class ServerTab2 extends Fragment {

	GridView gridView;
	ArrayList<ServerItem> rowItems;
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
		
        vw_layout = inflater.inflate(R.layout.server_tab2, container, false);
        
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
				
				Elements EUServers = doc.select("div#serverListEU div.serverBody:not(.serverMenu)");
				for(Element EUServer: EUServers) {
					
					String serverStatus = EUServer.child(0).text();
					String serverName = EUServer.child(1).text();
					String serverPop = EUServer.child(2).text();
					int serverStatusIMG = R.drawable.navigation_down;
					
					if (!serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.navigation_up;
		            }
					
					
					//ServerItem mItem = new ServerItem(R.drawable.navigation_up, serverName, serverPop);
					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
					rowItems.add(item);
				}

			} catch (Exception e) {
				if(e.getMessage() != null) {
				    Log.e("ITCRssReader", e.getMessage());
				}
			}
			
			// Debug the task thread name
			Log.d("ITCRssReader", Thread.currentThread().getName());
			return null;
		}

		protected void onPostExecute(ArrayList<ServerItem> result) {
			ServerAdapter mAdapter = new ServerAdapter(getActivity(), R.layout.server_row, rowItems);
			gridView = (GridView) vw_layout.findViewById(R.id.eulist);
			gridView.setAdapter(mAdapter);
		}
	}
}
package com.stryksta.swtorcentral;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.stryksta.swtorcentral.data.ServerItem;
import com.stryksta.swtorcentral.util.MaterialDialog;
import com.stryksta.swtorcentral.util.MaterialProgress;
import com.stryksta.swtorcentral.util.NonScrollGridView;
import com.stryksta.swtorcentral.util.Utility;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ServerActivity extends FragmentActivity  {
	
	NonScrollGridView usGridView;
	NonScrollGridView euGridView;
	
	ArrayList<ServerItem> usItems;
	ArrayList<ServerItem> euItems;
	
	MaterialProgress pDialog;
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_main);
		
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        usGridView = (NonScrollGridView) findViewById(R.id.uslist);
        euGridView = (NonScrollGridView) findViewById(R.id.eulist);

        if (MainActivity.isNetworkAvailable(ServerActivity.this)) {
       	 new GetServerStatus().execute();
       } else {
           Toast.makeText(ServerActivity.this, "Network is unavailable", Toast.LENGTH_LONG).show();
       }
        
		// Debug the thread name
		Log.d("SWTORCentral", Thread.currentThread().getName());
	}
	
    private class GetServerStatus extends AsyncTask<String, Void, ArrayList<ServerItem>> {
    	
    	protected void onPreExecute() {
 			super.onPreExecute();
 			// Create a progressbar
 			pDialog = new MaterialProgress(ServerActivity.this, "Server Status");
 			pDialog.setMessage("Loading...");
 			pDialog.setIndeterminate(false);
 			pDialog.show();

 		}
    	
		@Override
		protected ArrayList<ServerItem> doInBackground(String... urls) {

			try {
				String URL = "http://www.swtor.com/server-status";
				usItems = new ArrayList<ServerItem>();
				euItems = new ArrayList<ServerItem>();
				
				Document doc = Jsoup.connect(URL).get();
				
				Elements USServers = doc.select("div#serverListUS div.serverBody:not(.serverMenu)");
				for(Element USServer: USServers) {
					String serverStatus = USServer.child(0).text();
					String serverName = USServer.child(1).text();
					String serverPop = USServer.child(2).text();
					int serverStatusIMG = R.drawable.ic_action_serverdown;
					//ServerItem mItem = new ServerItem(R.drawable.navigation_up, serverName, serverPop);
					//int serverIcon, String serverRegion, String serverName,  String serverStatus
					
					if (!serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.ic_action_serverup;
		            }
					
					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
					usItems.add(item);
				}
				
				Elements EUServers = doc.select("div#serverListEU div.serverBody:not(.serverMenu)");
				for(Element EUServer: EUServers) {
					
					String serverStatus = EUServer.child(0).text();
					String serverName = EUServer.child(1).text();
					String serverPop = EUServer.child(2).text();
					int serverStatusIMG = R.drawable.ic_action_serverdown;
					
					if (!serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.ic_action_serverup;
		            }
					
					
					//ServerItem mItem = new ServerItem(R.drawable.navigation_up, serverName, serverPop);
					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
					euItems.add(item);
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

		protected void onPostExecute(ArrayList<ServerItem> result) {
			ServerAdapter usAdapter = new ServerAdapter(ServerActivity.this, R.layout.server_row, usItems);
			usGridView.setAdapter(usAdapter);
			
			ServerAdapter euAdapter = new ServerAdapter(ServerActivity.this, R.layout.server_row, euItems);
			euGridView.setAdapter(euAdapter);
			
			pDialog.dismiss();
			
			TextView txtServerUS = (TextView) findViewById(R.id.txtServerUS);
			txtServerUS.setVisibility(View.VISIBLE);
			
			TextView txtServerUSSub = (TextView) findViewById(R.id.txtServerUSSub);
			txtServerUSSub.setVisibility(View.VISIBLE);
			
			TextView txtServerEU = (TextView) findViewById(R.id.txtServerEU);
			txtServerEU.setVisibility(View.VISIBLE);
			
			TextView txtServerEUSub = (TextView) findViewById(R.id.txtServerEUSub);
			txtServerEUSub.setVisibility(View.VISIBLE);
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.server_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	    	break;
	    case R.id.server_help:
	    	View v = getLayoutInflater().inflate(R.layout.server_key, null);
	        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
	        builder.setTitle("Server Status Help");
	        builder.setView(v);
	        
	        ImageView serverLight = (ImageView) v.findViewById(R.id.IMGserverLight);
	        serverLight.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.lightColor)));
	        
	        ImageView serverStandard = (ImageView) v.findViewById(R.id.serverStandard);
	        serverStandard.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.standardColor)));
	          
	        ImageView serverHeavy = (ImageView) v.findViewById(R.id.serverHeavy);
	        serverHeavy.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.heavyColor)));
	         
	        ImageView serverVeryHeavy = (ImageView) v.findViewById(R.id.serverVeryHeavy);
	        serverVeryHeavy.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.veryheavyColor)));
	          
	        ImageView serverFull = (ImageView) v.findViewById(R.id.serverFull);
	        serverFull.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.fullColor)));
	          
	        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		dialog.dismiss();
	            }
	        });
	        
	        builder.show();
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
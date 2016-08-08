package com.stryksta.swtorcentral.ui.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.SectionedGridRecyclerViewAdapter;
import com.stryksta.swtorcentral.ui.adapters.ServerAdapter;
import com.stryksta.swtorcentral.models.ServerItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ServerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ServerAdapter mRecycleAdapter;

    private ArrayList<ServerItem> serverItems;
    List<SectionedGridRecyclerViewAdapter.Section> mSections;

    //MaterialDialog pDialog;

    private Toolbar mToolbar;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.serverList);

        mLayoutManager = new GridLayoutManager(ServerActivity.this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //This is the code to provide a sectioned list
        mSections = new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

        //Sections
        mSections.add(new SectionedGridRecyclerViewAdapter.Section(0,"US Servers"));
        mSections.add(new SectionedGridRecyclerViewAdapter.Section(8,"European Servers"));

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
 		}
    	
		@Override
		protected ArrayList<ServerItem> doInBackground(String... urls) {

			try {
				String URL = "http://www.swtor.com/server-status";
				serverItems = new ArrayList<ServerItem>();
				
				Document doc = Jsoup.connect(URL).get();
				
				Elements USServers = doc.select("div#serverListUS div.serverBody:not(.serverMenu)");
				for(Element USServer: USServers) {
					String serverStatus = USServer.child(0).text();
					String serverName = USServer.child(1).text();
					String serverPop = USServer.child(2).text();
                    String serverType = USServer.child(3).text();
                    String serverZone = USServer.child(4).text();
					int serverStatusIMG = R.drawable.ic_arrow_up;
					
					if (serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.ic_arrow_down;
		            }

					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop, serverType, serverZone);
                    serverItems.add(item);
				}
				
				Elements EUServers = doc.select("div#serverListEU div.serverBody:not(.serverMenu)");
				for(Element EUServer: EUServers) {
					
					String serverStatus = EUServer.child(0).text();
					String serverName = EUServer.child(1).text();
					String serverPop = EUServer.child(2).text();
                    String serverType = EUServer.child(3).text();
                    String serverZone = EUServer.child(4).text();
					int serverStatusIMG = R.drawable.ic_arrow_up;
					
					if (serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.ic_arrow_down;
		            }

					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop, serverType, serverZone);
                    serverItems.add(item);
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
            //Set Adapter
            mRecycleAdapter = new ServerAdapter(ServerActivity.this, serverItems);
            //mRecyclerView.setAdapter(mRecycleAdapter);

            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[mSections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new SectionedGridRecyclerViewAdapter(ServerActivity.this,R.layout.section,R.id.section_text,mRecyclerView,mRecycleAdapter);
            mSectionedAdapter.setSections(mSections.toArray(dummy));

            mRecyclerView.setAdapter(mSectionedAdapter);

		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	    	break;
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
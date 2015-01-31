package com.stryksta.swtorcentral;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.adapters.ReaderAdapter;
import com.stryksta.swtorcentral.adapters.SectionedGridRecyclerViewAdapter;
import com.stryksta.swtorcentral.adapters.ServerAdapter;
import com.stryksta.swtorcentral.adapters.SimpleSectionedRecyclerViewAdapter;
import com.stryksta.swtorcentral.data.ServerItem;
import com.stryksta.swtorcentral.util.DividerItemDecoration;
import com.stryksta.swtorcentral.util.FloatingActionButton;
import com.stryksta.swtorcentral.util.NonScrollGridView;
import com.stryksta.swtorcentral.util.Utility;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ServerActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ServerAdapter mRecycleAdapter;

    private ArrayList<ServerItem> serverItems;
    List<SectionedGridRecyclerViewAdapter.Section> mSections;

    MaterialDialog pDialog;

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

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.serverList);

        mLayoutManager = new GridLayoutManager(ServerActivity.this, 2);
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

        FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_help_white))
                .withButtonColor(getResources().getColor(R.color.swtor_blue))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View v = getLayoutInflater().inflate(R.layout.server_key, null);

                MaterialDialog dialog = new MaterialDialog.Builder(ServerActivity.this)
                        .title("Server Status")
                        .customView(v)
                        .positiveText("OK")
                        .build();

                ImageView serverLight = (ImageView) v.findViewById(R.id.IMGserverLight);
                serverLight.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.lightcolor)));

                ImageView serverStandard = (ImageView) v.findViewById(R.id.serverStandard);
                serverStandard.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.standardcolor)));

                ImageView serverHeavy = (ImageView) v.findViewById(R.id.serverHeavy);
                serverHeavy.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.heavycolor)));

                ImageView serverVeryHeavy = (ImageView) v.findViewById(R.id.serverVeryHeavy);
                serverVeryHeavy.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.veryheavycolor)));

                ImageView serverFull = (ImageView) v.findViewById(R.id.serverFull);
                serverFull.setColorFilter(Utility.getColoredMatrix(getResources().getColor(R.color.fullcolor)));


                dialog.show();

            }
        });
		// Debug the thread name
		Log.d("SWTORCentral", Thread.currentThread().getName());
	}
	
    private class GetServerStatus extends AsyncTask<String, Void, ArrayList<ServerItem>> {
    	
    	protected void onPreExecute() {
 			super.onPreExecute();
 			// Create a progressbar
            pDialog = new MaterialDialog.Builder(ServerActivity.this)
                    .title("Server Status")
                    .customView(R.layout.progress_alert)
                    .build();
            pDialog.show();
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
					int serverStatusIMG = R.drawable.ic_action_serverdown;
					
					if (!serverStatus.equals("DOWN")) {
						serverStatusIMG = R.drawable.ic_action_serverup;
		            }
					
					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
                    serverItems.add(item);
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

					ServerItem item = new ServerItem(serverStatusIMG, serverName, serverPop);
                    serverItems.add(item);
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
            //Set Adapter
            mRecycleAdapter = new ServerAdapter(ServerActivity.this, serverItems);
            //mRecyclerView.setAdapter(mRecycleAdapter);

            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[mSections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(ServerActivity.this,R.layout.section,R.id.section_text,mRecyclerView,mRecycleAdapter);
            mSectionedAdapter.setSections(mSections.toArray(dummy));

            mRecyclerView.setAdapter(mSectionedAdapter);

			pDialog.dismiss();
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
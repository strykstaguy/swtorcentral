package com.stryksta.swtorcentral.ui.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.ServerItem;
import com.stryksta.swtorcentral.ui.adapters.ServerAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ServerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewUS;
    private ServerAdapter mRecycleAdapterUS;

    private RecyclerView mRecyclerViewEU;
    private ServerAdapter mRecycleAdapterEU;

    private LinearLayoutManager mLayoutManagerUS;
    private LinearLayoutManager mLayoutManagerEU;

    private ArrayList<ServerItem> serverItemsUS;
    private ArrayList<ServerItem> serverItemsEU;

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
            getWindow().setStatusBarColor(ContextCompat.getColor(ServerActivity.this, R.color.colorPrimary));
        }

        //Set RecyclerView
        mRecyclerViewUS = (RecyclerView) findViewById(R.id.serverListUS);
        mRecyclerViewEU = (RecyclerView) findViewById(R.id.serverListEU);

        mLayoutManagerUS = new LinearLayoutManager(ServerActivity.this, LinearLayoutManager.VERTICAL, false);
        mLayoutManagerEU = new LinearLayoutManager(ServerActivity.this, LinearLayoutManager.VERTICAL, false);

        mRecyclerViewUS.setLayoutManager(mLayoutManagerUS);
        mRecyclerViewEU.setLayoutManager(mLayoutManagerEU);

        mRecyclerViewUS.setNestedScrollingEnabled(false);
        mRecyclerViewEU.setNestedScrollingEnabled(false);

        mRecyclerViewUS.setHasFixedSize(true);
        mRecyclerViewEU.setHasFixedSize(true);

        mRecyclerViewUS.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(ServerActivity.this)
                        .color(ContextCompat.getColor(ServerActivity.this, R.color.backgroundlight))
                        .sizeResId(R.dimen.divider)
                        .marginResId(R.dimen.divider_leftmargin, R.dimen.divider_rightmargin)
                        .build());

        mRecyclerViewEU.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(ServerActivity.this)
                        .color(ContextCompat.getColor(ServerActivity.this, R.color.backgroundlight))
                        .sizeResId(R.dimen.divider)
                        .marginResId(R.dimen.divider_leftmargin, R.dimen.divider_rightmargin)
                        .build());


        if (MainActivity.isNetworkAvailable(ServerActivity.this)) {
       	    new GetServerStatus().execute();
        } else {
           Toast.makeText(ServerActivity.this, "Network is unavailable", Toast.LENGTH_LONG).show();
        }

		// Debug the thread name
		//Log.d("SWTORCentral", Thread.currentThread().getName());
	}

    private class GetServerStatus extends AsyncTask<String, Void, ArrayList<ServerItem>> {

    	protected void onPreExecute() {
 			super.onPreExecute();
            serverItemsUS = new ArrayList<>();
            serverItemsEU = new ArrayList<>();
 		}

		@Override
		protected ArrayList<ServerItem> doInBackground(String... urls) {

			try {
				String URL = "http://www.swtor.com/server-status3";


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

                    //Log.d("SWTORCentral", "US Server: " + serverName);

                    serverItemsUS.add(item);
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
                    serverItemsEU.add(item);
				}
			} catch (Exception e) {
				if(e.getMessage() != null) {
				    Log.e("SWTORCentral", e.getMessage());
				}
			}

			// Debug the task thread name
			//Log.d("SWTORCentral", Thread.currentThread().getName());
			return null;
		}

		protected void onPostExecute(ArrayList<ServerItem> result) {
            //Set Adapter
            mRecycleAdapterUS = new ServerAdapter(ServerActivity.this, serverItemsUS);
            mRecycleAdapterEU = new ServerAdapter(ServerActivity.this, serverItemsEU);

            mRecyclerViewUS.setAdapter(mRecycleAdapterUS);
            mRecyclerViewEU.setAdapter(mRecycleAdapterEU);
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
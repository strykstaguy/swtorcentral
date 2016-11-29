package com.stryksta.swtorcentral.ui.activities;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.ReaderAdapter;
import com.stryksta.swtorcentral.models.RssItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.RssReader;
import com.stryksta.swtorcentral.util.database.RssDatabaseHandler;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;


public class ReaderActivity extends Fragment {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private ReaderAdapter mRecycleAdapter;
    private RssDatabaseHandler db;
    ArrayList<RssItem> rssItems;

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

        vw_layout = inflater.inflate(R.layout.reader_main, container, false);

		getActivity().setTitle("News");

        //Set RecyclerView
        mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.readerList);

        if (mRecyclerView != null) {
			mLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
			/*
			mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
				@Override
				public int getSpanSize(int position) {
					return (position % 3 == 0 ? 2 : 1);
				}
			});
			*/
            mRecyclerView.setLayoutManager(mLayoutManager);

        	GetRSSDataTask task = new GetRSSDataTask();

    		// Start download RSS task
    	    task.execute("http://www.swtor.com/feed/news/all");
        }
        
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
                        //Log.d("SWTORCentral", item.getCategory());
					}
					
				}
					rssItems =  db.getNews();
					
					db.close();
					//End Add to Database

                    //Set Adapter
                    mRecycleAdapter = new ReaderAdapter(getActivity(), rssItems);

                    mRecyclerView.setAdapter(mRecycleAdapter);

					if (MainActivity.isNetworkAvailable(getActivity())) {

                        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
                        {
                            public void onItemClick(View view, int position)
                            {
                                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(rssItems.get(position).getLink()));
                                startActivity(intent);
                            }

                            public void onItemLongClick(View view, int position)
                            {

                            }
                        }));

					} else {
						Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
		            
					}
		    		
					
            
		}
		
	}

	@Override
	public void onResume() {
		super.onResume();
		//getActivity().getActionBar().setTitle("News");
	}
}
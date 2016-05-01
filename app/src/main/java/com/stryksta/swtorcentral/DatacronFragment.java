package com.stryksta.swtorcentral;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stryksta.swtorcentral.adapters.DatacronAdapter;
import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.util.database.DatacronDatabase;

public class DatacronFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DatacronAdapter mRecycleAdapter;
	private ArrayList<DatacronItem> datacronItems;
	private DatacronDatabase datacronDB;

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
		
        vw_layout = inflater.inflate(R.layout.datacron_main, container, false);

        getActivity().setTitle("Datacrons");

        //Set RecyclerView
        mRecyclerView = (RecyclerView) vw_layout.findViewById(R.id.datacronList);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        datacronDB = new DatacronDatabase(getActivity());
        datacronItems = datacronDB.getDatacrons();
        datacronDB.close();

        //Set Adapter
        mRecycleAdapter = new DatacronAdapter(datacronItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
		
        return vw_layout;
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
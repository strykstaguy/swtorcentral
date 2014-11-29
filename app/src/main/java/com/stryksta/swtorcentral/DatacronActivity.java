package com.stryksta.swtorcentral;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.util.database.DatacronDatabase;

public class DatacronActivity extends ListFragment {
	private ArrayList<DatacronItem> datacrons;
	private DatacronDatabase db;
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
        
        db = new DatacronDatabase(getActivity());
		datacrons = db.getDatacrons();
		db.close();
		
        ListView itcItems = (ListView) vw_layout.findViewById(android.R.id.list);
        
        DatacronAdapter adapter = new DatacronAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, datacrons);

		itcItems.setAdapter(adapter);
		
        return vw_layout;
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.DatacronItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;


public class LoreAdapter extends ArrayAdapter<DatacronItem> {
	private final ArrayList<DatacronItem> results;
	Context mContext;
	public LoreAdapter(Context mContext, int resource, int textViewResourceId, final ArrayList<DatacronItem> results) {

		super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
	}
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		ViewHolder holder;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.planet_row, parent, false);
			holder = new ViewHolder();
			holder.chkViewDatacron = (CheckBox) v.findViewById(R.id.chkDatacron);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		DatacronItem testItem = results.get(position);
		
		if (testItem != null) {
			holder.chkViewDatacron.setText(testItem.getReward());
		}
		
		return v;
	}
	
	private static class ViewHolder {
		public CheckBox chkViewDatacron;
	}
}
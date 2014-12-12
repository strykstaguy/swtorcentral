package com.stryksta.swtorcentral.adapters;

import java.util.ArrayList;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.DatacronItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class PlanetAdapter extends ArrayAdapter<DatacronItem> {
	private final ArrayList<DatacronItem> results;
	Context mContext;
	public PlanetAdapter(Context mContext, int resource, int textViewResourceId, final ArrayList<DatacronItem> results) {

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
			v = inflater.inflate(R.layout.planet_tab2_row, parent, false);
			holder = new ViewHolder();
			holder.txtDatacron = (TextView) v.findViewById(R.id.txtDatacron);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}

		DatacronItem testItem = results.get(position);

		if (testItem != null) {
			holder.txtDatacron.setText(testItem.getReward());
		}

		return v;
	}

	private static class ViewHolder {
		public TextView txtDatacron;
	}
}
package com.stryksta.swtorcentral.adapters;

import java.util.ArrayList;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.util.PinnedSectionListView.PinnedSectionListAdapter;
import com.stryksta.swtorcentral.data.DatacronItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DatacronAdapter extends ArrayAdapter<DatacronItem> implements PinnedSectionListAdapter {

	public DatacronAdapter(Context context, int resource, int textViewResourceId, ArrayList<DatacronItem> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		ViewHolder holder;

		if (v == null) {
			
			int layout = R.layout.datacron_row;
			if (getItem(position).type == DatacronItem.SECTION)
				layout = R.layout.datacron_header;
			
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(layout, parent, false);
			holder = new ViewHolder();
			holder.txtViewPlanet = (TextView) v.findViewById(R.id.txtPlanet);
			holder.textViewReward = (TextView) v.findViewById(R.id.txtReward);
			holder.textViewLocation = (TextView) v.findViewById(R.id.txtLocation);
			holder.textViewCodex = (TextView) v.findViewById(R.id.txtCodex);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		DatacronItem testItem = getItem(position);
		
		//if (testItem != null) {
			//holder.txtViewPlanet.setText(testItem.getPlanet());
			//holder.textViewReward.setText(testItem.getReward());
			//holder.textViewCoord.setText("Coordinates: " + testItem.getCoord());
		//}
		
		if (getItem(position).type == DatacronItem.ITEM){
			holder.txtViewPlanet.setVisibility(View.GONE);
			holder.textViewReward.setText(testItem.getReward());
			holder.textViewLocation.setText("Location: " + testItem.getLocation());
			
			if (testItem.getCodex() == null) {
				holder.textViewCodex.setVisibility(View.GONE);
			} else {
				holder.textViewCodex.setText(testItem.getCodex());
			}
			
		} else {
			holder.txtViewPlanet.setText(testItem.getPlanet());
			holder.textViewReward.setText(testItem.getReward());
			holder.textViewLocation.setText(testItem.getLocation());
		}
		
		return v;
	}
	
	@Override public int getViewTypeCount() {
		return 2;
	}
	
	@Override public int getItemViewType(int position) {
		return getItem(position).type;
	}

	public boolean isItemViewTypePinned(int viewType) {
		return viewType == DatacronItem.SECTION;
	}
	
	private static class ViewHolder {
		public TextView txtViewPlanet;
		public TextView textViewReward;
		public TextView textViewLocation;
		public TextView textViewCodex;
	}
}
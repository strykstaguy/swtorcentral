package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.data.LoreItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


public class LoreAdapter extends ArrayAdapter<LoreItem> {
	private final ArrayList<LoreItem> results;
	Context mContext;
	public LoreAdapter(Context mContext, int resource, int textViewResourceId, final ArrayList<LoreItem> results) {

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
			v = inflater.inflate(R.layout.lore_row, parent, false);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) v.findViewById(R.id.txtTitle);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		LoreItem testItem = results.get(position);
		
		if (testItem != null) {
			holder.txtTitle.setText(testItem.getCodex());
		}
		
		return v;
	}
	
	private static class ViewHolder {
		public TextView txtTitle;
	}
}
package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.ProgressionItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FactionAdapter extends ArrayAdapter<ProgressionItem> {

		private final Context context;
		private final ArrayList<ProgressionItem> swtorPlanets;

		public FactionAdapter(Context context, ArrayList<ProgressionItem> swtorPlanets) {
			super(context, R.layout.progression_item_single, swtorPlanets);
			
			this.context = context;
			this.swtorPlanets = swtorPlanets;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		    
			// 1. Create inflater 
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    
			// 2. Get rowView from inflater
			
			View rowView = null;
			final ProgressionItem item = swtorPlanets.get(position);
			
			if(item.isGroupHeader()){
				rowView = inflater.inflate(R.layout.progression_header_item, parent, false);
				
				TextView txtHeader = (TextView) rowView.findViewById(R.id.txtHeader);
				ImageView imgHeader = (ImageView) rowView.findViewById(R.id.imgHeader); 
				
				imgHeader.setImageResource(item.getimgPlanet());
				txtHeader.setText(item.getPlanet());

			} else if(item.isSeparator()) {
				rowView = inflater.inflate(R.layout.progression_item_sep, parent, false);
			} else {
				rowView = inflater.inflate(R.layout.progression_item_single, parent, false);
				
				// 3. Get icon,title & counter views from the rowView
				ImageView imgPlanet = (ImageView) rowView.findViewById(R.id.imgPlanet); 
				TextView txtPlanet = (TextView) rowView.findViewById(R.id.txtPlanet);
				TextView txtType = (TextView) rowView.findViewById(R.id.txtType);
				
		    	imgPlanet.setTag(position);
		    	
			    // 4. Set the text for textView 
				imgPlanet.setImageResource(item.getimgPlanet());
				txtPlanet.setText(item.getPlanet());
				txtType.setText(item.getType());
			}
		   
		    // 5. retrn rowView
		    return rowView;
		}
}
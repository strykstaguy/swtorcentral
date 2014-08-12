package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AchievementItemsAdapter extends ArrayAdapter<AchievementsItem> {

		private final Context context;
		private final ArrayList<AchievementsItem> swtorAchievements;

		public AchievementItemsAdapter(Context context, ArrayList<AchievementsItem> swtorAchievements) {
			super(context, R.layout.achievement_items_row, swtorAchievements);
			
			this.context = context;
			this.swtorAchievements = swtorAchievements;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final AchievementsItem item = swtorAchievements.get(position);
			
				rowView = inflater.inflate(R.layout.achievement_items_row, parent, false);
				
				TextView txtViewTitle = (TextView) rowView.findViewById(R.id.txtTitle);
				TextView txtViewDescription = (TextView) rowView.findViewById(R.id.txtDescription);
				TextView txtViewCount = (TextView) rowView.findViewById(R.id.txtCount);
				
				if (item != null) {
					
					txtViewTitle.setText(item.getTitle());
					txtViewDescription.setText(insertNewLine(item.getDescription()));
					txtViewCount.setText(String.valueOf(item.getPoints()));
					
				}
				
		    return rowView;
		}
		
		private String insertNewLine(String description) {
			/* "\\\t"  - tab*/
		    return description.replaceAll("~", "\\\n");
		    
		}
}
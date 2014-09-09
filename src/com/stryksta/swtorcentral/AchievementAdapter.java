package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.TextProgressBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AchievementAdapter extends ArrayAdapter<AchievementsItem> {

		private final Context context;
		private final ArrayList<AchievementsItem> swtorAchievements;
		int AdvancedPos1;
		int AdvancedPos2;
		String type;

		public AchievementAdapter(Context context, ArrayList<AchievementsItem> swtorAchievements, String type) {
			super(context, R.layout.achievement_row, swtorAchievements);
			
			this.context = context;
			this.swtorAchievements = swtorAchievements;
			this.type = type;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final AchievementsItem item = swtorAchievements.get(position);
			
			if(item.isGroupHeader()){
				rowView = inflater.inflate(R.layout.advanced_class_tab3_header, parent, false);
				TextView txtHeader = (TextView) rowView.findViewById(R.id.txtAbilityTitle);
				txtHeader.setText(item.getTitle());

			} else {
				rowView = inflater.inflate(R.layout.achievement_row, parent, false);
				
				//LinearLayout abilityLinearLayout = (LinearLayout) rowView.findViewById(R.id.abilitylinearlayout);
				
				TextView txtViewCategory1 = (TextView) rowView.findViewById(R.id.txtCategory1);
				//TextView txtViewCategory1Progress = (TextView) rowView.findViewById(R.id.txtCategory1Progress);
				TextProgressBar txtViewProgress = (TextProgressBar) rowView.findViewById(R.id.progressBarWithText);
				
				if (item != null) {
					
					if (type.equals("")) {
						txtViewCategory1.setText(item.getCategory1());
					} else if (type.equals("category1")) {
						txtViewCategory1.setText(item.getCategory1());
					} else if (type.equals("category2")) {
						txtViewCategory1.setText(item.getCategory2());
					} else if (type.equals("category3")) {
						txtViewCategory1.setText(item.getCategory3());
						
					}
					
					
					txtViewProgress.setText("0 / " + item.getCount());
				}
				
				/*abilityLinearLayout.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                Toast.makeText(context, "Ability: " + item.gettxtName() + " - ID: " + item.getabilityID(), Toast.LENGTH_SHORT).show();
		            }
		        });
		        */
			}
		    return rowView;
		}
}
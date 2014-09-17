package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AchievementsItem;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.GridViewItemLayout;
import com.stryksta.swtorcentral.util.SizeAdjustingTextView;
import com.stryksta.swtorcentral.util.TextProgressBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AchievementAdapter extends ArrayAdapter<AchievementsItem> {

		private final Context mContext;
		private final ArrayList<AchievementsItem> swtorAchievements;
		int AdvancedPos1;
		int AdvancedPos2;
		String type;

		public AchievementAdapter(Context mContext, ArrayList<AchievementsItem> swtorAchievements, String type) {
			super(mContext, R.layout.achievement_row, swtorAchievements);
			
			this.mContext = mContext;
			this.swtorAchievements = swtorAchievements;
			this.type = type;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) mContext
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final AchievementsItem item = swtorAchievements.get(position);
			
			if(item.isGroupHeader()){
				rowView = inflater.inflate(R.layout.advanced_class_tab3_header, parent, false);
				TextView txtHeader = (TextView) rowView.findViewById(R.id.txtAbilityTitle);
				txtHeader.setText(item.getTitle());

			} else {
				rowView = inflater.inflate(R.layout.achievement_row, parent, false);
				
				SizeAdjustingTextView txtViewCategory1 = (SizeAdjustingTextView) rowView.findViewById(R.id.txtCategory1);
				TextView txtViewSubCategory = (TextView) rowView.findViewById(R.id.txtSubCategory);
				
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
					
					//rowView.setMinimumHeight(300);
					txtViewProgress.setText("70%");
					txtViewSubCategory.setText("0 / " + item.getCount());
				}
				
			}
		    return rowView;
		}
		
		/**
	     * Run a pass through each item and force a measure to determine the max height for each row
	     */
	    public void measureItems(int columnWidth) {
	        // Obtain system inflater
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        // Inflate temp layout object for measuring
	        GridViewItemLayout itemView = (GridViewItemLayout)inflater.inflate(R.layout.achievement_row, null);

	        // Create measuring specs
	        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(columnWidth, MeasureSpec.EXACTLY);
	        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
	        
	            // Force measuring
	            itemView.requestLayout();
	            itemView.measure(widthMeasureSpec, heightMeasureSpec);
	    }
}
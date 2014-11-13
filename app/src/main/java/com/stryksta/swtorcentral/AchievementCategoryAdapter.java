package com.stryksta.swtorcentral;

import java.util.ArrayList;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.util.SizeAdjustingTextView;
import com.stryksta.swtorcentral.util.TextProgressBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AchievementCategoryAdapter extends ArrayAdapter<AchievementCategoryItem> {

		private final Context mContext;
		private final ArrayList<AchievementCategoryItem> achievementsCategories;
		int AdvancedPos1;
		int AdvancedPos2;
		String type;

		public AchievementCategoryAdapter(Context mContext, ArrayList<AchievementCategoryItem> achievementsCategories) {
			super(mContext, R.layout.achievement_row, achievementsCategories);
			
			this.mContext = mContext;
			this.achievementsCategories = achievementsCategories;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) mContext
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final AchievementCategoryItem item = achievementsCategories.get(position);
			
				rowView = inflater.inflate(R.layout.achievement_row, parent, false);
				
				SizeAdjustingTextView txtViewCategory1 = (SizeAdjustingTextView) rowView.findViewById(R.id.txtCategory1);
				TextView txtViewSubCategory = (TextView) rowView.findViewById(R.id.txtSubCategory);
				
				TextProgressBar txtViewProgress = (TextProgressBar) rowView.findViewById(R.id.progressBarWithText);
				
				if (item != null) {
					txtViewCategory1.setText(item.getCategory());
					txtViewSubCategory.setText(item.getCompleted() + " / " + item.getTotal());
					txtViewProgress.setMax(item.getTotal());
					txtViewProgress.setProgress(item.getCompleted());
					
					int percent = (int)((item.getCompleted() * 100.0f) / item.getTotal());
					txtViewProgress.setText(percent + "%");
				}
		    return rowView;
		}
}
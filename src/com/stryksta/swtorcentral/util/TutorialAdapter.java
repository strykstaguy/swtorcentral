package com.stryksta.swtorcentral.util;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.TutorialItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAdapter extends ArrayAdapter<TutorialItem> {

		private final Context context;
		private View commonItemView;
		private final ArrayList<TutorialItem> tutorialItems;

		public TutorialAdapter(Context context, ArrayList<TutorialItem> tutorialItems) {
			super(context, R.layout.tutorial_row, tutorialItems);
			
			this.context = context;
			this.tutorialItems = tutorialItems;
			this.commonItemView = null;
		}
		
		@Override
		public View getView(int position, View convertView, final ViewGroup parent) {
		    
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View rowView = null;
			final TutorialItem item = tutorialItems.get(position);
			
			rowView = inflater.inflate(R.layout.tutorial_row, parent, false);
			
			TextView txtViewTitle = (TextView) rowView.findViewById(R.id.txtTitle);
			TextView txtViewDescription = (TextView) rowView.findViewById(R.id.txtDescription);
			ImageView imgViewThumb = (ImageView) rowView.findViewById(R.id.imgThumbnail);
			
			if (item != null) {
				txtViewTitle.setText(item.getTitle());
				txtViewDescription.setText(item.getDescription());
				
				Picasso.with(rowView.getContext())
				.load(item.getImageURL())
				.fit()
				.into(imgViewThumb);
			}
			
			this.commonItemView = rowView;
			
		    return rowView;
		}
		
		public int getCellHeight(int row, int column) {
			
			View view = this.commonItemView;
			
			if (view != null) {
				return view.getMeasuredHeight();
			} else {
				return 0;
			}
		}
		
		 /**
	     * Run a pass through each item and force a measure to determine the max height for each row
	     */
		public void measureItems(int columnWidth) {
	        // Obtain system inflater
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        // Inflate temp layout object for measuring
	        GridViewItemLayout itemView = (GridViewItemLayout)inflater.inflate(R.layout.tutorial_row, null);
	        
	        // Create measuring specs
	        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(columnWidth, MeasureSpec.EXACTLY);
	        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

	            // Force measuring
	            itemView.requestLayout();
	            itemView.measure(widthMeasureSpec, heightMeasureSpec);
	    }
}
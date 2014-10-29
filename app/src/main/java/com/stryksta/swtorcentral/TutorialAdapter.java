package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.TutorialItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAdapter extends ArrayAdapter<TutorialItem> {

		private final Context context;
		private final ArrayList<TutorialItem> tutorialItems;

		public TutorialAdapter(Context context, ArrayList<TutorialItem> tutorialItems) {
			super(context, R.layout.tutorial_row, tutorialItems);
			
			this.context = context;
			this.tutorialItems = tutorialItems;
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
		    return rowView;
		}
}
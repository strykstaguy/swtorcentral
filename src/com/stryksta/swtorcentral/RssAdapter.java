package com.stryksta.swtorcentral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.RssItem;

import java.util.ArrayList;

public class RssAdapter extends ArrayAdapter<RssItem> {
	
	int width;
	int height;
	
	private final Activity activity;
	private final ArrayList<RssItem> results;
	public RssAdapter(final Activity a, final int textViewResourceId,
			final ArrayList<RssItem> results) {

		super(a, textViewResourceId, results);
		this.results = results;
		activity = a;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		ViewHolder holder;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.reader_row, parent, false);
			holder = new ViewHolder();
			
			//v.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			width = v.getMeasuredWidth();
			height = v.getMeasuredHeight();
			//v.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, height));
			
			//Log.d("SWTORCentral", "Height: " + height);
			
			holder.textViewName = (TextView) v.findViewById(R.id.txtTitle);
			holder.imgViewThumb = (ImageView) v.findViewById(R.id.imgThumbnail);
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		RssItem rssItem = results.get(position);
		if (rssItem != null) {
			holder.textViewName.setText(rssItem.getTitle());
			
			//Log.d("ITCRssReader", rssItem.getImage());

			holder.imgViewThumb.setImageResource(R.drawable.placeholder); 
			
			Picasso.with(v.getContext())
			.load(rssItem.getImage())
			.into(holder.imgViewThumb);
			
			holder.imgViewThumb.setTag(rssItem.getImage());
			
		}
		return v;
	}

	private static class ViewHolder {
		public TextView textViewName;
		public ImageView imgViewThumb;
	}
	
}

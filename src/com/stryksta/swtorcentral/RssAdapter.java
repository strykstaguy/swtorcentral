package com.stryksta.swtorcentral;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.RssItem;

import java.util.ArrayList;

public class RssAdapter extends ArrayAdapter<RssItem> {

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
			holder.textViewName = (TextView) v.findViewById(R.id.txtTitle);
			//holder.textViewDesc = (TextView) v.findViewById(R.id.description);
			holder.imgViewThumb = (ImageView) v.findViewById(R.id.imgThumbnail);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		RssItem rssItem = results.get(position);
		if (rssItem != null) {
			//holder.textViewName.setLayoutParams(new AbsListView.LayoutParams(Width,Height));
			holder.textViewName.setText(rssItem.getTitle());
			//holder.textViewDesc.setText(rssItem.getDescription());
			
			//Log.d("ITCRssReader", rssItem.getImage());
			//imageLoader.displayImage(rssItem.getImage(), holder.imgViewThumb);
			
			holder.imgViewThumb.setImageResource(R.drawable.placeholder); 
			
			Picasso.with(v.getContext())
			.load(rssItem.getImage())
			.into(holder.imgViewThumb);
			
			holder.imgViewThumb.setTag(rssItem.getImage());
			//holder.textViewDesc.setText(Html.fromHtml(rssItem.getDescription()));
			
		}
		return v;
	}

	private static class ViewHolder {
		public TextView textViewName;
		//public TextView textViewDesc;
		public ImageView imgViewThumb;
	}
}

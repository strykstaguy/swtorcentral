package com.stryksta.swtorcentral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.stryksta.swtorcentral.data.EventItem;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<EventItem> {

	Context mContext;
    private final ArrayList<EventItem> results;
    public EventAdapter(Context mContext, final int textViewResourceId,
                        final ArrayList<EventItem> results) {

        super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.event_row, null);
            holder = new ViewHolder();

            holder.txtViewEventName = (TextView) v.findViewById(R.id.eventTitle);
            holder.txtViewEventDescription = (TextView) v.findViewById(R.id.eventDescription);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        EventItem rowItem = results.get(position);

        	if (holder.txtViewEventName != null) {
        		holder.txtViewEventName.setText(rowItem.geteventTitle());
            }
        	
        	if (holder.txtViewEventDescription != null) {
        		holder.txtViewEventDescription.setText(rowItem.geteventDesc());
            }

        return v;
    }

    private static class ViewHolder {
        public TextView txtViewEventName;
        public TextView txtViewEventDescription;
    }
}

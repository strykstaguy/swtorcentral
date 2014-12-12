package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.EventItem;
import com.stryksta.swtorcentral.util.Utility;

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
            holder.txtViewEventStatus = (TextView) v.findViewById(R.id.eventStatus);
            holder.txtViewEventDescription = (TextView) v.findViewById(R.id.eventDescription);
            holder.txtViewEventStart = (TextView) v.findViewById(R.id.eventStart);
            holder.txtViewEventEnd = (TextView) v.findViewById(R.id.eventEnd);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        EventItem rowItem = results.get(position);

        	if (holder.txtViewEventName != null) {
        		holder.txtViewEventName.setText(rowItem.getTitle());
            }
        	
        	if (holder.txtViewEventDescription != null) {
        		holder.txtViewEventDescription.setText(Html.fromHtml(rowItem.getDescription()));
            }

            if (holder.txtViewEventStatus != null) {
                holder.txtViewEventStatus.setText(rowItem.getStatus());
            }

            if (holder.txtViewEventEnd != null) {
                if (rowItem.getEnd().equals("")) {
                    holder.txtViewEventEnd.setVisibility(View.GONE);
                } else {
                    holder.txtViewEventEnd.setText("End: " + rowItem.getEnd());
                    //String TestDate = Utility.convertDateFormat(rowItem.getEnd(), "M d, y", "dd/MM/yyyy", "Failed to convert");
                   //Toast.makeText(mContext, TestDate, Toast.LENGTH_SHORT).show();
                }
            }

            if (holder.txtViewEventStart != null) {

                if (rowItem.getStart().equals("")) {
                    holder.txtViewEventStart.setVisibility(View.GONE);
                } else {
                    holder.txtViewEventStart.setText("Start: " + rowItem.getStart());
                }
            }

        return v;
    }

    private static class ViewHolder {
        public TextView txtViewEventName;
        public TextView txtViewEventStatus;
        public TextView txtViewEventDescription;
        public TextView txtViewEventEnd;
        public TextView txtViewEventStart;
    }
}

package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.timeline.TimelineHView;

import java.util.ArrayList;

public class ProgressionAdapter extends ArrayAdapter<ProgressionItem>{
    private final ArrayList<ProgressionItem> progressionItems;
    Context mContext;
    public ProgressionAdapter(Context mContext, final ArrayList<ProgressionItem> progressionItems) {
        super(mContext, R.layout.progression_row, progressionItems);
        this.progressionItems = progressionItems;
        this.mContext = mContext;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.progression_row, parent, false);
            holder = new ViewHolder();

            holder.imgPlanet = (ImageView) v.findViewById(R.id.imgPlanet);
            holder.txtPlanet = (TextView) v.findViewById(R.id.txtPlanet);
            holder.txtLevel = (TextView) v.findViewById(R.id.txtLevel);
            holder.txtLabel = (TextView) v.findViewById(R.id.txtLabel);
            holder.timeLineView = (TimelineHView) v.findViewById(R.id.timeline);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        ProgressionItem progressionItem = progressionItems.get(position);

        if (progressionItem != null) {
            holder.imgPlanet.setImageResource(progressionItem.getimgPlanet());
            holder.txtPlanet.setText(progressionItem.getPlanet());
            holder.txtLevel.setText(progressionItem.getLevel());
            holder.txtLabel.setText(progressionItem.getLabel());
            holder.timeLineView.setTimelineType(progressionItem.getType());
        }

        return v;
    }

    private static class ViewHolder {
        public ImageView imgPlanet;
        public TextView txtPlanet;
        public TextView txtLevel;
        public TextView txtLabel;
        public TimelineHView timeLineView;


    }
}
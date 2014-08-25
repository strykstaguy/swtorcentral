package com.stryksta.swtorcentral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.util.TimelineView;
import com.stryksta.swtorcentral.util.VerticalTextView;
import com.stryksta.swtorcentral.data.TestItem;

import java.util.List;

public class ProgressionAdapter extends ArrayAdapter<TestItem> {
    private final LayoutInflater mInflater;

    public ProgressionAdapter(Context context, List<TestItem> objects) {
        super(context, 0, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.progression_row, null);
        
        ImageView imgPlanet = (ImageView) v.findViewById(R.id.imgPlanet);
        TextView txtPlanet = (TextView) v.findViewById(R.id.txtPlanet);
        TextView txtLevel = (TextView) v.findViewById(R.id.txtLevel);
        VerticalTextView txtTimeLineLabel = (VerticalTextView) v.findViewById(R.id.txtTimeLineLabel);
        TimelineView timeline = (TimelineView) v.findViewById(R.id.timeline);

        TestItem event = getItem(position);
        
        imgPlanet.setImageResource(event.getimgPlanet());
        txtPlanet.setText(event.getPlanet());
        txtLevel.setText(event.getLevel());
        txtTimeLineLabel.setText(event.getLabel());
        timeline.setTimelineType(event.getType());
        
        return v;
    }
}

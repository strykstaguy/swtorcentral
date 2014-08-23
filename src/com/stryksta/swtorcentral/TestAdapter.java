package com.stryksta.swtorcentral;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.stryksta.swtorcentral.util.TimelineType;
import com.stryksta.swtorcentral.util.TimelineView;
import com.stryksta.swtorcentral.data.TestItem;

import java.util.List;

public class TestAdapter extends ArrayAdapter<TestItem> {
    private final LayoutInflater mInflater;

    public TestAdapter(Context context, List<TestItem> objects) {
        super(context, 0, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.test_row, null);
        
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.layoutSinglePlanet);
        ImageView imgPlanet = (ImageView) v.findViewById(R.id.imgPlanet);
        TextView txtPlanet = (TextView) v.findViewById(R.id.txtPlanet);
        TextView txtLevel = (TextView) v.findViewById(R.id.txtLevel);
        TextView txtTimeLineLabel = (TextView) v.findViewById(R.id.txtTimeLineLabel);
        TimelineView timeline = (TimelineView) v.findViewById(R.id.timeline);

        TestItem event = getItem(position);
        
        imgPlanet.setImageResource(event.getimgPlanet());
        txtPlanet.setText(event.getPlanet());
        txtLevel.setText(event.getLevel());
        txtTimeLineLabel.setText(event.getLabel());
        timeline.setTimelineType(event.getType());
        
        if (event.getType().equals(TimelineType.START)) {
        	setViewBackgroundWithoutResettingPadding(linearLayout, R.drawable.card_background_start);
        	
        	LayoutParams params = new LayoutParams(
        	        LayoutParams.WRAP_CONTENT,      
        	        LayoutParams.WRAP_CONTENT
        	);
        	params.setMargins(10, 0, 0, 0);
        	linearLayout.setLayoutParams(params);
        }
        
        if (event.getType().equals(TimelineType.END)) {
        	setViewBackgroundWithoutResettingPadding(linearLayout, R.drawable.card_background_end);
        	
        	LayoutParams params = new LayoutParams(
        	        LayoutParams.WRAP_CONTENT,      
        	        LayoutParams.WRAP_CONTENT
        	);
        	params.setMargins(0, 0, 10, 0);
        	linearLayout.setLayoutParams(params);
        }
        
        if (event.getType().equals(TimelineType.SINGLE)) {
        	setViewBackgroundWithoutResettingPadding(linearLayout, R.drawable.card_background);
        	
        	LayoutParams params = new LayoutParams(
        	        LayoutParams.WRAP_CONTENT,      
        	        LayoutParams.WRAP_CONTENT
        	);
        	params.setMargins(10, 0, 10, 0);
        	linearLayout.setLayoutParams(params);
        }
        
        return v;
    }
    
    public static void setViewBackgroundWithoutResettingPadding(final View v, final int backgroundResId) {
        final int paddingBottom = v.getPaddingBottom(), paddingLeft = v.getPaddingLeft();
        final int paddingRight = v.getPaddingRight(), paddingTop = v.getPaddingTop();
        v.setBackgroundResource(backgroundResId);
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}

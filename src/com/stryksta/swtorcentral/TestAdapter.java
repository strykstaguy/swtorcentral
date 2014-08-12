package com.stryksta.swtorcentral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stryksta.swtorcentral.util.TimelineView;
import com.stryksta.swtorcentral.data.TestItem;

import java.util.List;

/**
 * Created by Bernat on 06/04/2014.
 */
public class TestAdapter extends ArrayAdapter<TestItem> {
    private final LayoutInflater mInflater;

    public TestAdapter(Context context, List<TestItem> objects) {
        super(context, 0, objects);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.test_row, null);

        TextView text = (TextView) v.findViewById(R.id.textView);
        TimelineView timeline = (TimelineView) v.findViewById(R.id.timeline);

        TestItem evento = getItem(position);

        text.setText(evento.getName());
        timeline.setTimelineType(evento.getTipo());

        return v;
    }
}

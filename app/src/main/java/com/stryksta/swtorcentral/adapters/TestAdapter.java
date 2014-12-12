package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.LoreItem;

import java.util.ArrayList;

public class TestAdapter extends ArrayAdapter<LoreItem> {

    private final Context context;
    private final ArrayList<LoreItem> loreItems;
    public TestAdapter(Context context, ArrayList<LoreItem> loreItems) {
        super(context, R.layout.test_row, loreItems);

        this.context = context;
        this.loreItems = loreItems;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = null;
        final LoreItem item = loreItems.get(position);

        if(item.isGroupHeader()){
            rowView = inflater.inflate(R.layout.test_header, parent, false);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
            TextView txtSubtitle = (TextView) rowView.findViewById(R.id.txtSubtitle);


            txtTitle.setText(item.getFaction());
            txtSubtitle.setText(item.getPlanet());

        } else {
            rowView = inflater.inflate(R.layout.test_row, parent, false);
            View txtSep = (View) rowView.findViewById(R.id.txtSep);
            LinearLayout layoutRow = (LinearLayout) rowView.findViewById(R.id.layoutRow);

            if(position == 1) {
                setViewBackgroundWithoutResettingPadding(layoutRow, R.drawable.background_top);
            } else if (position == getCount()-1) {
                setViewBackgroundWithoutResettingPadding(layoutRow, R.drawable.background_bottom);
                txtSep.setVisibility(View.GONE);
            }

            TextView txtCodex = (TextView) rowView.findViewById(R.id.txtCodex);
            txtCodex.setText(item.getCodex());
        }

        return rowView;
    }

    public static void setViewBackgroundWithoutResettingPadding(final View v, final int backgroundResId) {
        final int paddingBottom = v.getPaddingBottom(), paddingLeft = v.getPaddingLeft();
        final int paddingRight = v.getPaddingRight(), paddingTop = v.getPaddingTop();
        v.setBackgroundResource(backgroundResId);
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
package com.stryksta.swtorcentral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.data.TestItem;
import com.stryksta.swtorcentral.util.PinnedSectionListView;
import java.util.ArrayList;

public class TestAdapter extends ArrayAdapter<TestItem> implements PinnedSectionListView.PinnedSectionListAdapter {
    private final ArrayList<TestItem> progressionItems;
    Context mContext;
    public TestAdapter(Context mContext, final ArrayList<TestItem> progressionItems) {
        super(mContext, R.layout.test_row, progressionItems);
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

            v = inflater.inflate(R.layout.test_row, parent, false);
            holder = new ViewHolder();

            holder.imgPlanet = (ImageView) v.findViewById(R.id.imgPlanet);
            holder.txtPlanet = (TextView) v.findViewById(R.id.txtPlanet);
            holder.txtLevel = (TextView) v.findViewById(R.id.txtLevel);
            holder.txtLabel = (TextView) v.findViewById(R.id.txtLabel);

            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }

        TestItem progressionItem = progressionItems.get(position);

        if (progressionItem != null) {

            holder.txtLabel.setText(progressionItem.getLabel());
            holder.imgPlanet.setImageResource(progressionItem.getImgPlanet());
            holder.txtPlanet.setText(progressionItem.getPlanet());
            holder.txtLevel.setText(progressionItem.getLevel());

            if (progressionItems.get(position).type == TestItem.ITEM) {
                holder.txtLabel.setVisibility(View.GONE);
            }
        }

        return v;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }

    public boolean isItemViewTypePinned(int viewType) {
        return viewType == TestItem.SECTION;
    }

    private static class ViewHolder {
        public ImageView imgPlanet;
        public TextView txtPlanet;
        public TextView txtLevel;
        public TextView txtLabel;
    }
}
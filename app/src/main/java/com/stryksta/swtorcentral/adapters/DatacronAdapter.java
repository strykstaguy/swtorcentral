package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.util.ExpandableTextView;

import java.util.ArrayList;

public class DatacronAdapter extends RecyclerView.Adapter<DatacronAdapter.ViewHolder>{

    private ArrayList<DatacronItem> datacronItems;

    public DatacronAdapter(ArrayList<DatacronItem> datacronItems) {
        super();
        this.datacronItems = datacronItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.datacron_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        DatacronItem datacronRow = datacronItems.get(position);
        viewHolder.txtReward.setText(datacronRow.getReward());
        viewHolder.txtLocation.setText("Location: " + datacronRow.getCodexLocation());

       // Log.d("Location: ", datacronRow.getCodexLocation());

        viewHolder.txtCodex.setText(datacronRow.getCodex());
        viewHolder.txtDesc.setText(datacronRow.getDescription());
    }

    @Override
    public int getItemCount() {
        return (null != datacronItems ? datacronItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtReward;
        public TextView txtLocation;
        public TextView txtCodex;
        public ExpandableTextView txtDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            txtReward = (TextView) itemView.findViewById(R.id.txtReward);
            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
            txtCodex = (TextView) itemView.findViewById(R.id.txtCodex);
            txtDesc = (ExpandableTextView) itemView.findViewById(R.id.txtDesc);

        }
    }
}
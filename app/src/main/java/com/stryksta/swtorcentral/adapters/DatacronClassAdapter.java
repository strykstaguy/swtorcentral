package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.data.DatacronItem;

import java.util.ArrayList;

public class DatacronClassAdapter extends RecyclerView.Adapter<DatacronClassAdapter.ViewHolder>{

    private ArrayList<DatacronItem> companionItems;

    public DatacronClassAdapter(ArrayList<DatacronItem> companionItems) {
        super();
        this.companionItems = companionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.datacron_small_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        DatacronItem datacronRow = companionItems.get(position);
        viewHolder.dtcName.setText(datacronRow.getReward());
    }

    @Override
    public int getItemCount() {
        return (null != companionItems ? companionItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView dtcName;

        public ViewHolder(View itemView) {
            super(itemView);
            dtcName = (TextView) itemView.findViewById(R.id.dtcName);
        }
    }
}
package com.stryksta.swtorcentral.ui.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.models.DatacronItem;
import com.stryksta.swtorcentral.models.FilterItem;
import com.stryksta.swtorcentral.ui.activities.PlanetActivity;
import com.stryksta.swtorcentral.ui.views.TextViewLabel;
import com.stryksta.swtorcentral.ui.views.chipcloud.Chip;

import java.util.ArrayList;
import java.util.logging.Filter;

public class CodexFilterAdapter extends RecyclerView.Adapter<CodexFilterAdapter.ViewHolder>{

    private ArrayList<FilterItem> cdxItems;
    private OnFilterDoneListener onFilterDoneListener;

    public CodexFilterAdapter(ArrayList<FilterItem> cdxItems, OnFilterDoneListener onFilterDoneListener) {
        super();
        this.cdxItems = cdxItems;
        this.onFilterDoneListener = onFilterDoneListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.filter_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        FilterItem cdxRow = cdxItems.get(position);
        viewHolder.txtTitle.setText(cdxRow.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != cdxItems ? cdxItems.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public Chip txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (Chip) itemView.findViewById(R.id.txtTitle);
            txtTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext(),  String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
            if (v.getId() == txtTitle.getId()) {

                if (txtTitle.getSelected()) {
                    txtTitle.setSelected(false);
                } else {
                    txtTitle.setSelected(true);
                }
            }


            onFilterDone(getAdapterPosition(), cdxItems.get(getAdapterPosition()).getTitle());
        }
    }

    private void onFilterDone(int position, String title) {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(position, title, "");
        }
    }
}
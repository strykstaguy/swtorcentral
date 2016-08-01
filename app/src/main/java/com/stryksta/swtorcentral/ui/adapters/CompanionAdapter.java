package com.stryksta.swtorcentral.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CompanionItem;

import java.util.ArrayList;

public class CompanionAdapter extends RecyclerView.Adapter<CompanionAdapter.ViewHolder>{

    private ArrayList<CompanionItem> companionItem;

    public CompanionAdapter(ArrayList<CompanionItem> companionItem) {
        super();
        this.companionItem = companionItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.companion_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        CompanionItem companionRow = companionItem.get(position);
        viewHolder.comName.setText(companionRow.getName());
        viewHolder.comTitle.setText(companionRow.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != companionItem ? companionItem.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView comName;
        public TextView comTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            comName = (TextView) itemView.findViewById(R.id.comName);
            comTitle = (TextView) itemView.findViewById(R.id.comTitle);
        }
    }
}
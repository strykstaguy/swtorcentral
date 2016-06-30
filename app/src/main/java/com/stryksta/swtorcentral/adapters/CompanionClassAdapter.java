package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.CompanionItem;

import java.util.ArrayList;

public class CompanionClassAdapter extends RecyclerView.Adapter<CompanionClassAdapter.ViewHolder>{

    private ArrayList<CompanionItem> companionItems;

    public CompanionClassAdapter(ArrayList<CompanionItem> companionItems) {
        super();
        this.companionItems = companionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.companion_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        CompanionItem companionRow = companionItems.get(position);
        viewHolder.comName.setText(companionRow.getName());
    }

    @Override
    public int getItemCount() {
        return (null != companionItems ? companionItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView comName;

        public ViewHolder(View itemView) {
            super(itemView);
            comName = (TextView) itemView.findViewById(R.id.comName);
        }
    }
}
package com.stryksta.swtorcentral.ui.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CompanionItem;

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
        viewHolder.comDesc.setText(companionRow.getDescription());

        //viewHolder.comImage.setBackground(ContextCompat.getDrawable(viewHolder.comImage.getContext(), R.drawable.round));
    }

    @Override
    public int getItemCount() {
        return (null != companionItems ? companionItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView comName;
        public TextView comDesc;
        public ImageButton comImage;

        public ViewHolder(View itemView) {
            super(itemView);
            comName = (TextView) itemView.findViewById(R.id.comName);
            comDesc = (TextView) itemView.findViewById(R.id.comDesc);
            comImage = (ImageButton) itemView.findViewById(R.id.imgClass);
        }
    }
}
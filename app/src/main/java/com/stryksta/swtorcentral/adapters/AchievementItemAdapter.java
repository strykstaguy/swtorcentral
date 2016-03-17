package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AchievementsItem;

import java.util.ArrayList;

public class AchievementItemAdapter extends RecyclerView.Adapter<AchievementItemAdapter.ViewHolder>{

    private ArrayList<AchievementsItem> achievementsItems;

    public AchievementItemAdapter(ArrayList<AchievementsItem> achievementsItems) {
        super();
        this.achievementsItems = achievementsItems;
    }

    public void updateItems(ArrayList<AchievementsItem> achievementsUpdated) {
        this.achievementsItems = achievementsUpdated;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.achievement_items_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AchievementsItem achItem = achievementsItems.get(position);
        if (achItem != null) {
            viewHolder.achTitle.setText(achItem.getTitle());
            viewHolder.achDescription.setText(insertNewLine(achItem.getDescription()));
            viewHolder.achRewardPoints.setText(String.valueOf(achItem.getRewardPoints()));

            if (achItem.getRewardFleetRequisition() == null && achItem.getRewardTitle().equals("")) {
                viewHolder.txtRewardsSep.setVisibility(View.GONE);
                viewHolder.achRewardTitle.setVisibility(View.GONE);
                viewHolder.achRewardFleetRequisition.setVisibility(View.GONE);
            } else {
                viewHolder.achRewardTitle.setText("Title: " + achItem.getRewardTitle());
                viewHolder.achRewardFleetRequisition.setText("Fleet Requisition: " + achItem.getRewardFleetRequisition());
            }

            if (achItem.getCompleted() == 1) {
                viewHolder.txtStatus.setText(Html.fromHtml("Status: <b>Complete</b>"));
            } else {
                viewHolder.txtStatus.setText(Html.fromHtml("Status: <b>Incomplete</b>"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != achievementsItems ? achievementsItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView achTitle;
        public TextView achDescription;
        public TextView txtRewardsSep;
        public TextView achRewardTitle;
        public TextView achRewardFleetRequisition;
        public TextView txtStatus;
        public TextView achRewardPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            achTitle = (TextView) itemView.findViewById(R.id.achTitle);
            achDescription = (TextView) itemView.findViewById(R.id.achDescription);
            txtRewardsSep = (TextView) itemView.findViewById(R.id.txtRewardsSep);
            achRewardTitle = (TextView) itemView.findViewById(R.id.achRewardTitle);
            achRewardFleetRequisition = (TextView) itemView.findViewById(R.id.achRewardFleetRequisition);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            achRewardPoints = (TextView) itemView.findViewById(R.id.achRewardPoints);
        }
    }

    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n" + "\\\t");

    }
}
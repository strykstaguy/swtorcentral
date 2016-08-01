package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.AchievementsItem;
import com.stryksta.swtorcentral.util.CircleCheckBox;

import java.util.ArrayList;

public class AchievementItemAdapter extends RecyclerView.Adapter<AchievementItemAdapter.ViewHolder>{

    private ArrayList<AchievementsItem> achievementsItems;
    Context mContext;

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
        mContext = viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AchievementsItem achItem = achievementsItems.get(position);
        if (achItem != null) {
            viewHolder.achTitle.setText(achItem.getTitle());
            viewHolder.achDescription.setText(insertNewLine(achItem.getDescription()));
            viewHolder.achRewardPoints.setText(String.valueOf(achItem.getRewardPoints()));

            if (achItem.getRewardFleetRequisition() == null) {
                viewHolder.achRewardFleetRequisition.setVisibility(View.GONE);
            } else {
                viewHolder.achRewardFleetRequisition.setText("Fleet Requisition: " + achItem.getRewardFleetRequisition());
            }

            if (achItem.getRewardTitle() == null) {
                viewHolder.achRewardFleetRequisition.setVisibility(View.GONE);
            } else {
                viewHolder.achRewardTitle.setText("Title: " + achItem.getRewardTitle());
            }

            if (achItem.getCompleted() == 1) {
                viewHolder.txtStatus.setText(Html.fromHtml("Status: <b>Complete</b>"));
                viewHolder.achRewardPoints.setChecked(true);

                viewHolder.achRewardPoints.setBackgroundColor(ContextCompat.getColor(mContext, R.color.completed_card));
                viewHolder.achCard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.completed_card));
                viewHolder.achTitle.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achDescription.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.txtRewardsSep.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achRewardTitle.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achRewardFleetRequisition.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.txtStatus.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
            } else {
                viewHolder.txtStatus.setText(Html.fromHtml("Status: <b>Incomplete</b>"));
                viewHolder.achRewardPoints.setChecked(false);

                viewHolder.achCard.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                viewHolder.achTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                viewHolder.achDescription.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                viewHolder.txtRewardsSep.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                viewHolder.achRewardTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                viewHolder.achRewardFleetRequisition.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                viewHolder.txtStatus.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != achievementsItems ? achievementsItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView achCard;
        public TextView achTitle;
        public TextView achDescription;
        public TextView txtRewardsSep;
        public TextView achRewardTitle;
        public TextView achRewardFleetRequisition;
        public TextView txtStatus;
        public CircleCheckBox achRewardPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            achCard = (CardView) itemView.findViewById(R.id.cardview);
            achTitle = (TextView) itemView.findViewById(R.id.achTitle);
            achDescription = (TextView) itemView.findViewById(R.id.achDescription);
            txtRewardsSep = (TextView) itemView.findViewById(R.id.txtRewardsSep);
            achRewardTitle = (TextView) itemView.findViewById(R.id.achRewardTitle);
            achRewardFleetRequisition = (TextView) itemView.findViewById(R.id.achRewardFleetRequisition);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            achRewardPoints = (CircleCheckBox) itemView.findViewById(R.id.achRewardPoints);
        }
    }

    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n" + "\\\t");

    }
}
package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.AchievementsItem;
import com.stryksta.swtorcentral.util.AchievementToggle;
import com.stryksta.swtorcentral.util.CircleCheckBox;
import com.stryksta.swtorcentral.util.SmoothCheckBox;

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
            viewHolder.achRewardPoints.setTitleText(String.valueOf(achItem.getRewardPoints()));

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

                viewHolder.achRewardPoints.setChecked(true);

            float achCardRadius = viewHolder.achCard.getRadius();

            if (achItem.getCompleted() == 1) {
                viewHolder.achRewardPoints.setBackgroundColor(ContextCompat.getColor(mContext, R.color.completed_card));
                viewHolder.achCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.completed_card));
                viewHolder.achCard.setRadius(achCardRadius);
                viewHolder.achTitle.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achDescription.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.txtRewardsSep.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achRewardTitle.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
                viewHolder.achRewardFleetRequisition.setTextColor(ContextCompat.getColor(mContext, R.color.completed_text));
            } else {
                viewHolder.achRewardPoints.setChecked(false);

                viewHolder.achRewardPoints.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                viewHolder.achCard.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                viewHolder.achCard.setRadius(achCardRadius);
                viewHolder.achTitle.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
                viewHolder.achDescription.setTextColor(ContextCompat.getColor(mContext, R.color.subtext_dark));
                viewHolder.txtRewardsSep.setTextColor(ContextCompat.getColor(mContext, R.color.subtext_dark));
                viewHolder.achRewardTitle.setTextColor(ContextCompat.getColor(mContext, R.color.subtext_dark));
                viewHolder.achRewardFleetRequisition.setTextColor(ContextCompat.getColor(mContext, R.color.subtext_dark));
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
        public AchievementToggle achRewardPoints;

        public ViewHolder(View itemView) {
            super(itemView);
            achCard = (CardView) itemView.findViewById(R.id.cardview);
            achTitle = (TextView) itemView.findViewById(R.id.achTitle);
            achDescription = (TextView) itemView.findViewById(R.id.achDescription);
            txtRewardsSep = (TextView) itemView.findViewById(R.id.txtRewardsSep);
            achRewardTitle = (TextView) itemView.findViewById(R.id.achRewardTitle);
            achRewardFleetRequisition = (TextView) itemView.findViewById(R.id.achRewardFleetRequisition);
            achRewardPoints = (AchievementToggle) itemView.findViewById(R.id.achRewardPoints);
        }
    }

    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n" + "\\\t");

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
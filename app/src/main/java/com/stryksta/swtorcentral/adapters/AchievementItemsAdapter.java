package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AchievementsItem;
import java.util.ArrayList;

public class AchievementItemsAdapter extends ArrayAdapter<AchievementsItem> {

	private final Context context;
    private final ArrayList<AchievementsItem> swtorAchievements;

    public AchievementItemsAdapter(Context context, ArrayList<AchievementsItem> swtorAchievements) {
        super(context, R.layout.achievement_items_row, swtorAchievements);
            
        this.context = context;
        this.swtorAchievements = swtorAchievements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.achievement_items_row, null);
            holder = new ViewHolder();
            
            holder.achTitle = (TextView) v.findViewById(R.id.achTitle);
            holder.achDescription = (TextView) v.findViewById(R.id.achDescription);
            holder.txtRewardsSep = (TextView) v.findViewById(R.id.txtRewardsSep);
            holder.achRewardTitle = (TextView) v.findViewById(R.id.achRewardTitle);
            holder.achRewardFleetRequisition = (TextView) v.findViewById(R.id.achRewardFleetRequisition);
            holder.txtStatus = (TextView) v.findViewById(R.id.txtStatus);
            holder.achRewardPoints = (TextView) v.findViewById(R.id.achRewardPoints);
            
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        
        AchievementsItem achItem = swtorAchievements.get(position);
        
        if (achItem != null) {
        	holder.achTitle.setText(achItem.getTitle());
        	holder.achDescription.setText(insertNewLine(achItem.getDescription()));
        	holder.achRewardPoints.setText(String.valueOf(achItem.getRewardPoints()));
                    
            if (achItem.getRewardFleetRequisition() == null && achItem.getRewardTitle().equals("")) {
            	holder.txtRewardsSep.setVisibility(View.GONE);
                holder.achRewardTitle.setVisibility(View.GONE);
                holder.achRewardFleetRequisition.setVisibility(View.GONE);
            } else {
                holder.achRewardTitle.setText("Title: " + achItem.getRewardTitle());
                holder.achRewardFleetRequisition.setText("Fleet Requisition: " + achItem.getRewardFleetRequisition());
            }
            
            if (achItem.getCompleted() == 1) {
            	holder.txtStatus.setText(Html.fromHtml("Status: <b>Complete</b>"));
            } else {
            	holder.txtStatus.setText(Html.fromHtml("Status: <b>Incomplete</b>"));
            }
        }
        
        
        return v;
    }

    public static class ViewHolder {
        public TextView achTitle;
        public TextView achDescription;
        public TextView txtRewardsSep;
        public TextView achRewardTitle;
        public TextView achRewardFleetRequisition;
        public TextView txtStatus;
        public TextView achRewardPoints;
    }
    
    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n" + "\\\t");
            
    }
}

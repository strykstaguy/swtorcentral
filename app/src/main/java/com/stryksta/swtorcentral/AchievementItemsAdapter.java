package com.stryksta.swtorcentral;

import android.content.Context;
import android.graphics.Paint;
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
            
            holder.txtViewTitle = (TextView) v.findViewById(R.id.txtTitle);
            holder.txtViewDescription = (TextView) v.findViewById(R.id.txtDescription);
            holder.txtViewCount = (TextView) v.findViewById(R.id.txtCount);
            holder.txtViewReward = (TextView) v.findViewById(R.id.txtRewards);
            holder.txtViewStatus = (TextView) v.findViewById(R.id.txtStatus);
            holder.txtViewCharacter = (TextView) v.findViewById(R.id.txtCharacter);
            
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        
        AchievementsItem rowItem = swtorAchievements.get(position);
        
        if (rowItem != null) {
        	holder.txtViewTitle.setText(rowItem.getTitle());
        	holder.txtViewDescription.setText(insertNewLine(rowItem.getDescription()));
        	holder.txtViewCount.setText(String.valueOf(rowItem.getPoints()));
                    
            if (rowItem.getRewards().equals("")) {
            	holder.txtViewReward.setVisibility(View.GONE);
            } else {
            	holder.txtViewReward.setText(insertNewLine(String.valueOf(rowItem.getRewards())));
            }
            
            if (rowItem.getCompleted() == 1) {
            	holder.txtViewStatus.setText(Html.fromHtml("Status: <b>Complete</b>"));
            	//holder.txtViewTitle.setPaintFlags(holder.txtViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            	//holder.txtViewDescription.setPaintFlags(holder.txtViewDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            	//holder.txtViewCount.setPaintFlags(holder.txtViewCount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            	//v.setBackgroundResource(R.drawable.card_selected_background);
            } else {
            	holder.txtViewStatus.setText(Html.fromHtml("Status: <b>Incomplete</b>"));
            	//v.setBackgroundResource(R.drawable.card_background);
            }
            
            //rowItem.getCharacter();
            
            
            if (rowItem.getPlayer() != null) {
            	holder.txtViewCharacter.setText(Html.fromHtml("Player: <b>"+ rowItem.getPlayer() + "</b>"));
            } else {
            	//holder.txtViewCharacter.setVisibility(View.GONE);
            	holder.txtViewCharacter.setText("");
            }
            
        }
        
        
        return v;
    }

    public static class ViewHolder {
        public TextView txtViewTitle;
        public TextView txtViewDescription;
        public TextView txtViewCount;
        public TextView txtViewReward;
        public TextView txtViewStatus;
        public TextView txtViewCharacter;
    }
    
    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n" + "\\\t");
            
    }
}
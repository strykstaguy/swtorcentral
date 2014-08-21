package com.stryksta.swtorcentral;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
            	//v.setBackgroundResource(R.drawable.card_selected_background);
            } else {
            	holder.txtViewStatus.setText(Html.fromHtml("Status: <b>Incomplete</b>"));
            	//v.setBackgroundResource(R.drawable.card_background);
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
    }
    /*
    public void setItemCheckedState(int position, boolean checked) {
        PhoneListView item = data[position];
        
        item.setItemToggled(checked);
            
        //if checked and not yet in the list, put it in the list
        if (checked)
            if (!checkedItem.contains(item))
            {
                checkedItem.add("" + item.getId());
            }
        } else 
        {
            //remove from checked list
            checkedItem.remove("" + item.getId());
        }
    }
    
    public boolean isItemChecked(int position) {
        return data[position].isItemToggled();
    }
    */
    
    
    
    private String insertNewLine(String description) {
        /* "\\\t"  - tab*/
        return description.replaceAll("~", "\\\n");
            
    }
}

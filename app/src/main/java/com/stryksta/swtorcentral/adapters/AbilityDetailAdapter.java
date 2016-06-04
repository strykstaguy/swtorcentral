package com.stryksta.swtorcentral.adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.Spanny;

import java.util.ArrayList;

public class AbilityDetailAdapter extends RecyclerView.Adapter<AbilityDetailAdapter.ViewHolder>{

    private ArrayList<AbilitiesItem> abilitiesItems;

    public AbilityDetailAdapter(ArrayList<AbilitiesItem> abilitiesItems) {
        super();
        this.abilitiesItems = abilitiesItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.ability_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AbilitiesItem abilitiesRow = abilitiesItems.get(position);
        viewHolder.ablName.setText(abilitiesRow.getAbilityName());

        Spanny levelSpanny = new Spanny("Level: ").append(String.valueOf(abilitiesRow.getLevelAquired()), new StyleSpan(Typeface.BOLD));
        viewHolder.ablLevel.setText(levelSpanny);
    }

    @Override
    public int getItemCount() {
        return (null != abilitiesItems ? abilitiesItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ablName;
        public TextView ablLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ablName = (TextView) itemView.findViewById(R.id.ablName);
            ablLevel = (TextView) itemView.findViewById(R.id.ablLevel);
        }
    }
}
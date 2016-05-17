package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private ArrayList<AbilitiesItem> abilitiesItems;

    public RecycleAdapter(ArrayList<AbilitiesItem> abilitiesItems) {
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
        AbilitiesItem abilityRow = abilitiesItems.get(position);
        viewHolder.ablName.setText(abilityRow.getAbilityName());
    }

    @Override
    public int getItemCount() {
        return (null != abilitiesItems ? abilitiesItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ablName;
        public TextView ablLevel;
        public TextView ablCastingActivation;
        public TextView ablResource;
        public TextView ablChanneled;
        public TextView ablCooldown;
        public TextView ablRange;
        public TextView ablDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ablName = (TextView) itemView.findViewById(R.id.ablName);
            ablLevel = (TextView) itemView.findViewById(R.id.ablLevel);
            ablCastingActivation = (TextView) itemView.findViewById(R.id.ablCastingActivation);
            ablResource = (TextView) itemView.findViewById(R.id.ablResource);
            ablChanneled = (TextView) itemView.findViewById(R.id.ablChanneled);
            ablCooldown = (TextView) itemView.findViewById(R.id.ablCooldown);
            ablRange = (TextView) itemView.findViewById(R.id.ablRange);
            ablDescription = (TextView) itemView.findViewById(R.id.ablDescription);
        }
    }
}
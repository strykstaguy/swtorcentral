package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;

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

        viewHolder.ablLevel.setText("Level: " + String.valueOf(abilitiesRow.getLevelAquired()));

        if (abilitiesRow.getAbilityPassive() == "True") {
            viewHolder.ablCastingActivation.setText("Passive");
        } else {
            if (abilitiesRow.getCastingTime() == 0) {
                viewHolder.ablCastingActivation.setText("Instant");
            } else {
                viewHolder.ablCastingActivation.setText("Activation: " +  String.valueOf(abilitiesRow.getCastingTime()) + "s");
            }
        }

        if (abilitiesRow.getChannelingTime() == 0) {
            viewHolder.ablChanneled.setVisibility(View.GONE);
        } else {
            viewHolder.ablChanneled.setText("Channeled: " +  String.valueOf(abilitiesRow.getCastingTime()) + "s");
        }

        if (abilitiesRow.getCooldownTime() == 0) {
            viewHolder.ablCooldown.setVisibility(View.GONE);
        } else {
            viewHolder.ablCooldown.setText("Cooldown: " +  String.valueOf(abilitiesRow.getCooldownTime()) + "s");
        }

        if (abilitiesRow.getMaxRange() == 0) {
            viewHolder.ablRange.setVisibility(View.GONE);
        } else {
            int maxRange = abilitiesRow.getCooldownTime() * 10;
            viewHolder.ablRange.setText("Range: " +  String.valueOf(maxRange) + "m");
        }

        if (abilitiesRow.getEnergyCost() != 0) {
            viewHolder.ablRange.setText(abilitiesRow.getClassResource() + " " +  String.valueOf(abilitiesRow.getEnergyCost()));
        } else if (abilitiesRow.getForceCost() != 0) {
            viewHolder.ablRange.setText(abilitiesRow.getClassResource() + " " +  String.valueOf(abilitiesRow.getForceCost()));
        } else {
            viewHolder.ablRange.setVisibility(View.GONE);
        }

        viewHolder.ablDescription.setText(abilitiesRow.getAbilityDescription());
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
package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.CodexItem;

import java.util.ArrayList;

public class CodexAdapter extends RecyclerView.Adapter<CodexAdapter.ViewHolder>{

	private ArrayList<CodexItem> cdxItems;

	public CodexAdapter(ArrayList<CodexItem> cdxItems) {
		super();
		this.cdxItems = cdxItems;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = View.inflate(viewGroup.getContext(), R.layout.codex_row, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		CodexItem codexRow = cdxItems.get(position);

		if (codexRow != null) {
			viewHolder.cdxTitle.setText(codexRow.getTitle());
			viewHolder.cdxDescription.setText(codexRow.getDescription());
			viewHolder.cdxLevel.setText("Level: " + codexRow.getLevel());
			viewHolder.cdxFaction.setText(codexRow.getFaction());

			if (codexRow.getPlanets() != null && !codexRow.getPlanets().equals("")) {
				viewHolder.cdxPlants.setText("Planet(s): " + codexRow.getPlanets());
			}
		}
	}

	@Override
	public int getItemCount() {
		return (null != cdxItems ? cdxItems.size() : 0);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView cdxTitle;
		public TextView cdxDescription;
		public TextView cdxLevel;
		public TextView cdxFaction;
		public TextView cdxPlants;

		public ViewHolder(View itemView) {
			super(itemView);
			cdxTitle = (TextView) itemView.findViewById(R.id.cdxTitle);
			cdxDescription = (TextView) itemView.findViewById(R.id.cdxDescription);
			cdxLevel = (TextView) itemView.findViewById(R.id.cdxLevel);
			cdxPlants = (TextView) itemView.findViewById(R.id.cdxPlants);
			cdxFaction = (TextView) itemView.findViewById(R.id.cdxFaction);
		}
	}
}
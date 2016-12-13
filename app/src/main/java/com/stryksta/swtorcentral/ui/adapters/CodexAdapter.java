package com.stryksta.swtorcentral.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.ui.views.TextViewLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodexAdapter extends RecyclerView.Adapter<CodexAdapter.ViewHolder>{

	private ArrayList<CodexItem> cdxItems;

	public CodexAdapter(ArrayList<CodexItem> cdxItems) {
		super();
		this.cdxItems = cdxItems;
	}

    public void updateItems(ArrayList<CodexItem> cdxItems) {
        this.cdxItems.clear();
        this.cdxItems.addAll(cdxItems);
        notifyDataSetChanged();
       // this.cdxItems = cdxItems;
    }

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = View.inflate(viewGroup.getContext(), R.layout.codex_detail_row, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		CodexItem codexRow = cdxItems.get(position);

		if (codexRow != null) {
			viewHolder.cdxTitle.setText(codexRow.getTitle());
			viewHolder.cdxDescription.setText(codexRow.getDescription());
		}
	}

	@Override
	public int getItemCount() {
		return (null != cdxItems ? cdxItems.size() : 0);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView cdxTitle;
		public TextView cdxDescription;

		public ViewHolder(View itemView) {
			super(itemView);
			cdxTitle = (TextView) itemView.findViewById(R.id.cdxTitle);
			cdxDescription = (TextView) itemView.findViewById(R.id.cdxDescription);
		}
	}
}
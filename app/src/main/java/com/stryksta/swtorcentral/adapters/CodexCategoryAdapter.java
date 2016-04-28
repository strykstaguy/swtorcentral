package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.CodexCategoryItem;

import java.util.ArrayList;

public class CodexCategoryAdapter extends RecyclerView.Adapter<CodexCategoryAdapter.ViewHolder>{

	private ArrayList<CodexCategoryItem> cdxCategoryItems;

	public CodexCategoryAdapter(ArrayList<CodexCategoryItem> cdxCategoryItems) {
		super();
		this.cdxCategoryItems = cdxCategoryItems;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = View.inflate(viewGroup.getContext(), R.layout.codex_category_row, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		CodexCategoryItem CodexCategoryRow = cdxCategoryItems.get(position);
		viewHolder.cdxCategory.setText(CodexCategoryRow.getCategory());
	}

	@Override
	public int getItemCount() {
		return (null != cdxCategoryItems ? cdxCategoryItems.size() : 0);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView cdxCategory;

		public ViewHolder(View itemView) {
			super(itemView);
			cdxCategory = (TextView) itemView.findViewById(R.id.txtCategory);
		}
	}
}
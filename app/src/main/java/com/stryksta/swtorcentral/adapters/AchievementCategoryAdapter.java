package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;

import java.util.ArrayList;

public class AchievementCategoryAdapter extends RecyclerView.Adapter<AchievementCategoryAdapter.ViewHolder>{

	private Context mContext;
	private ArrayList<AchievementCategoryItem> achievementCategoryItemRow;

	public AchievementCategoryAdapter(Context context, ArrayList<AchievementCategoryItem> achievementCategoryItemRow) {
		super();
		mContext = context;
		this.achievementCategoryItemRow = achievementCategoryItemRow;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View view = View.inflate(viewGroup.getContext(), R.layout.achievement_row, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		AchievementCategoryItem achievementCategoryRow = achievementCategoryItemRow.get(position);

		viewHolder.txtViewCategory1.setText(achievementCategoryRow.getCategory());
		viewHolder.txtViewSubCategory.setText(achievementCategoryRow.getCompleted() + " / " + achievementCategoryRow.getTotal());



		int total_completed = achievementCategoryRow.getCompleted();
		int total = achievementCategoryRow.getTotal();

		viewHolder.txtViewProgress.setMax(total);
		viewHolder.txtViewProgress.setProgress(total_completed);

		if (total > 0) {
            int progressValue = total_completed / total;
            String str = String.valueOf(Math.round(progressValue * 100.0f));
            viewHolder.txtProgressText.setText(str + "%");
        } else {
            viewHolder.txtProgressText.setText("0%");
        }

	}

	@Override
	public int getItemCount() {
		return (null != achievementCategoryItemRow ? achievementCategoryItemRow.size() : 0);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder{

		public TextView txtViewCategory1;
		public TextView txtViewSubCategory;
		public TextView txtProgressText;
		public ProgressBar txtViewProgress;


		public ViewHolder(View itemView) {
			super(itemView);
			txtViewCategory1 = (TextView) itemView.findViewById(R.id.txtCategory1);
			txtViewSubCategory = (TextView) itemView.findViewById(R.id.txtSubCategory);
			txtProgressText = (TextView) itemView.findViewById(R.id.txtProgressText);
			txtViewProgress = (ProgressBar) itemView.findViewById(R.id.progressBar);
		}
	}
}
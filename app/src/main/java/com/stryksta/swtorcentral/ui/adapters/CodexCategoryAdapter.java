package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CodexCategoryItem;
import com.stryksta.swtorcentral.ui.activities.AchievementActivity;
import com.stryksta.swtorcentral.ui.activities.CodexActivity;
import com.stryksta.swtorcentral.ui.fragments.Category2Fragment;
import com.stryksta.swtorcentral.ui.fragments.CodexDetailFragment;
import com.stryksta.swtorcentral.util.FragmentUtils;

import java.util.ArrayList;

public class CodexCategoryAdapter extends RecyclerView.Adapter<CodexCategoryAdapter.ViewHolder>{

    private ArrayList<CodexCategoryItem> codexCategoryItem;
    private Context mContext;

    public CodexCategoryAdapter(Context context, ArrayList<CodexCategoryItem> codexCategoryItem) {
        super();
        this.codexCategoryItem = codexCategoryItem;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.codex_category_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        CodexCategoryItem categoryRow = codexCategoryItem.get(position);

        if (categoryRow != null) {
            viewHolder.txtCodexCategory.setText(categoryRow.getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return (null != codexCategoryItem ? codexCategoryItem.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCodexCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCodexCategory = (TextView) itemView.findViewById(R.id.txtCodexCategory);
        }
    }
}
package com.stryksta.swtorcentral.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.ClassItem;

import java.util.ArrayList;

public class ClassesCategoryAdapter extends RecyclerView.Adapter<ClassesCategoryAdapter.ViewHolder>{

    private ArrayList<ClassItem> classItems;

    public ClassesCategoryAdapter(ArrayList<ClassItem> classItems) {
        super();
        this.classItems = classItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.class_fragment_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ClassItem classRow = classItems.get(position);

        if (classRow != null) {
            viewHolder.clsName.setText(classRow.getClassName());
            viewHolder.clsDescription.setText(classRow.getDescription());
            viewHolder.clsImage.setImageResource(classRow.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return (null != classItems ? classItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView clsName;
        public TextView clsDescription;
        public ImageButton clsImage;

        public ViewHolder(View itemView) {
            super(itemView);
            clsName = (TextView) itemView.findViewById(R.id.txtClass);
            clsDescription = (TextView) itemView.findViewById(R.id.txtDesc);
            clsImage = (ImageButton) itemView.findViewById(R.id.imgClass);
        }
    }
}
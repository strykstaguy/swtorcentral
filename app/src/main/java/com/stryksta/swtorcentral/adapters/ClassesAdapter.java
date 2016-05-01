package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ClassItem;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder>{

    private ArrayList<ClassItem> classItems;

    public ClassesAdapter(ArrayList<ClassItem> classItems) {
        super();
        this.classItems = classItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.class_main, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ClassItem classRow = classItems.get(position);

        if (classRow != null) {
            viewHolder.txtClass.setText(classRow.getClassName());
            viewHolder.txtDescription.setText(classRow.getDescription());
            viewHolder.txtStory.setText(classRow.getStory());
            viewHolder.imgClass.setImageResource(classRow.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return (null != classItems ? classItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtClass;
        public TextView txtStory;
        public TextView txtDescription;
        public ImageView imgClass;

        public ViewHolder(View itemView) {
            super(itemView);
            txtClass = (TextView) itemView.findViewById(R.id.txtClass);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDesc);
            txtStory = (TextView) itemView.findViewById(R.id.txtStory);
            imgClass = (ImageButton) itemView.findViewById(R.id.imgClass);
        }
    }
}
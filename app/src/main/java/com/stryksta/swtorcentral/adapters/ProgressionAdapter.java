package com.stryksta.swtorcentral.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.VerticalTextView;
import com.stryksta.swtorcentral.util.timeline.TimelineView;
import java.util.ArrayList;

public class ProgressionAdapter extends RecyclerView.Adapter<ProgressionAdapter.ViewHolder>{

    private ArrayList<ProgressionItem> progressionItems;

    public ProgressionAdapter(ArrayList<ProgressionItem> progressionItems) {
        super();
        this.progressionItems = progressionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.progression_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ProgressionItem progressionItem = progressionItems.get(position);
        viewHolder.imgPlanet.setImageResource(progressionItem.getimgPlanet());
        viewHolder.txtPlanet.setText(progressionItem.getPlanet());
        viewHolder.txtLevel.setText(progressionItem.getLevel());
        viewHolder.txtLabel.setText(progressionItem.getLabel());
        viewHolder.timeLineView.setTimelineType(progressionItem.getType());
    }

    @Override
    public int getItemCount() {
        return (null != progressionItems ? progressionItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgPlanet;
        public TextView txtPlanet;
        public TextView txtLevel;
        public VerticalTextView txtLabel;
        public TimelineView timeLineView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPlanet = (ImageView) itemView.findViewById(R.id.imgPlanet);
            txtPlanet = (TextView) itemView.findViewById(R.id.txtPlanet);
            txtLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            txtLabel = (VerticalTextView) itemView.findViewById(R.id.txtLabel);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);
        }
    }
}
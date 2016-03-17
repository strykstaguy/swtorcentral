package com.stryksta.swtorcentral.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.PlanetActivity;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.VerticalTextView;
import com.stryksta.swtorcentral.util.timeline.TimelineHView;
import com.stryksta.swtorcentral.util.timeline.TimelineType;
import com.stryksta.swtorcentral.util.timeline.TimelineView;
import java.util.ArrayList;

public class ProgressionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ProgressionItem> progressionItems;
    public static final int HEADER = 0;
    public static final int PLANET = 1;
    public static final int SECTION = 2;
    public static final int FLASHOP = 3;
    public static final int BONUS = 4;
    public static final int FOOTER = 5;

    public ProgressionAdapter(ArrayList<ProgressionItem> progressionItems) {
        super();
        this.progressionItems = progressionItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from ( viewGroup.getContext () );
        switch (viewType) {

            case PLANET:
                ViewGroup vImage = ( ViewGroup ) mInflater.inflate ( R.layout.progression_row, viewGroup, false );
                ItemViewHolder vhImage = new ItemViewHolder(vImage);
                return vhImage;
            case HEADER:
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_header, viewGroup, false );
                HeaderViewHolder vhGroup = new HeaderViewHolder(vGroup);
                return vhGroup;
            case SECTION:
                ViewGroup vSection = ( ViewGroup ) mInflater.inflate ( R.layout.progression_section, viewGroup, false );
                SectionViewHolder vhSection = new SectionViewHolder(vSection);
                return vhSection;
            case FLASHOP:
                ViewGroup fSection = ( ViewGroup ) mInflater.inflate ( R.layout.progression_flashop, viewGroup, false );
                FlashopViewHolder fhSection = new FlashopViewHolder(fSection);
                return fhSection;
            case BONUS:
                ViewGroup bSection = ( ViewGroup ) mInflater.inflate ( R.layout.progression_section, viewGroup, false );
                SectionViewHolder bhSection = new SectionViewHolder(bSection);
                return bhSection;
            default:
                ViewGroup vDefault = ( ViewGroup ) mInflater.inflate ( R.layout.progression_row, viewGroup, false );
                ItemViewHolder vhDefault = new ItemViewHolder(vDefault);
                return vhDefault;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case PLANET:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;

                ProgressionItem progressionItem = progressionItems.get(position);
                itemViewHolder.imgPlanet.setImageResource(progressionItem.getimgPlanet());
                itemViewHolder.txtPlanet.setText(progressionItem.getPlanet());
                itemViewHolder.txtLevel.setText(progressionItem.getLevel());
                itemViewHolder.timeLineView.setTimelineType(progressionItem.getTimelineTypeType());
                break;

            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                ProgressionItem headerItem = progressionItems.get(position);
                headerViewHolder.txtTitle.setText(headerItem.getPlanet());
                headerViewHolder.timeLineView.setTimelineType(TimelineType.START);
                break;

            case SECTION:
                SectionViewHolder sectionViewHolder = (SectionViewHolder) viewHolder;
                ProgressionItem sectionItem = progressionItems.get(position);
                sectionViewHolder.txtTitle.setText(sectionItem.getPlanet());
                break;

            case FLASHOP:
                FlashopViewHolder flashpointViewHolder = (FlashopViewHolder) viewHolder;
                ProgressionItem flashpointItem = progressionItems.get(position);
                flashpointViewHolder.txtTitle.setText(flashpointItem.getPlanet());
                flashpointViewHolder.timeLineView.setTimelineType(TimelineType.LINE);
                break;

            case BONUS:
                SectionViewHolder bonusViewHolder = (SectionViewHolder) viewHolder;
                ProgressionItem bonusItem = progressionItems.get(position);
                bonusViewHolder.txtTitle.setText(bonusItem.getPlanet());
                //bonusViewHolder.timeLineView.setTimelineType(TimelineType.LINE);
                break;

            case FOOTER:
                FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
                footerViewHolder.timeLineView.setTimelineType(TimelineType.END);
                break;


        }

    }

    @Override
    public int getItemViewType ( int position ) {

        int viewType;
        if ( progressionItems.get(position) != null ) {
            viewType = progressionItems.get(position).getLayoutType();
        } else {
            viewType = PLANET;
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return (null != progressionItems ? progressionItems.size() : 0);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgPlanet;
        public TextView txtPlanet;
        public TextView txtLevel;
        public TimelineView timeLineView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgPlanet = (ImageView) itemView.findViewById(R.id.imgPlanet);
            txtPlanet = (TextView) itemView.findViewById(R.id.txtPlanet);
            txtLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class FlashopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtTitle;
        public TimelineView timeLineView;

        public FlashopViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TimelineView timeLineView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public TimelineView timeLineView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);
        }
    }
}
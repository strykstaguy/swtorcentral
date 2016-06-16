package com.stryksta.swtorcentral.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stryksta.swtorcentral.PlanetActivity;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.timeline.TimelineHView;
import com.stryksta.swtorcentral.util.timeline.TimelineType;
import com.stryksta.swtorcentral.util.timeline.TimelineView;
import java.util.ArrayList;

public class ProgressionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ProgressionItem> progressionItems;
    public static final int HEADER = 0;
    public static final int CHAPTER = 1;
    public static final int PLANET_SINGLE = 2;
    public static final int PLANET_DOUBLE = 3;
    public static final int FLASHOP_SINGLE = 4;
    public static final int FLASHOP_DOUBLE = 5;

    public ProgressionAdapter(ArrayList<ProgressionItem> progressionItems) {
        super();
        this.progressionItems = progressionItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from ( viewGroup.getContext () );
        switch (viewType) {

            case HEADER:
                ViewGroup headerViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_header, viewGroup, false );
                HeaderViewHolder headerViewHolder = new HeaderViewHolder(headerViewGroup);
                return headerViewHolder;
            case CHAPTER:
                ViewGroup chapterViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_chapter, viewGroup, false );
                SectionViewHolder chapterViewHolder = new SectionViewHolder(chapterViewGroup);
                return chapterViewHolder;
            case PLANET_SINGLE:
                ViewGroup planetSingleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_planet, viewGroup, false );
                PlanetSingleViewHolder planetSingleViewHolder = new PlanetSingleViewHolder(planetSingleViewGroup);
                return planetSingleViewHolder;
            case PLANET_DOUBLE:
                ViewGroup planetDoubleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_planet, viewGroup, false );
                PlanetDoubleViewHolder planetViewHolder = new PlanetDoubleViewHolder(planetDoubleViewGroup);
                return planetViewHolder;
            case FLASHOP_SINGLE:
                ViewGroup flashSingleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_flashop, viewGroup, false );
                FlashopSingleViewHolder flashSingleViewHolder = new FlashopSingleViewHolder(flashSingleViewGroup);
                return flashSingleViewHolder;
            case FLASHOP_DOUBLE:
                ViewGroup flashDoubleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_flashop_right, viewGroup, false );
                FlashopSingleViewHolder flashDoubleViewHolder = new FlashopSingleViewHolder(flashDoubleViewGroup);
                return flashDoubleViewHolder;
            default:
                ViewGroup vDefault = ( ViewGroup ) mInflater.inflate ( R.layout.progression_planet, viewGroup, false );
                PlanetSingleViewHolder vhDefault = new PlanetSingleViewHolder(vDefault);
                return vhDefault;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case PLANET_SINGLE:
                PlanetSingleViewHolder planetViewHolder = (PlanetSingleViewHolder) viewHolder;
                ProgressionItem progressionItem = progressionItems.get(position);

                planetViewHolder.imgPlanetRepublic.setImageResource(progressionItem.getRepublicPlanetImage());
                planetViewHolder.txtPlanetRepublic.setText(progressionItem.getRepublicPlanet());
                planetViewHolder.txtLevelRepublic.setText(progressionItem.getRepublicLevel());

                planetViewHolder.imgPlanetEmpire.setImageResource(progressionItem.getEmpirePlanetImage());
                planetViewHolder.txtPlanetEmpire.setText(progressionItem.getEmpirePlanet());
                planetViewHolder.txtLevelEmpire.setText(progressionItem.getEmpireLevel());

                planetViewHolder.timeLineView.setTimelineType(progressionItem.getTimelineTypeType());
                break;

            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                ProgressionItem headerItem = progressionItems.get(position);
                headerViewHolder.txtTitle.setText(headerItem.getRepublicPlanet());
                headerViewHolder.timeLineView.setTimelineType(TimelineType.START);
                break;

            case CHAPTER:
                SectionViewHolder sectionViewHolder = (SectionViewHolder) viewHolder;
                ProgressionItem sectionItem = progressionItems.get(position);
                sectionViewHolder.txtTitle.setText(sectionItem.getRepublicPlanet());
                break;

            case FLASHOP_SINGLE:
                FlashopSingleViewHolder flashpointViewHolder = (FlashopSingleViewHolder) viewHolder;
                ProgressionItem flashpointItem = progressionItems.get(position);
                flashpointViewHolder.txtFlashRepublicName.setText(flashpointItem.getRepublicPlanet());
                flashpointViewHolder.txtFlashRepublicTitle.setText(flashpointItem.getRepublicLevel());
                flashpointViewHolder.txtFlashEmpireName.setText(flashpointItem.getEmpirePlanet());
                flashpointViewHolder.txtFlashEmpireTitle.setText(flashpointItem.getEmpireLevel());

                flashpointViewHolder.imgIcon.setImageResource(flashpointItem.getRepublicPlanetImage());
                flashpointViewHolder.timeLineView.setTimelineType(TimelineType.LINE);
                break;

            case FLASHOP_DOUBLE:
                FlashopSingleViewHolder flashpointDoubleViewHolder = (FlashopSingleViewHolder) viewHolder;
                ProgressionItem flashpointDoubleItem = progressionItems.get(position);
                flashpointDoubleViewHolder.txtFlashRepublicName.setText(flashpointDoubleItem.getRepublicPlanet());
                flashpointDoubleViewHolder.txtFlashRepublicTitle.setText(flashpointDoubleItem.getRepublicLevel());
                flashpointDoubleViewHolder.txtFlashEmpireName.setText(flashpointDoubleItem.getEmpirePlanet());
                flashpointDoubleViewHolder.txtFlashEmpireTitle.setText(flashpointDoubleItem.getEmpireLevel());

                flashpointDoubleViewHolder.imgIcon.setImageResource(flashpointDoubleItem.getRepublicPlanetImage());
                flashpointDoubleViewHolder.timeLineView.setTimelineType(TimelineType.LINE);
                break;
        }

    }

    @Override
    public int getItemViewType ( int position ) {

        int viewType;
        if ( progressionItems.get(position) != null ) {
            viewType = progressionItems.get(position).getLayoutType();
        } else {
            viewType = PLANET_SINGLE;
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return (null != progressionItems ? progressionItems.size() : 0);
    }

    public class PlanetDoubleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgPlanetRepublic;
        public TextView txtPlanetRepublic;
        public TextView txtLevelRepublic;

        public ImageView imgPlanetEmpire;
        public TextView txtPlanetEmpire;
        public TextView txtLevelEmpire;

        public TimelineView timeLineView;

        public PlanetDoubleViewHolder(View itemView) {
            super(itemView);
            imgPlanetRepublic = (ImageView) itemView.findViewById(R.id.imgPlanetRepublic);
            txtPlanetRepublic = (TextView) itemView.findViewById(R.id.txtPlanetRepublic);
            txtLevelRepublic = (TextView) itemView.findViewById(R.id.txtLevelRepublic);

            imgPlanetEmpire = (ImageView) itemView.findViewById(R.id.imgPlanetEmpire);
            txtPlanetEmpire = (TextView) itemView.findViewById(R.id.txtPlanetEmpire);
            txtLevelEmpire = (TextView) itemView.findViewById(R.id.txtLevelEmpire);

            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Boom!", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            //bundle.putString("planet", progressionItems.get(getAdapterPosition()).getPlanet());
            //bundle.putString("type", progressionItems.get(getAdapterPosition()).getLevel());
            //bundle.putString("faction", "Republic");
            Intent intent = new Intent(v.getContext(), PlanetActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }
    }

    public class PlanetSingleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgPlanetRepublic;
        public TextView txtPlanetRepublic;
        public TextView txtLevelRepublic;

        public ImageView imgPlanetEmpire;
        public TextView txtPlanetEmpire;
        public TextView txtLevelEmpire;

        public TimelineView timeLineView;

        public PlanetSingleViewHolder(View itemView) {
            super(itemView);
            imgPlanetRepublic = (ImageView) itemView.findViewById(R.id.imgPlanetRepublic);
            txtPlanetRepublic = (TextView) itemView.findViewById(R.id.txtPlanetRepublic);
            txtLevelRepublic = (TextView) itemView.findViewById(R.id.txtLevelRepublic);

            imgPlanetEmpire = (ImageView) itemView.findViewById(R.id.imgPlanetEmpire);
            txtPlanetEmpire = (TextView) itemView.findViewById(R.id.txtPlanetEmpire);
            txtLevelEmpire = (TextView) itemView.findViewById(R.id.txtLevelEmpire);

            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Boom!", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
                //bundle.putString("planet", progressionItems.get(getAdapterPosition()).getPlanet());
                //bundle.putString("type", progressionItems.get(getAdapterPosition()).getLevel());
                //bundle.putString("faction", "Republic");
            Intent intent = new Intent(v.getContext(), PlanetActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
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

    public class FlashopDoubleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtFlashRepublicName;
        public TextView txtFlashRepublicTitle;
        public TextView txtFlashEmpireName;
        public TextView txtFlashEmpireTitle;
        public ImageButton imgIcon;

        public TimelineView timeLineView;

        public FlashopDoubleViewHolder(View itemView) {
            super(itemView);
            txtFlashRepublicName = (TextView) itemView.findViewById(R.id.txtFlashRepublicName);
            txtFlashRepublicTitle = (TextView) itemView.findViewById(R.id.txtFlashRepublicTitle);
            txtFlashEmpireName = (TextView) itemView.findViewById(R.id.txtFlashEmpireName);
            txtFlashEmpireTitle = (TextView) itemView.findViewById(R.id.txtFlashEmpireTitle);

            imgIcon = (ImageButton) itemView.findViewById(R.id.imgIcon);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timelineFlashPoint);


            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "FlashPoint!", Toast.LENGTH_SHORT).show();
        }
    }

    public class FlashopSingleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtFlashRepublicName;
        public TextView txtFlashRepublicTitle;
        public TextView txtFlashEmpireName;
        public TextView txtFlashEmpireTitle;
        public ImageButton imgIcon;

        public TimelineView timeLineView;

        public FlashopSingleViewHolder(View itemView) {
            super(itemView);
            txtFlashRepublicName = (TextView) itemView.findViewById(R.id.txtFlashRepublicName);
            txtFlashRepublicTitle = (TextView) itemView.findViewById(R.id.txtFlashRepublicTitle);
            txtFlashEmpireName = (TextView) itemView.findViewById(R.id.txtFlashEmpireName);
            txtFlashEmpireTitle = (TextView) itemView.findViewById(R.id.txtFlashEmpireTitle);

            imgIcon = (ImageButton) itemView.findViewById(R.id.imgIcon);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timelineFlashPoint);


            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "FlashPoint!", Toast.LENGTH_SHORT).show();
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
}
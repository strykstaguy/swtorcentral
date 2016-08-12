package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stryksta.swtorcentral.ui.activities.PlanetActivity;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.ProgressionItem;
import com.stryksta.swtorcentral.ui.views.timeline.TimelineType;
import com.stryksta.swtorcentral.ui.views.timeline.TimelineView;
import java.util.ArrayList;

public class ProgressionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ProgressionItem> progressionItems;
    public static final int HEADER = 0;
    public static final int CHAPTER = 1;
    public static final int PLANET_SINGLE = 2;
    public static final int PLANET_DOUBLE = 3;
    public static final int FLASHOP_SINGLE = 4;
    public static final int FLASHOP_DOUBLE = 5;
    public static final int DIR_RIGHT = 0;
    public static final int DIR_LEFT = 1;

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
                ViewGroup planetSingleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_planet_single, viewGroup, false );
                PlanetSingleViewHolder planetSingleViewHolder = new PlanetSingleViewHolder(planetSingleViewGroup);
                return planetSingleViewHolder;
            case PLANET_DOUBLE:
                ViewGroup planetDoubleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_planet, viewGroup, false );
                PlanetDoubleViewHolder planetViewHolder = new PlanetDoubleViewHolder(planetDoubleViewGroup);
                return planetViewHolder;
            case FLASHOP_SINGLE:
                ViewGroup flashSingleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_flashop_single, viewGroup, false );
                FlashopSingleViewHolder flashSingleViewHolder = new FlashopSingleViewHolder(flashSingleViewGroup);
                return flashSingleViewHolder;
            case FLASHOP_DOUBLE:
                ViewGroup flashDoubleViewGroup = ( ViewGroup ) mInflater.inflate ( R.layout.progression_flashop, viewGroup, false );
                FlashopDoubleViewHolder flashDoubleViewHolder = new FlashopDoubleViewHolder(flashDoubleViewGroup);
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

            case PLANET_DOUBLE:
                PlanetDoubleViewHolder planetDoubleViewHolder = (PlanetDoubleViewHolder) viewHolder;
                ProgressionItem progressionDoubleItem = progressionItems.get(position);

                planetDoubleViewHolder.imgPlanetRepublic.setImageResource(progressionDoubleItem.getRepublicPlanetImage());
                planetDoubleViewHolder.txtPlanetRepublic.setText(progressionDoubleItem.getRepublicPlanet());
                planetDoubleViewHolder.txtLevelRepublic.setText(progressionDoubleItem.getRepublicLevel());

                planetDoubleViewHolder.imgPlanetEmpire.setImageResource(progressionDoubleItem.getEmpirePlanetImage());
                planetDoubleViewHolder.txtPlanetEmpire.setText(progressionDoubleItem.getEmpirePlanet());
                planetDoubleViewHolder.txtLevelEmpire.setText(progressionDoubleItem.getEmpireLevel());

                planetDoubleViewHolder.timeLineView.setTimelineType(TimelineType.LINE);
                break;

            case PLANET_SINGLE:
                PlanetSingleViewHolder planetViewHolder = (PlanetSingleViewHolder) viewHolder;
                ProgressionItem progressionItem = progressionItems.get(position);

                planetViewHolder.imgPlanet.setImageResource(progressionItem.getRepublicPlanetImage());
                planetViewHolder.txtPlanet.setText(progressionItem.getRepublicPlanet());
                planetViewHolder.txtLevel.setText(progressionItem.getRepublicLevel());

                planetViewHolder.timeLineView.setTimelineType(TimelineType.LINE);

                if (progressionItems.get(position).getLayoutDirection() == DIR_RIGHT) {

                    LinearLayout.LayoutParams layoutPlanetParams = (LinearLayout.LayoutParams) planetViewHolder.txtPlanet.getLayoutParams();
                    layoutPlanetParams.gravity = Gravity.LEFT;

                    LinearLayout.LayoutParams layoutLevelParams = (LinearLayout.LayoutParams) planetViewHolder.txtLevel.getLayoutParams();
                    layoutLevelParams.gravity = Gravity.LEFT;
                    planetViewHolder.txtLevel.setLayoutParams(layoutLevelParams);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, planetViewHolder.imgPlanet.getId());
                    layoutParams.addRule(RelativeLayout.END_OF, planetViewHolder.imgPlanet.getId());
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                    layoutParams.setMargins(dpToPx(15), 0, 0, 0);
                    planetViewHolder.linearLayout.setLayoutParams(layoutParams);
                } else if (progressionItems.get(position).getLayoutDirection() == DIR_LEFT) {

                    LinearLayout.LayoutParams layoutPlanetParams = (LinearLayout.LayoutParams) planetViewHolder.txtPlanet.getLayoutParams();
                    layoutPlanetParams.gravity = Gravity.RIGHT;

                    LinearLayout.LayoutParams layoutLevelParams = (LinearLayout.LayoutParams) planetViewHolder.txtLevel.getLayoutParams();
                    layoutLevelParams.gravity = Gravity.RIGHT;
                    planetViewHolder.txtLevel.setLayoutParams(layoutLevelParams);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.addRule(RelativeLayout.LEFT_OF, planetViewHolder.imgPlanet.getId());
                    layoutParams.addRule(RelativeLayout.START_OF, planetViewHolder.imgPlanet.getId());
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                    layoutParams.setMargins(0, 0, dpToPx(15), 0); //left, top, right, bottom
                    planetViewHolder.linearLayout.setLayoutParams(layoutParams);

                }


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
                flashpointViewHolder.flashpointName.setText(flashpointItem.getRepublicFlashpoint());
                flashpointViewHolder.flashpointTitle.setText(flashpointItem.getRepublicLevel());

                flashpointViewHolder.imgIcon.setImageResource(flashpointItem.getIcon());
                flashpointViewHolder.timeLineView.setTimelineType(TimelineType.LINE);

                if (progressionItems.get(position).getLayoutDirection() == DIR_RIGHT) {

                    LinearLayout.LayoutParams layoutLevelParams = (LinearLayout.LayoutParams) flashpointViewHolder.flashpointTitle.getLayoutParams();
                    layoutLevelParams.gravity = Gravity.LEFT;
                    flashpointViewHolder.flashpointTitle.setLayoutParams(layoutLevelParams);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, flashpointViewHolder.imgIcon.getId());
                    layoutParams.addRule(RelativeLayout.END_OF, flashpointViewHolder.imgIcon.getId());
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                    layoutParams.setMargins(dpToPx(15), 0, 0, 0);
                    flashpointViewHolder.linearLayout.setLayoutParams(layoutParams);
                } else if (progressionItems.get(position).getLayoutDirection() == DIR_LEFT) {

                    LinearLayout.LayoutParams layoutLevelParams = (LinearLayout.LayoutParams) flashpointViewHolder.flashpointTitle.getLayoutParams();
                    layoutLevelParams.gravity = Gravity.RIGHT;
                    flashpointViewHolder.flashpointTitle.setLayoutParams(layoutLevelParams);

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.addRule(RelativeLayout.LEFT_OF, flashpointViewHolder.imgIcon.getId());
                    layoutParams.addRule(RelativeLayout.START_OF, flashpointViewHolder.imgIcon.getId());
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                    layoutParams.setMargins(0, 0, dpToPx(15), 0); //left, top, right, bottom
                    flashpointViewHolder.linearLayout.setLayoutParams(layoutParams);

                }

                break;

            case FLASHOP_DOUBLE:
                FlashopDoubleViewHolder flashpointDoubleViewHolder = (FlashopDoubleViewHolder) viewHolder;
                ProgressionItem flashpointDoubleItem = progressionItems.get(position);
                flashpointDoubleViewHolder.txtFlashRepublicName.setText(flashpointDoubleItem.getRepublicFlashpoint());
                flashpointDoubleViewHolder.txtFlashRepublicTitle.setText(flashpointDoubleItem.getRepublicLevel());
                flashpointDoubleViewHolder.txtFlashEmpireName.setText(flashpointDoubleItem.getEmpireFlashpoint());
                flashpointDoubleViewHolder.txtFlashEmpireTitle.setText(flashpointDoubleItem.getEmpireLevel());

                flashpointDoubleViewHolder.imgIcon.setImageResource(flashpointDoubleItem.getIcon());
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

        public LinearLayout linearLayoutRepublic;
        public LinearLayout linearLayoutEmpire;

        public TimelineView timeLineView;

        public PlanetDoubleViewHolder(View itemView) {
            super(itemView);
            imgPlanetRepublic = (ImageView) itemView.findViewById(R.id.imgPlanetRepublic);
            txtPlanetRepublic = (TextView) itemView.findViewById(R.id.txtPlanetRepublic);
            txtLevelRepublic = (TextView) itemView.findViewById(R.id.txtLevelRepublic);
            imgPlanetRepublic.setTag("Right");

            imgPlanetEmpire = (ImageView) itemView.findViewById(R.id.imgPlanetEmpire);
            txtPlanetEmpire = (TextView) itemView.findViewById(R.id.txtPlanetEmpire);
            txtLevelEmpire = (TextView) itemView.findViewById(R.id.txtLevelEmpire);

            linearLayoutRepublic = (LinearLayout) itemView.findViewById(R.id.linearLayoutRepublic);
            linearLayoutEmpire = (LinearLayout) itemView.findViewById(R.id.linearLayoutEmpire);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            linearLayoutRepublic.setOnClickListener(this);
            linearLayoutEmpire.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext(),  String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();

            if (v.getId() == linearLayoutRepublic.getId()){

                Bundle bundle = new Bundle();
                bundle.putString("planet", progressionItems.get(getAdapterPosition()).getRepublicPlanet());
                bundle.putString("type", progressionItems.get(getAdapterPosition()).getRepublicLevel());
                bundle.putInt("image", progressionItems.get(getAdapterPosition()).getRepublicPlanetImage());
                bundle.putString("faction", "Republic");
                Intent intent = new Intent(v.getContext(), PlanetActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            } else if (v.getId() == linearLayoutEmpire.getId()){

                Bundle bundle = new Bundle();
                bundle.putString("planet", progressionItems.get(getAdapterPosition()).getEmpirePlanet());
                bundle.putString("type", progressionItems.get(getAdapterPosition()).getEmpireLevel());
                bundle.putInt("image", progressionItems.get(getAdapterPosition()).getEmpirePlanetImage());
                bundle.putString("faction", "Empire");
                Intent intent = new Intent(v.getContext(), PlanetActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }


        }
    }

    public class PlanetSingleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgPlanet;
        public TextView txtPlanet;
        public TextView txtLevel;

        public TimelineView timeLineView;
        public LinearLayout linearLayout;

        public PlanetSingleViewHolder(View itemView) {
            super(itemView);
            imgPlanet = (ImageView) itemView.findViewById(R.id.imgPlanet);
            txtPlanet = (TextView) itemView.findViewById(R.id.txtPlanet);
            txtLevel = (TextView) itemView.findViewById(R.id.txtLevel);

            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

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

        public TextView flashpointName;
        public TextView flashpointTitle;

        public ImageButton imgIcon;

        public LinearLayout linearLayout;
        public TimelineView timeLineView;

        public FlashopSingleViewHolder(View itemView) {
            super(itemView);
            flashpointName = (TextView) itemView.findViewById(R.id.flashpointName);
            flashpointTitle = (TextView) itemView.findViewById(R.id.flashpointTitle);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

            imgIcon = (ImageButton) itemView.findViewById(R.id.imgIcon);
            timeLineView = (TimelineView) itemView.findViewById(R.id.timeline);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "FlashPoint Single", Toast.LENGTH_SHORT).show();
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

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
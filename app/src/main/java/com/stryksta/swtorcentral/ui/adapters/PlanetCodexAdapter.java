package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.PlanetCodexItem;
import com.stryksta.swtorcentral.models.ServerItem;
import com.stryksta.swtorcentral.ui.activities.CodexDetailActivity;
import com.stryksta.swtorcentral.ui.activities.PlanetActivity;
import com.stryksta.swtorcentral.util.SizeAdjustingTextView;

import java.util.ArrayList;

public class PlanetCodexAdapter extends RecyclerView.Adapter<PlanetCodexAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<PlanetCodexItem> planetCodexItems;

    public PlanetCodexAdapter(Context context, ArrayList<PlanetCodexItem> planetCodexItems) {
        super();
        mContext = context;
        this.planetCodexItems = planetCodexItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.codex_planet_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        //if the page is down?
        if (planetCodexItems != null) {

            PlanetCodexItem planetCodexRow = planetCodexItems.get(position);
            viewHolder.cdxCount.setText(planetCodexRow.getCount());
            viewHolder.cdxCategory.setText(planetCodexRow.getCategory());

        }
    }

    @Override
    public int getItemCount() {
        return (null != planetCodexItems ? planetCodexItems.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cdxCount;
        public TextView cdxCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            cdxCount = (TextView) itemView.findViewById(R.id.cdxCount);
            cdxCategory = (TextView) itemView.findViewById(R.id.cdxCategory);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //click here
            Bundle bundle = new Bundle();
            bundle.putString("cdxCategory", planetCodexItems.get(getAdapterPosition()).getCategory());
            bundle.putString("cdxPlanetID", planetCodexItems.get(getAdapterPosition()).getPlanetID());
            bundle.putString("cdxPlanetName", planetCodexItems.get(getAdapterPosition()).getPlanetName());
            Intent intent = new Intent(v.getContext(), CodexDetailActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }
    }
}
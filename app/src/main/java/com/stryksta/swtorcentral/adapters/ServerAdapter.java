package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.RssItem;
import com.stryksta.swtorcentral.data.ServerItem;
import com.stryksta.swtorcentral.util.SizeAdjustingTextView;

import java.util.ArrayList;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<ServerItem> serverItems;

    public ServerAdapter(Context context, ArrayList<ServerItem> serverItems) {
        super();
        mContext = context;
        this.serverItems = serverItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = View.inflate(viewGroup.getContext(), R.layout.server_row, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ServerItem serverRow = serverItems.get(position);
        viewHolder.txtServerName.setText(serverRow.getserverName());
        viewHolder.txtServerType.setText(serverRow.getserverType());
        viewHolder.txtServerZone.setText(serverRow.getserverZone());

        viewHolder.txtServerPopulation.setText(serverRow.getserverStatus());
        if (serverRow.getserverStatus().equalsIgnoreCase("Light")) {
            viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.lightcolor));
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Standard")) {
            viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.standardcolor));
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Heavy")) {
            viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.heavycolor));
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Very Heavy")) {
            viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.veryheavycolor));
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Full")) {
            viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.fullcolor));
        }
    }

    @Override
    public int getItemCount() {
        return (null != serverItems ? serverItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public SizeAdjustingTextView txtServerName;
        public TextView txtServerType;
        public TextView txtServerZone;
        public TextView txtServerPopulation;
        public ImageButton serverStatusIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            txtServerName = (SizeAdjustingTextView) itemView.findViewById(R.id.serverTitle);
            txtServerType = (TextView) itemView.findViewById(R.id.serverType);
            txtServerZone = (TextView) itemView.findViewById(R.id.serverZone);
            txtServerPopulation = (TextView) itemView.findViewById(R.id.serverPopulation);
            serverStatusIcon = (ImageButton) itemView.findViewById(R.id.serverStatusIcon);
        }
    }
}
package com.stryksta.swtorcentral.ui.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.ServerItem;
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

        //if the page is down?
        if (serverItems != null) {

            ServerItem serverRow = serverItems.get(position);
            viewHolder.txtServerName.setText(serverRow.getserverName());
            viewHolder.txtServerType.setText(serverRow.getserverType());
            viewHolder.txtServerZone.setText(serverRow.getserverZone());

            //GradientDrawable background = (GradientDrawable) viewHolder.serverStatusIcon.getBackground();
            //background.setColor(ContextCompat.getColor(mContext, R.color.md_grey_700));

            //viewHolder.serverStatusIcon.setBackgroundResource(serverRow.getImageId());
            viewHolder.serverStatusIcon.setImageDrawable(ContextCompat.getDrawable(mContext, serverRow.getImageId()));

            viewHolder.txtServerPopulation.setText(serverRow.getserverStatus());
            if (serverRow.getserverStatus().equalsIgnoreCase("Light")) {
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_green_500));
                viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_green_500));

            } else if (serverRow.getserverStatus().equalsIgnoreCase("Standard")) {
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_amber_600));
                viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_amber_600));

            } else if (serverRow.getserverStatus().equalsIgnoreCase("Heavy")) {
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_orange_500));
                viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_orange_500));

            } else if (serverRow.getserverStatus().equalsIgnoreCase("Very Heavy")) {
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_red_500));
                viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_red_500));

            } else if (serverRow.getserverStatus().equalsIgnoreCase("Full")) {
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_red_900));
                viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_red_900));

            } else if (serverRow.getserverStatus().equalsIgnoreCase("Offline")) {
                viewHolder.txtServerName.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
                viewHolder.txtServerType.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
                viewHolder.txtServerZone.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
                viewHolder.txtServerPopulation.setTextColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
                //viewHolder.serverBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.md_black_1000));
            }
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
        public RelativeLayout serverBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            txtServerName = (SizeAdjustingTextView) itemView.findViewById(R.id.serverTitle);
            txtServerType = (TextView) itemView.findViewById(R.id.serverType);
            txtServerZone = (TextView) itemView.findViewById(R.id.serverZone);
            txtServerPopulation = (TextView) itemView.findViewById(R.id.serverPopulation);
            serverStatusIcon = (ImageButton) itemView.findViewById(R.id.serverStatusIcon);
            serverBackground = (RelativeLayout) itemView.findViewById(R.id.serverBackground);
        }
    }
}
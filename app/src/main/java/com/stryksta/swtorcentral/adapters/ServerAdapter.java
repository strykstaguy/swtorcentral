package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
        viewHolder.txtServerStatus.setText(serverRow.getserverStatus());

        int iColor = mContext.getResources().getColor(R.color.regularcolor);

        if (serverRow.getserverStatus().equalsIgnoreCase("Light")) {
            iColor = mContext.getResources().getColor(R.color.lightcolor);
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Standard")) {
            iColor = mContext.getResources().getColor(R.color.standardcolor);
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Heavy")) {
            iColor = mContext.getResources().getColor(R.color.heavycolor);
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Very Heavy")) {
            iColor = mContext.getResources().getColor(R.color.veryheavycolor);
        } else if (serverRow.getserverStatus().equalsIgnoreCase("Full")) {
            iColor = mContext.getResources().getColor(R.color.fullcolor);
        } else {
            iColor = mContext.getResources().getColor(R.color.regularcolor);
        }

        viewHolder.txtServerStatus.setVisibility(View.GONE);

        viewHolder.txtServerIcon.setImageResource(serverRow.getImageId());

        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        viewHolder.txtServerIcon.setColorFilter(colorFilter);
    }

    @Override
    public int getItemCount() {
        return (null != serverItems ? serverItems.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView txtServerIcon;
        public SizeAdjustingTextView txtServerName;
        public TextView txtServerStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtServerIcon = (ImageView) itemView.findViewById(R.id.serverIcon);
            txtServerName = (SizeAdjustingTextView) itemView.findViewById(R.id.serverTitle);
            txtServerStatus = (TextView) itemView.findViewById(R.id.serverStatus);
        }
    }
}
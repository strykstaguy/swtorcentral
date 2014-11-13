package com.stryksta.swtorcentral;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.ServerItem;
import com.stryksta.swtorcentral.util.SizeAdjustingTextView;

import java.util.ArrayList;

public class ServerAdapter extends ArrayAdapter<ServerItem> {

	Context mContext;
    private final ArrayList<ServerItem> results;
    public ServerAdapter(Context mContext, final int textViewResourceId,
            final ArrayList<ServerItem> results) {

        super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.server_row, null);
            holder = new ViewHolder();
            
            holder.txtViewServerIcon = (ImageView) v.findViewById(R.id.serverIcon);
            holder.txtViewServerName = (SizeAdjustingTextView) v.findViewById(R.id.serverTitle);
            holder.txtViewServerStatus = (TextView) v.findViewById(R.id.serverStatus);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        ServerItem rowItem = results.get(position);

        	if (holder.txtViewServerName != null) {
        		holder.txtViewServerName.setText(rowItem.getserverName());
            }
        	
        	if (holder.txtViewServerStatus != null) {
        		holder.txtViewServerStatus.setText(rowItem.getserverStatus());
            }
        	
           //holder.txtViewServerStatus.setText(rowItem.getserverStatus());
           //holder.txtViewServerName.setText(rowItem.getserverName());
            
        	int iColor = mContext.getResources().getColor(R.color.regularcolor);
        	
            if (rowItem.getserverStatus().equalsIgnoreCase("Light")) {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.lightcolor));
                iColor = mContext.getResources().getColor(R.color.lightcolor);
            } else if (rowItem.getserverStatus().equalsIgnoreCase("Standard")) {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.standardcolor));
                iColor = mContext.getResources().getColor(R.color.standardcolor);
            } else if (rowItem.getserverStatus().equalsIgnoreCase("Heavy")) {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.heavycolor));
                iColor = mContext.getResources().getColor(R.color.heavycolor);
            } else if (rowItem.getserverStatus().equalsIgnoreCase("Very Heavy")) {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.veryheavycolor));
                iColor = mContext.getResources().getColor(R.color.veryheavycolor);
            } else if (rowItem.getserverStatus().equalsIgnoreCase("Full")) {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.fullcolor));
                iColor = mContext.getResources().getColor(R.color.fullcolor);
            } else {
                holder.txtViewServerStatus.setTextColor(mContext.getResources().getColor(R.color.regularcolor));
                iColor = mContext.getResources().getColor(R.color.regularcolor);
            }
            
            holder.txtViewServerStatus.setVisibility(View.GONE);
            
            holder.txtViewServerIcon.setImageResource(rowItem.getImageId());
			
            int red = (iColor & 0xFF0000) / 0xFFFF;
            int green = (iColor & 0xFF00) / 0xFF;
            int blue = iColor & 0xFF;

            float[] matrix = { 0, 0, 0, 0, red
                             , 0, 0, 0, 0, green
                             , 0, 0, 0, 0, blue
                             , 0, 0, 0, 1, 0 };

            ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

            holder.txtViewServerIcon.setColorFilter(colorFilter);
            
        return v;
    }

    private static class ViewHolder {
        public ImageView txtViewServerIcon;
        public SizeAdjustingTextView txtViewServerName;
        public TextView txtViewServerStatus;
    }
    
    private void changeColor() {
    	//int iColor = Color.parseColor(color);
    	int iColor = mContext.getResources().getColor(R.color.standardcolor);
    			
        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                         , 0, 0, 0, 0, green
                         , 0, 0, 0, 0, blue
                         , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        //drawable.setColorFilter(colorFilter);
        //holder.txtViewServerIcon.setImageResource(rowItem.getImageId());
    }
    
    
}

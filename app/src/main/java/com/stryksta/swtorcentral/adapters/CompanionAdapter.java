package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.CompanionItem;

import java.util.ArrayList;

public class CompanionAdapter extends ArrayAdapter<CompanionItem> {

	Context mContext;
    private final ArrayList<CompanionItem> results;
    public CompanionAdapter(Context mContext, final int textViewResourceId,
            final ArrayList<CompanionItem> results) {

        super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
    }

    public String unescapeString(String string) {
        if (string == null)
            return null;
        
        string = DatabaseUtils.sqlEscapeString(string);
        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder sb = new StringBuilder(string);

        return sb.toString().replaceAll("\\\n", "\n");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.advanced_class_row, null);
            holder = new ViewHolder();
            
            holder.txtName = (TextView) v.findViewById(R.id.txtName);
            holder.txtNameSub = (TextView) v.findViewById(R.id.txtNameSub);
            holder.txtRole = (TextView) v.findViewById(R.id.txtRole);
            holder.txtBonus = (TextView) v.findViewById(R.id.txtBonus);
            holder.txtRomance = (TextView) v.findViewById(R.id.txtRomance);
            holder.txtStats = (TextView) v.findViewById(R.id.txtStats);
            holder.txtWeapon = (TextView) v.findViewById(R.id.txtWeapon);
            holder.txtDescription = (TextView) v.findViewById(R.id.txtDescription);
            holder.txtArmor = (TextView) v.findViewById(R.id.txtArmor);
            
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        CompanionItem rowItem = results.get(position);
        	holder.txtName.setText(rowItem.getName());
        	holder.txtNameSub.setText(rowItem.getGender() + " " + rowItem.getRace());
        	holder.txtRole.setText("Role: " + rowItem.getRole());
        	holder.txtBonus.setText(rowItem.getBonus());
        	holder.txtRomance.setText("Romanceable: " + rowItem.getRomance());
        	holder.txtStats.setText("Stats: " + rowItem.getPrimaryStat() + ", " + rowItem.getSecondaryStat());
        	holder.txtWeapon.setText("Weapons: " + rowItem.getPrimaryWeapon() + ", " + rowItem.getSecondaryWeapon());
        	holder.txtDescription.setText(rowItem.getDescription());
        	holder.txtArmor.setText("Armor: " + rowItem.getArmor());
            //String s = unescapeString(rowItem.getGifts());
           ///holder.txtGifts.setText(s);

        return v;
    }

    private static class ViewHolder {
        public TextView txtBonus;
        public TextView txtNameSub;
        public TextView txtGifts;
        public TextView txtArmor;
        public TextView txtName;
        public TextView txtStats;
        public TextView txtWeapon;
        public TextView txtRole;
        public TextView txtRomance;
        public TextView txtDescription;
    }
}

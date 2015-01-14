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

public class CompanionClassAdapter extends ArrayAdapter<CompanionItem> {

	Context mContext;
    private final ArrayList<CompanionItem> results;
    public CompanionClassAdapter(Context mContext, final int textViewResourceId,
                                 final ArrayList<CompanionItem> results) {

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
            v = inflater.inflate(R.layout.companion_class_row, null);
            holder = new ViewHolder();
            
            holder.txtName = (TextView) v.findViewById(R.id.txtName);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        CompanionItem rowItem = results.get(position);
        	holder.txtName.setText(rowItem.getName());

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

    public String unescapeString(String string) {
        if (string == null)
            return null;

        string = DatabaseUtils.sqlEscapeString(string);
        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder sb = new StringBuilder(string);

        return sb.toString().replaceAll("\\\n", "\n");
    }
}

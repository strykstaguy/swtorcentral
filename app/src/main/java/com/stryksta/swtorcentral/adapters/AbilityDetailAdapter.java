package com.stryksta.swtorcentral.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AbilitiesItem;

import java.util.ArrayList;

public class AbilityDetailAdapter extends ArrayAdapter<AbilitiesItem> {

	Context mContext;
	
    private final ArrayList<AbilitiesItem> results;
    public AbilityDetailAdapter(Context mContext, final ArrayList<AbilitiesItem> results) {

        super(mContext, R.layout.ability_detail_row, results);
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
            v = inflater.inflate(R.layout.ability_detail_row, null);
            holder = new ViewHolder();
            
            holder.ablName = (TextView) v.findViewById(R.id.ablName);
            holder.ablLevel = (TextView) v.findViewById(R.id.ablLevel);
            holder.ablCastingActivation = (TextView) v.findViewById(R.id.ablCastingActivation);
            holder.ablResource = (TextView) v.findViewById(R.id.ablResource);
            holder.ablChanneled = (TextView) v.findViewById(R.id.ablChanneled);
            holder.ablCooldown = (TextView) v.findViewById(R.id.ablCooldown);
            holder.ablRange = (TextView) v.findViewById(R.id.ablRange);
            holder.ablDescription = (TextView) v.findViewById(R.id.ablDescription);
            
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        AbilitiesItem rowItem = results.get(position);
        
        //Hide 0 from ability name
        holder.ablName.setText(rowItem.getablName());




        if (rowItem.getablDesc().equals("")) {
        	holder.ablDescription.setVisibility(View.GONE);
        } else {
        	holder.ablDescription.setText(rowItem.getablDesc());
        }

        if (rowItem.getablCastingTime() == 0) {
            holder.ablCastingActivation.setVisibility(View.GONE);
        } else {
            holder.ablCastingActivation.setText("Activation: " +  rowItem.getablCastingTime());
        }

        /*
        //Resource
        if (rowItem.getresource() == 0) {
        	holder.txtResource.setVisibility(View.GONE);
        } else {
        	holder.txtResource.setText(classResource + ": " +  String.valueOf(rowItem.getresource()));
        }


        
        if (rowItem.getchanneled() == null) {
        	holder.txtChanneled.setVisibility(View.GONE);
        } else {
        	holder.txtChanneled.setText("Channeled: " +  rowItem.getchanneled());
        }
        
        if (rowItem.getcooldown() == null) {
        	holder.txtCooldown.setVisibility(View.GONE);
        } else {
        	holder.txtCooldown.setText("Cooldown: " +  rowItem.getcooldown());
        }

        if (rowItem.getrange() == null) {
        	holder.txtRange.setVisibility(View.GONE);
        } else {
        	holder.txtRange.setText("Range: " +  rowItem.getrange());
        }
        
        if (rowItem.gethighlight() == null) {
        	holder.txtHighlight.setVisibility(View.GONE);
        } else {
        	holder.txtHighlight.setText(rowItem.gethighlight());
        }
        
        if (rowItem.getfootnote() == null) {
        	holder.txtFootnote.setVisibility(View.GONE);
        } else {
        	String footnote = rowItem.getfootnote();
        	CharSequence newFootnote = applyTextStyle(footnote, "@", true, false);
        	newFootnote = applyTextStyle(newFootnote, "#", false, true); 
        	holder.txtFootnote.setText(newFootnote);
        }
        */
        String description = rowItem.getablDesc();
        CharSequence newDescription = applyTextStyle(description, "@", true, false);
        newDescription = applyTextStyle(newDescription, "#", false, true);
        holder.ablDescription.setText(newDescription);

        return v;
    }

    private static class ViewHolder {
        public TextView ablName;
        public TextView ablLevel;
        public TextView ablCastingActivation;
        public TextView ablResource;
        public TextView ablChanneled;
        public TextView ablCooldown;
        public TextView ablRange;
        public TextView ablDescription;
    }
    
    private static CharSequence applyTextStyle(CharSequence text, String token, boolean bold, boolean italic) {
    	int tokenLen = token.length();
    	int start = text.toString().indexOf(token) + tokenLen;
    	int end = text.toString().indexOf(token, start);

    	while (start > -1 && end > -1) {
    		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
    		
    		if (bold) {
    			spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
    		}
    		
    		if (italic) {
    			spannableStringBuilder.setSpan(new StyleSpan(Typeface.ITALIC), start, end, 0);
    		}

    		// Delete the tokens before and after the span
    		spannableStringBuilder.delete(end, end + tokenLen);
    		spannableStringBuilder.delete(start - tokenLen, start);
    		text = spannableStringBuilder;

    		start = text.toString().indexOf(token, end - tokenLen - tokenLen) + tokenLen;
    		end = text.toString().indexOf(token, start);
    	}

    	return text;
    } 
}

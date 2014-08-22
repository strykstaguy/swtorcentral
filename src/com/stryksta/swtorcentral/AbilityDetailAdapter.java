package com.stryksta.swtorcentral;

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
	String classResource;
	String type;
	
    private final ArrayList<AbilitiesItem> results;
    public AbilityDetailAdapter(Context mContext, final int textViewResourceId,
            final ArrayList<AbilitiesItem> results, final String classResource, final String type) {

        super(mContext, textViewResourceId, results);
        this.results = results;
        this.mContext = mContext;
        this.classResource = classResource;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.advanced_class_tab3_dialog_row, null);
            holder = new ViewHolder();
            
            holder.txtName = (TextView) v.findViewById(R.id.txtName);
            holder.txtLevel = (TextView) v.findViewById(R.id.txtLevel);
            holder.txtDescription = (TextView) v.findViewById(R.id.txtDescription);
            holder.txtResource = (TextView) v.findViewById(R.id.txtResource);
            holder.txtActivation = (TextView) v.findViewById(R.id.txtActivation);
            holder.txtChanneled = (TextView) v.findViewById(R.id.txtChanneled);
            holder.txtCooldown = (TextView) v.findViewById(R.id.txtCooldown);
            holder.txtRange = (TextView) v.findViewById(R.id.txtRange);
            holder.txtFootnote = (TextView) v.findViewById(R.id.txtFootnote);
            holder.txtSummary = (TextView) v.findViewById(R.id.txtSummary);
            holder.txtHighlight = (TextView) v.findViewById(R.id.txtHighlight);
            
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        AbilitiesItem rowItem = results.get(position);
        
        //Hide 0 from ability name
        if (rowItem.getrank() > 0) {
        	holder.txtName.setText(rowItem.gettxtName() + " " + String.valueOf(rowItem.getrank()));
        } else {
        	holder.txtName.setText(rowItem.gettxtName());
        }
        
        
        //If level is 0 or less then display as level 1
        if (rowItem.getlevel() <= 0) {
        	holder.txtLevel.setText("Level 1");
        } else {
        	holder.txtLevel.setText("Level " + String.valueOf(rowItem.getlevel()));
        }
        
        //If it is a skill  ability hide level
        if (type.equals("skill")) {
        	holder.txtLevel.setVisibility(View.GONE);
        }

        if (rowItem.getsummary().equals("Active")) {
        	holder.txtSummary.setVisibility(View.GONE);
        } else {
        	holder.txtSummary.setText(rowItem.getsummary());
        }
        
        //Resource
        if (rowItem.getresource() == 0) {
        	holder.txtResource.setVisibility(View.GONE);
        } else {
        	holder.txtResource.setText(classResource + ": " +  String.valueOf(rowItem.getresource()));
        }

        if (rowItem.getactivation() == null) {
        	holder.txtActivation.setVisibility(View.GONE);
        } else {
        	holder.txtActivation.setText("Activation: " +  rowItem.getactivation());
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
        
        String description = rowItem.getdescription();
        CharSequence newDescription = applyTextStyle(description, "@", true, false);
        newDescription = applyTextStyle(newDescription, "#", false, true);
        holder.txtDescription.setText(newDescription);

        return v;
    }

    private static class ViewHolder {
        public TextView txtName;
        public TextView txtLevel;
        public TextView txtDescription;
        public TextView txtResource;
        public TextView txtActivation;
        public TextView txtChanneled;
        public TextView txtCooldown;
        public TextView txtRange;
        public TextView txtFootnote;
        public TextView txtSummary;
        public TextView txtHighlight;
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

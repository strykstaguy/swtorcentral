package com.stryksta.swtorcentral;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public ExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.test_list_item, null);
        }
        
        
        if(expandedListPosition == 0) {
			setViewBackgroundWithoutResettingPadding(convertView, R.drawable.background_top);
		} else if (isLastChild) {
			setViewBackgroundWithoutResettingPadding(convertView, R.drawable.background_bottom);
		}
        
        
        //convertView.setBackgroundResource(R.drawable.background);
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    public long getGroupId(int listPosition) {
        return listPosition;
    }

    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.test_group, null);
        }
        
        
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.txtTitle);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
    
    public static void setViewBackgroundWithoutResettingPadding(final View v, final int backgroundResId) {
	    final int paddingBottom = v.getPaddingBottom(), paddingLeft = v.getPaddingLeft();
	    final int paddingRight = v.getPaddingRight(), paddingTop = v.getPaddingTop();
	    v.setBackgroundResource(backgroundResId);
	    v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}
}
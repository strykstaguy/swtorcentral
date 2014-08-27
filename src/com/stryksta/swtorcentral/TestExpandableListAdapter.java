package com.stryksta.swtorcentral;

import java.util.HashMap;
import java.util.List;

import com.stryksta.swtorcentral.util.SessionManager;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TestExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    SessionManager session;

    public TestExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        session = new SessionManager(context);
    }

    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.test_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        
        if (expandedListText.equals("Add Character")) {
        	ImageView expandedimgAction = (ImageView) convertView.findViewById(R.id.imgAction);
        	expandedimgAction.setImageResource(R.drawable.ic_action_add_dark);
        }
        
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

    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.test_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.userCharacter);
        TextView mUserStatus = (TextView) convertView.findViewById(R.id.userStatus);
        
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        if (session.isLoggedIn()) {
        	mUserStatus.setText("Logged in");
        	HashMap<String, String> user = session.getUserDetails();
            String mUserCharacter = user.get(SessionManager.KEY_NAME);
        	listTitleTextView.setText(mUserCharacter);
        } else {
        	listTitleTextView.setText(listTitle);
        	mUserStatus.setText("Logged out");
        }
        
        
        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
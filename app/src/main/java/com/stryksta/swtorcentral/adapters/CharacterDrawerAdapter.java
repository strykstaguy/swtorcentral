package com.stryksta.swtorcentral.adapters;

import java.util.HashMap;
import java.util.List;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.util.SessionManager;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterDrawerAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    SessionManager session;

    public CharacterDrawerAdapter(Context context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        session = new SessionManager(context);
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
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drawer_character_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        ImageView expandedimgAction = (ImageView) convertView.findViewById(R.id.imgAction);
        
        expandedListTextView.setText(expandedListText);
        
        if (expandedListText.equals("Add Character")) {
        	expandedimgAction.setImageResource(R.drawable.ic_action_add);
        } else if (expandedListText.equals("Logout")) {
        	expandedimgAction.setImageResource(R.drawable.ic_action_logout);
        } else {
        	expandedimgAction.setImageResource(android.R.color.transparent);
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

    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drawer_character_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.userCharacter);
        TextView mUserStatus = (TextView) convertView.findViewById(R.id.userStatus);
        ImageView imgExpandCollapse = (ImageView) convertView.findViewById(R.id.imgExpandCollapse);
        ImageView imgCharacterImage = (ImageView) convertView.findViewById(R.id.imgClassIcon);

        // check if GroupView is expanded and set imageview for expand/collapse-action
        if(isExpanded){
            imgExpandCollapse.setImageResource(R.drawable.ic_action_indicator_up);
        }
        else{
            imgExpandCollapse.setImageResource(R.drawable.ic_action_indicator_down);
        }
        
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        if (session.isLoggedIn()) {
        	mUserStatus.setText("Logged in");
        	HashMap<String, String> user = session.getUserDetails();
            String mUserCharacter = user.get(SessionManager.KEY_NAME);
            int mCharacterImage = context.getResources().getIdentifier(user.get(SessionManager.KEY_IMAGE), "drawable", context.getPackageName());
            
            imgCharacterImage.setImageResource(mCharacterImage);
        	listTitleTextView.setText(mUserCharacter);
        } else {
        	imgCharacterImage.setImageResource(R.drawable.ic_action_user);
        	listTitleTextView.setText("None");
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
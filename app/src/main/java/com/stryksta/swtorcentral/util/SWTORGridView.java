package com.stryksta.swtorcentral.util;

import com.stryksta.swtorcentral.adapters.AchievementAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Custom subclass of grid view to measure all view cells
 * in order to determine the max height of the row
 * 
 * @author Chase Colburn
 */
public class SWTORGridView extends GridView {
	
    private ListAdapter mAdapter;

	public SWTORGridView(Context context) {
        super(context);
    }

    public SWTORGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SWTORGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    @Override
	public void setAdapter(ListAdapter adapter) {
		this.mAdapter = adapter;

		super.setAdapter(adapter);
		super.setAdapter(this.mAdapter);
	}
    
    @Override
	public ListAdapter getAdapter() {
		return this.mAdapter;
	}
    
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed) {
        	AchievementAdapter achievementAdapter = (AchievementAdapter)getAdapter();
            int numColumns = 2;
            
            if(achievementAdapter != null) {
            	GridViewItemLayout.initItemLayout(numColumns, achievementAdapter.getCount());
            }
            
            if(numColumns > 1) {
                int columnWidth = getMeasuredWidth() / numColumns;
                achievementAdapter.measureItems(columnWidth);
            }
        }
        super.onLayout(changed, l, t, r, b);
    }
    
}

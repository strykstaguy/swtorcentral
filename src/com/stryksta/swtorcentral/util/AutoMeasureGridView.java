package com.stryksta.swtorcentral.util;

import com.stryksta.swtorcentral.TutorialAdapter;

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
public class AutoMeasureGridView extends GridView {
	
    private ListAdapter mAdapter;

	public AutoMeasureGridView(Context context) {
        super(context);
    }

    public AutoMeasureGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoMeasureGridView(Context context, AttributeSet attrs, int defStyle) {
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
        	//TutorialAdapter adapter = (TutorialAdapter)getAdapter();
            int numColumns = getNumColumns();
            
            if(getAdapter() != null) {
            	GridViewItemLayout.initItemLayout(numColumns, getAdapter().getCount());
            } else {
            	  //do another thing
            }
            
            
            
            
            /*if(numColumns > 1) {
                int columnWidth = getMeasuredWidth() / numColumns;
                adapter.measureItems(columnWidth);
            }*/
        }
        super.onLayout(changed, l, t, r, b);
    }
    
   
}

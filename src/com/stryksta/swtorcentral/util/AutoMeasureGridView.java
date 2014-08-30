package com.stryksta.swtorcentral.util;

import com.stryksta.swtorcentral.TutorialAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Custom subclass of grid view to measure all view cells
 * in order to determine the max height of the row
 * 
 * @author Chase Colburn
 */
public class AutoMeasureGridView extends GridView {
	
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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed) {
        	TutorialAdapter adapter = (TutorialAdapter)getAdapter();
            int numColumns = 2;
            GridViewItemLayout.initItemLayout(numColumns, adapter.getCount());

            if(numColumns > 1) {
                int columnWidth = getMeasuredWidth() / numColumns;
                adapter.measureItems(columnWidth);
            }
        }
        super.onLayout(changed, l, t, r, b);
    }
    
   
}

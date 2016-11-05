package com.stryksta.swtorcentral.ui.views.chipcloud;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author RAW
 */
public class FlowLayout extends ViewGroup {

    private int line_height;
    private int mRows = 2;

    public static class LayoutParams extends ViewGroup.LayoutParams {

        public final int horizontal_spacing;
        public final int vertical_spacing;

        /**
         * @param horizontal_spacing Pixels between items, horizontally
         * @param vertical_spacing Pixels between items, vertically
         */
        public LayoutParams(int horizontal_spacing, int vertical_spacing) {
            super(0, 0);
            this.horizontal_spacing = horizontal_spacing;
            this.vertical_spacing = vertical_spacing;
        }
    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);

        final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        final int count = getChildCount();
        int line_height = 0;
        int perRowCount = (int) Math.ceil(count / mRows);
        int finalWidthRow1 = width;
        int finalWidthRow2 = width;


        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();

        int childHeightMeasureSpec;
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        } else {
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                        childHeightMeasureSpec);
                final int childw = child.getMeasuredWidth();
                line_height = Math.max(line_height, child.getMeasuredHeight() + lp.vertical_spacing);

                if (i == perRowCount) {
                    int xposRow1 = xpos;
                    xpos = getPaddingLeft();
                    ypos += line_height;
                    finalWidthRow1 = xposRow1;
                    //Log.d("SWTORCentral", "xPOS is " + xpos2);
                }

                xpos += childw + lp.horizontal_spacing;
                finalWidthRow2 = xpos;
                //Log.d("SWTORCentral", "xPOS is " + xpos);
            }
        }
        this.line_height = line_height;

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED || (MeasureSpec.getMode(
                heightMeasureSpec) == MeasureSpec.AT_MOST && ypos + line_height < height)) {
            height = ypos + line_height;
        }

        int longestRow = Math.max(finalWidthRow1, finalWidthRow2);
        //Log.d("SWTORCentral", "Row 1 is " + finalWidthRow1);
        //Log.d("SWTORCentral", "Row 2 is " + finalWidthRow2);

        setMeasuredDimension(longestRow, height);
    }

    @Override protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(dpToPx(7), dpToPx(7));
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int perRowCount = (int) Math.ceil(count / mRows);
        final int width = r - l;
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();



                if (i == perRowCount) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                    //Log.d("SWTORCentral", "Count " + perRowCount);
                }

                /*
                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                }
                */
                child.layout(xpos, ypos, xpos + childw, ypos + childh);
                xpos += childw + lp.horizontal_spacing;
            }
        }
    }
}

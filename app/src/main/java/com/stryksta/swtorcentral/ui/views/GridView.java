package com.stryksta.swtorcentral.ui.views;

import android.view.ViewGroup;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.stryksta.swtorcentral.R;

/**
 * A GridLayout positions its children in a static grid, defined by a fixed number of rows
 * and columns. The size of the rows and columns is dynamically computed depending on the
 * size of the GridLayout itself. As a result, GridLayout children's layout parameters
 * are ignored.
 *
 * The number of rows and columns are specified in XML using the attributes android:numRows
 * and android:numColumns.
 *
 * The GridLayout cannot be used when its size is unspecified.
 *
 * @attr ref com.google.android.photostream.R.styleable#GridLayout_numColumns
 * @attr ref com.google.android.photostream.R.styleable#GridLayout_numRows
 */
public class GridView extends ViewGroup {
    private int mNumColumns;
    private int mNumRows;

    private int mColumnWidth;
    private int mRowHeight;

    public GridView(Context context) {
        this(context, null);
    }

    public GridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GridLayout, defStyle, 0);

        //mNumColumns = a.getInt(R.styleable.GridLayout_numColumns, 1);
        //mNumRows = a.getInt(R.styleable.GridLayout_numRows, 1);

        mNumColumns = 1;
        mNumRows = 1;

        //a.recycle();
    }

    @Override
    protected void attachLayoutAnimationParameters(View child,
                                                   ViewGroup.LayoutParams params, int index, int count) {

        GridLayoutAnimationController.AnimationParameters animationParams =
                (GridLayoutAnimationController.AnimationParameters)
                        params.layoutAnimationParameters;

        if (animationParams == null) {
            animationParams = new GridLayoutAnimationController.AnimationParameters();
            params.layoutAnimationParameters = animationParams;
        }

        animationParams.count = count;
        animationParams.index = index;
        animationParams.columnsCount = mNumColumns;
        animationParams.rowsCount = mNumRows;

        animationParams.column = index % mNumColumns;
        animationParams.row = index / mNumColumns;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSpecSize =  MeasureSpec.getSize(widthMeasureSpec);

        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSpecSize =  MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.UNSPECIFIED || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            //throw new RuntimeException("GridLayout cannot have UNSPECIFIED dimensions");
        }

        final int width = widthSpecSize - getPaddingLeft() - getPaddingRight();
        final int height = heightSpecSize - getPaddingTop() - getPaddingBottom();

        final int columnWidth = mColumnWidth = width / mNumColumns;
        final int rowHeight = mRowHeight = height / mNumRows;

        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            int childWidthSpec = MeasureSpec.makeMeasureSpec(columnWidth, MeasureSpec.EXACTLY);
            int childheightSpec = MeasureSpec.makeMeasureSpec(rowHeight, MeasureSpec.EXACTLY);

            child.measure(childWidthSpec, childheightSpec);
        }

        setMeasuredDimension(widthSpecSize, heightSpecSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int columns = mNumColumns;
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int columnWidth = mColumnWidth;
        final int rowHeight = mRowHeight;
        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int column = i % columns;
                final int row = i / columns;

                int childLeft = paddingLeft + column * columnWidth;
                int childTop = paddingTop + row * rowHeight;

                child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(),
                        childTop + child.getMeasuredHeight());
            }
        }
    }
}
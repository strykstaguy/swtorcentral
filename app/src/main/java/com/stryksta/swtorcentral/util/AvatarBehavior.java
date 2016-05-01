package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.stryksta.swtorcentral.R;

public class AvatarBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private final String COLLAPSE_PERCENT = "collapsePercent ";

    private int toolBarHeight = 0;
    private int statusBarHeight = 0;
    private float collapsePercent = 0.0f;

    private int startPosition = 0;
    private int endPosition = 0;

    private int childSize;

    private int scrolled;

    private int childY;

    public AvatarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        toolBarHeight = context.getResources().getDimensionPixelSize(R.dimen.tool_bar_height);
        statusBarHeight = context.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        NestedScrollView nestedScrollView = (NestedScrollView) dependency;

        if (startPosition == 0) {
            startPosition = nestedScrollView.getTop();
            childSize = child.getWidth();
            child.setX(parent.getWidth() / 2 - childSize / 2);
            childY = parent.getChildAt(0).getHeight() / 2 - childSize / 2;
            child.setTranslationY(childY);

        }

        if (endPosition == 0) {
            endPosition = toolBarHeight + toolBarHeight / 2;
        }
        scrolled = startPosition - nestedScrollView.getTop();
        collapsePercent = (float) scrolled / (float) endPosition;


        float scale = 1f - collapsePercent;
        if (collapsePercent >= 0.5f) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            scale = scale + 0.5f;
            if (scale >= 0.8f) {
                params.width = (int) (childSize * scale);
                params.height = (int) (childSize * scale);
                params.leftMargin = (childSize - params.width) / 2;
                child.requestLayout();
            }
        }

        Log.d(AvatarBehavior.class.getSimpleName(), COLLAPSE_PERCENT + collapsePercent + " | top = " + nestedScrollView.getTop() + " | scrolled = " + scrolled + " | endPosition = " + endPosition);
        return true;
    }
}
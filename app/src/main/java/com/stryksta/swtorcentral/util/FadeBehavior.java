package com.stryksta.swtorcentral.util;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ImageView;

public class FadeBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int startPos = 0;
    private int finalPos = 0;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        shouldInitProperties(child, dependency);

        float fullDistance = startPos - finalPos;
        float currDistance = child.getY() + child.getHeight() / 2f - finalPos;

        float x = currDistance * 100 / fullDistance;

        child.setAlpha(x / 100f);

        return true;
    }

    private void shouldInitProperties(View child, View dependency) {

        if(startPos == 0) {
            startPos = (int) child.getY() + child.getHeight() / 2;
        }

        if(finalPos == 0 ){
            finalPos = dependency.getHeight() / 2;
        }
    }
}
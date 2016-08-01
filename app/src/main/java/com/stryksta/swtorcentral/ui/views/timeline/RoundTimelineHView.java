package com.stryksta.swtorcentral.ui.views.timeline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by Bernat on 10/04/2014.
 */
public class RoundTimelineHView extends TimelineHView {
    public RoundTimelineHView(Context context) {
        super(context);
    }

    public RoundTimelineHView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundTimelineHView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void drawStart(Canvas canvas, Paint firstPaint, float centerX, float centerY, float mStartSize) {
        canvas.drawCircle(centerX, centerY, mStartSize + 5, firstPaint);
    }

    @Override
    public void drawMiddle(Canvas canvas, Paint middlePaint, float centerX, float centerY, float mMiddleSize) {
        canvas.drawCircle(centerX, centerY, mMiddleSize + 5, middlePaint);
    }

    @Override
    public void drawEnd(Canvas canvas, Paint lastPaint, float centerX, float centerY, float mLastSize) {
        canvas.drawCircle(centerX, centerY, mLastSize+ 5, lastPaint);
    }
}

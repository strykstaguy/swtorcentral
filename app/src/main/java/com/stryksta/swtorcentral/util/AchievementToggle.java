package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AchievementToggle extends View {

    // setup initial color
    private final int paintColor = Color.BLACK;
    private Paint mPaint;
    private int mCanvasSize;

    //region Constructor & Init Method
    public AchievementToggle(final Context context) {
        this(context, null);
    }

    public AchievementToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AchievementToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    // Setup paint with color and stroke styles
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint();
        mPaint.setColor(paintColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!isInEditMode()) {
            mCanvasSize = canvas.getWidth();
            if (canvas.getHeight() < mCanvasSize) {
                mCanvasSize = canvas.getHeight();
            }
        }

        // circleCenter is the x or y of the view's center
        // radius is the radius in pixels of the cirle to be drawn
        // paint contains the shader that will texture the shape
        int circleCenter = (int) (mCanvasSize / 2);
        // Draw Border
        canvas.drawCircle(circleCenter, circleCenter, circleCenter, mPaint);

        //canvas.drawCircle(50, 50, 20, mPaint);
    }

}
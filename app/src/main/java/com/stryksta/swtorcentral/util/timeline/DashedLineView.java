package com.stryksta.swtorcentral.util.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class DashedLineView extends View {

    private Paint mPaint;
    private Path mPath;
    protected PathEffect mEffects;

    private int mDashHeight = 7;
    private float mDashWidth = 20;
    private float mDashGap = 15;
    private int mDashColor = Color.rgb(117,117,117);

    public DashedLineView(Context context) {
        super(context, null);
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        if(!isInEditMode()){
            setAttributes(context, attrs);
        }
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(!isInEditMode()){
            setAttributes(context, attrs);
        }
        init();

    }

    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.dashedLineView);

        try {
            mDashWidth = array.getFloat(R.styleable.dashedLineView_dashWidth, mDashWidth);
            mDashGap = array.getFloat(R.styleable.dashedLineView_dashGap, mDashGap);
            mDashColor = array.getColor(R.styleable.dashedLineView_dashColor, mDashColor);
        } finally {
            array.recycle();
        }


    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mDashHeight);
        mPaint.setColor(mDashColor);
        mPath = new Path();
        mEffects = new DashPathEffect(new float[]{mDashWidth, mDashGap}, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setPathEffect(mEffects);
        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();

        int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int contentHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        //int startX = contentHeight - mDashHeight / 2;
        //int endX = contentHeight + mDashHeight / 2;

        mPath.moveTo(left, 10);
        mPath.lineTo(right, 10);
        canvas.drawPath(mPath, mPaint);


        //demo
        /*
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        if (height <= width) {
            // horizontal
            mPath.moveTo(0, (float) (height / 2.0));
            mPath.lineTo(width, (float) (height / 2.0));
            canvas.drawPath(mPath, mPaint);
        } else {
            // vertical
            mPath.moveTo((float) (width / 2.0), 0);
            mPath.lineTo((float) (width / 2.0), height);
            canvas.drawPath(mPath, mPaint);
        }
*/
            //canvas.drawLine(startX, startY, stopX, stopY, mPaint);
        //canvas.drawLine(left, contentHeight / 2, right, contentHeight / 2, mPaint);
    }
}

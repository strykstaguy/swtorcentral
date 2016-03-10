package com.stryksta.swtorcentral.util.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class DashedLineView extends View {

    private static final int DEFAULT_DASH_WIDTH = 5;
    private static final int DEFAULT_DASH_GAP = 2;

    private Paint mPaint;
    private Path mPath;
    protected PathEffect mEffects;

    private int mDashHeight = 1;
    private int mDashWidth;
    private int mDashGap;
    private int mDashColor;

    public DashedLineView(Context context) {
        super(context);
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs);
        init();
    }

    public DashedLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttributes(context, attrs);
        init();
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.dashedLineView);
        mDashWidth = array.getDimensionPixelSize(R.styleable.dashedLineView_dashWidth, DEFAULT_DASH_WIDTH);
        mDashGap = array.getDimensionPixelSize(R.styleable.dashedLineView_dashGap, DEFAULT_DASH_GAP);
        mDashColor = array.getColor(R.styleable.dashedLineView_dashColor, ContextCompat.getColor(context, R.color.black));
        array.recycle();
    }

    private void init() {
        isInEditMode();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
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

        mPath.moveTo(left, 0);
        mPath.lineTo(right, 0);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, mDashHeight);
    }
}

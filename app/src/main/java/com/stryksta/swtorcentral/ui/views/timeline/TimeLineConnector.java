package com.stryksta.swtorcentral.ui.views.timeline;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class TimeLineConnector extends View implements View.OnClickListener {

    private static int DEFAULT_LINE_COLOR = Color.BLACK;
    private static int DEFAULT_ORIENTATION = 0;
    private static float DEFAULT_LINE_SIZE = 5f;
    private int mLineColor;
    private int mOrientation;
    private Paint mPaint;
    private float mLineWidth;

    private int rightStartX;
    private int rightStartY;
    private int rightStopX;
    private int rightStopY;

    private int leftStartX;
    private int leftStartY;
    private int leftStopX;
    private int leftStopY;

    private int bottomStartX;
    private int bottomStartY;
    private int bottomStopX;
    private int bottomStopY;

    public TimeLineConnector(Context context) {
        super(context);
        init(null, 0);
    }

    public TimeLineConnector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TimeLineConnector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TimelineConnector, defStyle, 0);
        mLineColor = a.getColor(R.styleable.TimelineConnector_timeline_lineColor, DEFAULT_LINE_COLOR);
        mLineWidth = a.getDimension(R.styleable.TimelineConnector_timeline_lineSize, DEFAULT_LINE_SIZE);
        mOrientation = a.getInt(R.styleable.TimelineConnector_timeline_orientation, DEFAULT_ORIENTATION);

        a.recycle();

        //Background Paint
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        //mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setColor(mLineColor);

        setOnClickListener(this);
    }

    private void invalidatePaints(){
        mPaint.setColor(mLineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /*
        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);
        */
        //setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int contentHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        if (mOrientation == 0) {
            //Direction Up
            //Right Line
            rightStartX = getWidth() - getPaddingRight() - ((int) mLineWidth / 2) - 1;
            rightStartY = getPaddingTop();
            rightStopX = getWidth() - getPaddingRight() - ((int) mLineWidth / 2) - 1;
            rightStopY = contentHeight;

            canvas.drawLine(rightStartX, rightStartY, rightStopX, rightStopY, mPaint);

            //Left Line
            leftStartX = (Math.round(mLineWidth) / 2) + getPaddingLeft();
            leftStartY = getPaddingTop();
            leftStopX = leftStartX;
            leftStopY = contentHeight;

            //Bottom Line
            canvas.drawLine(leftStartX, leftStartY, leftStopX, leftStopY, mPaint);

            bottomStartX = getPaddingLeft() - 1;
            bottomStartY = contentHeight - ((int) mLineWidth / 2);
            bottomStopX = rightStopX;
            bottomStopY = bottomStartY;


            canvas.drawLine(bottomStartX, bottomStartY, bottomStopX, bottomStopY, mPaint);

        } else {
            //Direction Down
            //Right Line
            rightStartX = getWidth() - getPaddingRight() - ((int) mLineWidth / 2) - 1;
            rightStartY = getHeight() - getPaddingBottom();
            rightStopX = getWidth() - getPaddingRight() - ((int) mLineWidth / 2) - 1;
            rightStopY = contentHeight;

            canvas.drawLine(rightStartX, rightStartY, rightStopX, rightStopY, mPaint);

            //Left Line
            leftStartX = (Math.round(mLineWidth) / 2) + getPaddingLeft();
            leftStartY = getPaddingTop();
            leftStopX = leftStartX;
            leftStopY = contentHeight;

            //Bottom Line
            canvas.drawLine(leftStartX, leftStartY, leftStopX, leftStopY, mPaint);

            bottomStartX = getPaddingLeft() - 1;
            bottomStartY = contentHeight - ((int) mLineWidth / 2);
            bottomStopX = rightStopX;
            bottomStopY = bottomStartY;


            canvas.drawLine(bottomStartX, bottomStartY, bottomStopX, bottomStopY, mPaint);

        }
    }
    /**
     * Sets the view's onclick.
     */
    @Override
    public void onClick(View v) {

    }
}
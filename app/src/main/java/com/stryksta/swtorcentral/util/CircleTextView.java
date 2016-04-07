package com.stryksta.swtorcentral.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class CircleTextView extends View implements View.OnClickListener {

    private TextPaint textPaint;
    private Paint circlePaint;
    private Bitmap circleBitmap;
    private BitmapFactory bitmapFactory;
    private Rect bitmapRect;
    private int myColor;

    private int viewWidth, viewHeight;

    // Views values
    private int circleRadius;
    private int circleX, circleY;

    private boolean mIsSelected = false;

    private String mText = "Text";
    private float textSize = 20;
    private int textColor = Color.WHITE;
    private int backgroundColor = Color.rgb(63,159,224);
    private Bitmap mCheck;
    private Paint paintCheck;

    private int textX;
    private int textY;

    private int checkSize;

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs);
        initTools();
    }

    public CircleTextView(Context context) {
        super(context);
        initTools();
    }

    private void initTools() {

        setOnClickListener(this);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(backgroundColor);
        circlePaint.setStyle(Paint.Style.FILL);

        paintCheck = new Paint();
        paintCheck.setAntiAlias(true);

        checkSize = (int) getContext().getResources().getDimension(R.dimen.circle_view_check);
        mCheck = getResizedBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.ic_check), checkSize, checkSize);
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);

        try {
            mText = array.getString(R.styleable.CircleTextView_ctv_text);
            setText(mText);

            textSize = array.getDimension(R.styleable.CircleTextView_ctv_textSize, textSize);
            textColor = array.getColor(R.styleable.CircleTextView_ctv_textColor, textColor);
            backgroundColor = array.getColor(R.styleable.CircleTextView_ctv_backgroundColor, backgroundColor);
            setChecked(array.getBoolean(R.styleable.CircleTextView_ctv_selected, false));
        } finally {
            array.recycle();
        }


    }
    public void setText(String mText) {
        this.mText = mText;
        requestLayout();
        invalidate();
    }

    @Override
    public void onClick(View v) {
        if (mIsSelected == true) {
            setChecked(false);
        } else {
            setChecked(true);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewidth = View.resolveSize(0, widthMeasureSpec);
        int viewHeight = View.resolveSize(0, heightMeasureSpec);
        int minValue = Math.min(viewidth, viewHeight);
        setMeasuredDimension(minValue, minValue);

    }

    public void setChecked(boolean checked) {
        boolean oldChecked = mIsSelected;
        mIsSelected = checked;
        requestLayout();
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (mIsSelected) {
            canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);
            canvas.drawBitmap(mCheck, circleX - checkSize, circleY - checkSize, paintCheck);
        } else if (!mIsSelected) {
            canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);
            canvas.drawText(mText, textX, textY, textPaint);
        }

    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        circleRadius = viewWidth / 2;
        circleX = viewWidth / 2;
        circleY = viewHeight / 2;

        int halfRadius = circleRadius / 2;

        bitmapRect = new Rect(halfRadius, halfRadius, circleX + halfRadius, circleX);

        textSize = textPaint.measureText(mText);
        textX = circleX - (int) textSize / 2;
        textY = circleY + 20;

    }

}
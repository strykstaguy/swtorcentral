package com.stryksta.swtorcentral.ui.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;

public class TextViewLabel extends TextView implements View.OnClickListener{
    private float cornerRadius = 0;
    private RectF labelBounds = new RectF();
    private Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean mIsSelected;
    private int mLabelColor;
    private int mLabelColorSelected;

    public TextViewLabel(Context context) {
        super(context);
        init(context, null);
    }

    public TextViewLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextViewLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        DisplayMetrics dm = getResources().getDisplayMetrics();

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextViewLabel);
            mLabelColor = ta.getColor(R.styleable.TextViewLabel_labelColor, ContextCompat.getColor(context, R.color.swtor_blue));
            mLabelColorSelected = ta.getColor(R.styleable.TextViewLabel_labelColorSelected, ContextCompat.getColor(context, R.color.swtor_blue));
            cornerRadius = ta.getDimension(R.styleable.TextViewLabel_labelCornerRadius, 0);
        }

        // Default padding
        if (getPaddingLeft() == 0
                || getPaddingRight() == 0
                || getPaddingBottom() == 0
                || getPaddingTop() == 0) {
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
            setPadding(padding, padding, padding, padding);
        }

        setOnClickListener(this);
        setSelected(false);
        setLabelColor(mLabelColor);
    }

    /**
     * Sets the label color.
     * @param color The color of the label
     */
    public void setLabelColor(int color) {
        labelPaint.setColor(color);
    }

    /**
     * Sets the corner radius on the label. By default the corner radius is 0
     * @param cornerRadius The radius of each corner
     */
    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * Sets if its selected or not
     */
    public void setSelected(boolean checked) {
        mIsSelected = checked;
        if (mIsSelected) {
            setLabelColor(mLabelColorSelected);
        } else if (!mIsSelected) {
            setLabelColor(mLabelColor);
        }

        requestLayout();
        invalidate();
    }
    /**
     * gets if its selected or not
     */
    public boolean getSelected() {
        return mIsSelected;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        labelBounds.left = 0;
        labelBounds.right = getMeasuredWidth();
        labelBounds.top = 0;
        labelBounds.bottom = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLabel(canvas, cornerRadius);
        super.onDraw(canvas);
    }

    private void drawLabel(Canvas canvas, float cornerRadius) {
        canvas.drawRoundRect(labelBounds, cornerRadius, cornerRadius, labelPaint);
    }

    /**
     * Sets the view's onclick.
     */
    @Override
    public void onClick(View v) {
        if (mIsSelected) {
            setSelected(false);
        } else {
            setSelected(true);
        }
    }
}
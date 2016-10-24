package com.stryksta.swtorcentral.ui.views.chipcloud;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipCloud;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipListener;

public class Chip extends TextView implements View.OnClickListener{
    private float cornerRadius = Integer.MIN_VALUE;
    private RectF labelBounds = new RectF();
    private Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //Defaults
    private static int DEFAULT_LABEL_COLOR = Color.GRAY;
    private static int DEFAULT_LABEL_SELECTED_COLOR = Color.BLACK;
    private static int DEFAULT_FONT_COLOR = Color.WHITE;
    private static int DEFAULT_FONT_SELECTED_COLOR = Color.WHITE;

    private int mLabelColor = DEFAULT_LABEL_COLOR;
    private int mLabelColorSelected = DEFAULT_LABEL_SELECTED_COLOR;

    private int mFontColor = DEFAULT_FONT_COLOR;
    private int mFontColorSelected = DEFAULT_FONT_SELECTED_COLOR;

    private String mText;

    private int index = -1;
    private boolean mIsSelected = false;
    private ChipListener listener = null;
    private boolean isLocked = false;
    private ChipCloud.Mode mode;

    public Chip(Context context) {
        super(context);
        init(context, null);
    }

    public Chip(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Chip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setChipListener(ChipListener listener) {
        this.listener = listener;
    }

    private void init(Context context, AttributeSet attrs) {
        DisplayMetrics dm = getResources().getDisplayMetrics();

        mLabelColor = ContextCompat.getColor(context, R.color.swtor_blue);
        mLabelColorSelected = ContextCompat.getColor(context, R.color.swtor_blue_dark);

        // Default padding
        if (getPaddingLeft() == 0
                || getPaddingRight() == 0
                || getPaddingBottom() == 0
                || getPaddingTop() == 0) {
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
            setPadding(padding, padding, padding, padding);
        }

        setOnClickListener(this);
    }

    public void initChip(Context context, int index, String mText, int mLabelColorSelected, int mFontColorSelected, int mLabelColor, int mFontColor, ChipCloud.Mode mode) {

        this.index = index;
        this.mFontColor = mFontColor;
        this.mFontColorSelected = mFontColorSelected;
        this.mLabelColor = mLabelColor;
        this.mLabelColorSelected = mLabelColorSelected;
        this.mode = mode;
        this.mText = mText;

        DisplayMetrics dm = getResources().getDisplayMetrics();

        // Default padding
        if (getPaddingLeft() == 0
                || getPaddingRight() == 0
                || getPaddingBottom() == 0
                || getPaddingTop() == 0) {
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
            setPadding(padding, padding, padding, padding);
        }

        setText(mText);
        setTextColor(mFontColor);
        setSelected(false);
    }

    /**
     * Sets the label color.
     * @param color The color of the label
     */
    public void setLabelColor(int color) {
        labelPaint.setColor(color);
    }

    /**
     * Sets the corner radius on the label. By default the corner radius is 1/20th of the
     * labels width.
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

    /**
     * Return Text
     */
    public String getText() {
         return mText;
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
        if (cornerRadius == Integer.MIN_VALUE) {
            drawLabel(canvas, getWidth()/20);
        } else {
            drawLabel(canvas, cornerRadius);
        }
        super.onDraw(canvas);
    }

    private void drawLabel(Canvas canvas, float cornerRadius) {
        canvas.drawRoundRect(labelBounds, cornerRadius, cornerRadius, labelPaint);
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Sets the view's onclick.
     */
    @Override
    public void onClick(View v) {
        if (mode != ChipCloud.Mode.NONE)
            if (mIsSelected && !isLocked) {
                //set as unselected
                setSelected(false);
                if (listener != null) {
                    listener.chipDeselected(index);
                }
            } else if (!mIsSelected) {
                //set as selected

                setSelected(true);
                if (listener != null) {
                    listener.chipSelected(index, mText);
                }
            }

        mIsSelected = !mIsSelected;
    }

    public static class ChipBuilder {
        private int index;
        private String mText;
        private int selectedColor;
        private int selectedFontColor;
        private int unselectedColor;
        private int unselectedFontColor;
        private int chipHeight;
        private float chipCornerRadius;

        private ChipListener chipListener;
        private ChipCloud.Mode mode;

        public ChipBuilder index(int index) {
            this.index = index;
            return this;
        }

        public ChipBuilder cornerRadius(float chipCornerRadius) {
            this.chipCornerRadius = chipCornerRadius;
            return this;
        }

        public ChipBuilder selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        public ChipBuilder selectedFontColor(int selectedFontColor) {
            this.selectedFontColor = selectedFontColor;
            return this;
        }

        public ChipBuilder unselectedColor(int unselectedColor) {
            this.unselectedColor = unselectedColor;
            return this;
        }

        public ChipBuilder unselectedFontColor(int unselectedFontColor) {
            this.unselectedFontColor = unselectedFontColor;
            return this;
        }

        public ChipBuilder label(String mText) {
            this.mText = mText;
            return this;
        }

        public ChipBuilder chipHeight(int chipHeight) {
            this.chipHeight = chipHeight;
            return this;
        }

        public ChipBuilder chipListener(ChipListener chipListener) {
            this.chipListener = chipListener;
            return this;
        }

        public ChipBuilder mode(ChipCloud.Mode mode) {
            this.mode = mode;
            return this;
        }

        public Chip build(Context context) {
            //Chip chip = (Chip) LayoutInflater.from(context).inflate(R.layout.chip, null);
            Chip chip = new Chip(context);
            chip.initChip(context, index, mText, selectedColor, selectedFontColor, unselectedColor, unselectedFontColor, mode);
            chip.setCornerRadius(chipCornerRadius);
            chip.setChipListener(chipListener);
            chip.setHeight(chipHeight);
            return chip;
        }
    }
}
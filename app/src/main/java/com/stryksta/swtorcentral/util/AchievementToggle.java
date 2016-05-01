package com.stryksta.swtorcentral.util;

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

public class AchievementToggle extends View implements View.OnClickListener {

    private static int DEFAULT_TITLE_COLOR = Color.WHITE;
    private static int DEFAULT_BACKGROUND_COLOR = Color.CYAN;
    private static final int DEFAULT_VIEW_SIZE = 96;
    private static float DEFAULT_TITLE_SIZE = 20f;
    private static String DEFAULT_TITLE = "20";
    private static Boolean DEFAULT_SELECTED = false;

    private int mTitleColor = DEFAULT_TITLE_COLOR;
    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private String mTitleText = DEFAULT_TITLE;
    private float mTitleSize = DEFAULT_TITLE_SIZE;

    private TextPaint mTitleTextPaint;
    private Paint mBackgroundPaint;
    private Bitmap mCheck;
    private Bitmap mCheckResized;
    private Paint mPaintCheck;
    private RectF mInnerRectF;
    private int mViewSize;
    private int mCheckSize;
    private boolean mIsSelected;

    private Typeface mFont = Typeface.defaultFromStyle(Typeface.NORMAL);

    public AchievementToggle(Context context) {
        super(context);
        init(null, 0);
    }

    public AchievementToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AchievementToggle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.AchievementToggle, defStyle, 0);

        if(a.hasValue(R.styleable.AchievementToggle_at_text)){
            mTitleText = a.getString(R.styleable.AchievementToggle_at_text);
            //setText(mText);
        }

        mTitleColor = a.getColor(R.styleable.AchievementToggle_at_textColor,DEFAULT_TITLE_COLOR);
        mBackgroundColor = a.getColor(R.styleable.AchievementToggle_at_backgroundColor,DEFAULT_BACKGROUND_COLOR);

        mCheckSize = (int) getContext().getResources().getDimension(R.dimen.circle_view_check);
        setChecked(a.getBoolean(R.styleable.AchievementToggle_at_selected, DEFAULT_SELECTED));

        mCheck = getBitmap(getContext(), R.drawable.ic_check);
        //Drawable drawFace = ContextCompat.getDrawable(getContext(), R.drawable.ic_check);
        mCheckResized = getResizedBitmap(mCheck, mCheckSize, mCheckSize);

        mTitleSize = a.getDimension(R.styleable.AchievementToggle_at_textSize,DEFAULT_TITLE_SIZE);
        a.recycle();

        //Title TextPaint
        mTitleTextPaint = new TextPaint();
        mTitleTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextAlign(Paint.Align.CENTER);
        mTitleTextPaint.setLinearText(true);
        mTitleTextPaint.setColor(mTitleColor);
        mTitleTextPaint.setTextSize(mTitleSize);

        //Background Paint
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundColor);

        //Check Paint
        mPaintCheck = new Paint();
        mPaintCheck.setAntiAlias(true);

        //Set Check to same color as text
        int iColor = mTitleColor;
        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
        mPaintCheck.setColorFilter(colorFilter);

        mInnerRectF = new RectF();

        setOnClickListener(this);
    }

    private void invalidateTextPaints(){
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextSize(mTitleSize);
    }

    private void invalidatePaints(){
        mBackgroundPaint.setColor(mBackgroundColor);
    }

    public boolean isChecked() {
        return mIsSelected;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mInnerRectF.set(0, 0, mViewSize, mViewSize);
        mInnerRectF.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);

        float centerX = mInnerRectF.centerX();
        float centerY = mInnerRectF.centerY();

        int xPos = (int) centerX;
        int yPos = (int) (centerY - (mTitleTextPaint.descent() + mTitleTextPaint.ascent()) / 2);

        int checkXPos = (int) centerX - (mCheckSize / 2);
        int checkYPos = (int) centerY - (mCheckSize / 2);

        if (mIsSelected) {
            canvas.drawOval(mInnerRectF, mBackgroundPaint);
            canvas.drawBitmap(mCheckResized, checkXPos, checkYPos, mPaintCheck);
        } else if (!mIsSelected) {
            canvas.drawOval(mInnerRectF, mBackgroundPaint);
            canvas.drawText(mTitleText,
                    xPos,
                    yPos,
                    mTitleTextPaint);
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

    /**
     * Gets the title string attribute value.
     * @return The title string attribute value.
     */
    public String getTitleText() {
        return mTitleText;
    }

    /**
     * Sets the view's title string attribute value.
     * @param title The example string attribute value to use.
     */
    public void setTitleText(String title) {
        mTitleText = title;
        invalidate();
    }

    /**
     * Gets the background color attribute value.
     * @return The background color attribute value.
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Sets the view's background color attribute value.
     * @param backgroundColor The background color attribute value to use.
     */
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        invalidatePaints();
    }

    /**
     * Gets the title size dimension attribute value.
     * @return The title size dimension attribute value.
     */
    public float getTitleSize() {
        return mTitleSize;
    }

    /**
     * Sets the view's title size dimension attribute value.
     * @param titleSize The title size dimension attribute value to use.
     */
    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        invalidateTextPaints();
    }

    /**
     * Sets the view's title typeface.
     * @param font The typeface to be used for the text.
     */
    public void setTextTypeface(Typeface font){
        this.mFont = font;
        invalidateTextPaints();
    }

    /**
     * Sets if its selected or not
     */
    public void setChecked(boolean checked) {
        mIsSelected = checked;
        requestLayout();
        invalidate();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    /**
     * Sets the view's onclick.
     */
    @Override
    public void onClick(View v) {
        if (mIsSelected) {
            setChecked(false);
        } else {
            setChecked(true);
        }
    }
}
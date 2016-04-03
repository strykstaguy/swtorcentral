package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class CircleTextView extends View {

    private TextPaint textPaint;
    private Paint circlePaint;
    private Bitmap circleBitmap;
    private BitmapFactory bitmapFactory;
    private Rect bitmapRect;
    private int myColor;

    private int viewWidth, viewHeight;
    private String text = "50";

    // Views values
    private int circleRadius;
    private int circleX, circleY;

    private float textSize;
    private int textX;
    private int textY;

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTools();
    }

    public CircleTextView(Context context) {
        super(context);
        initTools();
        //myColor = ContextCompat.getColor(context, R.color.swtor_blue);
    }

    private void initTools() {
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);

        float textSize =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getResources().getDimension(R.dimen.tex_size_normal), getResources().getDisplayMetrics());
        textPaint.setTextSize(textSize);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.rgb(63,159,224));
        circlePaint.setStyle(Paint.Style.FILL);

        circleBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
    }

    public void setText(String text) {
        this.text = text;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewidth = View.resolveSize(0, widthMeasureSpec);
        int viewHeight = View.resolveSize(0, heightMeasureSpec);
        int minValue = Math.min(viewidth, viewHeight);
        setMeasuredDimension(minValue, minValue);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);
        //canvas.drawBitmap(circleBitmap, null, bitmapRect, null);
        canvas.drawText(text, textX, textY, textPaint);
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

        bitmapRect = new Rect(halfRadius, halfRadius, circleX + halfRadius,
                circleX);

        textSize = textPaint.measureText(text);
        textX = circleX - (int) textSize / 2;
        textY = circleY + 20;

    }

}
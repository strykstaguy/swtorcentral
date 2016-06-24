package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.View;

import com.stryksta.swtorcentral.R;

public class Triangle extends View {

    private Path path;
    private Paint shapePaint;
    private int fillColor;
    private int type;

    public Triangle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.TriangleShape, 0, 0);
        //type = attr.getInt(R.styleable.TriangleShape_triangleType, Type.CENTER_TOP);
        fillColor = attr.getColor(R.styleable.TriangleShape_triangleColor, Color.WHITE);
        path = new Path();
        shapePaint = new Paint();
        attr.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();


        path.moveTo(0, 0);
        path.lineTo(getWidth() / 2, getHeight());
        path.lineTo(getWidth(), 0);
        path.close();

        shapePaint.setStrokeJoin(Paint.Join.ROUND);
        shapePaint.setAntiAlias(true);
        shapePaint.setColor(getFillColor());
        shapePaint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path, shapePaint);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(@ColorRes int fillColor) {
        this.fillColor = fillColor;
    }
}
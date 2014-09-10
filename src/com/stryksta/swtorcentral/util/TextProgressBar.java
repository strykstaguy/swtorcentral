package com.stryksta.swtorcentral.util;

import java.util.LinkedList;
import java.util.List;

import com.stryksta.swtorcentral.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class TextProgressBar extends ProgressBar {

    private String mText = "";
    private int textColor = Color.BLACK;
    private float textSize = 15;

    public TextProgressBar(Context context) {
        super(context);
    }

    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(attrs);
    }

    public TextProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAttrs(attrs);
    }

    private void setAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextProgressBar, 0, 0);
            setText(a.getString(R.styleable.TextProgressBar_text));
            setTextColor(a.getColor(R.styleable.TextProgressBar_textColor, Color.BLACK));
            setTextSize(a.getDimension(R.styleable.TextProgressBar_textSize, 15));
            a.recycle();
        }
    }
    
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        Rect bounds = new Rect();
        textPaint.getTextBounds(mText, 0, mText.length(), bounds);
        int x = getWidth() / 2 - bounds.centerX();
        int y = getHeight() / 2 - bounds.centerY();
        canvas.drawText(mText, x, y, textPaint);
    }

    public String getText() {
        return mText;
    }

    public synchronized void setText(String mText) {
        if (mText != null) {
            this.mText = mText;
        } else {
            this.mText = "";
        }
        postInvalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public synchronized void setTextColor(int textColor) {
        this.textColor = textColor;
        postInvalidate();
    }

    public float getTextSize() {
        return textSize;
    }

    public synchronized void setTextSize(float textSize) {
        this.textSize = textSize;
        postInvalidate();
    }
    
    private static String[] wrapText(String text, Paint paint, int wrapWidth, int currentX) {
        // wrapped text lines
        final List<String> wt = new LinkedList<String>();
        final int len = text.length();
        if (len == 0) {
          return new String[0];
        }
     
        int first = 0;
        int last = 0;
        boolean emptylineflag = false;
        for (int i = 0; i < len; i++) {
          // split at '\n'
          if (text.charAt(i) == '\n') {
            wt.add(text.substring(first, i));
            first = i + 1;
            last = i;
            emptylineflag = false;
            continue;
          }
     
          // update last if necessary
          if (text.charAt(i) == ' ') {
            last = i;
            // don't check to split - we would have splitted at the previous char
            continue;
          }
          
          // check that wrapping is switched on
          if (wrapWidth < 0) {
            continue;
          }
     
          // check length, see if we should split
          float w = paint.measureText(text, first, i + 1);
          // we should not split if we printed nothing on the previous line
          if ((first == 0 && w + currentX > wrapWidth) || (first != 0 && w > wrapWidth)) {
            // yep, we should split, first to last, then the rest
            // if last is equal to first, the first word doesn't fit, so add an
            // empty string
            // but set a flag to avoid adding it again
            if (first < last || (first == last && !emptylineflag)) {
              String s = text.substring(first, last);
              wt.add(s);
            }
            if (first < last) {
              // start with last+1
              emptylineflag = false;
              first = last + 1;
            } else {
              emptylineflag = true;
            }
          }
        }
     
        // add the last string
        wt.add(text.substring(first));
        return wt.toArray(new String[wt.size()]);
      }
}

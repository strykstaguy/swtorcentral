package com.stryksta.swtorcentral.util;

import com.stryksta.swtorcentral.R;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utility {
	public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        listView.refreshDrawableState();
    }
    public static void setListViewHeightBasedOnChildren(GridView gridView) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(gridView.getWidth(), MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i+=4) {
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = gridView.getLayoutParams();

        int row = (listAdapter.getCount()%4 == 0) ? (listAdapter.getCount()/4) : ((listAdapter.getCount()/4)+1);
        
        int t = totalHeight + ((gridView.getPaddingBottom() + gridView.getPaddingTop())* (row+1));
        params.height = t;
        gridView.setLayoutParams(params);
        gridView.requestLayout();
        gridView.refreshDrawableState();
    }


    public static String toTitleCase(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static String capitalizeFirstOnly (String line)
    {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static String subStringBetween(String sentence, String before, String after) {

        int startSub = subStringStartIndex(sentence, before);
        int stopSub = subStringEndIndex(sentence, after);

        String newWord = sentence.substring(startSub, stopSub);
        return newWord;
    }

    public static int subStringStartIndex(String sentence, String delimiterBeforeWord) {

        int startIndex = 0;
        String newWord = "";
        int x = 0, y = 0;

        for (int i = 0; i < sentence.length(); i++) {
            newWord = "";

            if (sentence.charAt(i) == delimiterBeforeWord.charAt(0)) {
                startIndex = i;
                for (int j = 0; j < delimiterBeforeWord.length(); j++) {
                    try {
                        if (sentence.charAt(startIndex) == delimiterBeforeWord.charAt(j)) {
                            newWord = newWord + sentence.charAt(startIndex);
                        }
                        startIndex++;
                    } catch (Exception e) {
                    }

                }
                if (newWord.equals(delimiterBeforeWord)) {
                    x = startIndex;
                }
            }
        }
        return x;
    }

    public static int subStringEndIndex(String sentence, String delimiterAfterWord) {

        int startIndex = 0;
        String newWord = "";
        int x = 0;

        for (int i = 0; i < sentence.length(); i++) {
            newWord = "";

            if (sentence.charAt(i) == delimiterAfterWord.charAt(0)) {
                startIndex = i;
                for (int j = 0; j < delimiterAfterWord.length(); j++) {
                    try {
                        if (sentence.charAt(startIndex) == delimiterAfterWord.charAt(j)) {
                            newWord = newWord + sentence.charAt(startIndex);
                        }
                        startIndex++;
                    } catch (Exception e) {
                    }

                }
                if (newWord.equals(delimiterAfterWord)) {
                    x = startIndex;
                    x = x - delimiterAfterWord.length();
                }
            }
        }
        return x;
    }

    public static ColorFilter getColoredMatrix(int targetColor) {
        int red = (targetColor & 0xFF0000) / 0xFFFF;
        int green = (targetColor & 0xFF00) / 0xFF;
        int blue = targetColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        return colorFilter;
    }
}

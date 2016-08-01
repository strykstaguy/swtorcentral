package com.stryksta.swtorcentral.util;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

public class Utility {
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

    public static String subStringBefore(String sentence, String after) {

        int stopSub = subStringEndIndex(sentence, after);
        /*
        int idx = beginsTxt.indexOf("AT");
        if (idx < 0)
            throw new IllegalArgumentException("Word not found.");
        String before = beginsTxt.substring(0, idx);
        */
        String newWord = sentence.substring(0, stopSub);
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
    /**
     * Convert Dp to Pixel
     */
    public static int dpToPx(float dp, Resources resources){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static int getRelativeTop(View myView) {
//	    if (myView.getParent() == myView.getRootView())
        if(myView.getId() == android.R.id.content)
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    public static int getRelativeLeft(View myView) {
//	    if (myView.getParent() == myView.getRootView())
        if(myView.getId() == android.R.id.content)
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    public static boolean isEmptyString(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }

    public static boolean isEmptyTest(@Nullable CharSequence text) {
        if (text == null || text.length() == 0) return true;

        return text.toString().isEmpty();
    }
}

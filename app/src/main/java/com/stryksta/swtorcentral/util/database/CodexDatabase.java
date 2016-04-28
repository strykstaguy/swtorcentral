package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.CodexCategoryItem;
import com.stryksta.swtorcentral.data.CodexItem;
import com.stryksta.swtorcentral.data.LoreItem;

public class CodexDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "swtor";
    private static final int DATABASE_VERSION = 1;

    public CodexDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<CodexCategoryItem> getCategories() {
        ArrayList<CodexCategoryItem> categoryItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT codexes.cdxCategory ")
                .append("FROM codexes ")
                .append("WHERE codexes.cdxCategory IS NOT NULL ")
                .append("AND codexes.cdxCategory GLOB '*[A-Za-z]*' ")
                .append("AND codexes.cdxTitle GLOB '*[A-Za-z]*' ")
                .append("GROUP BY codexes.cdxCategory ")
                .toString();
        Cursor c = db.rawQuery(sqlSelect, null);

        if (c.moveToFirst()) {
            do {
                String cdxCategory = c.getString(c.getColumnIndex("cdxCategory"));
                categoryItem.add(new CodexCategoryItem(cdxCategory));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return categoryItem;
    }

    public ArrayList<CodexItem> getCodexes(String category) {
        ArrayList<CodexItem> categoryItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT * ")
                .append("FROM codexes ")
                .append("WHERE codexes.cdxCategory = ? ")
                .append("AND codexes.cdxTitle GLOB '*[A-Za-z]*' ")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(category)});

        if (c.moveToFirst()) {
            do {
                int cdxID = c.getInt(c.getColumnIndex("_id"));
                String cdxTitle = c.getString(c.getColumnIndex("cdxTitle"));
                String cdxDescription = c.getString(c.getColumnIndex("cdxDescription"));
                String cdxCategory = c.getString(c.getColumnIndex("cdxCategory"));
                String cdxLevel = c.getString(c.getColumnIndex("cdxLevel"));
                String cdxImage = c.getString(c.getColumnIndex("cdxImage"));
                String cdxFaction = c.getString(c.getColumnIndex("cdxFaction"));
                String cdxIsPlanetCodex = c.getString(c.getColumnIndex("cdxIsPlanetCodex"));
                String cdxPlants = c.getString(c.getColumnIndex("cdxPlanets"));

                categoryItem.add(new CodexItem(cdxID, cdxTitle, cdxDescription, cdxCategory, cdxLevel, cdxImage, cdxFaction, cdxIsPlanetCodex, cdxPlants));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return categoryItem;
    }
}
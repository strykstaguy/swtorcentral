package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.models.CodexItem;

public class CodexDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "swtor";
    private static final int DATABASE_VERSION = 1;

    public CodexDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
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
*/

    public List<String> getCategories() {
        List<String> categoryItem = new ArrayList<>();
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

        categoryItem.add("All");

        if (c.moveToFirst()) {
            do {
                String cdxCategory = c.getString(c.getColumnIndex("cdxCategory"));
                categoryItem.add(cdxCategory);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return categoryItem;
    }

    public ArrayList<CodexItem> getCodexes(String category, String faction) {
        ArrayList<CodexItem> categoryItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();




        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * ");
        queryBuilder.append("FROM codexes ");

        //If Category isn't all, include in query
        if (category != "All") {
            queryBuilder.append("WHERE codexes.cdxCategory = '" + category + "' ");
        }

        //If Faction isn't all, include in query
        if (faction != "All") {
            //If Category isn't all then it should be a WHERE, or else it's AND
            if (category != "All") {
                queryBuilder.append("AND codexes.cdxFaction = '" + faction + "' ");
            } else {
                queryBuilder.append("WHERE codexes.cdxFaction = '" + faction + "' ");
            }
        }

        //If both are all set to and not where
        if (category == "All" && faction == "All") {
            queryBuilder.append("WHERE codexes.cdxTitle GLOB '*[A-Za-z]*' ");
        } else {
            queryBuilder.append("AND codexes.cdxTitle GLOB '*[A-Za-z]*' ");
        }



        queryBuilder.append("ORDER BY codexes.cdxLevel ASC, codexes.cdxTitle ASC");
        String sqlSelect = queryBuilder.toString();
        /*
        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT * ")
                .append("FROM codexes ")
                .append("WHERE codexes.cdxCategory = ? ")
                .append("WHERE codexes.cdxCategory = ? ")
                .append("AND codexes.cdxTitle GLOB '*[A-Za-z]*' ")
                .append("ORDER BY codexes.cdxLevel ASC, codexes.cdxTitle ASC")
                .toString();
        */

        Cursor c = db.rawQuery(sqlSelect, null);
        //Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(category), String.valueOf(faction)});

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

    public ArrayList<CodexItem> getAllCodexes() {
        ArrayList<CodexItem> categoryItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT * ")
                .append("FROM codexes ")
                .append("WHERE codexes.cdxTitle GLOB '*[A-Za-z]*' ")
                .append("ORDER BY codexes.cdxLevel ASC, codexes.cdxTitle ASC")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, null);

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
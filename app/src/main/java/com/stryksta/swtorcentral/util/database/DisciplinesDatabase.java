package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.models.DisciplineItem;

import java.util.ArrayList;

public class DisciplinesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;

	public DisciplinesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    public ArrayList<DisciplineItem> getDisciplines(String advancedClassID) {
        ArrayList<DisciplineItem> disciplineItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT disciplines._id, advanced_classes.advClassName, disciplines.sortindex, disciplines.type, disciplines.name, disciplines.description, disciplines.abl, disciplines.apc ")
                .append("FROM disciplines ")
                .append("LEFT JOIN advanced_classes ")
                .append("ON disciplines.advanced_class_id = advanced_classes._id ")
                .append("WHERE disciplines.advanced_class_id = ? ")
                .append("AND disciplines.type IS NOT \"Utility\" ")
                .append("ORDER BY disciplines.sortindex ASC")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(advancedClassID)});

        if (c.moveToFirst()) {
            do {

                int disID = c.getInt(c.getColumnIndex("_id"));
                String advClassName = c.getString(c.getColumnIndex("advanced_class_id"));
                int disSortIndex = c.getInt(c.getColumnIndex("sortindex"));
                String disType = c.getString(c.getColumnIndex("type"));
                String disName = c.getString(c.getColumnIndex("name"));
                String disDescription = c.getString(c.getColumnIndex("description"));
                String disAbl = c.getString(c.getColumnIndex("abl"));
                String disApc = c.getString(c.getColumnIndex("apc"));

                //int disID, String advClassName, int disSortIndex, String disType, String disName, String disDescription, String disAbl, String disApc
                disciplineItems.add(new DisciplineItem(disID, advClassName, disSortIndex, disType, disName, disDescription, disAbl, disApc));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return disciplineItems;
    }
}
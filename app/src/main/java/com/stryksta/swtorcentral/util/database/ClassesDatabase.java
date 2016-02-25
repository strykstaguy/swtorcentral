package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ClassesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public ClassesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    public Cursor getClasses() {
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelect = "SELECT * FROM classes ORDER BY _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        return c;
    }

	public Cursor getAdvancedClasses(long id) {
		SQLiteDatabase db = getReadableDatabase();
		String sqlSelect = "SELECT * FROM advanced_classes WHERE class_id = ?";

		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
		return c;
	}
}
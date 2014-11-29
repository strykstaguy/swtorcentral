package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AdvancedClassesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public AdvancedClassesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getAdvancedClass(long id) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "class_id", "advanced_id", "second_id", "class", "description", "role", "armor", "weapons", "priattribute"}; 
		String sqlTables = "advanced_classes";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "class_id" + " = ?", new String[]{String.valueOf(id)}, null, null, null);
		
		/*if (c.moveToFirst()) {
            do {
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String reward = c.getString(c.getColumnIndex("reward"));
            	String coord = c.getString(c.getColumnIndex("coord"));
            } while (c.moveToNext());
        } */
		
		c.moveToFirst();
		//c.close();
		return c;
	}
	
	public Cursor getAdvanced(long id) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "class_id", "class"}; 
		String sqlTables = "advanced_classes";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "class_id" + " = ?", new String[]{String.valueOf(id)}, null, null, null);
		
		c.moveToFirst();
		//c.close();
		return c;
	}
}
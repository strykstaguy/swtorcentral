package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CompanionGiftsDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public CompanionGiftsDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getCompanions(long id) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "companion_name", "class", "gift"}; 
		String sqlTables = "companion_gifts";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "companion_name" + " = ?", new String[]{String.valueOf(id)}, null, null, null);
		
		c.moveToFirst();
		//c.close();
		return c;
	}
	
	public String getGifts(String txtCompanion) {
		String txtGifts = "";
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "companion_name", "class", "gift"};  
		String sqlTables = "companion_gifts";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "companion_name" + " = ?", new String[]{String.valueOf(txtCompanion)}, null, null, null);
		
		if (c.moveToFirst()) {
            do {
            	txtGifts = txtGifts + c.getString(c.getColumnIndex("gift")) + "\n";
            } while (c.moveToNext());
        }
		
		c.close();
		return txtGifts;

	}
}
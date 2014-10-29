package com.stryksta.swtorcentral.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class PlanetDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public PlanetDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public String PlanetDescription(String txtplanet) {
		String txtDescription = null;
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "planet", "description"}; 
		String sqlTables = "planets";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "planet" + " = ?", new String[]{String.valueOf(txtplanet)}, null, null, null);
		
		if (c.moveToFirst()) {
			txtDescription = c.getString(c.getColumnIndex("description"));
        }
		
		//c.moveToFirst();
		c.close();
		return txtDescription;

	}
	
	public String PlanetBackground(String txtplanet) {
		String txtBackground = null;
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "planet", "background"}; 
		String sqlTables = "planets";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "planet" + " = ?", new String[]{String.valueOf(txtplanet)}, null, null, null);
		
		if (c.moveToFirst()) {
			txtBackground = c.getString(c.getColumnIndex("background"));
        }
		
		//c.moveToFirst();
		c.close();
		return txtBackground;

	}
}
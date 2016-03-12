package com.stryksta.swtorcentral.util.database;

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

		String [] sqlSelect = {"0 _id", "pltName", "pltDescription"};
		String sqlTables = "planets";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "pltName" + " = ?", new String[]{String.valueOf(txtplanet)}, null, null, null);
		
		if (c.moveToFirst()) {
			txtDescription = c.getString(c.getColumnIndex("pltDescription"));
        }
		
		//c.moveToFirst();
		c.close();
		return txtDescription;

	}
}
package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DisciplinesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;

	public DisciplinesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getDisciplines(long id) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "advanced_class_id", "sortindex", "type", "name", "description", "abl", "apc"};
		String sqlTables = "disciplines";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "advanced_class_id" + " = ? AND sortindex <> '4'", new String[]{String.valueOf(id)}, null, null, null);

		c.moveToFirst();
		//c.close();
		return c;
	}
}
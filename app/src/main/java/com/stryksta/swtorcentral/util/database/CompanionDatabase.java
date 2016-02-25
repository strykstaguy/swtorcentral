package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CompanionDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public CompanionDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public Cursor getOriginalCompanions(long id) {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "companion_name", "class_id", "role", "crew_skill_bonus", "romance", "primarystat", "secondarystat", "primaryweapon", "secondaryweapon", "gender", "race", "found", "armor", "description"}; 
		String sqlTables = "companions";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, "class_id" + " = ?", new String[]{String.valueOf(id)}, null, null, null);
		
		c.moveToFirst();
		//c.close();
		return c;
	}
}
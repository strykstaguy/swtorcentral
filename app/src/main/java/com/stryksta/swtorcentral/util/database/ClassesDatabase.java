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

	/*public ArrayList<ClassItem> getClasses() {
		ArrayList<ClassItem> classItem = new ArrayList<ClassItem>();
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "faction", "planet", "map", "reward", "location", "reward", "codex"}; 
		String sqlTables = "datacrons";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
		
		if (c.moveToFirst()) {
            do {
            	Integer id = c.getInt(c.getColumnIndex("_id"));
            	String name = c.getString(c.getColumnIndex("name"));
            	String classicon = c.getString(c.getColumnIndex("class_icon"));
            	classItem.add(new ClassItem(id, name, classicon));
            	
            } while (c.moveToNext());
        }
		c.close();
		return classItem;

	}
	*/
	public Cursor getClasses() {
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		//String [] sqlSelect = {"0 _id", "faction_id", "name", "class_icon", "description", "resource"};
		//String sqlTables = "classes";
		
		String sqlSelect = "SELECT * FROM classes ORDER BY _id asc";

		
		
		//qb.setTables(sqlTables);
		//Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
		
		Cursor c = db.rawQuery(sqlSelect, null);
		
		c.moveToFirst();
		//c.close();
		return c;
	}
	
	public Cursor getAdvancedClasses(long id) {
		SQLiteDatabase db = getReadableDatabase();
		String sqlSelect = "SELECT * FROM advanced_classes WHERE class_id = ?";

		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
		return c;
	}
	
	public Cursor getAdvancedClass(String id) {
		SQLiteDatabase db = getReadableDatabase();
		String sqlSelect = "SELECT * FROM advanced_classes WHERE class = ?";
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
		return c;
	}
}
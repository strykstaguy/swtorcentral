package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.stryksta.swtorcentral.data.TutorialItem;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class TutorialDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public TutorialDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public ArrayList<TutorialItem> getTutorials() {
		ArrayList<TutorialItem> tutorialItem = new ArrayList<TutorialItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT * FROM tutorials order by _id asc";
		Cursor c = db.rawQuery(sqlSelect, null);
		
		if (c.moveToFirst()) {
            do {
            	String title = c.getString(c.getColumnIndex("title"));
            	String videourl = c.getString(c.getColumnIndex("videourl"));
            	String imageurl = c.getString(c.getColumnIndex("imgurl"));
            	String smalldescription = c.getString(c.getColumnIndex("smalldescription"));
            	String description = c.getString(c.getColumnIndex("description"));
            	
            	tutorialItem.add(new TutorialItem(title, videourl, imageurl, smalldescription, description));
            } while (c.moveToNext());
        }
		c.close();
		return tutorialItem;

	}
}
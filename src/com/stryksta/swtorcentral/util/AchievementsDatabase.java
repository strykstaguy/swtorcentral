package com.stryksta.swtorcentral.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.AchievementsItem;

public class AchievementsDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public AchievementsDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public ArrayList<AchievementsItem> getCategory1() {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT *, sum(points) AS count FROM achievements GROUP BY category1 ORDER BY _id asc";
		Cursor c = db.rawQuery(sqlSelect, null);
		
		if (c.moveToFirst()) {
            do {
            	int achievementID = c.getInt(c.getColumnIndex("_id"));
            	String category1 = c.getString(c.getColumnIndex("category1"));
            	String category2 = c.getString(c.getColumnIndex("category2"));
            	String category3 = c.getString(c.getColumnIndex("category3"));
            	String title = c.getString(c.getColumnIndex("title"));
            	String description = c.getString(c.getColumnIndex("description"));
            	int points = c.getInt(c.getColumnIndex("points"));
            	String rewards = c.getString(c.getColumnIndex("rewards"));
            	int count = c.getInt(c.getColumnIndex("count"));
            	
            	achievementItem.add(new AchievementsItem(achievementID, category1, category2, category3, title, description, points, rewards, count, 0, ""));
            } while (c.moveToNext());
        }
		c.close();
		return achievementItem;

	}
	
	public ArrayList<AchievementsItem> getCategory2(String category) {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT *, sum(points) AS count FROM achievements WHERE category1 = ? GROUP BY category2 ORDER BY _id asc";
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(category)});
		
		if (c.moveToFirst()) {
            do {
            	int achievementID = c.getInt(c.getColumnIndex("_id"));
            	String category1 = c.getString(c.getColumnIndex("category1"));
            	String category2 = c.getString(c.getColumnIndex("category2"));
            	String category3 = c.getString(c.getColumnIndex("category3"));
            	String title = c.getString(c.getColumnIndex("title"));
            	String description = c.getString(c.getColumnIndex("description"));
            	int points = c.getInt(c.getColumnIndex("points"));
            	String rewards = c.getString(c.getColumnIndex("rewards"));
            	int count = c.getInt(c.getColumnIndex("count"));
            	
            	achievementItem.add(new AchievementsItem(achievementID, category1, category2, category3, title, description, points, rewards, count, 0, ""));
            } while (c.moveToNext());
        }
		c.close();
		return achievementItem;
	}
	
	public ArrayList<AchievementsItem> getCategory3(String txtcategory1, String txtcategory2) {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT *, sum(points) AS count FROM achievements WHERE category1 = ? AND category2 = ? GROUP BY category3 ORDER BY _id asc";
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtcategory1), String.valueOf(txtcategory2)});
		
		if (c.moveToFirst()) {
            do {
            	int achievementID = c.getInt(c.getColumnIndex("_id"));
            	String category1 = c.getString(c.getColumnIndex("category1"));
            	String category2 = c.getString(c.getColumnIndex("category2"));
            	String category3 = c.getString(c.getColumnIndex("category3"));
            	String title = c.getString(c.getColumnIndex("title"));
            	String description = c.getString(c.getColumnIndex("description"));
            	int points = c.getInt(c.getColumnIndex("points"));
            	String rewards = c.getString(c.getColumnIndex("rewards"));
            	int count = c.getInt(c.getColumnIndex("count"));
            	
            	achievementItem.add(new AchievementsItem(achievementID, category1, category2, category3, title, description, points, rewards, count, 0, ""));
            } while (c.moveToNext());
        }
		c.close();
		return achievementItem;
	}
	
	public ArrayList<AchievementsItem> getAchievements(String txtcategory1, String txtcategory2, String txtcategory3) {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT achievements._id, achievements.category1, achievements.category2, achievements.category3, achievements.title, achievements.description, achievements.points, achievements.rewards, achievements.hidden,character.name, ")
		    .append("CASE WHEN (a.character_id is not null) ")
		    .append("THEN \'1\' ")
		    .append("ELSE \'0\' ")
		    .append("END AS completed ")
		    .append("FROM achievements ")
		    .append("LEFT JOIN character_achievements a ")
		    .append("ON achievements._id = a.achievements_id ")
		    .append("LEFT JOIN character")
		    .append("ON a.character_id = character._id ")
		    .append("WHERE category1 = ? AND category2 = ? and category3 = ?")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtcategory1), String.valueOf(txtcategory2), String.valueOf(txtcategory3)});
		
		if (c.moveToFirst()) {
            do {
            	int achievementID = c.getInt(c.getColumnIndex("_id"));
            	String category1 = c.getString(c.getColumnIndex("category1"));
            	String category2 = c.getString(c.getColumnIndex("category2"));
            	String category3 = c.getString(c.getColumnIndex("category3"));
            	String title = c.getString(c.getColumnIndex("title"));
            	String description = c.getString(c.getColumnIndex("description"));
            	int points = c.getInt(c.getColumnIndex("points"));
            	String rewards = c.getString(c.getColumnIndex("rewards"));
            	int completed = c.getInt(c.getColumnIndex("completed"));
            	String player = c.getString(c.getColumnIndex("name"));
            	
            	Log.d("SWTORCentral", player);
            	
            	achievementItem.add(new AchievementsItem(achievementID, category1, category2, category3, title, description, points, rewards, 0, completed, player));
            } while (c.moveToNext());
        }
		c.close();
		return achievementItem;
	}
	
	public void setCompleted (int characterID, int achievementID) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("character_id", characterID);
		values.put("achievements_id", achievementID);
		
		db.insert("character_achievements", "character_id", values);
		db.close();
	}
	
	public void removeCompleted (int characterID, int achievementID) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlSelect = "character_id = ? AND achievements_id = ?";
		db.delete("character_achievements", sqlSelect , new String[]{String.valueOf(characterID), String.valueOf(achievementID)});
		db.close();
	}
}
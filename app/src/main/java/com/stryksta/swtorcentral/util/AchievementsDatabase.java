package com.stryksta.swtorcentral.util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.data.AchievementsItem;

public class AchievementsDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public AchievementsDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public ArrayList<AchievementCategoryItem> getCategory1(String characterID, String Legacy) {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT a.category1 as category, ")
			.append("SUM(CASE WHEN ca.achievements_id is not null then points end) AS completed, ")
			.append("SUM(points) total ")
			.append("FROM achievements a ")
		    .append("LEFT JOIN character_achievements ca ")
		    .append("ON ca.achievements_id = a._id AND (ca.character_id = ? OR ca.legacy = ?) ")
		    .append("GROUP BY a.category1 ")
		    .append("ORDER BY a._id asc ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(characterID), String.valueOf(Legacy)});
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("category"));
            	int completed = c.getInt(c.getColumnIndex("completed"));
            	int total = c.getInt(c.getColumnIndex("total"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementCategoryItem> getCategory2(String characterID, String Legacy, String Category1) {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT a.category2 as category, ")
			.append("SUM(CASE WHEN ca.achievements_id is not null then points end) AS completed, ")
			.append("SUM(points) total ")
			.append("FROM achievements a ")
		    .append("LEFT JOIN character_achievements ca ")
		    .append("ON ca.achievements_id = a._id AND (ca.character_id = ? OR ca.legacy = ?) ")
		    .append("WHERE a.category1 = ? ")
		    .append("GROUP BY a.category2 ")
		    .append("ORDER BY a._id asc ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(characterID), String.valueOf(Legacy), String.valueOf(Category1)});
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("category"));
            	int completed = c.getInt(c.getColumnIndex("completed"));
            	int total = c.getInt(c.getColumnIndex("total"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementCategoryItem> getCategory3(String characterID, String Legacy, String Category1, String Category2) {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT a.category3 as category, ")
			.append("SUM(CASE WHEN ca.achievements_id is not null then points end) AS completed, ")
			.append("SUM(points) total ")
			.append("FROM achievements a ")
		    .append("LEFT JOIN character_achievements ca ")
		    .append("ON ca.achievements_id = a._id AND (ca.character_id = ? OR ca.legacy = ?) ")
		    .append("WHERE a.category1 = ? AND a.category2 = ? ")
		    .append("GROUP BY a.category3 ")
		    .append("ORDER BY a._id asc ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(characterID), String.valueOf(Legacy), String.valueOf(Category1), String.valueOf(Category2)});
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("category"));
            	int completed = c.getInt(c.getColumnIndex("completed"));
            	int total = c.getInt(c.getColumnIndex("total"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementsItem> getAchievements(String txtcategory1, String txtcategory2, String txtcategory3, String userLegacy) {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT achievements._id, achievements.category1, achievements.category2, achievements.category3, achievements.title, achievements.description, achievements.points, achievements.rewards, achievements.hidden, character.name, character.legacy, ")
		    .append("CASE WHEN (a.character_id is not null) ")
		    .append("THEN \'1\' ")
		    .append("ELSE \'0\' ")
		    .append("END AS completed ")
		    .append("FROM achievements ")
		    .append("LEFT JOIN character_achievements a ")
		    .append("ON achievements._id = a.achievements_id ")
		    .append("AND (a.legacy = ?) ")
		    .append("LEFT JOIN character ")
		    .append("ON a.character_id = character._id AND character.legacy = ? ")
		    .append("WHERE category1 = ? AND category2 = ? and category3 = ?")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(userLegacy), String.valueOf(userLegacy), String.valueOf(txtcategory1), String.valueOf(txtcategory2), String.valueOf(txtcategory3)});
		
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
            	
            	//Log.d("SWTORCentral", sqlSelect);
            	
            	achievementItem.add(new AchievementsItem(achievementID, category1, category2, category3, title, description, points, rewards, 0, completed, player));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return achievementItem;
	}
	
	public void setCompleted (int characterID, int achievementID, String characterLegacy) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("character_id", characterID);
		values.put("achievements_id", achievementID);
		values.put("legacy", characterLegacy);
		
		try {
			db.insert("character_achievements", "character_id", values);
			//Toast.makeText(getApplicationContext(), "Character ID: " + characterID + " Achievement ID: " + achievementAdapter.getItem(position).getAchievementID(), Toast.LENGTH_SHORT).show();
			Log.d("SWTORCentral", "Added Successfully");
		} catch (SQLException ex) {
		      
		}
		
		
		db.close();
	}
	
	public void removeCompleted (int characterID, int achievementID, String characterLegacy) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlSelect = "character_id = ? AND achievements_id = ? AND legacy = ?";
		
		try {
			db.delete("character_achievements", sqlSelect , new String[]{String.valueOf(characterID), String.valueOf(achievementID), String.valueOf(characterLegacy)});
			Log.d("SWTORCentral", "Removed Successfully");
		} catch (SQLException ex) {
		      
		}
		
		db.close();
	}
}
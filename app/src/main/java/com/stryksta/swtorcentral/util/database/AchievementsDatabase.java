package com.stryksta.swtorcentral.util.database;

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
	
	public ArrayList<AchievementCategoryItem> getCategory1() {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
				.append("SELECT achievements.achCategory as achCategory1,categories.achievementOrderPosition, ")
				.append("SUM(CASE WHEN ca.achievements_id is not null then achRewardPoints end) AS achCompleted,  ")
				.append("SUM(achRewardPoints) achTotal ")
				.append("FROM achievements ")
		    	.append("LEFT JOIN categories ")
		    	.append("ON achievements.achCategory = categories.achCategory ")
		    	.append("LEFT JOIN completed_achievements ca ")
				.append("ON ca.achievements_id = achievements._id ")
				.append("WHERE achievements.achCategory IS NOT null ")
                .append("GROUP BY achievements.achCategory ")
                .append("ORDER BY cast(categories.achievementOrderPosition as unsigned) ASC, achCategory1 DESC ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, null);
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("achCategory1"));
            	int completed = c.getInt(c.getColumnIndex("achCompleted"));
            	int total = c.getInt(c.getColumnIndex("achTotal"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementCategoryItem> getCategory2(String Category1) {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
				.append("SELECT achievements.achSubCategory as achCategory2, ")
				.append("SUM(CASE WHEN ca.achievements_id is not null then achRewardPoints end) AS achCompleted, ")
				.append("SUM(achRewardPoints) achTotal ")
				.append("FROM achievements ")
		    	.append("LEFT JOIN categories ")
		    	.append("ON achievements.achCategory = categories.achCategory ")
		    	.append("LEFT JOIN completed_achievements ca ")
		    	.append("ON ca.achievements_id = achievements._id ")
		    	.append("WHERE achievements.achCategory = ? ")
				.append("GROUP BY achievements.achSubCategory ")
				.append("ORDER BY achCategory2 ASC ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(Category1)});
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("achCategory2"));
            	int completed = c.getInt(c.getColumnIndex("achCompleted"));
            	int total = c.getInt(c.getColumnIndex("achTotal"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementCategoryItem> getCategory3(String Category1, String Category2) {
		ArrayList<AchievementCategoryItem> categoryItem = new ArrayList<AchievementCategoryItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
				.append("SELECT achievements.achTertiaryCategory as achCategory3, ")
				.append("SUM(CASE WHEN ca.achievements_id is not null then achRewardPoints end) AS achCompleted,  ")
				.append("SUM(achRewardPoints) achTotal ")
				.append("FROM achievements ")
		    	.append("LEFT JOIN categories ")
		    	.append("ON achievements.achCategory = categories.achCategory ")
		    	.append("LEFT JOIN completed_achievements ca ")
		    	.append("ON ca.achievements_id = achievements._id ")
		    	.append("WHERE achievements.achCategory = ? AND achievements.achSubCategory = ? ")
				.append("GROUP BY achievements.achTertiaryCategory ")
				.append("ORDER BY achCategory3 ASC ")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(Category1), String.valueOf(Category2)});
		
		if (c.moveToFirst()) {
            do {
            	String category = c.getString(c.getColumnIndex("achCategory3"));
            	int completed = c.getInt(c.getColumnIndex("achCompleted"));
            	int total = c.getInt(c.getColumnIndex("achTotal"));
            	
            	categoryItem.add(new AchievementCategoryItem(category, completed, total));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return categoryItem;
	}
	
	public ArrayList<AchievementsItem> getAchievements(String Category1, String Category2, String Category3) {
		ArrayList<AchievementsItem> achievementItem = new ArrayList<AchievementsItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT achievements._id, achievements.achCategory, achievements.achSubCategory, achievements.achTertiaryCategory, achievements.achTitle, achievements.achDescription, achievements.achRewardPoints, achievements.achRewardTitle, achievements.achRewardFleetRequisition, achievements.achVisibility, ")
		    .append("CASE WHEN (a._id is not null) ")
		    .append("THEN \'1\' ")
		    .append("ELSE \'0\' ")
		    .append("END AS achCompleted ")
		    .append("FROM achievements ")
		    .append("LEFT JOIN completed_achievements a ")
		    .append("ON achievements._id = a.achievements_id ")
		    .append("WHERE achCategory = ? AND achSubCategory = ? and achTertiaryCategory = ?")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(Category1), String.valueOf(Category2), String.valueOf(Category3)});
		
		if (c.moveToFirst()) {
            do {
            	int achID = c.getInt(c.getColumnIndex("_id"));
            	String achCategory1 = c.getString(c.getColumnIndex("achCategory1"));
            	String achCategory2 = c.getString(c.getColumnIndex("achCategory2"));
            	String achCategory3 = c.getString(c.getColumnIndex("achCategory3"));
            	String achTitle = c.getString(c.getColumnIndex("achTitle"));
            	String achDescription = c.getString(c.getColumnIndex("achDescription"));
            	int achRewardPoints = c.getInt(c.getColumnIndex("achRewardPoints"));
            	String achRewardTitle = c.getString(c.getColumnIndex("achRewardTitle"));
            	int achRewardFleetRequisition = c.getInt(c.getColumnIndex("achRewardFleetRequisition"));
            	String achVisibility = c.getString(c.getColumnIndex("achVisibility"));
				int achCompleted = c.getInt(c.getColumnIndex("achCompleted"));

            	achievementItem.add(new AchievementsItem(achID, achCategory1, achCategory2, achCategory3, achTitle, achDescription, achRewardPoints, achRewardTitle, achRewardFleetRequisition, achVisibility, achCompleted));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return achievementItem;
	}
	
	public void setCompleted (int achievementID) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("achievements_id", achievementID);
		
		try {
			db.insert("completed_achievements", "achievements_id", values);
			//Toast.makeText(getApplicationContext(), "Character ID: " + characterID + " Achievement ID: " + achievementAdapter.getItem(position).getAchievementID(), Toast.LENGTH_SHORT).show();
			Log.d("SWTORCentral", "Added Successfully");
		} catch (SQLException ex) {
		      
		}
		
		
		db.close();
	}
	
	public void removeCompleted (int achievementID) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sqlSelect = "achievements_id = ?";
		
		try {
			db.delete("character_achievements", sqlSelect , new String[]{String.valueOf(achievementID)});
			Log.d("SWTORCentral", "Removed Successfully");
		} catch (SQLException ex) {
		      
		}
		
		db.close();
	}
}
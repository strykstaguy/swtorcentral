package com.stryksta.swtorcentral.util;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.LoreItem;

public class LoreDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public LoreDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public ArrayList<LoreItem> getLore(String txtPlanet, String txtFaction) {
		ArrayList<LoreItem> loreItem = new ArrayList<LoreItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT faction, planet, category, codex, comment ")
			.append("FROM lore ")
		    .append("WHERE (faction = ? OR faction = 'Both') AND planet = ? AND category = 'Lore' ")
		    .append("ORDER BY category asc ")
		.toString();
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtFaction), String.valueOf(txtPlanet)});
		
		if (c.moveToFirst()) {
            do {
            	String faction = c.getString(c.getColumnIndex("faction"));
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String category = c.getString(c.getColumnIndex("category"));
            	String codex = c.getString(c.getColumnIndex("codex"));
            	String comment = c.getString(c.getColumnIndex("comment"));
            	
            	loreItem.add(new LoreItem(faction, planet, category, codex, comment));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return loreItem;
	}
	
	public ArrayList<LoreItem> getLocationLore(String txtPlanet, String txtFaction) {
		ArrayList<LoreItem> loreItem = new ArrayList<LoreItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT faction, planet, category, codex, comment ")
			.append("FROM lore ")
		    .append("WHERE (faction = ? OR faction = 'Both') AND planet = ? AND category = 'Locations' ")
		    .append("ORDER BY category asc ")
		.toString();
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtFaction), String.valueOf(txtPlanet)});
		
		if (c.moveToFirst()) {
            do {
            	String faction = c.getString(c.getColumnIndex("faction"));
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String category = c.getString(c.getColumnIndex("category"));
            	String codex = c.getString(c.getColumnIndex("codex"));
            	String comment = c.getString(c.getColumnIndex("comment"));
            	
            	loreItem.add(new LoreItem(faction, planet, category, codex, comment));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return loreItem;
	}
	
	public ArrayList<LoreItem> getPersonsLore(String txtPlanet, String txtFaction) {
		ArrayList<LoreItem> loreItem = new ArrayList<LoreItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT faction, planet, category, codex, comment ")
			.append("FROM lore ")
		    .append("WHERE (faction = ? OR faction = 'Both') AND planet = ? AND category = 'Persons' ")
		    .append("ORDER BY category asc ")
		.toString();
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtFaction), String.valueOf(txtPlanet)});
		
		if (c.moveToFirst()) {
            do {
            	String faction = c.getString(c.getColumnIndex("faction"));
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String category = c.getString(c.getColumnIndex("category"));
            	String codex = c.getString(c.getColumnIndex("codex"));
            	String comment = c.getString(c.getColumnIndex("comment"));
            	
            	loreItem.add(new LoreItem(faction, planet, category, codex, comment));
            } while (c.moveToNext());
        }
		c.close();
		db.close();
		return loreItem;
	}
}
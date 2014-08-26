package com.stryksta.swtorcentral.util;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.DatacronItem;

public class DatacronDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public DatacronDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public ArrayList<DatacronItem> getDatacrons() {
		ArrayList<DatacronItem> datacronItem = new ArrayList<DatacronItem>();
		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "faction", "planet", "map", "reward", "location", "reward", "codex"}; 
		String sqlTables = "datacrons";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
		
		if (c.moveToFirst()) {
            do {
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String reward = c.getString(c.getColumnIndex("reward"));
            	String location = c.getString(c.getColumnIndex("location"));
            	String codex = c.getString(c.getColumnIndex("codex"));
            	String prevPlanet = null;
            		
            	if (c.getPosition() > 0 && c.moveToPrevious()) {
            		prevPlanet = c.getString(c.getColumnIndex("planet"));
            	    c.moveToNext();
            	}
            		
            	if (prevPlanet == null || !prevPlanet.equals(planet)) {
            		datacronItem.add(new DatacronItem(DatacronItem.SECTION, planet));
            		datacronItem.add(new DatacronItem(DatacronItem.ITEM, planet, reward, location, codex));
            	} else {
            	    datacronItem.add(new DatacronItem(DatacronItem.ITEM, planet, reward, location, codex));
            	}
            } while (c.moveToNext());
        }
		
		//c.moveToFirst();
		c.close();
		return datacronItem;

	}
	
	public ArrayList<DatacronItem> getDatacronsPerPlanet(String txtplanet, String txtfaction) {
		ArrayList<DatacronItem> datacronItem = new ArrayList<DatacronItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		String sqlSelect = "SELECT * FROM datacrons where planet = ? AND faction = ?";
	    Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtplanet), String.valueOf(txtfaction)});
	        
		if (c.moveToFirst()) {
            do {
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String reward = c.getString(c.getColumnIndex("reward"));
            	String location = c.getString(c.getColumnIndex("location"));
            	String codex = c.getString(c.getColumnIndex("codex"));
            		
            	datacronItem.add(new DatacronItem(DatacronItem.ITEM, planet, reward, location, codex));
            	
            } while (c.moveToNext());
        }
		
		//c.moveToFirst();
		c.close();
		return datacronItem;

	}
}
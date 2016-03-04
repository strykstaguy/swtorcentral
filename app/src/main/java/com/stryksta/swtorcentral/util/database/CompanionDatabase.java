package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.CompanionItem;

import java.util.ArrayList;

public class CompanionDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public CompanionDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
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
	*/

	public ArrayList<CompanionItem> getOriginalCompanions(String node) {
		ArrayList<CompanionItem> companionItems = new ArrayList<CompanionItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT nco._id, nco.ncoName, nco.ncoCategory, nco.ncoSubCategory, nco.ncoDescription, chrCompanionInfo.chrCompanionGender, chrCompanionInfo.chrCompanionGiftInterestUnRomanced, chrCompanionInfo.chrCompanionGiftInterestRomanced, nco.npcID, nco.ncoPath\n" +
				"FROM nco\n" +
				"LEFT JOIN chrCompanionTable\n" +
				"ON nco.npcID = chrCompanionTable.chrCompanionNode\n" +
				"LEFT JOIN chrCompanionInfo\n" +
				"ON chrCompanionTable.chrCompanionNode = chrCompanionInfo.chrCompanionNode\n" +
				"WHERE chrCompanionTable.chrCompanionClassNode = ?";

		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(node)});

		if (c.moveToFirst()) {
			do {

                String txtID = c.getString(c.getColumnIndex("npcID"));
                String txtName = c.getString(c.getColumnIndex("ncoName"));
                String txtCategory = c.getString(c.getColumnIndex("ncoCategory"));
                String txtSubCategory = c.getString(c.getColumnIndex("ncoSubCategory"));
                String txtDescription = c.getString(c.getColumnIndex("ncoDescription"));
                String txtGender = c.getString(c.getColumnIndex("chrCompanionGender"));
                String txtGiftsUnRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestUnRomanced"));
                String txtGiftsRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestRomanced"));
                String txtRace = "";
                String txtPath = c.getString(c.getColumnIndex("ncoPath"));

				companionItems.add(new CompanionItem(txtID, txtName, txtCategory, txtSubCategory, txtDescription, txtGender, txtGiftsUnRomanced, txtGiftsRomanced, txtRace, txtPath));
			} while (c.moveToNext());
		}

		//c.moveToFirst();
		c.close();
		return companionItems;
	}
}
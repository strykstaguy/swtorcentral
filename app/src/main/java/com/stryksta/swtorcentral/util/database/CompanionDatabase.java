package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.models.CompanionItem;

import java.util.ArrayList;

public class CompanionDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public CompanionDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public ArrayList<CompanionItem> getOriginalCompanions(String node) {
		ArrayList<CompanionItem> companionItems = new ArrayList<CompanionItem>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT nco._id, nco.ncoName, nco.ncoTitle, nco.ncoCategory, nco.ncoSubCategory, nco.ncoDescription, chrCompanionInfo.chrCompanionGender, chrCompanionInfo.chrCompanionGiftInterestUnRomanced, chrCompanionInfo.chrCompanionGiftInterestRomanced, nco.npcID, nco.ncoPath\n" +
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
				String ncoTitle = c.getString(c.getColumnIndex("ncoTitle"));
                String txtCategory = c.getString(c.getColumnIndex("ncoCategory"));
                String txtSubCategory = c.getString(c.getColumnIndex("ncoSubCategory"));
                String txtDescription = c.getString(c.getColumnIndex("ncoDescription"));
                String txtGender = c.getString(c.getColumnIndex("chrCompanionGender"));
                String txtGiftsUnRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestUnRomanced"));
                String txtGiftsRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestRomanced"));
                String txtRace = "";
                String txtPath = c.getString(c.getColumnIndex("ncoPath"));

				companionItems.add(new CompanionItem(txtID, txtName, ncoTitle, txtCategory, txtSubCategory, txtDescription, txtGender, txtGiftsUnRomanced, txtGiftsRomanced, txtRace, txtPath));
			} while (c.moveToNext());
		}

		//c.moveToFirst();
		c.close();
		return companionItems;
	}

    public ArrayList<CompanionItem> getAllCompanions() {
        ArrayList<CompanionItem> companionItems = new ArrayList<CompanionItem>();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelect = "SELECT nco.ncoName as comName, nco.ncoTitle as comTitle, nco.ncoDescription as comDescription, nco.ncoCategory as comCategory, nco.ncoSubCategory as comSubCategory, nco.ncoInfluenceCap as comInfluenceCap, nco.ncoMaxInfluenceTier as MaxInfluenceTier, nco.npcID, nco.ncoID, nco.ncoPath, chrCompanionInfo.chrCompanionNode, chrCompanionInfo.chrCompanionMinimumAffection as comMinimumAffection, chrCompanionInfo.chrCompanionMaximumAffection as comMaximumAffection, chrCompanionInfo.chrCompanionNumber as comNumber, chrCompanionInfo.chrCompanionGiftInterestUnRomanced as comGiftUnRomanced, chrCompanionInfo.chrCompanionGiftInterestRomanced as comGiftRomanced, chrCompanionInfo.chrCompanionGender as comGender\n" +
                "FROM nco\n" +
                "LEFT JOIN chrCompanionInfo\n" +
                "LEFT JOIN chrCompanionInfoON nco.npcID = chrCompanionInfo.chrCompanionNode\n" +
                "WHERE nco.ncoDescription GLOB '*[A-Za-z]*'\n" +
                "AND nco.ncoCategory IS NOT \"Unavailable Companions\"\n" +
                "AND nco.ncoCategory IS NOT \"Creatures\"\n" +
                "AND nco.ncoCategory IS NOT \"Droids\"\n" +
                "ORDER BY (nco.ncoCategory = 'Main Characters') DESC, nco.ncoCategory";

        Cursor c = db.rawQuery(sqlSelect, null);

        if (c.moveToFirst()) {
            do {

                String txtID = c.getString(c.getColumnIndex("npcID"));
                String txtName = c.getString(c.getColumnIndex("comName"));
                String ncoTitle = c.getString(c.getColumnIndex("comTitle"));
                String txtCategory = c.getString(c.getColumnIndex("comCategory"));
                String txtSubCategory = c.getString(c.getColumnIndex("comSubCategory"));
                String txtDescription = c.getString(c.getColumnIndex("comDescription"));
                String txtGender = c.getString(c.getColumnIndex("chrCompanionGender"));
                String txtGiftsUnRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestUnRomanced"));
                String txtGiftsRomanced = c.getString(c.getColumnIndex("chrCompanionGiftInterestRomanced"));
                String txtRace = "";
                String txtPath = c.getString(c.getColumnIndex("ncoPath"));

                companionItems.add(new CompanionItem(txtID, txtName, ncoTitle, txtCategory, txtSubCategory, txtDescription, txtGender, txtGiftsUnRomanced, txtGiftsRomanced, txtRace, txtPath));
            } while (c.moveToNext());
        }

        //c.moveToFirst();
        c.close();
        return companionItems;
    }
}
package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.AbilitiesItem;

public class AbilitiesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public AbilitiesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    public ArrayList<AbilitiesItem> getAbilities(String apc) {
        ArrayList<AbilitiesItem> abilityItem = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelect = "SELECT abilities._id, abilities.ablName, abilities.ablDesc, abilities.ablID, abilities.ablIsPassive, abilities.ablIconSpec, abilities.ablActionPointCost, abilities.ablGlobalCooldownTime, abilities.ablCooldownTime, abilities.ablCastingTime, abilities.ablChannelingTime, abilities.ablForceCost, abilities.ablEnergyCost, abilities.ablMinRange, abilities.ablMaxRange, abilities.ablNode, apc.ablLevelAquired, classes.clsResource\n" +
                "FROM abilities\n" +
                "LEFT JOIN apc\n" +
                "ON abilities.ablNode = apc.Node\n" +
                "LEFT JOIN classes\n" +
                "ON apc.NodeCat = classes.clsApc\n" +
                "WHERE apc.NodeCat = ?\n" +
                "ORDER BY apc.ablLevelAquired, abilities.ablName";
        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(apc)});

        if (c.moveToFirst()) {
            do {

                String ablName = c.getString(c.getColumnIndex("ablName"));
                String ablDesc = c.getString(c.getColumnIndex("ablDesc"));
                String ablIsPassive = c.getString(c.getColumnIndex("ablIsPassive"));
                String ablIconSpec = c.getString(c.getColumnIndex("ablIconSpec"));
                int ablGlobalCooldownTime = c.getInt(c.getColumnIndex("ablGlobalCooldownTime"));
                int ablActionPointCost = c.getInt(c.getColumnIndex("ablActionPointCost"));
                int ablCooldownTime = c.getInt(c.getColumnIndex("ablCooldownTime"));
                int ablCastingTime = c.getInt(c.getColumnIndex("ablCastingTime"));
                int ablChannelingTime = c.getInt(c.getColumnIndex("ablChannelingTime"));
                int ablForceCost = c.getInt(c.getColumnIndex("ablForceCost"));
                int ablEnergyCost = c.getInt(c.getColumnIndex("ablEnergyCost"));
                int ablMinRange = c.getInt(c.getColumnIndex("ablMinRange"));
                int ablMaxRange = c.getInt(c.getColumnIndex("ablMaxRange"));
                String ablNode = c.getString(c.getColumnIndex("ablNode"));
                int ablLevelAquired = c.getInt(c.getColumnIndex("ablLevelAquired"));
                String clsResource = c.getString(c.getColumnIndex("clsResource"));

                abilityItem.add(new AbilitiesItem(ablName, ablDesc, ablIsPassive, ablIconSpec, ablActionPointCost, ablGlobalCooldownTime, ablCooldownTime, ablCastingTime, ablChannelingTime, ablForceCost, ablEnergyCost, ablMinRange, ablMaxRange, ablNode, ablLevelAquired, clsResource));
            } while (c.moveToNext());
        }

        //c.moveToFirst();
        c.close();
        return abilityItem;
    }
}
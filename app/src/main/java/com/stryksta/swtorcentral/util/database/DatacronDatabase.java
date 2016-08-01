package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.models.DatacronItem;

public class DatacronDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public DatacronDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public ArrayList<DatacronItem> getDatacrons() {
		ArrayList<DatacronItem> datacronItems = new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT codexes._id, codexes.cdxTitle as dtcTitle, datacrons.dtcMap, datacrons.dtcLocation, codexes.cdxFaction as dtcFaction, datacrons.dtcReward, datacrons.dtcCoord, codexes.cdxDescription as dtcCodex , codexes.cdxPath as dtcPath\n" +
				"FROM datacrons\n" +
				"LEFT JOIN codexes\n" +
				"ON datacrons.dtcPath = codexes.cdxPath";

		Cursor c = db.rawQuery(sqlSelect, null);

		if (c.moveToFirst()) {
			do {

				String dtcTitle = c.getString(c.getColumnIndex("dtcTitle"));
				String dtcCodex = c.getString(c.getColumnIndex("dtcCodex"));
				String dtcMap = c.getString(c.getColumnIndex("dtcMap"));
				String dtcLocation = c.getString(c.getColumnIndex("dtcLocation"));
				String dtcFaction = c.getString(c.getColumnIndex("dtcFaction"));
				String dtcReward = c.getString(c.getColumnIndex("dtcReward"));
				String dtcCoord = c.getString(c.getColumnIndex("dtcCoord"));
				String dtcPath = c.getString(c.getColumnIndex("dtcPath"));

				datacronItems.add(new DatacronItem(dtcTitle, dtcCodex, dtcMap, dtcLocation, dtcFaction, dtcReward, dtcCoord, dtcPath));
			} while (c.moveToNext());
		}

		//c.moveToFirst();
		c.close();
		return datacronItems;
	}

	public ArrayList<DatacronItem> getDatacronsPerPlanet(String txtPlanet) {
		ArrayList<DatacronItem> datacronItems = new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();

		String sqlSelect = "SELECT codexes._id, codexes.cdxTitle as dtcTitle, datacrons.dtcMap, datacrons.dtcLocation, codexes.cdxFaction as dtcFaction, datacrons.dtcReward, datacrons.dtcCoord, codexes.cdxDescription as dtcCodex , codexes.cdxPath as dtcPath\n" +
				"FROM datacrons\n" +
				"LEFT JOIN codexes\n" +
                "ON datacrons.dtcPath = codexes.cdxPath\n" +
                "WHERE datacrons.dtcLocation = ?";

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtPlanet)});

		if (c.moveToFirst()) {
			do {

				String dtcTitle = c.getString(c.getColumnIndex("dtcTitle"));
				String dtcCodex = c.getString(c.getColumnIndex("dtcCodex"));
				String dtcMap = c.getString(c.getColumnIndex("dtcMap"));
				String dtcLocation = c.getString(c.getColumnIndex("dtcLocation"));
				String dtcFaction = c.getString(c.getColumnIndex("dtcFaction"));
				String dtcReward = c.getString(c.getColumnIndex("dtcReward"));
				String dtcCoord = c.getString(c.getColumnIndex("dtcCoord"));
				String dtcPath = c.getString(c.getColumnIndex("dtcPath"));

				datacronItems.add(new DatacronItem(dtcTitle, dtcCodex, dtcMap, dtcLocation, dtcFaction, dtcReward, dtcCoord, dtcPath));
			} while (c.moveToNext());
		}

		//c.moveToFirst();
		c.close();
		return datacronItems;
	}
}
package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.adapters.SimpleSectionedRecyclerViewAdapter;
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

		String [] sqlSelect = {"0 _id", "faction", "planet", "map", "reward", "location", "reward", "codex", "desc"};
		String sqlTables = "datacrons";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
		
		if (c.moveToFirst()) {
            do {
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String reward = c.getString(c.getColumnIndex("reward"));
            	String location = c.getString(c.getColumnIndex("location"));
            	String codex = c.getString(c.getColumnIndex("codex"));
                String desc = c.getString(c.getColumnIndex("desc"));

                datacronItem.add(new DatacronItem(planet, reward, location, codex, desc));

            } while (c.moveToNext());
        }
		
		//c.moveToFirst();
		c.close();
		return datacronItem;

	}

    public List<SimpleSectionedRecyclerViewAdapter.Section> getDatacronsSections() {
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "faction", "planet", "map", "reward", "location", "reward", "codex", "desc"};
        String sqlTables = "datacrons";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String planet = c.getString(c.getColumnIndex("planet"));
                String prevPlanet = null;

                if (c.getPosition() > 0 && c.moveToPrevious()) {
                    prevPlanet = c.getString(c.getColumnIndex("planet"));
                    c.moveToNext();
                }

                if (prevPlanet == null || !prevPlanet.equals(planet)) {
                    sections.add(new SimpleSectionedRecyclerViewAdapter.Section(c.getPosition(),planet));
                }
            } while (c.moveToNext());
        }

        //c.moveToFirst();
        c.close();
        return sections;

    }

	public ArrayList<DatacronItem> getDatacronsPerPlanet(String txtplanet, String txtfaction) {
		ArrayList<DatacronItem> datacronItem = new ArrayList<DatacronItem>();
		SQLiteDatabase db = getReadableDatabase();
		
		String sqlSelect = "SELECT * FROM datacrons where planet = ? AND (faction= ? OR faction='Both')";
		
		
	    Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtplanet), String.valueOf(txtfaction)});
	        
		if (c.moveToFirst()) {
            do {
            	String planet = c.getString(c.getColumnIndex("planet"));
            	String reward = c.getString(c.getColumnIndex("reward"));
            	String location = c.getString(c.getColumnIndex("location"));
            	String codex = c.getString(c.getColumnIndex("codex"));
                String desc = c.getString(c.getColumnIndex("desc"));

            	datacronItem.add(new DatacronItem(planet, reward, location, codex, desc));
            	
            } while (c.moveToNext());
        }
		
		//c.moveToFirst();
		c.close();
		return datacronItem;

	}
}
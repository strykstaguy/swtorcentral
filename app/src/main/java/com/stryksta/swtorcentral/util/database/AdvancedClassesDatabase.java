package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AdvancedClassesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
	
	public AdvancedClassesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

    public Cursor getAdvancedClasses (long id) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "class_id", "class", "description", "role", "armor", "weapons", "priattribute", "advanced_class_icon", "apc", "adv_bg"};
        String sqlTables = "advanced_classes";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, "_id" + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        c.moveToFirst();
        return c;
    }

    /*
    public ArrayList<AdvancedClassItem> getAdvancedClasses(long id) {
        ArrayList<AdvancedClassItem> advancedClassItems = new ArrayList<AdvancedClassItem>();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelect = "SELECT * FROM advanced_classes WHERE _id = ?";

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            do {

                int ID = c.getInt(c.getColumnIndex("_id"));
                int advClass_ID = c.getInt(c.getColumnIndex("class_id"));
                String advClass = c.getString(c.getColumnIndex("class"));
                String advDescription = c.getString(c.getColumnIndex("description"));
                String advRole = c.getString(c.getColumnIndex("role"));
                String advArmor = c.getString(c.getColumnIndex("armor"));
                String advWeapons = c.getString(c.getColumnIndex("weapons"));
                String advPriAttribute = c.getString(c.getColumnIndex("priattribute"));
                String advAdvanced_class_icon = c.getString(c.getColumnIndex("advanced_class_icon"));
                String advApc = c.getString(c.getColumnIndex("apc"));
                String advAdv_bg = c.getString(c.getColumnIndex("adv_bg"));

                advancedClassItems.add(new AdvancedClassItem(ID, advClass_ID, advClass, advDescription, advRole, advArmor, advWeapons, advPriAttribute, advAdvanced_class_icon, advApc, advAdv_bg));
            } while (c.moveToNext());
        }
        c.close();
        return advancedClassItems;
    }
    */
}
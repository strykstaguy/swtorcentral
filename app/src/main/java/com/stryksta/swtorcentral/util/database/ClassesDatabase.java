package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.AchievementCategoryItem;
import com.stryksta.swtorcentral.data.ClassItem;

import java.util.ArrayList;

public class ClassesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
    Context mContext;

	public ClassesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
	}

	public ArrayList<ClassItem> getClasses() {
		ArrayList<ClassItem> classItems = new ArrayList<>();
		SQLiteDatabase db = getReadableDatabase();

		@SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
				.append("SELECT * FROM classes ")
				.append("ORDER BY _id asc ")
				.toString();

		Cursor c = db.rawQuery(sqlSelect, null);

		if (c.moveToFirst()) {
			do {

                String txtClassName = c.getString(c.getColumnIndex("clsName"));
                int clsFaction = c.getInt(c.getColumnIndex("clsFaction"));
                int txtIcon = mContext.getResources().getIdentifier(c.getString(c.getColumnIndex("clsIcon")), "drawable", mContext.getPackageName());
                //String txtIcon = c.getString(c.getColumnIndex("clsIcon"));
                String txtDescription = c.getString(c.getColumnIndex("clsDescription"));
                String txtResource = c.getString(c.getColumnIndex("clsResource"));
                String txtCombatRole = c.getString(c.getColumnIndex("clsCombatRole"));
                String txtStory = c.getString(c.getColumnIndex("clsStory"));
                String txtAbilities = c.getString(c.getColumnIndex("clsAbilities"));
                String txtEquipment = c.getString(c.getColumnIndex("clsEquipment"));
                String txtApc = c.getString(c.getColumnIndex("clsApc"));
                String txtNode = c.getString(c.getColumnIndex("clsNode"));

                classItems.add(new ClassItem(txtClassName, clsFaction, txtIcon, txtDescription, txtResource, txtCombatRole, txtStory, txtAbilities, txtEquipment, txtApc, txtNode));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return classItems
                ;
	}
	public Cursor getAdvancedClasses(long id) {
		SQLiteDatabase db = getReadableDatabase();
		String sqlSelect = "SELECT * FROM advanced_classes WHERE class_id = ?";

		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
		return c;
	}
}
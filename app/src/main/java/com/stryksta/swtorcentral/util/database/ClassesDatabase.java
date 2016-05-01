package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AdvancedClassItem;
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

                int clsID = c.getInt(c.getColumnIndex("_id"));
                String txtClassName = c.getString(c.getColumnIndex("clsName"));
                int clsFaction = c.getInt(c.getColumnIndex("clsFaction"));
                int txtIcon = mContext.getResources().getIdentifier(c.getString(c.getColumnIndex("clsIcon")), "drawable", mContext.getPackageName());

                //ContextCompat.getDrawable(mContext, R.drawable.ic_check);

                //String txtIcon = c.getString(c.getColumnIndex("clsIcon"));
                String txtDescription = c.getString(c.getColumnIndex("clsDescription"));
                String txtResource = c.getString(c.getColumnIndex("clsResource"));
                String txtCombatRole = c.getString(c.getColumnIndex("clsCombatRole"));
                String txtStory = c.getString(c.getColumnIndex("clsStory"));
                String txtAbilities = c.getString(c.getColumnIndex("clsAbilities"));
                String txtEquipment = c.getString(c.getColumnIndex("clsEquipment"));
                String txtApc = c.getString(c.getColumnIndex("clsApc"));
                String txtNode = c.getString(c.getColumnIndex("clsNode"));

                classItems.add(new ClassItem(clsID, txtClassName, clsFaction, txtIcon, txtDescription, txtResource, txtCombatRole, txtStory, txtAbilities, txtEquipment, txtApc, txtNode));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return classItems;
	}

    public ArrayList<AdvancedClassItem> getAdvancedClasses(int class_id) {
        ArrayList<AdvancedClassItem> advancedClassItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT * FROM advanced_classes ")
                .append("WHERE class_id = ? ")
                .append("ORDER BY _id asc ")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(class_id)});

        if (c.moveToFirst()) {
            do {

                int advClass_id = c.getInt(c.getColumnIndex("_id"));
                String advClass = c.getString(c.getColumnIndex("class"));
                String advDescription = c.getString(c.getColumnIndex("description"));
                String advRole = c.getString(c.getColumnIndex("role"));
                String advArmor = c.getString(c.getColumnIndex("armor"));
                String advWeapons = c.getString(c.getColumnIndex("weapons"));
                String advPriAttribute = c.getString(c.getColumnIndex("priattribute"));
                int advAdvanced_class_icon = mContext.getResources().getIdentifier(c.getString(c.getColumnIndex("advanced_class_icon")), "drawable", mContext.getPackageName());
                String advApc = c.getString(c.getColumnIndex("apc"));
                String advAdv_bg = c.getString(c.getColumnIndex("adv_bg"));

                //int advID, int advClass_id, String advClass, String advDescription, String advRole, String advArmor, String advWeapons, String advPriAttribute, int advAdvanced_class_icon, String advApc, String advAdv_bg
                advancedClassItems.add(new AdvancedClassItem(clsID, txtClassName, clsFaction, txtIcon, txtDescription, txtResource, txtCombatRole, txtStory, txtAbilities, txtEquipment, txtApc, txtNode));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return advancedClassItems;
    }
}
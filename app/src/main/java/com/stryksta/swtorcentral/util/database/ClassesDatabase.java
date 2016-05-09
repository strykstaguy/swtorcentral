package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.data.AdvancedClassItem;
import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.data.DisciplineItem;

import java.util.ArrayList;

public class ClassesDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "swtor";
	private static final int DATABASE_VERSION = 1;
    Context mContext;

	public ClassesDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
	}

    public Cursor getClassTitles () {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT _id, clsName, clsApc FROM classes ")
                .append("ORDER BY _id asc ")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, null);

        c.moveToFirst();
        return c;
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

    public ArrayList<AdvancedClassItem> getAdvancedClasses(String txtClassName) {
        ArrayList<AdvancedClassItem> advancedClassItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT advanced_classes._id, advanced_classes.advClassName, classes.clsName, advanced_classes.advDescription, advanced_classes.advRole, advanced_classes.advArmor, advanced_classes.advWeapons, advanced_classes.advPrimaryAttribute, advanced_classes.advIcon, advanced_classes.advAPN ")
                .append("FROM advanced_classes ")
                .append("LEFT JOIN classes ")
                .append("ON advanced_classes.clsID = classes._id ")
                .append("WHERE classes.clsName = ? ")
                .append("ORDER BY advanced_classes._id asc ")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtClassName)});

        if (c.moveToFirst()) {
            do {

                int advClass_id = c.getInt(c.getColumnIndex("_id"));
                String advClassName = c.getString(c.getColumnIndex("advClassName"));
                String clsName = c.getString(c.getColumnIndex("clsName"));
                String advDescription = c.getString(c.getColumnIndex("advDescription"));
                String advRole = c.getString(c.getColumnIndex("advRole"));
                String advArmor = c.getString(c.getColumnIndex("advArmor"));
                String advWeapons = c.getString(c.getColumnIndex("advWeapons"));
                String advPriAttribute = c.getString(c.getColumnIndex("advPrimaryAttribute"));
                int advAdvanced_class_icon = mContext.getResources().getIdentifier(c.getString(c.getColumnIndex("advIcon")), "drawable", mContext.getPackageName());
                String advApc = c.getString(c.getColumnIndex("advAPN"));

                advancedClassItems.add(new AdvancedClassItem(advClass_id, advClassName, clsName, advDescription, advRole, advArmor, advWeapons, advPriAttribute, advAdvanced_class_icon, advApc));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return advancedClassItems;
    }

    public ArrayList<DisciplineItem> getDisciplines(int advancedClassID) {
        ArrayList<DisciplineItem> disciplineItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        @SuppressWarnings("StringBufferReplaceableByString") StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
                .append("SELECT disciplines._id, advanced_classes.advClassName, disciplines.sortindex, disciplines.type, disciplines.name, disciplines.description, disciplines.abl, disciplines.apc ")
                .append("FROM disciplines ")
                .append("LEFT JOIN advanced_classes ")
                .append("ON disciplines.advanced_class_id = advanced_classes._id ")
                .append("WHERE disciplines.advanced_class_id = ? ")
                .append("AND disciplines.type IS NOT \"Utility\" ")
                .append("ORDER BY disciplines.sortindex ASC")
                .toString();

        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(advancedClassID)});

        if (c.moveToFirst()) {
            do {

                int disID = c.getInt(c.getColumnIndex("_id"));
                String advClassName = c.getString(c.getColumnIndex("advClassName"));
                int disSortIndex = c.getInt(c.getColumnIndex("sortindex"));
                String disType = c.getString(c.getColumnIndex("type"));
                String disName = c.getString(c.getColumnIndex("name"));
                String disDescription = c.getString(c.getColumnIndex("description"));
                String disAbl = c.getString(c.getColumnIndex("abl"));
                String disApc = c.getString(c.getColumnIndex("apc"));

                //int disID, String advClassName, int disSortIndex, String disType, String disName, String disDescription, String disAbl, String disApc
                disciplineItems.add(new DisciplineItem(disID, advClassName, disSortIndex, disType, disName, disDescription, disAbl, disApc));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return disciplineItems;
    }
}
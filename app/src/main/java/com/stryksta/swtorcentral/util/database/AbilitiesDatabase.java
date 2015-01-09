package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<AbilitiesItem> getClassAbilities(String apc) {
        ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
        SQLiteDatabase db = getReadableDatabase();

        String sqlSelect = "SELECT abilities._id, abilities.ablClass_ID, abilities.ablAdvanced_Class_ID, abilities.ablID, abilities.ablIsPassive , abilities.ablIconSpec, abilities.ablGlobalCooldownTime, abilities.ablCooldownTime , abilities.ablCastingTime, abilities.ablForceCost, abilities.ablEnergyCost, abilities.ablMinRange, abilities.ablMaxRange, abilities.ablName, abilities.ablDesc, abilities.ablNode, apc.ablLevelAquired\n" +
                "FROM abilities\n" +
                "LEFT JOIN apc\n" +
                "ON abilities.ablNode = apc.Node\n" +
                "WHERE apc.NodeCat = ?";
        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(apc)});

        if (c.moveToFirst()) {
            do {

                int ID = c.getInt(c.getColumnIndex("_id"));
                int ablClass_ID = c.getInt(c.getColumnIndex("ablClass_ID"));
                int ablAdvanced_Class_ID = c.getInt(c.getColumnIndex("ablAdvanced_Class_ID"));
                int ablID = c.getInt(c.getColumnIndex("ablID"));
                String ablIsPassive = c.getString(c.getColumnIndex("ablIsPassive"));
                String ablIconSpec = c.getString(c.getColumnIndex("ablIconSpec"));
                int ablGlobalCooldownTime = c.getInt(c.getColumnIndex("ablGlobalCooldownTime"));
                int ablCooldownTime = c.getInt(c.getColumnIndex("ablCooldownTime"));
                int ablCastingTime = c.getInt(c.getColumnIndex("ablCastingTime"));
                int ablForceCost = c.getInt(c.getColumnIndex("ablForceCost"));
                int ablEnergyCost = c.getInt(c.getColumnIndex("ablEnergyCost"));
                int ablMinRange = c.getInt(c.getColumnIndex("ablMinRange"));
                int ablMaxRange = c.getInt(c.getColumnIndex("ablMaxRange"));
                String ablName = c.getString(c.getColumnIndex("ablName"));
                String ablDesc = c.getString(c.getColumnIndex("ablDesc"));
                String ablNode = c.getString(c.getColumnIndex("ablNode"));

                abilityItem.add(new AbilitiesItem("", ID, ablClass_ID, ablAdvanced_Class_ID, ablID, ablIsPassive, ablIconSpec, ablGlobalCooldownTime, ablCooldownTime, ablCastingTime, ablForceCost, ablEnergyCost, ablMinRange, ablMaxRange, ablName, ablDesc, ablNode));
            } while (c.moveToNext());
        }

        //c.moveToFirst();
        c.close();
        return abilityItem;

    }

     public ArrayList<AbilitiesItem> getAdvancedClassAbilities(long id) {
  		ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
  		SQLiteDatabase db = getReadableDatabase();

  		String sqlSelect = "SELECT * FROM ability WHERE advanced_class_id = ? AND rank <= 1 order by level asc, rank asc";
  		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
  		
  		if (c.moveToFirst()) {
              do {
              	int abilityID = c.getInt(c.getColumnIndex("_id"));
              	int classID = c.getInt(c.getColumnIndex("class_id"));
              	int advancedID = c.getInt(c.getColumnIndex("advanced_class_id"));
              	int skilltreeID = c.getInt(c.getColumnIndex("skill_tree_id"));
              	int dependonID = c.getInt(c.getColumnIndex("depends_on_id"));
              	int level = c.getInt(c.getColumnIndex("level"));
              	int credits = c.getInt(c.getColumnIndex("credits"));
              	String txtName = c.getString(c.getColumnIndex("name"));
              	int rank = c.getInt(c.getColumnIndex("rank"));
              	String summary = c.getString(c.getColumnIndex("summary"));
              	String description = c.getString(c.getColumnIndex("description"));
              	String footnote = c.getString(c.getColumnIndex("footnote"));
              	String highlight = c.getString(c.getColumnIndex("highlight"));
              	int resource = c.getInt(c.getColumnIndex("resource"));
              	int passive = c.getInt(c.getColumnIndex("passive"));
              	String activation = c.getString(c.getColumnIndex("activation"));
              	String channeled = c.getString(c.getColumnIndex("channeled"));
              	String cooldown = c.getString(c.getColumnIndex("cooldown"));
              	String range = c.getString(c.getColumnIndex("range"));
              	
              	//abilityItem.add(new AbilitiesItem("", abilityID, classID, advancedID, skilltreeID, dependonID, level, credits, txtName, rank, summary, description, footnote, highlight, resource, passive, activation, channeled, cooldown, range));
              } while (c.moveToNext());
          }
  		
  		//c.moveToFirst();
  		c.close();
  		return abilityItem;

  	}
     public HashMap<String, Integer> getSkills(long id) {
    	 HashMap<String, Integer> abilityItem = new HashMap<String, Integer>();
   		SQLiteDatabase db = getReadableDatabase();

   		String sqlSelect = "SELECT _id, name FROM skill_tree WHERE advanced_class_id = ? ORDER BY position asc";
   		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
   		
   		if (c.moveToFirst()) {
               do {
               	int skillID = c.getInt(c.getColumnIndex("_id"));
               	String skillName = c.getString(c.getColumnIndex("name"));
               	
               	abilityItem.put(skillName, skillID);
               	
               } while (c.moveToNext());
           }
   		
   		//c.moveToFirst();
   		c.close();
   		return abilityItem;

   	}
     
     public ArrayList<AbilitiesItem> getSkillAbilities(long id) {
   		ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
   		SQLiteDatabase db = getReadableDatabase();

   		String sqlSelect = "SELECT * FROM ability WHERE skill_tree_id = ? AND rank <= 1 order by _id asc";
   		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
   		
   		if (c.moveToFirst()) {
               do {
               	int abilityID = c.getInt(c.getColumnIndex("_id"));
               	int classID = c.getInt(c.getColumnIndex("class_id"));
               	int advancedID = c.getInt(c.getColumnIndex("advanced_class_id"));
               	int skilltreeID = c.getInt(c.getColumnIndex("skill_tree_id"));
               	int dependonID = c.getInt(c.getColumnIndex("depends_on_id"));
               	int level = c.getInt(c.getColumnIndex("level"));
               	int credits = c.getInt(c.getColumnIndex("credits"));
               	String txtName = c.getString(c.getColumnIndex("name"));
               	int rank = c.getInt(c.getColumnIndex("rank"));
               	String summary = c.getString(c.getColumnIndex("summary"));
               	String description = c.getString(c.getColumnIndex("description"));
               	String footnote = c.getString(c.getColumnIndex("footnote"));
               	String highlight = c.getString(c.getColumnIndex("highlight"));
               	int resource = c.getInt(c.getColumnIndex("resource"));
               	int passive = c.getInt(c.getColumnIndex("passive"));
               	String activation = c.getString(c.getColumnIndex("activation"));
               	String channeled = c.getString(c.getColumnIndex("channeled"));
               	String cooldown = c.getString(c.getColumnIndex("cooldown"));
               	String range = c.getString(c.getColumnIndex("range"));
               	
               	//abilityItem.add(new AbilitiesItem("", abilityID, classID, advancedID, skilltreeID, dependonID, level, credits, txtName, rank, summary, description, footnote, highlight, resource, passive, activation, channeled, cooldown, range));
               } while (c.moveToNext());
           }
   		
   		//c.moveToFirst();
   		c.close();
   		return abilityItem;

   	}
     
     public ArrayList<AbilitiesItem> getAbilityAdvanced(String advanced_class_id, long id) {
    		ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
    		SQLiteDatabase db = getReadableDatabase();

    		String sqlSelect = "SELECT * FROM ability WHERE name = ? AND advanced_class_id = ? order by rank asc";
    		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(advanced_class_id), String.valueOf(id)});
    		
    		if (c.moveToFirst()) {
                do {
                	int abilityID = c.getInt(c.getColumnIndex("_id"));
                	int classID = c.getInt(c.getColumnIndex("class_id"));
                	int advancedID = c.getInt(c.getColumnIndex("advanced_class_id"));
                	int skilltreeID = c.getInt(c.getColumnIndex("skill_tree_id"));
                	int dependonID = c.getInt(c.getColumnIndex("depends_on_id"));
                	int level = c.getInt(c.getColumnIndex("level"));
                	int credits = c.getInt(c.getColumnIndex("credits"));
                	String txtName = c.getString(c.getColumnIndex("name"));
                	int rank = c.getInt(c.getColumnIndex("rank"));
                	String summary = c.getString(c.getColumnIndex("summary"));
                	String description = c.getString(c.getColumnIndex("description"));
                	String footnote = c.getString(c.getColumnIndex("footnote"));
                	String highlight = c.getString(c.getColumnIndex("highlight"));
                	int resource = c.getInt(c.getColumnIndex("resource"));
                	int passive = c.getInt(c.getColumnIndex("passive"));
                	String activation = c.getString(c.getColumnIndex("activation"));
                	String channeled = c.getString(c.getColumnIndex("channeled"));
                	String cooldown = c.getString(c.getColumnIndex("cooldown"));
                	String range = c.getString(c.getColumnIndex("range"));
                	
                	//abilityItem.add(new AbilitiesItem("", abilityID, classID, advancedID, skilltreeID, dependonID, level, credits, txtName, rank, summary, description, footnote, highlight, resource, passive, activation, channeled, cooldown, range));
                } while (c.moveToNext());
            }
    		
    		//c.moveToFirst();
    		c.close();
    		return abilityItem;

    	}
     
     public ArrayList<AbilitiesItem> getAbilityClass(String class_id, long id) {
 		ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
 		SQLiteDatabase db = getReadableDatabase();

 		String sqlSelect = "SELECT * FROM ability WHERE name = ? AND class_id = ? order by rank asc";
 		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(class_id), String.valueOf(id)});
 		
 		if (c.moveToFirst()) {
             do {
             	int abilityID = c.getInt(c.getColumnIndex("_id"));
             	int classID = c.getInt(c.getColumnIndex("class_id"));
             	int advancedID = c.getInt(c.getColumnIndex("advanced_class_id"));
             	int skilltreeID = c.getInt(c.getColumnIndex("skill_tree_id"));
             	int dependonID = c.getInt(c.getColumnIndex("depends_on_id"));
             	int level = c.getInt(c.getColumnIndex("level"));
             	int credits = c.getInt(c.getColumnIndex("credits"));
             	String txtName = c.getString(c.getColumnIndex("name"));
             	int rank = c.getInt(c.getColumnIndex("rank"));
             	String summary = c.getString(c.getColumnIndex("summary"));
             	String description = c.getString(c.getColumnIndex("description"));
             	String footnote = c.getString(c.getColumnIndex("footnote"));
             	String highlight = c.getString(c.getColumnIndex("highlight"));
             	int resource = c.getInt(c.getColumnIndex("resource"));
             	int passive = c.getInt(c.getColumnIndex("passive"));
             	String activation = c.getString(c.getColumnIndex("activation"));
             	String channeled = c.getString(c.getColumnIndex("channeled"));
             	String cooldown = c.getString(c.getColumnIndex("cooldown"));
             	String range = c.getString(c.getColumnIndex("range"));
             	
             	//abilityItem.add(new AbilitiesItem("", abilityID, classID, advancedID, skilltreeID, dependonID, level, credits, txtName, rank, summary, description, footnote, highlight, resource, passive, activation, channeled, cooldown, range));
             } while (c.moveToNext());
         }
 		
 		//c.moveToFirst();
 		c.close();
 		return abilityItem;

 	}
     
     public ArrayList<AbilitiesItem> getAbilitySkill(String skill, long skill_tree_id) {
	 		ArrayList<AbilitiesItem> abilityItem = new ArrayList<AbilitiesItem>();
	 		SQLiteDatabase db = getReadableDatabase();

	 		String sqlSelect = "SELECT * FROM ability WHERE name = ? AND skill_tree_id = ? order by rank asc";
	 		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(skill), String.valueOf(skill_tree_id)});
	 		
	 		if (c.moveToFirst()) {
	             do {
	             	int abilityID = c.getInt(c.getColumnIndex("_id"));
	             	int classID = c.getInt(c.getColumnIndex("class_id"));
	             	int advancedID = c.getInt(c.getColumnIndex("advanced_class_id"));
	             	int skilltreeID = c.getInt(c.getColumnIndex("skill_tree_id"));
	             	int dependonID = c.getInt(c.getColumnIndex("depends_on_id"));
	             	int level = c.getInt(c.getColumnIndex("level"));
	             	int credits = c.getInt(c.getColumnIndex("credits"));
	             	String txtName = c.getString(c.getColumnIndex("name"));
	             	int rank = c.getInt(c.getColumnIndex("rank"));
	             	String summary = c.getString(c.getColumnIndex("summary"));
	             	String description = c.getString(c.getColumnIndex("description"));
	             	String footnote = c.getString(c.getColumnIndex("footnote"));
	             	String highlight = c.getString(c.getColumnIndex("highlight"));
	             	int resource = c.getInt(c.getColumnIndex("resource"));
	             	int passive = c.getInt(c.getColumnIndex("passive"));
	             	String activation = c.getString(c.getColumnIndex("activation"));
	             	String channeled = c.getString(c.getColumnIndex("channeled"));
	             	String cooldown = c.getString(c.getColumnIndex("cooldown"));
	             	String range = c.getString(c.getColumnIndex("range"));
	             	
	             	//abilityItem.add(new AbilitiesItem("", abilityID, classID, advancedID, skilltreeID, dependonID, level, credits, txtName, rank, summary, description, footnote, highlight, resource, passive, activation, channeled, cooldown, range));
	             } while (c.moveToNext());
	         }
	 		//c.moveToFirst();
	 		c.close();
	 		return abilityItem;

	 	}
}
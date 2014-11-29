package com.stryksta.swtorcentral.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RssDatabaseHelper extends SQLiteOpenHelper {

private final String TAG = "DatabaseHelper";
private static final String DATABASE_NAME = "news";
private static final int DATABASE_VERSION = 1;


public RssDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);

    //fdd
    Log.i(TAG, "Object created.");
}

@Override
public void onCreate(SQLiteDatabase db) {

    String CREATE_RSSS_TABLE = "CREATE TABLE news (" +
    		                "title TEXT," +
    		                "link TEXT," +
    		                "description TEXT," +
    		                "image TEXT," +
    		                "published DATETIME)";
    db.execSQL(CREATE_RSSS_TABLE);

}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(RssDatabaseHelper.class.getName(), "Upgrading database from version "
            + oldVersion + " to " + newVersion + ", which will destroy all old data");
    db.execSQL("Drop table if exists news" );


    onCreate(db);
}

}
package com.stryksta.swtorcentral.util.database;

import java.util.ArrayList;

import com.stryksta.swtorcentral.data.RssItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TestDatabaseHandler {

private TestDatabaseHelper dbHelper;
private SQLiteDatabase database;
Context context;

//methods for all table

public TestDatabaseHandler(Context context) {
    dbHelper = new TestDatabaseHelper(context);
    this.context = context;

}

public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
}

public void close() {
    dbHelper.close();
}

public void clearTable(String tableName) {
    database.delete( tableName, null, null);
}

//news table method

public void insertNewsInfo(RssItem newsInfo) {
    ContentValues cv = new ContentValues();
    cv.put("title", newsInfo.getTitle());
    cv.put("link", newsInfo.getLink());
    cv.put("description", newsInfo.getDescription());
    cv.put("image", newsInfo.getImage());
    
 // Check if row already existed in database
 	if (!isSiteExists(database, newsInfo.getLink())) {
 		// site not existed, create a new row
 		database.insert("news", "title", cv);
 		//database.close();
 	} else {
 		// rss link already
 		//database.close();
 	}
}


public ArrayList<RssItem> getNews() {   
	ArrayList<RssItem> NewsInfoList = new ArrayList<RssItem>();

    Cursor cursor = database.rawQuery("select title " +
            " , link , description, image" +
            " FROM news", null);


    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	RssItem newsInfo = new RssItem();
        newsInfo.setTitle(cursor.getString(0));
        newsInfo.setDescription(cursor.getString(1));
        newsInfo.setLink(cursor.getString(2));
        newsInfo.setImage(cursor.getString(3));

        NewsInfoList.add(newsInfo);
        cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();

    return NewsInfoList;
}

public String getBigBody(String completeBodyLink) {
    Cursor cursor = database.rawQuery("select bigBody FROM news where completeTextLink = ?", new String[]{completeBodyLink});
    cursor.moveToFirst();
    return cursor.getString(0);
}   

/**
 * Checking whether a site is already existed check is done by matching rss
 * link
 * */
public boolean isSiteExists(SQLiteDatabase db, String rss_link) {

	Cursor cursor = db.rawQuery("SELECT 1 FROM " + "news"
			+ " WHERE link = '" + rss_link + "'", new String[] {});
	boolean exists = (cursor.getCount() > 0);
	return exists;
}

}
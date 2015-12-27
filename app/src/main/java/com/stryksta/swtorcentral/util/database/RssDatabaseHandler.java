package com.stryksta.swtorcentral.util.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.stryksta.swtorcentral.data.RssItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RssDatabaseHandler {

private RssDatabaseHelper dbHelper;
private SQLiteDatabase database;
Context context;

//methods for all table

public RssDatabaseHandler(Context context) {
    dbHelper = new RssDatabaseHelper(context);
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
    cv.put("category", newsInfo.getCategory());
    cv.put("image", newsInfo.getImage());
    cv.put("published", getDateTime(newsInfo.getPubDate()));
    //cv.put("published", "2014-05-01 14:09:14");
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

   /* Cursor cursor = database.rawQuery("select title " +
            " , link , description, image" +
            " FROM news", null);
   */
	
	Cursor cursor = database.rawQuery("select title, link , description, category, image FROM news order by published desc", null);
	
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	RssItem newsInfo = new RssItem();
        	newsInfo.setTitle(cursor.getString(0));
        newsInfo.setLink(cursor.getString(1));
        newsInfo.setDescription(cursor.getString(2));
        newsInfo.setCategory(cursor.getString(3));
			newsInfo.setImage(cursor.getString(4));
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

private String getDateTime(String updated) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault());
	SimpleDateFormat reformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
	Date date = new Date();
 
	try {
 
		date = formatter.parse(updated);
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	return reformatter.format(date);
}

}
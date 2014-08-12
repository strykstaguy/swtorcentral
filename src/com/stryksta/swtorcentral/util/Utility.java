package com.stryksta.swtorcentral.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class Utility {
	
	//Utility class is main role here
	//Before inserting image and retrive image from SQLite Database we use these below methods.
	//getBytes() Convert bitmap image to byte array and retrun byte[]
	//getImage() convert byte[] to bitmap and retrun bitmap image
	
 // convert from bitmap to byte array
 public static byte[] getBytes(Bitmap bitmap) {
  ByteArrayOutputStream stream = new ByteArrayOutputStream();
  bitmap.compress(CompressFormat.PNG, 0, stream);
  return stream.toByteArray();
 }

  // convert from byte array to bitmap
 public static Bitmap getImage(byte[] image) {
  return BitmapFactory.decodeByteArray(image, 0, image.length);
 }
}

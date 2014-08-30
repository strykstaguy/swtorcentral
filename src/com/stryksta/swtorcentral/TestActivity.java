package com.stryksta.swtorcentral;
 
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
 
public class TestActivity extends FragmentActivity {
	ProgressDialog pDialog;
	VideoView videoView;
    String videoTitle = "New Play Tutorial";
	String VideoURL = "http://cdn-www.swtor.com/sites/all/files/en/vc/tutorials/01_movement.mp4";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.test_main);
        
        //ActionBar actionbar = getActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeButtonEnabled(true);
        
        //getActionBar().setTitle("Test");
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	VideoURL = bundle.getString("videourl");
        	videoTitle = bundle.getString("title");
        }
        
        videoView = (VideoView) findViewById(R.id.VideoView);
        
        new StreamVideo().execute();
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
 // StreamVideo AsyncTask
 	private class StreamVideo extends AsyncTask<Void, Void, Void> {
 		protected void onPreExecute() {
 			super.onPreExecute();
 			// Create a progressbar
 			pDialog = new ProgressDialog(TestActivity.this);
 			// Set progressbar title
 			pDialog.setTitle(videoTitle);
 			// Set progressbar message
 			pDialog.setMessage("Loading...");
 			pDialog.setIndeterminate(false);
 			// Show progressbar
 			pDialog.show();

 		}

 		@Override
 		protected Void doInBackground(Void... params) {
 			// TODO Auto-generated method stub
 			return null;
 		}

 		@Override
 		protected void onPostExecute(Void args) {

 			try {
 				// Start the MediaController
 				MediaController mediacontroller = new MediaController(TestActivity.this);
 				mediacontroller.setAnchorView(videoView);
 				// Get the URL from String VideoURL
 				Uri video = Uri.parse(VideoURL);
 				videoView.setMediaController(mediacontroller);
 				videoView.setVideoURI(video);

 				videoView.requestFocus();
 				videoView.setOnPreparedListener(new OnPreparedListener() {
 					// Close the progress bar and play the video
 					public void onPrepared(MediaPlayer mp) {
 						pDialog.dismiss();
 						videoView.start();
 					}
 				});
 				
 				videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
 				    public void onCompletion(MediaPlayer mp) {
 				    	finish();    
 				    }
 				});
 			} catch (Exception e) {
 				pDialog.dismiss();
 				Log.e("Error", e.getMessage());
 				e.printStackTrace();
 			}

 		}

 	}
 	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
}
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
 
public class VideoActivity extends FragmentActivity {
	ProgressDialog pDialog;
	VideoView videoView;

	private int position = 0;
    String videoTitle = "New Play Tutorial";
	String VideoURL = "http://cdn-www.swtor.com/sites/all/files/en/vc/tutorials/01_movement.mp4";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.video_main);
        
        //ActionBar actionbar = getActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeButtonEnabled(true);
        
        //getActionBar().setTitle("Test");
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	VideoURL = bundle.getString("video_url");
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
 			pDialog = new ProgressDialog(VideoActivity.this);
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
 				MediaController mediacontroller = new MediaController(VideoActivity.this);
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
 						videoView.requestFocus();
 						
 						videoView.seekTo(position);
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
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		//we use onSaveInstanceState in order to store the video playback position for orientation change
		savedInstanceState.putInt("Position", videoView.getCurrentPosition());
		videoView.pause();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		//we use onRestoreInstanceState in order to play the video playback from the stored position 
		position = savedInstanceState.getInt("Position");
		videoView.seekTo(position);
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
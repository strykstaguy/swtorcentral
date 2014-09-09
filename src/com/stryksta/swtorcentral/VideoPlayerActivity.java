package com.stryksta.swtorcentral;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;

public class VideoPlayerActivity extends Activity implements
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnVideoSizeChangedListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnInfoListener,
        MediaController.MediaPlayerControl,
        SurfaceHolder.Callback {

    private static final String LOG_TAG = VideoPlayerActivity.class.getSimpleName();

    private static final int VIDEO_CONTROLLER_SHOW_TIME = 2000;

    private String videoUrl;

    private Window window;
    private SurfaceView videoSurfaceView;
    private SurfaceHolder videoSurfaceHolder;

    private MediaPlayer videoPlayer;
    private MediaController videoController;

    private Bundle bundleExtras;

    //set to true on video play start, instead of videoPLayer.isPlaying()
    private volatile boolean videoIsPlayable = false;

    private Handler handler = new Handler();

    private volatile int bufferingPercent;

    //on resume seek to after orientation switch
    private int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        if(savedInstanceState != null){
            lastPosition = savedInstanceState.getInt("playback_location");
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.video_surface_layout);

        videoSurfaceView = (SurfaceView) findViewById(R.id.video_surface_view);
        videoSurfaceHolder = videoSurfaceView.getHolder();
        videoSurfaceHolder.addCallback(this);

        videoController = new MediaController(this);

        bundleExtras = getIntent().getExtras();

        videoUrl = bundleExtras.getString("video_url");

        videoPlayer = new MediaPlayer();

        videoSurfaceView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                videoController.show(VIDEO_CONTROLLER_SHOW_TIME);
            }
        });

    }

    private void playVideo(){
        try{
            videoPlayer.setDataSource(videoUrl);
            videoPlayer.setDisplay(videoSurfaceHolder);
            videoPlayer.prepareAsync();
            videoPlayer.setOnVideoSizeChangedListener(this);
            videoPlayer.setOnBufferingUpdateListener(this);
            videoPlayer.setOnCompletionListener(this);
            videoPlayer.setOnErrorListener(this);
            videoPlayer.setOnPreparedListener(this);
            videoPlayer.setOnSeekCompleteListener(this);
            videoPlayer.setScreenOnWhilePlaying(true);
            videoPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            videoIsPlayable = true; //hacky, just this once
        }catch(IOException e){
            Log.e(LOG_TAG, "error starting video playback", e);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        videoPlayer.pause();
    }

    @Override
    public void onResume(){
        super.onResume();
        videoPlayer.reset();
        videoPlayer.start();
        videoPlayer.seekTo(lastPosition);
        videoController.show(VIDEO_CONTROLLER_SHOW_TIME);
    }

    @Override
    public void onStop(){
        super.onStop();
        videoPlayer.release();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        playVideo();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        //TODO - implement
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        videoPlayer.release();
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        Log.i(LOG_TAG, "video buffering at " + i + " %");
        bufferingPercent = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i(LOG_TAG, "video is complete");
        videoController.show(VIDEO_CONTROLLER_SHOW_TIME);
    }

    public boolean onError(MediaPlayer mediaPlayer, int whatError, int extraErrorInfo) {
        Toast.makeText(this, "video playback error", Toast.LENGTH_SHORT).show();

        switch (whatError){
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.e(LOG_TAG, "unknown media playback error");
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.e(LOG_TAG, "server connection died");
            default:
                Log.e(LOG_TAG, "generic video playback error");
                break;
        }

        switch (extraErrorInfo){
            case MediaPlayer.MEDIA_ERROR_IO:
                Log.e(LOG_TAG, "video IO media error");
                break;
            case MediaPlayer.MEDIA_ERROR_MALFORMED:
                Log.e(LOG_TAG, "media error, malformed");
                break;
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                Log.e(LOG_TAG, "unsupported media content");
                break;
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                Log.e(LOG_TAG, "media timeout error");
                break;
            default:
                Log.e(LOG_TAG, "unknown video playback error");
                break;
        }

        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        videoPlayer.seekTo(lastPosition);
        videoPlayer.start();
        videoController.setMediaPlayer(this);
        videoController.setAnchorView(videoSurfaceView);
        handler.post(new Runnable() {
            public void run() {
                videoController.setEnabled(true);
                videoController.show(VIDEO_CONTROLLER_SHOW_TIME);
            }
        });
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        //TODO - implement
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {
        //TODO - implement me
    }

    public void start() {
        videoPlayer.start();
    }

    public void pause() {
        videoPlayer.pause();
    }

    public int getDuration() {
        return videoPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return videoPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        videoPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return videoPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return bufferingPercent;
    }

    public boolean canPause() {
        if(videoIsPlayable){
            return true;
        }
        return false;
    }

    public boolean canSeekBackward() {
        if(videoIsPlayable && videoPlayer.getCurrentPosition() > 0){
            return true;
        }
        return false;
    }

    public boolean canSeekForward() {
        if(videoIsPlayable && videoPlayer.getCurrentPosition() < videoPlayer.getDuration()){
            return true;
        }
        return false;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int whatInfo, int extraInfo) {
        switch(whatInfo){
            case MediaPlayer.MEDIA_INFO_UNKNOWN:
                Log.i(LOG_TAG, "unknown media information");
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                Log.i(LOG_TAG, "video playback is lagging");
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                Log.i(LOG_TAG, "video rendering start");
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                Log.i(LOG_TAG, "video buffering start");
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                Log.i(LOG_TAG, "video buffering end");
                break;
            case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                Log.i(LOG_TAG, "bad video interlacing");
                break;
            case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                Log.i(LOG_TAG, "media is not seek-able");
                break;
            case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                Log.i(LOG_TAG, "media info mata-data update");
                break;
            default:
                Log.w(LOG_TAG, "unknown what info onInfo callback, number=" + whatInfo);
                break;
        }

        switch(extraInfo){ //this is platform dependant
            default:
                Log.w(LOG_TAG, "unknown extra info onInfo callback, number=" + extraInfo);
                break;
        }

        return true; //metadata was handled
    }

	public int getAudioSessionId() {
		return 0;
	}
}
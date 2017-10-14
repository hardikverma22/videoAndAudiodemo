package com.example.android.videodemo;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.VideoView;
import android.widget.MediaController;
public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void play(View view)
    {
        mediaPlayer.start();
    }
    public void pause(View view)
    {
            mediaPlayer.pause();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.myaudio);

        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol =audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
        int currVol =audioManager.getStreamVolume(audioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar)findViewById(R.id.seekBar);
        volumeControl.setMax(maxVol);
        volumeControl.setProgress(currVol);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("Seekbar changed",Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /*
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.myfile);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        mc.setAnchorView(videoView);
        videoView.start();

         //put this in xml
         <VideoView
        android:id="@+id/videoView"
        android:layout_width="360dp"
        android:layout_height="360dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHorizontal_bias="0.6"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        */
    }
}

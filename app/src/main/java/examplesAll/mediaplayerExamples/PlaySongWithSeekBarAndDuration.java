package examplesAll.mediaplayerExamples;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityPlaySongWithSeekBarAndDurationBinding;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import controllerAll.AppUtilsAll.MyLogger;

public class PlaySongWithSeekBarAndDuration extends AppCompatActivity {
    private ActivityPlaySongWithSeekBarAndDurationBinding viewBinding;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private MediaPlayer mediaPlayer;
    public static int oneTimeOnly = 0;
    private AudioManager audioManager;
    float volumeLevel, maxVolumeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(PlaySongWithSeekBarAndDuration.this, R.layout.activity_play_song_with_seek_bar_and_duration);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(this, R.raw.naino_ki_baat);

        volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        MyLogger.e("checkapp", "volumeLevel  and maxVolumeLevel: " + volumeLevel + " - " + maxVolumeLevel);

        viewBinding.imgPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.imgPause.setVisibility(View.GONE);
                viewBinding.imgPlay.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Playing  music", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                MyLogger.e("checkapp", "start  and final time: " + startTime + " - " + finalTime);

                if (oneTimeOnly == 0) {
                    MyLogger.e("checkapp", "seek bar final time: " + finalTime);
                    viewBinding.seekBar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                viewBinding.totalDuration.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                mediaPlayer.setVolume(volumeLevel, volumeLevel);

                viewBinding.duration.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime))));

                viewBinding.seekBar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);
            }
        });

        viewBinding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.imgPlay.setVisibility(View.GONE);
                viewBinding.imgPause.setVisibility(View.VISIBLE);

                mediaPlayer.pause();
            }
        });


        viewBinding.unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.unmute.setVisibility(View.GONE);
                viewBinding.mute.setVisibility(View.VISIBLE);

                mediaPlayer.setVolume(0.0f, 0.0f);
            }
        });

        viewBinding.mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.mute.setVisibility(View.GONE);
                viewBinding.unmute.setVisibility(View.VISIBLE);

                mediaPlayer.setVolume(volumeLevel, volumeLevel);
                MyLogger.e("checkapp", "mute: " + volumeLevel);
            }
        });


        viewBinding.imgForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp + forwardTime) <= finalTime) {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            viewBinding.duration.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            viewBinding.seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

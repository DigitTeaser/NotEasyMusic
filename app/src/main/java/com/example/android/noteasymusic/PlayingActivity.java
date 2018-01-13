package com.example.android.noteasymusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PlayingActivity extends AppCompatActivity {

    // Set SeekBar max value only for one time.
    public static int oneTimeOnly = 0;
    // Start time of the playing song.
    private int startTime = 0;
    // Indicate whether user has change the song time.
    private boolean isTimeChanged = false;
    // Use MediaPlayer API to play music.
    private MediaPlayer mediaPlayer;
    // Use SeekBar to display the progress of the music.
    private SeekBar seekBar;
    // For update the progress of SeekBar.
    private Handler timeUpdateHandler = new Handler();

    // Update the progress of SeekBar according to the current position of MediaPlayer
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            TextView songStartTime = findViewById(R.id.song_start_time);

            // If user change the song time, jump to the change point. Otherwise, continue playing.
            if (isTimeChanged) {
                mediaPlayer.seekTo(startTime);
                isTimeChanged = !isTimeChanged;
            } else {
                startTime = mediaPlayer.getCurrentPosition();
            }
            songStartTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    startTime)))
            );
            seekBar.setProgress(startTime);
            timeUpdateHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        final ImageView imageView = findViewById(R.id.play_pause_image);
        final TextView songEndTime = findViewById(R.id.song_end_time);

        mediaPlayer = MediaPlayer.create(this, R.raw.dont_panic_coldplay);

        // When the song finished, display the play button
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                imageView.setImageResource(R.drawable.play);
                seekBar.setProgress(0);
            }
        });

        seekBar = findViewById(R.id.seekBar);
        // Set SeekBar Max value only for one time
        final double finalTime = mediaPlayer.getDuration();
        if (oneTimeOnly == 0) {
            seekBar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }
        // Display the end time of the song to TextView songEndTime
        songEndTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    isTimeChanged = true;
                    startTime = progress;
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mediaPlayer.isPlaying()) {
                    // When the song start, display pause button
                    imageView.setImageResource(R.drawable.pause);
                    // Jump to the right progress of the song
                    mediaPlayer.seekTo(startTime);
                    // Start to play the song
                    mediaPlayer.start();

                    // Update song time in UpdateSongTime method
                    timeUpdateHandler.postDelayed(UpdateSongTime, 100);
                } else {
                    // When the song pause, display play button
                    imageView.setImageResource(R.drawable.play);
                    // Pause the song
                    mediaPlayer.pause();
                }
            }
        });
    }
}

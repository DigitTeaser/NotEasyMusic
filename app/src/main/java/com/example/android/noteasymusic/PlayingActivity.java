package com.example.android.noteasymusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PlayingActivity extends AppCompatActivity {

    /**
     * Set SeekBar max value only for one time.
     */
    private static int oneTimeOnly = 0;

    /**
     * Indicate whether the user has change the song time.
     */
    private boolean hasTimeChanged = false;

    /**
     * The boolean indicates whether get Audio Focus.
     */
    private boolean hasAudioFocus = false;

    /**
     * Current position of the song.
     */
    private int songPosition = 0;

    /**
     * Use SeekBar to display the progress of the music.
     */
    private SeekBar seekBar;

    /**
     * Display the play or pause button.
     */
    private ImageButton button_play;

    /**
     * Handles playback of all the sound files.
     */
    private MediaPlayer mMediaPlayer;

    /**
     * Handles audio focus when playing a sound file.
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Changing button image to play button.
            button_play.setImageResource(R.drawable.btn_play);
            // Stop updating the song time.
            timeUpdateHandler.removeCallbacks(UpdateSongTime);
            // Set SeekBar back to start.
            seekBar.setProgress(0);
        }
    };

    /**
     * Instant of the BecomingNoisyReceiver.
     */
    private BecomingNoisyReceiver myNoisyAudioStreamReceiver = new BecomingNoisyReceiver();

    IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);

    /**
     * Create a BroadcastReceiver that listens for the intent whenever it's playing audio.
     * When the device disconnect from Bluetooth/wired speaker or headphone, pause the song.
     */
    private class BecomingNoisyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
                // Changing button image to play button.
                button_play.setImageResource(R.drawable.btn_play);
                // Pause the playback.
                mMediaPlayer.pause();
            }
        }
    }

    /**
     * Handle the update the progress of SeekBar.
     */
    private Handler timeUpdateHandler = new Handler();

    /**
     * Update the progress of SeekBar according to the current position of MediaPlayer.
     */
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            // If user change the song time, jump to the change point. Otherwise, continue playing.
            if (hasTimeChanged) {
                mMediaPlayer.seekTo(songPosition);
                hasTimeChanged = !hasTimeChanged;
            } else {
                songPosition = mMediaPlayer.getCurrentPosition();
            }
            // Set the current time of the song.
            TextView songStartTime = findViewById(R.id.song_start_time);
            songStartTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes((long) songPosition),
                    TimeUnit.MILLISECONDS.toSeconds((long) songPosition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                    songPosition))));
            seekBar.setProgress(songPosition);
            timeUpdateHandler.postDelayed(this, 100);
        }
    };

    /**
     * This listener gets triggered when the {@link SeekBar} states change.
     * (i.e., when the user change the progress of the SeekBar.)
     */
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        hasTimeChanged = true;
                        // The default progress values for min is 0 and max is 100.
                        songPosition = progress;
                    }
                }
            };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            // Changing button image to pause button.
                            button_play.setImageResource(R.drawable.btn_pause);
                            // Wait one second before resume the song.
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // Resume the song.
                                    mMediaPlayer.start();
                                }
                            }, 1000);
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            // Changing button image to play button.
                            button_play.setImageResource(R.drawable.btn_play);
                            // Pause the song.
                            mMediaPlayer.pause();
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            // Set the boolean indicator to false, since we loss Audio Focus.
                            hasAudioFocus = false;
                            // Get the current position of the song.
                            songPosition = mMediaPlayer.getCurrentPosition();
                            // Stop the song.
                            mMediaPlayer.stop();
                            // Changing button image to play button.
                            button_play.setImageResource(R.drawable.btn_play);
                    }
                }
            };

    /**
     * This listener gets triggered whenever the play/pause button was pressed.
     * This is the main controller of the audio playback.
     */
    View.OnClickListener buttonPlayOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!hasAudioFocus) {
                // When we don't have Audio Focus for the playback, we need to request it.
                // Normally it's the first time since PlayingActivity launch, or
                // After Audio Focus loss the user come back and press the play button.

                // Request audio focus so in order to play the audio file.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // After getting Audio Focus, create MediaPlayer and start to play the song.

                    mMediaPlayer = MediaPlayer.create(PlayingActivity.this,
                            R.raw.dont_panic_coldplay);

                    // Set SeekBar Max value only for the first time.
                    final double finalTime = mMediaPlayer.getDuration();
                    if (oneTimeOnly == 0) {
                        seekBar.setMax((int) finalTime);
                        // if the user change the start time before playing the song,
                        // calculate the right position and set the SeekBar for once.
                        songPosition = (int) (songPosition * finalTime / 100);
                        seekBar.setProgress(songPosition);
                        oneTimeOnly = 1;
                    }
                    // Display the end time of the song to TextView songEndTime
                    TextView songEndTime = findViewById(R.id.song_end_time);
                    songEndTime.setText(String.format(Locale.getDefault(), "%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                            finalTime))));

                    // When come back from Audio Focus loss, seek to the song position first.
                    mMediaPlayer.seekTo(songPosition);
                    // Start to play the song
                    mMediaPlayer.start();
                    // Setup a listener on the media player.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    // Register the BecomingNoisyReceiver.
                    registerReceiver(myNoisyAudioStreamReceiver, intentFilter);
                    // Changing button image to pause button
                    button_play.setImageResource(R.drawable.btn_pause);
                    // Set the indicator to true since we get Audio Focus.
                    hasAudioFocus = true;
                    // Update song time in UpdateSongTime method
                    timeUpdateHandler.postDelayed(UpdateSongTime, 100);
                }
            } else if (mMediaPlayer.isPlaying()) {
                // Changing button image to play button
                button_play.setImageResource(R.drawable.btn_play);
                // Pause the song
                mMediaPlayer.pause();
            } else {
                // Changing button image to pause button
                button_play.setImageResource(R.drawable.btn_pause);
                // Pause the song
                mMediaPlayer.start();
                // Update song time in UpdateSongTime method
                timeUpdateHandler.postDelayed(UpdateSongTime, 100);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        // Create and setup the {@link AudioManager} to request audio focus.
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Make sure that the user adjust the correct music stream volume,
        // Even if there’s no music for the current location.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // The SeekBar that display the progress of the playback.
        seekBar = findViewById(R.id.seekBar);
        // When the user drag and drop SeekBar, update the song position.
        seekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

        // The play or pause button controls the media playback.
        button_play = findViewById(R.id.btn_play);
        // Setup the button OnClickListener to handle the playback.
        button_play.setOnClickListener(buttonPlayOnClickListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unregister the BecomingNoisyReceiver.
        unregisterReceiver(myNoisyAudioStreamReceiver);
    }
}

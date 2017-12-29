package com.example.android.noteasymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Set the play and pause ImageView status to true.
    private boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If user click the play and pause Image, show the other icon and switch the status
        final ImageView imageView = findViewById(R.id.play_pause_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    imageView.setImageResource(R.drawable.pause);
                } else {
                    imageView.setImageResource(R.drawable.play);
                }
                isPlaying = !isPlaying;
            }
        });

        // If user click the bottom bar, intent to the PlayingActivity
        setListenerOpenActivity(R.id.now_playing, PlayingActivity.class);
        // If user click the Local View, intent to the LocalActivity
        setListenerOpenActivity(R.id.local_music, LocalActivity.class);
        // If user click the Online View, intent to the OnlineActivity
        setListenerOpenActivity(R.id.online_music, OnlineActivity.class);
    }

    // This function handles the click event and open Activity
    private void setListenerOpenActivity(int viewId, final Class activityToOpen) {
        findViewById(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activityToOpen));
            }
        });
    }
}

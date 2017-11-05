package com.example.android.noteasymusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayingActivity extends AppCompatActivity {

    // Set the play and pause ImageView status to true.
    boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

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
    }
}

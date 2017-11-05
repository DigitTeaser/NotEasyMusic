package com.example.android.noteasymusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    // Set the play and pause ImageView status to true.
    boolean isPlaying = true;

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
        LinearLayout linearLayout = findViewById(R.id.now_playing);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PlayingActivity.class));
            }
        });

        // If user click the Local View, intent to the LocalActivity
        RelativeLayout localRelativeLayout = findViewById(R.id.local_music);
        localRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LocalActivity.class));
            }
        });

        // If user click the Online View, intent to the OnlineActivity
        RelativeLayout onlineRelativeLayout = findViewById(R.id.online_music);
        onlineRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), OnlineActivity.class));
            }
        });
    }
}

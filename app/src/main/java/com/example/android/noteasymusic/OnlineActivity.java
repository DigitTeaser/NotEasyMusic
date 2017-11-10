package com.example.android.noteasymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class OnlineActivity extends AppCompatActivity {

    // Set the play and pause ImageView status to true.
    private boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

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

        // Create an ArrayList of online music info
        ArrayList<OnlineMusic> onlineMusics = new ArrayList<>();
        onlineMusics.add(new OnlineMusic(getString(R.string.now_playing_info), getString(R.string.song_price), R.drawable.coldplay_cover));

        // Create an {@link OnlineMusicAdapter}, whose data source is a list of
        // {@link OnlineMusic}s. The adapter knows how to create list item views for each item
        // in the list.
        OnlineMusicAdapter onlineMusicAdapter = new OnlineMusicAdapter(this, onlineMusics);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = findViewById(R.id.online_list);
        listView.setAdapter(onlineMusicAdapter);
    }
}

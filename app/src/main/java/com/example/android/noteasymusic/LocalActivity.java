package com.example.android.noteasymusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class LocalActivity extends AppCompatActivity {

    // Set the play and pause ImageView status to true.
    private boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

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

        // Create an ArrayList of local songs
        ArrayList<String> localSongs = new ArrayList<>();
        localSongs.add(getString(R.string.now_playing_info));

        // Create an {@link ArrayAdapter}, whose data source is a list of {@link String}s. The
        // adapter knows how to create list items for each item in the list.
        ArrayAdapter localAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, localSongs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_local.xml file.
        ListView listView = findViewById(R.id.local_list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link String} in the list.
        listView.setAdapter(localAdapter);
    }
}

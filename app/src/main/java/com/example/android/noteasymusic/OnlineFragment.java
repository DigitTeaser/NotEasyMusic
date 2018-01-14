package com.example.android.noteasymusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {


    public OnlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.song_list, container, false);

        // Add Contents to the array list
        final List<OnlineMusic> onlineList = new ArrayList<>();
        onlineList.add(new OnlineMusic(getString(R.string.now_playing_info),
                getString(R.string.song_price), R.drawable.coldplay_cover));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list.
        OnlineMusicAdapter mAdapter = new OnlineMusicAdapter(onlineList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Make the {@link RecycleView} use the {@link PlaceAdapter} created above, so that the
        // {@link RecycleView} will display list items for each {@link Place} in the list.
        recyclerView.setAdapter(mAdapter);

        // Setup an OnItemClickListener to handle the click event of the RecyclerView item
        mAdapter.setOnItemClickListener(new OnlineMusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"Item " + position + " was clicked.",Toast.LENGTH_SHORT).show();
            }
        });

        return recyclerView;
    }

}

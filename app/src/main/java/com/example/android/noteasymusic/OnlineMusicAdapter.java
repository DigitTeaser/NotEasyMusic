package com.example.android.noteasymusic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
* {@link onlineMusicAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link OnlineMusic} objects.
* */
public class OnlineMusicAdapter extends ArrayAdapter<OnlineMusic> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context      The current context. Used to inflate the layout file.
     * @param onlineMusics A List of OnlineMusic objects to display in a list
     */
    public OnlineMusicAdapter(Activity context, ArrayList<OnlineMusic> onlineMusics) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, onlineMusics);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.online_list_item, parent, false);
        }

        // Get the {@link OnlineMusic} object located at this position in the list
        OnlineMusic currentOnlineMusic = getItem(position);

        // Find the TextView in the online_list_item.xml layout with the song name
        TextView nameTextView = listItemView.findViewById(R.id.song_name);
        // Get the version name from the current OnlineMusic object and
        // set this text on the name TextView
        nameTextView.setText(currentOnlineMusic.getSongName());

        // Find the TextView in the online_list_item.xml layout with the song price
        TextView numberTextView = listItemView.findViewById(R.id.song_price);
        // Get the version number from the current OnlineMusic object and
        // set this text on the price TextView
        numberTextView.setText(currentOnlineMusic.getSongPrice());

        // Find the ImageView in the list_item.xml layout with the ID song cover
        ImageView iconView = listItemView.findViewById(R.id.song_cover);
        // Get the image resource ID from the current OnlineMusic object and
        // set the image to song cover
        iconView.setImageResource(currentOnlineMusic.getSongCoverId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}

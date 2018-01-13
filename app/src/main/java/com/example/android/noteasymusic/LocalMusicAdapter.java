package com.example.android.noteasymusic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/*
* {@link LocalMusicAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link OnlineMusic} objects.
* */
public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.MyViewHolder> {

    /**
     * Create a new list of {@link OnlineMusic} object.
     */
    private List<LocalMusic> mLocalMusicList;

    /**
     * An implementation of RecyclerVIew OnItemClickListener.
     */
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        mOnItemClickListener = OnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param LocalMusicList A List of LocalMusic objects to display in a list
     */
    public LocalMusicAdapter(List<LocalMusic> LocalMusicList) {
        mLocalMusicList = LocalMusicList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.local_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * Bind the data to the views
     *
     * @param holder   is the custom ViewHolder
     * @param position is the current position in RecyclerView
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        LocalMusic localMusic = mLocalMusicList.get(position);
        holder.textView.setText(localMusic.toString());

        if (mOnItemClickListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mLocalMusicList.size();
    }

    /**
     * {@link MyViewHolder} is an custom {@link RecyclerView.ViewHolder} that provide the ViewHolder
     * for each RecyclerView item based on {@link OnlineMusic} objects.
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MyViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.song);
        }
    }
}

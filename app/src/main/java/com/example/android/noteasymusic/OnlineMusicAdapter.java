package com.example.android.noteasymusic;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
* {@link onlineMusicAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link OnlineMusic} objects.
* */
public class OnlineMusicAdapter extends RecyclerView.Adapter<OnlineMusicAdapter.MyViewHolder> {

    /**
     * Create a new list of {@link OnlineMusic} object.
     */
    private List<OnlineMusic> mOnlineMusicList;

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
     * @param OnlineMusicList A List of OnlineMusic objects to display in a list
     */
    public OnlineMusicAdapter(List<OnlineMusic> OnlineMusicList) {
        mOnlineMusicList = OnlineMusicList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.online_list_item, parent, false);

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
        OnlineMusic onlineMusic = mOnlineMusicList.get(position);
        holder.imageImageView.setImageResource(onlineMusic.getSongCoverId());
        holder.nameTextView.setText(onlineMusic.getSongName());
        holder.priceTextView.setText(onlineMusic.getSongPrice());

        if (mOnItemClickListener != null) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mOnlineMusicList.size();
    }

    /**
     * {@link MyViewHolder} is an custom {@link RecyclerView.ViewHolder} that provide the ViewHolder
     * for each RecyclerView item based on {@link OnlineMusic} objects.
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameTextView, priceTextView;
        ImageView imageImageView;

        MyViewHolder(View view) {
            super(view);

            cardView = view.findViewById(R.id.card_view);
            imageImageView = view.findViewById(R.id.song_cover);
            nameTextView = view.findViewById(R.id.song_name);
            priceTextView = view.findViewById(R.id.song_price);
        }
    }
}

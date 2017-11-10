package com.example.android.noteasymusic;

/**
 * {@link OnlineMusic} represents a single Online Music release.
 * Each object has 3 properties: song name, song price, and song cover resource ID.
 */
public class OnlineMusic {

    // Name of the song (e.g. Don't panic)
    private String mSongName;
    // Price of the song (e.g. $1.50)
    private String mSongPrice;
    // Drawable resource ID of a song cover.
    private int mSongCoverId;

    /*
    * Create a new OnlineMusic object.
    *
    * @param SongName is the name of the song (e.g. Don't panic)
    * @param SongPrice is the corresponding price (e.g. $1.50)
    * @param SongCoverId is drawable reference ID that corresponds to the song
    * */
    public OnlineMusic(String SongName, String SongPrice, int SongCoverId) {
        mSongName = SongName;
        mSongPrice = SongPrice;
        mSongCoverId = SongCoverId;
    }

    /**
     * Get the name of the song
     */
    public String getSongName() {
        return mSongName;
    }

    /**
     * Set the name of the song
     */
    public void setSongName(String SongName) {
        this.mSongName = SongName;
    }

    /**
     * Get the price of the song
     */
    public String getSongPrice() {
        return mSongPrice;
    }

    /**
     * Set the price of the song
     */
    public void setSongPrice(String SongPrice) {
        this.mSongPrice = SongPrice;
    }

    /**
     * Get the song cover resource ID
     */
    public int getSongCoverId() {
        return mSongCoverId;
    }

    /**
     * Set the song cover resource ID
     */
    public void setSongCoverId(int SongCoverId) {
        this.mSongCoverId = SongCoverId;
    }
}

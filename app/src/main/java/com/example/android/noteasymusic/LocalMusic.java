package com.example.android.noteasymusic;

/**
 * {@link LocalMusic} represents a single Online Music release.
 * Each object has 3 properties: song name, song price, and song cover resource ID.
 */
public class LocalMusic {

    // Name of the song (e.g. Don't panic)
    private String mSongName;
    // Singer of the song (e.g. Coldplay)
    private String mSongSinger;

    /*
    * Create a new LocalMusic object.
    *
    * @param SongName is the name of the song (e.g. Don't panic)
    * @param SongSinger is the singer of the song (e.g. Coldplay)
    * */
    public LocalMusic(String SongName, String SongSinger) {
        mSongName = SongName;
        mSongSinger = SongSinger;
    }

    @Override
    public String toString() {
        return mSongName + " - " + mSongSinger;
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
        mSongName = SongName;
    }

    /**
     * Get the singer of the song
     */
    public String getSongSinger() {
        return mSongSinger;
    }

    /**
     * Set the singer of the song
     */
    public void setSongSinger(String SongSinger) {
        mSongSinger = SongSinger;
    }
}

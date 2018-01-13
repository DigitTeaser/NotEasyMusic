package com.example.android.noteasymusic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * Declare the name of the tab
     */
    private static final int TAB_LOCAL = 0;
    private static final int TAB_ONLINE = 1;

    /**
     * Number of the fragments
     */
    private static final int PAGE_COUNT = 2;

    /**
     * Context of the app
     */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm      is the fragment manager that will keep each fragment's state in the adapter
     *                across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_LOCAL:
                return new LocalFragment();
            case TAB_ONLINE:
                return new OnlineFragment();
            default:
                return null;
        }
    }

    /**
     * Set the number of the fragments
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * Return the String that should be displayed for the given page number.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_LOCAL:
                return mContext.getString(R.string.local_music);
            case TAB_ONLINE:
                return mContext.getString(R.string.online_music);
            default:
                return null;
        }
    }
}

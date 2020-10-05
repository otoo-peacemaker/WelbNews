package com.kwesiwelbred.welbnews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new RecyclerViewFragment(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 10;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0)
            return "VICE-News";
        else if(position == 1)
            return "Ary News";  //ary-news
        else if(position ==2)
            return "BBC News";  //bbc-news
        else if(position ==3)
            return "BBC Sport"; //bbc-sport
        else if(position == 4)
            return "USA Today";    //cnbc
        else if(position ==5)
            return "CNN";    //cnn
        else if(position ==6)
            return "Fox News";  //fox-news
        else if(position ==7)
            return "Google News"; //google-news
        else if(position ==8)
            return "The Verge";
        else if(position ==9)
            return "News24";    //news24
        else
        return super.getPageTitle(position);
    }
}

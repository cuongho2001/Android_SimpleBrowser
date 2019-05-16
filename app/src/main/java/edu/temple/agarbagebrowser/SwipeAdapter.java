package edu.temple.agarbagebrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Admin on 4/8/2018.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {

    List<WebViewFragment> list;

    public SwipeAdapter(FragmentManager fm, List<WebViewFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public WebViewFragment getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

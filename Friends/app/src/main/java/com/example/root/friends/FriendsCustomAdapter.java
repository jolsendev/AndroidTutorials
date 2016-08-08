package com.example.root.friends;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by root on 8/5/16.
 */
public class FriendsCustomAdapter {
    private List<Friend> data;
    public FriendsCustomAdapter(FragmentActivity activity, FragmentManager childFragmentManager) {
    }

    public void setData(List<Friend> data) {
        this.data = data;
    }
}

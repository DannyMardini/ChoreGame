package com.mardinistudios.choregame;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mardinid on 3/27/16.
 */
public class ChoreListFragment extends ListFragment {
    private List<HashMap<String, String>> data;
    private ListAdapter adapter;

    public ChoreListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<HashMap<String, String>>();

        String[] chores = new String[] {
                "Do the dishes",
                "Take out the trash",
                "clean up after the dog",
                "Make the oats",
                "Water the plants"
        };

        for(String chore : chores) {
            HashMap<String, String> row = new HashMap<String, String>();
            row.put("title", chore);
            data.add(row);
        }

        String[] from = new String[] { "title" };
        int[] to = new int[] { R.id.choreName };

        this.adapter = new SimpleAdapter(getActivity(), data, R.layout.chore_list, from, to);
        setListAdapter(this.adapter);
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.chore_list, container, false);
//        return v;
//    }
}

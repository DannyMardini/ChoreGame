package com.mardinistudios.choregame;

import android.app.Fragment;
import android.app.ListFragment;
import android.database.DataSetObserver;
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
    private SimpleAdapter adapter;

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

        int[] points = new int[] {
                3,
                2,
                1,
                1,
                1
        };

        for (int i = 0; i < chores.length; i++) {
            String chore = chores[i];
            int point = points[i];
            HashMap<String, String> row = new HashMap<String, String>();
            row.put("title", chore);
            row.put("points", Integer.toString(point));
            data.add(row);
        }

        String[] from = new String[] { "checkbox", "title", "points" };
        int[] to = new int[] { R.id.choreCheckbox, R.id.choreName, R.id.chorePoints };

        this.adapter = new SimpleAdapter(getActivity(), data, R.layout.chore_list, from, to);
        setListAdapter(adapter);
    }

    public void addNewChore() {
        HashMap<String, String> newRow = new HashMap<String, String>();
        newRow.put("title", "New Chore");
        newRow.put("points", "0");
        data.add(newRow);
        adapter.notifyDataSetChanged();
    }
}

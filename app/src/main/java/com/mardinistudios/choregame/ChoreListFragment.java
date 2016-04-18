package com.mardinistudios.choregame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mardinistudios.choregame.data.Chore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mardinid on 3/27/16.
 */
public class ChoreListFragment extends Fragment {
    private static final String TAG = "ChoreListFragment";
    private List<Chore> data;
    private ChoreListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<Chore>();

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
            String name = chores[i];
            int point = points[i];
            Chore chore = new Chore(name, point);
            data.add(chore);
        }
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView list = (ListView) getActivity().findViewById(R.id.list);
        this.adapter = new ChoreListAdapter(getActivity(), data, R.layout.chore_list);
        list.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chore_list_fragment, container, false);
    }

    public void addNewChore() {
        HashMap<String, String> newRow = new HashMap<String, String>();
        Chore chore = new Chore();
        data.add(chore);
        adapter.notifyDataSetChanged();
    }
}

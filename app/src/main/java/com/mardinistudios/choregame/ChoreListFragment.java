package com.mardinistudios.choregame;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mardinistudios.choregame.data.Chore;
import com.mardinistudios.choregame.data.DataStub;
import com.mardinistudios.choregame.listeners.FabClickListener;

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

        data = DataStub.GetChores();
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Activity activity = getActivity();

        ListView list = (ListView) activity.findViewById(R.id.list);
        this.adapter = new ChoreListAdapter(getActivity(), data, R.layout.chore_list);
        list.setAdapter(adapter);

        final FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        final FabClickListener fabClickListener = new FabClickListener(this);
        fab.setOnClickListener(fabClickListener);

        adapter.setSelectedItemsListener(new ChoreListAdapter.SelectedItemsListener() {
            @Override
            public void onItemsSelected() {
                Log.i(TAG, "Items were selected!");
                fab.setImageResource(R.drawable.minus_icon);
                fabClickListener.setAction(FabClickListener.Action.REMOVING);
            }

            @Override
            public void onZeroItemsSelected() {
                Log.i(TAG, "Zero items selected!");
                fab.setImageResource(android.R.drawable.ic_input_add);
                fabClickListener.setAction(FabClickListener.Action.ADDING);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.chore_list_fragment, container, false);
    }

    public void addNewChore() {
        Chore chore = new Chore();
        adapter.addChore(chore);
    }

    public void removeSelectedChores() {
        adapter.removeSelectedChores();
    }
}

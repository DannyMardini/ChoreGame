package com.mardinistudios.choregame.listeners;

import android.view.View;

import com.mardinistudios.choregame.ChoreListFragment;

public class FabClickListener implements View.OnClickListener {
    private ChoreListFragment list;

    private Action action;

    public FabClickListener(final ChoreListFragment list) {
        this.list = list;
        action = Action.ADDING;
    }

    @Override
    public void onClick(View view) {
        if (action == Action.ADDING) {
            list.addNewChore();
        } else {
            list.removeSelectedChores();
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public enum Action {
        ADDING,
        REMOVING
    }
}

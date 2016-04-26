package com.mardinistudios.choregame;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mardinistudios.choregame.data.Chore;

import java.util.HashSet;
import java.util.List;

public class ChoreListAdapter extends BaseAdapter implements ListAdapter {
    private final static String TAG = "ChoreListAdapter";

    private final Context context;

    private final List<Chore> data;

    private final int resource;

    private final HashSet<Integer> selectedItems;

    /**
     * Constructor
     * @param context The context.
     * @param data The data that this adapter contains.
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from A list of column names that will be added to the Map associated with each item.
     * @param to The views that should display column in the "from" parameter. These should all be
     *           TextViews. The first N views in this list are given the values of the first N
     *           columns in the from parameter.
     */
    public ChoreListAdapter(final Context context, final List<Chore> data,
                            final int resource) {
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.selectedItems = new HashSet<Integer>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (position >= getCount()) {
            return null;
        }

        Chore chore = data.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);
        EditText choreName = (EditText) rowView.findViewById(R.id.choreName);
        choreName.setText(chore.getName());

        choreName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    final String newName = v.getText().toString();
                    Log.i(TAG, "DONE editing, text is: " + newName + ", position: " + position);
                    v.clearFocus();
                    Chore c = (Chore) getItem(position);
                    c.setName(newName);
                    return true;
                }
                return false;
            }
        });

        TextView points = (TextView) rowView.findViewById(R.id.chorePoints);
        points.setText(String.valueOf(chore.getPointValue()));

        CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.choreCheckbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "box " + position + " is Checked: " + Boolean.toString(isChecked));
                if (isChecked) {
                    selectItem(position);
                } else {
                    deselectItem(position);
                }
            }
        });

        return rowView;
    }

    private void selectItem(int position) {
        selectedItems.add(position);
    }

    private void deselectItem(int position) {
        selectedItems.remove(position);
    }
}

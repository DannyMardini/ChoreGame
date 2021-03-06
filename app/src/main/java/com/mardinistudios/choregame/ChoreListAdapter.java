package com.mardinistudios.choregame;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

    private final HashSet<Chore> selectedItems;

    private final InputMethodManager imm;

    private SelectedItemsListener listener = null;

    /**
     * Constructor
     * @param context The context.
     * @param data The data that this adapter contains.
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     */
    public ChoreListAdapter(final Context context, final List<Chore> data,
                            final int resource) {
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.selectedItems = new HashSet<>();
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
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
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    final String newName = v.getText().toString();
                    Log.i(TAG, "DONE editing, text is: " + newName + ", position: " + position);
                    Chore c = (Chore) getItem(position);
                    c.setName(newName);
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);   //Close the keyboard.
                    v.clearFocus();
                    return true;
                }
                return false;
            }
        });

        TextView points = (TextView) rowView.findViewById(R.id.chorePoints);
        points.setText(String.valueOf(chore.getPointValue()));

        points.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    final int newPointValue = Integer.decode(v.getText().toString());
                    Log.i(TAG, "DONE editing, new point view: " + newPointValue);
                    Chore c = (Chore) getItem(position);
                    c.setPointValue(newPointValue);
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
                    v.clearFocus();
                    return true;
                }
                return false;
            }
        });

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

    public void addChore(Chore chore) {
        if (chore != null) {
            data.add(chore);
            notifyDataSetChanged();
        }
    }

    public void removeSelectedChores() {
        if (!selectedItems.isEmpty()) {
            for (Chore chore : selectedItems) {
                data.remove(chore);
            }
            selectedItems.clear();
            updateZeroItemsSelectedListener();
            notifyDataSetChanged();
        }
    }

    private void selectItem(int position) {
        int startSize = selectedItems.size();
        if (selectedItems.add(data.get(position)) && startSize == 0) {
            updateOnItemsSelectedListener();
        }
    }

    private void deselectItem(int position) {
        if (selectedItems.remove(data.get(position)) && selectedItems.size() == 0) {
            updateZeroItemsSelectedListener();
        }
    }

    private void updateOnItemsSelectedListener() {
        if (listener != null) {
            listener.onItemsSelected();
        }
    }

    private void updateZeroItemsSelectedListener() {
        if (listener != null) {
            listener.onZeroItemsSelected();
        }
    }

    public void setSelectedItemsListener(SelectedItemsListener listener) {
        this.listener = listener;
    }

    public interface SelectedItemsListener {
        /** Called when any item is selected (when the first item is selected only). */
        void onItemsSelected();
        /** Called when no items are selected. */
        void onZeroItemsSelected();
    }
}

package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Button buttons;
    private Context context;
    private String[] items;
    private boolean[] toggles;
    private ArrayList<object> dataObjects;
    public CustomAdapter(Context context, ArrayList<object> dataObjects) {
        this.context = context;
        this.dataObjects = dataObjects;
        this.items = new String[dataObjects.size()];
        this.toggles = new boolean[dataObjects.size()];
    }


    @Override
    public int getCount() {
        return dataObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return dataObjects.get(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        TextView textView;
        ToggleButton toggle;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_1, parent, false);
            textView = view.findViewById(R.id.text_view);
            toggle = view.findViewById(R.id.toggle_switch);
            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        python_2 hari = new python_2("light",dataObjects.get(position).port,"1",context);
                    } else {
                        python_2 second = new python_2("light", dataObjects.get(position).port,"0", context);
                    }
                }
            });

            textView.setText(dataObjects.get(position).name);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private static class ViewHolder {
        TextView text;
        ToggleButton toggle;
    }

    public boolean[] getToggles() {
        return toggles;
    }
}


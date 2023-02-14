package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class List_Adaptor extends BaseAdapter {
    public ArrayList<User> list;
    private Context context;

    public List_Adaptor(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void removeItem(int i) {
        MainHelper db = new MainHelper(context);
        db.deleteUserdata(list.get(i).sm);
        list.remove(i);
        notifyDataSetChanged();
    }



    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View show = inflator.inflate(R.layout.customlistview,viewGroup,false);
        TextView text = show.findViewById(R.id.textView3);
        TextView text2 = show.findViewById(R.id.textView4);
        text.setText(list.get(i).sm);
        text2.setText(list.get(i).rm);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(i);
            }
        });
        return show;
    }
    private void showDeleteDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItem(i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

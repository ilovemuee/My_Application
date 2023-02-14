package com.example.myapplication;

import static com.example.myapplication.Modelclass.Layout_One;
import static com.example.myapplication.Modelclass.Layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter {
    ArrayList<Modelclass> list;
    Context context;

    public Adaptor(ArrayList<Modelclass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int Case = list.get(position).getViewtype();
        if(Case == 1) {
            return Layout_One;
        }
        else{
            return Layout_two;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case Layout_One:
                View layout_one= LayoutInflater.from(context).inflate(R.layout.sender_message_layout,parent,false);
                return new SenderMessagereciever(layout_one);
            case Layout_two:
                View layout_two= LayoutInflater.from(context).inflate(R.layout.reciever_message_layout,parent,false);
                return new recieverMessagereciever(layout_two);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getViewtype()){
            case Layout_One:
                String sm=list.get(position).getMessage();
                ((SenderMessagereciever)holder).setView(sm);
                break;
            case Layout_two:
                String sr=list.get(position).getMessage();
                ((recieverMessagereciever)holder).setView(sr);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    private class SenderMessagereciever extends RecyclerView.ViewHolder {
        private final TextView tv_sm;
        public SenderMessagereciever(View layout_one) {
            super(layout_one);
            tv_sm = layout_one.findViewById(R.id.tv_sm);
        }
        private void setView(String text){
            tv_sm.setText(text);
        }
    }
    private class recieverMessagereciever extends RecyclerView.ViewHolder {
        private final TextView tv_sm;
        public recieverMessagereciever(View layout_one) {
            super(layout_one);
            tv_sm = layout_one.findViewById(R.id.tv_rm);
        }
        private void setView(String text){
            tv_sm.setText(text);
        }
    }
}

package com.example.android.work;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.media.CamcorderProfile.get;

/**
 * Created by DELL on 29-12-2017.
 */

public class Adapter extends RecyclerView.Adapter {
    private Context con;
    private LayoutInflater inflater;
    private Activity context;
    List<Recipe> recipes = Collections.emptyList();
    List<Ingredients> ing = Collections.emptyList();
    private MyClickListener myClickListener;


    public Adapter(Context con, List<Recipe> recipes){
        this.con = con;
        inflater= LayoutInflater.from(con);
        this.recipes= recipes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row=inflater.inflate(R.layout.custom_row_item,parent,false);
        ItemHolder holder=new ItemHolder(row);

        return(holder);

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ItemHolder myHolder= (ItemHolder) holder;
        Recipe current=recipes.get(position);
        myHolder.textViewname.setText(current.name);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewname;
        TextView text;



        public ItemHolder(View itemView) {
            super(itemView);
            textViewname = (TextView)itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
           /* */
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListene) {
        this.myClickListener = myClickListene;
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }



}

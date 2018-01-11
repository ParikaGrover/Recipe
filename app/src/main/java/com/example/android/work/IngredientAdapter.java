package com.example.android.work;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by DELL on 03-01-2018.
 */

public class IngredientAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflaters;
    private Context context;
    List<Ingredient_content> ingredient_data ;
    List<Recipe> recipe = Collections.emptyList();


    public IngredientAdapter(Context context,List<Recipe> recipe){
        this.context = context;
        inflaters= LayoutInflater.from(context);
        this.recipe = recipe;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row=inflaters.inflate(R.layout.custom_row_item,parent,false);
        ItemHolder holder = new ItemHolder(row);
        return(holder);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder myHolder= (ItemHolder) holder;
        Ingredient_content current = recipe.get(position).getIngredient_data().get(0);
        myHolder.text.setText(current.getIngredient_name());
       Log.d("check",current.getIngredient_name());

    }
    @Override
    public int getItemCount() {
        return recipe.size();
    }
    private class ItemHolder extends RecyclerView.ViewHolder  {
        TextView text;



        public ItemHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.textView);


        }

    }

}

package com.example.android.work;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class Ingredients extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IngredientAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Recipe values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* Intent intent = this.getIntent();

        Bundle b = getIntent().getExtras();
        Recipe recipe = (Recipe) b.getSerializable("value");*/
        List<Recipe> hacks = null;
        Recipe hack = null;

        QueryBuilder<Recipe> queryBuilder =  ((Global) getApplicationContext()).getAppDaoSession().getRecipeDao().queryBuilder();
        queryBuilder.join(Ingredient_content.class, Ingredient_contentDao.Properties.ContactId);
        List<Recipe> joined = queryBuilder.list();



        hacks = ((Global) getApplicationContext()).getDaoSession().getRecipeDao().loadAll();


        Log.d("names", joined.size() + "");


        for (Recipe p : joined) {
            System.out.println(p.getName());
        }


       /* List<Ingredient_content> numbers = persons.get(1).getIngredient_data();

        List<Ingredient_content> joined = queryBuilder.list();*/
           /* for (Ingredient_content p : hack.getIngredient_data()) {
                System.out.println(p);
            }*/



/*
        if (recipe != null)

       for (int i = 0; i < recipe.getIngredient_data().size(); i++) {

                Log.d("ab", recipe.getIngredient_data().get(i).getIngredient_name());}*/


     /*   mAdapter = new IngredientAdapter(this, hacks);
        recyclerView.setAdapter(mAdapter);*/


    }
            }




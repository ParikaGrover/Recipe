package com.example.android.work;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.greendao.database.Database;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.phoneNumber;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.android.work.R.id.textView;
import static java.util.logging.Logger.global;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    String server_url = "http://www.speechify.in/internship/android_task.php";
    RecyclerView recycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView textView1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Click();

        TextView txtv = (TextView) findViewById(R.id.txtv);


    }

    private String getJSONString(Context context) {
        String str = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("data.json");
            InputStreamReader isr = new InputStreamReader(in);
            char[] inputBuffer = new char[100];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return str;
    }



    public void Click() {


        final List<Recipe> recip = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(getJSONString(getApplicationContext()));
            JSONArray array = jsonObject.getJSONArray("recipe_data");
            String status = jsonObject.getString("status");

            Log.d("Tester", array.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Recipe rec = new Recipe();
                Ingredient_content ingred = new Ingredient_content();

                rec.setName(o.getString("name"));

                Log.d("InsertedRecipe",((Global) getApplicationContext()).getAppDaoSession().getRecipeDao().insert(rec)+"");
                recip.add(rec);


                String id = o.getString("id");
                JSONArray ing_array = o.getJSONArray("ingredient_data");

                List<String> ingredient = new ArrayList<>();
                List<Ingredient_content> ingredientses = new ArrayList<>();

                for (int j = 0; j < ing_array.length(); j++) {
                    JSONObject k = ing_array.getJSONObject(j);
                    String ing_id = k.getString("ingredient_id");
                    String ing_name = k.getString("ingredient_name");

                    Log.d("aaa", ing_id + ":" + ing_name);

                    ingredientses.add(new Ingredient_content((long) j + 1, (long) j + 1, ing_name));

                    Ingredient_content ingredient_content = new Ingredient_content();
                    ingredient_content.setContactId((long) j + 1);
                    ingredient_content.setIngredient_name(ing_name);
                    Log.d("Inserted", ((Global) getApplicationContext()).getAppDaoSession().getIngredient_contentDao().insert(ingredient_content) + "Ā");

                }




                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                adapter = new Adapter(MainActivity.this, recip);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


            }

            adapter.setOnItemClickListener(new Adapter.MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {


                    Intent intent = new Intent(MainActivity.this, Ingredients.class);
                               /* intent.putExtra("values", rec.getIngredient_data().get(0));*/


                    Bundle bundle = new Bundle();
                    bundle.putSerializable("value", recip.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);


                    Log.d("Value", position + "");

                }
            });

        } catch (JSONException e) {
            Log.d("Test", "Exception");
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
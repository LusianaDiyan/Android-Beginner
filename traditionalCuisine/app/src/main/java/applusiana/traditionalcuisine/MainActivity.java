package applusiana.traditionalcuisine;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDeskripsi;
    private TypedArray dataPicture;
    private FoodAdapter foodAdapter;
    private ListView listView;
    private ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodAdapter = new FoodAdapter(this);

        listView = findViewById(R.id.lv_food);
        listView.setAdapter(foodAdapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        foods.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FOOD, foods.get(position));
                startActivity(intent);
            }
        });
    }

    private void addItem(){
        foods = new ArrayList<>();

        for (int i =0; i<dataName.length; i++){
            Food food = new Food();
            food.setPicture(dataPicture.getResourceId(i, -1));
            food.setName(dataName[i]);
            food.setDescription(dataDeskripsi[i]);
            foods.add(food);
        }
        foodAdapter.setFoods(foods);
    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.data_namamakanan);
        dataDeskripsi = getResources().getStringArray(R.array.data_description);
        dataPicture = getResources().obtainTypedArray(R.array.data_picture);
    }
}

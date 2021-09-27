package applusiana.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataName;
    private String[] dataDeskripsi;
    private TypedArray dataPoster;
    private MovieAdapter movieAdapter;
    private ListView listView;
    private ArrayList<Movies> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter(this);

        listView = findViewById(R.id.lv_movie);
        listView.setAdapter(movieAdapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
    }

    private void addItem(){
        movies = new ArrayList<>();

        for (int i =0; i<dataName.length; i++){
            Movies movie = new Movies();
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setTitle(dataName[i]);
            movie.setDeskripsi(dataDeskripsi[i]);
            movies.add(movie);
        }
        movieAdapter.setMovies(movies);
    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.data_title);
        dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
        dataPoster = getResources().obtainTypedArray(R.array.data_poster);
    }
}

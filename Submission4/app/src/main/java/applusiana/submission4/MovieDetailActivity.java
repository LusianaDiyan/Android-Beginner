package applusiana.submission4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;

import static applusiana.submission4.DatabaseContract.TABLE_MOVIE;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private static SQLiteDatabase database;
    private TextView name,plotSynopsis,userRating;
    private ImageView photo;
    private ImageButton back;
    private MovieModel movie;
    private MovieHelper movieHelper;
    private MaterialFavoriteButton materialFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        photo=findViewById(R.id.img_movie_detail);
        plotSynopsis=findViewById(R.id.d_description_movie);
        name= findViewById(R.id.d_name_movie);
        userRating= findViewById(R.id.item_scoreAngkaHome);
        materialFavoriteButton = findViewById(R.id.favorite);
        back=findViewById(R.id.btn_back);
        movie =getIntent().getParcelableExtra(EXTRA_MOVIE);

        name.setText(movie.getName());
        plotSynopsis.setText(movie.getOverview());
        userRating.setText(Double.toString(movie.getRate()));
        Glide.with(this)
                .load(movie.getPoster())
                .apply(new RequestOptions().override(350,550))
                .into(photo);

        if (Exist(movie.getName())){
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        saveFavorite();
                        Snackbar.make(buttonView,"Added to Favorite",Snackbar.LENGTH_SHORT).show();
                    }else {
                        int movie_id = movie.getId();
                        movieHelper = new MovieHelper(MovieDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteMovie(movie_id);
                        movieHelper.close();
                        Snackbar.make(buttonView,"Removed from Favorite",Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite) {
                        saveFavorite();
                        Snackbar.make(buttonView, "Added to Favorite", Snackbar.LENGTH_SHORT).show();
                    } else {
                        int movie_id = movie.getId();
                        movieHelper = new MovieHelper(MovieDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteMovie(movie_id);
                        movieHelper.close();
                        Snackbar.make(buttonView, "Removed from Favorite", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveFavorite() {
        movieHelper=new MovieHelper(this);
        String thumbnail=movie.getPoster();
        String movieName=movie.getName();
        String synopsis=movie.getOverview();
        Double rating=movie.getRate();

        movie.setName(movieName);
        movie.setOverview(synopsis);
        movie.setPoster(thumbnail);
        movie.setRate(rating);

        movieHelper.open();
        movieHelper.insertMovie(movie);
        movieHelper.close();
    }

    public boolean Exist(String item){
        String pilih= DatabaseContract.MovieColumns.TITLE+" =?";
        String[] pilihArg={item};
        String limit="1";
        movieHelper= new MovieHelper(this);
        movieHelper.open();
        DatabaseHelper dataBaseHelper= new DatabaseHelper(MovieDetailActivity.this);
        database = dataBaseHelper.getWritableDatabase();
        Cursor cursor= database.query(TABLE_MOVIE,null,pilih,pilihArg,null,null,null,limit);
        boolean exists;
        exists=(cursor.getCount() > 0);
        cursor.close();
        movieHelper.close();
        return exists;
    }
}

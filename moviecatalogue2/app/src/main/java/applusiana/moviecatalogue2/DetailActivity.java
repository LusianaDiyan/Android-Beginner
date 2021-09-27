package applusiana.moviecatalogue2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE ="extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvmovies, tvdesc;
        ImageView ivPost;

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setTitle(movie.getTitle());

        tvmovies = findViewById(R.id.tvMovieTitle);
        tvdesc = findViewById(R.id.tvDescMovie);
        ivPost = findViewById(R.id.ivPoster);

        tvmovies.setText(movie.getTitle());
        tvdesc.setText(movie.getDeskripsi());
        ivPost.setImageResource(movie.getPoster());
    }
}

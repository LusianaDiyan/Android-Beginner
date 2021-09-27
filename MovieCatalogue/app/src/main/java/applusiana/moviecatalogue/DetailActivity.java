package applusiana.moviecatalogue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE ="extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvmovies, tvdesc;
        ImageView ivPost;

        Movies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setTitle(movie.getTitle());

        tvmovies = (TextView) findViewById(R.id.tvMovieTitle);
        tvdesc = (TextView) findViewById(R.id.tvDescMovie);
        ivPost = (ImageView) findViewById(R.id.ivPoster);

        tvmovies.setText(movie.getTitle());
        tvdesc.setText(movie.getDeskripsi());
        ivPost.setImageResource(movie.getPoster());
    }
}

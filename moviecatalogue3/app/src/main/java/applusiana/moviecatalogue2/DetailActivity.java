package applusiana.moviecatalogue2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity implements DetailMovieAsyncTaskCallback{
    public static final String EXTRA_MOVIE ="extra_movie";
    private TextView tvJudul, tvOverview, tvRilis;
    private ImageView ivPoster;
    private ProgressBar progressBar;

    public static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static final SimpleDateFormat EEEddMMMMyyyy = new SimpleDateFormat("EEE, dd MM yyyy", Locale.US);

    public String formatDate(String inputDate, SimpleDateFormat inputDateFormat, SimpleDateFormat outputDateFormat){
        Date date = null;
        String outputDate = null;

        try {
            date = inputDateFormat.parse(inputDate);
            outputDate = outputDateFormat.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return outputDate;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvJudul = findViewById(R.id.tvMovieTitle);
        tvOverview = findViewById(R.id.tvDescMovie);
        tvRilis = findViewById(R.id.tvTglRilis);
        ivPoster = findViewById(R.id.ivPoster);
        progressBar = findViewById(R.id.pbList);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        new DetailAsyncTask(this).execute(movie);
    }

    private void showLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPreExecute() {
        showLoading(true);
    }

    @Override
    public void onPostExecute(Movie movie) {
        tvJudul.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRilis.setText(movie.getReleaseDate());

        Picasso.get().load(TheMovieDBAPI.getPoster(movie.getPosterPath())).into(ivPoster);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(movie.getTitle());
        }

        showLoading(false);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private static class DetailAsyncTask extends AsyncTask<Movie, Void, Movie> {

        WeakReference<DetailMovieAsyncTaskCallback> callback;

        DetailAsyncTask(DetailMovieAsyncTaskCallback callback) {
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected Movie doInBackground(Movie... movies) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return movies[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DetailMovieAsyncTaskCallback callback = this.callback.get();
            if (callback != null) {
                callback.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);

            DetailMovieAsyncTaskCallback callback = this.callback.get();
            if (callback != null) {
                callback.onPostExecute(movie);
            }
        }
    }
}

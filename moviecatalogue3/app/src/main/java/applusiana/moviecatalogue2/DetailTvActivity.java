package applusiana.moviecatalogue2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class DetailTvActivity extends AppCompatActivity implements DetailTvAsyncTaskCallback {
    public static final String EXTRA_MOVIE ="extra_movie";
    private TextView tvJudul, tvOverview, tvRilis;
    private ImageView ivPoster;
    private ProgressBar progressBar;

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedIinstanceState){
        super.onCreate(savedIinstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvJudul = findViewById(R.id.tvMovieTitle);
        tvOverview = findViewById(R.id.tvDescMovie);
        tvRilis = findViewById(R.id.tvTglRilis);
        ivPoster = findViewById(R.id.ivPoster);
        progressBar = findViewById(R.id.pbList);

        TvShow listTv = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (listTv.getName() != null){
            setTitle(listTv.getName());
        }
        new DetailAsyncTask(this).execute(listTv);
    }

    private void showLoading(boolean loading){
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPreExecute() {
        showLoading(true);
    }

    @Override
    public void onPostExecute(TvShow tvshow) {
        tvJudul.setText(tvshow.getName());
        tvRilis.setText(tvshow.getFirstAirDate());
        tvOverview.setText(tvshow.getOverview());

        Picasso.get().load(TheMovieDBAPI.getPoster(tvshow.getPosterPath())).into(ivPoster);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(tvshow.getName());
        }

        showLoading(false);
    }

    private static class DetailAsyncTask extends AsyncTask<TvShow, Void, TvShow> {

        WeakReference<DetailTvAsyncTaskCallback> callback;

        DetailAsyncTask(DetailTvAsyncTaskCallback callback){
            this.callback = new WeakReference<DetailTvAsyncTaskCallback>(callback);
        }

        @Override
        protected TvShow doInBackground(TvShow... tvShows) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return tvShows[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DetailTvAsyncTaskCallback callback = this.callback.get();
            if(callback != null){
                callback.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(TvShow tvShow) {
            super.onPostExecute(tvShow);

            DetailTvAsyncTaskCallback callback = this.callback.get();
            if (callback != null){
                callback.onPostExecute(tvShow);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

package applusiana.subm4;

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

import static applusiana.subm4.DatabaseContract.TABLE_TV;

public class TvDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    private static SQLiteDatabase sqLiteDatabase;
    private TextView title,plotOverview,userRating;
    private ImageView poster;
    private ImageButton back;
    private TvModel tvShow;
    private MovieHelper movieHelper;
    private MaterialFavoriteButton materialFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        poster=findViewById(R.id.img_tv_detail);
        plotOverview=findViewById(R.id.d_description_tv);
        title= findViewById(R.id.name_tv);
        userRating= findViewById(R.id.item_tvRate);
        materialFavoriteButton = findViewById(R.id.favorite);
        back=findViewById(R.id.btn_back);
        tvShow =getIntent().getParcelableExtra(EXTRA_TV);

        title.setText(tvShow.getTitle());
        plotOverview.setText(tvShow.getOverview());
        userRating.setText(Double.toString(tvShow.getRate()));
        Glide.with(this)
                .load(tvShow.getPoster())
                .apply(new RequestOptions().override(350,550))
                .into(poster);
        if (Exist(tvShow.getTitle())){
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        saveFavorite();
                        Snackbar.make(buttonView,"Added to Favorite",Snackbar.LENGTH_SHORT).show();
                    }else {
                        int tv_id = tvShow.getId();
                        movieHelper = new MovieHelper(TvDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteTv(tv_id);
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
                        int tv_id = tvShow.getId();
                        movieHelper = new MovieHelper(TvDetailActivity.this);
                        movieHelper.open();
                        movieHelper.deleteTv(tv_id);
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

    private boolean Exist(String name) {
        String pilih= DatabaseContract.TvColumns.TITLE_TV+" =?";
        String[] pilihArg={name};
        String limit="1";
        movieHelper= new MovieHelper(this);
        movieHelper.open();
        DatabaseHelper dataBaseHelper= new DatabaseHelper(TvDetailActivity.this);
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.query(TABLE_TV,null,pilih,pilihArg,null,null,null,limit);
        boolean exists;

        exists=(cursor.getCount() > 0);
        cursor.close();
        movieHelper.close();
        return exists;
    }

    private void saveFavorite() {
        movieHelper=new MovieHelper(this);
        String thumbnail=tvShow.getPoster();
        String tvName=tvShow.getTitle();
        String synopsis=tvShow.getOverview();
        Double rating=tvShow.getRate();

        tvShow.setTitle(tvName);
        tvShow.setOverview(synopsis);
        tvShow.setRate(rating);
        tvShow.setPoster(thumbnail);

        movieHelper.open();
        movieHelper.insertTv(tvShow);
        movieHelper.close();
    }
}

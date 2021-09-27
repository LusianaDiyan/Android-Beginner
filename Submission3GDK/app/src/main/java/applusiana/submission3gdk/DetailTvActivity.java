package applusiana.submission3gdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailTvActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradasi));
        }
        ImageView image_detail = findViewById(R.id.image_detail);
        TextView textDescription = findViewById(R.id.description_detail);
        TextView textTitle = findViewById(R.id.overview);

        TvModel tvItems = getIntent().getParcelableExtra(EXTRA_TV);

        textTitle.setText(tvItems.getName());
        textDescription.setText(tvItems.getOverview());

        getSupportActionBar().setTitle(tvItems.getName());

        Glide.with(this)
                .load(tvItems.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(image_detail);
    }
}

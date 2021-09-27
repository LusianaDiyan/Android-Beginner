package applusiana.playmovie;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class DetailMovieActivity extends AppCompatActivity {
    private movieHelper MmovieHelper;
    private EditText title;
    private RatingBar rating;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        MmovieHelper = new movieHelper(this);

        title = findViewById(R.id.edt_title);
        rating = findViewById(R.id.rtb_rating);

        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().isEmpty()){
                    Toast.makeText(DetailMovieActivity.this, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = MmovieHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(FilmContract.FilmEntry.COLUMN_TITLE, title.getText().toString());
                values.put(FilmContract.FilmEntry.COLUMN_RATING, (int) rating.getRating());

                long newId = db.insert(FilmContract.FilmEntry.TABLE, null, values);

                if (newId > 0){
                    finish();
                }
            }
        });
    }
}

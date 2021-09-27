package applusiana.playmovie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private movieHelper MmovieHelper;
    private ListView listmovie;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MmovieHelper = new movieHelper(this);

        listmovie = findViewById(R.id.list_movie);
        mAdapter = new MovieAdapter(this, getAllDatas());
        listmovie.setAdapter(mAdapter);
        //insertDummyData();
    }

    private void insertDummyData(){
        SQLiteDatabase db = MmovieHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FilmContract.FilmEntry.COLUMN_TITLE, "Dilan");
        values.put(FilmContract.FilmEntry.COLUMN_RATING, 5);
        long newId = db.insert(FilmContract.FilmEntry.TABLE,null, values);
        Log.d("NewEntry", "New id : " + newId);
    }

    private Cursor getAllDatas(){
        String[] projections= {
                FilmContract.FilmEntry._ID,
                FilmContract.FilmEntry.COLUMN_TITLE,
                FilmContract.FilmEntry.COLUMN_RATING
        };

        //String selection = MyMovieContract.MovieEntry.COLUMN_RATING + " > ?";
        //String[] selectionArgs = {"2"};

        SQLiteDatabase db = MmovieHelper.getReadableDatabase();
        Cursor cursor = db.query(
                FilmContract.FilmEntry.TABLE,
                projections,
                null,
                null,
                null,
                null,
                null);

         return cursor;
    }

    @Override
    protected void onResume(){
        super.onResume();
        mAdapter.swapCursor(getAllDatas());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this, FilmContract.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy(){
        getAllDatas().close();
        super.onDestroy();
    }


}

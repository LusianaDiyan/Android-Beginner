package applusiana.submission4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.UserDictionary.Words._ID;
import static applusiana.submission4.DatabaseContract.MovieColumns.DESCRIPTION;
import static applusiana.submission4.DatabaseContract.MovieColumns.ID_MOVIE;
import static applusiana.submission4.DatabaseContract.MovieColumns.IMAGE;
import static applusiana.submission4.DatabaseContract.MovieColumns.RATE;
import static applusiana.submission4.DatabaseContract.MovieColumns.TITLE;
import static applusiana.submission4.DatabaseContract.TABLE_MOVIE;
import static applusiana.submission4.DatabaseContract.TABLE_TV;
import static applusiana.submission4.DatabaseContract.TvColumns.DESCRIPTION_TV;
import static applusiana.submission4.DatabaseContract.TvColumns.ID_TV;
import static applusiana.submission4.DatabaseContract.TvColumns.IMAGE_TV;
import static applusiana.submission4.DatabaseContract.TvColumns.RATE_TV;
import static applusiana.submission4.DatabaseContract.TvColumns.TITLE_TV;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static final String DATABASE_TABLE1 = TABLE_TV;
    private static DatabaseHelper dataBaseHelper;
    private static SQLiteDatabase database;

    public MovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }
    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<MovieModel> getAllMovies() {
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        MovieModel filmku;
        if (cursor.getCount() > 0) {
            do {
                filmku = new MovieModel();
                filmku.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                filmku.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                filmku.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                filmku.setRate(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(RATE))));
                filmku.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                arrayList.add(filmku);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<TvModel> getAllTv() {
        ArrayList<TvModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE1, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TvModel filmku;
        if (cursor.getCount() > 0) {
            do {
                filmku = new TvModel();
                filmku.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TV)));
                filmku.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TV)));
                filmku.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_TV)));
                filmku.setRate(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(RATE_TV))));
                filmku.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_TV)));
                arrayList.add(filmku);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(MovieModel filmku) {
        ContentValues args = new ContentValues();
        args.put(ID_MOVIE, filmku.getId());
        args.put(TITLE, filmku.getName());
        args.put(DESCRIPTION, filmku.getOverview());
        args.put(RATE, filmku.getRate());
        args.put(IMAGE, filmku.getPoster());
        return database.insert(DATABASE_TABLE, null, args);
    }
    public long insertTv(TvModel filmku) {
        ContentValues args = new ContentValues();
        args.put(ID_TV, filmku.getId());
        args.put(TITLE_TV, filmku.getName());
        args.put(DESCRIPTION_TV, filmku.getOverview());
        args.put(RATE_TV, filmku.getRate());
        args.put(IMAGE_TV, filmku.getPoster());
        return database.insert(DATABASE_TABLE1, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(TABLE_MOVIE, ID_MOVIE + " = '" + id + "'", null);
    }
    public int deleteTv(int id) {
        return database.delete(TABLE_TV, ID_TV + " = '" + id + "'", null);
    }
}

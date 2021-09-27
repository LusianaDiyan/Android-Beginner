package applusiana.subm4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.UserDictionary.Words._ID;
import static applusiana.subm4.DatabaseContract.MovieColumns.ID_MOVIE;
import static applusiana.subm4.DatabaseContract.MovieColumns.OVERVIEW;
import static applusiana.subm4.DatabaseContract.MovieColumns.POSTER;
import static applusiana.subm4.DatabaseContract.MovieColumns.RATE;
import static applusiana.subm4.DatabaseContract.MovieColumns.TITLE;
import static applusiana.subm4.DatabaseContract.TABLE_MOVIE;
import static applusiana.subm4.DatabaseContract.TABLE_TV;
import static applusiana.subm4.DatabaseContract.TvColumns.ID_TV;
import static applusiana.subm4.DatabaseContract.TvColumns.OVERVIEW_TV;
import static applusiana.subm4.DatabaseContract.TvColumns.POSTER_TV;
import static applusiana.subm4.DatabaseContract.TvColumns.RATE_TV;
import static applusiana.subm4.DatabaseContract.TvColumns.TITLE_TV;

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static final String DATABASE_TABLE2 = TABLE_TV;
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;

    public MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public ArrayList<MovieModel> getAllMovies() {
        ArrayList<MovieModel> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        MovieModel movies;
        if (cursor.getCount() > 0) {
            do {
                movies = new MovieModel();
                movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_MOVIE)));
                movies.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movies.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movies.setRate(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(RATE))));
                movies.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                arrayList.add(movies);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<TvModel> getAllTv() {
        ArrayList<TvModel> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE2, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TvModel movies;
        if (cursor.getCount() > 0) {
            do {
                movies = new TvModel();
                movies.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID_TV)));
                movies.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TV)));
                movies.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW_TV)));
                movies.setRate(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(RATE_TV))));
                movies.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_TV)));
                arrayList.add(movies);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(MovieModel movies) {
        ContentValues args = new ContentValues();
        args.put(ID_MOVIE, movies.getId());
        args.put(TITLE, movies.getTitle());
        args.put(OVERVIEW, movies.getOverview());
        args.put(RATE, movies.getRate());
        args.put(POSTER, movies.getPoster());
        return sqLiteDatabase.insert(DATABASE_TABLE, null, args);
    }
    public long insertTv(TvModel movies) {
        ContentValues args = new ContentValues();
        args.put(ID_TV, movies.getId());
        args.put(TITLE_TV, movies.getTitle());
        args.put(OVERVIEW_TV, movies.getOverview());
        args.put(RATE_TV, movies.getRate());
        args.put(POSTER_TV, movies.getPoster());
        return sqLiteDatabase.insert(DATABASE_TABLE2, null, args);
    }

    public int deleteMovie(int id) {
        return sqLiteDatabase.delete(TABLE_MOVIE, ID_MOVIE + " = '" + id + "'", null);
    }
    public int deleteTv(int id) {
        return sqLiteDatabase.delete(TABLE_TV, ID_TV + " = '" + id + "'", null);
    }
}

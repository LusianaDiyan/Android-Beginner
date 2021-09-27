package applusiana.playmovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 10/02/2018.
 */

public class movieHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "film.db";
    public static int DATABASE_VERSION = 1;

    public movieHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//    String query = "CREATE" + FilmContract.FilmEntry.TABLE + "(" +
//            FilmContract.FilmEntry._ID + "INTEGER PRIMARY KEY" +
//            FilmContract.FilmEntry.COLUMN_TITLE + "TEXT NOT NULL" +
//            FilmContract.FilmEntry.COLUMN_RATING + "INTEGER DEFAULT 0)";

        sqLiteDatabase.execSQL(FilmContract.FilmEntry.SQL_CREATE_MOVIE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

/*
* "CREATE TABLE movie (
*           _id INTEGER PRIMARY KEY,
*           title TEXT NOT NULL,
*           rating INTEGER DEFAULT 0)"
* */

}
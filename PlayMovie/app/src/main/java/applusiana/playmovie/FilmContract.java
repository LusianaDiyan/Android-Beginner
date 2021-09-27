package applusiana.playmovie;

import android.provider.BaseColumns;

/**
 * Created by USER on 10/02/2018.
 */

public class FilmContract {
    public FilmContract(){
    }

    public static class FilmEntry implements BaseColumns{
        public static String TABLE = "film";
        public static String COLUMN_TITLE = "title";
        public static String COLUMN_RATING = "rating";

        public static final String SQL_CREATE_MOVIE_ENTRIES =
                "CREATE TABLE" + FilmEntry.TABLE + " (" +
                        FilmEntry. _ID + " INTEGER PRIMARY KEY, " +
                        FilmEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                        FilmEntry.COLUMN_RATING + " INTEGER DEFAULT 0)";
    }
}

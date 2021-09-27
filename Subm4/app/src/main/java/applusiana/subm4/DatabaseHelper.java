package applusiana.subm4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmovieapp";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
            "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s INTEGER NOT NULL, " +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",
        DatabaseContract.TABLE_MOVIE,
        DatabaseContract.MovieColumns._ID,
        DatabaseContract.MovieColumns.ID_MOVIE,
        DatabaseContract.MovieColumns.TITLE,
        DatabaseContract.MovieColumns.OVERVIEW,
        DatabaseContract.MovieColumns.RATE,
        DatabaseContract.MovieColumns.POSTER
    );

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_TV,
            DatabaseContract.TvColumns._ID,
            DatabaseContract.TvColumns.ID_TV,
            DatabaseContract.TvColumns.TITLE_TV,
            DatabaseContract.TvColumns.OVERVIEW_TV,
            DatabaseContract.TvColumns.RATE_TV,
            DatabaseContract.TvColumns.POSTER_TV
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_TV);
        onCreate(sqLiteDatabase);
    }
}

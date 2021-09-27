package applusiana.subm4;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_MOVIE = "movie";
    public static String TABLE_TV ="tv";

    public static final class MovieColumns implements BaseColumns{
        public static String ID_MOVIE = "id_movie";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RATE = "rate";
        public static String POSTER = "poster";
    }

    public static final class TvColumns implements BaseColumns{
        public static String ID_TV = "id_TV";
        public static String TITLE_TV = "title";
        public static String OVERVIEW_TV = "overview";
        public static String RATE_TV = "rate";
        public static String POSTER_TV = "poster";
    }
}

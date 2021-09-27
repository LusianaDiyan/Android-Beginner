package applusiana.submission4;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_MOVIE = "movie";
    public static String TABLE_TV = "tv";
    public static final class MovieColumns implements BaseColumns {
        public static String ID_MOVIE = "id_movie";
        public static String TITLE = "title";
        public static String DESCRIPTION = "description";
        public static String RATE = "rate";
        public static String IMAGE = "image";
    }
    public static final class TvColumns implements BaseColumns{
        public static String ID_TV = "id_tv";
        public static String TITLE_TV = "title";
        public static String DESCRIPTION_TV = "description";
        public static String RATE_TV = "rate";
        public static String IMAGE_TV = "image";
    }
}

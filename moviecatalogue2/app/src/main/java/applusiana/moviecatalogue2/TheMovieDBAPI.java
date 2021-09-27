package applusiana.moviecatalogue2;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TheMovieDBAPI {
    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/";
    private static final String API_KEY = "adfbc51e769ed59068f068ef2c7cf21a";
    public static final String API_LANG = "en-US";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler){
        requestParams.put("api_key", API_KEY);
        requestParams.put("language", API_LANG);

        client.get(getAbsoluteUrl(url), requestParams, asyncHttpResponseHandler);

    }

    private static String getAbsoluteUrl(String url) {
        return BASE_URL + url;
    }

    public static String getPoster(String poster){
        return "https://image.tmdb.org/t/p/w185" + poster;
    }
}

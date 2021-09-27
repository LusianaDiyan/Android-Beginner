package applusiana.subm4;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "adfbc51e769ed59068f068ef2c7cf21a";
    private MutableLiveData<ArrayList<MovieModel>> listMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvModel>> listTv = new MutableLiveData<>();
    private String language;

    public void setListMovie(final String type){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieModel> listItemsMovie = new ArrayList<>();
        String locale = Locale.getDefault().getDisplayLanguage();
        if (locale.contains("English")) {
            language = "en-US";
        } else if (locale.contains("Indonesia")) {
            language = "id";
        }

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=" + language;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    Log.d("API Result", result);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieModel movieItems = new MovieModel(movie);
                        listItemsMovie.add(movieItems);
                    }
                    listMovie.postValue(listItemsMovie);

                }
                catch (Exception e) {
                    Log.d("Error",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Error",error.getMessage());
            }

        });
    }
    public LiveData<ArrayList<MovieModel>> getMovie() {
        return listMovie;
    }

    public void setListTv(final String type){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvModel> listItemsTv = new ArrayList<>();
        String locale = Locale.getDefault().getDisplayLanguage();
        if (locale.contains("English")) {
            language = "en-US";
        } else if (locale.contains("Indonesia")) {
            language = "id";
        }

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=" + language;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("API Result", result);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvModel tvItems = new TvModel(tv);
                        listItemsTv.add(tvItems);
                    }
                    listTv.postValue(listItemsTv);
                } catch (Exception e) {
                    Log.d("Error",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Error",error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<TvModel>> getTv(){
        return listTv;
    }
}

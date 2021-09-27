package applusiana.moviecatalogue2;

public interface DetailMovieAsyncTaskCallback {
    void onPreExecute();
    void onPostExecute(Movie movie);
}

package applusiana.moviecatalogue2;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MovieFragment extends Fragment {
    public static MovieFragment newInstance(){
        return new MovieFragment();
    }
    private String[] dataJudul, dataDeskripsi;
    private TypedArray dataPoster;
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private ArrayList<Movie> listMovie = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        movieAdapter = new MovieAdapter(getContext());
        rvMovie = rootView.findViewById(R.id.rvMovie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(movieAdapter);

        addItem();

        return rootView;
    }

    private void addItem(){
        dataJudul = getResources().getStringArray(R.array.data_judul_movie);
        dataDeskripsi = getResources().getStringArray(R.array.data_sinopsis_movie);
        dataPoster = getResources().obtainTypedArray(R.array.data_poster_movie);
        listMovie = new ArrayList<>();

        for (int i =0; i<dataJudul.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataJudul[i]);
            movie.setDeskripsi(dataDeskripsi[i]);
            movie.setPoster(dataPoster.getResourceId(i, -1));
            listMovie.add(movie);
        }
        movieAdapter.setListMovie(listMovie);
    }
}

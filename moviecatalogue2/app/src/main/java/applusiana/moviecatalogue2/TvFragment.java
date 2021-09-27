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

public class TvFragment extends Fragment {

    public static TvFragment newInstance(){
        return new TvFragment();
    }

    private String[] dataJudul, dataDeskripsi;
    private TypedArray dataPoster;
    private MovieAdapter tvAdapter;
    private RecyclerView rvTv;
    private ArrayList<Movie> listMovie = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tv, container, false);
        tvAdapter = new MovieAdapter(getContext());
        rvTv = rootView.findViewById(R.id.rvTv);
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTv.setAdapter(tvAdapter);

        addItem();

        return rootView;
    }

    private void addItem(){
        dataJudul = getResources().getStringArray(R.array.data_judul_tv);
        dataDeskripsi = getResources().getStringArray(R.array.data_sinopsis_tv);
        dataPoster = getResources().obtainTypedArray(R.array.data_poster_tv);
        listMovie = new ArrayList<>();

        for (int i =0; i<dataJudul.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataJudul[i]);
            movie.setDeskripsi(dataDeskripsi[i]);
            movie.setPoster(dataPoster.getResourceId(i, -1));
            listMovie.add(movie);
        }
        tvAdapter.setListMovie(listMovie);
    }
}

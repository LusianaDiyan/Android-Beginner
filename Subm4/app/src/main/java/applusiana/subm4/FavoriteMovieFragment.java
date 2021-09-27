package applusiana.subm4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FavoriteMovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<MovieModel> movieList;
    private MovieHelper movieHelper;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        movieAdapter= new MovieAdapter(getContext());
        movieHelper =new MovieHelper(getContext());
        movieHelper.open();
        movieList=new ArrayList<>();
        movieList=movieHelper.getAllMovies();
        movieAdapter.setData(movieList);
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
        MovieHelper item =new MovieHelper(getContext());
        item.open();
        movieList=item.getAllMovies();
        movieAdapter.setData(movieList);
        recyclerView.setAdapter(movieAdapter);
    }
}

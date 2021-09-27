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


public class FavoriteTvFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private ArrayList<TvModel> tvList;
    private MovieHelper item;


    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);
        recyclerView = view.findViewById(R.id.rv_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        tvAdapter= new TvAdapter(getContext());
        item =new MovieHelper(getContext());
        item.open();
        tvList=new ArrayList<>();
        tvList=item.getAllTv();
        tvAdapter.setTvList(tvList);
        recyclerView.setAdapter(tvAdapter);
        tvAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        MovieHelper item =new MovieHelper(getContext());
        tvList=item.getAllTv();
        tvAdapter.setTvList(tvList);
        recyclerView.setAdapter(tvAdapter);
    }
}

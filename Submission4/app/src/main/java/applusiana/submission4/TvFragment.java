package applusiana.submission4;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TvFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter adapter;
    ProgressDialog pdialog;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView =view.findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        pdialog = new ProgressDialog(getActivity());
        pdialog.setMessage("Loading...");
        pdialog.setCancelable(false);
        pdialog.show();
        adapter = new TvAdapter(getActivity());
        adapter.notifyDataSetChanged();
        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getTv().observe(this, getTvs);
        model.setListTv("tv");
        return view;
    }


    private Observer<ArrayList<TvModel>> getTvs = new Observer<ArrayList<TvModel>>() {
        @Override
        public void onChanged(ArrayList<TvModel> tvShows) {

            if(tvShows != null) {
                adapter.setData(tvShows);
                recyclerView.setAdapter(adapter);
                pdialog.dismiss();
            }
        }
    };

}

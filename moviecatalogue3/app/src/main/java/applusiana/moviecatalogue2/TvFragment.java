package applusiana.moviecatalogue2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class TvFragment extends Fragment implements TvAdapter.TvListener{
    private TvAdapter tvAdapter;
    private RecyclerView rvTv;
    private ArrayList<TvShow> listTv;
    private ProgressBar progressBar;
    private ListTvModelJava tvViewModel;

    public static Fragment newInstance() {
        return new TvFragment();
    }

    private void showLoading(boolean loading){
        if (loading){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreatView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTv = view.findViewById(R.id.rvTv);
        progressBar = view.findViewById(R.id.pbList);
        tvAdapter = new TvAdapter(getContext(), this);

        tvViewModel = ViewModelProvider.of(this)
                .get(ListTvModelJava.class);
        tvViewModel.getListTv().observe(this, getListTv);

        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTv.setAdapter(tvAdapter);

        showLoading(true);
        tvViewModel.setListTv();
    }

    private Observer<ArrayList<TvShow>> getListTv = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShow) {
            if (tvShow != null){
                listTv = tvShow;
                tvAdapter.setListTv(tvShow);
            }
            showLoading(false);
        }
    };

    @Override
    public void onTvItemClick(int i) {
        Intent intent = new Intent(getContext(), DetailTvActivity.class);
        intent.putExtra(DetailTvActivity.EXTRA_MOVIE, listTv.get(i));
        startActivity(intent);
    }
}

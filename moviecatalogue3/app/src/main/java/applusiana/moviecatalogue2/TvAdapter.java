package applusiana.moviecatalogue2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private Context context;
    private ArrayList<TvShow> listTv;
    private TvListener tvListener;

    public void setListTv(ArrayList<TvShow> listTv){
        this.listTv = listTv;
        notifyDataSetChanged();
    }

    public TvAdapter(Context context, TvListener tvListener){
        this.context = context;
        this.tvListener = tvListener;
        listTv = new ArrayList<>();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        return new TvAdapter.TvViewHolder(view, tvListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder tvViewHolder, int i) {
        tvViewHolder.bind(listTv.get(i));
    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public interface TvListener {
        @Nullable
        View onCreatView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState);

        void onTvItemClick(int i);
    }

    public class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvJudul;
        ImageView ivPoster;
        TvListener tvListener;

        TvViewHolder(@NonNull View itemView, TvListener tvListener) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudul);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            this.tvListener = tvListener;

            itemView.setOnClickListener(this);
        }

        void bind(TvShow tvShow) {
            tvJudul.setText(tvShow.getName());
            Picasso.get().load(TheMovieDBAPI.getPoster(tvShow.getPosterPath())).into(ivPoster);

        }

        @Override
        public void onClick(View v) {

        }
    }
}

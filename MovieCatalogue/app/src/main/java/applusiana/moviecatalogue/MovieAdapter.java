package applusiana.moviecatalogue;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Movies> movies;

    public void setMovies(ArrayList<Movies> movies){
        this.movies = movies;
    }

    public MovieAdapter(Context context){
        this.context = context;
        movies = new ArrayList<>();
    }

    public ArrayList<Movies> getMovies(){
        return movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movies getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bind(getItem(position));

        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.tvTitle);
            txtDescription = view.findViewById(R.id.tvDesc);
            imgPhoto = view.findViewById(R.id.iv_movie);
        }
        void bind(Movies movies) {
            txtName.setText(movies.getTitle());
            txtDescription.setText(movies.getDeskripsi());
            imgPhoto.setImageResource(movies.getPoster());
        }
    }
}

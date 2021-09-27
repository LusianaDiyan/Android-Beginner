package applusiana.androidpemula;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class IslandsAdapter extends RecyclerView.Adapter<IslandsAdapter.CategoryViewHolder> {
    private ArrayList<Pulau> listPulau;

    public IslandsAdapter(ArrayList<Pulau> listP){
        this.listPulau = listP;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_island, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        Pulau pulau = listPulau.get(i);

        Glide.with(categoryViewHolder.itemView.getContext())
                .load(pulau.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(categoryViewHolder.imageView);

        categoryViewHolder.tvNama.setText(pulau.getNamaPulau());
        categoryViewHolder.tvSebutan.setText(pulau.getNamaLain());
    }

    @Override
    public int getItemCount() {
        return listPulau.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvNama, tvSebutan;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pict);
            tvNama = itemView.findViewById(R.id.tvNamaPulau);
            tvSebutan = itemView.findViewById(R.id.tvNamaLain);
        }
    }
}

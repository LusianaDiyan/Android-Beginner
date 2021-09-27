package applusiana.traditionalcuisine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Food> foods;

    public void setFoods(ArrayList<Food> foods){
        this.foods = foods;
    }

    public FoodAdapter(Context context){
        this.context = context;
        foods =new ArrayList<>();
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Food getItem(int i) {
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_food, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.bind(getItem(i));

        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.tvFoodName);
            txtDescription = view.findViewById(R.id.tvDesc);
            imgPhoto = view.findViewById(R.id.iv_food);
        }
        void bind(Food foods) {
            txtName.setText(foods.getName());
            txtDescription.setText(foods.getDescription());
            imgPhoto.setImageResource(foods.getPicture());
        }
    }
}

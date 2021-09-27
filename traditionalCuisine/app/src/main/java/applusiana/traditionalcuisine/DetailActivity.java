package applusiana.traditionalcuisine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_FOOD = "extra_food";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvfoodname, tvdesc;
        ImageView ivPict;

        Food food = getIntent().getParcelableExtra(EXTRA_FOOD);
        setTitle(food.getName());

        tvfoodname = (TextView) findViewById(R.id.tvFoodName);
        tvdesc = (TextView) findViewById(R.id.tvDescription);
        ivPict = (ImageView) findViewById(R.id.ivPicture);

        tvfoodname.setText(food.getName());
        tvdesc.setText(food.getDescription());
        ivPict.setImageResource(food.getPicture());
    }
}

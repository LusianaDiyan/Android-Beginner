package applusiana.androidpemula;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    ImageView gambar;
    TextView nama, other, deskripsi;

    String namaPulau;
    String namaLain;
    String desk;
    int gambarPulau;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        gambar = findViewById(R.id.ivPulau);
        nama = findViewById(R.id.tvNamaPulau);
        other = findViewById(R.id.tvNamaLain);
        deskripsi = findViewById(R.id.tvDeskripsiPulau);

        namaPulau = getIntent().getStringExtra("PULAU");
        namaLain = getIntent().getStringExtra("Nama Lain");
        desk = getIntent().getStringExtra("Deskripsi");
        gambarPulau = getIntent().getIntExtra("Gambar", 0);

        gambar.setImageResource(gambarPulau);
        nama.setText(namaPulau);
        other.setText(namaLain);
        deskripsi.setText(desk);
    }
}

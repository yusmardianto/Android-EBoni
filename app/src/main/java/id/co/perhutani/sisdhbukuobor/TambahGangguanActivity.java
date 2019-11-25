package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan.TambahGangguanFragment;

public class TambahGangguanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_gangguan_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TambahGangguanFragment.newInstance())
                    .commitNow();
        }
    }
}

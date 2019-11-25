package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan.TambahPerubahanFragment;

public class TambahPerubahanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_perubahan_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TambahPerubahanFragment.newInstance())
                    .commitNow();
        }
    }
}

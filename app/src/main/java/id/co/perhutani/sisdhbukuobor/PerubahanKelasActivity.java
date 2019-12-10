package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.PerubahanKelasFragment;

public class PerubahanKelasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perubahan_kelas_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PerubahanKelasFragment.newInstance())
                    .commitNow();
        }
    }
}

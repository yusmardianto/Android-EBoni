package id.co.perhutani.sisdhbukuobor.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.ui.identifikasitenurial.IdentifikasiTenurialFragment;

public class IdentifikasiTenurial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifikasi_tenurial_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, IdentifikasiTenurialFragment.newInstance())
                    .commitNow();
        }
    }
}

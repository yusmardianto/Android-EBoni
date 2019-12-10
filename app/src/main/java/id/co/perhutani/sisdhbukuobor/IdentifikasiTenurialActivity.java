package id.co.perhutani.sisdhbukuobor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.IdentifikasiTenurialFragment;

public class IdentifikasiTenurialActivity extends AppCompatActivity {

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

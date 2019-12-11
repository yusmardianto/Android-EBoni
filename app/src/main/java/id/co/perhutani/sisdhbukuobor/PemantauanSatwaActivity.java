package id.co.perhutani.sisdhbukuobor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.PemantauanSatwaFragment;

public class PemantauanSatwaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemantauan_satwa_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PemantauanSatwaFragment.newInstance())
                    .commitNow();
        }
    }
}

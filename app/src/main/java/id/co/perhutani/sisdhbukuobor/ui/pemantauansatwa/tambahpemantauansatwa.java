package id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.ui.pemantauansatwa.tambahpemantauansatwa.TambahpemantauansatwaFragment;

public class tambahpemantauansatwa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahpemantauansatwa_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TambahpemantauansatwaFragment.newInstance())
                    .commitNow();
        }
    }
}

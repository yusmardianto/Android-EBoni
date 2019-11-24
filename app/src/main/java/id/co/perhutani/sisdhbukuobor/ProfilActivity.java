package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.perhutani.sisdhbukuobor.ui.profil.ProfilFragment;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ProfilFragment.newInstance())
                    .commitNow();
        }
    }
}

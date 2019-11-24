package id.co.perhutani.sisdhbukuobor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.co.perhutani.sisdhbukuobor.ui.gangguan.GangguanFragment;

public class GangguanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gangguan_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GangguanFragment.newInstance())
                    .commitNow();
        }
    }
}

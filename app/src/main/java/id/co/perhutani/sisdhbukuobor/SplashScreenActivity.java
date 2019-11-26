package id.co.perhutani.sisdhbukuobor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class SplashScreenActivity extends AppCompatActivity {

    //Set waktu lama splashscreen
    private static int splashInterval = 3000;
    private SessionManager session;
    private SQLiteHandler db;
    int count = 0;
    public static String getnama, getbkph;
    private TextView nama, bkph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = new SQLiteHandler(SplashScreenActivity.this.getApplicationContext());
        db.altertable_update_aplication();

        nama = findViewById(R.id.txt_splash_nama);
        getnama = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
        nama.setText(getnama);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

        }, splashInterval);


    }
}

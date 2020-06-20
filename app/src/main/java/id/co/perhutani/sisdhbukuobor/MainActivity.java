package id.co.perhutani.sisdhbukuobor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.DashboardBukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderFragment;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;
import id.co.perhutani.sisdhbukuobor.Service.MyBackgroundService;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.home.HomeFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.profil.ProfilFragment;
import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private SQLiteHandler db;
    public static String username, namedesc, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        // mengaktifkan service sentry io
        AjnClass.pasang_sentry(this.getApplicationContext());
        // Start service
        Intent i = new Intent(this, MyBackgroundService.class);
        MainActivity.this.startService(i);
        // config
        db = new SQLiteHandler(getApplicationContext());
        db.altertable_update_aplication();
        try {
            username = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME);
            namedesc = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
            token = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN);
        } catch (Exception e) {
        }
        try {
            Sentry.getContext().recordBreadcrumb(
                    new BreadcrumbBuilder().setMessage(namedesc).build()
            );
            // Set the user in the current context.
            Sentry.getContext().setUser(
                    new UserBuilder().setUsername(username)
                            .setEmail(namedesc)
                            .build());
        }
        catch (Exception e) {
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                new HomeFragment()).commit();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Keluar Aplikasi?");
        builder.setMessage("Pilih Yes untuk keluar, No untuk kembali")
                .setIcon(R.drawable.ic_exit)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                        break;
                        case R.id.navigation_bukuobor:
                            selectedFragment = new BukuOborFragment();
                            break;
                        case R.id.navigation_workorder:
                            selectedFragment = new WorkOrderFragment();
                            break;
//                        case R.id.navigation_pengelolaan:
//                            selectedFragment = new DashboardBukuOborFragment();
//                            break;
                        case R.id.navigation_profile:
                            selectedFragment = new ProfilFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            selectedFragment).commit();

                    //replacing the fragment
                    if (selectedFragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.nav_host_fragment, selectedFragment);
                        ft.commit();
                    }

                    return true;
                }
            };

}

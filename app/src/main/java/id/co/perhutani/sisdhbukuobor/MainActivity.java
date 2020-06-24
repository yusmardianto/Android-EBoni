package id.co.perhutani.sisdhbukuobor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.DashboardBukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderFragment;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstKondisiPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPihakTerlibatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStrataSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstWaktuLihatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnOverSpin;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPembuatanBedengSapih;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPersiapanLahan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
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
    String str_rolename = "";
    private SessionManager session;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(MainActivity.this.getApplicationContext());

        db = new SQLiteHandler(getApplicationContext());
        db.altertable_update_aplication();
        try {
            username = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME);
            namedesc = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
            token = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN);
            str_rolename = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.KET10);
        } catch (Exception e) {
        }

        if(str_rolename.equals("mobile_rph"))
                                        {
                                            setContentView(R.layout.activity_main_rph);
                                            navView = findViewById(R.id.nav_view_rph);

                                        } else if(str_rolename.equals("mobile_phw")){
            setContentView(R.layout.activity_main_phw);
            navView = findViewById(R.id.nav_view_phw);


                                        }else{
                                            AjnClass.showAlert(MainActivity.this,"Siapa lu ?");
                                            actionDeleteData();
                                        }


//        setContentView(R.layout.activity_main);
//        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(navListener);

        // mengaktifkan service sentry io
        AjnClass.pasang_sentry(this.getApplicationContext());
        // Start service
        Intent i = new Intent(this, MyBackgroundService.class);
        MainActivity.this.startService(i);
        // config

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

    private void actionDeleteData() {
        session.setLogin(false);
        db.deleteAllRow(UserSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstAnakPetakSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisGangguanHutanSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisSatwa.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisTemuan.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstWaktuLihatSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisPalSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstKondisiPalSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisTanamanSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisPermasalahanSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstStrataSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstPihakTerlibatSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnGangguanKeamananHutan.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPerubahanKelas.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPemantauanSatwa.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnRegisterPcp.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnInteraksimdh.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnIdentifikasiTenurial.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnLaporanPalBatas.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPengelolaanHutan.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPersiapanLahan.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPembuatanBedengSapih.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnOverSpin.SQL_DELETE_ALL_ROWS);

        db.clear_database(db.getReadableDatabase());

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){

//                        BottomNavigationView.getMenu().removeItem(R.id.navigation_home);
//                        case R.id.navigation_home.rem(View.VISIBLE)
                        case R.id.navigation_home:

                            selectedFragment = new HomeFragment();
                        break;
                        case R.id.navigation_bukuobor:
//                            selectedFragment = new BukuOborFragment();
                            selectedFragment.setMenuVisibility(true);
                            break;
                        case R.id.navigation_workorder:
                            selectedFragment = new WorkOrderFragment();
                            break;
                        case R.id.navigation_tallysheet:
                            selectedFragment = new TallySheetFragment();
                            break;
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

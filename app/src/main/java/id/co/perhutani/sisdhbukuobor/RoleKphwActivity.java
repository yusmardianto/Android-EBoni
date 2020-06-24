package id.co.perhutani.sisdhbukuobor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.home.HomeFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.profil.ProfilFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderFragment;

public class RoleKphwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_kphw);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_role_kphw,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
//                        case R.id.navigation_home:
//                            selectedFragment = new HomeFragment();
//                            break;

                        case R.id.navigation_tallysheet:
                            selectedFragment = new TallySheetFragment();
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
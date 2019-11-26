package id.co.perhutani.sisdhbukuobor.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SessionManager session;
    private SQLiteHandler db;

    public static String username, namedesc, token;
    int count = 0;
    private Handler handler = new Handler();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        db = new SQLiteHandler(getActivity().getApplicationContext());
        db.altertable_update_aplication();

        session = new SessionManager(getActivity().getApplicationContext());
        try {
            username = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME);
            namedesc = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
            token = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN);
        } catch (Exception e) {
        }
        // mengaktifkan service sentry io
//        AjnClass.pasang_sentry(this.getActivity().getApplicationContext());
//        AjnClass.test_fungsi_sentry();



        TextView user_info_nama = homeView.findViewById(R.id.text_nama);
        user_info_nama.setText("Hai, "+namedesc);
        TextView str_tanggal = homeView.findViewById(R.id.text_tanggal);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = df.format(c.getTime());
        str_tanggal.setText(formattedDate);
        return homeView;
    }
}
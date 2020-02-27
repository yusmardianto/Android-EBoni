package id.co.perhutani.sisdhbukuobor.FragmentUi.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SessionManager session;
    private SQLiteHandler db;
    public static String username, namedesc, token;
    public static String jml_data_gukamhut, jml_data_perubahankelas, jml_data_interaksimdh, jml_data_pemantauansatwa, jml_data_lappalbatas, jml_data_registerpcp, jml_data_tenurial;
    TextView txt_jumlah_data_gukamhut, txt_jumlah_data_perubahankelas, txt_jumlah_data_interaksimdh, txt_jumlah_data_pemantauansatwa, txt_jumlah_data_lappalbatas, txt_jumlah_data_registerpcp, txt_jumlah_data_tenurial;
    int count = 0;
    private Handler handler = new Handler();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        db = new SQLiteHandler(getActivity().getApplicationContext());
        db.altertable_update_aplication();

        session = new SessionManager(getActivity().getApplicationContext());
        try
        {
            username = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME);
            namedesc = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
            token = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN);
            jml_data_gukamhut = String.valueOf(db.cek_jumlah_data(TrnGangguanKeamananHutan.TABLE_NAME));
            jml_data_perubahankelas = String.valueOf(db.cek_jumlah_data(TrnPerubahanKelas.TABLE_NAME));
            jml_data_interaksimdh = String.valueOf(db.cek_jumlah_data(TrnInteraksimdh.TABLE_NAME));
            jml_data_pemantauansatwa = String.valueOf(db.cek_jumlah_data(TrnPemantauanSatwa.TABLE_NAME));
            jml_data_lappalbatas = String.valueOf(db.cek_jumlah_data(TrnLaporanPalBatas.TABLE_NAME));
            jml_data_registerpcp = String.valueOf(db.cek_jumlah_data(TrnRegisterPcp.TABLE_NAME));
            jml_data_tenurial = String.valueOf(db.cek_jumlah_data(TrnIdentifikasiTenurial.TABLE_NAME));
        }
        catch (Exception e){
        }

        txt_jumlah_data_gukamhut = homeView.findViewById(R.id.txt_jumlah_data_gukamhut);
        txt_jumlah_data_gukamhut.setText(jml_data_gukamhut);
        txt_jumlah_data_perubahankelas = homeView.findViewById(R.id.txt_jumlah_data_perubahankelas);
        txt_jumlah_data_perubahankelas.setText(jml_data_perubahankelas);
        txt_jumlah_data_interaksimdh = homeView.findViewById(R.id.txt_jumlah_data_interaksimdh);
        txt_jumlah_data_interaksimdh.setText(jml_data_interaksimdh);
        txt_jumlah_data_pemantauansatwa = homeView.findViewById(R.id.txt_jumlah_data_pemantauansatwa);
        txt_jumlah_data_pemantauansatwa.setText(jml_data_pemantauansatwa);
        txt_jumlah_data_lappalbatas = homeView.findViewById(R.id.txt_jumlah_data_laporanpalbatas);
        txt_jumlah_data_lappalbatas.setText(jml_data_lappalbatas);
        txt_jumlah_data_registerpcp = homeView.findViewById(R.id.txt_jumlah_data_registerpcp);
        txt_jumlah_data_registerpcp.setText(jml_data_registerpcp);
        txt_jumlah_data_tenurial = homeView.findViewById(R.id.txt_jumlah_data_identifikasitenurial);
        txt_jumlah_data_tenurial.setText(jml_data_tenurial);

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
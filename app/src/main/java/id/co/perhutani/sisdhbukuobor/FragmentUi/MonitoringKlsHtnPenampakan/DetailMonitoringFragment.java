package id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;


public class DetailMonitoringFragment extends Fragment {

    View view_detail_monitoring;
    private static final String MSG_KEY = "id_anakpetak";
    private static Context context;
    private static String id_anakpetak;
    private static SQLiteHandler db;

    private static String get_anakpetak, get_luas, get_jenistanaman, get_tahuntanam, get_kbd, get_dkn, get_kelashutan;
    private TextView anakpetak, luas, jenistanaman, tahuntanam, kbd, dkn, kelashutan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_detail_monitoring = inflater.inflate(R.layout.fragment_detail_monitoring, container, false);

        context=getActivity();
        try {
            String message = getArguments().getString(MSG_KEY);
            if (message != null) {
                id_anakpetak=message;
            } else {
                id_anakpetak="null";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        db = new SQLiteHandler(getActivity());

        Toolbar toolbar = view_detail_monitoring.findViewById(R.id.top_navigation_detail_monitoring);
        toolbar.setNavigationIcon(R.drawable.ic_kembali);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MonitoringKlsHtnFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        try {

            get_anakpetak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.ANAK_PETAK_NAME);
            get_jenistanaman = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.JENIS_TANAMAN);
            get_luas = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.LUAS);
            get_tahuntanam = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.TAHUN_TANAM);
            get_kbd = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.KBD);
            get_dkn = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.DKN);
            get_kelashutan = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.KELAS_HUTAN);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        anakpetak = view_detail_monitoring.findViewById(R.id.detail_monitoring_anakpetak);
        anakpetak.setText(get_anakpetak);
        jenistanaman = view_detail_monitoring.findViewById(R.id.detail_monitoring_jenistanaman);
        jenistanaman.setText(get_jenistanaman);
        luas = view_detail_monitoring.findViewById(R.id.detail_monitoring_luas);
        luas.setText(get_luas);
        tahuntanam = view_detail_monitoring.findViewById(R.id.detail_monitoring_tahuntanam);
        tahuntanam.setText(get_tahuntanam);
        kbd = view_detail_monitoring.findViewById(R.id.detail_monitoring_kbd);
        kbd.setText(get_kbd);
        dkn = view_detail_monitoring.findViewById(R.id.detail_monitoring_dkn);
        dkn.setText(get_dkn);
        kelashutan = view_detail_monitoring.findViewById(R.id.detail_monitoring_kelashutan);
        kelashutan.setText(get_kelashutan);

        return view_detail_monitoring;
    }
}
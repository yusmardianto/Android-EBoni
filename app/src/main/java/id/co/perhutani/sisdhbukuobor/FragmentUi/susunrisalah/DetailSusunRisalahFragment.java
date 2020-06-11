package id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah;

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
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;


public class DetailSusunRisalahFragment extends Fragment {

    View detail_susun_risalah;
    private static final String MSG_KEY = "id_anakpetak";
    private static Context context;
    private static String id_anakpetak;
    private static SQLiteHandler db;

    private static String get_kph, get_divisi, get_bkph, get_rph, get_petak, get_anakpetak,
                        get_luas, get_jenis_tanaman, get_peninggi, get_bonita, get_kbd,get_dkn,
                        get_nha, get_tahun_tanam, get_kelas_hutan;

    private TextView divisi, bkph, kph, rph, petak, anak_petak, luas, jenis_tanaman, peninggi,
                    bonita, kbd, dkn, nha, tahun_tanam, kelas_hutan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detail_susun_risalah = inflater.inflate(R.layout.fragment_detail_susun_risalah, container, false);

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

        Toolbar toolbar = detail_susun_risalah.findViewById(R.id.top_navigation_detail_susunrisalah);
        toolbar.setNavigationIcon(R.drawable.ic_kembali);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SusunRisalahFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        try {
            get_divisi = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.DIVISI);
            get_kph = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.KPH);
            get_bkph = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.BKPH);
            get_rph = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.RPH);
            get_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.PETAK_NAME);
            get_anakpetak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,id_anakpetak,MstAnakPetakSchema.ANAK_PETAK_NAME);
            get_luas = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.LUAS);
            get_jenis_tanaman = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.JENIS_TANAMAM);
            get_peninggi = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.PENINGGI);
            get_bonita = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.BONITA);
            get_kbd = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.KBD);
            get_dkn = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.DKN);
            get_nha = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.N_HA);
            get_tahun_tanam = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.TAHUN_TANAM);
            get_kelas_hutan = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah.ANAK_PETAK_ID,id_anakpetak,TrnSusunRisalah.KELAS_HUTAN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//

        divisi = detail_susun_risalah.findViewById(R.id.detail_risalah_divisi);
        divisi.setText(get_divisi);
        bkph = detail_susun_risalah.findViewById(R.id.detail_risalah_bkph);
        bkph.setText(get_bkph);
        kph = detail_susun_risalah.findViewById(R.id.detail_risalah_kph);
        kph.setText(get_kph);
        rph = detail_susun_risalah.findViewById(R.id.detail_risalah_rph);
        rph.setText(get_rph);
        petak = detail_susun_risalah.findViewById(R.id.detail_risalah_petak);
        petak.setText(get_petak);
        anak_petak = detail_susun_risalah.findViewById(R.id.detail_risalah_anakpetak);
        anak_petak.setText(get_anakpetak);
        luas = detail_susun_risalah.findViewById(R.id.detail_risalah_luas);
        luas.setText(get_luas);
        jenis_tanaman = detail_susun_risalah.findViewById(R.id.detail_risalah_jenis_tanaman);
        jenis_tanaman.setText(get_jenis_tanaman);
        peninggi = detail_susun_risalah.findViewById(R.id.detail_risalah_peninggi);
        peninggi.setText(get_peninggi);
        bonita = detail_susun_risalah.findViewById(R.id.detail_risalah_bonita);
        bonita.setText(get_bonita);
        kbd = detail_susun_risalah.findViewById(R.id.detail_risalah_kbd);
        kbd.setText(get_kbd);
        dkn = detail_susun_risalah.findViewById(R.id.detail_risalah_dkn);
        dkn.setText(get_dkn);
        nha = detail_susun_risalah.findViewById(R.id.detail_risalah_nha);
        nha.setText(get_nha);
        tahun_tanam = detail_susun_risalah.findViewById(R.id.detail_risalah_tahun_tanam);
        tahun_tanam.setText(get_tahun_tanam);
        kelas_hutan = detail_susun_risalah.findViewById(R.id.detail_risalah_kelashutan);
        kelas_hutan.setText(get_kelas_hutan);

        return detail_susun_risalah;
    }
}
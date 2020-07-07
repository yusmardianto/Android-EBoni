package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.TallySheetAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon.CreatePuPohonFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

public class DetailTallySheetFragment extends Fragment {

    View view_detail_tallysheet;
//    private static final String MSG_KEY = "id";
    private static Context context;
    private static String id_tallysheet;
    private static SQLiteHandler db;

    private SessionManager session;


    private static String ts_nopu,ts_bagian_hutan, ts_kph, ts_bkph, ts_rph, ts_petak, ts_anak_petak, ts_desa,
            ts_kecamatan, ts_kabupaten, ts_luasbaku, ts_luaspu , ts_tahuntanam, ts_tglinventarisasi,
            ts_kh_awal, ts_kh, ts_peninggi_kayusdh,ts_bonita, ts_umur, ts_jenis_tanaman_pokok,ts_jenis_tanaman_pencampur,
            ts_jenis_tanaman_sela, ts_jarak_tanam, ts_pertumbuhan_tegakan,ts_kerataan_tegakan, ts_kemurnian_tegakan,
            ts_bentuk_lapangan, ts_kemiringan_lapangan, ts_arah_lereng, ts_jenis_tanah,ts_warna_tanah,ts_kedalaman_tanah,
            ts_kesarangan_tanah, ts_kemantapan_tanah, ts_batuan_tanah, ts_kandungan_humus, ts_kemasaman, ts_tingkat_erosi,
            ts_intensitas_tumbuhan_bwh, ts_jenis_tumbuhan_bwh, perawatan_kelak;

    private TextView txt_nopu,txt_bagian_hutan, txt_kph, txt_bkph, txt_rph, txt_petak, txt_anak_petak, txt_desa,
            txt_kecamatan, txt_kabupaten, txt_tahuntanam,txt_tglinventarisasi, txt_luasbaku, txt_luaspu, txt_kh_awal, txt_kh, txt_peninggi_kayusdh,
            txt_bonita, txt_umur, txt_jenis_tanaman_pokok, txt_jenis_tanaman_pencampur, txt_jenis_tanaman_sela, txt_jarak_tanam,
            txt_pertumbuhan_tegakan, txt_kerataan_tegakan, txt_kemurnian_tegakan, txt_bentuk_lapangan, txt_kemiringan_lapangan,
            txt_arah_lereng, txt_jenis_tanah, txt_warna_tanah, txt_kedalaman_tanah, txt_kesarangan_tanah, txt_kemantapan_tanah, txt_batuan_tanah,
            txt_kandungan_humus, txt_kemasaman, txt_tingkat_erosi, txt_intensitas_tumbuhan_bwh, txt_jenis_tumbuhan_bwh, txt_perawatan_kelak;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_detail_tallysheet = inflater.inflate(R.layout.fragment_detail_tally_sheet, container, false);
        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
        try {
            String message = session.getPreferences(getActivity(),"ses_id_tallysheet");
            if (message != null) {
                id_tallysheet=message;
            } else {
                id_tallysheet="null";
                AjnClass.showAlert(getActivity(), "Terjadi kesalahan dalam pengambilan data");
                Fragment fragment = new ListTallySheetFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        try {
            ts_nopu = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet._ID);
            ts_kph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KPH_NAME);
            ts_bagian_hutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BAGIAN_HUTAN_NAME);
            ts_bkph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BKPH_NAME);
            ts_rph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.RPH_NAME);
            ts_petak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.PETAK_NAME);
            ts_anak_petak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.ANAK_PETAK_NAME);
            ts_desa = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.DESA_NAME);
            ts_kecamatan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KECAMATAN_NAME);
            ts_kabupaten = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KABUPATEN_NAME);
            ts_tglinventarisasi = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TGL_INVENTARISASI);
            ts_luasbaku = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.LUAS_BAKU);
            ts_luaspu = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.LUAS_PU_NAME);
            ts_kh_awal = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KH_AWAL_NAME);
            ts_kh = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KELAS_HUTAN_NAME);
            ts_peninggi_kayusdh = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.SDH_KAYU_PENINGGI);
            ts_bonita = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BONITA);
            ts_tahuntanam = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TAHUN_TANAM);
            ts_umur = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.UMUR);
            ts_jenis_tanaman_pokok = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JENIS_TANAMAN_POKOK_NAME);
            ts_jenis_tanaman_pencampur = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JENIS_TANAMAN_PENCAMPUR_NAME);
            ts_jenis_tanaman_sela = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JENIS_TANAMAN_SELA_NAME);
            ts_jarak_tanam = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JARAK_TANAM);
            ts_pertumbuhan_tegakan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.PERTUMBUHAN_TEGAKAN_NAME);
            ts_kerataan_tegakan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KERATAAN_TEGAKAN_NAME);
            ts_kemurnian_tegakan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEMURNIAN_TEGAKAN_NAME);
            ts_bentuk_lapangan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BENTUK_LAPANGAN_NAME);
            ts_kemiringan_lapangan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEMIRINGAN_LAPANGAN_NAME);
            ts_arah_lereng = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.ARAH_LERENG_NAME);
            ts_jenis_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JENIS_TANAH_NAME);
            ts_warna_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.WARNA_TANAH_NAME);
            ts_kedalaman_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEDALAMAN_TANAH_NAME);
            ts_kesarangan_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KESARANGAN_TANAH_NAME);
            ts_kemantapan_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEMANTAPAN_TANAH_NAME);
            ts_batuan_tanah = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BATUAN_TANAH_NAME);
            ts_kandungan_humus = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KANDUNGAN_HUMUS_NAME);
            ts_kemasaman = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEMASAMAN_NAME);
            ts_tingkat_erosi = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TINGKAT_EROSI_NAME);
            ts_intensitas_tumbuhan_bwh = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TUMBUHAN_BWH_INTENSITAS_NAME);
            ts_jenis_tumbuhan_bwh = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TUMBUHAN_BWH_JENIS_NAME);
            perawatan_kelak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KETERANGAN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        txt_nopu = view_detail_tallysheet.findViewById(R.id.detail_ts_no_pu);
        txt_nopu.setText(ts_nopu);
        txt_kph = view_detail_tallysheet.findViewById(R.id.detail_ts_kph);
        txt_kph.setText(ts_kph);
        txt_bagian_hutan = view_detail_tallysheet.findViewById(R.id.detail_ts_bagianhutan);
        txt_bagian_hutan.setText(ts_bagian_hutan);
        txt_bkph = view_detail_tallysheet.findViewById(R.id.detail_ts_bkph);
        txt_bkph.setText(ts_bkph);
        txt_rph = view_detail_tallysheet.findViewById(R.id.detail_ts_rph);
        txt_rph.setText(ts_rph);
        txt_petak = view_detail_tallysheet.findViewById(R.id.detail_ts_petak);
        txt_petak.setText(ts_petak);
        txt_anak_petak = view_detail_tallysheet.findViewById(R.id.detail_ts_anakpetak);
        txt_anak_petak.setText(ts_anak_petak);
        txt_desa = view_detail_tallysheet.findViewById(R.id.detail_ts_desa);
        txt_desa.setText(ts_desa);
        txt_kecamatan = view_detail_tallysheet.findViewById(R.id.detail_ts_kecamatan);
        txt_kecamatan.setText(ts_kecamatan);
        txt_kabupaten = view_detail_tallysheet.findViewById(R.id.detail_ts_kabupaten);
        txt_kabupaten.setText(ts_kabupaten);
        txt_tglinventarisasi = view_detail_tallysheet.findViewById(R.id.detail_ts_tgl_inven);
        txt_tglinventarisasi.setText(ts_tglinventarisasi);
        txt_luasbaku = view_detail_tallysheet.findViewById(R.id.detail_ts_luasbaku);
        txt_luaspu = view_detail_tallysheet.findViewById(R.id.detail_ts_luaspu);
        txt_luaspu.setText(ts_luaspu);
        txt_kh_awal = view_detail_tallysheet.findViewById(R.id.detail_ts_kh_awal);
        txt_kh_awal.setText(ts_kh_awal);
        txt_kh = view_detail_tallysheet.findViewById(R.id.detail_ts_kh_awal);
        txt_kh.setText(ts_kh);
        txt_peninggi_kayusdh = view_detail_tallysheet.findViewById(R.id.detail_ts_peninggi_sdhkayu);
        txt_peninggi_kayusdh.setText(ts_peninggi_kayusdh);
        txt_bonita = view_detail_tallysheet.findViewById(R.id.detail_ts_bonita);
        txt_bonita.setText(ts_bonita);
        txt_tahuntanam = view_detail_tallysheet.findViewById(R.id.detail_ts_tahuntanam);
        txt_tahuntanam.setText(ts_tahuntanam);
        txt_umur = view_detail_tallysheet.findViewById(R.id.detail_ts_no_umur);
        txt_umur.setText(ts_umur);
        txt_jenis_tanaman_pokok = view_detail_tallysheet.findViewById(R.id.detail_ts_jns_tanaman_pokok);
        txt_jenis_tanaman_pokok.setText(ts_jenis_tanaman_pokok);
        txt_jenis_tanaman_pencampur = view_detail_tallysheet.findViewById(R.id.detail_ts_jns_tanaman_pencampur);
        txt_jenis_tanaman_pencampur.setText(ts_jenis_tanaman_pencampur);
        txt_jenis_tanaman_sela = view_detail_tallysheet.findViewById(R.id.detail_ts_jsn_tanaman_sela);
        txt_jenis_tanaman_sela.setText(ts_jenis_tanaman_sela);
        txt_jarak_tanam = view_detail_tallysheet.findViewById(R.id.detail_ts_jrk_tanam);
        txt_jarak_tanam.setText(ts_jarak_tanam);
        txt_pertumbuhan_tegakan = view_detail_tallysheet.findViewById(R.id.detail_ts_pertumbuhan_tegakan);
        txt_pertumbuhan_tegakan.setText(ts_pertumbuhan_tegakan);
        txt_kerataan_tegakan = view_detail_tallysheet.findViewById(R.id.detail_ts_kerataan_tegakan);
        txt_kerataan_tegakan.setText(ts_kerataan_tegakan);
        txt_kemurnian_tegakan = view_detail_tallysheet.findViewById(R.id.detail_ts_kemurnian_tegakan);
        txt_kemurnian_tegakan.setText(ts_kemurnian_tegakan);
        txt_bentuk_lapangan = view_detail_tallysheet.findViewById(R.id.detail_ts_btk_lapangan);
        txt_bentuk_lapangan.setText(ts_bentuk_lapangan);
        txt_kemiringan_lapangan = view_detail_tallysheet.findViewById(R.id.detail_ts_kemiringan_lapangan);
        txt_kemiringan_lapangan.setText(ts_kemiringan_lapangan);
        txt_arah_lereng = view_detail_tallysheet.findViewById(R.id.detail_ts_arah_lereng);
        txt_arah_lereng.setText(ts_arah_lereng);
        txt_arah_lereng = view_detail_tallysheet.findViewById(R.id.detail_ts_arah_lereng);
        txt_arah_lereng.setText(ts_arah_lereng);
        txt_jenis_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_jns_tnh);
        txt_jenis_tanah.setText(ts_jenis_tanah);
        txt_jenis_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_jns_tnh);
        txt_jenis_tanah.setText(ts_jenis_tanah);
        txt_kedalaman_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_kedalaman_tanah);
        txt_kedalaman_tanah.setText(ts_kedalaman_tanah);
        txt_kesarangan_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_kesarangan_tanah);
        txt_kesarangan_tanah.setText(ts_kesarangan_tanah);
        txt_kemantapan_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_kemantapan_tanah);
        txt_kemantapan_tanah.setText(ts_kemantapan_tanah);
        txt_batuan_tanah = view_detail_tallysheet.findViewById(R.id.detail_ts_batuan_tanah);
        txt_batuan_tanah.setText(ts_batuan_tanah);
        txt_kandungan_humus = view_detail_tallysheet.findViewById(R.id.detail_ts_humus);
        txt_kandungan_humus.setText(ts_kandungan_humus);
        txt_kemasaman = view_detail_tallysheet.findViewById(R.id.detail_ts_kemasaman);
        txt_kemasaman.setText(ts_kemasaman);
        txt_tingkat_erosi = view_detail_tallysheet.findViewById(R.id.detail_ts_tingkat_erosi);
        txt_tingkat_erosi.setText(ts_tingkat_erosi);
        txt_intensitas_tumbuhan_bwh = view_detail_tallysheet.findViewById(R.id.detail_ts_intensitas_tumbuhan_bwh);
        txt_intensitas_tumbuhan_bwh.setText(ts_intensitas_tumbuhan_bwh);
        txt_jenis_tumbuhan_bwh = view_detail_tallysheet.findViewById(R.id.detail_ts_jns_tumbuhan_bwh);
        txt_jenis_tumbuhan_bwh.setText(ts_jenis_tumbuhan_bwh);
        txt_perawatan_kelak = view_detail_tallysheet.findViewById(R.id.detail_ts_perawatan_kelak);
        txt_perawatan_kelak.setText(perawatan_kelak);



        return view_detail_tallysheet;
    }
}
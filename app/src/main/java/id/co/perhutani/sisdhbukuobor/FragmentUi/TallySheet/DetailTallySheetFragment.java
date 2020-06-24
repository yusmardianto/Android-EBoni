package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

public class DetailTallySheetFragment extends Fragment {

    View view_detail_tallysheet;
    private static final String MSG_KEY = "id";
    private static Context context;
    private static String id_tallysheet;
    private static SQLiteHandler db;

    private static String ts_bagian_hutan, ts_kph, ts_bkph, ts_rph, ts_petak, ts_anak_petak, ts_desa, ts_jarakdesa,
            ts_kecamatan, ts_kabupaten, ts_tinggipdl, ts_iklim, ts_curah_hujan, ts_kelashutan, ts_fungsihutan,
            ts_penggunaanhutan, ts_tahuntanam, ts_bonitalalu, ts_bonitabaru, ts_kbd, ts_dkn, ts_volume,
            ts_intensitassampling, ts_carasampling, ts_tglinventarisasi, ts_pelaksana, ts_kepalaseksi, ts_no_rak,
            ts_no_laci, ts_tallysheet_plot, ts_keterangan;
    private TextView txt_bagian_hutan, txt_kph, txt_bkph, txt_rph, txt_petak, txt_anak_petak, txt_desa, txt_jarakdesa,
            txt_kecamatan, txt_kabupaten, txt_tinggipdl, txt_iklim, txt_curah_hujan, txt_kelashutan, txt_fungsihutan,
            txt_penggunaanhutan, txt_tahuntanam, txt_bonitalalu, txt_bonitabaru, txt_kbd, txt_dkn, txt_volume,
            txt_intensitassampling, txt_carasampling, txt_tglinventarisasi, txt_pelaksana, txt_kepalaseksi, txt_no_rak,
            txt_no_laci, txt_tallysheet_plot, txt_keterangan;;

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
        context=getActivity();
        try {
            String message = getArguments().getString(MSG_KEY);
            if (message != null) {
                id_tallysheet=message;
            } else {
                id_tallysheet="null";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Toolbar toolbar = view_detail_tallysheet.findViewById(R.id.top_navigation_detail_tallysheet);
        toolbar.setNavigationIcon(R.drawable.ic_kembali);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TallySheetFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        try {
            ts_kph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KPH);
            ts_bagian_hutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BAGIAN_HUTAN);
            ts_bkph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BKPH);
            ts_rph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.RPH);
            ts_petak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.PETAK);
            ts_anak_petak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.ANAK_PETAK);
            ts_desa = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.DESA);
            ts_jarakdesa = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.JARAK_DESA);
            ts_kecamatan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KECAMATAN);
            ts_kabupaten = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KABUPATEN);
            ts_tinggipdl = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TINGGI_PDL);
            ts_iklim = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.IKLIM);
            ts_curah_hujan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.CURAH_HUJAN);
            ts_kelashutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KELAS_HUTAN);
            ts_fungsihutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.FUNGSI_HUTAN);
            ts_penggunaanhutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.PENGGUNAAN_HUTAN);
            ts_tahuntanam = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TAHUN_TANAM);
            ts_bonitalalu = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BONITA_LALU);
            ts_bonitabaru = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BONITA_BARU);
            ts_kbd = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KBD);
            ts_dkn = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.DKN);
            ts_volume = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.VOLUME);
            ts_intensitassampling = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.INTENSITAS_SAMPLING);
            ts_carasampling = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.CARA_SAMPLING);
            ts_tglinventarisasi = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TGL_INVENTARISASI);
            ts_pelaksana = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.PELAKSANA);
            ts_kepalaseksi = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KEPALA_SEKSI);
            ts_no_rak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.NO_RAK);
            ts_no_laci = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.NO_LACI);
            ts_tallysheet_plot = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TALLYSHEET_PLOT);
            ts_keterangan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KETERANGAN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

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
        txt_jarakdesa = view_detail_tallysheet.findViewById(R.id.detail_ts_jarak_desa);
        txt_jarakdesa.setText(ts_desa);
        txt_kecamatan = view_detail_tallysheet.findViewById(R.id.detail_ts_kecamatan);
        txt_kecamatan.setText(ts_kecamatan);
        txt_kabupaten = view_detail_tallysheet.findViewById(R.id.detail_ts_kabupaten);
        txt_kabupaten.setText(ts_kabupaten);
        txt_tinggipdl = view_detail_tallysheet.findViewById(R.id.detail_ts_tinggipdl);
        txt_tinggipdl.setText(ts_tinggipdl);
        txt_iklim = view_detail_tallysheet.findViewById(R.id.detail_ts_iklim);
        txt_iklim.setText(ts_iklim);
        txt_curah_hujan = view_detail_tallysheet.findViewById(R.id.detail_ts_curah_hujan);
        txt_curah_hujan.setText(ts_curah_hujan);
        txt_kelashutan = view_detail_tallysheet.findViewById(R.id.detail_ts_kelashutan);
        txt_kelashutan.setText(ts_kelashutan);
        txt_fungsihutan = view_detail_tallysheet.findViewById(R.id.detail_ts_fungsihutan);
        txt_fungsihutan.setText(ts_fungsihutan);
        txt_penggunaanhutan = view_detail_tallysheet.findViewById(R.id.detail_ts_penggunaanhutan);
        txt_penggunaanhutan.setText(ts_penggunaanhutan);
        txt_tahuntanam = view_detail_tallysheet.findViewById(R.id.detail_ts_tahuntanam);
        txt_tahuntanam.setText(ts_tahuntanam);
        txt_bonitalalu = view_detail_tallysheet.findViewById(R.id.detail_ts_bonitalalu);
        txt_bonitalalu.setText(ts_bonitalalu);
        txt_bonitabaru = view_detail_tallysheet.findViewById(R.id.detail_ts_bonitabaru);
        txt_bonitabaru.setText(ts_bonitabaru);
        txt_kbd = view_detail_tallysheet.findViewById(R.id.detail_ts_kbd);
        txt_kbd.setText(ts_kbd);
        txt_dkn = view_detail_tallysheet.findViewById(R.id.detail_ts_dkn);
        txt_dkn.setText(ts_dkn);
        txt_volume = view_detail_tallysheet.findViewById(R.id.detail_ts_volume);
        txt_volume.setText(ts_volume);
        txt_intensitassampling = view_detail_tallysheet.findViewById(R.id.detail_ts_intensitas_sampling);
        txt_intensitassampling.setText(ts_intensitassampling);
        txt_carasampling = view_detail_tallysheet.findViewById(R.id.detail_ts_cara_sampling);
        txt_carasampling.setText(ts_carasampling);
        txt_tglinventarisasi = view_detail_tallysheet.findViewById(R.id.detail_ts_tgl_inven);
        txt_tglinventarisasi.setText(ts_tglinventarisasi);
        txt_pelaksana = view_detail_tallysheet.findViewById(R.id.detail_ts_pelaksana);
        txt_pelaksana.setText(ts_pelaksana);
        txt_kepalaseksi = view_detail_tallysheet.findViewById(R.id.detail_ts_kepala_seksi);
        txt_kepalaseksi.setText(ts_kepalaseksi);
        txt_no_rak = view_detail_tallysheet.findViewById(R.id.detail_ts_no_rak);
        txt_no_rak.setText(ts_no_rak);
        txt_no_laci = view_detail_tallysheet.findViewById(R.id.detail_ts_no_laci);
        txt_no_laci.setText(ts_no_laci);
        txt_tallysheet_plot = view_detail_tallysheet.findViewById(R.id.detail_ts_plot);
        txt_tallysheet_plot.setText(ts_tallysheet_plot);
        txt_keterangan = view_detail_tallysheet.findViewById(R.id.detail_ts_keterangan);
        txt_keterangan.setText(ts_keterangan);

        return view_detail_tallysheet;
    }
}
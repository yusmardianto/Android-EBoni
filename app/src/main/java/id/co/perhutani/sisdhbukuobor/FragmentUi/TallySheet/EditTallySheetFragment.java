package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

public class EditTallySheetFragment extends Fragment {

    View view_detail_ts;
    private static SQLiteHandler db;
    private static final String MSG_KEY = "id";
    private static String id_tallysheet;
    private static String ts_bagian_hutan, ts_kph, ts_bkph, ts_rph, ts_petak, ts_anak_petak, ts_desa, ts_jarakdesa,
            ts_kecamatan, ts_kabupaten, ts_tinggipdl, ts_iklim, ts_curah_hujan, ts_kelashutan, ts_fungsihutan,
            ts_penggunaanhutan, ts_tahuntanam, ts_bonitalalu, ts_bonitabaru, ts_kbd, ts_dkn, ts_volume,
            ts_intensitassampling, ts_carasampling, ts_tglinventarisasi, ts_pelaksana, ts_kepalaseksi, ts_no_rak,
            ts_no_laci, ts_tallysheet_plot, ts_keterangan;

    private TextView txt_bagian_hutan, txt_kph, txt_bkph, txt_rph, txt_petak, txt_anak_petak, txt_desa, txt_jarakdesa,
            txt_kecamatan, txt_kabupaten, txt_tinggipdl, txt_iklim, txt_curah_hujan, txt_kelashutan, txt_fungsihutan,
            txt_penggunaanhutan, txt_tahuntanam, txt_bonitalalu, txt_bonitabaru, txt_kbd, txt_dkn, txt_volume,
            txt_intensitassampling, txt_carasampling, txt_tglinventarisasi, txt_pelaksana, txt_kepalaseksi, txt_no_rak,
            txt_no_laci, txt_tallysheet_plot, txt_keterangan;
    private Button btn_simpan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_detail_ts = inflater.inflate(R.layout.fragment_edit_tally_sheet, container, false);
        db = new SQLiteHandler(getActivity());
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

        Toolbar toolbar = view_detail_ts.findViewById(R.id.toolbar_edit_tallysheet);
        toolbar.setNavigationIcon(R.drawable.ic_back);
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
            ts_tahuntanam = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TAHUN_TANAM);
            ts_bonitalalu = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.BONITA);
            ts_kbd = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KBD);
            ts_dkn = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.DKN);
            ts_volume = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.VOLUME);
            ts_tglinventarisasi = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.TGL_INVENTARISASI);
            ts_keterangan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_tallysheet,TrnTallySheet.KETERANGAN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        txt_bonitalalu = view_detail_ts.findViewById(R.id.edit_ts_bonita_lama);
        txt_bonitalalu.setText(ts_bonitalalu);
        txt_bonitabaru = view_detail_ts.findViewById(R.id.edit_ts_bonita_baru);
        txt_bonitabaru.setText(ts_bonitabaru);
        txt_kbd = view_detail_ts.findViewById(R.id.edit_ts_kbd);
        txt_kbd.setText(ts_kbd);
        txt_dkn = view_detail_ts.findViewById(R.id.edit_ts_dkn);
        txt_dkn.setText(ts_dkn);
        txt_volume = view_detail_ts.findViewById(R.id.edit_ts_volume);
        txt_volume.setText(ts_volume);

        btn_simpan = view_detail_ts.findViewById(R.id.ts_btnsubmit);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_tallysheet();
            }
        });

        return view_detail_ts;
    }

    public void simpan_tallysheet() {

        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Simpan ?")
                .setCancelText("Batal")
                .setConfirmText("Simpan")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("Dibatalkan!")
                                .setContentText("")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Success!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub


                                try {
                                    TallySheetModel ts = new TallySheetModel();
                                    ts.setId(id_tallysheet);
                                    ts.setTs_kph(ts_kph);
                                    ts.setTs_bagian_hutan(ts_bagian_hutan);
                                    ts.setTs_bkph(ts_bkph);
                                    ts.setTs_rph(ts_rph);
                                    ts.setTs_rph(ts_rph);
                                    ts.setTs_petak(ts_petak);
                                    ts.setTs_anak_petak(ts_anak_petak);
                                    ts.setTs_desa(ts_desa);
                                    ts.setTs_jarakdesa(ts_jarakdesa);
                                    ts.setTs_kecamatan(ts_kecamatan);
                                    ts.setTs_kabupaten(ts_kabupaten);
                                    ts.setTs_tinggipdl(ts_tinggipdl);
                                    ts.setTs_iklim(ts_iklim);
                                    ts.setTs_curah_hujan(ts_curah_hujan);
                                    ts.setTs_kelashutan(ts_kelashutan);
                                    ts.setTs_fungsihutan(ts_fungsihutan);
                                    ts.setTs_penggunaanhutan(ts_penggunaanhutan);
                                    ts.setTs_tahuntanam(ts_tahuntanam);
                                    ts.setTs_bonitalalu(txt_bonitalalu.getText().toString());
                                    ts.setTs_bonitabaru(txt_bonitabaru.getText().toString());
                                    ts.setTs_kbd(txt_kbd.getText().toString());
                                    ts.setTs_dkn(txt_dkn.getText().toString());
                                    ts.setTs_volume(txt_volume.getText().toString());
                                    ts.setTs_intensitassampling(ts_intensitassampling);
                                    ts.setTs_carasampling(ts_carasampling);
                                    ts.setTs_tglinventarisasi(ts_tglinventarisasi);
                                    ts.setTs_pelaksana(ts_pelaksana);
                                    ts.setTs_kepalaseksi(ts_kepalaseksi);
                                    ts.setTs_no_rak(ts_no_rak);
                                    ts.setTs_no_laci(ts_no_laci);
                                    ts.setTs_tallysheet_plot(ts_tallysheet_plot);
                                    ts.setTs_keterangan(ts_keterangan);
                                    ts.setTs_ket9("2");
                                    db.EditDataTallySheet(ts);

                                    Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                    Fragment fragment = new TallySheetFragment();
                                    FragmentTransaction ft = manager.beginTransaction();
                                    ft.replace(R.id.nav_host_fragment, fragment);
                                    ft.commit();


                                } catch (Exception e) {
                                    AjnClass.showAlert(getActivity(), e.toString());
                                    e.printStackTrace();
                                }
                            }

                        }, 1000);
                    }
                })
                .show();

    }
}
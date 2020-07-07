package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstFungsiHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPenggunaanHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;


public class CreateTallySheetFragment extends Fragment {

    View create_tally_sheet;
    EditText ts_bagian_hutan, ts_kph, ts_bkph, ts_rph, ts_petak, ts_anak_petak, ts_desa, ts_jarakdesa,
            ts_kecamatan, ts_kabupaten, ts_tinggipdl, ts_iklim, ts_curah_hujan, ts_kelashutan, ts_fungsihutan,
            ts_penggunaanhutan, ts_tahuntanam, ts_bonitalalu, ts_bonitabaru, ts_kbd, ts_dkn, ts_volume,
            ts_intensitassampling, ts_carasampling, ts_tglinventarisasi, ts_pelaksana, ts_kepalaseksi, ts_no_rak,
            ts_no_laci, ts_tallysheet_plot, ts_keterangan;
    Button btn_simpan;
    private static SQLiteHandler db;
    private Spinner spin_kelashutan, spin_fungsihutan, spin_penggunaanhutan, spin_kph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void load_spinner_kelashutan() {
        List<String> listtpg = db.getKelasHutan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelashutan.setAdapter(dataAdapter_tpg);
        spin_kelashutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelashutan = spin_kelashutan.getSelectedItem().toString();
                String id_kelashutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelashutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
                ts_kelashutan.setText(id_kelashutan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_fungsihutan() {
        List<String> listtpg = db.getFungsiHutan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_fungsihutan.setAdapter(dataAdapter_tpg);
        spin_fungsihutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_fungsihutan = spin_fungsihutan.getSelectedItem().toString();
                String id_fungsihutan = db.getDataDetail(MstFungsiHutanSchema.TABLE_NAME,
                        MstFungsiHutanSchema.FUNGSI_HUTAN_NAME, pil_fungsihutan, MstFungsiHutanSchema.FUNGSI_HUTAN_ID);
                ts_fungsihutan.setText(id_fungsihutan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_penggunaanhutan() {
        List<String> listtpg = db.getPenggunaanHutan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_penggunaanhutan.setAdapter(dataAdapter_tpg);
        spin_penggunaanhutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_penggunaanhutan = spin_penggunaanhutan.getSelectedItem().toString();
                String id_penggunaanhutan = db.getDataDetail(MstPenggunaanHutan.TABLE_NAME,
                        MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME, pil_penggunaanhutan, MstPenggunaanHutan.PENGGUNAAN_HUTAN_ID);
                ts_penggunaanhutan.setText(id_penggunaanhutan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kph() {
        List<String> listtpg = db.getPenggunaanHutan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kph.setAdapter(dataAdapter_tpg);
        spin_kph.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kph = spin_kph.getSelectedItem().toString();
                String id_penggunaanhutan = db.getDataDetail(MstPenggunaanHutan.TABLE_NAME,
                        MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME, pil_kph, MstPenggunaanHutan.PENGGUNAAN_HUTAN_ID);
                ts_penggunaanhutan.setText(id_penggunaanhutan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        create_tally_sheet = inflater.inflate(R.layout.fragment_create_tally_sheet, container, false);

        Toolbar toolbar = create_tally_sheet.findViewById(R.id.toolbar_create_tallysheet);
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

        db = new SQLiteHandler(getActivity());

        ts_bagian_hutan = create_tally_sheet.findViewById(R.id.ts_bagianhutan);
        ts_kph = create_tally_sheet.findViewById(R.id.ts_kph);
        ts_bkph = create_tally_sheet.findViewById(R.id.ts_bkph);
        ts_rph = create_tally_sheet.findViewById(R.id.ts_rph);
        ts_petak = create_tally_sheet.findViewById(R.id.ts_petak);
        ts_anak_petak = create_tally_sheet.findViewById(R.id.ts_anak_petak);
        ts_desa = create_tally_sheet.findViewById(R.id.ts_desa);
        ts_jarakdesa = create_tally_sheet.findViewById(R.id.ts_jarak_desa);
        ts_kecamatan = create_tally_sheet.findViewById(R.id.ts_kecamatan);
        ts_kabupaten = create_tally_sheet.findViewById(R.id.ts_kabupaten);
        ts_tinggipdl = create_tally_sheet.findViewById(R.id.ts_tinggi_pdl);
        ts_iklim = create_tally_sheet.findViewById(R.id.ts_iklim);
        ts_curah_hujan = create_tally_sheet.findViewById(R.id.ts_curah_hujan);

        ts_kelashutan = create_tally_sheet.findViewById(R.id.ts_kelas_hutan);
        spin_kelashutan = create_tally_sheet.findViewById(R.id.spinner_kelashutan);
        load_spinner_kelashutan();
        String pil_kelashutan = spin_kelashutan.getSelectedItem().toString();
        String id_kelashutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelashutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
        ts_kelashutan.setText(id_kelashutan);

        ts_fungsihutan = create_tally_sheet.findViewById(R.id.ts_fungsi_hutan);
        spin_fungsihutan = create_tally_sheet.findViewById(R.id.spinner_fungsihutan);
        load_spinner_fungsihutan();
        String pil_fungsihutan = spin_fungsihutan.getSelectedItem().toString();
        String id_fungsihutan = db.getDataDetail(MstFungsiHutanSchema.TABLE_NAME,
                MstFungsiHutanSchema.FUNGSI_HUTAN_NAME, pil_fungsihutan, MstFungsiHutanSchema.FUNGSI_HUTAN_ID);
        ts_fungsihutan.setText(id_fungsihutan);

        ts_penggunaanhutan = create_tally_sheet.findViewById(R.id.ts_penggunaan_hutan);
        spin_penggunaanhutan = create_tally_sheet.findViewById(R.id.spinner_penggunaanhutan);
        load_spinner_penggunaanhutan();
        String pil_penggunaanhutan = spin_penggunaanhutan.getSelectedItem().toString();
        String id_penggunaanhutan = db.getDataDetail(MstPenggunaanHutan.TABLE_NAME,
                MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME, pil_penggunaanhutan, MstPenggunaanHutan.PENGGUNAAN_HUTAN_ID);
        ts_penggunaanhutan.setText(id_penggunaanhutan);

        ts_tahuntanam = create_tally_sheet.findViewById(R.id.ts_tahun_tanam);
        ts_bonitalalu = create_tally_sheet.findViewById(R.id.ts_bonita_lama);
        ts_bonitabaru = create_tally_sheet.findViewById(R.id.ts_bonita_baru);
        ts_kbd = create_tally_sheet.findViewById(R.id.ts_kbd);
        ts_dkn = create_tally_sheet.findViewById(R.id.ts_dkn);
        ts_volume = create_tally_sheet.findViewById(R.id.ts_volume);
        ts_intensitassampling = create_tally_sheet.findViewById(R.id.ts_intensitas_sampling);
        ts_carasampling = create_tally_sheet.findViewById(R.id.ts_cara_sampling);
        ts_tglinventarisasi = create_tally_sheet.findViewById(R.id.ts_tanggal_inventarisasi);
        ts_pelaksana = create_tally_sheet.findViewById(R.id.ts_pelaksana);
        ts_kepalaseksi = create_tally_sheet.findViewById(R.id.ts_kepala_seksi);
        ts_no_rak = create_tally_sheet.findViewById(R.id.ts_rak_dokumentasi_nomor);
        ts_no_laci = create_tally_sheet.findViewById(R.id.ts_laci_nomor);
        ts_tallysheet_plot = create_tally_sheet.findViewById(R.id.ts_tallysheet_plot);
        ts_keterangan = create_tally_sheet.findViewById(R.id.ts_keterangan);
        btn_simpan = create_tally_sheet.findViewById(R.id.ts_btnsubmit);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpanData();
            }
        });

        return create_tally_sheet;
    }

    public void SimpanData() {

        try {
            final String save_bagianhutan = ts_bagian_hutan.getText().toString();
            final String save_kph = ts_kph.getText().toString();
            final String save_bkph = ts_bkph.getText().toString();
            final String save_rph = ts_rph.getText().toString();
            final String save_petak = ts_petak.getText().toString();
            final String save_anak_petak = ts_anak_petak.getText().toString();
            final String save_desa = ts_desa.getText().toString();
            final String save_jarakdesa = ts_jarakdesa.getText().toString();
            final String save_kecamatan = ts_kecamatan.getText().toString();
            final String save_kabupaten = ts_kabupaten.getText().toString();
            final String save_tinggipdl = ts_tinggipdl.getText().toString();
            final String save_iklim = ts_iklim.getText().toString();
            final String save_curah_hujan = ts_curah_hujan.getText().toString();
            final String save_kelas_hutan = ts_kelashutan.getText().toString();
            final String save_fungsi_hutan = ts_fungsihutan.getText().toString();
            final String save_penggunaan_hutan = ts_penggunaanhutan.getText().toString();
            final String save_tahun_tanam = ts_tahuntanam.getText().toString();
            final String save_bonita_lalu = ts_bonitalalu.getText().toString();
            final String save_bonita_baru = ts_bonitabaru.getText().toString();
            final String save_kbd = ts_kbd.getText().toString();
            final String save_dkn = ts_dkn.getText().toString();
            final String save_volume = ts_volume.getText().toString();
            final String save_intensitas_sampling = ts_intensitassampling.getText().toString();
            final String save_cara_sampling = ts_carasampling.getText().toString();
            final String save_tgl_inven = ts_tglinventarisasi.getText().toString();
            final String save_pelaksana = ts_pelaksana.getText().toString();
            final String save_kepalaseksi = ts_kepalaseksi.getText().toString();
            final String save_no_rak = ts_no_rak.getText().toString();
            final String save_no_laci = ts_no_laci.getText().toString();
            final String save_ts_plot = ts_tallysheet_plot.getText().toString();
            final String save_keterangan = ts_keterangan.getText().toString();

            if (save_bagianhutan.equals("") || save_bagianhutan.equals("0") || save_bagianhutan.equals(" ") || save_bagianhutan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Bagian Hutan harus diisi");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(save_bagianhutan)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText(save_bagianhutan)
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
                                        .setContentText(save_bagianhutan)
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

//                                            String ambilKunci = "perhutani";
//                                            String ambilKata = isipetak.getText().toString();
//                                            String enKata = "";
                                            ContentValues values_aktifitas = new ContentValues();
//                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            values_aktifitas.put(TrnTallySheet.BAGIAN_HUTAN, save_bagianhutan);
                                            values_aktifitas.put(TrnTallySheet.KPH, save_kph);
                                            values_aktifitas.put(TrnTallySheet.BKPH, save_bkph);
                                            values_aktifitas.put(TrnTallySheet.RPH, save_rph);
                                            values_aktifitas.put(TrnTallySheet.PETAK, save_petak);
                                            values_aktifitas.put(TrnTallySheet.ANAK_PETAK, save_anak_petak);
                                            values_aktifitas.put(TrnTallySheet.DESA, save_desa);
                                            values_aktifitas.put(TrnTallySheet.JARAK_DESA, save_jarakdesa);
                                            values_aktifitas.put(TrnTallySheet.KECAMATAN, save_kecamatan);
                                            values_aktifitas.put(TrnTallySheet.KABUPATEN, save_kabupaten);
                                            values_aktifitas.put(TrnTallySheet.KELAS_HUTAN, save_kelas_hutan);
                                            values_aktifitas.put(TrnTallySheet.TAHUN_TANAM, save_tahun_tanam);
                                            values_aktifitas.put(TrnTallySheet.BONITA, save_bonita_lalu);
                                            values_aktifitas.put(TrnTallySheet.KBD, save_kbd);
                                            values_aktifitas.put(TrnTallySheet.DKN, save_dkn);
                                            values_aktifitas.put(TrnTallySheet.VOLUME, save_volume);
                                            values_aktifitas.put(TrnTallySheet.TGL_INVENTARISASI, save_tgl_inven);
                                            values_aktifitas.put(TrnTallySheet.KETERANGAN, save_keterangan);
                                            values_aktifitas.put(TrnTallySheet.KET9, "0");
                                            db.create(TrnTallySheet.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                                          // Move to fragment gangguan
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

        } catch (Exception e) {
            AjnClass.showAlert(getActivity(), "error " + e.toString());
//            sendMessage(e.getMessage());
        }
    }
    
}
package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.tambahidentifikasitenurial;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;

public class TambahIdentifikasiTenurialFragment extends Fragment {

    private EditText petak_id, jenis_permasalahan, tanggal, kelas_hutan, strata, luas_baku,
            luas_tenurial, kondisi_petak, awal_konflik, status_penyelesaian;

    private TambahIdentifikasiTenurialViewModel mViewModel;

    private static SQLiteHandler db;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_permasalahan;
    private Spinner spin_kelas_hutan;
    private Spinner spin_pihak_terlibat;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    private Button btnSimpanIdentifikasi;

    public static TambahIdentifikasiTenurialFragment newInstance() {
        return new TambahIdentifikasiTenurialFragment();
    }

    public void load_spinner_anak_petak() {
        List<String> listtpg = db.getAnakPetak();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(dataAdapter_tpg);
        spin_anak_petak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_petak = spin_anak_petak.getSelectedItem().toString();
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME,
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
                petak_id.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_jenis_permasalahan() {
        List<String> listtpg = db.getJenisPermasalahan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_permasalahan.setAdapter(dataAdapter_tpg);
        spin_jenis_permasalahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_permasalahan = spin_jenis_permasalahan.getSelectedItem().toString();
                String id_jenis_permasalahan = db.getDataDetail(MstJenisPermasalahanSchema.TABLE_NAME,
                        MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME, pil_jenis_permasalahan, MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_ID);
                jenis_permasalahan.setText(id_jenis_permasalahan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kelas_hutan() {
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
        spin_kelas_hutan.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan = spin_kelas_hutan.getSelectedItem().toString();
                String id_kelas_hutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_hutan.setText(id_kelas_hutan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tambah_identifikasi_tenurial_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        petak_id = root.findViewById(R.id.tenurial_anakpetak);
        jenis_permasalahan = root.findViewById(R.id.tenurial_jenispermasalahan);

        tanggal = root.findViewById(R.id.tenurial_tahun);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tglmulai.format(new Date());
        tanggal.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date1 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tgl = sdf_view.format(calendar.getTime());

                tanggal.setText(str_tgl);
            }

        };
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        kelas_hutan = root.findViewById(R.id.tenurial_kelashutan);
        strata = root.findViewById(R.id.tenurial_strata);
        luas_baku = root.findViewById(R.id.tenurial_luasbaku);
        luas_tenurial = root.findViewById(R.id.tenurial_luastenurial);
        kondisi_petak = root.findViewById(R.id.tenurial_kondisipetakidentifikasi);
        awal_konflik = root.findViewById(R.id.tenurial_awalkonflik);
        status_penyelesaian = root.findViewById(R.id.tenurial_statuspenyelesaian);
        btnSimpanIdentifikasi = root.findViewById(R.id.tenurial_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_tenurial_anakpetak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        petak_id.setText(id_petak);

        spin_jenis_permasalahan = root.findViewById(R.id.spinner_jenis_permasalahan);
        load_spinner_jenis_permasalahan();
        String pil_jenis_permasalahan = spin_anak_petak.getSelectedItem().toString();
        String id_jenis_permasalahan = db.getDataDetail(MstJenisPermasalahanSchema.TABLE_NAME, MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME, pil_jenis_permasalahan, MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_ID);
        jenis_permasalahan.setText(id_jenis_permasalahan);

        spin_kelas_hutan = root.findViewById(R.id.spinner_tenurial_kelashutan);
        load_spinner_kelas_hutan();
        String pil_kelas_hutan = spin_anak_petak.getSelectedItem().toString();
        String id_kelas_hutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
        kelas_hutan.setText(id_kelas_hutan);

        spin_pihak_terlibat = root.findViewById(R.id.spinner_tenurial_pihakterlibat);

        btnSimpanIdentifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    public void act_simpan() {
        try {
            final String jenispermasalahan = jenis_permasalahan.getText().toString();
            final String anak_petak = petak_id.getText().toString();
            final String tahun = tanggal.getText().toString();
            final String kelashutan = kelas_hutan.getText().toString();
            final String stratatenurial = strata.getText().toString();
            final String luasbaku = luas_baku.getText().toString();
            final String luastenurial = luas_tenurial.getText().toString();
            final String kondisiidentifikasi = kondisi_petak.getText().toString();
            final String awalkonflik = awal_konflik.getText().toString();
            final String pihakterlibat = spin_pihak_terlibat.getSelectedItem().toString();
            final String status = status_penyelesaian.getText().toString();

            if (jenispermasalahan.equals("") || jenispermasalahan.equals("0") || jenispermasalahan.equals(" ") || jenispermasalahan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Permasalahan tidak boleh kosong");

            } else if (anak_petak.equals("") || anak_petak.equals("0") || anak_petak.equals(" ") || anak_petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak tidak boleh kosong");

            } else if (tahun.equals("") || tahun.equals("0") || tahun.equals(" ") || tahun.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tahun tidak boleh kosong");

            } else if (kelashutan.equals("") || kelashutan.equals("0") || kelashutan.equals(" ") || kelashutan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan tidak boleh kosong");

            } else if (stratatenurial.equals("") || stratatenurial.equals("0") || stratatenurial.equals(" ") || stratatenurial.equals(null)) {
                AjnClass.showAlert(getActivity(), "Strata tidak boleh kosong");

            } else if (luasbaku.equals("") || luasbaku.equals("0") || luasbaku.equals(" ") || luasbaku.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Baku tidak boleh kosong");

            } else if (luastenurial.equals("") || luastenurial.equals("0") || luastenurial.equals(" ") || luastenurial.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Tenurial tidak boleh kosong");

            } else if (kondisiidentifikasi.equals("") || kondisiidentifikasi.equals("0") || kondisiidentifikasi.equals(" ") || kondisiidentifikasi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kondisi Identifikasi tidak boleh kosong");

            } else if (awalkonflik.equals("") || awalkonflik.equals("0") || awalkonflik.equals(" ") || awalkonflik.equals(null)) {
                AjnClass.showAlert(getActivity(), "Awal Konflik tidak boleh kosong");

            } else if (pihakterlibat.equals("") || pihakterlibat.equals("- Pilih Pihak Terlibat -") || pihakterlibat.equals(" ") || pihakterlibat.equals(null)) {
                AjnClass.showAlert(getActivity(), "Pihak Terlibat tidak boleh kosong");

            } else if (status.equals("") || status.equals("0") || status.equals(" ") || status.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Penyelesaian tidak boleh kosong");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(jenispermasalahan)
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
                                        .setContentText(jenispermasalahan)
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
                                            ContentValues values_aktifitas = new ContentValues();
                                            values_aktifitas.put(TrnIdentifikasiTenurial.ANAK_PETAK_ID, petak_id.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.JENIS_PERMASALAHAN, jenis_permasalahan.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.TANGGAL, tanggal.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KELAS_HUTAN_ID, kelas_hutan.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.STRATA, strata.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.LUAS_BAKU, luas_baku.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.LUAS_TENURIAL, luas_tenurial.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KONDISI_PETAK, kondisi_petak.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.AWAL_KONFLIK, awal_konflik.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.PIHAK_TERLIBAT, spin_pihak_terlibat.getSelectedItem().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.STATUS_PENYELESAIAN, status_penyelesaian.getText().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KET1, spin_anak_petak.getSelectedItem().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KET2, spin_jenis_permasalahan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KET3, spin_kelas_hutan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KET4, spin_pihak_terlibat.getSelectedItem().toString());
                                            values_aktifitas.put(TrnIdentifikasiTenurial.KET9, "0");
                                            db.create(TrnIdentifikasiTenurial.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment Identifikasi Konflik Tenurial
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListIdentifikasiTenurialFragment();
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

        } catch(Exception e) {
                AjnClass.showAlert(getActivity(), "error " + e.toString());
//                sendMessage(e.getMessage());
        }
    }
}
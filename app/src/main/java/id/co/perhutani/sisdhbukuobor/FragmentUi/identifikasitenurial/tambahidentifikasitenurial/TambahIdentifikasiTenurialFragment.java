package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.tambahidentifikasitenurial;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;

public class TambahIdentifikasiTenurialFragment extends Fragment {

    private EditText petak_id, jenis_permasalahan, tanggal, kelas_hutan, strata, luas_baku,
            luas_tenurial, kondisi_petak, awal_konflik, pihak_terlibat, status_penyelesaian;

    private TambahIdentifikasiTenurialViewModel mViewModel;

    private static SQLiteHandler db;
    private Spinner spin_anak_petak;

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
        pihak_terlibat = root.findViewById(R.id.tenurial_pihakterlibat);
        status_penyelesaian = root.findViewById(R.id.tenurial_statuspenyelesaian);
        btnSimpanIdentifikasi = root.findViewById(R.id.tenurial_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_tenurial_anakpetak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        petak_id.setText(id_petak);

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
                                        values_aktifitas.put(TrnIdentifikasiTenurial.PIHAK_TERLIBAT, pihak_terlibat.getText().toString());
                                        values_aktifitas.put(TrnIdentifikasiTenurial.STATUS_PENYELESAIAN, status_penyelesaian.getText().toString());
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

            } catch (Exception e) {
                AjnClass.showAlert(getActivity(), "error " + e.toString());
//                sendMessage(e.getMessage());
        }
    }
}
package id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.tambahperubahan;

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
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;

public class TambahPerubahanFragment extends Fragment {

    private EditText petak_id, tahun, luas, jenis_tanaman, kelas_hutan, luas_perkiraan,
            jenis_perkiraan, kelas_perkiraan, no_bappkh, luas_definitif, jenis_definitif,
            kelas_definitif, keterangan;


    private TambahPerubahanViewModel mViewModel;

    private static SQLiteHandler db;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_tanaman;
    private Spinner spin_jenis_tanaman_perkiraan;
    private Spinner spin_kelas_hutan;
    private Spinner spin_kelas_hutan_perkiraan;
    private Spinner spin_jenis_tanaman_definitif;
    private Spinner spin_kelas_hutan_definitif;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    private Button btnSimpanPerubahan;


    public static TambahPerubahanFragment newInstance() {
        return new TambahPerubahanFragment();
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
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
                petak_id.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_jenis_tanaman() {
        List<String> listjenis = db.getJenisTanaman();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman = spin_jenis_tanaman.getSelectedItem().toString();
                String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_tanaman.setText(id_jenis);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kelas_hutan() {
        List<String> listjenis = db.getKelasHutan();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
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


    public void load_spinner_jenis_tanaman_perkiraan() {
        List<String> listjenis = db.getJenisTanaman();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_perkiraan.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman_perkiraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman_perkiraan = spin_jenis_tanaman_perkiraan.getSelectedItem().toString();
                String id_jenis_perkiraan = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_perkiraan, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_perkiraan.setText(id_jenis_perkiraan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kelas_hutan_perkiraan() {
        List<String> listjenis = db.getKelasHutan();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_perkiraan.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan_perkiraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan_perkiraan = spin_kelas_hutan_perkiraan.getSelectedItem().toString();
                String id_kelas_hutan_perkiraan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_perkiraan, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_perkiraan.setText(id_kelas_hutan_perkiraan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_jenis_tanaman_definitif() {
        List<String> listjenis = db.getJenisTanaman();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_definitif.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman_definitif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman_definitif = spin_jenis_tanaman_definitif.getSelectedItem().toString();
                String id_jenis_tanaman_definitif = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_definitif, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_definitif.setText(id_jenis_tanaman_definitif);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kelas_hutan_definitif() {
        List<String> listjenis = db.getKelasHutan();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_definitif.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan_definitif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan_definitif = spin_kelas_hutan_definitif.getSelectedItem().toString();
                String id_kelas_hutan_definitif = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_definitif, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_definitif.setText(id_kelas_hutan_definitif);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tambah_perubahan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        petak_id = root.findViewById(R.id.perubahankls_idpetakperubahan);
        tahun = root.findViewById(R.id.perubahankls_tahunperubahan);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tglmulai.format(new Date());
        tahun.setFocusable(false);
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

                tahun.setText(str_tgl);
            }

        };
        tahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luas = root.findViewById(R.id.perubahankls_luasperubahan);
        jenis_tanaman = root.findViewById(R.id.perubahankls_jenisperubahan);
        kelas_hutan = root.findViewById(R.id.perubahankls_kelasperubahan);
        luas_perkiraan = root.findViewById(R.id.perubahankls_luasperkiraan);
        jenis_perkiraan = root.findViewById(R.id.perubahankls_jenisperkiraan);
        kelas_perkiraan = root.findViewById(R.id.perubahankls_kelasperkiraan);
        no_bappkh = root.findViewById(R.id.perubahankls_nobappkh);
        luas_definitif = root.findViewById(R.id.perubahankls_luasdefinitif);
        jenis_definitif = root.findViewById(R.id.perubahankls_jenisdefinitif);
        kelas_definitif = root.findViewById(R.id.perubahankls_kelasdefinitif);
        keterangan = root.findViewById(R.id.perubahankls_ketperubahan);
        btnSimpanPerubahan = root.findViewById(R.id.perubahankls_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak_perubahan);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
        petak_id.setText(id_petak);

        spin_jenis_tanaman = root.findViewById(R.id.spinner_jenis_perubahan);
        load_spinner_jenis_tanaman();
        String pil_jenis_tanaman = spin_jenis_tanaman.getSelectedItem().toString();
        String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman, MstJenisTanamanSchema.JENIS_TANAMAN_NAME);
        jenis_tanaman.setText(id_jenis);

        spin_jenis_tanaman_perkiraan = root.findViewById(R.id.spinner_jenis_perkiraan);
        load_spinner_jenis_tanaman_perkiraan();
        String pil_jenis_tanaman_perkiraan = spin_jenis_tanaman_perkiraan.getSelectedItem().toString();
        String id_jenis_perkiraan = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_perkiraan, MstJenisTanamanSchema.JENIS_TANAMAN_NAME);
        jenis_perkiraan.setText(id_jenis_perkiraan);

        spin_kelas_hutan = root.findViewById(R.id.spinner_kelas_hutan);
        load_spinner_kelas_hutan();
        String pil_kelas_hutan = spin_kelas_hutan.getSelectedItem().toString();
        String id_kelas_hutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan, MstKelasHutanSchema.KELAS_HUTAN_NAME);
        kelas_hutan.setText(id_kelas_hutan);

        spin_kelas_hutan_perkiraan = root.findViewById(R.id.spinner_kelas_hutan_perkiraan);
        load_spinner_kelas_hutan_perkiraan();
        String pil_kelas_hutan_perkiraan = spin_kelas_hutan_perkiraan.getSelectedItem().toString();
        String id_kelas_hutan_perkiraan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_perkiraan, MstKelasHutanSchema.KELAS_HUTAN_NAME);
        kelas_perkiraan.setText(id_kelas_hutan_perkiraan);

        spin_jenis_tanaman_definitif = root.findViewById(R.id.spinner_jenis_tanaman_definitif);
        load_spinner_jenis_tanaman_definitif();
        String pil_jenis_tanaman_definitif = spin_jenis_tanaman_definitif.getSelectedItem().toString();
        String id_jenis_tanaman_definitif = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_definitif, MstJenisTanamanSchema.JENIS_TANAMAN_NAME);
        jenis_definitif.setText(id_jenis_tanaman_definitif);

        spin_kelas_hutan_definitif = root.findViewById(R.id.spinner_perubahan_kelas_definitif);
        load_spinner_kelas_hutan_definitif();
        String pil_kelas_hutan_definitif = spin_kelas_hutan_definitif.getSelectedItem().toString();
        String id_kelas_hutan_definitif = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_definitif, MstKelasHutanSchema.KELAS_HUTAN_NAME);
        kelas_definitif.setText(id_kelas_hutan_definitif);

        btnSimpanPerubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });


        return root;
    }

    public void act_simpan() {
        try {

            final String jenistanaman = jenis_tanaman.getText().toString();
            final String petak = petak_id.getText().toString();
            final String str_tanggal = tahun.getText().toString();
            final String kelas = kelas_hutan.getText().toString();
            final String luasperubahan = luas.getText().toString();
            final String tanamanperkiraan = jenis_perkiraan.getText().toString();
            final String kelasperkiraan = kelas_perkiraan.getText().toString();
            final String luasperkiraan = luas_perkiraan.getText().toString();
            final String nobap = no_bappkh.getText().toString();
            final String tanamandefinitif = jenis_definitif.getText().toString();
            final String luasdefinitif = luas_definitif.getText().toString();
            final String kelasdefinitif = kelas_definitif.getText().toString();

            if (jenistanaman.equals("") || jenistanaman.equals("0") || jenistanaman.equals(" ") || jenistanaman.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman harus diisi");

            } else if (petak.equals("") || petak.equals("0") || petak.equals(" ") || petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (kelas.equals("") || kelas.equals("0") || kelas.equals(" ") || kelas.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan harus diisi");

            } else if (luasperubahan.equals("") || luasperubahan.equals("0") || luasperubahan.equals(" ") || luasperubahan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas harus diisi");

            } else if (str_tanggal.equals("") || str_tanggal.equals("0") || str_tanggal.equals(" ") || str_tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (tanamanperkiraan.equals("") || tanamanperkiraan.equals("0") || tanamanperkiraan.equals(" ") || tanamanperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman Perkiraan harus diisi");

            } else if (kelasperkiraan.equals("") || kelasperkiraan.equals("0") || kelasperkiraan.equals(" ") || kelasperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan Perkiraan harus diisi");

            } else if (luasperkiraan.equals("") || luasperkiraan.equals("0") || luasperkiraan.equals(" ") || luasperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Perkiraan harus diisi");

            } else if (nobap.equals("") || nobap.equals("0") || nobap.equals(" ") || nobap.equals(null)) {
                AjnClass.showAlert(getActivity(), "No BAP PKH harus diisi");

            } else if (tanamandefinitif.equals("") || tanamandefinitif.equals("0") || tanamandefinitif.equals(" ") || tanamandefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman Definitf harus diisi");

            } else if (kelasdefinitif.equals("") || kelasdefinitif.equals("0") || kelasdefinitif.equals(" ") || kelasdefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan Definitif harus diisi");

            } else if (luasdefinitif.equals("") || luasdefinitif.equals("0") || luasdefinitif.equals(" ") || luasdefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Definitif harus diisi");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText("")
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
                                        .setContentText("")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        String ambilKunci = "perhutani";
                                        String ambilKata = petak_id.getText().toString();
                                        String enKata = "";
                                        try {
                                            ContentValues values_aktifitas = new ContentValues();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            values_aktifitas.put(TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN, petak_id.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.TAHUN_PERUBAHAN, tahun.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_PERUBAHAN, luas.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN, jenis_tanaman.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN, kelas_hutan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_PERKIRAAN, luas_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN, jenis_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN, kelas_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.NO_BAPPKH, no_bappkh.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_DEFINITIF, luas_definitif.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF, jenis_definitif.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF, kelas_definitif.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET1, spin_anak_petak.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET2, spin_jenis_tanaman.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET3, spin_kelas_hutan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET4, spin_jenis_tanaman_perkiraan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET5, spin_kelas_hutan_perkiraan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET6, spin_jenis_tanaman_definitif.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET7, spin_kelas_hutan_definitif.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KET9, "0");
                                            values_aktifitas.put(TrnPerubahanKelas.HEXA, enKata);
                                            values_aktifitas.put(TrnPerubahanKelas.KETERANGAN_PERUBAHAN, keterangan.getText().toString());
                                            db.create(TrnPerubahanKelas.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment Perubahan Kelas
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListPerubahanKelasFragment();
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
package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.editidentifikasitenurial;

import android.app.DatePickerDialog;
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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPihakTerlibatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStrataSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;

public class EditIdentifikasiTenurialFragment extends Fragment {

    private EditText jenis_permasalahan, tahun, petak, strata, kelas_hutan, luas_baku, luas_tenurial,
            kondisi_petak, awal_konflik, pihak_terlibat, status_penyelesaian;

    private static String id, str_jenis_permasalahan, str_tahun, str_petak, str_strata, str_kelas_hutan,
            str_luas_baku, str_luas_tenurial, str_kondisi_petak, str_awal_konflik,
            str_pihak_terlibat, str_status_penyelesaian, str_anakpetak;

    private Button btnSimpanTenurial;
    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_permasalahan;
    private Spinner spin_kelas_hutan;
    private Spinner spin_strata;
    private Spinner spin_pihak_terlibat;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    private EditIdentifikasiTenurialViewModel mViewModel;

    public static EditIdentifikasiTenurialFragment newInstance() {
        return new EditIdentifikasiTenurialFragment();
    }

    private static String str_spin_anakpetak, str_spin_jenispermasalahan, str_spin_kelashutan,
            str_spin_strata, str_spin_pihakterlibat;

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
                petak.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_anakpetak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(adapter);
        if (str_spin_anakpetak != null) {
            int spinnerPosition = adapter.getPosition(str_spin_anakpetak);
            spin_anak_petak.setSelection(spinnerPosition);
        }
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

        str_spin_jenispermasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_permasalahan.setAdapter(adapter);
        if (str_spin_jenispermasalahan != null) {
            int spinnerPosition = adapter.getPosition(str_spin_jenispermasalahan);
            spin_jenis_permasalahan.setSelection(spinnerPosition);
        }
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

        str_spin_kelashutan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan.setAdapter(adapter);
        if (str_spin_kelashutan != null) {
            int spinnerPosition = adapter.getPosition(str_spin_kelashutan);
            spin_kelas_hutan.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_strata() {
        List<String> listtpg = db.getStrata();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_strata.setAdapter(dataAdapter_tpg);
        spin_strata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_strata = spin_strata.getSelectedItem().toString();
                String id_strata = db.getDataDetail(MstStrataSchema.TABLE_NAME,
                        MstStrataSchema.STRATA_NAME, pil_strata, MstStrataSchema.STRATA_ID);
                strata.setText(id_strata);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_strata = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_strata.setAdapter(adapter);
        if (str_spin_strata != null) {
            int spinnerPosition = adapter.getPosition(str_spin_strata);
            spin_strata.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_pihak_terlibat() {
        List<String> listtpg = db.getPihakTerlibat();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_pihak_terlibat.setAdapter(dataAdapter_tpg);
        spin_pihak_terlibat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_pihak_terlibat = spin_pihak_terlibat.getSelectedItem().toString();
                String id_pihak_terlibat = db.getDataDetail(MstPihakTerlibatSchema.TABLE_NAME,
                        MstPihakTerlibatSchema.PIHAK_TERLIBAT_NAME, pil_pihak_terlibat, MstPihakTerlibatSchema.PIHAK_TERLIBAT_ID);
                pihak_terlibat.setText(id_pihak_terlibat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_pihakterlibat = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_pihak_terlibat.setAdapter(adapter);
        if (str_spin_pihakterlibat != null) {
            int spinnerPosition = adapter.getPosition(str_spin_pihakterlibat);
            spin_pihak_terlibat.setSelection(spinnerPosition);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.edit_identifikasi_tenurial_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        try {
            String message = getArguments().getString(MSG_KEY);
            if (message != null) {
                id = message;
            } else {
                id = "null";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        jenis_permasalahan = root.findViewById(R.id.edit_tenurial_jenispermasalahan);
        petak = root.findViewById(R.id.edit_tenurial_anakpetak);
        kelas_hutan = root.findViewById(R.id.edit_tenurial_kelashutan);

        tahun = root.findViewById(R.id.edit_tenurial_tahun);
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

        strata = root.findViewById(R.id.edit_tenurial_strata);
        luas_baku = root.findViewById(R.id.edit_tenurial_luasbaku);
        luas_tenurial = root.findViewById(R.id.edit_tenurial_luastenurial);
        kondisi_petak = root.findViewById(R.id.edit_tenurial_kondisipetakidentifikasi);
        awal_konflik = root.findViewById(R.id.edit_tenurial_awalkonflik);
        pihak_terlibat = root.findViewById(R.id.edit_tenurial_pihakterlibat);
        status_penyelesaian = root.findViewById(R.id.edit_tenurial_statuspenyelesaian);
        btnSimpanTenurial = root.findViewById(R.id.edit_tenurial_btnsimpan);

        spin_jenis_permasalahan = root.findViewById(R.id.edit_spinner_jenis_permasalahan);
        load_spinner_jenis_permasalahan();

        spin_anak_petak = root.findViewById(R.id.edit_spinner_tenurial_anakpetak);
        load_spinner_anak_petak();

        spin_kelas_hutan = root.findViewById(R.id.edit_spinner_tenurial_kelashutan);
        load_spinner_kelas_hutan();

        spin_strata = root.findViewById(R.id.edit_spinner_tenurial_strata);
        load_spinner_strata();

        spin_pihak_terlibat = root.findViewById(R.id.edit_spinner_tenurial_pihakterlibat);
        load_spinner_pihak_terlibat();

        str_jenis_permasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.JENIS_PERMASALAHAN);
        str_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.ANAK_PETAK_ID);
        str_kelas_hutan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KELAS_HUTAN_ID);
        str_tahun = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.TANGGAL);
        str_strata = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STRATA);
        str_luas_baku = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_BAKU);
        str_luas_tenurial = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_TENURIAL);
        str_kondisi_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KONDISI_PETAK);
        str_awal_konflik = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.AWAL_KONFLIK);
        str_pihak_terlibat = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.PIHAK_TERLIBAT);
        str_status_penyelesaian = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STATUS_PENYELESAIAN);
        str_anakpetak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.HEXA);

        jenis_permasalahan.setText(str_jenis_permasalahan);
        petak.setText(str_petak);
        kelas_hutan.setText(str_kelas_hutan);
        tahun.setText(str_tahun);
        strata.setText(str_strata);
        luas_baku.setText(str_luas_baku);
        luas_tenurial.setText(str_luas_tenurial);
        kondisi_petak.setText(str_kondisi_petak);
        awal_konflik.setText(str_awal_konflik);
        pihak_terlibat.setText(str_pihak_terlibat);
        status_penyelesaian.setText(str_status_penyelesaian);

        btnSimpanTenurial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        Toolbar toolbar = root.findViewById(R.id.toolbar_edittenurial);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListIdentifikasiTenurialFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditIdentifikasiTenurialViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String jenispermasalahan = jenis_permasalahan.getText().toString();
            final String anak_petak = petak.getText().toString();
            final String kelashutan = kelas_hutan.getText().toString();
            final String tahuntenurial = tahun.getText().toString();
            final String stratatenurial = strata.getText().toString();
            final String luasbaku = luas_baku.getText().toString();
            final String luastenurial = luas_tenurial.getText().toString();
            final String kondisiidentifikasi = kondisi_petak.getText().toString();
            final String awalkonflik = awal_konflik.getText().toString();
            final String pihakterlibat = pihak_terlibat.getText().toString();
            final String status = status_penyelesaian.getText().toString();

            if (jenispermasalahan.equals("") || jenispermasalahan.equals("0") || jenispermasalahan.equals(" ") || jenispermasalahan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Permasalahan harus diisi");

            } else if (anak_petak.equals("") || anak_petak.equals("0") || anak_petak.equals(" ") || anak_petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (kelashutan.equals("") || kelashutan.equals("0") || kelashutan.equals(" ") || kelashutan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan harus diisi");

            } else if (tahuntenurial.equals("") || tahuntenurial.equals("0") || tahuntenurial.equals(" ") || tahuntenurial.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (stratatenurial.equals("") || stratatenurial.equals("0") || stratatenurial.equals(" ") || stratatenurial.equals(null)) {
                AjnClass.showAlert(getActivity(), "Strata harus diisi");

            } else if (luasbaku.equals("") || luasbaku.equals("0") || luasbaku.equals(" ") || luasbaku.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Baku harus diisi");

            } else if (luastenurial.equals("") || luastenurial.equals("0") || luastenurial.equals(" ") || luastenurial.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Tenurial harus diisi");

            } else if (kondisiidentifikasi.equals("") || kondisiidentifikasi.equals("0") || kondisiidentifikasi.equals(" ") || kondisiidentifikasi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kondisi Petak Saat Identifikasi harus diisi");

            } else if (awalkonflik.equals("") || awalkonflik.equals("0") || awalkonflik.equals(" ") || awalkonflik.equals(null)) {
                AjnClass.showAlert(getActivity(), "Awal Konflik harus diisi");

            } else if (pihakterlibat.equals("") || pihakterlibat.equals("0") || pihakterlibat.equals(" ") || pihakterlibat.equals(null)) {
                AjnClass.showAlert(getActivity(), "Pihak Terlibat harus diisi");

            } else if (status.equals("") || status.equals("0") || status.equals(" ") || status.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Penyelesaian harus diisi");

            } else {

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

                                        String ambilKunci = "perhutani";
                                        String ambilKata = petak.getText().toString();
                                        String enKata = "";

                                        try {
                                            IdentifikasiTenurialModel Aktifitasnya = new IdentifikasiTenurialModel();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            Aktifitasnya.setID_Tenurial(Integer.parseInt(id));
                                            Aktifitasnya.setJenisPermasalahan(jenis_permasalahan.getText().toString());
                                            Aktifitasnya.setAnakPetak(petak.getText().toString());
                                            Aktifitasnya.setKelasHutan(kelas_hutan.getText().toString());
                                            Aktifitasnya.setTanggal(tahun.getText().toString());
                                            Aktifitasnya.setStrata(strata.getText().toString());
                                            Aktifitasnya.setLuasBaku(luas_baku.getText().toString());
                                            Aktifitasnya.setLuasTenurial(luas_tenurial.getText().toString());
                                            Aktifitasnya.setKondisiPetakSaatIdentifikasi(kondisi_petak.getText().toString());
                                            Aktifitasnya.setAwalKonflik(awal_konflik.getText().toString());
                                            Aktifitasnya.setPihakTerlibat(pihak_terlibat.getText().toString());
                                            Aktifitasnya.setStatusPenyelesaian(status_penyelesaian.getText().toString());
                                            Aktifitasnya.setKet1(spin_jenis_permasalahan.getSelectedItem().toString());
                                            Aktifitasnya.setKet2(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_kelas_hutan.getSelectedItem().toString());
                                            Aktifitasnya.setKet4(spin_strata.getSelectedItem().toString());
                                            Aktifitasnya.setKet5(spin_pihak_terlibat.getSelectedItem().toString());
                                            Aktifitasnya.setKet11(enKata);
                                            Aktifitasnya.setKet9("2");

                                            db.EditDataIdentifikasiTenurial(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
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

        } catch (Exception e) {
            AjnClass.showAlert(getActivity(), "error " + e.toString());
//            sendMessage(e.getMessage());
        }
    }

}

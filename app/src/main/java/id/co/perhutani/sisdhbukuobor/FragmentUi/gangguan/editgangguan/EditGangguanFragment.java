package id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.editgangguan;

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
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;

public class EditGangguanFragment extends Fragment {

    private EditGangguanViewModel mViewModel;

    private EditText tgl_kejadian, isipetak, jenistanaman, tanggal, nomora, isi_kejadian, luas_lahan,
            jumlah_pohon, kerugian_kyp, kerugian_kyb, kerugian_getah,
            nilai_kerugian, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_tgl_kejadian,  str_isipetak, str_jenistanaman,  str_tanggal, str_nomora, str_isi_kejadian, str_luas_lahan,
            str_jumlah_pohon, str_kerugian_kyp, str_kerugian_kyb, str_kerugian_getah,
            str_nilai_kerugian, str_keterangan, str_anakpetak;
    private Button btnSimpanGangguan;
    private Spinner spin_jenis_tanaman;
    private Spinner spin_anak_petak;
    private Spinner spin_gangguan_hutan;
    final Calendar calendar = Calendar.getInstance();
    final Calendar calendar_kejadian = Calendar.getInstance();

    public static EditGangguanFragment newInstance() {
        return new EditGangguanFragment();
    }

    private static String str_edit_gangguan_hutan, str_edit_anakpetak, str_edit_jenistanaman;

    public void load_spinner_gangguan_hutan() {
        List<String> listgangguan = db.getJenisGangguan();
        final int _tpg = listgangguan.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listgangguan) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_gangguan_hutan.setAdapter(dataAdapter_tpg);
        spin_gangguan_hutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_gangguan = spin_gangguan_hutan.getSelectedItem().toString();
                String id_gangguan = db.getDataDetail(MstJenisGangguanHutanSchema.TABLE_NAME,
                        MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, pil_gangguan, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_ID);
                isi_kejadian.setText(id_gangguan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_gangguan_hutan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listgangguan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_gangguan_hutan.setAdapter(adapter);
        if (str_edit_gangguan_hutan != null) {
            int spinnerPosition = adapter.getPosition(str_edit_gangguan_hutan);
            spin_gangguan_hutan.setSelection(spinnerPosition);
        }

    }

    public void load_spinner_anak_petak() {
        List<String> list_anakpetak = db.getAnakPetak();
        final int _tpg = list_anakpetak.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list_anakpetak) {
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
                isipetak.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_anakpetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list_anakpetak);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(adapter);
        if (str_edit_anakpetak != null) {
            int spinnerPosition = adapter.getPosition(str_edit_anakpetak);
            spin_anak_petak.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_jenis_tanaman() {
        List<String> list_tanaman = db.getJenisTanaman();
        final int _tpg = list_tanaman.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list_tanaman) {
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
                String pil_jenis = spin_jenis_tanaman.getSelectedItem().toString();
                String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenistanaman.setText(id_jenis);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_jenistanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list_tanaman);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman.setAdapter(adapter);
        if (str_edit_jenistanaman != null) {
            int spinnerPosition = adapter.getPosition(str_edit_jenistanaman);
            spin_jenis_tanaman.setSelection(spinnerPosition);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_gangguan_fragment, container, false);

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

        Toolbar toolbar = root.findViewById(R.id.toolbar_editgukamhut);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListGangguanFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        tgl_kejadian = root.findViewById(R.id.edit_gangguan_tanggal);
        tgl_kejadian.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date1 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar_kejadian.set(Calendar.YEAR, year);
                calendar_kejadian.set(Calendar.MONTH, monthOfYear);
                calendar_kejadian.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tgl_kejadian = sdf_view.format(calendar_kejadian.getTime());

                tgl_kejadian.setText(str_tgl_kejadian);
            }

        };
        tgl_kejadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar_kejadian.get(Calendar.YEAR), calendar_kejadian.get(Calendar.MONTH),
                        calendar_kejadian.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        isipetak = root.findViewById(R.id.edit_gangguan_idpetak);
        jenistanaman = root.findViewById(R.id.edit_gangguan_jenistanaman);

        tanggal = root.findViewById(R.id.edit_gangguan_tanggalHA);
        tanggal.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date2 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tanggal = sdf_view.format(calendar.getTime());

                tanggal.setText(str_tanggal);
            }

        };
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        nomora = root.findViewById(R.id.edit_gangguan_nomora);
        isi_kejadian = root.findViewById(R.id.edit_gangguan_kejadian);
        luas_lahan = root.findViewById(R.id.edit_gangguan_luas);
        jumlah_pohon = root.findViewById(R.id.edit_gangguan_jmlpohon);
        kerugian_kyp = root.findViewById(R.id.edit_gangguan_kerugiankyp);
        kerugian_kyb = root.findViewById(R.id.edit_gangguan_kerugiankyb);
        kerugian_getah = root.findViewById(R.id.edit_gangguan_kerugiangetah);
        nilai_kerugian = root.findViewById(R.id.edit_gangguan_nilaikerugian);
        keterangan = root.findViewById(R.id.edit_gangguan_keterangan);
        btnSimpanGangguan = root.findViewById(R.id.edit_gangguan_btnsubmit);

        spin_gangguan_hutan = root.findViewById(R.id.edit_spinner_gangguan_hutan);
        load_spinner_gangguan_hutan();
//        String pil_gangguan = spin_gangguan_hutan.getSelectedItem().toString();
//        String id_gangguan = db.getDataDetail(MstJenisGangguanHutanSchema.TABLE_NAME, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, pil_gangguan, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME);
//        isi_kejadian.setText(id_gangguan);

        spin_anak_petak = root.findViewById(R.id.edit_spinner_anak_petak_gukamhut);
        load_spinner_anak_petak();
//        String pil_petak = spin_anak_petak.getSelectedItem().toString();
//        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
//        isipetak.setText(id_petak);

        spin_jenis_tanaman = root.findViewById(R.id.edit_spinner_jenis_tanaman);
        load_spinner_jenis_tanaman();
//        String pil_jenis = spin_jenis_tanaman.getSelectedItem().toString();
//        String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
//        jenistanaman.setText(id_jenis);

        str_tgl_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_KEJADIAN);
        str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET1);
        str_jenistanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET2);
        str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL);
        str_nomora = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_A);
        str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET3);
        str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
        str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
        str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
        str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
        str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
        str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
        str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);
        str_anakpetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.HEXA);

        tgl_kejadian.setText(str_tgl_kejadian);
        isipetak.setText(str_isipetak);
        jenistanaman.setText(str_jenistanaman);
        tanggal.setText(str_tanggal);
        nomora.setText(str_nomora);
        isi_kejadian.setText(str_isi_kejadian);
        luas_lahan.setText(str_luas_lahan);
        jumlah_pohon.setText(str_jumlah_pohon);
        kerugian_kyp.setText(str_kerugian_kyp);
        kerugian_kyb.setText(str_kerugian_kyb);
        kerugian_getah.setText(str_kerugian_getah);
        nilai_kerugian.setText(str_nilai_kerugian);
        keterangan.setText(str_keterangan);

        btnSimpanGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditGangguanViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String tgl = tgl_kejadian.getText().toString();
            final String kejadian = isi_kejadian.getText().toString();
            final String petak = isipetak.getText().toString();
            final String tgl_a = tanggal.getText().toString();
            final String nomorA = nomora.getText().toString();
            final String jenis_tanaman = jenistanaman.getText().toString();
            final String luas = luas_lahan.getText().toString();
            final String nilai = nilai_kerugian.getText().toString();
            final String pohon = jumlah_pohon.getText().toString();
            final String getah = kerugian_getah.getText().toString();
            final String kayubakar = kerugian_kyb.getText().toString();
            final String kayupap = kerugian_kyp.getText().toString();

            if (tgl.equals("") || tgl.equals("0") || tgl.equals(" ") || tgl.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal Kejadian harus diisi");

            } else if (petak.equals("") || petak.equals("0") || petak.equals(" ") || petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak petak harus diisi");

            } else if (nomorA.equals("") || nomorA.equals("0") || nomorA.equals(" ") || nomorA.equals(null)) {
                AjnClass.showAlert(getActivity(), "No Laporan Huruf A harus diisi");

            } else if (tgl_a.equals("") || tgl_a.equals("0") || tgl_a.equals(" ") || tgl_a.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal Laporan Huruf A harus diisi");

            } else if (kejadian.equals("") || kejadian.equals("0") || kejadian.equals(" ") || kejadian.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kejadian harus diisi");

            } else if (luas.equals("") || luas.equals(" ") || luas.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Kerugian harus diisi");

            } else if (jenis_tanaman.equals("") || jenis_tanaman.equals("0") || jenis_tanaman.equals(" ") || jenis_tanaman.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman harus diisi");

            } else if (pohon.equals("") || pohon.equals(" ") || pohon.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah Kerugian Pohon harus diisi");

            } else if (kayupap.equals("") || kayupap.equals(" ") || kayupap.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah Kerugian Kayu Perkakas harus diisi");

            } else if (kayubakar.equals("") || kayubakar.equals(" ") || kayubakar.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah Kerugian Kayu Bakar harus diisi");

            } else if (getah.equals("") || getah.equals(" ") || getah.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah Kerugian Getah harus diisi");

            } else if (nilai.equals("") || nilai.equals(" ") || nilai.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nilai Kerugian harus diisi");

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

                                        String ambilKunci = "perhutani";
                                        String ambilKata = isipetak.getText().toString();
                                        String enKata = "";

                                        try {
                                            GangguanModel Aktifitasnya = new GangguanModel();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            Aktifitasnya.setID_gangguan(Integer.parseInt(id));
                                            Aktifitasnya.setTgl_Kejadian(tgl_kejadian.getText().toString());
                                            Aktifitasnya.setPetak(isipetak.getText().toString());
                                            Aktifitasnya.setJenisTanaman(jenistanaman.getText().toString());
                                            Aktifitasnya.setTanggal(tanggal.getText().toString());
                                            Aktifitasnya.setNoA(nomora.getText().toString());
                                            Aktifitasnya.setIsi(isi_kejadian.getText().toString());
                                            Aktifitasnya.setLuas(luas_lahan.getText().toString());
                                            Aktifitasnya.setPohon(jumlah_pohon.getText().toString());
                                            Aktifitasnya.setKyp(kerugian_kyp.getText().toString());
                                            Aktifitasnya.setKyb(kerugian_kyb.getText().toString());
                                            Aktifitasnya.setGetah(kerugian_getah.getText().toString());
                                            Aktifitasnya.setNilai(nilai_kerugian.getText().toString());
                                            Aktifitasnya.setKeterangan(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet2(spin_jenis_tanaman.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_gangguan_hutan.getSelectedItem().toString());
                                            Aktifitasnya.setKet11(enKata);
                                            Aktifitasnya.setKet9("2");
                                            db.EditDataGangguanHutan(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListGangguanFragment();
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

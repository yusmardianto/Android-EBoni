package id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan;

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
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.ListGangguanFragment;


public class TambahGangguanFragment extends Fragment {

    private EditText isipetak, tanggal, isi_kejadian, luas_lahan,
            jumlah_pohon, kerugian_kyp, kerugian_kyb, kerugian_getah,
            nilai_kerugian, keterangan;

    private Spinner spin_gangguan_hutan;
    private Spinner spin_anak_petak;
    private static SQLiteHandler db;
    final Calendar calendar = Calendar.getInstance();

    private String str_tgl;

    private Button btnSimpanGangguan;

    public static TambahGangguanFragment newInstance() {
        return new TambahGangguanFragment();
    }

    public void load_spinner_gangguan_hutan() {
        List<String> listtpg = db.getJenisGangguan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
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
                String id_gangguan = db.getDataDetail(MstJenisGangguanHutanSchema.TABLE_NAME, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, pil_gangguan, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_ID);
                isi_kejadian.setText(id_gangguan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
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
                isipetak.setText(id_petak);

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
//        return inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        isipetak = root.findViewById(R.id.gangguan_idpetak);
        tanggal = root.findViewById(R.id.gangguan_tanggalHA);
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

        isi_kejadian = root.findViewById(R.id.gangguan_kejadian);
        luas_lahan = root.findViewById(R.id.gangguan_luas);
        jumlah_pohon = root.findViewById(R.id.gangguan_jmlpohon);
        kerugian_kyp = root.findViewById(R.id.gangguan_kerugiankyp);
        kerugian_kyb = root.findViewById(R.id.gangguan_kerugiankyb);
        kerugian_getah = root.findViewById(R.id.gangguan_kerugiangetah);
        nilai_kerugian = root.findViewById(R.id.gangguan_nilaikerugian);
        keterangan = root.findViewById(R.id.gangguan_keterangan);
        btnSimpanGangguan = root.findViewById(R.id.gangguan_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        isipetak.setText(id_petak);

        spin_gangguan_hutan = root.findViewById(R.id.spinner_gangguan_hutan);
        load_spinner_gangguan_hutan();
        String pil_gangguan = spin_gangguan_hutan.getSelectedItem().toString();
        String id_gangguan = db.getDataDetail(MstJenisGangguanHutanSchema.TABLE_NAME, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, pil_gangguan, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_ID);
        isi_kejadian.setText(id_gangguan);

        btnSimpanGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    public void act_simpan() {
        try {

            final String kejadian = isi_kejadian.getText().toString();
            final String str_tanggal = tanggal.getText().toString();
            if (kejadian.equals("") || kejadian.equals("0") || kejadian.equals(" ") || kejadian.equals(null)) {
                AjnClass.showAlert(getActivity(), "Judul Kejadian tidak boleh kosong");

            } else if (str_tanggal.equals("") || str_tanggal.equals("0") || str_tanggal.equals(" ") || str_tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal tidak boleh kosong");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(kejadian)
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
                                        .setContentText(kejadian)
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
                                            values_aktifitas.put(TrnGangguanKeamananHutan.ANAK_PETAK_ID, isipetak.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.TANGGAL_HA, tanggal.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KEJADIAN, isi_kejadian.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_LUAS, luas_lahan.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_POHON, jumlah_pohon.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_KYP, kerugian_kyp.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_KYB, kerugian_kyb.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_GETAH, kerugian_getah.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.NILAI_KERUGIAN, nilai_kerugian.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KETERANGAN, keterangan.getText().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KET1, spin_anak_petak.getSelectedItem().toString());
                                            values_aktifitas.put(TrnGangguanKeamananHutan.KET9, "0");
                                            db.create(TrnGangguanKeamananHutan.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment gangguan
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

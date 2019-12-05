package id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.ListPerubahanKelasFragment;

public class TambahPerubahanFragment extends Fragment {

    private EditText petak_id, tahun, luas, jenis_tanaman, kelas_tanaman, luas_perkiraan,
            jenis_perkiraan, kelas_perkiraan, no_bappkh, luas_definitif, jenis_definitif,
            kelas_definitif, keterangan;


    private TambahPerubahanViewModel mViewModel;

    private static SQLiteHandler db;
    private Spinner spin_anak_petak;
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
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
                petak_id.setText(id_petak);

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
        kelas_tanaman = root.findViewById(R.id.perubahankls_kelasperubahan);
        luas_perkiraan = root.findViewById(R.id.perubahankls_luasperkiraan);
        jenis_perkiraan = root.findViewById(R.id.perubahankls_jenisperkiraan);
        kelas_perkiraan = root.findViewById(R.id.perubahankls_kelasperkiraan);
        no_bappkh = root.findViewById(R.id.perubahankls_nobappkh);
        luas_definitif = root.findViewById(R.id.perubahankls_luasdefinitif);
        jenis_definitif = root.findViewById(R.id.perubahankls_jenisdefinitif);
        kelas_definitif = root.findViewById(R.id.perubahankls_kelasdefinitif);
        keterangan = root.findViewById(R.id.perubahankls_ketperubahan);
        btnSimpanPerubahan = root.findViewById(R.id.perubahankls_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        petak_id.setText(id_petak);

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
            final String str_tanggal = tahun.getText().toString();
            if (jenistanaman.equals("") || jenistanaman.equals("0") || jenistanaman.equals(" ") || jenistanaman.equals(null)) {
                AjnClass.showAlert(getActivity(), "Judul Kejadian tidak boleh kosong");

            } else if (str_tanggal.equals("") || str_tanggal.equals("0") || str_tanggal.equals(" ") || str_tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal tidak boleh kosong");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(jenistanaman)
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
                                        .setContentText(jenistanaman)
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
                                            values_aktifitas.put(TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN, petak_id.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.TAHUN_PERUBAHAN, tahun.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_PERUBAHAN, luas.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN, jenis_tanaman.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN, kelas_tanaman.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_PERKIRAAN, luas_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN, jenis_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN, kelas_perkiraan.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.NO_BAPPKH, no_bappkh.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.LUAS_DEFINITIF, luas_definitif.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF, jenis_definitif.getText().toString());
                                            values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF, kelas_definitif.getText().toString());
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

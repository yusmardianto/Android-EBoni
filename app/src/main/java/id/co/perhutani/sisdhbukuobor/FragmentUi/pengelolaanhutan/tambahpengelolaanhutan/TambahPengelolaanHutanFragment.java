package id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.tambahpengelolaanhutan;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;

public class TambahPengelolaanHutanFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText jenispekerjaan, subpekerjaan, tanggal, anakpetak, rencana, realisasi, status, keterangan;
    private Spinner spin_jenispekerjaan, spin_subpekerjaan, spin_anak_petak, spin_status;
    private String str_tgl;
    private Button btnSimpanPengelolaan;

    final Calendar calendar = Calendar.getInstance();

    public static TambahPengelolaanHutanFragment newInstance() {
        return new TambahPengelolaanHutanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tambah_pengelolaanhutan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        jenispekerjaan = root.findViewById(R.id.pengelolaanhutan_jenispekerjaan);
        subpekerjaan = root.findViewById(R.id.pengelolaanhutan_subpekerjaan);
        tanggal = root.findViewById(R.id.pengelolaanhutan_tanggal);
        anakpetak = root.findViewById(R.id.pengelolaanhutan_anakpetak);
        rencana = root.findViewById(R.id.pengelolaanhutan_rencana);
        realisasi = root.findViewById(R.id.pengelolaanhutan_realisasi);
        status = root.findViewById(R.id.pengelolaanhutan_status);
        keterangan = root.findViewById(R.id.pengelolaanhutan_keterangan);
        btnSimpanPengelolaan = root.findViewById(R.id.pengelolaanhutan_btnsubmit);

        SimpleDateFormat sdf_tgl_a = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tgl_a.format(new Date());
        tanggal.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date = new android.app.DatePickerDialog.OnDateSetListener() {

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
                new DatePickerDialog(getActivity(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSimpanPengelolaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    public void act_simpan() {
        try {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Yakin simpan?")
                    .setCancelText("Tidak")
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
                            sDialog.setTitleText("Berhasil simpan")
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
                                        values_aktifitas.put(TrnPengelolaanHutan.JENIS_KEGIATAN_ID, jenispekerjaan.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.SUB_KEGIATAN_ID, subpekerjaan.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.TANGGAL, tanggal.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.LOKASI_KEGIATAN, anakpetak.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.RENCANA, rencana.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.REALISASI, realisasi.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.STATUS, status.getText().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.KETERANGAN, keterangan.getText().toString());
//                                        values_aktifitas.put(TrnPengelolaanHutan.KET1, spin_jenispekerjaan.getSelectedItem().toString());
//                                        values_aktifitas.put(TrnPengelolaanHutan.KET2, spin_subpekerjaan.getSelectedItem().toString());
//                                        values_aktifitas.put(TrnPengelolaanHutan.KET3, spin_anak_petak.getSelectedItem().toString());
                                        values_aktifitas.put(TrnPengelolaanHutan.KET9, "0");
                                        db.create(TrnPengelolaanHutan.TABLE_NAME, values_aktifitas);
                                        Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                                          // Move to fragment pengelolaan hutan
                                        FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                        Fragment fragment = new ListPengelolaanHutanFragment();
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
//            sendMessage(e.getMessage());
        }
    }
}
package id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.tambahpengelolaanhutan;

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
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstSubPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;

public class TambahPengelolaanHutanFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText jenispekerjaan, subpekerjaan, tanggal, anakpetak, rencana, realisasi, keterangan;
    private Spinner spin_jenispekerjaan, spin_subpekerjaan, spin_anak_petak, spin_status;
    private String str_tgl;
    private Button btnSimpanPengelolaan;

    final Calendar calendar = Calendar.getInstance();

    public static TambahPengelolaanHutanFragment newInstance() {
        return new TambahPengelolaanHutanFragment();
    }

    public void load_spinner_jenis_pekerjaan() {
        List<String> listtpg = db.getPekerjaan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenispekerjaan.setAdapter(dataAdapter_tpg);
        spin_jenispekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_pekerjaan = spin_jenispekerjaan.getSelectedItem().toString();
                String pekerjaan = db.getDataDetail(MstPekerjaan.TABLE_NAME,
                        MstPekerjaan.PEKERJAAN_NAME, pil_pekerjaan, MstPekerjaan.PEKERJAAN_ID);
                jenispekerjaan.setText(pekerjaan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_sub_pekerjaan() {
        List<String> listtpg = db.getSubPekerjaan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_subpekerjaan.setAdapter(dataAdapter_tpg);
        spin_subpekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_sub = spin_subpekerjaan.getSelectedItem().toString();
                String sub_pekerjaan = db.getDataDetail(MstSubPekerjaan.TABLE_NAME,
                        MstSubPekerjaan.SUB_PEKERJAAN_NAME, pil_sub, MstSubPekerjaan.SUB_PEKERJAAN_ID);
                subpekerjaan.setText(sub_pekerjaan);
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
                anakpetak.setText(id_petak);

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

        View root = inflater.inflate(R.layout.tambah_pengelolaanhutan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        jenispekerjaan = root.findViewById(R.id.pengelolaanhutan_jenispekerjaan);
        subpekerjaan = root.findViewById(R.id.pengelolaanhutan_subpekerjaan);
        tanggal = root.findViewById(R.id.pengelolaanhutan_tanggal);
        anakpetak = root.findViewById(R.id.pengelolaanhutan_anakpetak);
        rencana = root.findViewById(R.id.pengelolaanhutan_rencana);
        realisasi = root.findViewById(R.id.pengelolaanhutan_realisasi);
        spin_status =root.findViewById(R.id.spinner_statuspengelolaanhutan);
        keterangan = root.findViewById(R.id.pengelolaanhutan_keterangan);
        btnSimpanPengelolaan = root.findViewById(R.id.pengelolaanhutan_btnsubmit);

        spin_jenispekerjaan = root.findViewById(R.id.spinner_jenispekerjaan);
        load_spinner_jenis_pekerjaan();
        String pil_pekerjaan = spin_jenispekerjaan.getSelectedItem().toString();
        String pekerjaan = db.getDataDetail(MstPekerjaan.TABLE_NAME, MstPekerjaan.PEKERJAAN_NAME, pil_pekerjaan, MstPekerjaan.PEKERJAAN_NAME);
        jenispekerjaan.setText(pekerjaan);

        spin_subpekerjaan = root.findViewById(R.id.spinner_subpekerjaan);
        load_spinner_sub_pekerjaan();
        String pil_sub = spin_subpekerjaan.getSelectedItem().toString();
        String sub_pekerjaan = db.getDataDetail(MstSubPekerjaan.TABLE_NAME, MstSubPekerjaan.SUB_PEKERJAAN_NAME, pil_sub, MstSubPekerjaan.SUB_PEKERJAAN_NAME);
        subpekerjaan.setText(sub_pekerjaan);

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

        spin_anak_petak = root.findViewById(R.id.spinner_anakpetak_pengelolaanhutan);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);

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

            final String str_jenispekerjaan = jenispekerjaan.getText().toString();
            final String str_sub_pekerjaan = subpekerjaan.getText().toString();
            final String str_tgl = tanggal.getText().toString();
            final String str_anakpetak = anakpetak.getText().toString();
            final String str_rencana = rencana.getText().toString();
            final String str_realisasi = realisasi.getText().toString();
            final String str_status = spin_status.getSelectedItem().toString();

            if (str_jenispekerjaan.equals("") || str_jenispekerjaan.equals("0") || str_jenispekerjaan.equals(" ") || str_jenispekerjaan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Pekerjaan harus diisi");

            } else if (str_sub_pekerjaan.equals("") || str_sub_pekerjaan.equals("0") || str_sub_pekerjaan.equals(" ") || str_sub_pekerjaan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Sub Pekerjaan harus diisi");

            } else if (str_tgl.equals("") || str_tgl.equals("0") || str_tgl.equals(" ") || str_tgl.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (str_anakpetak.equals("") || str_anakpetak.equals("0") || str_anakpetak.equals(" ") || str_anakpetak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (str_rencana.equals("") || str_rencana.equals("0") || str_rencana.equals(" ") || str_rencana.equals(null)) {
                AjnClass.showAlert(getActivity(), "Rencana harus diisi");

            } else if (str_realisasi.equals("") || str_realisasi.equals(" ") || str_realisasi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Realisasi harus diisi");

            } else if (str_status.equals("") || str_status.equals("- Pilih Status Pekerjaan -") || str_status.equals(" ") || str_status.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Pekerjaan harus diisi");

            } else {

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
                                            values_aktifitas.put(TrnPengelolaanHutan.STATUS, spin_status.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPengelolaanHutan.KETERANGAN, keterangan.getText().toString());
                                            values_aktifitas.put(TrnPengelolaanHutan.KET1, spin_jenispekerjaan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPengelolaanHutan.KET2, spin_subpekerjaan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPengelolaanHutan.KET3, spin_anak_petak.getSelectedItem().toString());
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
            }

        } catch (Exception e) {
            AjnClass.showAlert(getActivity(), "error " + e.toString());
//            sendMessage(e.getMessage());
        }
    }
}
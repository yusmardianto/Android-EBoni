package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PembuatanBedengSapih;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPembuatanBedengSapih;

public class ListPembuatanBedengSapihFragment extends Fragment {

    View listpembuatanbedeng, popuptambah;

    private AlertDialog.Builder builder;
    private static SQLiteHandler db;
    private EditText tanggal, anakpetak, luas, target, realisasi, keterangan;
    private Spinner spin_anak_petak;

    final Calendar calendar = Calendar.getInstance();
    private static String str_tgl;

    public static ListPembuatanBedengSapihFragment newInstance() {
        return new ListPembuatanBedengSapihFragment();
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        listpembuatanbedeng = inflater.inflate(R.layout.persemaian_pembuatan_bedeng_sapih_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        Toolbar toolbar = listpembuatanbedeng.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PersemaianFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        FloatingActionButton myFab = listpembuatanbedeng.findViewById(R.id.btn_tambahpembuatanbedengsapih);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                popuptambah = factory.inflate(R.layout.tambah_pembuatanbedengsapih_popup, null);

                tanggal = popuptambah.findViewById(R.id.pembuatanbedeng_tanggal);
                SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("yyyy-MM-dd");
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

                anakpetak = popuptambah.findViewById(R.id.pembuatanbedeng_anakpetak);
                spin_anak_petak = popuptambah.findViewById(R.id.spinner_pembuatanbedeng_anakpetak);
                load_spinner_anak_petak();
                String pil_petak = spin_anak_petak.getSelectedItem().toString();
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
                anakpetak.setText(id_petak);

                luas = popuptambah.findViewById(R.id.pembuatanbedeng_luas);
                target = popuptambah.findViewById(R.id.pembuatanbedeng_target);
                realisasi = popuptambah.findViewById(R.id.pembuatanbedeng_realisasi);
                keterangan = popuptambah.findViewById(R.id.pembuatanbedeng_keterangan);

                builder.setView(popuptambah);
                builder.setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    final String str_tgl = tanggal.getText().toString();
                                    final String str_anakpetak = anakpetak.getText().toString();
                                    final String str_luas = luas.getText().toString();
                                    final String str_target = target.getText().toString();
                                    final String str_realisasi = realisasi.getText().toString();

                                    if (str_tgl.equals("") || str_tgl.equals("0") || str_tgl.equals(" ") || str_tgl.equals(null)) {
                                        AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

                                    } else if (str_anakpetak.equals("") || str_anakpetak.equals("0") || str_anakpetak.equals(" ") || str_anakpetak.equals(null)) {
                                        AjnClass.showAlert(getActivity(), "Anak petak harus diisi");

                                    } else if (str_luas.equals("") || str_luas.equals("0") || str_luas.equals(" ") || str_luas.equals(null)) {
                                        AjnClass.showAlert(getActivity(), "Luas harus diisi");

                                    } else if (str_target.equals("") || str_target.equals("0") || str_target.equals(" ") || str_target.equals(null)) {
                                        AjnClass.showAlert(getActivity(), "Target harus diisi");

                                    } else if (str_realisasi.equals("") || str_realisasi.equals("0") || str_realisasi.equals(" ") || str_realisasi.equals(null)) {
                                        AjnClass.showAlert(getActivity(), "Realisasi harus diisi");

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
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.TANGGAL, tanggal.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.ANAK_PETAK, anakpetak.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.LUAS, luas.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.TARGET, target.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.REALISASI, realisasi.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.KETERANGAN, keterangan.getText().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.KET1, spin_anak_petak.getSelectedItem().toString());
                                                                    values_aktifitas.put(TrnPembuatanBedengSapih.KET9, "0");
                                                                    db.create(TrnPembuatanBedengSapih.TABLE_NAME, values_aktifitas);
                                                                    Toast.makeText(getActivity(), "Data berhasil ditambah! ", Toast.LENGTH_SHORT).show();

                                                                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                                                    Fragment fragment = new ListPembuatanBedengSapihFragment();
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
                                }
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        return listpembuatanbedeng;
    }
}
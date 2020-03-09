package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPasangBatasPersemaian;

public class PasangBatasPersemaianFragment extends Fragment {

    private AlertDialog.Builder builder;
    private static SQLiteHandler db;
    private EditText patok_batas, tanggal, luas, target, realisasi, keterangan;
    private Button btnSimpanPersiapanPasangBatas;
    private String str_tgl_pasang_batas;
    final Calendar calendar_pasang = Calendar.getInstance();
//    private static String id, Patok, Tanggal, Luas, Target, Realisasi, Keterangan;
    View viewpasang, viewpopup;

    public static PasangBatasPersemaianFragment newInstance() { return new PasangBatasPersemaianFragment(); }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        viewpasang = inflater.inflate(R.layout.persemaian_pasang_batas_persemaian,container,false);

        Toolbar toolbar = viewpasang.findViewById(R.id.toolbar_data_pasang);
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

        FloatingActionButton myFab = viewpasang.findViewById(R.id.btn_tambah_pasang_persemaian);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                viewpopup = factory.inflate(R.layout.tambah_pasang_batas_persemaian, null);

                builder.setView(viewpopup);
                builder.setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                db = new SQLiteHandler(getActivity());
                                patok_batas = viewpopup.findViewById(R.id.pasang_batas_patok_batas);
                                tanggal = viewpopup.findViewById(R.id.pasang_batas_tanggal);
//                                SimpleDateFormat sdf_tgl_a = new SimpleDateFormat("dd-MM-yyyy");
//                                str_tgl_pasang_batas = sdf_tgl_a.format(new Date());
//                                tanggal.setFocusable(false);
//                                final DatePickerDialog.OnDateSetListener date2 = new android.app.DatePickerDialog.OnDateSetListener() {
//
//                                    @Override
//                                    public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                                          int dayOfMonth) {
//                                        // TODO Auto-generated method stub
//                                        calendar_pasang.set(Calendar.YEAR, year);
//                                        calendar_pasang.set(Calendar.MONTH, monthOfYear);
//                                        calendar_pasang.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                                        SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
//                                        str_tgl_pasang_batas = sdf_view.format(calendar_pasang.getTime());
//
//                                        tanggal.setText(str_tgl_pasang_batas);
//                                    }
//
//                                };
//                                tanggal.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        new DatePickerDialog(getActivity(), date2, calendar_pasang.get(Calendar.YEAR), calendar_pasang.get(Calendar.MONTH),
//                                                calendar_pasang.get(Calendar.DAY_OF_MONTH)).show();
//                                    }
//                                });
                                luas = viewpopup.findViewById(R.id.pasang_batas_luas);
                                target = viewpopup.findViewById(R.id.pasang_batas_target);
                                realisasi = viewpopup.findViewById(R.id.pasang_batas_realisasi);
                                keterangan = viewpopup.findViewById(R.id.pasang_batas_keterangan);

                                try {
                                    ContentValues values_aktifitas = new ContentValues();
                                    values_aktifitas.put(TrnPasangBatasPersemaian.PATOK_BATAS, patok_batas.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.TANGGAL, tanggal.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.LUAS, luas.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.TARGET, target.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.REALISASI, realisasi.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.KETERANGAN, keterangan.getText().toString());
                                    values_aktifitas.put(TrnPasangBatasPersemaian.KET9, "0");
                                    db.create(TrnPasangBatasPersemaian.TABLE_NAME, values_aktifitas);
                                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

                                    // Reload current fragment
                                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                    Fragment fragment = new PasangBatasPersemaianFragment();
                                    FragmentTransaction ft = manager.beginTransaction();
                                    ft.replace(R.id.nav_host_fragment, fragment);
                                    ft.commit();
                                }
                                catch (Exception e) {
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

        return viewpasang;
    }
}

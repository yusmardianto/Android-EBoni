package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersiapanSapra;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian.PasangBatasPersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPasangBatasPersemaian;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPersiapanSapra;

public class ListPersiapanSapraFragment extends Fragment {

    private PersiapanSapraViewModel mViewModel;
    private AlertDialog.Builder builder;
    private static SQLiteHandler db;
    private EditText tanggal, anak_petak, sapra, target, realisasi, keterangan;
    private Spinner spin_anak_petak;
    private static String str_tgl;
    final Calendar calendar = Calendar.getInstance();
    View viewsapra, viewpopup;

    public static ListPersiapanSapraFragment newInstance() {
        return new ListPersiapanSapraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewsapra = inflater.inflate(R.layout.persemaian_persiapan_sapra_fragment, container, false);

        Toolbar toolbar = viewsapra.findViewById(R.id.toolbar_persiapan_sapra);
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

        FloatingActionButton myFab = viewsapra.findViewById(R.id.btn_tambahpersiapansapra);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                viewpopup = factory.inflate(R.layout.tambah_persiapan_sapra, null);

                builder.setView(viewpopup);
                builder.setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                db = new SQLiteHandler(getActivity());
                                tanggal = viewpopup.findViewById(R.id.persiapan_sapra_tanggal);
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

//                                anakpetak = popuptambah.findViewById(R.id.persiapanlahan_anakpetak);
//                                spin_anak_petak = popuptambah.findViewById(R.id.spinner_persiapanlahan_anakpetak);
//                                load_spinner_anak_petak();
//                                String pil_petak = spin_anak_petak.getSelectedItem().toString();
//                                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
//                                anakpetak.setText(id_petak);

                                sapra = viewpopup.findViewById(R.id.persiapan_sapra_spinner_jenis_sapra);
                                target = viewpopup.findViewById(R.id.persiapan_sapra_target);
                                realisasi = viewpopup.findViewById(R.id.persiapan_sapra_realisasi);
                                keterangan = viewpopup.findViewById(R.id.persiapan_sapra_keterangan);

                                try {
                                    ContentValues values_aktifitas = new ContentValues();
                                    values_aktifitas.put(TrnPersiapanSapra.TANGGAL, tanggal.getText().toString());
//                                    values_aktifitas.put(TrnPersiapanSapra.ANAK_PETAK, anak_petak.getText().toString());
                                    values_aktifitas.put(TrnPersiapanSapra.SAPRA, sapra.getText().toString());
                                    values_aktifitas.put(TrnPersiapanSapra.TARGET, target.getText().toString());
                                    values_aktifitas.put(TrnPersiapanSapra.REALISASI, realisasi.getText().toString());
                                    values_aktifitas.put(TrnPersiapanSapra.KETERANGAN, keterangan.getText().toString());
                                    values_aktifitas.put(TrnPersiapanSapra.KET9, "0");
                                    db.create(TrnPersiapanSapra.TABLE_NAME, values_aktifitas);
                                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

                                    // Reload current fragment
                                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                    Fragment fragment = new ListPersiapanSapraFragment();
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

        return viewsapra;
    }

//    public void load_spinner_anak_petak() {
//        List<String> listtpg = db.getAnakPetak();
//        final int _tpg = listtpg.size();
//        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_item, listtpg) {
//            @Override
//            public int getCount() {
//                return (_tpg); // Truncate the list
//            }
//        };
//        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin_anak_petak.setAdapter(dataAdapter_tpg);
//        spin_anak_petak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                // your code here
//                String pil_petak = spin_anak_petak.getSelectedItem().toString();
//                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME,
//                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
//                anak_petak.setText(id_petak);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//            }
//        });
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PersiapanSapraViewModel.class);
        // TODO: Use the ViewModel

    }

}

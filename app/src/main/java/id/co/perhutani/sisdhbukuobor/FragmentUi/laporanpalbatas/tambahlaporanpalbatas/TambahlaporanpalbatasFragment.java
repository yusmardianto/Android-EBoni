package id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.tambahlaporanpalbatas;

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
import id.co.perhutani.sisdhbukuobor.Schema.MstBagianHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKondisiPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.ListPelaporanpalFragment;

public class TambahlaporanpalbatasFragment extends Fragment {

    private EditText bagianhutanpal, tanggalpal, jenispal, nopal, kondisipal, jumlahpal, keteranganpal;
    private Button BtnSubmitPal;
    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;
    private Spinner spin_bagianhutan;
    private Spinner spin_jenis_pal;
    private Spinner spin_kondisi;

    private static SQLiteHandler db;

    private TambahlaporanpalbatasViewModel mViewModel;

    public static TambahlaporanpalbatasFragment newInstance() {
        return new TambahlaporanpalbatasFragment();
    }

    public void load_spinner_bagian_hutan_pal() {
        List<String> listtpg = db.getBagianHutan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bagianhutan.setAdapter(dataAdapter_tpg);
        spin_bagianhutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_bh = spin_bagianhutan.getSelectedItem().toString();
                String bagian_hutan = db.getDataDetail(MstBagianHutan.TABLE_NAME, MstBagianHutan.BAGIAN_HUTAN_NAME,
                        pil_bh, MstBagianHutan.BAGIAN_HUTAN_ID);
                bagianhutanpal.setText(bagian_hutan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_jenis_pal() {
        List<String> listtpg = db.getJenisPal();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_pal.setAdapter(dataAdapter_tpg);
        spin_jenis_pal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_pal = spin_jenis_pal.getSelectedItem().toString();
                String jenis_pal = db.getDataDetail(MstJenisPalSchema.TABLE_NAME, MstJenisPalSchema.JENIS_PAL_NAME,
                        pil_pal, MstJenisPalSchema.JENIS_PAL_ID);
                jenispal.setText(jenis_pal);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_kondisi_pal() {
        List<String> listtpg = db.getKondisiPal();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kondisi.setAdapter(dataAdapter_tpg);
        spin_kondisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kondisi = spin_kondisi.getSelectedItem().toString();
                String kondisi_pal = db.getDataDetail(MstKondisiPalSchema.TABLE_NAME, MstKondisiPalSchema.KONDISI_PAL_NAME,
                        pil_kondisi, MstKondisiPalSchema.KONDISI_PAL_ID);
                kondisipal.setText(kondisi_pal);

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
//        return in flater.inflate(R.layout.tambah_laporanpalbatas_fragment, container, false);

        View root = inflater.inflate(R.layout.tambah_laporanpalbatas_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        bagianhutanpal = root.findViewById(R.id.palbatas_bagianhutan);
        tanggalpal = root.findViewById(R.id.palbatas_tanggal);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tglmulai.format(new Date());
        tanggalpal.setFocusable(false);
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

                tanggalpal.setText(str_tgl);
            }

        };
        tanggalpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jenispal = root.findViewById(R.id.palbatas_jenispal);
        nopal = root.findViewById(R.id.palbatas_nopal);
        kondisipal = root.findViewById(R.id.palbatas_kondisipal);
        jumlahpal = root.findViewById(R.id.palbatas_jumlahpal);
        keteranganpal = root.findViewById(R.id.palbatas_ketpal);
        BtnSubmitPal = root.findViewById(R.id.palbatas_btnsubmitpal);

        spin_bagianhutan = root.findViewById(R.id.spinner_bagian_hutan_pal);
        load_spinner_bagian_hutan_pal();
        String pil_bh = spin_bagianhutan.getSelectedItem().toString();
        String bagian_hutan = db.getDataDetail(MstBagianHutan.TABLE_NAME, MstBagianHutan.BAGIAN_HUTAN_NAME, pil_bh, MstBagianHutan.BAGIAN_HUTAN_ID);
        bagianhutanpal.setText(bagian_hutan);

        spin_jenis_pal = root.findViewById(R.id.spinner_jenis_pal);
        load_spinner_jenis_pal();
        String pil_pal = spin_jenis_pal.getSelectedItem().toString();
        String jenis_pal = db.getDataDetail(MstJenisPalSchema.TABLE_NAME, MstJenisPalSchema.JENIS_PAL_NAME, pil_pal, MstJenisPalSchema.JENIS_PAL_ID);
        jenispal.setText(jenis_pal);

        spin_kondisi = root.findViewById(R.id.spinner_kondisi);
        load_spinner_kondisi_pal();
        String pil_kondisi = spin_kondisi.getSelectedItem().toString();
        String kondisi_pal = db.getDataDetail(MstKondisiPalSchema.TABLE_NAME, MstKondisiPalSchema.KONDISI_PAL_NAME, pil_kondisi, MstKondisiPalSchema.KONDISI_PAL_ID);
        kondisipal.setText(kondisi_pal);

        BtnSubmitPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });
        return root;
    }

    public void act_simpan() {
        try {

            final String bagianhutan = bagianhutanpal.getText().toString();
            final String tanggal = tanggalpal.getText().toString();
            final String nomor = nopal.getText().toString();
            final String jenis = jenispal.getText().toString();
            final String kondisi = kondisipal.getText().toString();
            final String jumlah = jumlahpal.getText().toString();

            if (bagianhutan.equals("") || bagianhutan.equals("0") || bagianhutan.equals(" ") || bagianhutan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Bagian Hutan harus diisi");

            } else if (tanggal.equals("") || tanggal.equals("0") || tanggal.equals(" ") || tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (nomor.equals("") || nomor.equals("0") || nomor.equals(" ") || nomor.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nomor PAL harus diisi");

            } else if (jenis.equals("") || jenis.equals("0") || jenis.equals(" ") || jenis.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis PAL harus diisi");

            } else if (kondisi.equals("") || kondisi.equals("0") || kondisi.equals(" ") || kondisi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kondisi PAL harus diisi");

            } else if (jumlah.equals("") || jumlah.equals("0") || jumlah.equals(" ") || jumlah.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah harus diisi");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(jenis)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText(jenis)
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
                                        .setContentText(jenis)
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
                                            values_aktifitas.put(TrnLaporanPalBatas.BAGIAN_HUTAN, bagianhutanpal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.TANGGAL_PAL, tanggalpal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.NO_PAL, nopal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.JENIS_PAL, jenispal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KONDISI_PAL, kondisipal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.JUMLAH_PAL, jumlahpal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KET1, spin_bagianhutan.getSelectedItem().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KET2, spin_jenis_pal.getSelectedItem().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KET3, spin_kondisi.getSelectedItem().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KETERANGAN_PAL, keteranganpal.getText().toString());
                                            values_aktifitas.put(TrnLaporanPalBatas.KET9, "0");
                                            db.create(TrnLaporanPalBatas.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment laporan pal batas
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListPelaporanpalFragment();
                                            FragmentTransaction ft = manager.beginTransaction();
                                            ft.replace(R.id.nav_host_fragment, fragment);
                                            ft.commit();
                                        }catch (Exception e) {
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
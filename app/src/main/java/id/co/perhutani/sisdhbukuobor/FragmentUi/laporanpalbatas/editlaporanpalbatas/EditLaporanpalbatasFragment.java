package id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.editlaporanpalbatas;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.ListPelaporanpalFragment;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstBagianHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKondisiPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;

public class EditLaporanpalbatasFragment extends Fragment {

    private EditText bagianhutanpal, tanggalpal, jenispal, kondisipal, nopal, jumlahpal, keteranganpal;
    private Spinner spin_bagianhutan;
    private Spinner spin_jenis_pal;
    private Spinner spin_kondisi;


    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_bagianhutan, str_tanggalpal, str_jenispal, str_kondisipal, str_nopal,
            str_jumlahpal, str_keteranganpal;
    private Button btnSimpanLaporan;

    private EditLaporanpalbatasViewModel mViewModel;

    public static EditLaporanpalbatasFragment newInstance() {
        return new EditLaporanpalbatasFragment();
    }

    private static String str_spin_bagianhutan, str_spin_jenispal, str_spin_kondisi;

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
                        pil_bh, MstBagianHutan.BAGIAN_HUTAN_KODE);
                bagianhutanpal.setText(bagian_hutan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_bagianhutan = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bagianhutan.setAdapter(adapter);
        if (str_spin_bagianhutan != null) {
            int spinnerPosition = adapter.getPosition(str_spin_bagianhutan);
            spin_bagianhutan.setSelection(spinnerPosition);
        }
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

        str_spin_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_pal.setAdapter(adapter);
        if (str_spin_jenispal != null) {
            int spinnerPosition = adapter.getPosition(str_spin_jenispal);
            spin_jenis_pal.setSelection(spinnerPosition);
        }
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

        str_spin_kondisi = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kondisi.setAdapter(adapter);
        if (str_spin_kondisi != null) {
            int spinnerPosition = adapter.getPosition(str_spin_kondisi);
            spin_kondisi.setSelection(spinnerPosition);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_laporanpalbatas_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_laporanpalbatas_fragment, container, false);

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

        bagianhutanpal = root.findViewById(R.id.edit_palbatas_bagianhutan);
        tanggalpal = root.findViewById(R.id.edit_palbatas_tanggal);
        jenispal = root.findViewById(R.id.edit_palbatas_jenispal);
        kondisipal = root.findViewById(R.id.edit_palbatas_kondisipal);
        nopal = root.findViewById(R.id.edit_palbatas_nopal);
        jumlahpal = root.findViewById(R.id.edit_palbatas_jumlahpal);
        keteranganpal = root.findViewById(R.id.edit_palbatas_ketpal);
        btnSimpanLaporan = root.findViewById(R.id.edit_palbatas_btnsimpanpal);

        spin_bagianhutan = root.findViewById(R.id.edit_spinner_bagian_hutan_pal);
        load_spinner_bagian_hutan_pal();
        String pil_bh = spin_bagianhutan.getSelectedItem().toString();
        String bagian_hutan = db.getDataDetail(MstBagianHutan.TABLE_NAME, MstBagianHutan.BAGIAN_HUTAN_NAME, pil_bh, MstBagianHutan.BAGIAN_HUTAN_ID);
        bagianhutanpal.setText(bagian_hutan);

        spin_jenis_pal = root.findViewById(R.id.edit_spinner_jenis_pal);
        load_spinner_jenis_pal();
        String pil_pal = spin_jenis_pal.getSelectedItem().toString();
        String jenis_pal = db.getDataDetail(MstJenisPalSchema.TABLE_NAME, MstJenisPalSchema.JENIS_PAL_NAME, pil_pal , MstJenisPalSchema.JENIS_PAL_ID);
        jenispal.setText(jenis_pal);

        spin_kondisi = root.findViewById(R.id.edit_spinner_kondisi);
        load_spinner_kondisi_pal();
        String pil_kondisi = spin_kondisi.getSelectedItem().toString();
        String kondisi_pal = db.getDataDetail(MstKondisiPalSchema.TABLE_NAME, MstKondisiPalSchema.KONDISI_PAL_NAME, pil_kondisi, MstKondisiPalSchema.KONDISI_PAL_ID);
        kondisipal.setText(kondisi_pal);

        str_bagianhutan = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET1);
        str_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
        str_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET2);
        str_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET3);
        str_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
        str_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
        str_keteranganpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);

        bagianhutanpal.setText(str_bagianhutan);
        tanggalpal.setText(str_tanggalpal);
        jenispal.setText(str_jenispal);
        kondisipal.setText(str_kondisipal);
        nopal.setText(str_nopal);
        jumlahpal.setText(str_jumlahpal);
        keteranganpal.setText(str_keteranganpal);

        btnSimpanLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        Toolbar toolbar = root.findViewById(R.id.toolbar_editpal);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPelaporanpalFragment();
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
        mViewModel = ViewModelProviders.of(this).get(EditLaporanpalbatasViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String bagianhutan = bagianhutanpal.getText().toString();
            final String nomor = nopal.getText().toString();
            final String jenis = jenispal.getText().toString();
            final String kondisi = kondisipal.getText().toString();
            final String tanggal = tanggalpal.getText().toString();
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
                                        try {
                                            PelaporanpalbatasModel Aktifitasnya = new PelaporanpalbatasModel();
                                            Aktifitasnya.setID_Laporan(Integer.parseInt(id));
                                            Aktifitasnya.setBagianHutanPal(bagianhutanpal.getText().toString());
                                            Aktifitasnya.setKet1(spin_bagianhutan.getSelectedItem().toString());
                                            Aktifitasnya.setTanggalPal(tanggalpal.getText().toString());
                                            Aktifitasnya.setNomerPal(nopal.getText().toString());
                                            Aktifitasnya.setJenisPal(jenispal.getText().toString());
                                            Aktifitasnya.setKet2(spin_jenis_pal.getSelectedItem().toString());
                                            Aktifitasnya.setKondisiPal(kondisipal.getText().toString());
                                            Aktifitasnya.setKet3(spin_kondisi.getSelectedItem().toString());
                                            Aktifitasnya.setJumlahPal(jumlahpal.getText().toString());
                                            Aktifitasnya.setKeteranganPal(keteranganpal.getText().toString());
                                            Aktifitasnya.setKet9("2");
                                            db.EditDataLaporanPalBatas(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListPelaporanpalFragment();
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

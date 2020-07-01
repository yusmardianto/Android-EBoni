package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.editinteraksimdh;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.ListInteraksiMDHFragment;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstBentukInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaByRph;
import id.co.perhutani.sisdhbukuobor.Schema.MstStatusInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;

public class EditInteraksimdhFragment extends Fragment {

    private EditText anakpetak, namadesa, tahun, bentukinteraksi, status, keterangan;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_anakpetak, str_tahun, str_namadesa, str_bentukinteraksi, str_status,
            str_keterangan, str_petak;
    private Spinner spin_anak_petak;
    private Spinner spin_nama_desa;
    private Spinner spin_bentuk_interaksi;
    private Spinner spin_status;

    private Button btnSimpanInteraksi;

    private EditInteraksimdhViewModel mViewModel;

    public static EditInteraksimdhFragment newInstance() {
        return new EditInteraksimdhFragment();
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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
                anakpetak.setText(id_petak);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_nama_desa() {
        List<String> listtpg = db.getNamaDesa();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_nama_desa.setAdapter(dataAdapter_tpg);
        spin_nama_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_desa = spin_nama_desa.getSelectedItem().toString();
                String id_desa = db.getDataDetail(MstDesaByRph.TABLE_NAME, MstDesaByRph.DESA_NAME, pil_desa, MstDesaByRph.DESA_ID);
                namadesa.setText(id_desa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_bentuk_interaksi() {
        List<String> listtpg = db.getBentukInteraksi();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bentuk_interaksi.setAdapter(dataAdapter_tpg);
        spin_bentuk_interaksi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_interaksi = spin_bentuk_interaksi.getSelectedItem().toString();
                String id_interaksi = db.getDataDetail(MstBentukInteraksiSchema.TABLE_NAME, MstBentukInteraksiSchema.NAMA_INTERAKSI, pil_interaksi, MstBentukInteraksiSchema.ID_INTERAKSI);
                bentukinteraksi.setText(id_interaksi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_status_interaksi() {
        List<String> listtpg = db.getStatusInteraksi();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_status.setAdapter(dataAdapter_tpg);
        spin_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_status = spin_status.getSelectedItem().toString();
                String id_status = db.getDataDetail(MstStatusInteraksiSchema.TABLE_NAME, MstStatusInteraksiSchema.NAMA_STATUS, pil_status, MstStatusInteraksiSchema.ID_STATUS);
                status.setText(id_status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.edit_interaksimdh_fragment, container, false);

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

        anakpetak = root.findViewById(R.id.edit_interaksimdh_idpetak);

        tahun = root.findViewById(R.id.edit_interaksimdh_tahun);
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

        namadesa = root.findViewById(R.id.edit_interaksimdh_namadesa);
        bentukinteraksi = root.findViewById(R.id.edit_interaksimdh_bentukinteraksi);
        status = root.findViewById(R.id.edit_interaksimdh_statusinteraksi);
        keterangan = root.findViewById(R.id.edit_interaksimdh_keterangan);
        btnSimpanInteraksi = root.findViewById(R.id.edit_interaksimdh_btnsimpan);

        spin_anak_petak = root.findViewById(R.id.edit_spinner_anakpetak_interaksi);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
        anakpetak.setText(id_petak);

        spin_nama_desa = root.findViewById(R.id.edit_spinner_namadesa);
        load_spinner_nama_desa();
        String pil_desa = spin_nama_desa.getSelectedItem().toString();
        String id_desa = db.getDataDetail(MstDesaByRph.TABLE_NAME, MstDesaByRph.DESA_NAME, pil_desa, MstDesaByRph.DESA_ID);
        namadesa.setText(id_desa);

        spin_bentuk_interaksi = root.findViewById(R.id.edit_spinner_bentukinteraksi);
        load_spinner_bentuk_interaksi();
        String pil_interaksi = spin_bentuk_interaksi.getSelectedItem().toString();
        String id_interaksi = db.getDataDetail(MstBentukInteraksiSchema.TABLE_NAME, MstBentukInteraksiSchema.NAMA_INTERAKSI, pil_interaksi, MstBentukInteraksiSchema.ID_INTERAKSI);
        bentukinteraksi.setText(id_interaksi);

        spin_status = root.findViewById(R.id.edit_spinner_statusinteraksi);
        load_spinner_status_interaksi();
        String pil_status = spin_status.getSelectedItem().toString();
        String id_status = db.getDataDetail(MstStatusInteraksiSchema.TABLE_NAME, MstStatusInteraksiSchema.NAMA_STATUS, pil_status, MstStatusInteraksiSchema.ID_STATUS);
        status.setText(id_status);

        str_anakpetak = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.ANAK_PETAK_ID);
        str_tahun = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.TAHUN);
        str_namadesa = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.NAMA_DESA);
        str_bentukinteraksi = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.BENTUK_INTERAKSI);
        str_status = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.STATUS);
        str_keterangan = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KETERANGAN);
        str_petak = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.HEXA);

        anakpetak.setText(str_anakpetak);
        tahun.setText(str_tahun);
        namadesa.setText(str_namadesa);
        bentukinteraksi.setText(str_bentukinteraksi);
        status.setText(str_status);
        keterangan.setText(str_keterangan);

        btnSimpanInteraksi.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(EditInteraksimdhViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String anak_petak = anakpetak.getText().toString();
            final String tahun_interaksi = tahun.getText().toString();
            final String nama_desa = namadesa.getText().toString();
            final String bentuk_interaksi = bentukinteraksi.getText().toString();
            final String status_interaksi = status.getText().toString();

            if (anak_petak.equals("") || anak_petak.equals("0") || anak_petak.equals(" ") || anak_petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (tahun_interaksi.equals("") || tahun_interaksi.equals("0") || tahun_interaksi.equals(" ") || tahun_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tahun harus diisi");

            } else if (nama_desa.equals("") || nama_desa.equals("0") || nama_desa.equals(" ") || nama_desa.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nama Desa harus diisi");

            } else if (bentuk_interaksi.equals("") || bentuk_interaksi.equals("0") || bentuk_interaksi.equals(" ") || bentuk_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Bentuk Interaksi harus diisi");

            } else if (status_interaksi.equals("") || status_interaksi.equals("0") || status_interaksi.equals(" ") || status_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Interaksi harus diisi");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(nama_desa)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText(nama_desa)
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
                                        .setContentText(nama_desa)
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
                                        String ambilKata = anakpetak.getText().toString();
                                        String enKata = "";
                                        try {
                                            InteraksimdhModel Aktifitasnya = new InteraksimdhModel();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            Aktifitasnya.setID_IMDH(Integer.parseInt(id));
                                            Aktifitasnya.setAnakpetakid(anakpetak.getText().toString());
                                            Aktifitasnya.setTahun(tahun.getText().toString());
                                            Aktifitasnya.setKet2(tahun.getText().toString());
                                            Aktifitasnya.setDesaid(namadesa.getText().toString());
                                            Aktifitasnya.setBentukinteraksi(bentukinteraksi.getText().toString());
                                            Aktifitasnya.setStatus(status.getText().toString());
                                            Aktifitasnya.setKeterangan(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_nama_desa.getSelectedItem().toString());
                                            Aktifitasnya.setKet4(spin_bentuk_interaksi.getSelectedItem().toString());
                                            Aktifitasnya.setKet5(spin_status.getSelectedItem().toString());
//                                            Aktifitasnya.s(spin_status.getSelectedItem().toString());
                                            Aktifitasnya.setKet11(enKata);
                                            Aktifitasnya.setKet9("2");
                                            db.EditDataInteraksiMDH(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListInteraksiMDHFragment();
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

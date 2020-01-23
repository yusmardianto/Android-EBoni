package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.tambahinteraksimdh;

import android.content.ContentValues;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.ListInteraksiMDHFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstBentukInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStatusInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class TambahInteraksimdhFragment extends Fragment {

    private EditText anakpetak, namadesa, bentukinteraksi, status, keterangan;

    private TambahInteraksimdhViewModel mViewModel;

    private static SQLiteHandler db;
    private Spinner spin_anak_petak;
    private Spinner spin_tahun;
    private Spinner spin_nama_desa;
    private Spinner spin_bentuk_interaksi;
    private Spinner spin_status;

    private Button btnSubmitInteraksi;

    public static TambahInteraksimdhFragment newInstance() {
        return new TambahInteraksimdhFragment();
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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
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
                String id_desa = db.getDataDetail(MstDesaSchema.TABLE_NAME, MstDesaSchema.NAMA_DESA, pil_desa, MstDesaSchema.ID_DESA);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tambah_interaksimdh_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        anakpetak = root.findViewById(R.id.interaksimdh_idpetak);
        namadesa = root.findViewById(R.id.interaksimdh_namadesa);
        bentukinteraksi = root.findViewById(R.id.interaksimdh_bentukinteraksi);
        status = root.findViewById(R.id.interaksimdh_statusinteraksi);
        keterangan = root.findViewById(R.id.interaksimdh_keterangan);
        btnSubmitInteraksi = root.findViewById(R.id.interaksimdh_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_anakpetak_interaksi);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);

        spin_tahun = root.findViewById(R.id.spinner_tahuninteraksi);

        spin_nama_desa = root.findViewById(R.id.spinner_namadesa);
        load_spinner_nama_desa();
        String pil_desa = spin_nama_desa.getSelectedItem().toString();
        String id_desa = db.getDataDetail(MstDesaSchema.TABLE_NAME, MstDesaSchema.NAMA_DESA, pil_desa, MstDesaSchema.ID_DESA);
        namadesa.setText(id_desa);

        spin_bentuk_interaksi = root.findViewById(R.id.spinner_bentukinteraksi);
        load_spinner_bentuk_interaksi();
        String pil_interaksi = spin_bentuk_interaksi.getSelectedItem().toString();
        String id_interaksi = db.getDataDetail(MstBentukInteraksiSchema.TABLE_NAME, MstBentukInteraksiSchema.NAMA_INTERAKSI, pil_interaksi, MstBentukInteraksiSchema.ID_INTERAKSI);
        bentukinteraksi.setText(id_interaksi);

        spin_status = root.findViewById(R.id.spinner_statusinteraksi);
        load_spinner_status_interaksi();
        String pil_status = spin_status.getSelectedItem().toString();
        String id_status = db.getDataDetail(MstStatusInteraksiSchema.TABLE_NAME, MstStatusInteraksiSchema.NAMA_STATUS, pil_status, MstStatusInteraksiSchema.ID_STATUS);
        status.setText(id_status);

        btnSubmitInteraksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    public void act_simpan() {
        try {

            final String anak_petak = anakpetak.getText().toString();
            final String tahun_interaksi = spin_tahun.getSelectedItem().toString();
            final String nama_desa = namadesa.getText().toString();
            final String bentuk_interaksi = bentukinteraksi.getText().toString();
            final String status_interaksi = status.getText().toString();

            if (anak_petak.equals("") || anak_petak.equals("0") || anak_petak.equals(" ") || anak_petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Petak Kejadian tidak boleh kosong");

            } else if (tahun_interaksi.equals("") || tahun_interaksi.equals("- Pilih Tahun -") || tahun_interaksi.equals(" ") || tahun_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tahun tidak boleh kosong");

            } else if (nama_desa.equals("") || nama_desa.equals("0") || nama_desa.equals(" ") || nama_desa.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nama Desa tidak boleh kosong");

            } else if (bentuk_interaksi.equals("") || bentuk_interaksi.equals("0") || bentuk_interaksi.equals(" ") || bentuk_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Bentuk Interaksi tidak boleh kosong");

            } else if (status_interaksi.equals("") || status_interaksi.equals("0") || status_interaksi.equals(" ") || status_interaksi.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Interaksi tidak boleh kosong");

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
                                        try {

                                            ContentValues values_aktifitas = new ContentValues();
                                            values_aktifitas.put(TrnInteraksimdh.ANAK_PETAK_ID, anakpetak.getText().toString());
                                            values_aktifitas.put(TrnInteraksimdh.TAHUN, spin_tahun.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.NAMA_DESA, namadesa.getText().toString());
                                            values_aktifitas.put(TrnInteraksimdh.BENTUK_INTERAKSI, bentukinteraksi.getText().toString());
                                            values_aktifitas.put(TrnInteraksimdh.STATUS, status.getText().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KET1, spin_anak_petak.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KET2, spin_tahun.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KET3, spin_nama_desa.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KET4, spin_bentuk_interaksi.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KET5, spin_status.getSelectedItem().toString());
                                            values_aktifitas.put(TrnInteraksimdh.KETERANGAN, keterangan.getText().toString());
                                            values_aktifitas.put(TrnInteraksimdh.CREATED_BY, db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_ID));
                                            values_aktifitas.put(TrnInteraksimdh.KET9, "0");
                                            db.create(TrnInteraksimdh.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment interaksi mdh
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

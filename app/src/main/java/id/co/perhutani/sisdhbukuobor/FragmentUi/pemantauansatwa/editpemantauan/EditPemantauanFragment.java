package id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.editpemantauan;

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
import androidx.appcompat.widget.Toolbar;
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
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstWaktuLihatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;

public class EditPemantauanFragment extends Fragment {

    private EditText anakpetak, jenissatwa, jumlahsatwa, caralihat, waktulihat, tanggal, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_anakpetak, str_jenissatwa, str_jumlahsatwa, str_waktulihat,
            str_caralihat, str_tanggal, str_keterangan, str_petak;
    private Button btnSimpanPemantauan;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_satwa;
    private Spinner spin_cara_melihat;
    private Spinner spin_waktu_lihat;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;


    private EditPemantauanViewModel mViewModel;

    public static EditPemantauanFragment newInstance() {
        return new EditPemantauanFragment();
    }

    private static String str_spin_anakpetak, str_spin_jenissatwa, str_spin_caramelihat,
            str_spin_waktulihat;

    public void load_spinner_jenis_satwa() {
        List<String> listtpg = db.getJenisSatwa();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_satwa.setAdapter(dataAdapter_tpg);
        spin_jenis_satwa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_satwa = spin_jenis_satwa.getSelectedItem().toString();
                String id_jenissatwa = db.getDataDetail(MstJenisSatwa.TABLE_NAME,
                        MstJenisSatwa.JENIS_SATWA_NAME, pil_jenis_satwa, MstJenisSatwa.JENIS_SATWA_ID);
                jenissatwa.setText(id_jenissatwa);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_satwa.setAdapter(adapter);
        if (str_spin_jenissatwa != null) {
            int spinnerPosition = adapter.getPosition(str_spin_jenissatwa);
            spin_jenis_satwa.setSelection(spinnerPosition);
        }
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

        str_spin_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(adapter);
        if (str_spin_anakpetak != null) {
            int spinnerPosition = adapter.getPosition(str_spin_anakpetak);
            spin_anak_petak.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_waktu_lihat() {
        List<String> listtpg = db.getWaktuLihat();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_waktu_lihat.setAdapter(dataAdapter_tpg);
        spin_waktu_lihat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_waktu_lihat = spin_waktu_lihat.getSelectedItem().toString();
                String id_waktu_lihat = db.getDataDetail(MstWaktuLihatSchema.TABLE_NAME,
                        MstWaktuLihatSchema.WAKTU_LIHAT_NAME, pil_waktu_lihat, MstWaktuLihatSchema.WAKTU_LIHAT_ID);
                waktulihat.setText(id_waktu_lihat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_waktu_lihat.setAdapter(adapter);
        if (str_spin_waktulihat != null) {
            int spinnerPosition = adapter.getPosition(str_spin_waktulihat);
            spin_waktu_lihat.setSelection(spinnerPosition);
        }
    }


    public void load_spinner_cara_melihat() {
        List<String> listtpg = db.getCaraMelihat();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_cara_melihat.setAdapter(dataAdapter_tpg);
        spin_cara_melihat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_cara_melihat = spin_cara_melihat.getSelectedItem().toString();
                String id_caramelihat = db.getDataDetail(MstJenisTemuan.TABLE_NAME,
                        MstJenisTemuan.JENIS_TEMUAN_NAME, pil_cara_melihat, MstJenisTemuan.JENIS_TEMUAN_ID);
                caralihat.setText(id_caramelihat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        str_spin_caramelihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listtpg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_cara_melihat.setAdapter(adapter);
        if (str_spin_caramelihat != null) {
            int spinnerPosition = adapter.getPosition(str_spin_caramelihat);
            spin_cara_melihat.setSelection(spinnerPosition);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_pemantauan_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_pemantauan_fragment, container, false);

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

        anakpetak = root.findViewById(R.id.edit_pemantauan_anakpetak);
        jenissatwa = root.findViewById(R.id.edit_pemantauan_jenissatwa);
        jumlahsatwa = root.findViewById(R.id.edit_pemantauan_jumlahsatwa);
        waktulihat = root.findViewById(R.id.edit_pemantauan_waktulihat);
        caralihat = root.findViewById(R.id.edit_pemantauan_caralihat);
        tanggal = root.findViewById(R.id.edit_pemantauan_tanggal);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
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
        keterangan = root.findViewById(R.id.edit_pemantauan_keterangan);
        btnSimpanPemantauan = root.findViewById(R.id.edit_pemantauan_btnsimpan);

        spin_jenis_satwa = root.findViewById(R.id.edit_spinner_jenissatwa);
        load_spinner_jenis_satwa();

        spin_anak_petak = root.findViewById(R.id.edit_spinner_anak_petak_satwa);
        load_spinner_anak_petak();

        spin_waktu_lihat = root.findViewById(R.id.edit_spinner_waktu_lihat);
        load_spinner_waktu_lihat();

        spin_cara_melihat = root.findViewById(R.id.edit_spinner_caramelihat);
        load_spinner_cara_melihat();

        str_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET2);
        str_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET1);
        str_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
        str_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET3);
        str_caralihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET4);
        str_tanggal = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.TANGGAL_PEMANTAUAN);
        str_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);
        str_petak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.HEXA);

        jenissatwa.setText(str_jenissatwa);
        anakpetak.setText(str_anakpetak);
        jumlahsatwa.setText(str_jumlahsatwa);
        waktulihat.setText(str_waktulihat);
        caralihat.setText(str_caralihat);
        tanggal.setText(str_tanggal);
        keterangan.setText(str_keterangan);

        btnSimpanPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        Toolbar toolbar = root.findViewById(R.id.toolbar_editpemantauan);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPemantauansatwaFragment();
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
        mViewModel = ViewModelProviders.of(this).get(EditPemantauanViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String jenis_satwa = jenissatwa.getText().toString();
            final String anak_petak = anakpetak.getText().toString();
            final String jumlah_satwa = jumlahsatwa.getText().toString();
            final String waktu_lihat = waktulihat.getText().toString();
            final String cara_lihat = caralihat.getText().toString();
            final String tanggal_lihat = tanggal.getText().toString();

            if (jenis_satwa.equals("") || jenis_satwa.equals("0") || jenis_satwa.equals(" ") || jenis_satwa.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Satwa harus diisi");

            } else if (anak_petak.equals("") || anak_petak.equals("0") || anak_petak.equals(" ") || anak_petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (jumlah_satwa.equals("") || jumlah_satwa.equals("0") || jumlah_satwa.equals(" ") || jumlah_satwa.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah Satwa harus diisi");

            } else if (waktu_lihat.equals("") || waktu_lihat.equals("0") || waktu_lihat.equals(" ") || waktu_lihat.equals(null)) {
                AjnClass.showAlert(getActivity(), "Waktu Lihat harus diisi");

            } else if (tanggal_lihat.equals("") || tanggal_lihat.equals("0") || tanggal_lihat.equals(" ") || tanggal_lihat.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (cara_lihat.equals("") || cara_lihat.equals("0") || cara_lihat.equals(" ") || cara_lihat.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Temuan harus diisi");

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

                                        String ambilKunci = "perhutani";
                                        String ambilKata = anakpetak.getText().toString();
                                        String enKata = "";

                                        try {
                                            PemantauansatwaModel Aktifitasnya = new PemantauansatwaModel();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            Aktifitasnya.setID_Pemantauan(Integer.parseInt(id));
                                            Aktifitasnya.setAnakPetakId(anakpetak.getText().toString());
                                            Aktifitasnya.setJenis(jenissatwa.getText().toString());
                                            Aktifitasnya.setJumlah(jumlahsatwa.getText().toString());
                                            Aktifitasnya.setWaktulihat(waktulihat.getText().toString());
                                            Aktifitasnya.setCaralihat(caralihat.getText().toString());
                                            Aktifitasnya.setTanggal(tanggal.getText().toString());
                                            Aktifitasnya.setKeteranganSatwa(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet2(spin_jenis_satwa.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_waktu_lihat.getSelectedItem().toString());
                                            Aktifitasnya.setKet4(spin_cara_melihat.getSelectedItem().toString());
                                            Aktifitasnya.setKet9("2");
                                            Aktifitasnya.setKet11(enKata);
                                            db.EditDataPemantauanSatwa(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListPemantauansatwaFragment();
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

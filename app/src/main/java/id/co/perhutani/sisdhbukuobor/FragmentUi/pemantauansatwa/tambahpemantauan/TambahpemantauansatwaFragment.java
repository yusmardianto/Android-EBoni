package id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.tambahpemantauan;

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
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstWaktuLihatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;

public class TambahpemantauansatwaFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText en_anakpetak, jenissatwa, jumlahsatwa, caralihat, waktulihat, tanggal, keterangan;
    private Button btnSubmitPemantauan;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_satwa;
    private Spinner spin_cara_melihat;
    private Spinner spin_waktu_lihat;

    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    private TambahpemantauansatwaViewModel mViewModel;

    public static TambahpemantauansatwaFragment newInstance() {
        return new TambahpemantauansatwaFragment();
    }

    public void load_spinner_jenis_satwa() {
        List<String> listjenissatwa = db.getJenisSatwa();
        final int _tpg = listjenissatwa.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenissatwa) {
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
                String pil_jenis_tanaman = spin_jenis_satwa.getSelectedItem().toString();
                String id_jenis = db.getDataDetail(MstJenisSatwa.TABLE_NAME,
                        MstJenisSatwa.JENIS_SATWA_NAME, pil_jenis_tanaman, MstJenisSatwa.JENIS_SATWA_ID);
                jenissatwa.setText(id_jenis);

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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
                en_anakpetak.setText(id_petak);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_waktu_lihat() {
        List<String> listwaktulihat = db.getWaktuLihat();
        final int _tpg = listwaktulihat.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listwaktulihat) {
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
    }

    public void load_spinner_cara_melihat() {
        List<String> listcaramelihat = db.getCaraMelihat();
        final int _tpg = listcaramelihat.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listcaramelihat) {
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tambah_pemantauansatwa_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        jenissatwa = root.findViewById(R.id.pemantauan_jenissatwa);
        en_anakpetak = root.findViewById(R.id.pemantauan_anakpetak);
        waktulihat = root.findViewById(R.id.pemantauan_waktulihat);
        jumlahsatwa = root.findViewById(R.id.pemantauan_jumlahsatwa);
        caralihat = root.findViewById(R.id.pemantauan_caralihat);

        tanggal = root.findViewById(R.id.pemantauan_tanggal);
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

        keterangan = root.findViewById(R.id.pemantauan_keterangan);
        btnSubmitPemantauan = root.findViewById(R.id.pemantauan_btnsubmit);

        spin_jenis_satwa = root.findViewById(R.id.spinner_jenis_satwa);
        load_spinner_jenis_satwa();
        String pil_jenis_satwa = spin_jenis_satwa.getSelectedItem().toString();
        String id_jenis = db.getDataDetail(MstJenisSatwa.TABLE_NAME, MstJenisSatwa.JENIS_SATWA_NAME, pil_jenis_satwa, MstJenisSatwa.JENIS_SATWA_ID);
        jenissatwa.setText(id_jenis);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak_satwa);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
        en_anakpetak.setText(id_petak);

        spin_waktu_lihat = root.findViewById(R.id.spinner_waktu_lihat);
        load_spinner_waktu_lihat();
        String pil_waktu_lihat = spin_waktu_lihat.getSelectedItem().toString();
        String id_waktu_lihat = db.getDataDetail(MstWaktuLihatSchema.TABLE_NAME, MstWaktuLihatSchema.WAKTU_LIHAT_NAME, pil_waktu_lihat, MstWaktuLihatSchema.WAKTU_LIHAT_ID);
        waktulihat.setText(id_waktu_lihat);

        spin_cara_melihat = root.findViewById(R.id.spinner_cara_melihat);
        load_spinner_cara_melihat();
        String pil_cara_melihat = spin_cara_melihat.getSelectedItem().toString();
        String id_caramelihat = db.getDataDetail(MstJenisTemuan.TABLE_NAME, MstJenisTemuan.JENIS_TEMUAN_NAME, pil_cara_melihat, MstJenisTemuan.JENIS_TEMUAN_ID);
        caralihat.setText(id_caramelihat);

        btnSubmitPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    public void act_simpan() {

        try {
            final String jenis_satwa = jenissatwa.getText().toString();
            final String anak_petak = en_anakpetak.getText().toString();
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
                        .setContentText(jenis_satwa)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText(jenis_satwa)
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
                                        .setContentText(jenis_satwa)
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
                                        String ambilKata = en_anakpetak.getText().toString();
                                        String enKata = "";
                                        try {

                                            ContentValues values_aktifitas = new ContentValues();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            values_aktifitas.put(TrnPemantauanSatwa.JENIS_SATWA, jenissatwa.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.ANAK_PETAK_ID, en_anakpetak.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.JUMLAH_SATWA, jumlahsatwa.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.WAKTU_LIHAT, waktulihat.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.CARA_LIHAT, caralihat.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.TANGGAL_PEMANTAUAN, tanggal.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KETERANGAN, keterangan.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KET1, spin_anak_petak.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KET2, spin_jenis_satwa.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KET3, spin_waktu_lihat.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KET4, spin_cara_melihat.getSelectedItem().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KET9, "0");
                                            values_aktifitas.put(TrnPemantauanSatwa.HEXA, enKata);
                                            db.create(TrnPemantauanSatwa.TABLE_NAME, values_aktifitas);
                                            Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment pemantauan satwa
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
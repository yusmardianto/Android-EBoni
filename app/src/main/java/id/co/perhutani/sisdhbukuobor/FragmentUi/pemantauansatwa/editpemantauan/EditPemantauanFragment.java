package id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.editpemantauan;

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
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;

public class EditPemantauanFragment extends Fragment {

    private EditText petak, anakpetak, jenissatwa, jumlahsatwa, waktulihat, caralihat, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_petak, str_anakpetak, str_jenissatwa, str_jumlahsatwa, str_waktulihat,
            str_caralihat, str_keterangan;
    private Button btnSimpanPemantauan;
    private Spinner spin_anak_petak;
    private Spinner spin_jenis_satwa;
    private Spinner spin_cara_melihat;

    private EditPemantauanViewModel mViewModel;

    public static EditPemantauanFragment newInstance() {
        return new EditPemantauanFragment();
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
                String pil_jenis_satwa = spin_jenis_satwa.getSelectedItem().toString();
                String id_jenissatwa = db.getDataDetail(MstJenisSatwa.TABLE_NAME,
                        MstJenisSatwa.JENIS_SATWA_NAME, pil_jenis_satwa, MstJenisSatwa.JENIS_SATWA_NAME);
                jenissatwa.setText(id_jenissatwa);

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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_NAME);
                anakpetak.setText(id_petak);
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
                        MstJenisTemuan.JENIS_TEMUAN_NAME, pil_cara_melihat, MstJenisTemuan.JENIS_TEMUAN_NAME);
                caralihat.setText(id_caramelihat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
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
        keterangan = root.findViewById(R.id.edit_pemantauan_keterangan);
        btnSimpanPemantauan = root.findViewById(R.id.edit_pemantauan_btnsimpan);

        spin_jenis_satwa = root.findViewById(R.id.edit_spinner_jenissatwa);
        load_spinner_jenis_satwa();
        String pil_jenis_satwa = spin_jenis_satwa.getSelectedItem().toString();
        String id_jenis = db.getDataDetail(MstJenisSatwa.TABLE_NAME, MstJenisSatwa.JENIS_SATWA_NAME, pil_jenis_satwa, MstJenisSatwa.JENIS_SATWA_ID);
        jenissatwa.setText(id_jenis);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);

        spin_cara_melihat = root.findViewById(R.id.edit_spinner_caramelihat);
        load_spinner_cara_melihat();
        String pil_cara_melihat = spin_cara_melihat.getSelectedItem().toString();
        String id_caramelihat = db.getDataDetail(MstJenisTemuan.TABLE_NAME, MstJenisTemuan.JENIS_TEMUAN_NAME, pil_cara_melihat, MstJenisTemuan.JENIS_TEMUAN_ID);
        caralihat.setText(id_caramelihat);

        str_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
        str_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
        str_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
        str_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
        str_caralihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
        str_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);

        anakpetak.setText(str_anakpetak);
        jenissatwa.setText(str_jenissatwa);
        jumlahsatwa.setText(str_jumlahsatwa);
        waktulihat.setText(str_waktulihat);
        caralihat.setText(str_caralihat);
        keterangan.setText(str_keterangan);

        btnSimpanPemantauan.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(EditPemantauanViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String jumlah = jumlahsatwa.getText().toString();
            final String waktu = waktulihat.getText().toString();
            if (jumlah.equals("") || jumlah.equals("0") || jumlah.equals(" ") || jumlah.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jumlah tidak boleh kosong");

            } else if(waktu.equals("") || waktu.equals("0") || waktu.equals(" ") || waktu.equals(null)){
                AjnClass.showAlert(getActivity(), "Waktu Lihat tidak boleh kosong");

            }else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(jumlah)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText("")
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
                                        .setContentText(jumlah)
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
                                            PemantauansatwaModel Aktifitasnya = new PemantauansatwaModel();
                                            Aktifitasnya.setID_Pemantauan(Integer.parseInt(id));
                                            Aktifitasnya.setAnakPetakId(anakpetak.getText().toString());
                                            Aktifitasnya.setJenis(jenissatwa.getText().toString());
                                            Aktifitasnya.setJumlah(jumlahsatwa.getText().toString());
                                            Aktifitasnya.setWaktulihat(waktulihat.getText().toString());
                                            Aktifitasnya.setCaralihat(caralihat.getText().toString());
                                            Aktifitasnya.setKeteranganSatwa(keterangan.getText().toString());
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

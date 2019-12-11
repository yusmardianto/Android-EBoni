package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.editidentifikasitenurial;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;

public class EditIdentifikasiTenurialFragment extends Fragment {

    private EditText jenis_permasalahan, tahun, petak, strata, kelas_hutan, luas_baku, luas_tenurial,
                     kondisi_petak, awal_konflik, pihak_terlibat, status_penyelesaian;

    private static String id, str_jenis_permasalahan, str_tahun, str_petak, str_strata, str_kelas_hutan,
                          str_luas_baku, str_luas_tenurial, str_kondisi_petak, str_awal_konflik,
                          str_pihak_terlibat, str_status_penyelesaian;

    private Button btnSimpanTenurial;
    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private EditIdentifikasiTenurialViewModel mViewModel;

    public static EditIdentifikasiTenurialFragment newInstance() {
        return new EditIdentifikasiTenurialFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.edit_identifikasi_tenurial_fragment, container, false);

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

        jenis_permasalahan = root.findViewById(R.id.edit_tenurial_jenispermasalahan);
        tahun = root.findViewById(R.id.edit_tenurial_tahun);
        petak = root.findViewById(R.id.edit_tenurial_anakpetak);
        strata = root.findViewById(R.id.edit_tenurial_strata);
        kelas_hutan = root.findViewById(R.id.edit_tenurial_kelashutan);
        luas_baku = root.findViewById(R.id.edit_tenurial_luasbaku);
        luas_tenurial = root.findViewById(R.id.edit_tenurial_luastenurial);
        kondisi_petak = root.findViewById(R.id.edit_tenurial_kondisipetakidentifikasi);
        awal_konflik = root.findViewById(R.id.edit_tenurial_awalkonflik);
        pihak_terlibat = root.findViewById(R.id.edit_tenurial_pihakterlibat);
        status_penyelesaian = root.findViewById(R.id.edit_tenurial_statuspenyelesaian);
        btnSimpanTenurial = root.findViewById(R.id.edit_tenurial_btnsimpan);

        str_jenis_permasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.JENIS_PERMASALAHAN);
        str_tahun = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.TANGGAL);
        str_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.ANAK_PETAK_ID);
        str_strata = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STRATA);
        str_kelas_hutan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KELAS_HUTAN_ID);
        str_luas_baku = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_BAKU);
        str_luas_tenurial = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_TENURIAL);
        str_kondisi_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KONDISI_PETAK);
        str_awal_konflik = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.AWAL_KONFLIK);
        str_pihak_terlibat = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.PIHAK_TERLIBAT);
        str_status_penyelesaian = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STATUS_PENYELESAIAN);

        jenis_permasalahan.setText(str_jenis_permasalahan);
        tahun.setText(str_tahun);
        petak.setText(str_petak);
        strata.setText(str_strata);
        kelas_hutan.setText(str_kelas_hutan);
        luas_baku.setText(str_luas_baku);
        luas_tenurial.setText(str_luas_tenurial);
        kondisi_petak.setText(str_kondisi_petak);
        awal_konflik.setText(str_awal_konflik);
        pihak_terlibat.setText(str_pihak_terlibat);
        status_penyelesaian.setText(str_status_penyelesaian);

        btnSimpanTenurial.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(EditIdentifikasiTenurialViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String jenis = jenis_permasalahan.getText().toString();
            if (jenis.equals("") || jenis.equals("0") || jenis.equals(" ") || jenis.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Permasalahan tidak boleh kosong");

            } else if(jenis.equals("") || jenis.equals("0") || jenis.equals(" ") || jenis.equals(null)){
                AjnClass.showAlert(getActivity(), "Jenis Permasalahan tidak boleh kosong");

            }else {

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
                                            IdentifikasiTenurialModel Aktifitasnya = new IdentifikasiTenurialModel();
                                            Aktifitasnya.setID_Tenurial(Integer.parseInt(id));
                                            Aktifitasnya.setJenisPermasalahan(jenis_permasalahan.getText().toString());
                                            Aktifitasnya.setTanggal(tahun.getText().toString());
                                            Aktifitasnya.setAnakPetak(petak.getText().toString());
                                            Aktifitasnya.setStrata(strata.getText().toString());
                                            Aktifitasnya.setKelasHutan(kelas_hutan.getText().toString());
                                            Aktifitasnya.setLuasBaku(luas_baku.getText().toString());
                                            Aktifitasnya.setLuasTenurial(luas_tenurial.getText().toString());
                                            Aktifitasnya.setKondisiPetakSaatIdentifikasi(kondisi_petak.getText().toString());
                                            Aktifitasnya.setAwalKonflik(awal_konflik.getText().toString());
                                            Aktifitasnya.setPihakTerlibat(pihak_terlibat.getText().toString());
                                            Aktifitasnya.setStatusPenyelesaian(status_penyelesaian.getText().toString());

                                            db.EditDataIdentifikasiTenurial(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListIdentifikasiTenurialFragment();
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

package id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.editlaporanpalbatas;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.ListPelaporanpalFragment;
import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.ListPemantauansatwaFragment;

public class EditLaporanpalbatasFragment extends Fragment {

    private EditText tanggalpal, jenispal, kondisipal, nopal, jumlahpal, keteranganpal;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_tanggalpal, str_jenispal, str_kondisipal, str_nopal,
            str_jumlahpal, str_keteranganpal;
    private Button btnSimpanLaporan;

    private EditLaporanpalbatasViewModel mViewModel;

    public static EditLaporanpalbatasFragment newInstance() {
        return new EditLaporanpalbatasFragment();
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

        tanggalpal = root.findViewById(R.id.edit_palbatas_tanggal);
        jenispal = root.findViewById(R.id.edit_palbatas_jenispal);
        kondisipal = root.findViewById(R.id.edit_palbatas_kondisipal);
        nopal = root.findViewById(R.id.edit_palbatas_nopal);
        jumlahpal = root.findViewById(R.id.edit_palbatas_jumlahpal);
        keteranganpal = root.findViewById(R.id.edit_palbatas_ketpal);
        btnSimpanLaporan = root.findViewById(R.id.edit_palbatas_btnsimpanpal);

        str_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
        str_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JENIS_PAL);
        str_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KONDISI_PAL);
        str_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
        str_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
        str_keteranganpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);

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

            final String nomor = nopal.getText().toString();
            final String tanggal = tanggalpal.getText().toString();
            if (nomor.equals("") || nomor.equals("0") || nomor.equals(" ") || nomor.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nomor Pal tidak boleh kosong");

            } else if(tanggal.equals("") || tanggal.equals("0") || tanggal.equals(" ") || tanggal.equals(null)){
                AjnClass.showAlert(getActivity(), "Tanggal tidak boleh kosong");

            }else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(nomor)
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
                                        .setContentText(nomor)
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
                                            Aktifitasnya.setTanggalPal(tanggalpal.getText().toString());
                                            Aktifitasnya.setJenisPal(jenispal.getText().toString());
                                            Aktifitasnya.setKondisiPal(kondisipal.getText().toString());
                                            Aktifitasnya.setNomerPal(nopal.getText().toString());
                                            Aktifitasnya.setJumlahPal(jumlahpal.getText().toString());
                                            Aktifitasnya.setKeteranganPal(keteranganpal.getText().toString());
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

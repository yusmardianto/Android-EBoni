package id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.tambahpemantauan;

import android.content.ContentValues;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.ListPemantauansatwaFragment;

public class TambahpemantauansatwaFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText petak, anakpetak, jenissatwa, jumlahsatwa, waktulihat, caralihat, keterangan;
    private Button btnSubmitPemantauan;

    private TambahpemantauansatwaViewModel mViewModel;

    public static TambahpemantauansatwaFragment newInstance() {
        return new TambahpemantauansatwaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tambah_pemantauansatwa_fragment, container, false);
        View root = inflater.inflate(R.layout.tambah_pemantauansatwa_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        petak = root.findViewById(R.id.pemantauan_petak);
        anakpetak = root.findViewById(R.id.pemantauan_anakpetak);
        jenissatwa = root.findViewById(R.id.pemantauan_jenissatwa);
        jumlahsatwa = root.findViewById(R.id.pemantauan_jumlahsatwa);
        waktulihat = root.findViewById(R.id.pemantauan_waktulihat);
        caralihat = root.findViewById(R.id.pemantauan_caralihat);
        keterangan = root.findViewById(R.id.pemantauan_keterangan);
        btnSubmitPemantauan = root.findViewById(R.id.pemantauan_btnsubmit);

        btnSubmitPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan()
            }
        });

        return root;
    }

    public void act_simpan() {
        try {

            final String jenissatwa = jenissatwa.getText().toString();
            final String str_tanggal = tahun.getText().toString();
            if (jenistanaman.equals("") || jenistanaman.equals("0") || jenistanaman.equals(" ") || jenistanaman.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman tidak boleh kosong");

            } else if (str_tanggal.equals("") || str_tanggal.equals("0") || str_tanggal.equals(" ") || str_tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal tidak boleh kosong");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(jenistanaman)
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
                                        .setContentText(jenistanaman)
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
                                            values_aktifitas.put(TrnPemantauanSatwa.PETAK_ID, petak.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.ANAK_PETAK_ID, anakpetak.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.JENIS_SATWA, jenissatwa.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.JUMLAH_SATWA, jumlahsatwa.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.WAKTU_LIHAT, waktulihat.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.CARA_LIHAT, caralihat.getText().toString());
                                            values_aktifitas.put(TrnPemantauanSatwa.KETERANGAN, keterangan.getText().toString());
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

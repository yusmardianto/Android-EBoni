package id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.editpemantauan;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.ListPemantauansatwaFragment;

public class EditPemantauanFragment extends Fragment {

    private EditText petak, anakpetak, jenissatwa, jumlahsatwa, waktulihat, caralihat, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_petak, str_anakpetak, str_jenissatwa, str_jumlahsatwa, str_waktulihat,
            str_caralihat, str_keterangan;
    private Button btnSimpanPemantauan;

    private EditPemantauanViewModel mViewModel;

    public static EditPemantauanFragment newInstance() {
        return new EditPemantauanFragment();
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

        petak = root.findViewById(R.id.edit_pemantauan_petak);
        anakpetak = root.findViewById(R.id.edit_pemantauan_anakpetak);
        jenissatwa = root.findViewById(R.id.edit_pemantauan_jenissatwa);
        jumlahsatwa = root.findViewById(R.id.edit_pemantauan_jumlahsatwa);
        waktulihat = root.findViewById(R.id.edit_pemantauan_waktulihat);
        caralihat = root.findViewById(R.id.edit_pemantauan_caralihat);
        keterangan = root.findViewById(R.id.edit_pemantauan_keterangan);
        btnSimpanPemantauan = root.findViewById(R.id.edit_pemantauan_btnsimpan);

        str_petak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.PETAK_ID);
        str_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
        str_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
        str_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
        str_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
        str_caralihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
        str_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);

        petak.setText(str_petak);
        anakpetak.setText(str_anakpetak);
        jenissatwa.setText(str_jenissatwa);
        jumlahsatwa.setText(str_jumlahsatwa);
        waktulihat.setText(str_waktulihat);
        caralihat.setText(str_caralihat);
        keterangan.setText(str_keterangan);

        btnSimpanPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment pemantauan
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
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditPemantauanViewModel.class);
        // TODO: Use the ViewModel
    }

}

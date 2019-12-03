package id.co.perhutani.sisdhbukuobor.ui.perubahankelas.editperubahan;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.ListPerubahanKelasFragment;

public class EditPerubahanFragment extends Fragment {

    private EditText petak_id, tahun, luas, jenis_tanaman, kelas_tanaman, luas_perkiraan,
            jenis_perkiraan, kelas_perkiraan, no_bappkh, luas_definitif, jenis_definitif,
            kelas_definitif, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_petak_id, str_tahun, str_luas, str_jenis_tanaman,
            str_kelas_tanaman, str_luas_perkiraan, str_jenis_perkiraan, str_kelas_perkiraan,
            str_no_bappkh, str_luas_definitif, str_jenis_definitif, str_kelas_definitif, str_keterangan;
    private Button btnSimpanPerubahan;

    private EditPerubahanViewModel mViewModel;

    public static EditPerubahanFragment newInstance() {
        return new EditPerubahanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_perubahan_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_perubahan_fragment, container, false);

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

        petak_id = root.findViewById(R.id.edit_perubahankls_idpetakperubahan);
        tahun = root.findViewById(R.id.edit_perubahankls_tahunperubahan);
        luas = root.findViewById(R.id.edit_perubahankls_luasperubahan);
        jenis_tanaman = root.findViewById(R.id.edit_perubahankls_jenisperubahan);
        kelas_tanaman = root.findViewById(R.id.edit_perubahankls_kelasperubahan);
        luas_perkiraan = root.findViewById(R.id.edit_perubahankls_luasperkiraan);
        jenis_perkiraan = root.findViewById(R.id.edit_perubahankls_jenisperkiraan);
        kelas_perkiraan = root.findViewById(R.id.edit_perubahankls_kelasperkiraan);
        no_bappkh = root.findViewById(R.id.edit_perubahankls_nobappkh);
        luas_definitif = root.findViewById(R.id.edit_perubahankls_luasdefinitif);
        jenis_definitif = root.findViewById(R.id.edit_perubahankls_jenisdefinitif);
        kelas_definitif = root.findViewById(R.id.edit_perubahankls_kelasdefinitif);
        keterangan = root.findViewById(R.id.edit_perubahankls_ketperubahan);
        btnSimpanPerubahan = root.findViewById(R.id.edit_perubahankls_btnsimpan);

        str_petak_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN);
        str_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
        str_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
        str_jenis_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN);
        str_kelas_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN);
        str_luas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
        str_jenis_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN);
        str_kelas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN);
        str_no_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
        str_luas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
        str_jenis_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF);
        str_kelas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF);
        str_keterangan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KETERANGAN_PERUBAHAN);

        petak_id.setText(str_petak_id);
        tahun.setText(str_tahun);
        luas.setText(str_luas);
        jenis_tanaman.setText(str_jenis_tanaman);
        kelas_tanaman.setText(str_kelas_tanaman);
        luas_perkiraan.setText(str_luas_perkiraan);
        jenis_perkiraan.setText(str_jenis_perkiraan);
        kelas_perkiraan.setText(str_kelas_perkiraan);
        no_bappkh.setText(str_no_bappkh);
        luas_definitif.setText(str_luas_definitif);
        jenis_definitif.setText(str_jenis_definitif);
        kelas_definitif.setText(str_kelas_definitif);
        keterangan.setText(str_keterangan);

        btnSimpanPerubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {

                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment perubahan
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    Fragment fragment = new ListPerubahanKelasFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();

                } catch (Exception e)
                {
                    AjnClass.showAlert(getActivity(),e.toString());
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditPerubahanViewModel.class);
        // TODO: Use the ViewModel
    }

}

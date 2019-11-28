package id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan;

import android.content.ContentValues;
import android.os.Bundle;
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

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.ListPerubahanKelasFragment;

public class TambahPerubahanFragment extends Fragment {

    private EditText petak_id, tahun, luas, jenis_tanaman, kelas_tanaman, luas_perkiraan,
            jenis_perkiraan, kelas_perkiraan, no_bappkh, luas_definitif, jenis_definitif,
            kelas_definitif, keterangan;


    private TambahPerubahanViewModel mViewModel;

    private static SQLiteHandler db;

    private Button btnSimpanPerubahan;


    public static TambahPerubahanFragment newInstance() {
        return new TambahPerubahanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tambah_perubahan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        petak_id = root.findViewById(R.id.perubahankls_idpetakperubahan);
        tahun = root.findViewById(R.id.perubahankls_tahunperubahan);
        luas = root.findViewById(R.id.perubahankls_luasperubahan);
        jenis_tanaman = root.findViewById(R.id.perubahankls_jenisperubahan);
        kelas_tanaman = root.findViewById(R.id.perubahankls_kelasperubahan);
        luas_perkiraan = root.findViewById(R.id.perubahankls_luasperkiraan);
        jenis_perkiraan = root.findViewById(R.id.perubahankls_jenisperkiraan);
        kelas_perkiraan = root.findViewById(R.id.perubahankls_kelasperkiraan);
        no_bappkh = root.findViewById(R.id.perubahankls_nobappkh);
        luas_definitif = root.findViewById(R.id.perubahankls_luasdefinitif);
        jenis_definitif = root.findViewById(R.id.perubahankls_jenisdefinitif);
        kelas_definitif = root.findViewById(R.id.perubahankls_kelasdefinitif);
        keterangan = root.findViewById(R.id.perubahankls_ketperubahan);
        btnSimpanPerubahan = root.findViewById(R.id.perubahankls_btnsubmit);

        btnSimpanPerubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    ContentValues values_aktifitas = new ContentValues();
                    values_aktifitas.put(TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN, petak_id.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.TAHUN_PERUBAHAN, tahun.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.LUAS_PERUBAHAN, luas.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN, jenis_tanaman.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN, kelas_tanaman.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.LUAS_PERKIRAAN, luas_perkiraan.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN, jenis_perkiraan.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN, kelas_perkiraan.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.NO_BAPPKH, no_bappkh.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.LUAS_DEFINITIF, luas_definitif.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF, jenis_definitif.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF, kelas_definitif.getText().toString());
                    values_aktifitas.put(TrnPerubahanKelas.KETERANGAN_PERUBAHAN, keterangan.getText().toString());
                    db.create(TrnPerubahanKelas.TABLE_NAME, values_aktifitas);
                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment gangguan
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
}

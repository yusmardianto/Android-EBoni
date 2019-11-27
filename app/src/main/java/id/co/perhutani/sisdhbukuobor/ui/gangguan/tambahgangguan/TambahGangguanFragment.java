package id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.ListGangguanFragment;


public class TambahGangguanFragment extends Fragment {

    private EditText tahun, isipetak, no_ha, tanggal, isi_kejadian, luas_lahan,
            jumlah_pohon, kerugian_kyp, kerugian_kyb, kerugian_getah,
            nilai_kerugian, keterangan;

    private static SQLiteHandler db;

    private Button btnSimpanGangguan;

    public static TambahGangguanFragment newInstance() {
        return new TambahGangguanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        tahun = root.findViewById(R.id.gangguan_tahun);
        isipetak = root.findViewById(R.id.gangguan_idpetak);
        no_ha = root.findViewById(R.id.gangguan_noha);
        tanggal = root.findViewById(R.id.gangguan_tanggalHA);
        isi_kejadian = root.findViewById(R.id.gangguan_kejadian);
        luas_lahan = root.findViewById(R.id.gangguan_luas);
        jumlah_pohon = root.findViewById(R.id.gangguan_jmlpohon);
        kerugian_kyp = root.findViewById(R.id.gangguan_kerugiankyp);
        kerugian_kyb = root.findViewById(R.id.gangguan_kerugiankyb);
        kerugian_getah = root.findViewById(R.id.gangguan_kerugiangetah);
        nilai_kerugian = root.findViewById(R.id.gangguan_nilaikerugian);
        keterangan = root.findViewById(R.id.gangguan_keterangan);
        btnSimpanGangguan = root.findViewById(R.id.gangguan_btnsubmit);

        btnSimpanGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {

                    ContentValues values_aktifitas = new ContentValues();
                    values_aktifitas.put(TrnGangguanKeamananHutan.TAHUN, tahun.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.ANAK_PETAK_ID, isipetak.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.NOMOR_HA, no_ha.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.TANGGAL_HA, tanggal.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KEJADIAN, isi_kejadian.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_LUAS, luas_lahan.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_POHON, jumlah_pohon.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_KYP, kerugian_kyp.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_KYB, kerugian_kyb.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KERUGIAN_GETAH, kerugian_getah.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.NILAI_KERUGIAN, nilai_kerugian.getText().toString());
                    values_aktifitas.put(TrnGangguanKeamananHutan.KETERANGAN, keterangan.getText().toString());
                    db.create(TrnGangguanKeamananHutan.TABLE_NAME, values_aktifitas);
                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment gangguan
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    Fragment fragment = new ListGangguanFragment();
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

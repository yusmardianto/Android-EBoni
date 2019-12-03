package id.co.perhutani.sisdhbukuobor.ui.gangguan.editgangguan;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.ListGangguanFragment;

public class EditGangguanFragment extends Fragment {

    private EditGangguanViewModel mViewModel;

    private EditText isipetak, tahun, no_ha, tanggal, isi_kejadian, luas_lahan,
            jumlah_pohon, kerugian_kyp, kerugian_kyb, kerugian_getah,
            nilai_kerugian, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_tahun, str_isipetak, str_no_ha, str_tanggal,
            str_isi_kejadian, str_luas_lahan, str_jumlah_pohon, str_kerugian_kyp, str_kerugian_kyb,
            str_kerugian_getah, str_nilai_kerugian, str_keterangan;
    private Button btnSimpanGangguan;

    public static EditGangguanFragment newInstance() {
        return new EditGangguanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_gangguan_fragment, container, false);

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

        isipetak = root.findViewById(R.id.edit_gangguan_idpetak);
        tahun = root.findViewById(R.id.edit_gangguan_tahun);
        no_ha = root.findViewById(R.id.edit_gangguan_noha);
        tanggal = root.findViewById(R.id.edit_gangguan_tanggalHA);
        isi_kejadian = root.findViewById(R.id.edit_gangguan_kejadian);
        luas_lahan = root.findViewById(R.id.edit_gangguan_luas);
        jumlah_pohon = root.findViewById(R.id.edit_gangguan_jmlpohon);
        kerugian_kyp = root.findViewById(R.id.edit_gangguan_kerugiankyp);
        kerugian_kyb = root.findViewById(R.id.edit_gangguan_kerugiankyb);
        kerugian_getah = root.findViewById(R.id.edit_gangguan_kerugiangetah);
        nilai_kerugian = root.findViewById(R.id.edit_gangguan_nilaikerugian);
        keterangan = root.findViewById(R.id.edit_gangguan_keterangan);
        btnSimpanGangguan = root.findViewById(R.id.edit_gangguan_btnsimpan);

        str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
        str_tahun = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TAHUN);
        str_no_ha = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_HA);
        str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_HA);
        str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);
        str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
        str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
        str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
        str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
        str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
        str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
        str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);

        isipetak.setText(str_isipetak);
        tahun.setText(str_tahun);
        no_ha.setText(str_no_ha);
        tanggal.setText(str_tanggal);
        isi_kejadian.setText(str_isi_kejadian);
        luas_lahan.setText(str_luas_lahan);
        jumlah_pohon.setText(str_jumlah_pohon);
        kerugian_kyp.setText(str_kerugian_kyp);
        kerugian_kyb.setText(str_kerugian_kyb);
        kerugian_getah.setText(str_kerugian_getah);
        nilai_kerugian.setText(str_nilai_kerugian);
        keterangan.setText(str_keterangan);

        btnSimpanGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment gangguan
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    Fragment fragment = new ListGangguanFragment();
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
        mViewModel = ViewModelProviders.of(this).get(EditGangguanViewModel.class);
        // TODO: Use the ViewModel
    }

}

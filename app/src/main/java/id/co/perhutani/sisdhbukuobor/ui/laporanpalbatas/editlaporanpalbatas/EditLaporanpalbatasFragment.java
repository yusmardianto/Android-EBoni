package id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.editlaporanpalbatas;

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
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.ListPelaporanpalFragment;

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
                try {

                    Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment laporan pal batas
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
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditLaporanpalbatasViewModel.class);
        // TODO: Use the ViewModel
    }

}

package id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.tambahlaporanpalbatas;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.ListPelaporanpalFragment;

public class TambahlaporanpalbatasFragment extends Fragment {

    private EditText tanggalpal, jenispal, kondisipal, nopal, jumlahpal, keteranganpal;
    private Button BtnSubmitPal;
    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    private static SQLiteHandler db;

    private TambahlaporanpalbatasViewModel mViewModel;

    public static TambahlaporanpalbatasFragment newInstance() {
        return new TambahlaporanpalbatasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return in flater.inflate(R.layout.tambah_laporanpalbatas_fragment, container, false);

        View root = inflater.inflate(R.layout.tambah_laporanpalbatas_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        tanggalpal = root.findViewById(R.id.palbatas_tanggal);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tglmulai.format(new Date());
        tanggalpal.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date1 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tgl = sdf_view.format(calendar.getTime());

                tanggalpal.setText(str_tgl);
            }

        };
        tanggalpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        jenispal = root.findViewById(R.id.palbatas_jenispal);
        kondisipal = root.findViewById(R.id.palbatas_kondisipal);
        nopal = root.findViewById(R.id.palbatas_nopal);
        jumlahpal = root.findViewById(R.id.palbatas_jumlahpal);
        keteranganpal = root.findViewById(R.id.palbatas_ketpal);
        BtnSubmitPal = root.findViewById(R.id.palbatas_btnsubmitpal);

        BtnSubmitPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ContentValues values_aktifitas = new ContentValues();
                    values_aktifitas.put(TrnLaporanPalBatas.TANGGAL_PAL, tanggalpal.getText().toString());
                    values_aktifitas.put(TrnLaporanPalBatas.JENIS_PAL, jenispal.getText().toString());
                    values_aktifitas.put(TrnLaporanPalBatas.KONDISI_PAL, kondisipal.getText().toString());
                    values_aktifitas.put(TrnLaporanPalBatas.NO_PAL, nopal.getText().toString());
                    values_aktifitas.put(TrnLaporanPalBatas.JUMLAH_PAL, jumlahpal.getText().toString());
                    values_aktifitas.put(TrnLaporanPalBatas.KETERANGAN_PAL, keteranganpal.getText().toString());
                    db.create(TrnLaporanPalBatas.TABLE_NAME, values_aktifitas);
                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment gangguan
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
}
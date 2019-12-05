package id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.tambahpemantauan;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.ListPemantauansatwaFragment;

public class TambahpemantauansatwaFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText anakpetak, jenissatwa, jumlahsatwa, waktulihat, caralihat, keterangan;
    private Button btnSubmitPemantauan;
    private Spinner spin_anak_petak;

    private TambahpemantauansatwaViewModel mViewModel;

    public static TambahpemantauansatwaFragment newInstance() {
        return new TambahpemantauansatwaFragment();
    }

    public void load_spinner_anak_petak() {
        List<String> listtpg = db.getAnakPetak();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(dataAdapter_tpg);
        spin_anak_petak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_petak = spin_anak_petak.getSelectedItem().toString();
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
                anakpetak.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tambah_pemantauansatwa_fragment, container, false);
        View root = inflater.inflate(R.layout.tambah_pemantauansatwa_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        anakpetak = root.findViewById(R.id.pemantauan_anakpetak);
        jenissatwa = root.findViewById(R.id.pemantauan_jenissatwa);
        jumlahsatwa = root.findViewById(R.id.pemantauan_jumlahsatwa);
        waktulihat = root.findViewById(R.id.pemantauan_waktulihat);
        caralihat = root.findViewById(R.id.pemantauan_caralihat);
        keterangan = root.findViewById(R.id.pemantauan_keterangan);
        btnSubmitPemantauan = root.findViewById(R.id.pemantauan_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);

        btnSubmitPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {

                    ContentValues values_aktifitas = new ContentValues();
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


                } catch (Exception e)
                {
                    AjnClass.showAlert(getActivity(),e.toString());
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(TambahpemantauansatwaViewModel.class);
//        // TODO: Use the ViewModel
//    }

}

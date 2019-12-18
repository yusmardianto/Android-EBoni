package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.tambahinteraksimdh;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;

public class TambahInteraksimdhFragment extends Fragment {

    private TambahInteraksimdhViewModel mViewModel;
    private EditText isipetak,keterangan;
    private Spinner spin_anak_petak;
    private static SQLiteHandler db;

    private Button btnSimpan;

    public static TambahInteraksimdhFragment newInstance() {
        return new TambahInteraksimdhFragment();
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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME,
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_NAME);
                isipetak.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tambah_interaksimdh_fragment, container, false);
        keterangan = root.findViewById(R.id.interaksimdh_keterangan);
        btnSimpan = root.findViewById(R.id.gangguan_btnsubmit);

        db = new SQLiteHandler(getActivity());

        isipetak = root.findViewById(R.id.interaksimdh_idpetak);
        try{
            spin_anak_petak = root.findViewById(R.id.spinner_anakpetak_interaksi);
            load_spinner_anak_petak();
            String pil_petak = spin_anak_petak.getSelectedItem().toString();
            String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_NAME);
            isipetak.setText(id_petak);
        } catch (Exception e) {
            AjnClass.showAlert(getActivity(), "Master anak petak tidak ditemukan " + e.toString());
//            sendMessage(e.getMessage());
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                actionSimpan();
            }
        });
        return root;
    }


}

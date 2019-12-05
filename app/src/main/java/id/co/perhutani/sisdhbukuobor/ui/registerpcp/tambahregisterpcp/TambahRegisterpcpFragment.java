package id.co.perhutani.sisdhbukuobor.ui.registerpcp.tambahregisterpcp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.ui.registerpcp.ListRegisterpcpFragment;

public class TambahRegisterpcpFragment extends Fragment {

    private static SQLiteHandler db;
    private EditText nopcp, petak, anakpetak, tahunpcp, luasbaku, luasblok, umur, rataratakeliling,
            bonita, mpcp, normalpcp, nmati, tahunjurangan, keterangan;
    private Button btnSimpanRegister;

    private TambahRegisterPcpViewModel mViewModel;
    private Spinner spin_anak_petak;
    final Calendar calendar = Calendar.getInstance();
    private String str_tgl;

    public static TambahRegisterpcpFragment newInstance() {
        return new TambahRegisterpcpFragment();
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
                String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME,
                        pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
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
        View root = inflater.inflate(R.layout.tambah_registerpcp_fragment, container, false);

        db = new SQLiteHandler(getActivity());

        nopcp = root.findViewById(R.id.pcp_nopcp);
        anakpetak = root.findViewById(R.id.pcp_anakpetakpcp);
        tahunpcp = root.findViewById(R.id.pcp_tahunpcp);
        SimpleDateFormat sdf_tglmulai = new SimpleDateFormat("dd-MM-yyyy");
        str_tgl = sdf_tglmulai.format(new Date());
        tahunpcp.setFocusable(false);
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

                tahunpcp.setText(str_tgl);
            }

        };
        tahunpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luasbaku = root.findViewById(R.id.pcp_luasbakupcp);
        luasblok = root.findViewById(R.id.pcp_luasblockpcp);
        umur = root.findViewById(R.id.pcp_umurpcp);
        rataratakeliling = root.findViewById(R.id.pcp_rataratapcp);
        bonita = root.findViewById(R.id.pcp_bonitapcp);
        mpcp = root.findViewById(R.id.pcp_mpcp);
        normalpcp = root.findViewById(R.id.pcp_normalpcp);
        nmati = root.findViewById(R.id.pcp_nmatipcp);
        tahunjurangan = root.findViewById(R.id.pcp_jaranganpcp);
        keterangan = root.findViewById(R.id.pcp_ketpcp);
        btnSimpanRegister = root.findViewById(R.id.pcp_btnsubmitpcp);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);


        btnSimpanRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    ContentValues values_aktifitas = new ContentValues();
                    values_aktifitas.put(TrnRegisterPcp.NO_PCP, nopcp.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.ANAK_PETAK_ID, anakpetak.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.TAHUN_PCP, tahunpcp.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.LUAS_BAKU, luasbaku.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.LUAS_BLOK, luasblok.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.UMUR, umur.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.RATARATA_KELILING, rataratakeliling.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.BONITA, bonita.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.M_PCP, mpcp.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.NORMAL_PCP, normalpcp.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.N_MATI, nmati.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.TAHUN_JARANGAN, tahunjurangan.getText().toString());
                    values_aktifitas.put(TrnRegisterPcp.KETERANGAN, keterangan.getText().toString());
                    db.create(TrnRegisterPcp.TABLE_NAME, values_aktifitas);
                    Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                    // Move to fragment register pcp
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    Fragment fragment = new ListRegisterpcpFragment();
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
//        mViewModel = ViewModelProviders.of(this).get(TambahRegisterPcpViewModel.class);
//        // TODO: Use the ViewModel
//    }

}

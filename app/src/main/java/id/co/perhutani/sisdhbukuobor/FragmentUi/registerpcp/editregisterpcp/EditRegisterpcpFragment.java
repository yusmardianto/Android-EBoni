package id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.editregisterpcp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.ListRegisterpcpFragment;

public class EditRegisterpcpFragment extends Fragment {

    private EditText nopcp, anakpetak, tahunpcp, luasbaku, luasblok, umur, rataratakeliling,
            bonita, nlapangan, normalpcp, nmati, peninggi, tahunjarangan, keterangan;

    private static String id, str_nopcp, str_anakpetak, str_tahunpcp, str_luasbaku, str_luasblok,
            str_umur, str_rataratakeliling, str_bonita, str_nlapangan, str_normalpcp, str_nmati, str_tahunjarangan,
            str_peninggi, str_keterangan;

    private Button btnSimpanRegisterpcp;

    private Spinner spin_anak_petak;
    final Calendar calendar = Calendar.getInstance();

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private EditRegisterpcpViewModel mViewModel;

    public static EditRegisterpcpFragment newInstance() {
        return new EditRegisterpcpFragment();
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
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
                anakpetak.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.edit_registerpcp_fragment, container, false);

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

        nopcp = root.findViewById(R.id.edit_pcp_nopcp);
        anakpetak = root.findViewById(R.id.edit_pcp_anakpetakpcp);
        tahunpcp = root.findViewById(R.id.edit_pcp_tahunpcp);
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
                str_tahunpcp = sdf_view.format(calendar.getTime());

                tahunpcp.setText(str_tahunpcp);
            }

        };
        tahunpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luasbaku = root.findViewById(R.id.edit_pcp_luasbakupcp);
        luasblok = root.findViewById(R.id.edit_pcp_luasblockpcp);
        umur = root.findViewById(R.id.edit_pcp_umurpcp);
        rataratakeliling = root.findViewById(R.id.edit_pcp_rataratapcp);
        bonita = root.findViewById(R.id.edit_pcp_bonitapcp);
        nlapangan = root.findViewById(R.id.edit_pcp_nlapangan);
        normalpcp = root.findViewById(R.id.edit_pcp_normalpcp);
        nmati = root.findViewById(R.id.edit_pcp_nmatipcp);
        peninggi = root.findViewById(R.id.edit_pcp_peninggi);
        tahunjarangan = root.findViewById(R.id.edit_pcp_jaranganpcp);
        keterangan = root.findViewById(R.id.edit_pcp_ketpcp);
        btnSimpanRegisterpcp = root.findViewById(R.id.edit_pcp_btnsubmitpcp);

        spin_anak_petak = root.findViewById(R.id.spinner_anak_petak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_NAME);
        anakpetak.setText(id_petak);


        str_nopcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NO_PCP);
        str_anakpetak = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KET1);
        str_tahunpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_PCP);
        str_luasbaku = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BAKU);
        str_luasblok = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BLOK);
        str_umur = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UMUR);
        str_rataratakeliling = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.RATARATA_KELILING);
        str_bonita = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.BONITA);
        str_nlapangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_LAPANGAN);
        str_normalpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NORMAL_PCP);
        str_nmati = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_MATI);
        str_tahunjarangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_JARANGAN);
        str_peninggi = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.PENIGGI);
        str_keterangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KETERANGAN);

        nopcp.setText(str_nopcp);
        anakpetak.setText(str_anakpetak);
        tahunpcp.setText(str_tahunjarangan);
        luasbaku.setText(str_luasbaku);
        luasblok.setText(str_luasblok);
        umur.setText(str_umur);
        rataratakeliling.setText(str_rataratakeliling);
        bonita.setText(str_bonita);
        nlapangan.setText(str_nlapangan);
        normalpcp.setText(str_normalpcp);
        nmati.setText(str_nmati);
        tahunjarangan.setText(str_tahunjarangan);
        peninggi.setText(str_peninggi);
        keterangan.setText(str_keterangan);

        btnSimpanRegisterpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act_simpan();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditRegisterpcpViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String nomor = nopcp.getText().toString();
            final String AnakPetak = anakpetak.getText().toString();
            final String tahun = tahunpcp.getText().toString();
            final String umurpcp = umur.getText().toString();
            final String luasblokpcp = luasblok.getText().toString();
            final String luasbakupcp = luasbaku.getText().toString();
            final String ratakeliling = rataratakeliling.getText().toString();
            final String bonitapcp = bonita.getText().toString();
            final String nlapanganpcp = nlapangan.getText().toString();
            final String nmatipcp = nmati.getText().toString();
            final String normal = normalpcp.getText().toString();
            final String jarangan = tahunjarangan.getText().toString();
            final String peninggipcp = peninggi.getText().toString();

            if (nomor.equals("") || nomor.equals("0") || nomor.equals(" ") || nomor.equals(null)) {
                AjnClass.showAlert(getActivity(), "Nomor PCP tidak boleh kosong");

            } else if (AnakPetak.equals("") || AnakPetak.equals("0") || AnakPetak.equals(" ") || AnakPetak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak tidak boleh kosong");

            } else if(tahun.equals("") || tahun.equals("0") || tahun.equals(" ") || tahun.equals(null)){
                AjnClass.showAlert(getActivity(), "Tanggal tidak boleh kosong");

            } else if(umurpcp.equals("") || umurpcp.equals("0") || umurpcp.equals(" ") || umurpcp.equals(null)){
                AjnClass.showAlert(getActivity(), "Umur tidak boleh kosong");

            } else if(luasblokpcp.equals("") || luasblokpcp.equals("0") || luasblokpcp.equals(" ") || luasblokpcp.equals(null)){
                AjnClass.showAlert(getActivity(), "Luas Blok tidak boleh kosong");

            } else if(luasbakupcp.equals("") || luasbakupcp.equals("0") || luasbakupcp.equals(" ") || luasbakupcp.equals(null)){
                AjnClass.showAlert(getActivity(), "Luas Baku tidak boleh kosong");

            } else if(ratakeliling.equals("") || ratakeliling.equals("0") || ratakeliling.equals(" ") || ratakeliling.equals(null)){
                AjnClass.showAlert(getActivity(), "Rata-rata Keliling tidak boleh kosong");

            } else if(bonitapcp.equals("") || bonitapcp.equals("0") || bonitapcp.equals(" ") || bonitapcp.equals(null)){
                AjnClass.showAlert(getActivity(), "Bonita tidak boleh kosong");

            } else if(nlapanganpcp.equals("") || nlapanganpcp.equals("0") || nlapanganpcp.equals(" ") || nlapanganpcp.equals(null)){
                AjnClass.showAlert(getActivity(), "N Lapangan tidak boleh kosong");

            } else if(nmatipcp.equals("") || nmatipcp.equals("0") || nmatipcp.equals(" ") || nmatipcp.equals(null)){
                AjnClass.showAlert(getActivity(), "N Mati tidak boleh kosong");

            } else if(normal.equals("") || normal.equals("0") || normal.equals(" ") || normal.equals(null)){
                AjnClass.showAlert(getActivity(), "Normal dalam PCP tidak boleh kosong");

            } else if(jarangan.equals("") || jarangan.equals("0") || jarangan.equals(" ") || jarangan.equals(null)){
                AjnClass.showAlert(getActivity(), "Tahun Jarangan tidak boleh kosong");

            } else if(peninggipcp.equals("") || peninggipcp.equals("0") || peninggipcp.equals(" ") || peninggipcp.equals(null)){
                AjnClass.showAlert(getActivity(), "Peninggi tidak boleh kosong");

            }else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(AnakPetak)
                        .setCancelText("Batal")
                        .setConfirmText("Simpan")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Dibatalkan!")
                                        .setContentText("")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("Success!")
                                        .setContentText(AnakPetak)
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        try {
                                            RegisterpcpModel Aktifitasnya = new RegisterpcpModel();
                                            Aktifitasnya.setID_Registerpcp(Integer.parseInt(id));
                                            Aktifitasnya.setNoPcp(nopcp.getText().toString());
                                            Aktifitasnya.setAnakPetakId(anakpetak.getText().toString());
                                            Aktifitasnya.setTahun(tahunpcp.getText().toString());
                                            Aktifitasnya.setLuasBaku(luasbaku.getText().toString());
                                            Aktifitasnya.setLuasBlok(luasblok.getText().toString());
                                            Aktifitasnya.setUmur(umur.getText().toString());
                                            Aktifitasnya.setRataKeliling(rataratakeliling.getText().toString());
                                            Aktifitasnya.setBonita(bonita.getText().toString());
                                            Aktifitasnya.setNLapangan(nlapangan.getText().toString());
                                            Aktifitasnya.setNormalPcp(normalpcp.getText().toString());
                                            Aktifitasnya.setNMati(nmati.getText().toString());
                                            Aktifitasnya.setPeninggi(peninggi.getText().toString());
                                            Aktifitasnya.setTahunJarangan(tahunjarangan.getText().toString());
                                            Aktifitasnya.setKeteranganPcp(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet9("2");
                                            db.EditDataRegisterPCP(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListRegisterpcpFragment();
                                            FragmentTransaction ft = manager.beginTransaction();
                                            ft.replace(R.id.nav_host_fragment, fragment);
                                            ft.commit();


                                        } catch (Exception e) {
                                            AjnClass.showAlert(getActivity(), e.toString());
                                            e.printStackTrace();
                                        }
                                    }

                                }, 1000);
                            }
                        })
                        .show();

            }

        } catch (Exception e) {
            AjnClass.showAlert(getActivity(), "error " + e.toString());
//            sendMessage(e.getMessage());
        }
    }

}

package id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.editpengelolaanhutan;

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
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PengelolaanHutanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstSubPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;

public class EditPengelolaanHutanFragment extends Fragment
{
    private EditPengelolaanHutanViewModel mViewModel;
    private EditText jenispekerjaan, sub_pekerjaan, anakpetak, tanggal, rencana, realisasi, status, keterangan;
    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_pekerjaan, str_sub_pekerjaan, str_anakpetak, str_tanggal, str_rencana, str_realisasi, str_status, str_keterangan;
    private Button btnSimpanPengelolaanHutan;
    private Spinner spin_pekerjaan;
    private Spinner spin_sub_pekerjaan;
    private Spinner spin_anak_petak;
    private Spinner spin_status;
    final Calendar calendar_pekerjaan = Calendar.getInstance();

    public static EditPengelolaanHutanFragment newInstance() {
        return new EditPengelolaanHutanFragment();
    }

    public void load_spinner_jenis_pekerjaan() {
        List<String> listtpg = db.getPekerjaan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_pekerjaan.setAdapter(dataAdapter_tpg);
        spin_pekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_pekerjaan = spin_pekerjaan.getSelectedItem().toString();
                String pekerjaan = db.getDataDetail(MstPekerjaan.TABLE_NAME,
                        MstPekerjaan.PEKERJAAN_NAME, pil_pekerjaan, MstPekerjaan.PEKERJAAN_ID);
                jenispekerjaan.setText(pekerjaan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void load_spinner_sub_pekerjaan() {
        List<String> listtpg = db.getSubPekerjaan();
        final int _tpg = listtpg.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listtpg) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_sub_pekerjaan.setAdapter(dataAdapter_tpg);
        spin_sub_pekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_sub = spin_sub_pekerjaan.getSelectedItem().toString();
                String sub_Pekerjaan = db.getDataDetail(MstSubPekerjaan.TABLE_NAME,
                        MstSubPekerjaan.SUB_PEKERJAAN_NAME, pil_sub, MstSubPekerjaan.SUB_PEKERJAAN_ID);
                sub_pekerjaan.setText(sub_Pekerjaan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
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
//        return inflater.inflate(R.layout.edit_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.edit_pengelolaanhutan_fragment, container, false);

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

        tanggal = root.findViewById(R.id.edit_pengelolaanhutan_tanggal);
        tanggal.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date1 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar_pekerjaan.set(Calendar.YEAR, year);
                calendar_pekerjaan.set(Calendar.MONTH, monthOfYear);
                calendar_pekerjaan.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tanggal = sdf_view.format(calendar_pekerjaan.getTime());

                tanggal.setText(str_tanggal);
            }

        };
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar_pekerjaan.get(Calendar.YEAR), calendar_pekerjaan.get(Calendar.MONTH),
                        calendar_pekerjaan.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        rencana = root.findViewById(R.id.edit_pengelolaanhutan_rencana);
        realisasi = root.findViewById(R.id.edit_pengelolaanhutan_realisasi);
        status = root.findViewById(R.id.edit_pengelolaanhutan_status);
        keterangan = root.findViewById(R.id.edit_pengelolaanhutan_keterangan);
        btnSimpanPengelolaanHutan = root.findViewById(R.id.edit_pengelolaanhutan_btnsubmit);

        spin_pekerjaan = root.findViewById(R.id.edit_pengelolaanhutan_jenispekerjaan);
        load_spinner_jenis_pekerjaan();
        String pil_pekerjaan = spin_pekerjaan.getSelectedItem().toString();
        String id_pekerjaan = db.getDataDetail(MstJenisGangguanHutanSchema.TABLE_NAME, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, pil_pekerjaan, MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME);
        jenispekerjaan.setText(id_pekerjaan);

        spin_sub_pekerjaan = root.findViewById(R.id.edit_pengelolaanhutan_subpekerjaan);
        load_spinner_sub_pekerjaan();
        String pil_sub = spin_sub_pekerjaan.getSelectedItem().toString();
        String sub_Pekerjaan = db.getDataDetail(MstSubPekerjaan.TABLE_NAME, MstSubPekerjaan.SUB_PEKERJAAN_NAME, pil_sub, MstSubPekerjaan.SUB_PEKERJAAN_NAME);
        sub_pekerjaan.setText(sub_Pekerjaan);

        spin_anak_petak = root.findViewById(R.id.edit_pengelolaanhutan_anakpetak);
        load_spinner_anak_petak();
        String pil_petak = spin_anak_petak.getSelectedItem().toString();
        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.ANAK_PETAK_ID);
        anakpetak.setText(id_petak);

        str_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET1);
        str_sub_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET2);
        str_anakpetak = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET3);
        str_tanggal = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.TANGGAL);
        str_rencana = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.RENCANA);
        str_realisasi = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.REALISASI);
        str_status = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.STATUS);
        str_keterangan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KETERANGAN);

        jenispekerjaan.setText(str_pekerjaan);
        sub_pekerjaan.setText(str_sub_pekerjaan);
        anakpetak.setText(str_anakpetak);
        tanggal.setText(str_tanggal);
        rencana.setText(str_rencana);
        realisasi.setText(str_realisasi);
        status.setText(str_status);
        keterangan.setText(str_keterangan);

        btnSimpanPengelolaanHutan.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(EditPengelolaanHutanViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String pekerjaan = jenispekerjaan.getText().toString();
            final String sub_Pekerjaan = sub_pekerjaan.getText().toString();
            final String petak = anakpetak.getText().toString();
            final String tgl = tanggal.getText().toString();
            final String Rencana = rencana.getText().toString();
            final String Realisasi = realisasi.getText().toString();
            final String Status = status.getText().toString();

            if (pekerjaan.equals("") || pekerjaan.equals("0") || pekerjaan.equals(" ") || pekerjaan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Pekerjaan harus diisi");

            } else if (sub_Pekerjaan.equals("") || sub_Pekerjaan.equals("0") || sub_Pekerjaan.equals(" ") || sub_Pekerjaan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Sub Pekerjaan harus diisi");

            } else if (petak.equals("") || petak.equals("0") || petak.equals(" ") || petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (tgl.equals("") || tgl.equals("0") || tgl.equals(" ") || tgl.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal Pekerjaan harus diisi");

            } else if (Rencana.equals("") || Rencana.equals("0") || Rencana.equals(" ") || Rencana.equals(null)) {
                AjnClass.showAlert(getActivity(), "Rencana Pekerjaan harus diisi");

            } else if (Status.equals("") || Status.equals("0") || Status.equals(" ") || Status.equals(null)) {
                AjnClass.showAlert(getActivity(), "Status Pekerjaan harus diisi");

            } else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText(pekerjaan)
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
                                        .setContentText(pekerjaan)
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

                                            PengelolaanHutanModel Aktifitasnya = new PengelolaanHutanModel();
                                            Aktifitasnya.setID_Pekerjaan(Integer.parseInt(id));
                                            Aktifitasnya.setJenisPekerjaan(jenispekerjaan.getText().toString());
                                            Aktifitasnya.setSubPekerjaan(sub_pekerjaan.getText().toString());
                                            Aktifitasnya.setAnakPetak(anakpetak.getText().toString());
                                            Aktifitasnya.setTanggal(tanggal.getText().toString());
                                            Aktifitasnya.setRencana(rencana.getText().toString());
                                            Aktifitasnya.setRealisasi(realisasi.getText().toString());
                                            Aktifitasnya.setStatus(status.getText().toString());
                                            Aktifitasnya.setKeterangan(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_pekerjaan.getSelectedItem().toString());
                                            Aktifitasnya.setKet2(spin_sub_pekerjaan.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet9("2");
                                            db.EditDataPengelolaanHutan(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
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

package id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.editperubahan;

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
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;

public class EditPerubahanFragment extends Fragment {

    private EditText petak_id, tahun, luas, jenis_tanaman, kelas_hutan, luas_perkiraan,
            jenis_perkiraan, kelas_perkiraan, no_bappkh, luas_definitif, jenis_definitif,
            kelas_definitif, keterangan;

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private static String id, str_petak_id, str_tahun, str_luas, str_jenis_tanaman,
            str_kelas_tanaman, str_luas_perkiraan, str_jenis_perkiraan, str_kelas_perkiraan,
            str_no_bappkh, str_luas_definitif, str_jenis_definitif, str_kelas_definitif, str_keterangan, str_petak,
            str_tanggal;
    private Button btnSimpanPerubahan;

    private Spinner spin_anak_petak;
    private Spinner spin_jenis_tanaman;
    private Spinner spin_jenis_tanaman_perkiraan;
    private Spinner spin_kelas_hutan;
    private Spinner spin_kelas_hutan_perkiraan;
    private Spinner spin_jenis_tanaman_definitif;
    private Spinner spin_kelas_hutan_definitif;
    final Calendar calendar = Calendar.getInstance();

    private EditPerubahanViewModel mViewModel;

    public static EditPerubahanFragment newInstance() {
        return new EditPerubahanFragment();
    }

    private static String str_edit_anakpetak, str_edit_jenistanaman, str_edit_klshtn,
            str_edit_tanamanperkiraan, str_edit_klshtn_perkiraan, str_edit_jenis_tanaman_definitif,
    str_edit_klshtn_definitif;

    public void load_spinner_anak_petak() {
        List<String> list_anakpetak = db.getAnakPetak();
        final int _tpg = list_anakpetak.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list_anakpetak) {
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
                        MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
                petak_id.setText(id_petak);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_anakpetak = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list_anakpetak);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_anak_petak.setAdapter(adapter);
        if (str_edit_anakpetak != null) {
            int spinnerPosition = adapter.getPosition(str_edit_anakpetak);
            spin_anak_petak.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_jenis_tanaman() {
        List<String> listjenis = db.getJenisTanaman();
        final int _tpg = listjenis.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman = spin_jenis_tanaman.getSelectedItem().toString();
                String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_tanaman.setText(id_jenis);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_jenistanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listjenis);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman.setAdapter(adapter);
        if (str_edit_jenistanaman != null) {
            int spinnerPosition = adapter.getPosition(str_edit_jenistanaman);
            spin_jenis_tanaman.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_kelas_hutan() {
        List<String> listkelas = db.getKelasHutan();
        final int _tpg = listkelas.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listkelas) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan = spin_kelas_hutan.getSelectedItem().toString();
                String id_kelas_hutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_hutan.setText(id_kelas_hutan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_klshtn = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listkelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan.setAdapter(adapter);
        if (str_edit_klshtn != null) {
            int spinnerPosition = adapter.getPosition(str_edit_klshtn);
            spin_kelas_hutan.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_jenis_tanaman_perkiraan() {
        List<String> listjenis_tanaman_perkiraan = db.getJenisTanaman();
        final int _tpg = listjenis_tanaman_perkiraan.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis_tanaman_perkiraan) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_perkiraan.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman_perkiraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman_perkiraan = spin_jenis_tanaman_perkiraan.getSelectedItem().toString();
                String id_jenis_perkiraan = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_perkiraan, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_perkiraan.setText(id_jenis_perkiraan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_tanamanperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listjenis_tanaman_perkiraan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_perkiraan.setAdapter(adapter);
        if (str_edit_tanamanperkiraan != null) {
            int spinnerPosition = adapter.getPosition(str_edit_tanamanperkiraan);
            spin_jenis_tanaman_perkiraan.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_kelas_hutan_perkiraan() {
        List<String> list_kelas_hutan_perkiraan = db.getKelasHutan();
        final int _tpg = list_kelas_hutan_perkiraan.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list_kelas_hutan_perkiraan) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_perkiraan.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan_perkiraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan_perkiraan = spin_kelas_hutan_perkiraan.getSelectedItem().toString();
                String id_kelas_hutan_perkiraan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_perkiraan, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_perkiraan.setText(id_kelas_hutan_perkiraan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_klshtn_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list_kelas_hutan_perkiraan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_perkiraan.setAdapter(adapter);
        if (str_edit_klshtn_perkiraan != null) {
            int spinnerPosition = adapter.getPosition(str_edit_klshtn_perkiraan);
            spin_kelas_hutan_perkiraan.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_jenis_tanaman_definitif() {
        List<String> listjenis_tanaman_definitif = db.getJenisTanaman();
        final int _tpg = listjenis_tanaman_definitif.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listjenis_tanaman_definitif) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_definitif.setAdapter(dataAdapter_tpg);
        spin_jenis_tanaman_definitif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_jenis_tanaman_definitif = spin_jenis_tanaman_definitif.getSelectedItem().toString();
                String id_jenis_tanaman_definitif = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME,
                        MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_definitif, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
                jenis_definitif.setText(id_jenis_tanaman_definitif);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_jenis_tanaman_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET6);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listjenis_tanaman_definitif);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_jenis_tanaman_definitif.setAdapter(adapter);
        if (str_edit_jenis_tanaman_definitif != null) {
            int spinnerPosition = adapter.getPosition(str_edit_jenis_tanaman_definitif);
            spin_jenis_tanaman_definitif.setSelection(spinnerPosition);
        }
    }

    public void load_spinner_kelas_hutan_definitif() {
        List<String> list_kelas_hutan_definitif = db.getKelasHutan();
        final int _tpg = list_kelas_hutan_definitif.size();
        ArrayAdapter<String> dataAdapter_tpg = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list_kelas_hutan_definitif) {
            @Override
            public int getCount() {
                return (_tpg); // Truncate the list
            }
        };
        dataAdapter_tpg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_definitif.setAdapter(dataAdapter_tpg);
        spin_kelas_hutan_definitif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // your code here
                String pil_kelas_hutan_definitif = spin_kelas_hutan_definitif.getSelectedItem().toString();
                String id_kelas_hutan_definitif = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME,
                        MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_definitif, MstKelasHutanSchema.KELAS_HUTAN_ID);
                kelas_definitif.setText(id_kelas_hutan_definitif);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        str_edit_klshtn_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET7);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list_kelas_hutan_definitif);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kelas_hutan_definitif.setAdapter(adapter);
        if (str_edit_klshtn_definitif != null) {
            int spinnerPosition = adapter.getPosition(str_edit_klshtn_definitif);
            spin_kelas_hutan_definitif.setSelection(spinnerPosition);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.edit_perubahan_fragment, container, false);

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

        Toolbar toolbar = root.findViewById(R.id.toolbar_editperubahankls);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPerubahanKelasFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        petak_id = root.findViewById(R.id.edit_perubahankls_idpetakperubahan);
        tahun = root.findViewById(R.id.edit_perubahankls_tahunperubahan);
        tahun.setFocusable(false);
        final DatePickerDialog.OnDateSetListener date1 = new android.app.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf_view = new SimpleDateFormat("yyyy-MM-dd");
                str_tanggal = sdf_view.format(calendar.getTime());

                tahun.setText(str_tanggal);
            }

        };
        tahun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        luas = root.findViewById(R.id.edit_perubahankls_luasperubahan);
        jenis_tanaman = root.findViewById(R.id.edit_perubahankls_jenisperubahan);
        kelas_hutan = root.findViewById(R.id.edit_perubahankls_kelasperubahan);
        luas_perkiraan = root.findViewById(R.id.edit_perubahankls_luasperkiraan);
        jenis_perkiraan = root.findViewById(R.id.edit_perubahankls_jenisperkiraan);
        kelas_perkiraan = root.findViewById(R.id.edit_perubahankls_kelasperkiraan);
        no_bappkh = root.findViewById(R.id.edit_perubahankls_nobappkh);
        luas_definitif = root.findViewById(R.id.edit_perubahankls_luasdefinitif);
        jenis_definitif = root.findViewById(R.id.edit_perubahankls_jenisdefinitif);
        kelas_definitif = root.findViewById(R.id.edit_perubahankls_kelasdefinitif);
        keterangan = root.findViewById(R.id.edit_perubahankls_ketperubahan);
        btnSimpanPerubahan = root.findViewById(R.id.edit_perubahankls_btnsubmit);

        spin_anak_petak = root.findViewById(R.id.edit_spinner_anak_petak_perubahan);
        load_spinner_anak_petak();
//        String pil_petak = spin_anak_petak.getSelectedItem().toString();
//        String id_petak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_NAME, pil_petak, MstAnakPetakSchema.KODE_ANAKPETAK);
//        petak_id.setText(id_petak);

        spin_jenis_tanaman = root.findViewById(R.id.edit_spinner_jenis_perubahan);
        load_spinner_jenis_tanaman();
//        String pil_jenis_tanaman = spin_jenis_tanaman.getSelectedItem().toString();
//        String id_jenis = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
//        jenis_tanaman.setText(id_jenis);

        spin_jenis_tanaman_perkiraan = root.findViewById(R.id.edit_spinner_jenis_perkiraan);
        load_spinner_jenis_tanaman_perkiraan();
//        String pil_jenis_tanaman_perkiraan = spin_jenis_tanaman_perkiraan.getSelectedItem().toString();
//        String id_jenis_perkiraan = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_perkiraan, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
//        jenis_perkiraan.setText(id_jenis_perkiraan);

        spin_kelas_hutan = root.findViewById(R.id.edit_spinner_kelas_hutan);
        load_spinner_kelas_hutan();
//        String pil_kelas_hutan = spin_kelas_hutan.getSelectedItem().toString();
//        String id_kelas_hutan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan, MstKelasHutanSchema.KELAS_HUTAN_ID);
//        kelas_hutan.setText(id_kelas_hutan);

        spin_kelas_hutan_perkiraan = root.findViewById(R.id.edit_spinner_kelas_hutan_perkiraan);
        load_spinner_kelas_hutan_perkiraan();
//        String pil_kelas_hutan_perkiraan = spin_kelas_hutan_perkiraan.getSelectedItem().toString();
//        String id_kelas_hutan_perkiraan = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_perkiraan, MstKelasHutanSchema.KELAS_HUTAN_ID);
//        kelas_perkiraan.setText(id_kelas_hutan_perkiraan);

        spin_jenis_tanaman_definitif = root.findViewById(R.id.edit_spinner_jenis_tanaman_definitif);
        load_spinner_jenis_tanaman_definitif();
//        String pil_jenis_tanaman_definitif = spin_jenis_tanaman_definitif.getSelectedItem().toString();
//        String id_jenis_tanaman_definitif = db.getDataDetail(MstJenisTanamanSchema.TABLE_NAME, MstJenisTanamanSchema.JENIS_TANAMAN_NAME, pil_jenis_tanaman_definitif, MstJenisTanamanSchema.JENIS_TANAMAN_ID);
//        jenis_definitif.setText(id_jenis_tanaman_definitif);

        spin_kelas_hutan_definitif = root.findViewById(R.id.edit_spinner_perubahan_kelas_definitif);
        load_spinner_kelas_hutan_definitif();
//        String pil_kelas_hutan_definitif = spin_kelas_hutan_definitif.getSelectedItem().toString();
//        String id_kelas_hutan_definitif = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_NAME, pil_kelas_hutan_definitif, MstKelasHutanSchema.KELAS_HUTAN_ID);
//        kelas_definitif.setText(id_kelas_hutan_definitif);

        str_petak_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET1);
        str_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
        str_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
        str_jenis_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET2);
        str_kelas_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET3);
        str_luas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
        str_jenis_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET4);
        str_kelas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET5);
        str_no_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
        str_luas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
        str_jenis_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET6);
        str_kelas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET7);
        str_keterangan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KETERANGAN_PERUBAHAN);
        str_petak = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.HEXA);

        petak_id.setText(str_petak_id);
        tahun.setText(str_tahun);
        luas.setText(str_luas);
        jenis_tanaman.setText(str_jenis_tanaman);
        kelas_hutan.setText(str_kelas_tanaman);
        luas_perkiraan.setText(str_luas_perkiraan);
        jenis_perkiraan.setText(str_jenis_perkiraan);
        kelas_perkiraan.setText(str_kelas_perkiraan);
        no_bappkh.setText(str_no_bappkh);
        luas_definitif.setText(str_luas_definitif);
        jenis_definitif.setText(str_jenis_definitif);
        kelas_definitif.setText(str_kelas_definitif);
        keterangan.setText(str_keterangan);

        btnSimpanPerubahan.setOnClickListener(new View.OnClickListener() {
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
        mViewModel = ViewModelProviders.of(this).get(EditPerubahanViewModel.class);
        // TODO: Use the ViewModel
    }

    public void act_simpan() {
        try {

            final String jenistanaman = jenis_tanaman.getText().toString();
            final String petak = petak_id.getText().toString();
            final String str_tanggal = tahun.getText().toString();
            final String kelas = kelas_hutan.getText().toString();
            final String luasperubahan = luas.getText().toString();
            final String tanamanperkiraan = jenis_perkiraan.getText().toString();
            final String kelasperkiraan = kelas_perkiraan.getText().toString();
            final String luasperkiraan = luas_perkiraan.getText().toString();
            final String nobappkh = no_bappkh.getText().toString();
            final String tanamandefinitif = jenis_definitif.getText().toString();
            final String luasdefinitif = luas_definitif.getText().toString();
            final String kelasdefinitif = kelas_definitif.getText().toString();

            if (jenistanaman.equals("") || jenistanaman.equals("0") || jenistanaman.equals(" ") || jenistanaman.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman harus diisi");

            } else if (petak.equals("") || petak.equals("0") || petak.equals(" ") || petak.equals(null)) {
                AjnClass.showAlert(getActivity(), "Anak Petak harus diisi");

            } else if (kelas.equals("") || kelas.equals("0") || kelas.equals(" ") || kelas.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan harus diisi");

            } else if (luasperubahan.equals("") || luasperubahan.equals("0") || luasperubahan.equals(" ") || luasperubahan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas harus diisi");

            } else if (str_tanggal.equals("") || str_tanggal.equals("0") || str_tanggal.equals(" ") || str_tanggal.equals(null)) {
                AjnClass.showAlert(getActivity(), "Tanggal harus diisi");

            } else if (tanamanperkiraan.equals("") || tanamanperkiraan.equals("0") || tanamanperkiraan.equals(" ") || tanamanperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman Perkiraan harus diisi");

            } else if (kelasperkiraan.equals("") || kelasperkiraan.equals("0") || kelasperkiraan.equals(" ") || kelasperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan Perkiraan harus diisi");

            } else if (luasperkiraan.equals("") || luasperkiraan.equals("0") || luasperkiraan.equals(" ") || luasperkiraan.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Perkiraan harus diisi");

            } else if (nobappkh.equals("") || nobappkh.equals("0") || nobappkh.equals(" ") || nobappkh.equals(null)) {
                AjnClass.showAlert(getActivity(), "No BAP PKH harus diisi");

            } else if (tanamandefinitif.equals("") || tanamandefinitif.equals("0") || tanamandefinitif.equals(" ") || tanamandefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Jenis Tanaman Definitf harus diisi");

            } else if (kelasdefinitif.equals("") || kelasdefinitif.equals("0") || kelasdefinitif.equals(" ") || kelasdefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Kelas Hutan Definitif harus diisi");

            } else if (luasdefinitif.equals("") || luasdefinitif.equals("0") || luasdefinitif.equals(" ") || luasdefinitif.equals(null)) {
                AjnClass.showAlert(getActivity(), "Luas Definitif harus diisi");

            }else {

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Simpan ?")
                        .setContentText("")
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
                                        .setContentText("")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        String ambilKunci = "perhutani";
                                        String ambilKata = petak_id.getText().toString();
                                        String enKata = "";
                                        try {
                                            PerubahankelasModel Aktifitasnya = new PerubahankelasModel();
                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            Aktifitasnya.setID_Perubahan(Integer.parseInt(id));
                                            Aktifitasnya.setPetakID(petak_id.getText().toString());
                                            Aktifitasnya.setTahun(tahun.getText().toString());
                                            Aktifitasnya.setLuas(luas.getText().toString());
                                            Aktifitasnya.setJenisTanaman(jenis_tanaman.getText().toString());
                                            Aktifitasnya.setKelasHutan(kelas_hutan.getText().toString());
                                            Aktifitasnya.setLuasPerkiraan(luas_perkiraan.getText().toString());
                                            Aktifitasnya.setJenisTanamanPerkiraan(jenis_perkiraan.getText().toString());
                                            Aktifitasnya.setKelasHutanPerkiraan(kelas_perkiraan.getText().toString());
                                            Aktifitasnya.setNoBappkh(no_bappkh.getText().toString());
                                            Aktifitasnya.setLuasDefinitif(luas_definitif.getText().toString());
                                            Aktifitasnya.setJenisTanamanDefinitif(jenis_definitif.getText().toString());
                                            Aktifitasnya.setKelasHutanDefinitif(kelas_definitif.getText().toString());
                                            Aktifitasnya.setKeteranganPerubahan(keterangan.getText().toString());
                                            Aktifitasnya.setKet1(spin_anak_petak.getSelectedItem().toString());
                                            Aktifitasnya.setKet2(spin_jenis_tanaman.getSelectedItem().toString());
                                            Aktifitasnya.setKet3(spin_kelas_hutan.getSelectedItem().toString());
                                            Aktifitasnya.setKet4(spin_jenis_tanaman_perkiraan.getSelectedItem().toString());
                                            Aktifitasnya.setKet5(spin_kelas_hutan_perkiraan.getSelectedItem().toString());
                                            Aktifitasnya.setKet6(spin_jenis_tanaman_definitif.getSelectedItem().toString());
                                            Aktifitasnya.setKet7(spin_kelas_hutan_definitif.getSelectedItem().toString());
                                            Aktifitasnya.setKet9("2");
                                            Aktifitasnya.setKet11(enKata);
                                            db.EditDataPerubahanKelas(Aktifitasnya);

                                            Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                            FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                            Fragment fragment = new ListPerubahanKelasFragment();
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

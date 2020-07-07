package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.PengukuranPohonAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.TallySheetAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.DetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.ListTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TabDetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian.TambahPasangBatasPersemaian;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;


public class ListPengukuranPohonFragment extends Fragment {

    View list_pu,popup_tambah;
    private EditText no_pohon, keliling_pohon,peninggi_pohon,kualitas_batang;
    private AlertDialog.Builder builder;
    private RecyclerView rv_pengukuran_pohon;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<PuPohonModel> dataModels;
    private PengukuranPohonAdapter pengukuranPohonAdapter;
    private static final int VERTICAL_ITEM_SPACE = 0;
    private SessionManager session;
    private static String pu_id_tallysheet;


    public static String id_pu;
    private int color = 0;
    private SQLiteHandler db;
    private List<PuPohonModel> ls_pu_pohon;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (isOnline()) {
                    dataPengukuranPohon();
                }
            } catch (Exception ex) {
            }
            handler.postDelayed(this, 10000);
        }
    };
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    EditText id_pupu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getActivity());
        try {
            id_pu = session.getPreferences(getActivity(),"ses_id_tallysheet");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        list_pu = inflater.inflate(R.layout.fragment_list_pengukuran_pohon, container, false);
        db = new SQLiteHandler(getActivity());
        handler.postDelayed(runnable, 1000);

        FloatingActionButton btnTambah = list_pu.findViewById(R.id.btn_tambahpohonpu);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                popup_tambah = factory.inflate(R.layout.fragment_create_pu_pohon, null);
                db = new SQLiteHandler(getActivity());

                session = new SessionManager(getActivity());
                try {
                    String message = session.getPreferences(getActivity(),"ses_id_tallysheet");
                    id_pu = message;
                    id_pupu.setText(id_pu);
                    if (message != null) {
                        pu_id_tallysheet=message;
                    } else {
//                        pu_id_tallysheet="null";
//                        AjnClass.showAlert(getActivity(), "Terjadi kesalahan dalam pengambilan data");
                        Fragment fragment = new ListTallySheetFragment();
                        FragmentManager frgManager = getFragmentManager();
                        FragmentTransaction ft = frgManager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment, fragment);
                        ft.commit();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                TextView no_pu = popup_tambah.findViewById(R.id.no_petak_ukur);
                no_pu.setText(pu_id_tallysheet);
                no_pohon = popup_tambah.findViewById(R.id.pu_no_pohon);
                keliling_pohon = popup_tambah.findViewById(R.id.pu_keliling_pohon);
                peninggi_pohon = popup_tambah.findViewById(R.id.pu_peninggi_pohon);
                kualitas_batang = popup_tambah.findViewById(R.id.pu_kualitas_batang);

                builder.setView(popup_tambah);
                builder.setCancelable(false)
                        .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                try {

                                    if (!vallidasi()) {
                                        return;
                                    }

                                        ContentValues xxxx = new ContentValues();
                                        xxxx.put(TrnPuPohon.TS_ID, id_pu);
                                        xxxx.put(TrnPuPohon.NO_POHON, no_pohon.getText().toString());
                                        xxxx.put(TrnPuPohon.KELILING_POHON, keliling_pohon.getText().toString());
                                        xxxx.put(TrnPuPohon.PENINGGI_POHON, peninggi_pohon.getText().toString());
                                        xxxx.put(TrnPuPohon.KUALITAS_BATANG, kualitas_batang.getText().toString());
                                        xxxx.put(TrnPuPohon.KET9, "0");
                                        db.create(TrnPuPohon.TABLE_NAME, xxxx);

                                        Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                        FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                        Fragment fragment = new TabDetailTallySheetFragment();
                                        FragmentTransaction ft = manager.beginTransaction();
                                        ft.replace(R.id.nav_host_fragment, fragment);
                                        ft.commit();
                                }
                                catch (Exception e) {
                                    AjnClass.showAlert(getActivity(), "error \n\n" + e.toString());
                                }


                            }


                        })

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        rv_pengukuran_pohon = list_pu.findViewById(R.id.rv_pengukuran_pohon);
        swipeRefreshLayout = list_pu.findViewById(R.id.swipe_refresh_layout_recycler_view_pengukuran_pohon);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (color > 4) {
                            color = 0;
                        }
                        ls_pu_pohon.clear();
                        try {
                            pengukuranPohonAdapter = new PengukuranPohonAdapter(getActivity(),ls_pu_pohon);
                            rv_pengukuran_pohon.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_pengukuran_pohon.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_pengukuran_pohon.setAdapter(pengukuranPohonAdapter);

                            try {

                                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                                final Cursor cur = db.rawQuery("SELECT " +
                                        " ID" +
                                        " FROM TRN_PU_POHON " +
                                        " WHERE TS_ID = '"+ id_pu + "'" +
                                        " ORDER BY ID DESC", null);

                                cur.moveToPosition(0);
                                dataModels = new ArrayList<>();
                                for (int i = 0; i < cur.getCount(); i++) {
                                    ls_pu_pohon.add(new PuPohonModel(
                                            cur.getString(0)
                                    ));
                                    cur.moveToNext();
                                }

                                cur.close();
                                db.close();

                            } catch (Exception ex) {
                                AjnClass.showAlert(getActivity(), ex.toString());
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        pengukuranPohonAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        final LinearLayout datakosong = list_pu.findViewById(R.id.layout_tidakadadata_pu);

        final int ceksampling = db.cek_jumlah_data(TrnPuPohon.TABLE_NAME);
        if(String.valueOf(ceksampling).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            rv_pengukuran_pohon.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            rv_pengukuran_pohon.setVisibility(View.VISIBLE);
        }

        dataPengukuranPohon();

        return list_pu;
    }

    public void dataPengukuranPohon(){
        ls_pu_pohon = new ArrayList<>();
        try {
            pengukuranPohonAdapter = new PengukuranPohonAdapter(getActivity(),ls_pu_pohon);
            rv_pengukuran_pohon.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_pengukuran_pohon.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_pengukuran_pohon.setAdapter(pengukuranPohonAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        "ID" +
                        " FROM TRN_PU_POHON " +
                        " WHERE TS_ID="+id_pu+
                        " ORDER BY ID DESC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_pu_pohon.add(new PuPohonModel(
                            cur.getString(0)
                    ));
                    cur.moveToNext();
                }

                cur.close();
                db.close();

            } catch (Exception ex) {
                AjnClass.showAlert(getActivity(), ex.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean vallidasi() {
        if (no_pohon.getText().toString().equals("") || no_pohon.getText().toString().equals("0") || no_pohon.getText().toString().equals(" ") || no_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Nomor Pohon harus diisi");
            return false;

        }else if (keliling_pohon.getText().toString().equals("") || keliling_pohon.getText().toString().equals("0") || keliling_pohon.getText().toString().equals(" ") || keliling_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Keliling Pohon harus diisi");
            return false;

        }else if (peninggi_pohon.getText().toString().equals("") || peninggi_pohon.getText().toString().equals("0") || peninggi_pohon.getText().toString().equals(" ") || peninggi_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Peninggi Pohon harus diisi");
            return false;

        }else if (kualitas_batang.getText().toString().equals("") || kualitas_batang.getText().toString().equals("0") || kualitas_batang.getText().toString().equals(" ") || kualitas_batang.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Kualitas Batang harus diisi");
            return false;

        }

        return true;
    }

}
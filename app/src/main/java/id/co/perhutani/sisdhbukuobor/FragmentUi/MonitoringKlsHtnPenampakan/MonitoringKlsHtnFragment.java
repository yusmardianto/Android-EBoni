package id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.MonitoringKlsHtn.MonitoringKlsHtnAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.SusunRisalahAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahViewModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;

public class MonitoringKlsHtnFragment extends Fragment {

    View view_monitoring;
    private RecyclerView rv_monitoring;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<MonitoringKlsHtnViewModel> dataModels;
    private MonitoringKlsHtnAdapter monitoringAdapter;
    private int color = 0;
    private static final int VERTICAL_ITEM_SPACE = 0;

    private SQLiteHandler db;
    private List<MonitoringKlsHtnViewModel> ls_monitoring;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_monitoring = inflater.inflate(R.layout.fragment_monitoring_kls_htn, container, false);

        db = new SQLiteHandler(getActivity());

        rv_monitoring = view_monitoring.findViewById(R.id.monitoring_klshtn_recyclerview);
        swipeRefreshLayout = view_monitoring.findViewById(R.id.swipe_refresh_layout_recycler_view_monitoring);
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
                        ls_monitoring.clear();
                        try {
                            monitoringAdapter = new MonitoringKlsHtnAdapter(getActivity(),ls_monitoring);
                            rv_monitoring.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_monitoring.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_monitoring.setAdapter(monitoringAdapter);

                            try {

                                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                                final Cursor cur = db.rawQuery("SELECT " +
                                        " ID " +
                                        " FROM TRN_SUSUN_RISALAH " +
                                        " WHERE STATUS" + " LIKE " + "5" +
                                        " ORDER BY ID ASC", null);

                                cur.moveToPosition(0);
                                dataModels = new ArrayList<>();
                                for (int i = 0; i < cur.getCount(); i++) {
                                    ls_monitoring.add(new MonitoringKlsHtnViewModel(
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

                        monitoringAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        final EditText txt_searcrisalah = view_monitoring.findViewById(R.id.txt_search_monitoring);
        txt_searcrisalah.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = txt_searcrisalah.getText().toString();
                if (input.equals(null) || input.equals("") || input.equals(" ")) {
                    data_monitoring();
                } else {
                    search_monitoring(input);
                }
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        final LinearLayout datakosong = view_monitoring.findViewById(R.id.monitoring_nodata);

        final int cekdata = db.cek_jumlah_data(TrnSusunRisalah.TABLE_NAME);
        if(String.valueOf(cekdata).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            rv_monitoring.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            rv_monitoring.setVisibility(View.VISIBLE);
        }

        Toolbar toolbar = view_monitoring.findViewById(R.id.toolbar_monitoring);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BukuOborFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        data_monitoring();

        return view_monitoring;
    }

    public void data_monitoring(){

        ls_monitoring = new ArrayList<>();
        try {
            monitoringAdapter = new MonitoringKlsHtnAdapter(getActivity(),ls_monitoring);
            rv_monitoring.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_monitoring.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_monitoring.setAdapter(monitoringAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        " ID " +
                        " FROM TRN_SUSUN_RISALAH " +
                        " WHERE STATUS" + " LIKE " + "5" +
                        " ORDER BY ID ASC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_monitoring.add(new MonitoringKlsHtnViewModel(
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
    public void search_monitoring(String kph){

        ls_monitoring = new ArrayList<>();
        try {
            monitoringAdapter = new MonitoringKlsHtnAdapter(getActivity(),ls_monitoring);
            rv_monitoring.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_monitoring.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_monitoring.setAdapter(monitoringAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        " TRN_SUSUN_RISALAH.ID " +
                        " FROM TRN_SUSUN_RISALAH " +
                        " INNER JOIN MST_ANAK_PETAK" +
                        " ON TRN_SUSUN_RISALAH.ANAK_PETAK_ID = MST_ANAK_PETAK.KODE_ANAKPETAK" +
                        " WHERE MST_ANAK_PETAK.KPH" + " LIKE  " + "'%" + kph + "%'" +
                        " AND TRN_SUSUN_RISALAH.STATUS " + " = " + " 5 " +
                        " ORDER BY TRN_SUSUN_RISALAH.ID ASC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_monitoring.add(new MonitoringKlsHtnViewModel(
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
}
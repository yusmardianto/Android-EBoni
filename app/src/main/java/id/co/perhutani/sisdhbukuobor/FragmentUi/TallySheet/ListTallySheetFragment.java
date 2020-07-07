package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.GangguanAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.SusunRisalahAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.TallySheetAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.WorkOrder.WorkOrderAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.tambahgangguan.TambahGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderViewModel;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;


public class ListTallySheetFragment extends Fragment {

    View list_tally_sheet;
    private RecyclerView rv_tallysheet;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<TallySheetModel> dataModels;
    private TallySheetAdapter tallySheetAdapter;
    private static final int VERTICAL_ITEM_SPACE = 0;
    private SessionManager session;

    private int color = 0;
    private SQLiteHandler db;
    private List<TallySheetModel> ls_tally_sheet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        list_tally_sheet = inflater.inflate(R.layout.fragment_list_tally_sheet, container, false);
        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
//        session.setPreferences(getActivity(), "ses_id_tallysheet", null);
//

//        ImageView imgTambahGangguan = list_tally_sheet.findViewById(R.id.create_tally_sheet);
//        imgTambahGangguan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new CreateTallySheetFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });

        rv_tallysheet = list_tally_sheet.findViewById(R.id.rv_tally_sheet);
        swipeRefreshLayout = list_tally_sheet.findViewById(R.id.swipe_refresh_layout_recycler_view_tallysheet);
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
                        ls_tally_sheet.clear();
                        try {
                            tallySheetAdapter = new TallySheetAdapter(getActivity(),ls_tally_sheet);
                            rv_tallysheet.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_tallysheet.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_tallysheet.setAdapter(tallySheetAdapter);

                            try {

                                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                                final Cursor cur = db.rawQuery("SELECT " +
                                        " ID" +
                                        " FROM TRN_TALLY_SHEET " +
                                        " ORDER BY ID DESC", null);

                                cur.moveToPosition(0);
                                dataModels = new ArrayList<>();
                                for (int i = 0; i < cur.getCount(); i++) {
                                    ls_tally_sheet.add(new TallySheetModel(
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

                        tallySheetAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        final LinearLayout datakosong = list_tally_sheet.findViewById(R.id.layout_tidakadadata_ts);
        final RecyclerView dataada = list_tally_sheet.findViewById(R.id.rv_tally_sheet);

        final int ceksampling = db.cek_jumlah_data(TrnTallySheet.TABLE_NAME);
        if(String.valueOf(ceksampling).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            dataada.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            dataada.setVisibility(View.VISIBLE);
        }


        dataTallysheet();

        final EditText txt_searchworkorder = list_tally_sheet.findViewById(R.id.txt_search_ts);
        txt_searchworkorder.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = txt_searchworkorder.getText().toString();
                if (input.equals(null) || input.equals("") || input.equals(" ")) {
                    dataTallysheet();
                } else {
                    dataTallysheet_search(input);
                }
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        return list_tally_sheet;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void dataTallysheet(){

        ls_tally_sheet = new ArrayList<>();
        try {
            tallySheetAdapter = new TallySheetAdapter(getActivity(),ls_tally_sheet);
            rv_tallysheet.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_tallysheet.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_tallysheet.setAdapter(tallySheetAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        "ID" +
                        " FROM TRN_TALLY_SHEET " +
                        " ORDER BY ID DESC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_tally_sheet.add(new TallySheetModel(
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


    public void dataTallysheet_search(String kph){

        ls_tally_sheet = new ArrayList<>();
        try {
            tallySheetAdapter = new TallySheetAdapter(getActivity(),ls_tally_sheet);
            rv_tallysheet.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_tallysheet.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_tallysheet.setAdapter(tallySheetAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        "ID" +
                        " FROM TRN_TALLY_SHEET " +
                        " WHERE KPH_NAME " + " LIKE  " + "'%" + kph + "%'" +
                        " ORDER BY ID DESC", null);


                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_tally_sheet.add(new TallySheetModel(
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
package id.co.perhutani.sisdhbukuobor.FragmentUi.workorder;

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

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.Persemaian.PersemaianMenuAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.WorkOrder.WorkOrderAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.DashboardBukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianViewModel;
import id.co.perhutani.sisdhbukuobor.R;


public class WorkOrderFragment extends Fragment {

    View view_workorder;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rv_workorder;
    private WorkOrderAdapter workorderAdapter;
    ArrayList<WorkOrderViewModel> dataModels;
    private int color = 0;
    private static final int VERTICAL_ITEM_SPACE = 0;

    private List<WorkOrderViewModel> lsworkorder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_workorder = inflater.inflate(R.layout.fragment_work_order, container, false);

        rv_workorder = view_workorder.findViewById(R.id.workorder_recyclerview);
        swipeRefreshLayout = view_workorder.findViewById(R.id.swipe_refresh_layout_recycler_view_workorder);
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
                        lsworkorder.clear();
                        try {
                            workorderAdapter = new WorkOrderAdapter(getActivity(),lsworkorder);
                            rv_workorder.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_workorder.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_workorder.setAdapter(workorderAdapter);

                            try {

                                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                                final Cursor cur = db.rawQuery("SELECT " +
                                        " ID ,WORKORDER,TANGGAL,JENISKEGIATAN, DARI" +
                                        " FROM TRN_WORK_ORDER " +
                                        " ORDER BY ID ASC", null);

                                cur.moveToPosition(0);
                                dataModels = new ArrayList<>();
                                for (int i = 0; i < cur.getCount(); i++) {
                                    lsworkorder.add(new WorkOrderViewModel(
                                            cur.getString(0),
                                            cur.getString(1),
                                            cur.getString(2),
                                            cur.getString(3),
                                            cur.getString(4)
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

                        workorderAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        Toolbar toolbar = view_workorder.findViewById(R.id.toolbar_workorder);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DashboardBukuOborFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        dataWorkorder();

        final EditText txt_searchworkorder = view_workorder.findViewById(R.id.txt_search_workorder);
        txt_searchworkorder.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = txt_searchworkorder.getText().toString();
                if (input.equals(null) || input.equals("") || input.equals(" ")) {
                    dataWorkorder();
                } else {
                    search_workorder(input);
                }
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        return view_workorder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void dataWorkorder(){

        lsworkorder = new ArrayList<>();
        try {
            workorderAdapter = new WorkOrderAdapter(getActivity(),lsworkorder);
            rv_workorder.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_workorder.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_workorder.setAdapter(workorderAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        " ID ,WORKORDER,JENISKEGIATAN,TANGGAL, DARI" +
                        " FROM TRN_WORK_ORDER " +
                        " ORDER BY ID ASC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    lsworkorder.add(new WorkOrderViewModel(
                            cur.getString(0),
                            cur.getString(1),
                            cur.getString(2),
                            cur.getString(3),
                            cur.getString(4)
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

    public void search_workorder(String workorder){

        lsworkorder = new ArrayList<>();
        try {
            workorderAdapter = new WorkOrderAdapter(getActivity(),lsworkorder);
            rv_workorder.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_workorder.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_workorder.setAdapter(workorderAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        " ID ,WORKORDER,JENISKEGIATAN,TANGGAL, DARI" +
                        " FROM TRN_WORK_ORDER " +
                        " WHERE WORKORDER " + " LIKE  " + "'%" + workorder + "%'" +
                        " ORDER BY ID ASC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    lsworkorder.add(new WorkOrderViewModel(
                            cur.getString(0),
                            cur.getString(1),
                            cur.getString(2),
                            cur.getString(3),
                            cur.getString(4)
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

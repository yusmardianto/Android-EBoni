package id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.SusunRisalahAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.R;


public class SudahAccFragment extends Fragment {

    View view_risalah_sudahacc;
    private RecyclerView rv_risalah_blmacc;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<SusunRisalahViewModel> dataModels;
    private SusunRisalahAdapter susunRisalahAdapter;
    private int color = 0;
    private static final int VERTICAL_ITEM_SPACE = 0;

    private List<SusunRisalahViewModel> ls_risalah_blmacc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_risalah_sudahacc = inflater.inflate(R.layout.fragment_sudah_acc, container, false);

        rv_risalah_blmacc = view_risalah_sudahacc.findViewById(R.id.risalah_blmacc_recyclerview);
        swipeRefreshLayout = view_risalah_sudahacc.findViewById(R.id.swipe_refresh_layout_recycler_view_risalah_blmacc);
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
                        ls_risalah_blmacc.clear();
                        try {
                            susunRisalahAdapter = new SusunRisalahAdapter(getActivity(),ls_risalah_blmacc);
                            rv_risalah_blmacc.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_risalah_blmacc.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_risalah_blmacc.setAdapter(susunRisalahAdapter);

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
                                    ls_risalah_blmacc.add(new SusunRisalahViewModel(
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

                        susunRisalahAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        final EditText txt_searcrisalah = view_risalah_sudahacc.findViewById(R.id.txt_search_risalah_sudahacc);
        txt_searcrisalah.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = txt_searcrisalah.getText().toString();
                if (input.equals(null) || input.equals("") || input.equals(" ")) {
                    data_sudahacc();
                } else {
                    search_susunrisalah(input);
                }
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        data_sudahacc();

        return view_risalah_sudahacc;
    }

    public void data_sudahacc(){

        ls_risalah_blmacc = new ArrayList<>();
        try {
            susunRisalahAdapter = new SusunRisalahAdapter(getActivity(),ls_risalah_blmacc);
            rv_risalah_blmacc.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_risalah_blmacc.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_risalah_blmacc.setAdapter(susunRisalahAdapter);

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
                    ls_risalah_blmacc.add(new SusunRisalahViewModel(
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
    public void search_susunrisalah(String kph){

        ls_risalah_blmacc = new ArrayList<>();
        try {
            susunRisalahAdapter = new SusunRisalahAdapter(getActivity(),ls_risalah_blmacc);
            rv_risalah_blmacc.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_risalah_blmacc.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_risalah_blmacc.setAdapter(susunRisalahAdapter);

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
                    ls_risalah_blmacc.add(new SusunRisalahViewModel(
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

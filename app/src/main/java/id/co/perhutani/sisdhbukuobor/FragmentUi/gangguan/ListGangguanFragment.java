package id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.GangguanAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.tambahgangguan.TambahGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;

public class ListGangguanFragment extends Fragment
{
//    View v ;
    private static RecyclerView myrecyclerview;
    private static ArrayList<GangguanModel> dataModels;
    private static List<GangguanModel> lsgangguan;
    private static GangguanAdapter gAdapter;
    private static Context context;
    private SQLiteHandler db;


    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListGangguanFragment newInstance() { return new ListGangguanFragment(); }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (isOnline()) {
                    refresh_list();
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.gangguan_fragment, container, false);
        myrecyclerview = root.findViewById(R.id.gangguan_recycler);
        init();
        context=getActivity();
        db = new SQLiteHandler(getActivity());

        //call timer
        handler.postDelayed(runnable, 1000);


        ImageView imgTambahGangguan = (ImageView) root.findViewById(R.id.img_tambahgangguan);
        imgTambahGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahGangguanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        final EditText txt_searchgangguan = root.findViewById(R.id.txt_search_gangguan);
        txt_searchgangguan.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchgangguan.getText().toString();
                if (input.equals(null)||input.equals("")||input.equals(" ")) {
                    def();
                }else {
                    refresh(input);
                }
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadatagangguan);
        final RecyclerView dataada = root.findViewById(R.id.gangguan_recycler);

        final int ceksampling = db.cek_jumlah_data(TrnGangguanKeamananHutan.TABLE_NAME);
        if(String.valueOf(ceksampling).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            dataada.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            dataada.setVisibility(View.VISIBLE);
        }

        return root;
    }

    public void refresh(String kejadian) {
            lsgangguan = new ArrayList<>();
            try {
                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        "ID, KEJADIAN, ANAKPETAK_ID, ID, ID , TANGGAL, ID, ID, ID, ID, ID, ID, ID, KET1, ID, KET9, ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                        " FROM TRN_GANGGUAN_HUTAN " +
                        " WHERE KET3 " + " LIKE  " + "'%" + kejadian + "%'" +
                        " ORDER BY ID DESC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    lsgangguan.add(new GangguanModel(
                            cur.getString(0),
                            cur.getString(1),
                            cur.getString(2),
                            cur.getString(3),
                            cur.getString(4),
                            cur.getString(5),
                            cur.getString(6),
                            cur.getString(7),
                            cur.getString(8),
                            cur.getString(9),
                            cur.getString(10),
                            cur.getString(11),
                            cur.getString(12),
                            cur.getString(13),
                            Integer.parseInt(cur.getString(0)),
                            cur.getString(14),
                            cur.getString(15)
                    ));
                    cur.moveToNext();
                }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void init() {
        try {
            gAdapter = new GangguanAdapter(getContext(),lsgangguan);
            myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecyclerview .setAdapter(gAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lsgangguan = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, KEJADIAN, ANAKPETAK_ID, ID, ID , TANGGAL, ID, ID, ID, ID, ID, ID, ID, KET1, ID, KET9, ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lsgangguan.add(new GangguanModel(
                        cur.getString(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6),
                        cur.getString(7),
                        cur.getString(8),
                        cur.getString(9),
                        cur.getString(10),
                        cur.getString(11),
                        cur.getString(12),
                        cur.getString(13),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(14),
                        cur.getString(15)
                        ));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }
    public static void refresh_list(){

        lsgangguan = new ArrayList<>();
        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, KEJADIAN, ANAKPETAK_ID, ID, ID , TANGGAL, ID, ID, ID, ID, ID, ID, ID, KET1, ID, KET9, ID" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lsgangguan.add(new GangguanModel(
                        cur.getString(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6),
                        cur.getString(7),
                        cur.getString(8),
                        cur.getString(9),
                        cur.getString(10),
                        cur.getString(11),
                        cur.getString(12),
                        cur.getString(13),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(14),
                        cur.getString(15)
                ));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        gAdapter = new GangguanAdapter(context,lsgangguan);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        gAdapter.notifyDataSetChanged();
        myrecyclerview.invalidate();
        myrecyclerview.setAdapter(gAdapter);

//        Toast.makeText(context, "Sync data..!", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}

package id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.PelaporanpalbatasAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.tambahlaporanpalbatas.TambahlaporanpalbatasFragment;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;

public class ListPelaporanpalFragment extends Fragment
{
    private static RecyclerView myrecylcerview;
    private static ArrayList<PelaporanpalbatasModel> DataPelaporan ;
    private static List<PelaporanpalbatasModel> lstpelaporanpal;
    private static PelaporanpalbatasAdapter pAdapter;
    private static Context context;
    private SQLiteHandler db;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListPelaporanpalFragment newInstance()
    {
        return new ListPelaporanpalFragment();
    }

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
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.laporan_pal_batas_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.pelaporanpal_recycler);
        PelaporanpalbatasAdapter gAdapter = new PelaporanpalbatasAdapter(getContext(),lstpelaporanpal);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecylcerview .setAdapter(gAdapter);
        init();

        context=getActivity();
        db = new SQLiteHandler(getActivity());

        //call timer
//        handler.postDelayed(runnable, 1000);

        ImageView imgTambahPelaporanpal = (ImageView) root.findViewById(R.id.img_tambahlaporanpal);
        imgTambahPelaporanpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahlaporanpalbatasFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchlaporanpal = root.findViewById(R.id.txt_search_laporanpal);
        txt_searchlaporanpal.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchlaporanpal.getText().toString();
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

        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadatalaporanpal);
        final RecyclerView dataada = root.findViewById(R.id.pelaporanpal_recycler);

        final int ceksampling = db.cek_jumlah_data(TrnLaporanPalBatas.TABLE_NAME);
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

    public void refresh(String laporanpal) {
        lstpelaporanpal = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, TANGGAL, JENIS_PAL, ID, NO_PAL, JUMLAH_PAL, ID, ID, ID, ID, ID" +
//                    " DISTINCT(ID)" +
                    " FROM TRN_LAPORAN_PAL " +
                    " WHERE NO_PAL " + " LIKE  " + "'%" + laporanpal + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataPelaporan = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpelaporanpal.add(new PelaporanpalbatasModel(
                        cur.getString(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6),
                        cur.getString(7),
                        cur.getString(8),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(9),
                        cur.getString(10)));
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
            pAdapter = new PelaporanpalbatasAdapter(getContext(),lstpelaporanpal);
            myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecylcerview .setAdapter(pAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lstpelaporanpal = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, TANGGAL, JENIS_PAL, ID, NO_PAL, JUMLAH_PAL, ID, ID, ID, ID, ID" +
//                    " DISTINCT(ID)" +
                    " FROM TRN_LAPORAN_PAL " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataPelaporan = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpelaporanpal.add(new PelaporanpalbatasModel(
                        cur.getString(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6),
                        cur.getString(7),
                        cur.getString(8),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(9),
                        cur.getString(10)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public static void refresh_list(){
        lstpelaporanpal = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, TANGGAL, JENIS_PAL, ID, NO_PAL, JUMLAH_PAL, ID, ID, ID, ID, ID" +
//                    " DISTINCT(ID)" +
                    " FROM TRN_LAPORAN_PAL " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataPelaporan = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpelaporanpal.add(new PelaporanpalbatasModel(
                        cur.getString(0),
                        cur.getString(1),
                        cur.getString(2),
                        cur.getString(3),
                        cur.getString(4),
                        cur.getString(5),
                        cur.getString(6),
                        cur.getString(7),
                        cur.getString(8),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(9),
                        cur.getString(10)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        pAdapter = new PelaporanpalbatasAdapter(context,lstpelaporanpal);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(context));
        myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        pAdapter.notifyDataSetChanged();
        myrecylcerview.invalidate();
        myrecylcerview.setAdapter(pAdapter);

//        Toast.makeText(context, "Sync data..!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}

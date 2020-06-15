package id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas;

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

import id.co.perhutani.sisdhbukuobor.Adapter.PerubahankelasAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.tambahperubahan.TambahPerubahanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;

public class ListPerubahanKelasFragment extends Fragment
{
    //View v;
    private static RecyclerView recylcerview;
    private static ArrayList<PerubahankelasModel> DataModel;
    private static List<PerubahankelasModel>lstperubahankls;
    private static PerubahankelasAdapter pkAdapter;
    private static Context context;
    private SQLiteHandler db;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListPerubahanKelasFragment newInstance()
    {
        return new ListPerubahanKelasFragment();
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
        View root = inflater.inflate(R.layout.perubahan_kelas_fragment, container, false);
        recylcerview = root.findViewById(R.id.perubahankls_recycler);
        pkAdapter = new PerubahankelasAdapter(getContext(),lstperubahankls);
        recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylcerview .setAdapter(pkAdapter);

        context=getActivity();
        db = new SQLiteHandler(getActivity());
        init();

        handler.postDelayed(runnable, 1000);

        ImageView imgTambahPerubahankls = (ImageView) root.findViewById(R.id.img_tambahperubahan);
        imgTambahPerubahankls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahPerubahanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchperubahan = root.findViewById(R.id.txt_search_perubahan);
        txt_searchperubahan.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchperubahan.getText().toString();
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

        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadataperubahan);
        final RecyclerView dataada = root.findViewById(R.id.perubahankls_recycler);

        final int cekdata = db.cek_jumlah_data(TrnPerubahanKelas.TABLE_NAME);
        if(String.valueOf(cekdata).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            dataada.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            dataada.setVisibility(View.VISIBLE);
        }


        return root;
    }

    public void refresh(String perubahan) {
        lstperubahankls = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, PETAK_ID, TAHUN, ID, JENIS_TANAMAN, KELAS_HUTAN, ID, ID, ID, ID, ID, ID, ID, KET1, KET2, KET3, KET4, KET5, KET6, KET7, ID, KET9, KET10, ID" +
//                    " DISTINCT(ANAK_PETAK_ID_PERUBAHAN)" +
                    " FROM TRN_PERUBAHAN_KELAS  " +
                    " WHERE PETAK_ID " + " LIKE  " + "'%" + perubahan + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstperubahankls.add(new PerubahankelasModel(
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
                        cur.getString(14),
                        cur.getString(15),
                        cur.getString(16),
                        cur.getString(17),
                        cur.getString(18),
                        cur.getString(19),
                        cur.getString(20),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(21),
                        cur.getString(22),
                        cur.getString(23)
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
            pkAdapter = new PerubahankelasAdapter(getContext(),lstperubahankls);
            recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            recylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            recylcerview .setAdapter(pkAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lstperubahankls = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, PETAK_ID, TAHUN, ID, JENIS_TANAMAN, KELAS_HUTAN, ID, ID, ID, ID, ID, ID, ID, KET1, KET2, KET3, KET4, KET5, KET6, KET7, ID, KET9, KET10, ID" +
//                    " DISTINCT(ANAK_PETAK_ID_PERUBAHAN)" +
                    " FROM TRN_PERUBAHAN_KELAS " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstperubahankls.add(new PerubahankelasModel(
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
                        cur.getString(14),
                        cur.getString(15),
                        cur.getString(16),
                        cur.getString(17),
                        cur.getString(18),
                        cur.getString(19),
                        cur.getString(20),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(21),
                        cur.getString(22),
                        cur.getString(23)
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
        lstperubahankls = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, PETAK_ID, TAHUN, ID, JENIS_TANAMAN, KELAS_HUTAN, ID, ID, ID, ID, ID, ID, ID, KET1, KET2, KET3, KET4, KET5, KET6, KET7, ID, KET9, KET10, ID" +
//                    " DISTINCT(ANAK_PETAK_ID_PERUBAHAN)" +
                    " FROM TRN_PERUBAHAN_KELAS " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstperubahankls.add(new PerubahankelasModel(
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
                        cur.getString(14),
                        cur.getString(15),
                        cur.getString(16),
                        cur.getString(17),
                        cur.getString(18),
                        cur.getString(19),
                        cur.getString(20),
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(21),
                        cur.getString(22),
                        cur.getString(23)
                ));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        pkAdapter = new PerubahankelasAdapter(context,lstperubahankls);
        recylcerview.setLayoutManager(new LinearLayoutManager(context));
        recylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        pkAdapter.notifyDataSetChanged();
        recylcerview.invalidate();
        recylcerview.setAdapter(pkAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}

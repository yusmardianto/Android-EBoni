package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.InteraksimdhAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.tambahinteraksimdh.TambahInteraksimdhFragment;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.R;


public class ListInteraksiMDHFragment extends Fragment {

    private static RecyclerView myrecylcerview;
    private static ArrayList<InteraksimdhModel> DataModel;
    private static List<InteraksimdhModel> lstinteraksi;
    private static InteraksimdhAdapter imdhAdapter;
    private static Context context;
    private static final int VERTICAL_ITEM_SPACE = 0;
    private SQLiteHandler db;

    public static ListInteraksiMDHFragment newInstance() {

        return new ListInteraksiMDHFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.interaksi_mdh_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.interaksimdh_recycler);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        init();

        ImageView imgTambahInteraksi = root.findViewById(R.id.tambah_interaksimdh);
        imgTambahInteraksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahInteraksimdhFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchinteraksi = root.findViewById(R.id.txt_search_interaksi);
        txt_searchinteraksi.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchinteraksi.getText().toString();
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

//        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadatainteraksimdh);
//        final RecyclerView dataada = root.findViewById(R.id.interaksimdh_recycler);
//
//        final int ceksampling = db.cek_jumlah_data(TrnInteraksimdh.TABLE_NAME);
//        if(String.valueOf(ceksampling).equals("0"))
//        {
//            datakosong.setVisibility(View.VISIBLE);
//            dataada.setVisibility(View.GONE);
//        }else {
//            datakosong.setVisibility(View.GONE);
//            dataada.setVisibility(View.VISIBLE);
//        }

        return root;
    }

    public void refresh(String interaksi) {
        lstinteraksi = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, NAMA_DESA, BENTUK_INTERAKSI, TAHUN, STATUS, KET, KET1, KET2, KET3, KET4, KET5, ID, KET9, KET10" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_INTERAKSI_MDH " +
                    " WHERE ANAK_PETAK_ID " + " LIKE  " + "'%" + interaksi + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstinteraksi.add(new InteraksimdhModel(
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
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(15),
                        cur.getString(16)));
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
            imdhAdapter = new InteraksimdhAdapter(getContext(), lstinteraksi);
            myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecylcerview.setAdapter(imdhAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def() {
        lstinteraksi = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, NAMA_DESA, BENTUK_INTERAKSI, TAHUN, STATUS, KET, KET1, KET2, KET3, KET4, KET5, ID, KET9, KET10" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_INTERAKSI_MDH " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstinteraksi.add(new InteraksimdhModel(
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
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(15),
                        cur.getString(16)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public static void refresh_list() {
        lstinteraksi = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, NAMA_DESA, BENTUK_INTERAKSI, TAHUN, STATUS, KET, KET1, KET2, KET3, KET4, KET5, ID, KET9, KET10" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_INTERAKSI_MDH " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstinteraksi.add(new InteraksimdhModel(
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
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(15),
                        cur.getString(16)));
                cur.moveToNext();
            }
            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        imdhAdapter = new InteraksimdhAdapter(context,lstinteraksi);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(context));
        myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        imdhAdapter.notifyDataSetChanged();
        myrecylcerview.invalidate();
        myrecylcerview.setAdapter(imdhAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}

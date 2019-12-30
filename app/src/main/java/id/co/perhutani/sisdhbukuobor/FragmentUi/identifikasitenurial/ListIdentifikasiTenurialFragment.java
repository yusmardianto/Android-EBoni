package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial;

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

import id.co.perhutani.sisdhbukuobor.Adapter.IdentifikasiTenurialAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.tambahidentifikasitenurial.TambahIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.R;

public class ListIdentifikasiTenurialFragment extends Fragment
{
    //View v;
    private static RecyclerView myrecylcerview;
    private static ArrayList<IdentifikasiTenurialModel> DataModel;
    private static List<IdentifikasiTenurialModel> lstenurial;
    private static IdentifikasiTenurialAdapter itAdapter;
    private static Context context;
    private static final int VERTICAL_ITEM_SPACE = 0;
    private SQLiteHandler db;

    public static ListIdentifikasiTenurialFragment newInstance()
    {
        return new ListIdentifikasiTenurialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.identifikasi_tenurial_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.identifikasitenurial_recycler);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        init();

        ImageView imgTambahTenurial = (ImageView) root.findViewById(R.id.img_tambahtenurial);
        imgTambahTenurial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahIdentifikasiTenurialFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchtenurial = root.findViewById(R.id.txt_search_tenurial);
        txt_searchtenurial.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchtenurial.getText().toString();
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

//        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadataidentifikasitenurial);
//        final RecyclerView dataada = root.findViewById(R.id.identifikasitenurial_recycler);
//
//        final int ceksampling = db.cek_jumlah_data(TrnIdentifikasiTenurial.TABLE_NAME);
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

    public void refresh(String tenurial) {
        lstenurial = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, TANGGAL, JENIS_PERMASALAHAN, ID, ANAK_PETAK_ID, ID, ID, ID, ID, AWAL_KONFLIK, ID, ID, ID, ID, ID, ID  " +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
                    " WHERE ANAK_PETAK_ID " + " LIKE  " + "'%" + tenurial + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstenurial.add(new IdentifikasiTenurialModel(
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
                        cur.getString(12),
                        cur.getString(13),
                        cur.getString(14)));
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
            itAdapter = new IdentifikasiTenurialAdapter(getContext(),lstenurial);
            myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecylcerview .setAdapter(itAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lstenurial = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, TANGGAL, JENIS_PERMASALAHAN, ID, ANAK_PETAK_ID, ID, ID, ID, ID, AWAL_KONFLIK, ID, ID, ID, ID, ID, ID  " +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstenurial.add(new IdentifikasiTenurialModel(
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
                        cur.getString(12),
                        cur.getString(13),
                        cur.getString(14)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public static void refresh_list(){
        lstenurial = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, TANGGAL, JENIS_PERMASALAHAN, ID, ANAK_PETAK_ID, ID, ID, ID, ID, AWAL_KONFLIK, ID, ID, ID, ID, ID, ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstenurial.add(new IdentifikasiTenurialModel(
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
                        cur.getString(12),
                        cur.getString(13),
                        cur.getString(14)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        itAdapter = new IdentifikasiTenurialAdapter(context,lstenurial);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(context));
        myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        itAdapter.notifyDataSetChanged();
        myrecylcerview.invalidate();
        myrecylcerview.setAdapter(itAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}


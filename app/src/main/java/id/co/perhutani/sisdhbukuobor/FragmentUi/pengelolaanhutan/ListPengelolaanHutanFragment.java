package id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import id.co.perhutani.sisdhbukuobor.Adapter.PengelolaanHutanAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.tambahpengelolaanhutan.TambahPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PengelolaanHutanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;

public class ListPengelolaanHutanFragment extends Fragment {

    private PengelolaanHutanViewModel mViewModel;
    private static RecyclerView myrecyclerview;
    private static ArrayList<PengelolaanHutanModel> dataModels;
    private static List<PengelolaanHutanModel> lspengelolaan;
    private static PengelolaanHutanAdapter PHAdapter;
    private static Context context;
    private SQLiteHandler db;
    private static final int VERTICAL_ITEM_SPACE = 0;

    public static ListPengelolaanHutanFragment newInstance() {
        return new ListPengelolaanHutanFragment();
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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.pengelolaan_hutan_fragment, container, false);
        myrecyclerview = root.findViewById(R.id.pengelolaanhutan_recycler);

        ImageView imgTambahPengelolaan = (ImageView) root.findViewById(R.id.img_tambahpengelolaanhutan);
        imgTambahPengelolaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahPengelolaanHutanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchpengelolaan = root.findViewById(R.id.txt_search_pengelolaanhutan);
        txt_searchpengelolaan.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchpengelolaan.getText().toString();
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

//        final LinearLayout datakosong = root.findViewById(R.id.layout_tidakadadatapengelolaan);
//        final RecyclerView dataada = root.findViewById(R.id.gangguan_recycler);
//
//        final int ceksampling = db.cek_jumlah_data(TrnPengelolaanHutan.TABLE_NAME);
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

    public void refresh(String pekerjaan) {
        lspengelolaan = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, JENIS_PEKERJAAN_ID, SUB_PEKERJAAN_ID, TANGGAL, LOKASI_PEKERJAAN, RENCANA, REALISASI, STATUS, " +
                    "KETERANGAN, KET1, KET2, KET3, KET4, KET9, KET10, ID" +
                    " FROM TRN_PENGELOLAAN_HUTAN " +
                    " WHERE KET1 " + " LIKE  " + "'%" + pekerjaan + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lspengelolaan.add(new PengelolaanHutanModel(
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
                        Integer.parseInt(cur.getString(0))
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
            PHAdapter = new PengelolaanHutanAdapter(getContext(),lspengelolaan);
            myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecyclerview .setAdapter(PHAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lspengelolaan = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, JENIS_PEKERJAAN_ID, SUB_PEKERJAAN_ID, TANGGAL, LOKASI_PEKERJAAN, RENCANA, REALISASI, STATUS, " +
                    "KETERANGAN, KET1, KET2, KET3, KET4, KET9, KET10, ID" +
                    " FROM TRN_PENGELOLAAN_HUTAN " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lspengelolaan.add(new PengelolaanHutanModel(
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
                        Integer.parseInt(cur.getString(0))
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

        lspengelolaan = new ArrayList<>();
        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    "ID, JENIS_PEKERJAAN_ID, SUB_PEKERJAAN_ID, TANGGAL, LOKASI_PEKERJAAN, RENCANA, REALISASI, STATUS, " +
                    "KETERANGAN, KET1, KET2, KET3, KET4, KET9, KET10, ID" +
                    " FROM TRN_PENGELOLAAN_HUTAN " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lspengelolaan.add(new PengelolaanHutanModel(
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
                        Integer.parseInt(cur.getString(0))
                ));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        PHAdapter = new PengelolaanHutanAdapter(context,lspengelolaan);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(context));
        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        PHAdapter.notifyDataSetChanged();
        myrecyclerview.invalidate();
        myrecyclerview.setAdapter(PHAdapter);

//        Toast.makeText(context, "Sync data..!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PengelolaanHutanViewModel.class);
        // TODO: Use the ViewModel
    }

}

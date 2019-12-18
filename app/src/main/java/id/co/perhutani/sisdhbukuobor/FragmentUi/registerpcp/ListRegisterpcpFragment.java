package id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp;
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

import id.co.perhutani.sisdhbukuobor.Adapter.RegisterpcpAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.tambahregisterpcp.TambahRegisterpcpFragment;

public class ListRegisterpcpFragment extends Fragment
{
    //View v;
    private static RecyclerView recylcerview;
    private static ArrayList<RegisterpcpModel> DataModel;
    private static List<RegisterpcpModel>lstregisterpcp;
    private static RegisterpcpAdapter rpAdapter;
    private static Context context;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListRegisterpcpFragment newInstance(){
        return new ListRegisterpcpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.register_pcp_fragment, container, false);
        recylcerview = root.findViewById(R.id.registerpcp_recycler);
        recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylcerview .setAdapter(rpAdapter);
        init();


        ImageView imgRegisterpcp = (ImageView) root.findViewById(R.id.img_tambahpcp);
        imgRegisterpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahRegisterpcpFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchregister = root.findViewById(R.id.txt_search_register);
        txt_searchregister.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchregister.getText().toString();
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

        return root;
    }

    public void refresh(String registerpcp) {
        lstregisterpcp = new ArrayList<>();
        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, NOMOR_PCP, ANAK_PETAK_ID, TAHUN, ID, ID, ID, ID, BONITA, ID, ID, ID , ID , ID, ID, ID, ID, ID" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_REGISTER_PCP " +
                    " WHERE NOMOR_PCP " + " LIKE  " + "'%" + registerpcp + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstregisterpcp.add(new RegisterpcpModel(
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
                        Integer.parseInt(cur.getString(0))));
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
            rpAdapter = new RegisterpcpAdapter(getContext(),lstregisterpcp);
            recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            recylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            recylcerview .setAdapter(rpAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lstregisterpcp = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, NOMOR_PCP, ANAK_PETAK_ID, TAHUN, ID, ID, ID, ID, BONITA, ID, ID, ID , ID , ID, ID, ID, ID, ID" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_REGISTER_PCP " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstregisterpcp.add(new RegisterpcpModel(
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
                        Integer.parseInt(cur.getString(0))));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public static void refresh_list()
    {
        lstregisterpcp = new ArrayList<>();

        try {

            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, NOMOR_PCP, ANAK_PETAK_ID, TAHUN, ID, ID, ID, ID, BONITA, ID, ID, ID , ID , ID, ID, ID, ID, ID" +
//                    " DISTINCT(ANAK_PETAK_ID)" +
                    " FROM TRN_REGISTER_PCP " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstregisterpcp.add(new RegisterpcpModel(
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
                        Integer.parseInt(cur.getString(0))));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        rpAdapter = new RegisterpcpAdapter(context,lstregisterpcp);
        recylcerview.setLayoutManager(new LinearLayoutManager(context));
        recylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        rpAdapter.notifyDataSetChanged();
        recylcerview.invalidate();
        recylcerview.setAdapter(rpAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();

        context = getActivity();
    }
}

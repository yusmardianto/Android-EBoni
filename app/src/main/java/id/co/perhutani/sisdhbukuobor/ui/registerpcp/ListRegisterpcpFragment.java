package id.co.perhutani.sisdhbukuobor.ui.registerpcp;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.ui.registerpcp.tambahregisterpcp.TambahRegisterpcpFragment;

public class ListRegisterpcpFragment extends Fragment
{
    //View v;
    private RecyclerView recylcerview;
    private ArrayList<RegisterpcpModel> DataModel;
    private List<RegisterpcpModel>lstregisterpcp;
    RegisterpcpAdapter rpAdapter;

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

        return root;
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
                    " ID, NOMOR_PC, ANAK_PETAK_ID, TAHUN, ID, ID, ID, ID, BONITA, ID, ID, ID , ID , ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
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
                        cur.getString(13)));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
    }
}

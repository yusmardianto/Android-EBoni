package id.co.perhutani.sisdhbukuobor.ui.gangguan;

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

import id.co.perhutani.sisdhbukuobor.Adapter.GangguanAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan.TambahGangguanFragment;

public class ListGangguanFragment extends Fragment
{
//    View v ;
    private RecyclerView myrecyclerview;
    private ArrayList<GangguanModel> dataModels;
    private List<GangguanModel> lsgangguan;
    GangguanAdapter gAdapter;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListGangguanFragment newInstance() { return new ListGangguanFragment(); }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.gangguan_fragment, container, false);
        myrecyclerview = root.findViewById(R.id.gangguan_recycler);
        init();



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

        return root;
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
                    " *" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            dataModels = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lsgangguan.add(new GangguanModel(
                        cur.getString(0),
                        cur.getString(0),
                        cur.getString(0),
                        cur.getString(0)));
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

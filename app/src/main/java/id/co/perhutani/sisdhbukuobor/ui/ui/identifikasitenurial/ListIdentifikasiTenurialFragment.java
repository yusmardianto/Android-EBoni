//package id.co.perhutani.sisdhbukuobor.ui.ui.identifikasitenurial;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import id.co.perhutani.sisdhbukuobor.Adapter.IdentifikasiTenurialAdapter;
//import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
//import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
//import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
//import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
//import id.co.perhutani.sisdhbukuobor.R;
//import id.co.perhutani.sisdhbukuobor.ui.IdentifikasiTenurial.TambahIdentifikasiTenurialFragment;
//import id.co.perhutani.sisdhbukuobor.ui.VerticalSpaceItemDecoration;
//
//public class ListIdentifikasiTenurialFragment {
//
//    private static RecyclerView recylcerview;
//    private static ArrayList<IdentifikasiTenurialModel> DataModel;
//    private static List<IdentifikasiTenurialModel> lsttenurial;
//    private static IdentifikasiTenurialAdapter itAdapter;
//    private static Context context;
//    private static final int VERTICAL_ITEM_SPACE = 0;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState)
//    {
//        View root = inflater.inflate(R.layout.identifikasi_tenurial_fragment, container, false);
//        recylcerview = root.findViewById(R.id.identifikasitenurial_recycler);
//        recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recylcerview .setAdapter(itAdapter);
//        init();
//
//
//        ImageView imgtenurial = (ImageView) root.findViewById(R.id.img_tambahtenurial);
//        imgtenurial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new TambahIdentifikasiTenurialFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//
//        return root;
//    }
//
//    public void init() {
//        try {
//            itAdapter = new IdentifikasiTenurialAdapter(getContext(),lsttenurial);
//            recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//            recylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
//            recylcerview .setAdapter(itAdapter);
//        } catch (Exception ex) {
//            AjnClass.showAlert(getActivity(), ex.toString());
//        }
//    }
//
//    public void def(){
//        lsttenurial = new ArrayList<>();
//
//        try {
//
//            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
//            SQLiteDatabase db = DB_Helper.getReadableDatabase();
//            final Cursor cur = db.rawQuery("SELECT " +
//                    " ID, TANGGAL, JENIS_PERMASALAHAN, ID, ANAK_PETAK_ID, ID, ID, ID, AWAL_KONFLIK, ID, ID, ID" +
////                    " DISTINCT(ANAK_PETAK_ID)" +
//                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
//                    " ORDER BY ID DESC", null);
//
//            cur.moveToPosition(0);
//            DataModel = new ArrayList<>();
//            for (int i = 0; i < cur.getCount(); i++) {
//                lsttenurial.add(new IdentifikasiTenurialModel(
//                        cur.getString(0),
//                        cur.getString(1),
//                        cur.getString(2),
//                        cur.getString(3),
//                        cur.getString(4),
//                        cur.getString(5),
//                        cur.getString(6),
//                        cur.getString(7),
//                        cur.getString(8),
//                        cur.getString(9),
//                        cur.getString(10),
//                        cur.getString(10)));
//                cur.moveToNext();
//            }
//
//            cur.close();
//            db.close();
//        } catch (Exception ex) {
//            AjnClass.showAlert(getActivity(), ex.toString());
//        }
//    }
//}

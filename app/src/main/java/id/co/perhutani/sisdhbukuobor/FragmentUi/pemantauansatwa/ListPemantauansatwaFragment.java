package id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa;

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

import id.co.perhutani.sisdhbukuobor.Adapter.PemantauansatwaAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;

import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.tambahpemantauan.TambahpemantauansatwaFragment;

public class ListPemantauansatwaFragment extends Fragment
{
    //View v;
    private static RecyclerView myrecylcerview;
    private static ArrayList<PemantauansatwaModel> DataModel;
    private static List<PemantauansatwaModel> lstpemantauan;
    private static PemantauansatwaAdapter psAdapter;
    private static Context context;
    private static final int VERTICAL_ITEM_SPACE = 0;

    public static ListPemantauansatwaFragment newInstance()
    {
        return new ListPemantauansatwaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.pemantauan_satwa_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.pemantauan_recycler);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        init();

        ImageView imgTambahPemantauan = (ImageView) root.findViewById(R.id.img_tambahpemantauan);
        imgTambahPemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahpemantauansatwaFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        final EditText txt_searchpemantauan = root.findViewById(R.id.txt_search_pemantauan);
        txt_searchpemantauan.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                init();
                String input =txt_searchpemantauan.getText().toString();
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

    public void refresh(String pemantauan) {
        lstpemantauan = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, JENIS_SATWA, JUMLAH_SATWA, WAKTU_LIHAT, ID, TANGGAL, ID, ID, ID, ID" +
                    " FROM TRN_PEMANTAUAN_SATWA " +
                    " WHERE ANAK_PETAK_ID " + " LIKE  " + "'%" + pemantauan + "%'" +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpemantauan.add(new PemantauansatwaModel(
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
            psAdapter = new PemantauansatwaAdapter(getContext(),lstpemantauan);
            myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            myrecylcerview .setAdapter(psAdapter);
        } catch (Exception ex) {
            AjnClass.showAlert(getActivity(), ex.toString());
        }
    }

    public void def(){
        lstpemantauan = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, JENIS_SATWA, JUMLAH_SATWA, WAKTU_LIHAT, ID, TANGGAL, ID, ID, ID, ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_PEMANTAUAN_SATWA " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpemantauan.add(new PemantauansatwaModel(
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
        lstpemantauan = new ArrayList<>();

        try {
            SQLiteHandler DB_Helper = new SQLiteHandler(context);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            final Cursor cur = db.rawQuery("SELECT " +
                    " ID, ANAK_PETAK_ID, JENIS_SATWA, JUMLAH_SATWA, WAKTU_LIHAT, ID, TANGGAL, ID, ID, ID, ID" +
//                    " DISTINCT(ANAKPETAK_ID)" +
                    " FROM TRN_PEMANTAUAN_SATWA " +
                    " ORDER BY ID DESC", null);

            cur.moveToPosition(0);
            DataModel = new ArrayList<>();
            for (int i = 0; i < cur.getCount(); i++) {
                lstpemantauan.add(new PemantauansatwaModel(
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
                        Integer.parseInt(cur.getString(0))
                ));
                cur.moveToNext();
            }

            cur.close();
            db.close();
        } catch (Exception ex) {
            AjnClass.showAlert(context, ex.toString());
        }

        psAdapter = new PemantauansatwaAdapter(context,lstpemantauan);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(context));
        myrecylcerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        psAdapter.notifyDataSetChanged();
        myrecylcerview.invalidate();
        myrecylcerview.setAdapter(psAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        def();
        context=getActivity();
    }
}

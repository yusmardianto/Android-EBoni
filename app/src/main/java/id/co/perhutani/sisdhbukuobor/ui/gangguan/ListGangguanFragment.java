package id.co.perhutani.sisdhbukuobor.ui.gangguan;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.perhutani.sisdhbukuobor.Adapter.GangguanAdapter;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan.TambahGangguanFragment;

public class ListGangguanFragment extends Fragment
{
//    View v ;
    private RecyclerView myrecyclerview;
    private ArrayList<GangguanModel> lstGangguan;
    private GangguanViewModel mViewModel;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListGangguanFragment newInstance() { return new ListGangguanFragment(); }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(GangguanViewModel.class);
//        v = inflater.inflate(R.layout.gangguan_fragment, container, false);


        View root = inflater.inflate(R.layout.gangguan_fragment, container, false);
        myrecyclerview = root.findViewById(R.id.gangguan_recycler);
        GangguanAdapter gAdapter = new GangguanAdapter(getContext(),lstGangguan);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        myrecyclerview .setAdapter(gAdapter);



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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstGangguan = new ArrayList<>();
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new GangguanModel("Penebangan ","Jkt","011","30/11/19"));


    }
}

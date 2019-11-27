package id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan;

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

import id.co.perhutani.sisdhbukuobor.Adapter.PerubahankelasAdapter;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;

public class ListPerubahanKelasFragment extends Fragment
{
    //View v;
    private RecyclerView myrecylcerview;
    private ArrayList<PerubahankelasModel> lstperubahankls;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListPerubahanKelasFragment newInstance()
    {
        return new ListPerubahanKelasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.perubahan_kelas_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.perubahankls_recycler);
        PerubahankelasAdapter gAdapter = new PerubahankelasAdapter(getContext(),lstperubahankls);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        myrecylcerview .setAdapter(gAdapter);

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

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstperubahankls = new ArrayList<>();
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));
        lstperubahankls.add(new PerubahankelasModel("201631141","087797315685","KPH Semarang","12-12-2012","Eboni"));

    }
}

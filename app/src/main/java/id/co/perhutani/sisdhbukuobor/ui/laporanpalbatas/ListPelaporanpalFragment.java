package id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.perhutani.sisdhbukuobor.Adapter.PelaporanpalbatasAdapter;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;

public class ListPelaporanpalFragment extends Fragment
{
    private RecyclerView myrecylcerview;
    private ArrayList<PelaporanpalbatasModel> lstpelaporanpal;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListPelaporanpalFragment newInstance()
    {
        return new ListPelaporanpalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.laporan_pal_batas_fragment, container, false);
        myrecylcerview = root.findViewById(R.id.pelaporanpal_recycler);
        PelaporanpalbatasAdapter gAdapter = new PelaporanpalbatasAdapter(getContext(),lstpelaporanpal);
        myrecylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        myrecylcerview .setAdapter(gAdapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstpelaporanpal = new ArrayList<>();
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));
        lstpelaporanpal.add(new PelaporanpalbatasModel("201631141","Semarang","10109090","Terbakar"));


    }
}

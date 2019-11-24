package id.co.perhutani.sisdhbukuobor.ui.gangguan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.VerticalSpaceItemDecoration;

public class ListGangguanFragment extends Fragment
{
    View v ;
    private RecyclerView myrecyclerview;
    private ArrayList<ListViewGangguan> lstGangguan;

    private static final int VERTICAL_ITEM_SPACE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.gangguan_fragment, container, false);

        myrecyclerview = (RecyclerView) v.findViewById(R.id.gangguan_recycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstGangguan);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        myrecyclerview .setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstGangguan = new ArrayList<>();
        lstGangguan.add(new ListViewGangguan("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new ListViewGangguan("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new ListViewGangguan("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new ListViewGangguan("Penebangan ","Jkt","011","30/11/19"));
        lstGangguan.add(new ListViewGangguan("Penebangan ","Jkt","011","30/11/19"));
    }
}

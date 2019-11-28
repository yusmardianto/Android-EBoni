package id.co.perhutani.sisdhbukuobor.ui.registerpcp;
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

import id.co.perhutani.sisdhbukuobor.Adapter.RegisterpcpAdapter;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;

public class ListRegisterpcpFragment extends Fragment
{
    //View v;
    private RecyclerView recylcerview;
    private ArrayList<RegisterpcpModel> lstregisterpcp;

    private static final int VERTICAL_ITEM_SPACE = 0;
    public static ListRegisterpcpFragment newInstance()
    {
        return new ListRegisterpcpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.register_pcp_fragment, container, false);
        recylcerview = root.findViewById(R.id.registerpcp_recycler);
        RegisterpcpAdapter rAdapter = new RegisterpcpAdapter(getContext(),lstregisterpcp);
        recylcerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylcerview .setAdapter(rAdapter);


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstregisterpcp = new ArrayList<>();
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
        lstregisterpcp.add(new RegisterpcpModel("201631141","Semarang","2010","Anjay"));
    }
}

package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.tambahinteraksimdh.TambahInteraksimdhFragment;
import id.co.perhutani.sisdhbukuobor.R;


public class ListInteraksiMDHFragment extends Fragment {

    private Context context;


    public static ListInteraksiMDHFragment newInstance() {

        return new ListInteraksiMDHFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_interaksi_mdh,container,false);
        ImageView imgTambahInteraksi = root.findViewById(R.id.tambah_interaksimdh);
        imgTambahInteraksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahInteraksimdhFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return root;
    }

}

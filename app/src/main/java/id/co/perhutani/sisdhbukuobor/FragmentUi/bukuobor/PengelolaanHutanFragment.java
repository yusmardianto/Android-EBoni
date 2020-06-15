package id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.MonitoringKlsHtnFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;


public class PengelolaanHutanFragment extends Fragment {

    View view_pengelolaan_hutan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_pengelolaan_hutan = inflater.inflate(R.layout.fragment_pengelolaan_hutan, container, false);

        LinearLayout pengelolaanhutan = view_pengelolaan_hutan.findViewById(R.id.pengelolaanhutan);
        pengelolaanhutan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPengelolaanHutanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout persemaian = view_pengelolaan_hutan.findViewById(R.id.persemaian);
        persemaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PersemaianFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view_pengelolaan_hutan;
    }
}
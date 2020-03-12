package id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.ListInteraksiMDHFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.ListPelaporanpalFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.ListRegisterpcpFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class BukuOborFragment extends Fragment {


    private SQLiteHandler db;

    public static BukuOborFragment newInstance() {
        return new BukuOborFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
//        return inflater.inflate(R.layout.buku_obor_fragment, container, false);


        View root = inflater.inflate(R.layout.buku_obor_fragment, container, false);


        final LinearLayout lingangguan = root.findViewById(R.id.linear_gangguan);
        lingangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View gangguan = inflater.inflate(R.layout.gangguan_fragment, container, false);

                Fragment fragment = new ListGangguanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout linPerubahankelas = (LinearLayout)root.findViewById(R.id.linear_perubahankelas);
        linPerubahankelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPerubahanKelasFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout lininteraksi = (LinearLayout)root.findViewById(R.id.linear_interaksi);
        lininteraksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListInteraksiMDHFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout linpemantauan = (LinearLayout)root.findViewById(R.id.linear_pemantauan);
        linpemantauan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPemantauansatwaFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout linlappal = (LinearLayout)root.findViewById(R.id.linear_lappal);
        linlappal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListPelaporanpalFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout linregisterpcp = (LinearLayout)root.findViewById(R.id.linear_regispcp);
        linregisterpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListRegisterpcpFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout linidentifikasi = (LinearLayout)root.findViewById(R.id.linear_identifikasitenurial);
        linidentifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ListIdentifikasiTenurialFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LinearLayout pengelolaanhutan = (LinearLayout)root.findViewById(R.id.linear_pengelolaanhutan);
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

        return root;
    }


}

package id.co.perhutani.sisdhbukuobor.ui.bukuobor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.GangguanFragment;
import id.co.perhutani.sisdhbukuobor.ui.interaksimdh.InteraksiMdhFragment;
import id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.LaporanPalBatasFragment;
import id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa.PemantauanSatwaFragment;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.PerubahanKelasFragment;
import id.co.perhutani.sisdhbukuobor.ui.registerpcp.RegisterPcpFragment;

public class BukuOborFragment extends Fragment {

    private BukuOborViewModel mViewModel;

    public static BukuOborFragment newInstance() {
        return new BukuOborFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.buku_obor_fragment, container, false);


        mViewModel = ViewModelProviders.of(this).get(BukuOborViewModel.class);
        View root = inflater.inflate(R.layout.buku_obor_fragment, container, false);


        LinearLayout lingangguan = root.findViewById(R.id.linear_gangguan);
        lingangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GangguanFragment();
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
                Fragment fragment = new PerubahanKelasFragment();
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
                Fragment fragment = new InteraksiMdhFragment();
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
                Fragment fragment = new PemantauanSatwaFragment();
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
                Fragment fragment = new LaporanPalBatasFragment();
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
                Fragment fragment = new RegisterPcpFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BukuOborViewModel.class);
        // TODO: Use the ViewModel
    }

}

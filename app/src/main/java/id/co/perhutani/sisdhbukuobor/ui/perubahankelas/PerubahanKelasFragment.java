package id.co.perhutani.sisdhbukuobor.ui.perubahankelas;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan.TambahPerubahanFragment;

public class PerubahanKelasFragment extends Fragment {

    private PerubahanKelasViewModel mViewModel;

    public static PerubahanKelasFragment newInstance() {
        return new PerubahanKelasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(PerubahanKelasViewModel.class);
        View root = inflater.inflate(R.layout.perubahan_kelas_fragment, container, false);

        ImageView imgTambahPerubahan = (ImageView) root.findViewById(R.id.img_tambahperubahan);
        imgTambahPerubahan.setOnClickListener(new View.OnClickListener() {
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PerubahanKelasViewModel.class);
        // TODO: Use the ViewModel
    }

}

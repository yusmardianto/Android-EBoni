package id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class LaporanPalBatasFragment extends Fragment {

    private LaporanPalBatasViewModel mViewModel;

    public static LaporanPalBatasFragment newInstance() {
        return new LaporanPalBatasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.laporan_pal_batas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LaporanPalBatasViewModel.class);
        // TODO: Use the ViewModel
    }

}

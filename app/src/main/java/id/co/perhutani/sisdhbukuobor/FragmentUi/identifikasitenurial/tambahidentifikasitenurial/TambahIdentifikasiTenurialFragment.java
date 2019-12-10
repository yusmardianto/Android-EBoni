package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.tambahidentifikasitenurial;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class TambahIdentifikasiTenurialFragment extends Fragment {

    private TambahIdentifikasiTenurialViewModel mViewModel;

    public static TambahIdentifikasiTenurialFragment newInstance() {
        return new TambahIdentifikasiTenurialFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambah_identifikasi_tenurial_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TambahIdentifikasiTenurialViewModel.class);
        // TODO: Use the ViewModel
    }

}

package id.co.perhutani.sisdhbukuobor.ui.perubahankelas.tambahperubahan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class TambahPerubahanFragment extends Fragment {

    private TambahPerubahanViewModel mViewModel;

    public static TambahPerubahanFragment newInstance() {
        return new TambahPerubahanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambah_perubahan_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TambahPerubahanViewModel.class);
        // TODO: Use the ViewModel
    }
}

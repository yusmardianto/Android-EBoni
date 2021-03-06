package id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.co.perhutani.sisdhbukuobor.R;

public class IdentifikasiTenurialFragment extends Fragment {

    private IdentifikasiTenurialViewModel mViewModel;

    public static IdentifikasiTenurialFragment newInstance() {
        return new IdentifikasiTenurialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.identifikasi_tenurial_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IdentifikasiTenurialViewModel.class);
        // TODO: Use the ViewModel
    }

}

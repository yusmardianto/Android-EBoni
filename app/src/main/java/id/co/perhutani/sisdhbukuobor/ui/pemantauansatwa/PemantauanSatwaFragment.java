package id.co.perhutani.sisdhbukuobor.ui.pemantauansatwa;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class PemantauanSatwaFragment extends Fragment {

    private PemantauanSatwaViewModel mViewModel;

    public static PemantauanSatwaFragment newInstance() {
        return new PemantauanSatwaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pemantauan_satwa_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PemantauanSatwaViewModel.class);
        // TODO: Use the ViewModel
    }

}

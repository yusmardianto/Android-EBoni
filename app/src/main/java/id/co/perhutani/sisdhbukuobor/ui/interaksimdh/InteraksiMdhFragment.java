package id.co.perhutani.sisdhbukuobor.ui.interaksimdh;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class InteraksiMdhFragment extends Fragment {

    private InteraksiMdhViewModel mViewModel;

    public static InteraksiMdhFragment newInstance() {
        return new InteraksiMdhFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.interaksi_mdh_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InteraksiMdhViewModel.class);
        // TODO: Use the ViewModel
    }

}

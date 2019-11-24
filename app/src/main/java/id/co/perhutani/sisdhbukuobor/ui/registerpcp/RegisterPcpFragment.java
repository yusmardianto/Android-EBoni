package id.co.perhutani.sisdhbukuobor.ui.registerpcp;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class RegisterPcpFragment extends Fragment {

    private RegisterPcpViewModel mViewModel;

    public static RegisterPcpFragment newInstance() {
        return new RegisterPcpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_pcp_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterPcpViewModel.class);
        // TODO: Use the ViewModel
    }

}

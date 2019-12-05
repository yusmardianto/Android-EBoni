package id.co.perhutani.sisdhbukuobor.ui.registerpcp.editregisterpcp;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class EditRegisterpcpFragment extends Fragment {

    private EditRegisterpcpViewModel mViewModel;

    public static EditRegisterpcpFragment newInstance() {
        return new EditRegisterpcpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_registerpcp_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditRegisterpcpViewModel.class);
        // TODO: Use the ViewModel
    }

}

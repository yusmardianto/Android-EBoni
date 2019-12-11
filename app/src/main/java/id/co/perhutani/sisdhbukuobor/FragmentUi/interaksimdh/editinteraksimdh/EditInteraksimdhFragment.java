package id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.editinteraksimdh;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;

public class EditInteraksimdhFragment extends Fragment {

    private EditInteraksimdhViewModel mViewModel;

    public static EditInteraksimdhFragment newInstance() {
        return new EditInteraksimdhFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_interaksimdh_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditInteraksimdhViewModel.class);
        // TODO: Use the ViewModel
    }

}

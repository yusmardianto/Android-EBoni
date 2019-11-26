package id.co.perhutani.sisdhbukuobor.ui.laporanpalbatas.ui.tambahlaporanpalbatas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.co.perhutani.sisdhbukuobor.R;

public class TambahlaporanpalbatasFragment extends Fragment {

    private TambahlaporanpalbatasViewModel mViewModel;

    public static TambahlaporanpalbatasFragment newInstance() {
        return new TambahlaporanpalbatasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tambahlaporanpalbatas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TambahlaporanpalbatasViewModel.class);
        // TODO: Use the ViewModel
    }

}

package id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.co.perhutani.sisdhbukuobor.R;


public class TambahGangguanFragment extends Fragment {
    private TambahGangguanViewModel mViewModel;

    public static TambahGangguanFragment newInstance() {
        return new TambahGangguanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);
        View root = inflater.inflate(R.layout.tambah_gangguan_fragment, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TambahGangguanViewModel.class);
        // TODO: Use the ViewModel
    }

}

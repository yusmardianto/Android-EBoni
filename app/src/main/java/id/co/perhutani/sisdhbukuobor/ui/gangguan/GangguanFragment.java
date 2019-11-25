package id.co.perhutani.sisdhbukuobor.ui.gangguan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.tambahgangguan.TambahGangguanFragment;

public class GangguanFragment extends Fragment {
    private GangguanViewModel mViewModel;

    public static GangguanFragment newInstance() { return new GangguanFragment(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.gangguan_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(GangguanViewModel.class);
        View root = inflater.inflate(R.layout.gangguan_fragment, container, false);

        ImageView imgTambahGangguan = (ImageView) root.findViewById(R.id.img_tambahgangguan);
        imgTambahGangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahGangguanFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GangguanViewModel.class);
        // TODO: Use the ViewModel
    }
}
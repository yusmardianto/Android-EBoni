package id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.tambahpengelolaanhutan.TambahPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class ListPengelolaanHutanFragment extends Fragment {

    private PengelolaanHutanViewModel mViewModel;
    private static RecyclerView myrecyclerview;

    public static ListPengelolaanHutanFragment newInstance() {
        return new ListPengelolaanHutanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.pengelolaan_hutan_fragment, container, false);
        myrecyclerview = root.findViewById(R.id.pengelolaanhutan_recycler);

        ImageView imgTambahPengelolaan = (ImageView) root.findViewById(R.id.img_tambahpengelolaanhutan);
        imgTambahPengelolaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TambahPengelolaanHutanFragment();
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
        mViewModel = ViewModelProviders.of(this).get(PengelolaanHutanViewModel.class);
        // TODO: Use the ViewModel
    }

}

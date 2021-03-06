package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.MutasiBibit;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class ListMutasiBibitFragment extends Fragment {

    private MutasiBibitViewModel mViewModel;
    View event;

    public static ListMutasiBibitFragment newInstance() {
        return new ListMutasiBibitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        event = inflater.inflate(R.layout.persemaian_mutasi_bibit_fragment, container, false);

        Toolbar toolbar = event.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PersemaianFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        return event;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MutasiBibitViewModel.class);
        // TODO: Use the ViewModel
    }

}

package id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class GangguanFragment extends Fragment {

    View view_gangguan;
    public static GangguanFragment newInstance() { return new GangguanFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view_gangguan=inflater.inflate(R.layout.gangguan_fragment, container, false);
//        Toolbar toolbar = view_gangguan.findViewById(R.id.toolbar_gangguan);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BukuOborFragment();
//                FragmentManager frgManager = getFragmentManager();
//                FragmentTransaction ft = frgManager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();
//            }
//        });
        return view_gangguan;
    }

}
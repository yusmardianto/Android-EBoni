package id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.co.perhutani.sisdhbukuobor.R;

public class GangguanFragment extends Fragment {

    public static GangguanFragment newInstance() { return new GangguanFragment(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.gangguan_fragment, container, false);
    }

}
package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.RekapTS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.perhutani.sisdhbukuobor.R;


public class RekapTSFragment extends Fragment {

    View view_rekap_ts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_rekap_ts = inflater.inflate(R.layout.fragment_rekap_t_s, container, false);

        return view_rekap_ts;
    }
}
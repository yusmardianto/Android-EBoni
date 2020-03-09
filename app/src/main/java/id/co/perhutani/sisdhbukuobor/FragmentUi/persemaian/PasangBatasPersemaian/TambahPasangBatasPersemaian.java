package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.co.perhutani.sisdhbukuobor.R;

public class TambahPasangBatasPersemaian extends Fragment {

    public static TambahPasangBatasPersemaian newInstance() { return new TambahPasangBatasPersemaian(); }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.persemaian_tambah_pasang_batas_persemaian, container, false);
    }
}

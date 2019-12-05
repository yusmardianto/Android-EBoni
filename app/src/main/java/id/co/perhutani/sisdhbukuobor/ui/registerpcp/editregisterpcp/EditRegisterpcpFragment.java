package id.co.perhutani.sisdhbukuobor.ui.registerpcp.editregisterpcp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;

public class EditRegisterpcpFragment extends Fragment {

    private EditText nopcp, anakpetak, tahunpcp, luasbaku, luasblok, umur, rataratakeliling,
            bonita, mpcp, normalpcp, nmati, tahunjurangan, keterangan;

    private static String id, str_nopcp, str_anakpetak, str_tahunpcp, str_luasbaku, str_luasblok,
            str_umur, str_rataratakeliling, str_bonita, str_mpcp, str_normalpcp, str_nmati, str_tahunjurangan, str_keterangan;

    private Button btnSimpanRegisterpcp;

    private Spinner spin_anak_petak;
    final Calendar calendar = Calendar.getInstance();

    public static final String MSG_KEY = "id";
    private static SQLiteHandler db;
    private EditRegisterpcpViewModel mViewModel;

    public static EditRegisterpcpFragment newInstance() {
        return new EditRegisterpcpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_registerpcp_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditRegisterpcpViewModel.class);
        // TODO: Use the ViewModel
    }

}

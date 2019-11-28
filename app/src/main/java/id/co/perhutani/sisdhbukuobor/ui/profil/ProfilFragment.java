package id.co.perhutani.sisdhbukuobor.ui.profil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.LocalDatabaseActivity;
import id.co.perhutani.sisdhbukuobor.LoginActivity;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class ProfilFragment extends Fragment {

    private ProfilViewModel mViewModel;
    private SessionManager session;
    private SQLiteHandler db;
    int count = 0;
    public static String getnama, getbkph;
    private TextView nama, bkph;

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.profil_fragment, container, false);
        View profileView = inflater.inflate(R.layout.profil_fragment, container, false);

        db = new SQLiteHandler(getActivity().getApplicationContext());
        db.altertable_update_aplication();
        session = new SessionManager(getActivity().getApplicationContext());
        if (!session.isLoggedIn()) {
            actionDeleteData();
        }

        nama = profileView.findViewById(R.id.txt_nama_user);
        bkph = profileView.findViewById(R.id.txt_lokasi_user);
        getnama = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_NAME_DESCRIPTIONS);
        getbkph = db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.KET1);
        nama.setText(getnama);
        bkph.setText(getbkph);

        LinearLayout logout = profileView.findViewById(R.id.linear_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Manually checking internet connection
                function_logout();
            }
        });
        LinearLayout checkdb = profileView.findViewById(R.id.linear_test);
        checkdb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                if (count == 3) {
                    count = 0;
                    db.altertable();
                    Intent intent = new Intent(getActivity(), LocalDatabaseActivity.class);
                    startActivity(intent);
                    getActivity();
                }
            }
        });

        return profileView;
    }

    private void function_logout() {

        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Logout and exit application ?")
                .setCancelText("No")
                .setConfirmText("Yes")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("Cancelled!")
                                .setContentText("")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Logout and exit application!")
                                .setContentText("")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                actionDeleteData();
                            }

                        }, 1000);
                    }
                })
                .show();

    }
    private void actionDeleteData() {
        session.setLogin(false);
        db.deleteAllRow(UserSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstAnakPetakSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisTanamanSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(MstJenisPermasalahanSchema.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnGangguanKeamananHutan.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPerubahanKelas.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnPemantauanSatwa.SQL_DELETE_ALL_ROWS);
        db.deleteAllRow(TrnRegisterPcp.SQL_DELETE_ALL_ROWS);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
        // TODO: Use the ViewModel
    }

}

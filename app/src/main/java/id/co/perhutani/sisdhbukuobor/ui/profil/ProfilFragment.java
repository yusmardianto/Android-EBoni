package id.co.perhutani.sisdhbukuobor.ui.profil;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.LoginActivity;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class ProfilFragment extends Fragment {

    private ProfilViewModel mViewModel;
    private SessionManager session;
    private SQLiteHandler db;

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

        LinearLayout logout = profileView.findViewById(R.id.linear_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Manually checking internet connection
                function_logout();
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
//        db.deleteAllRow(ProjekAndilSchema.SQL_DELETE_ALL_ROWS);
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

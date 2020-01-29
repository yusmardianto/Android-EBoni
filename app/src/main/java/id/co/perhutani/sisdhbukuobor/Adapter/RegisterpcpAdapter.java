package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.ListRegisterpcpFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.editregisterpcp.EditRegisterpcpFragment;

public class RegisterpcpAdapter extends RecyclerView.Adapter<RegisterpcpAdapter.RegisterpcpViewHolder>
{
    Context mContext;
    List<RegisterpcpModel> mData;
    SQLiteHandler db;
    public static final String MSG_KEY = "id";
    private AlertDialog.Builder builder;

    public RegisterpcpAdapter(Context mContext, List<RegisterpcpModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RegisterpcpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vregisterpcp;
        db = new SQLiteHandler(mContext);
        vregisterpcp = LayoutInflater.from(mContext).inflate(R.layout.register_pcp_item_fragment,parent,false);
        RegisterpcpViewHolder vHolder = new RegisterpcpViewHolder(vregisterpcp);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterpcpViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        String getAnakPetakId = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, mData.get(position).getID(), TrnRegisterPcp.KET1);

        holder.tv_nopcp.setText(mData.get(position).getNoPcp());
        holder.tv_anakpetakid.setText(getAnakPetakId);
        holder.tv_tahunpcp.setText(mData.get(position).getTahun());
        holder.tv_bonitapcp.setText(mData.get(position).getBonita());
        holder.img_detailregisterpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getID());

            }
        });

        String status_sync = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, mData.get(position).getID(), TrnRegisterPcp.KET9);
        if (status_sync.equals("1")){
            holder.name_data_sinkron.setText("Belum terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(228,0,4));
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_info_outline_red_24dp));
        }  else {
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_check_circle_green_24dp));
            holder.name_data_sinkron.setText("Sudah terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(146,198,91));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RegisterpcpViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nopcp;
        private TextView tv_anakpetakid;
        private TextView tv_tahunpcp;
        private TextView tv_bonitapcp;
        private LinearLayout img_detailregisterpcp;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;

        public RegisterpcpViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nopcp = itemView.findViewById(R.id.name_nomerpcp);
            tv_anakpetakid = itemView.findViewById(R.id.name_anakpetakpcp);
            tv_tahunpcp = itemView.findViewById(R.id.name_tahunpcp);
            tv_bonitapcp = itemView.findViewById(R.id.name_bonitapcp);
            img_detailregisterpcp = itemView.findViewById(R.id.img_registerpcpdetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_pcp);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_pcp);
        }
    }

    public void popup (final String id){
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_registerpcp, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            final String get_nopcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NO_PCP);
            String get_anakpetakid = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KET1);
            String get_tahunpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_PCP);
            String get_luasbaku = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BAKU);
            String get_luasblok = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BLOK);
            String get_umur = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UMUR);
            String get_ratarata = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.RATARATA_KELILING);
            String get_bonita = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.BONITA);
            String get_nlapangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_LAPANGAN);
            String get_normalpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NORMAL_PCP);
            String get_nmati = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_MATI);
            String get_tahunjarangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_JARANGAN);
            String get_peninggi = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.PENIGGI);
            String get_keterangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KETERANGAN);

            TextView nopcp = viewas.findViewById(R.id.registerpcp_nopcpdetail);
            nopcp.setText(get_nopcp);

            TextView anakpetakid = viewas.findViewById(R.id.registerpcp_anakpetakiddetail);
            anakpetakid.setText(get_anakpetakid);

            TextView tahun = viewas.findViewById(R.id.registerpcp_tahunpcpdetail);
            tahun.setText(get_tahunpcp);

            TextView luasbaku = viewas.findViewById(R.id.registerpcp_luasbakudetail);
            luasbaku.setText(get_luasbaku);

            TextView luasblock = viewas.findViewById(R.id.registerpcp_luasblockdetail);
            luasblock.setText(get_luasblok);

            TextView umur = viewas.findViewById(R.id.registerpcp_umur);
            umur.setText(get_umur);

            TextView ratarata = viewas.findViewById(R.id.registerpcp_ratakelilingdetail);
            ratarata.setText(get_ratarata);

            TextView bonita = viewas.findViewById(R.id.registerpcp_bonitadetail);
            bonita.setText(get_bonita);

            TextView nlapangan = viewas.findViewById(R.id.registerpcp_nlapangandetail);
            nlapangan.setText(get_nlapangan);

            TextView normalpcp = viewas.findViewById(R.id.registerpcp_normalpcpndetail);
            normalpcp.setText(get_normalpcp);

            TextView nmati = viewas.findViewById(R.id.registerpcp_nmatidetail);
            nmati.setText(get_nmati);

            TextView tahunjarangan = viewas.findViewById(R.id.registerpcp_tahunjarangandetail);
            tahunjarangan.setText(get_tahunjarangan);

            TextView peninggi = viewas.findViewById(R.id.registerpcp_peninggidetail);
            peninggi.setText(get_peninggi);

            TextView keterangan = viewas.findViewById(R.id.registerpcp_keterangandetail);
            keterangan.setText(get_keterangan);


            alertDialogBuilder.setView(viewas);

            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditregisterpcp);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditRegisterpcpFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(RegisterpcpAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeleteregisterpcp);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_nopcp;

                    new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Hapus Data ?")
                            .setContentText(str_note)
                            .setCancelText("Batal")
                            .setConfirmText("Hapus")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.setTitleText("Diabatalkan!")
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
                                    sDialog.setTitleText("Berhasil !")
                                            .setContentText(str_note)
                                            .setConfirmText("OK")
                                            .showCancelButton(false)
                                            .setCancelClickListener(null)
                                            .setConfirmClickListener(null)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                    new Handler().postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            // TODO Auto-generated method stub
                                            try {
                                                db.delete_one_date(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id);
                                                ListRegisterpcpFragment.refresh_list();
                                            } catch (Exception ex) {
                                            }
                                            alert.dismiss();
                                        }

                                    }, 1000);
                                }
                            })
                            .show();
                }
            });
        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }
}

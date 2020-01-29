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
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.ListInteraksiMDHFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.editinteraksimdh.EditInteraksimdhFragment;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;

public class InteraksimdhAdapter extends RecyclerView.Adapter<InteraksimdhAdapter.InteraksimdhViewHolder> {

    Context mContext;
    List<InteraksimdhModel> mData;
    private SQLiteHandler db;

    public static final String MSG_KEY = "id";

    public InteraksimdhAdapter(Context mContext, List<InteraksimdhModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public InteraksimdhAdapter.InteraksimdhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vimdh;
        vimdh = LayoutInflater.from(mContext).inflate(R.layout.interaksi_mdh_item_fragment,parent,false);
        InteraksimdhAdapter.InteraksimdhViewHolder vHolder = new InteraksimdhAdapter.InteraksimdhViewHolder(vimdh);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InteraksimdhViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        String getAnakpetakid = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, mData.get(position).getId(), TrnInteraksimdh.KET1);
        String getTahun = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, mData.get(position).getId(), TrnInteraksimdh.KET2);
        String getDesaid = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, mData.get(position).getId(), TrnInteraksimdh.KET3);
        String getBentukinteraksi = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, mData.get(position).getId(), TrnInteraksimdh.KET4);

        holder.tv_namadesa.setText(getDesaid);
        holder.tv_bentukinteraksi.setText(getBentukinteraksi);
        holder.tv_petakID.setText(getAnakpetakid);
        holder.tv_tahun.setText(getTahun);
        holder.img_detailimdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(mData.get(position).getId());
            }
        });

        String status_sync = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, mData.get(position).getId(), TrnInteraksimdh.KET9);
        if (status_sync.equals("1")) {
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_check_circle_green_24dp));
            holder.name_data_sinkron.setText("Sudah terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(146,198,91));
        }  else {
            holder.name_data_sinkron.setText("Belum terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(228,0,4));
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_info_outline_red_24dp));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class InteraksimdhViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_namadesa;
        private TextView tv_bentukinteraksi;
        private TextView tv_petakID;
        private TextView tv_tahun;
        private LinearLayout img_detailimdh;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;

        public InteraksimdhViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_namadesa = itemView.findViewById(R.id.name_interaksi_namadesa);
            tv_bentukinteraksi = itemView.findViewById(R.id.name_interaksi_bentukinteraksi);
            tv_petakID = itemView.findViewById(R.id.name_interaksi_petak);
            tv_tahun = itemView.findViewById(R.id.name_interaksi_tahun);
            img_detailimdh = itemView.findViewById(R.id.img_interaksimdhdetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_imdh);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_imdh);

        }
    }

    public void popup (final String id){
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_interaksimdh, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_id = db.getDataDetail(TrnInteraksimdh.TABLE_NAME,TrnInteraksimdh._ID, id, TrnInteraksimdh._ID);
            String get_anakpetakdetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET1);
            String get_tahundetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET2);
            final String get_namadesadetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET3);
            String get_bentukinteraksidetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET4);
            String get_statusdetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET5);
            String get_keterangandetail = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KETERANGAN);

            TextView anakpetak = viewas.findViewById(R.id.interaksimdh_petakdetailid);
            anakpetak.setText(get_anakpetakdetail);

            TextView tahun = viewas.findViewById(R.id.interaksimdh_tahundetail);
            tahun.setText(get_tahundetail);

            TextView namadesa = viewas.findViewById(R.id.interaksimdh_desadetail);
            namadesa.setText(get_namadesadetail);

            TextView bentukinteraksi = viewas.findViewById(R.id.interaksimdh_interaksidetail);
            bentukinteraksi.setText(get_bentukinteraksidetail);

            TextView status = viewas.findViewById(R.id.interaksimdh_statusdetail);
            status.setText(get_statusdetail);

            TextView keterangan = viewas.findViewById(R.id.interaksimdh_keterangandetail);
            keterangan.setText(get_keterangandetail);


            alertDialogBuilder.setView(viewas);

            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditinteraksimdh);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditInteraksimdhFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(InteraksimdhAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeleteinteraksimdh);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_namadesadetail;

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
                                                db.delete_one_date(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id);
                                                ListInteraksiMDHFragment.refresh_list();
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

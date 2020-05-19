package id.co.perhutani.sisdhbukuobor.Adapter;

import android.app.AlertDialog;
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
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.editpengelolaanhutan.EditPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PengelolaanHutanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;

public class PengelolaanHutanAdapter extends RecyclerView.Adapter<PengelolaanHutanAdapter.PengelolaanHutanViewHolder>
{
    Context mContext;
    List<PengelolaanHutanModel> mData;
    SQLiteHandler db;

    private AlertDialog.Builder builder;

    public static final String MSG_KEY = "id";

    public PengelolaanHutanAdapter(Context mContext, List<PengelolaanHutanModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PengelolaanHutanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vpengelolaan;
        db = new SQLiteHandler(mContext);
        vpengelolaan = LayoutInflater.from(mContext).inflate(R.layout.gangguanitem_fragment,parent,false);
        PengelolaanHutanViewHolder vHolder = new PengelolaanHutanViewHolder(vpengelolaan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PengelolaanHutanViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        String get_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, mData.get(position).getId(), TrnPengelolaanHutan.KET1);
        String get_sub_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, mData.get(position).getId(), TrnPengelolaanHutan.KET2);
        String get_anakpetak = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, mData.get(position).getId(), TrnPengelolaanHutan.KET3);

        holder.tv_pekerjaan.setText(get_pekerjaan);
        holder.tv_sub_pekerjaan.setText(get_sub_pekerjaan);
        holder.tv_anakpetak.setText(get_anakpetak);
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.img_detailpengelolaanhutan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getId());

            }
        });

        String status_sync = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, mData.get(position).getId(), TrnPengelolaanHutan.KET9);
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

    public static class PengelolaanHutanViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_pekerjaan;
        private TextView tv_sub_pekerjaan;
        private TextView tv_anakpetak;
        private TextView tv_tanggal;
        private LinearLayout img_detailpengelolaanhutan;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;


        public PengelolaanHutanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_pekerjaan = itemView.findViewById(R.id.name_pekerjaan);
            tv_sub_pekerjaan = itemView.findViewById(R.id.name_sub_pekerjaan);
            tv_anakpetak = itemView.findViewById(R.id.name_petak_pekerjaan);
            tv_tanggal = itemView.findViewById(R.id.name_tanggalpekerjaan);
            img_detailpengelolaanhutan = itemView.findViewById(R.id.img_pengelolaanhutandetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_pengelolaan_hutan);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_pengelolaanhutan);
        }
    }

    public void popup (final String id){
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_ganggaunkemanan, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            final String get_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET1);
            String get_sub_pekerjaan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET2);
            String get_anakpetak = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KET3);
            String get_tanggal = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.TANGGAL);
            String get_rencana = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.RENCANA);
            String get_realisasi = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.REALISASI);
            String get_status = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.STATUS);
            String get_keterangan = db.getDataDetail(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id, TrnPengelolaanHutan.KETERANGAN);

            TextView pekerjaan = viewas.findViewById(R.id.pengelolaanhutan_pekerjaan_detail);
            pekerjaan.setText(get_pekerjaan);

            TextView sub_pekerjaan = viewas.findViewById(R.id.pengelolaanhutan_sub_pekerjaan_detail);
            sub_pekerjaan.setText(get_sub_pekerjaan);

            TextView anakpetak = viewas.findViewById(R.id.pengelolaanhutan_anakpetak_detail);
            anakpetak.setText(get_anakpetak);

            TextView tanggal = viewas.findViewById(R.id.pengelolaanhutan_tanggal_detail);
            tanggal.setText(get_tanggal);

            TextView rencana = viewas.findViewById(R.id.pengelolaanhutan_rencana_detail);
            rencana.setText(get_rencana);

            TextView realisasi = viewas.findViewById(R.id.pengelolaanhutan_realisai_detail);
            realisasi.setText(get_realisasi);

            TextView status = viewas.findViewById(R.id.pengelolaanhutan_status_detail);
            status.setText(get_status);

            TextView keterangan = viewas.findViewById(R.id.pengelolaanhutan_keterangan_detail);
            keterangan.setText(get_keterangan);

            alertDialogBuilder.setView(viewas);

            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditgangguan);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditPengelolaanHutanFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(GangguanAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });


            ImageView delete = viewas.findViewById(R.id.detail_btndeletegangguan);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_pekerjaan;

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
                                                db.delete_one_date(TrnPengelolaanHutan.TABLE_NAME, TrnPengelolaanHutan._ID, id);
                                                ListPengelolaanHutanFragment.refresh_list();
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

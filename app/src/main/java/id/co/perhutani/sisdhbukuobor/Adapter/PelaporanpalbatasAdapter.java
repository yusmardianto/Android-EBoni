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
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.ListPelaporanpalFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.editlaporanpalbatas.EditLaporanpalbatasFragment;

public class PelaporanpalbatasAdapter extends RecyclerView.Adapter<PelaporanpalbatasAdapter.PelaporanViewHolder>
{
    Context mContext;
    List<PelaporanpalbatasModel> mData;
    SQLiteHandler db;

    public static final String MSG_KEY = "id";
    private AlertDialog.Builder builder;

    public PelaporanpalbatasAdapter(Context mContext, List<PelaporanpalbatasModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PelaporanpalbatasAdapter.PelaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vpelaporan;
        db = new SQLiteHandler(mContext);
        vpelaporan = LayoutInflater.from(mContext).inflate(R.layout.laporan_pal_batas_item_fragment,parent,false);
        PelaporanpalbatasAdapter.PelaporanViewHolder vHolder = new PelaporanpalbatasAdapter.PelaporanViewHolder(vpelaporan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PelaporanpalbatasAdapter.PelaporanViewHolder holder, final int position) {

        db = new SQLiteHandler(mContext);

        String getJenisPal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, mData.get(position).getID(), TrnLaporanPalBatas.KET1);

        holder.tv_jenispal.setText(getJenisPal);
        holder.tv_tanggalpal.setText(mData.get(position).getTanggalPal());
        holder.tv_nomerpal.setText(mData.get(position).getNomerPal());
        holder.tv_jumlahpal.setText(mData.get(position).getJumlahPal());
        holder.img_detaillaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getID());

            }
        });

        String status_sync = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, mData.get(position).getID(), TrnLaporanPalBatas.KET9);
        if (status_sync.equals("1")){
            holder.name_data_sinkron.setText("Sudah terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(146,198,91));
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_check_circle_green_24dp));
        }  else {
            holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_info_outline_red_24dp));
            holder.name_data_sinkron.setText("Belum terkirim keserver");
            holder.name_data_sinkron.setTextColor(Color.rgb(228,0,4));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class PelaporanViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_jenispal;
        private TextView tv_tanggalpal;
        private TextView tv_nomerpal;
        private TextView tv_jumlahpal;
        private LinearLayout img_detaillaporan;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;

        public PelaporanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jenispal = itemView.findViewById(R.id.name_jenispal);
            tv_tanggalpal = itemView.findViewById(R.id.name_tanggalpal);
            tv_nomerpal = itemView.findViewById(R.id.name_nomerpal);
            tv_jumlahpal = itemView.findViewById(R.id.name_jumlahpal);
            img_detaillaporan = itemView.findViewById(R.id.img_laporanpaldetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_pal);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_pal);

        }
    }

    public void popup (final String id){
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_pelaporanpal, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
            final String get_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET1);
            final String get_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET2);
            String get_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
            String get_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
            String get_keterangnapal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);

            TextView nopal = viewas.findViewById(R.id.laporanpal_nopaldetail);
            nopal.setText(get_nopal);

            TextView tanggalpal = viewas.findViewById(R.id.laporanpal_tanggaldetail);
            tanggalpal.setText(get_tanggalpal);

            TextView jenispal = viewas.findViewById(R.id.laporanpal_jenisdetail);
            jenispal.setText(get_jenispal);

            TextView kondisipal = viewas.findViewById(R.id.laporanpal_kondisidetail);
            kondisipal.setText(get_kondisipal);

            TextView jumlahpal = viewas.findViewById(R.id.laporanpal_jumlahdetail);
            jumlahpal.setText(get_jumlahpal);

            TextView keteranganpal = viewas.findViewById(R.id.laporanpal_keterangandetail);
            keteranganpal.setText(get_keterangnapal);

            alertDialogBuilder.setView(viewas);

            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditlaporanpal);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditLaporanpalbatasFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(PelaporanpalbatasAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeletelaporanpal);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_jenispal;

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
                                                db.delete_one_date(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id);
                                                ListPelaporanpalFragment.refresh_list();
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

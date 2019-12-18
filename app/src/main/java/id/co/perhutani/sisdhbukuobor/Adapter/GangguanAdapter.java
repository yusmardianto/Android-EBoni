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
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.editgangguan.EditGangguanFragment;

public class GangguanAdapter extends RecyclerView.Adapter<GangguanAdapter.GangguanViewHolder> {

    Context mContext;
    List<GangguanModel> mData;

    SQLiteHandler db;

    public static final String MSG_KEY = "id";
    private AlertDialog.Builder builder;

    public GangguanAdapter(Context mContext, List<GangguanModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public GangguanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vgangguan;
        db = new SQLiteHandler(mContext);
        vgangguan = LayoutInflater.from(mContext).inflate(R.layout.gangguanitem_fragment,parent,false);
        GangguanViewHolder vHolder = new GangguanViewHolder(vgangguan);

        return vHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull GangguanViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        String get_anakpetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, mData.get(position).getId(), TrnGangguanKeamananHutan.KET1);
        String get_tanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, mData.get(position).getId(), TrnGangguanKeamananHutan.KET2);
        String get_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, mData.get(position).getId(), TrnGangguanKeamananHutan.KET3);

        holder.tv_isi.setText(get_kejadian);
        holder.tv_petak.setText(get_anakpetak);
        holder.tv_no.setText(get_tanaman);
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.img_detailganggaun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getId());

            }
        });
        String status_sync = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, mData.get(position).getId(), TrnGangguanKeamananHutan.KET9);
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

    public static class GangguanViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_isi;
        private TextView tv_petak;
        private TextView tv_no;
        private TextView tv_tanggal;
        private LinearLayout img_detailganggaun;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;


        public GangguanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_isi = itemView.findViewById(R.id.name_isi);
            tv_petak = itemView.findViewById(R.id.name_petak);
            tv_no = itemView.findViewById(R.id.name_id);
            tv_tanggal = itemView.findViewById(R.id.name_tanggalgangguan);
            img_detailganggaun = itemView.findViewById(R.id.img_gangguandetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron);
            name_info_alert = itemView.findViewById(R.id.name_info_alert);
        }
    }

    public void popup (final String id){

//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_ganggaunkemanan, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_anakpetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET1);
            String get_jenistanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET2);
            String get_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL);
            String get_nomera = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_A);
            final String get_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET3);
            String get_kerugianluas = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
            String get_kerugianpohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
            String get_kerugiankyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
            String get_kerugiankyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
            String get_kerugiangetah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
            String get_nilaikerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
            String get_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);

            TextView anakpetak = viewas.findViewById(R.id.gangguan_petakdetailid);
            anakpetak.setText(get_anakpetak);

            TextView jenis = viewas.findViewById(R.id.gangguan_jenistanamandetail);
            jenis.setText(get_jenistanaman);

            TextView tanggal = viewas.findViewById(R.id.gangguan_tanggaldetail);
            tanggal.setText(get_tanggal);

            TextView nomerA = viewas.findViewById(R.id.gangguan_nomorAdetail);
            nomerA.setText(get_nomera);

            TextView kejadian = viewas.findViewById(R.id.gangguan_kejadiandetail);
            kejadian.setText(get_kejadian);

            TextView kerugianluas = viewas.findViewById(R.id.gangguan_rugiluasdetail);
            kerugianluas.setText(get_kerugianluas);

            TextView kerugianpohon = viewas.findViewById(R.id.gangguan_rugipohondetail);
            kerugianpohon.setText(get_kerugianpohon);

            TextView kerugiankyp = viewas.findViewById(R.id.gangguan_rugikypdetail);
            kerugiankyp.setText(get_kerugiankyp);

            TextView kerugiankyb = viewas.findViewById(R.id.gangguan_rugikybdetail);
            kerugiankyb.setText(get_kerugiankyb);

            TextView kerugiangetah = viewas.findViewById(R.id.gangguan_rugigetahdetail);
            kerugiangetah.setText(get_kerugiangetah);

            TextView nilaikerugian = viewas.findViewById(R.id.gangguan_nilairugidetail);
            nilaikerugian.setText(get_nilaikerugian);

            TextView keterangan = viewas.findViewById(R.id.gangguan_keterangandetail);
            keterangan.setText(get_keterangan);


            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditgangguan);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditGangguanFragment();
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
                    final String str_note = "Hapus : " + get_kejadian;

                    new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Hapus Data ?")
                            .setContentText(str_note)
                            .setCancelText("Batal")
                            .setConfirmText("Hapus")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    // reuse previous dialog instance, keep widget user state, reset them if you need
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
                                                db.delete_one_date(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id);
                                                ListGangguanFragment.refresh_list();
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

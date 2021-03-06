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
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.editperubahan.EditPerubahanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;

public class PerubahankelasAdapter extends RecyclerView.Adapter<PerubahankelasAdapter.PerubahanklsViewHolder> {

    Context mContext;
    List<PerubahankelasModel> mData;
    private SQLiteHandler db;

    public static final String MSG_KEY = "id";
    private AlertDialog.Builder builder;

    public PerubahankelasAdapter(Context mContext, List<PerubahankelasModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PerubahankelasAdapter.PerubahanklsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vperubahankls;
        vperubahankls = LayoutInflater.from(mContext).inflate(R.layout.perubahan_kelas_item_fragment,parent,false);
        PerubahankelasAdapter.PerubahanklsViewHolder vHolder = new PerubahankelasAdapter.PerubahanklsViewHolder(vperubahankls);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PerubahanklsViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        String getJenisTanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, mData.get(position).getID(), TrnPerubahanKelas.KET2);
        String getPetakID = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, mData.get(position).getID(), TrnPerubahanKelas.KET1);
        String getKelasHutan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, mData.get(position).getID(), TrnPerubahanKelas.KET3);


        holder.tv_jenistanaman.setText(getJenisTanaman);
        holder.tv_petakID.setText(getPetakID);
        holder.tv_tahun.setText(mData.get(position).getTahun());
        holder.tv_kelashutan.setText(getKelasHutan);
        holder.img_detailperubahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(mData.get(position).getID());
            }
        });

        String status_sync = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, mData.get(position).getID(), TrnPerubahanKelas.KET9);
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

    public static class PerubahanklsViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_jenistanaman;
        private TextView tv_petakID;
        private TextView tv_tahun;
        private TextView tv_kelashutan;
        private LinearLayout img_detailperubahan;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;

        public PerubahanklsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jenistanaman = itemView.findViewById(R.id.name_jenistanamanperubahankelas);
            tv_petakID = itemView.findViewById(R.id.name_petakidperubahankelas);
            tv_tahun = itemView.findViewById(R.id.name_tahunperubahankelas);
            tv_kelashutan = itemView.findViewById(R.id.name_kelashutanperubahankelas);
            img_detailperubahan = itemView.findViewById(R.id.img_perubahankelasdetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_perubahan);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_perubahan);

        }
    }

    public void popup (final String id){
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_perubahankelas, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME,TrnPerubahanKelas._ID, id, TrnPerubahanKelas._ID);
            String get_petakdetailid = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET1);
            String get_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
            String get_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
            final String get_jenistanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET2);
            String get_kelashutan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET3);
            String get_luasperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
            String get_jenisperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET4);
            String get_kelasperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET5);
            String get_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
            String get_luasdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
            String get_jenisdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET6);
            String get_kelasdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET7);
            String get_keterangandetail = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KETERANGAN_PERUBAHAN);

            TextView anakpetak = viewas.findViewById(R.id.perubahankls_petakdetailid);
            anakpetak.setText(get_petakdetailid);

            TextView tahun = viewas.findViewById(R.id.perubahankls_tahundetail);
            tahun.setText(get_tahun);

            TextView luasdetail = viewas.findViewById(R.id.perubahankls_luasdetail);
            luasdetail.setText(get_luas);

            TextView jenistanaman = viewas.findViewById(R.id.perubahankls_jenistanamandetail);
            jenistanaman.setText(get_jenistanaman);

            TextView kelashutan = viewas.findViewById(R.id.perubahankls_kelashutandetail);
            kelashutan.setText(get_kelashutan);

            TextView luasperkiraan = viewas.findViewById(R.id.perubahankls_luasperkiraandetail);
            luasperkiraan.setText(get_luasperkiraan);

            TextView jenisperkiraan = viewas.findViewById(R.id.perubahankls_tanamanperkiraandetail);
            jenisperkiraan.setText(get_jenisperkiraan);

            TextView kelasperkiraan = viewas.findViewById(R.id.perubahankls_kelasperkiraandetail);
            kelasperkiraan.setText(get_kelasperkiraan);

            TextView bappkh = viewas.findViewById(R.id.perubahankls_nobappkhdetail);
            bappkh.setText(get_bappkh);

            TextView luasdefinitif = viewas.findViewById(R.id.perubahankls_luasdefinitifdetail);
            luasdefinitif.setText(get_luasdefinitif);

            TextView jenisdefinitif = viewas.findViewById(R.id.perubahankls_tanamandefinitifdetail);
            jenisdefinitif.setText(get_jenisdefinitif);

            TextView kelasdefinitif = viewas.findViewById(R.id.perubahankls_kelasdefinitifdetail);
            kelasdefinitif.setText(get_kelasdefinitif);

            TextView keterangan = viewas.findViewById(R.id.perubahankls_keterangandetail);
            keterangan.setText(get_keterangandetail);


            alertDialogBuilder.setView(viewas);

            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditperubahankelas);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditPerubahanFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(PerubahankelasAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeleteperubahankelas);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_jenistanaman;

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
                                                db.delete_one_date(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id);
                                                ListPerubahanKelasFragment.refresh_list();
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

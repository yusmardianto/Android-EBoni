package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.editpemantauan.EditPemantauanFragment;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;

public class PemantauansatwaAdapter extends RecyclerView.Adapter<PemantauansatwaAdapter.PemantauanViewHolder> {

    Context mContext;
    List<PemantauansatwaModel> mData;
    SQLiteHandler db;

    public static final String MSG_KEY = "id";

    public PemantauansatwaAdapter(Context mContext, List<PemantauansatwaModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PemantauanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vpemantauan;
        db = new SQLiteHandler(mContext);
        vpemantauan = LayoutInflater.from(mContext).inflate(R.layout.pemantauan_satwa_item_fragment,parent,false);
        PemantauansatwaAdapter.PemantauanViewHolder vHolder = new PemantauansatwaAdapter.PemantauanViewHolder(vpemantauan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PemantauanViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        holder.tv_jenisatwa.setText(mData.get(position).getJenis());
        holder.tv_anakpetakid.setText(mData.get(position).getAnakPetakId());
        holder.tv_jumlahsatwa.setText(mData.get(position).getJumlah());
        holder.tv_waktulihat.setText(mData.get(position).getWaktulihat());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.img_pemantaundetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(mData.get(position).getID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class PemantauanViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_jenisatwa;
        private TextView tv_anakpetakid;
        private TextView tv_jumlahsatwa;
        private TextView tv_waktulihat;
        private TextView tv_tanggal;
        private LinearLayout img_pemantaundetail;

        public PemantauanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jenisatwa = (TextView) itemView.findViewById(R.id.name_jenissatwa);
            tv_anakpetakid = (TextView) itemView.findViewById(R.id.name_anakpetaksatwa);
            tv_jumlahsatwa = (TextView) itemView.findViewById(R.id.name_jumlahsatwa);
            tv_waktulihat = (TextView) itemView.findViewById(R.id.name_waktulihatsatwa);
            tv_tanggal = (TextView) itemView.findViewById(R.id.name_tanggalsatwa);
            img_pemantaundetail = itemView.findViewById(R.id.img_pemantauandetail);
        }
    }

    public void popup (final String id){

//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_pemantauansatwa, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
            final String get_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
            String get_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
            String get_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
            String get_caramelihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
            String get_tanggal = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.TANGGAL_PEMANTAUAN);
            String get_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);

            TextView anakpetak = viewas.findViewById(R.id.pemantauan_petakiddetail);
            anakpetak.setText(get_anakpetak);

            TextView jenissatwa = viewas.findViewById(R.id.pemantauan_jenissatwadetail);
            jenissatwa.setText(get_jenissatwa);

            TextView jumlahsatwa = viewas.findViewById(R.id.pemantauan_jumlahsatwadetail);
            jumlahsatwa.setText(get_jumlahsatwa);

            TextView waktulihat = viewas.findViewById(R.id.pemantauan_waktulihatdetail);
            waktulihat.setText(get_waktulihat);

            TextView caramelihat = viewas.findViewById(R.id.pemantauan_caralihatdetail);
            caramelihat.setText(get_caramelihat);

            TextView tanggal = viewas.findViewById(R.id.pemantauan_tanggaldetail);
            tanggal.setText(get_tanggal);

            TextView keterangan = viewas.findViewById(R.id.pemantauan_keterangandetail);
            keterangan.setText(get_keterangan);

            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

            ImageView edit = viewas.findViewById(R.id.detail_btneditpemantauan);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new EditPemantauanFragment();
                    alert.dismiss();

                    String message = id;
                    Bundle data = new Bundle();
                    data.putString(PemantauansatwaAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeletepemantauan);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_jenissatwa;

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
                                                db.delete_one_date(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id);
                                                ListPemantauansatwaFragment.refresh_list();
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
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
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.editpemantauan.EditPemantauanFragment;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;

public class IdentifikasiTenurialAdapter extends RecyclerView.Adapter<IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder> {

    Context mContext;
    List<IdentifikasiTenurialModel> mData;
    SQLiteHandler db;

    public static final String MSG_KEY = "id";

    public IdentifikasiTenurialAdapter(Context mContext, List<IdentifikasiTenurialModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public IdentifikasiTenurialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = new SQLiteHandler(mContext);
        View vtenurial;
        vtenurial = LayoutInflater.from(mContext).inflate(R.layout.identifikasi_tenurial_item_fragment, parent, false);
        IdentifikasiTenurialViewHolder vHolder = new IdentifikasiTenurialViewHolder(vtenurial);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);

        holder.tv_jenispermasalahan.setText(mData.get(position).getJenisPermasalahan());
        holder.tv_awalkonflik.setText(mData.get(position).getAwalKonflik());
        holder.tv_petakkejadian.setText(mData.get(position).getAnakPetak());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.img_detailtenurial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class IdentifikasiTenurialViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_petakkejadian;
        private TextView tv_jenispermasalahan;
        private TextView tv_awalkonflik;
        private TextView tv_tanggal;
        private LinearLayout img_detailtenurial;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;

        public IdentifikasiTenurialViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_petakkejadian = itemView.findViewById(R.id.name_petakitem);
            tv_jenispermasalahan = itemView.findViewById(R.id.name_jenismasalahitem);
            tv_awalkonflik = itemView.findViewById(R.id.name_awalkonflikitem);
            tv_tanggal = itemView.findViewById(R.id.name_tanggalitem);
            img_detailtenurial = itemView.findViewById(R.id.img_identifikasitenurialdetail);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkrontenurialitem);
            name_info_alert = itemView.findViewById(R.id.name_info_alerttenurialitem);

        }
    }

    public void popup (final String id){

//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_identifikasitenurial, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_petakkejadian = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.ANAK_PETAK_ID);
            final String get_jenispermasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.JENIS_PERMASALAHAN);
            String get_awalkonflik = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.AWAL_KONFLIK);
            String get_tanggal = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.TANGGAL);

            String get_petak_detail = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.ANAK_PETAK_ID, get_petakkejadian, MstAnakPetakSchema.ANAK_PETAK_NAME);

            TextView anakpetak = viewas.findViewById(R.id.tenurial_anakpetakdetail);
            anakpetak.setText(get_petak_detail);
//
            TextView jenispermasalahan = viewas.findViewById(R.id.tenurial_jenispermasalahandetail);
            jenispermasalahan.setText(get_jenispermasalahan);

            TextView awalkonflik = viewas.findViewById(R.id.tenurial_awalkonflikdetail);
            awalkonflik.setText(get_awalkonflik);

            TextView tanggal = viewas.findViewById(R.id.tenurial_tahundetail);
            tanggal.setText(get_tanggal);

            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();

//            ImageView edit = viewas.findViewById(R.id.detail_btneditidentifikasitenurial);
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Fragment fragment = new EditIdentifikasiTenurial();
//                    alert.dismiss();
//
//                    String message = id;
//                    Bundle data = new Bundle();
//                    data.putString(IdentifikasiTenurialAdapter.MSG_KEY, message);
//                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
//                    fragment.setArguments(data);
//                    FragmentTransaction ft = manager.beginTransaction();
//                    ft.replace(R.id.nav_host_fragment, fragment);
//                    ft.commit();
//                }
//            });

            ImageView delete = viewas.findViewById(R.id.detail_btndeleteidentifikasitenurial);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String str_note = "Hapus : " + get_jenispermasalahan;

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
                                                db.delete_one_date(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id);
                                                ListIdentifikasiTenurialFragment.refresh_list();
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

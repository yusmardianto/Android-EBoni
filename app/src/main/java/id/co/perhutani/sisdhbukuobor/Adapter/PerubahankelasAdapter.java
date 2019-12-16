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
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.editperubahan.EditPerubahanFragment;

public class PerubahankelasAdapter extends RecyclerView.Adapter<PerubahankelasAdapter.PerubahanklsViewHolder> {

    Context mContext;
    List<PerubahankelasModel> mData;
    private SQLiteHandler db;

    public static final String MSG_KEY = "id";

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

        holder.tv_jenistanaman.setText(mData.get(position).getJenisTanaman());
        holder.tv_petakID.setText(mData.get(position).getPetakID());
        holder.tv_tahun.setText(mData.get(position).getTahun());
        holder.tv_kelashutan.setText(mData.get(position).getKelasHutan());
        holder.img_detailperubahan.setOnClickListener(new View.OnClickListener() {
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

    public static class PerubahanklsViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_jenistanaman;
        private TextView tv_petakID;
        private TextView tv_tahun;
        private TextView tv_kelashutan;
        private LinearLayout img_detailperubahan;

        public PerubahanklsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jenistanaman = (TextView) itemView.findViewById(R.id.name_jenistanamanperubahankelas);
            tv_petakID = (TextView) itemView.findViewById(R.id.name_petakidperubahankelas);
            tv_tahun = (TextView) itemView.findViewById(R.id.name_tahunperubahankelas);
            tv_kelashutan = (TextView) itemView.findViewById(R.id.name_kelashutanperubahankelas);
            img_detailperubahan = itemView.findViewById(R.id.img_perubahankelasdetail);

        }
    }

    public void popup (final String id){


//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_perubahankelas, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME,TrnPerubahanKelas._ID, id, TrnPerubahanKelas._ID);
            String get_petakdetailid = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN);
            String get_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
            String get_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
            final String get_jenistanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN);
            String get_kelashutan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN);
            String get_luasperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
            String get_jenisperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN);
            String get_kelasperkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN);
            String get_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
            String get_luasdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
            String get_jenisdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF);
            String get_kelasdefinitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF);
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
//            alertDialogBuilder.setCancelable(false);

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

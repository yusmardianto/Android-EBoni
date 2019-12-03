package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.os.Bundle;
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

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.ui.gangguan.editgangguan.EditGangguanFragment;
import id.co.perhutani.sisdhbukuobor.ui.perubahankelas.editperubahan.EditPerubahanFragment;

public class PerubahankelasAdapter extends RecyclerView.Adapter<PerubahankelasAdapter.PerubahanklsViewHolder> {

    Context mContext;
    List<PerubahankelasModel> mData;
    private SQLiteHandler db;

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

        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_noPetak.setText(mData.get(position).getNoPetak());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.tv_kelas.setText(mData.get(position).getKelas());
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
        private TextView tv_ID;
        private TextView tv_noPetak;
        private TextView tv_petak;
        private TextView tv_tanggal;
        private TextView tv_kelas;
        private LinearLayout img_detailperubahan;

        public PerubahanklsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idperubahan);
            tv_noPetak = (TextView) itemView.findViewById(R.id.name_anakpetakidperubahan);
            tv_petak = (TextView) itemView.findViewById(R.id.name_petakperubahan);
            tv_tanggal = (TextView) itemView.findViewById(R.id.name_tglperubahan);
            tv_kelas = (TextView) itemView.findViewById(R.id.name_kelasperubahan);
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

            String get_petakdetailid = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN);
            String get_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
            String get_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
            String get_jenistanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN);
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
                    data.putString(GangguanAdapter.MSG_KEY, message);
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    fragment.setArguments(data);
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            });


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }
}

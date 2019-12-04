package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;

public class PelaporanpalbatasAdapter extends RecyclerView.Adapter<PelaporanpalbatasAdapter.PelaporanViewHolder>
{
    Context mContext;
    List<PelaporanpalbatasModel> mData;
    SQLiteHandler db;

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
        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_tanggalpal.setText(mData.get(position).getTanggalPal());
        holder.tv_jenispal.setText(mData.get(position).getJenisPal());
        holder.tv_nomerpal.setText(mData.get(position).getNomerPal());
        holder.tv_jumlahpal.setText(mData.get(position).getJumlahPal());
        holder.img_detaillaporan.setOnClickListener(new View.OnClickListener() {
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

    public static class PelaporanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ID;
        private TextView tv_tanggalpal;
        private TextView tv_jenispal;
        private TextView tv_nomerpal;
        private TextView tv_jumlahpal;
        private LinearLayout img_detaillaporan;

        public PelaporanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idpal);
            tv_tanggalpal = (TextView) itemView.findViewById(R.id.name_tanggalpal);
            tv_jenispal = (TextView) itemView.findViewById(R.id.name_jenispal);
            tv_nomerpal = (TextView) itemView.findViewById(R.id.name_nomerpal);
            tv_jumlahpal = (TextView) itemView.findViewById(R.id.name_jumlahpal);
            img_detaillaporan = itemView.findViewById(R.id.img_laporanpaldetail);

        }
    }

    public void popup (final String id){


//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_pelaporanpal, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
            String get_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JENIS_PAL);
            String get_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KONDISI_PAL);
            String get_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
            String get_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
            String get_keterangnapal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);

            TextView tanggalpal = viewas.findViewById(R.id.laporanpal_tanggaldetail);
            tanggalpal.setText(get_tanggalpal);

            TextView jenispal = viewas.findViewById(R.id.laporanpal_jenisdetail);
            jenispal.setText(get_jenispal);

            TextView kondisipal = viewas.findViewById(R.id.laporanpal_kondisidetail);
            kondisipal.setText(get_kondisipal);

            TextView nopal = viewas.findViewById(R.id.laporanpal_nopaldetail);
            nopal.setText(get_nopal);

            TextView jumlahpal = viewas.findViewById(R.id.laporanpal_jumlahdetail);
            jumlahpal.setText(get_jumlahpal);

            TextView keteranganpal = viewas.findViewById(R.id.laporanpal_keterangandetail);
            keteranganpal.setText(get_keterangnapal);


            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }
}

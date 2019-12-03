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
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;

public class PemantauansatwaAdapter extends RecyclerView.Adapter<PemantauansatwaAdapter.PemantauanViewHolder> {

    Context mContext;
    List<PemantauansatwaModel> mData;
    SQLiteHandler db;

    public PemantauansatwaAdapter(Context mContext, List<PemantauansatwaModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PemantauanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        db = new SQLiteHandler(mContext);
        View vpemantauan;
        vpemantauan = LayoutInflater.from(mContext).inflate(R.layout.pemantauan_satwa_item_fragment,parent,false);
        PemantauanViewHolder vHolder = new PemantauanViewHolder(vpemantauan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PemantauanViewHolder holder, final int position) {
        db = new SQLiteHandler(mContext);
        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_petakid.setText(mData.get(position).getPetakId());
        holder.tv_jenisatwa.setText(mData.get(position).getJenis());
        holder.tv_jumlahsatwa.setText(mData.get(position).getJumlah());
        holder.tv_waktulihat.setText(mData.get(position).getWaktulihat());
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
        private TextView tv_ID;
        private TextView tv_petakid;
        private TextView tv_jenisatwa;
        private TextView tv_jumlahsatwa;
        private TextView tv_waktulihat;
        private LinearLayout img_pemantaundetail;

        public PemantauanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idsatwa);
            tv_petakid = (TextView) itemView.findViewById(R.id.name_anakpetaksatwa);
            tv_jenisatwa = (TextView) itemView.findViewById(R.id.name_jenissatwa);
            tv_jumlahsatwa = (TextView) itemView.findViewById(R.id.name_jumlahsatwa);
            tv_waktulihat = (TextView) itemView.findViewById(R.id.name_waktulihatsatwa);
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

            String get_petakid = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.PETAK_ID);
            String get_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
            String get_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
            String get_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
            String get_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
            String get_caramelihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
            String get_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);

            TextView petakid = viewas.findViewById(R.id.pemantauan_petakiddetail);
            petakid.setText(get_petakid);
//
            TextView anakpetak = viewas.findViewById(R.id.pemantauan_anakpetakdetail);
            anakpetak.setText(get_anakpetak);
//
            TextView jenissatwa = viewas.findViewById(R.id.pemantauan_jenissatwadetail);
            jenissatwa.setText(get_jenissatwa);

            TextView jumlahsatwa = viewas.findViewById(R.id.pemantauan_jumlahsatwadetail);
            jumlahsatwa.setText(get_jumlahsatwa);

            TextView waktulihat = viewas.findViewById(R.id.pemantauan_waktulihatdetail);
            waktulihat.setText(get_waktulihat);

            TextView caralihat = viewas.findViewById(R.id.pemantauan_caralihatdetail);
            caralihat.setText(get_caramelihat);

            TextView keterangan = viewas.findViewById(R.id.pemantauan_keterangandetail);
            keterangan.setText(get_keterangan);


            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }
}

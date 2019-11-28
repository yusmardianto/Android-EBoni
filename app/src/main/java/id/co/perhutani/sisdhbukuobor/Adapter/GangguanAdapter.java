package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;

public class GangguanAdapter extends RecyclerView.Adapter<GangguanAdapter.GangguanViewHolder> {

    Context mContext;
    List<GangguanModel> mData;

    SQLiteHandler db;
    private AlertDialog.Builder builder;

    public GangguanAdapter(Context mContext, List<GangguanModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public GangguanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vgangguan;
        vgangguan = LayoutInflater.from(mContext).inflate(R.layout.gangguanitem_fragment,parent,false);
        GangguanViewHolder vHolder = new GangguanViewHolder(vgangguan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GangguanViewHolder holder, final int position) {

        holder.tv_isi.setText(mData.get(position).getIsi());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_no.setText(mData.get(position).getNo());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.btn_submitgangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                asu(mData.get(position).getId());

            }
        });
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
        private LinearLayout btn_submitgangguan;
        public GangguanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_isi = itemView.findViewById(R.id.name_isi);
            tv_petak = itemView.findViewById(R.id.name_petak);
            tv_no = itemView.findViewById(R.id.name_id);
            tv_tanggal = itemView.findViewById(R.id.name_tanggal);
            btn_submitgangguan = itemView.findViewById(R.id.img_gangguandetail);
        }
    }

    public void asu (final String id){

        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_ganggaunkemanan, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_anakpetak = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
            String get_tahun = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TAHUN);
            String get_nomorha = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_HA);
            String get_kejadian = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);

            TextView anakpetak = viewas.findViewById(R.id.gangguan_petakdetailid);
            anakpetak.setText(get_anakpetak);


            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }

}

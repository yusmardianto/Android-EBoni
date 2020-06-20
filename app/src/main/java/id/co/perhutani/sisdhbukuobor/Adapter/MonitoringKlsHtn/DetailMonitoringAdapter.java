package id.co.perhutani.sisdhbukuobor.Adapter.MonitoringKlsHtn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.DetailMonitoringViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.MonitoringKlsHtnViewModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnMonitoringKlsHtn;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;

public class DetailMonitoringAdapter extends RecyclerView.Adapter<DetailMonitoringAdapter.DetailMonitoringViewHolder> {

    Context mContext;
    List<DetailMonitoringViewModel> mData;
    private int color = 0;
    SQLiteHandler db;

    public DetailMonitoringAdapter(Context mContext, List<DetailMonitoringViewModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public DetailMonitoringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row_list_detail_monitoring,parent,false);
        DetailMonitoringAdapter.DetailMonitoringViewHolder viewHolder = new DetailMonitoringAdapter.DetailMonitoringViewHolder(v);
        db = new SQLiteHandler(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMonitoringViewHolder holder, int position) {

        final String id = String.valueOf(mData.get(position).getId());

        final String get_kbd = db.getDataDetail(TrnMonitoringKlsHtn.TABLE_NAME, TrnMonitoringKlsHtn._ID,id,TrnMonitoringKlsHtn.KBD);
        final String get_nha = db.getDataDetail(TrnMonitoringKlsHtn.TABLE_NAME, TrnMonitoringKlsHtn._ID,id,TrnMonitoringKlsHtn.N_HA);
        final String get_kelashtn_id = db.getDataDetail(TrnMonitoringKlsHtn.TABLE_NAME, TrnMonitoringKlsHtn._ID,id,TrnMonitoringKlsHtn.KELAS_HUTAN_ID);
        final String get_kelashtn_name = db.getDataDetail(MstKelasHutanSchema.TABLE_NAME, MstKelasHutanSchema.KELAS_HUTAN_ID,get_kelashtn_id,MstKelasHutanSchema.KELAS_HUTAN_NAME);
        final String get_catatan = db.getDataDetail(TrnMonitoringKlsHtn.TABLE_NAME, TrnMonitoringKlsHtn._ID,id,TrnMonitoringKlsHtn.CATATAN_KHUSUS);
        final String get_tahun = db.getDataDetail(TrnMonitoringKlsHtn.TABLE_NAME, TrnMonitoringKlsHtn._ID,id,TrnMonitoringKlsHtn.TAHUN);


        holder.kbd.setText(get_kbd);
        holder.nha.setText(get_nha);
        holder.kelashutan.setText(get_kelashtn_name);
        holder.catatan.setText(get_catatan);
        holder.tahun.setText(get_tahun);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public static class DetailMonitoringViewHolder extends RecyclerView.ViewHolder {

        private TextView kbd;
        private TextView nha;
        private TextView kelashutan;
        private TextView tahun;
        private TextView catatan;

        public DetailMonitoringViewHolder(@NonNull View itemView) {
            super(itemView);
            kbd = itemView.findViewById(R.id.detail_monitoring_rv_kbd);
            nha = itemView.findViewById(R.id.detail_monitoring_rv_nha);
            tahun = itemView.findViewById(R.id.detail_monitoring_rv_tahun);
            kelashutan = itemView.findViewById(R.id.detail_monitoring_rv_kls_htn);
            catatan = itemView.findViewById(R.id.detail_monitoring_rv_catatan_khusus);
        }
    }

}

package id.co.perhutani.sisdhbukuobor.Adapter.MonitoringKlsHtn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.DetailMonitoringFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.MonitoringKlsHtnViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.DetailSusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;

public class MonitoringKlsHtnAdapter extends RecyclerView.Adapter<MonitoringKlsHtnAdapter.MonitoringViewHolder> {

    Context mContext;
    List<MonitoringKlsHtnViewModel> mData;
    private int color = 0;
    SQLiteHandler db;

    public static final String MSG_KEY = "id_anakpetak";

    public MonitoringKlsHtnAdapter(Context mContext, List<MonitoringKlsHtnViewModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MonitoringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row_list_monitoring_klshtn,parent,false);
        MonitoringKlsHtnAdapter.MonitoringViewHolder viewHolder = new MonitoringKlsHtnAdapter.MonitoringViewHolder(v);
        db = new SQLiteHandler(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonitoringViewHolder holder, int position) {
        final String id = String.valueOf(mData.get(position).getId());

        final String get_anak_petakid = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah._ID,id,TrnSusunRisalah.ANAK_PETAK_ID);
        final String get_luaspetak = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah._ID,id,TrnSusunRisalah.LUAS);
        final String get_anakpetak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,get_anak_petakid,MstAnakPetakSchema.ANAK_PETAK_NAME);
        final String get_jenistanaman = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,get_anak_petakid,MstAnakPetakSchema.JENIS_TANAMAN);

        holder.luaspetak.setText(get_luaspetak);
        holder.anakpetak.setText(get_anakpetak);
        holder.jenistanaman.setText(get_jenistanaman);
        holder.monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = get_anak_petakid;
                Bundle data = new Bundle();
                data.putString(MonitoringKlsHtnAdapter.MSG_KEY, message);
                FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                Fragment fragment = new DetailMonitoringFragment();
                fragment.setArguments(data);
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public static class MonitoringViewHolder extends RecyclerView.ViewHolder{

        private TextView anakpetak;
        private TextView luaspetak;
        private TextView jenistanaman;
        private LinearLayout monitoring;

        public MonitoringViewHolder(@NonNull View itemView) {
            super(itemView);
            anakpetak = itemView.findViewById(R.id.monitoring_klshtn_anakpetak);
            luaspetak = itemView.findViewById(R.id.monitoring_klshtn_luas);
            jenistanaman = itemView.findViewById(R.id.monitoring_klshtn_jenistanaman);
            monitoring = itemView.findViewById(R.id.linear_row_monitoring_klshtn);
        }
    }

}

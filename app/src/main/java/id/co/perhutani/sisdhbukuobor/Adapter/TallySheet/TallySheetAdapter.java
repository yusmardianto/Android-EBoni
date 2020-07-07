package id.co.perhutani.sisdhbukuobor.Adapter.TallySheet;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.SusunRisalahAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.DetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.EditTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TabDetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.DetailSusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

public class TallySheetAdapter extends RecyclerView.Adapter<TallySheetAdapter.TallySheetViewHolder> {

    private SessionManager session;
    Context mContext;
    List<TallySheetModel> mData;
    SQLiteHandler db;
    private int color = 0;

    public static final String MSG_KEY = "id";
    public static final String MSG_KEY_TALLYSHEET = "id_tallysheet";

    public TallySheetAdapter(Context mContext, List<TallySheetModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public TallySheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vtallysheet;
        db = new SQLiteHandler(mContext);
        vtallysheet = LayoutInflater.from(mContext).inflate(R.layout.row_list_tally_sheet,parent,false);
        TallySheetAdapter.TallySheetViewHolder vHolder = new TallySheetAdapter.TallySheetViewHolder(vtallysheet);

        session = new SessionManager(mContext);


        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TallySheetViewHolder holder, int position) {

        final String id = String.valueOf(mData.get(position).getId());

        final String get_kph = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id,TrnTallySheet.KPH_NAME);
        final String get_petak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id,TrnTallySheet.PETAK_NAME);
        final String get_bagianhutan = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id,TrnTallySheet.BAGIAN_HUTAN_NAME);

        holder.kph.setText(get_kph);
        holder.bagianhutan.setText(get_bagianhutan);
        holder.petak.setText(get_petak);

        try{
            String sudah_update = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id, TrnTallySheet.KET9);
            if (sudah_update.equals("2")||sudah_update.equals("1")){
                holder.name_info_alert.setVisibility(View.VISIBLE);
                holder.name_data_sinkron.setVisibility(View.VISIBLE);
            }
            else {
                holder.name_info_alert.setVisibility(View.GONE);
                holder.name_data_sinkron.setVisibility(View.GONE);
            }
        }
        catch (Exception e){

        }


        try {
            String status_sync = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id, TrnTallySheet.KET9);
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
        catch (Exception e){

        }


        holder.linear_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.setPreferences(mContext, "ses_id_tallysheet", id);
//                String message = id;
//                Bundle data = new Bundle();
//                data.putString(TallySheetAdapter.MSG_KEY_TALLYSHEET, message);
                FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                Fragment fragment = new TabDetailTallySheetFragment();
//                fragment.setArguments(data);
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

//        holder.linear_ubah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = id;
//                Bundle data = new Bundle();
//                data.putString(TallySheetAdapter.MSG_KEY, message);
//                FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
//                Fragment fragment = new EditTallySheetFragment();
//                fragment.setArguments(data);
//                FragmentTransaction ft = manager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public static class TallySheetViewHolder extends RecyclerView.ViewHolder{

        private TextView kph;
        private TextView bagianhutan;
        private TextView petak;
        private TextView name_data_sinkron;
//        private LinearLayout linear_ubah;
        private LinearLayout linear_lihat;
        private ImageView name_info_alert;

        public TallySheetViewHolder(@NonNull View itemView) {
            super(itemView);
            kph = itemView.findViewById(R.id.list_ts_kph);
            bagianhutan = itemView.findViewById(R.id.list_ts_bagian_hutan);
            petak = itemView.findViewById(R.id.list_ts_petak);
            linear_lihat = itemView.findViewById(R.id.linear_ts_show);
//            linear_ubah = itemView.findViewById(R.id.linear_ts_edit);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_ts);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_ts);
        }
    }
}

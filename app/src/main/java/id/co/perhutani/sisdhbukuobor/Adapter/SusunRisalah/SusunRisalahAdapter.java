package id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah;

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
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.DetailSusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderViewModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;

public class SusunRisalahAdapter extends RecyclerView.Adapter<SusunRisalahAdapter.SusunRisalahViewHolder> {

    Context mContext;
    List<SusunRisalahViewModel> mData;
    private int color = 0;
    SQLiteHandler db;

    public static final String MSG_KEY = "id_anakpetak";

    public SusunRisalahAdapter(Context mContext, List<SusunRisalahViewModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public SusunRisalahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row_list_risalah_blmacc,parent,false);
        SusunRisalahViewHolder viewHolder = new SusunRisalahViewHolder( v);
        db = new SQLiteHandler(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SusunRisalahViewHolder holder, int position) {

        final String id = String.valueOf(mData.get(position).getId());

        final String get_anak_petakid = db.getDataDetail(TrnSusunRisalah.TABLE_NAME, TrnSusunRisalah._ID,id,TrnSusunRisalah.ANAK_PETAK_ID);
        final String get_kph = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,get_anak_petakid,MstAnakPetakSchema.KPH);
        final String get_anakpetak = db.getDataDetail(MstAnakPetakSchema.TABLE_NAME, MstAnakPetakSchema.KODE_ANAKPETAK,get_anak_petakid,MstAnakPetakSchema.ANAK_PETAK_NAME);

        holder.kph.setText(get_kph);
        holder.anakpetak.setText(get_anakpetak);
        holder.linear_risalah_blmacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = get_anak_petakid;
                Bundle data = new Bundle();
                data.putString(SusunRisalahAdapter.MSG_KEY, message);
                FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                Fragment fragment = new DetailSusunRisalahFragment();
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

    public static class SusunRisalahViewHolder extends RecyclerView.ViewHolder{

        private TextView kph;
        private TextView anakpetak;
        private LinearLayout linear_risalah_blmacc;


        public SusunRisalahViewHolder(@NonNull View itemView) {

            super(itemView);
            kph = itemView.findViewById(R.id.kph);
            anakpetak = itemView.findViewById(R.id.anakpetak);
            linear_risalah_blmacc = itemView.findViewById(R.id.linear_row_risalah_blmacc);

        }
    }

}

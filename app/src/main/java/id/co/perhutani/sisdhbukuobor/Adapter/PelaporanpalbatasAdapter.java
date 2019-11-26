package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.R;

public class PelaporanpalbatasAdapter extends RecyclerView.Adapter<PelaporanpalbatasAdapter.PelaporanViewHolder>
{
    Context mContext;
    List<PelaporanpalbatasModel> mData;

    public PelaporanpalbatasAdapter(Context mContext, List<PelaporanpalbatasModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PelaporanpalbatasAdapter.PelaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vpelaporan;
        vpelaporan = LayoutInflater.from(mContext).inflate(R.layout.laporan_pal_batas_item_fragment,parent,false);
        PelaporanpalbatasAdapter.PelaporanViewHolder vHolder = new PelaporanpalbatasAdapter.PelaporanViewHolder(vpelaporan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PelaporanpalbatasAdapter.PelaporanViewHolder holder, int position) {

        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_nopal.setText(mData.get(position).getNoPal());
        holder.tv_kondisi.setText(mData.get(position).getKondisi());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class PelaporanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ID;
        private TextView tv_petak;
        private TextView tv_nopal;
        private TextView tv_kondisi;

        public PelaporanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idpal);
            tv_petak = (TextView) itemView.findViewById(R.id.name_petakpal);
            tv_nopal = (TextView) itemView.findViewById(R.id.name_nopal);
            tv_kondisi = (TextView) itemView.findViewById(R.id.name_kondisipal);

        }
    }
}

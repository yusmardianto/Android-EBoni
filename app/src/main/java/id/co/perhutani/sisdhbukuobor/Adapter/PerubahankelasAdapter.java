package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.R;

public class PerubahankelasAdapter extends RecyclerView.Adapter<PerubahankelasAdapter.PerubahanklsViewHolder> {

    Context mContext;
    List<PerubahankelasModel> mData;

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
    public void onBindViewHolder(@NonNull PerubahanklsViewHolder holder, int position) {

        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_noPetak.setText(mData.get(position).getNoPetak());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.tv_kelas.setText(mData.get(position).getKelas());
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

        public PerubahanklsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idperubahan);
            tv_noPetak = (TextView) itemView.findViewById(R.id.name_anakpetakidperubahan);
            tv_petak = (TextView) itemView.findViewById(R.id.name_petakperubahan);
            tv_tanggal = (TextView) itemView.findViewById(R.id.name_tglperubahan);
            tv_kelas = (TextView) itemView.findViewById(R.id.name_kelasperubahan);

        }
    }
}

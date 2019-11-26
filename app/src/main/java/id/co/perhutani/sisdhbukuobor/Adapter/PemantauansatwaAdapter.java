package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.R;

public class PemantauansatwaAdapter extends RecyclerView.Adapter<PemantauansatwaAdapter.PemantauanViewHolder> {

    Context mContext;
    List<PemantauansatwaModel> mData;

    public PemantauansatwaAdapter(Context mContext, List<PemantauansatwaModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PemantauanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vgangguan;
        vgangguan = LayoutInflater.from(mContext).inflate(R.layout.pemantauan_satwa_item_fragment,parent,false);
        PemantauanViewHolder vHolder = new PemantauanViewHolder(vgangguan);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PemantauanViewHolder holder, int position) {

        holder.tv_ID.setText(mData.get(position).getID());
        holder.tv_anakpetak.setText(mData.get(position).getPetak());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.tv_jenis.setText(mData.get(position).getJenis());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class PemantauanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_ID;
        private TextView tv_anakpetak;
        private TextView tv_tanggal;
        private TextView tv_jenis;

        public PemantauanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ID = (TextView) itemView.findViewById(R.id.name_idsatwa);
            tv_anakpetak = (TextView) itemView.findViewById(R.id.name_anakpetaksatwa);
            tv_tanggal = (TextView) itemView.findViewById(R.id.name_tglsatwa);
            tv_jenis = (TextView) itemView.findViewById(R.id.name_jenissatwa);

        }
    }
}

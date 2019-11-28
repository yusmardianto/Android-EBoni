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

import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;

public class GangguanAdapter extends RecyclerView.Adapter<GangguanAdapter.GangguanViewHolder> {

    Context mContext;
    List<GangguanModel> mData;

    private AlertDialog.Builder builder;
    View viewasu;

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
    public void onBindViewHolder(@NonNull GangguanViewHolder holder, int position) {

        holder.tv_isi.setText(mData.get(position).getIsi());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_no.setText(mData.get(position).getNo());
        holder.tv_tanggal.setText(mData.get(position).getTanggal());
        holder.btn_submitgangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(mContext);
                viewasu = factory.inflate(R.layout.popup_detail_ganggaunkemanan, null);

                builder.setView(viewasu);
                builder.show();

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
}

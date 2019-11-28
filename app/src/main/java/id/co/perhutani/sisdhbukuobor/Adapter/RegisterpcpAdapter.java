package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;

public class RegisterpcpAdapter extends RecyclerView.Adapter<RegisterpcpAdapter.RegisterpcpViewHolder>
{
    Context mContext;
    List<RegisterpcpModel> mData;

    public RegisterpcpAdapter(Context mContext, List<RegisterpcpModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RegisterpcpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vregisterpcp;
        vregisterpcp = LayoutInflater.from(mContext).inflate(R.layout.register_pcp_item_fragment,parent,false);
        RegisterpcpViewHolder vHolder = new RegisterpcpViewHolder(vregisterpcp);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterpcpViewHolder holder, int position) {

        holder.tv_nopcp.setText(mData.get(position).getNoPcp());
        holder.tv_petakpcp.setText(mData.get(position).getPetakPcP());
        holder.tv_tahun.setText(mData.get(position).getTahun());
        holder.tv_keterangan.setText(mData.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RegisterpcpViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nopcp;
        private TextView tv_petakpcp;
        private TextView tv_tahun;
        private TextView tv_keterangan;

        public RegisterpcpViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nopcp = itemView.findViewById(R.id.name_nopcp);
            tv_petakpcp = itemView.findViewById(R.id.name_petakpcp);
            tv_tahun = itemView.findViewById(R.id.name_tahunpcp);
            tv_keterangan = itemView.findViewById(R.id.name_ketpcp);
        }
    }
}

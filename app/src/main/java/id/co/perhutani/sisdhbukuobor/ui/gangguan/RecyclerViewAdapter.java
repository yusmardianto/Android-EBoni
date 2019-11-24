package id.co.perhutani.sisdhbukuobor.ui.gangguan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Myholder>
{
    Context mContext;
    List<ListViewGangguan> mData;

    public RecyclerViewAdapter(Context mContext, ArrayList<ListViewGangguan> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.gangguanitem_fragment, parent, false);
        Myholder vHolder = new Myholder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.tv_isi.setText(mData.get(position).getIsi());
        holder.tv_petak.setText(mData.get(position).getPetak());
        holder.tv_no.setText(mData.get(position).getNo());
        holder.tv_tgl.setText(mData.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Myholder extends RecyclerView.ViewHolder
    {
        private TextView tv_isi;
        private TextView tv_petak;
        private TextView tv_no;
        private TextView tv_tgl;

        public Myholder(View ItemView)
        {
            super (ItemView);

            tv_isi =  (TextView) ItemView.findViewById(R.id.name_isi);
            tv_petak = (TextView) ItemView.findViewById(R.id.name_petak);
            tv_no = (TextView) ItemView.findViewById(R.id.name_id);
            tv_tgl = (TextView) ItemView.findViewById(R.id.name_tanggal);
        }
    }
}

package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianViewModel;
import id.co.perhutani.sisdhbukuobor.R;

public class PersemaianMenuAdapter extends RecyclerView.Adapter<PersemaianMenuAdapter.ScheduleViewHolder> {

    View x;
    Context mContext;
    List<PersemaianViewModel> mdata;
    private int color = 0;
    public static final String msg_id_event = "msg_id_event";
    public static final String msg_date_event = "msg_date_event";
    public static final String msg_desc_event = "msg_desc_event";


    public PersemaianMenuAdapter(Context mContext, List<PersemaianViewModel> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        x = LayoutInflater.from(mContext).inflate(R.layout.row_list_persemaian,parent,false);
        ScheduleViewHolder vHolder = new ScheduleViewHolder(x);

        return vHolder;
    }

    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_enter_from_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, final int position) {

        setAnimation(holder.itemView, position);
        final String id_event_day = mdata.get(position).getId();
        holder.txt_judul.setText(mdata.get(position).getId()+". "+mdata.get(position).getAktifitas_persemaian());
        holder.txt_keterangan.setText("Keterangan ............. rekap, satuan,dll");

    }

    @Override
    public int getItemCount() {
        return mdata.size();

    }
    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_judul;
        private TextView txt_keterangan;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_judul = itemView.findViewById(R.id.txt_judul);
            txt_keterangan = itemView.findViewById(R.id.txt_keterangan);
        }
    }

}

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

import id.co.perhutani.sisdhbukuobor.FragmentUi.tanaman.TanamanViewModel;
import id.co.perhutani.sisdhbukuobor.R;

public class TanamanMenuAdapter {

        View x;
        Context mContext;
        List<TanamanViewModel> mdata;
        private int color = 0;
        public static final String msg_id_event = "msg_id_event";
        public static final String msg_date_event = "msg_date_event";
        public static final String msg_desc_event = "msg_desc_event";


        public TanamanMenuAdapter(Context mContext, List<TanamanViewModel> mdata) {
            this.mContext = mContext;
            this.mdata = mdata;
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

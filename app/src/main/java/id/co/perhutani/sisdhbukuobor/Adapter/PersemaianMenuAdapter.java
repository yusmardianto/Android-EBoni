package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.DistribusiBibit.ListDistribusiBibitFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.MutasiBibit.ListMutasiBibitFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.OverSpin.ListOverSpinFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian.PasangBatasPersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PembuatanBedengSapih.ListPembuatanBedengSapihFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.Pemeliharaan.ListPemeliharaanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PenaburanBenih.ListPenaburanBenihFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersiapanLahan.ListPersiapanLahanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersiapanSapra.ListPersiapanSapraFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.SeleksiBibitSiapTanam.ListSeleksiBibitSiapTanamFragment;
import id.co.perhutani.sisdhbukuobor.MainActivity;
import id.co.perhutani.sisdhbukuobor.R;

public class PersemaianMenuAdapter extends RecyclerView.Adapter<PersemaianMenuAdapter.ScheduleViewHolder> {

    View x;
    Context mContext;
    List<PersemaianViewModel> mdata;
    SQLiteHandler db;
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
    public void onBindViewHolder(@NonNull final ScheduleViewHolder holder, final int position) {

//        setAnimation(holder.itemView, position);
        final String id_event_day = mdata.get(position).getId();

        holder.liner_event_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AjnClass.showAlert(mContext,id_event_day);
                if(id_event_day.equals("1"))
                {
                    holder.txt_judul.setText("Pasang Batas Persemaian");
                    holder.txt_keterangan.setText("Persiapan Pasang Batas Persemaian");

                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new PasangBatasPersemaianFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("2"))
                {
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListPersiapanLahanFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("3")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListPersiapanSapraFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("4")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListPembuatanBedengSapihFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("5")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListPenaburanBenihFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("6")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListOverSpinFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("7")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListPemeliharaanFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("8")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListSeleksiBibitSiapTanamFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("9")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListMutasiBibitFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else if (id_event_day.equals("10")){
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new ListDistribusiBibitFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
            }
        });
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
        private RelativeLayout liner_event_days;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_judul = itemView.findViewById(R.id.txt_judul);
            txt_keterangan = itemView.findViewById(R.id.txt_keterangan);
            liner_event_days = itemView.findViewById(R.id.liner_event_days);
        }
    }

//    public void pop_pup(final String id) {
//        try {
//            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//            final View viewpopup = layoutInflater.inflate(R.layout.tambah_pasang_batas_persemaian, null);
//            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
//            alertDialogBuilder.setView(viewpopup);
//
//            final android.app.AlertDialog alert = alertDialogBuilder.create();
//            alert.show();
//
//        } catch (Exception ex) {
//            AjnClass.showAlert(mContext,ex.toString());
//        }
//    }
}

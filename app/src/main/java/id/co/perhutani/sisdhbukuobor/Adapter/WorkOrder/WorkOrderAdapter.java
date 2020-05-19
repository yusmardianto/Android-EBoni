package id.co.perhutani.sisdhbukuobor.Adapter.WorkOrder;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.GangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderViewModel;
import id.co.perhutani.sisdhbukuobor.MainActivity;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnWorkOrder;

public class WorkOrderAdapter extends RecyclerView.Adapter<WorkOrderAdapter.WorkorderViewHolder> {

    Context mContext;
    List<WorkOrderViewModel> mData;
    private int color = 0;
    SQLiteHandler db;

    public WorkOrderAdapter(Context mContext, List<WorkOrderViewModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public WorkorderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.row_list_workorder,parent,false);
        WorkorderViewHolder viewHolder = new WorkorderViewHolder(v);
        db = new SQLiteHandler(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkorderViewHolder holder, int position) {

        holder.workorder.setText(mData.get(position).getWorkorder());
        holder.jeniskegiatan.setText(mData.get(position).getJeniskegiatan());
        holder.tanggal.setText(mData.get(position).getTanggal());
        holder.dari.setText(mData.get(position).getDari());

        final String get_jeniskegiatan = db.getDataDetail(TrnWorkOrder.TABLE_NAME, TrnWorkOrder.JENISKEGIATAN,mData.get(position).getJeniskegiatan(),TrnWorkOrder.JENISKEGIATAN);

        holder.linear_workorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(get_jeniskegiatan.equals("Persemaian"))
                {

                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new PersemaianFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }
                else {
                    FragmentManager manager = ((MainActivity) mContext).getSupportFragmentManager();
                    Fragment fragment = new GangguanFragment();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                }

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

    public static class WorkorderViewHolder extends RecyclerView.ViewHolder {

        private TextView workorder;
        private TextView jeniskegiatan;
        private TextView tanggal;
        private TextView dari;
        private LinearLayout linear_workorder;

        public WorkorderViewHolder(@NonNull View itemView) {
            super(itemView);
            workorder = itemView.findViewById(R.id.workorder);
            jeniskegiatan = itemView.findViewById(R.id.jeniskegiatan);
            tanggal = itemView.findViewById(R.id.tanggal);
            dari = itemView.findViewById(R.id.dari);
            linear_workorder = itemView.findViewById(R.id.linear_row_workorder);
        }
    }

}

//package id.co.perhutani.sisdhbukuobor.Adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
//import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
//import id.co.perhutani.sisdhbukuobor.R;
//
//public class IdentifikasiTenurialAdapter extends RecyclerView.Adapter<IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder> {
//    Context mContext;
//    List<IdentifikasiTenurialModel> mData;
//
//    SQLiteHandler db;
//
//    @NonNull
//    @Override
//    public IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View vtenurial;
//        db = new SQLiteHandler(mContext);
//        vtenurial = LayoutInflater.from(mContext).inflate(R.layout.identifikasi_tenurial_item_fragmenten, parent, false);
//        IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder vHolder = new IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder(vtenurial);
//
//        return vHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull IdentifikasiTenurialAdapter.IdentifikasiTenurialViewHolder holder, final int position) {
//        db = new SQLiteHandler(mContext);
//
//        holder.tv_jenispermasalahan.setText(mData.get(position).getJenisPermasalahan());
//        holder.tv_awalkonflik.setText(mData.get(position).getAwalKonflik());
//        holder.tv_petakkejadian.setText(mData.get(position).getAnakPetak());
//        holder.tv_tanggal.setText(mData.get(position).getTanggal());
//        holder.img_detailtenurial.setOnClickListener(new View.OnClickListener();
//
//        public static class IdentifikasiTenurialViewHolder extends RecyclerView.ViewHolder {
//
//            private TextView tv_jenispermasalahan;
//            private TextView tv_awalkonflik;
//            private TextView tv_petakkejadian;
//            private TextView tv_tanggal;
//            private LinearLayout img_detailtenurial;
//            private TextView name_data_sinkron;
//            private ImageView name_info_alert;
//
//
//            public IdentifikasiTenurialViewHolder(@NonNull View itemView) {
//                super(itemView);
//
//                tv_jenispermasalahan = itemView.findViewById(R.id.name_jenismasalahitem);
//                tv_awalkonflik = itemView.findViewById(R.id.name_awalkonflikitem);
//                tv_petakkejadian = itemView.findViewById(R.id.name_petakitem);
//                tv_tanggal = itemView.findViewById(R.id.name_tanggalitem);
//                img_detailtenurial = itemView.findViewById(R.id.img_identifikasitenurialdetail);
//                name_data_sinkron = itemView.findViewById(R.id.name_data_sinkrontenurialitem);
//                name_info_alert = itemView.findViewById(R.id.name_info_alerttenurialitem);
//            }
//        }
//    }
//}

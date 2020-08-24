package id.co.perhutani.sisdhbukuobor.Adapter.TallySheet;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.GangguanAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.GenerateAESAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon.PuPohonModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TabDetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.editgangguan.EditGangguanFragment;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstKonversiKeliling;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

public class PengukuranPohonAdapter extends RecyclerView.Adapter<PengukuranPohonAdapter.pengukuranPohonViewHolder>{

    private SessionManager session;
    Context mContext;
    List<PuPohonModel> mData;
    SQLiteHandler db;
    private int color = 0;
    private AlertDialog.Builder builder;
    private TextView edt_no_pohon, edt_no_pu, edt_kwb,edt_peninggi_pohon,edt_keliling_pohon, edt_bidang_dasar;

    public PengukuranPohonAdapter(Context mContext, List<PuPohonModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public pengukuranPohonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vPuPohon;
        db = new SQLiteHandler(mContext);
        vPuPohon = LayoutInflater.from(mContext).inflate(R.layout.row_list_pengukuran_pohon,parent,false);
        PengukuranPohonAdapter.pengukuranPohonViewHolder vHolder = new PengukuranPohonAdapter.pengukuranPohonViewHolder(vPuPohon);

        session = new SessionManager(mContext);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pengukuranPohonViewHolder holder, final int position) {

        final String id = String.valueOf(mData.get(position).getId());

        final String get_nopohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id,TrnPuPohon.NO_POHON);
        final String get_kelpohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id,TrnPuPohon.KELILING_POHON);
        final String get_pngpohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id,TrnPuPohon.PENINGGI_POHON);
        final String get_bdg_dsr = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id,TrnPuPohon.BIDANG_DASAR);
        final String get_ts_id = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id,TrnPuPohon.TS_ID);

        holder.nopohon.setText(get_nopohon);
        holder.kelpohon.setText(get_kelpohon);
        holder.peninggipohon.setText(get_pngpohon);
        holder.bidangdasar.setText(get_bdg_dsr);
        holder.edit_pengukuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup(mData.get(position).getId());
            }
        });

//        try {
//            String status_sync = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id, TrnPuPohon.KET9);
//            if (status_sync.equals("3")) {
//                holder.name_data_sinkron.setVisibility(View.INVISIBLE);
//                holder.name_info_alert.setVisibility(View.INVISIBLE);
//
//            }  else {
//                holder.name_data_sinkron.setVisibility(View.VISIBLE);
//                holder.name_info_alert.setVisibility(View.VISIBLE);
//            }
//        }
//        catch (Exception e){
//
//        }

        try {
            String status_sync = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID,id, TrnPuPohon.KET9);
            if (status_sync.equals("1")) {

                holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_check_circle_green_24dp));
                holder.name_data_sinkron.setText("Sudah terkirim keserver");
                holder.name_data_sinkron.setTextColor(Color.rgb(146,198,91));
            }  else {
                holder.name_data_sinkron.setText("Belum terkirim keserver");
                holder.name_data_sinkron.setTextColor(Color.rgb(228,0,4));
                holder.name_info_alert.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_info_outline_red_24dp));
            }
        }
        catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setColor(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

    public static class pengukuranPohonViewHolder extends RecyclerView.ViewHolder{

        private TextView nopohon;
        private TextView kelpohon;
        private TextView peninggipohon;
        private TextView bidangdasar;
        private TextView name_data_sinkron;
        private ImageView name_info_alert;
        private LinearLayout edit_pengukuran;

        public pengukuranPohonViewHolder(@NonNull View itemView) {
            super(itemView);
            nopohon = itemView.findViewById(R.id.list_pu_nopohon);
            kelpohon = itemView.findViewById(R.id.list_pu_kelilingpohon);
            peninggipohon = itemView.findViewById(R.id.list_pu_peninggipohon);
            bidangdasar = itemView.findViewById(R.id.list_pu_bdgdsr);
            name_data_sinkron = itemView.findViewById(R.id.name_data_sinkron_pohon);
            name_info_alert = itemView.findViewById(R.id.name_info_alert_pohon);
            edit_pengukuran = itemView.findViewById(R.id.linear_edit_pengukuran);
        }
    }

    public void popup (final String id){
        try {
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.edit_pu_pohon, null);

            String get_no_pohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID, id, TrnPuPohon.NO_POHON);
            String get_keliling_pohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID, id, TrnPuPohon.KELILING_POHON);
            String get_bidang_dasar = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID, id, TrnPuPohon.BIDANG_DASAR);
            final String get_ts_id = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID, id, TrnPuPohon.TS_ID);
            String get_peninggi_pohon = db.getDataDetail(TrnPuPohon.TABLE_NAME, TrnPuPohon._ID, id, TrnPuPohon.PENINGGI_POHON);

            edt_no_pu = viewas.findViewById(R.id.edit_no_petak_ukur);
            edt_no_pu.setText(get_ts_id);

            edt_no_pohon = viewas.findViewById(R.id.edit_pu_no_pohon);
            edt_no_pohon.setText(get_no_pohon);

            edt_keliling_pohon = viewas.findViewById(R.id.edit_pu_keliling_pohon);
            edt_keliling_pohon.setText(get_keliling_pohon);

            edt_bidang_dasar = viewas.findViewById(R.id.edit_pu_bidang_dasar);
            edt_bidang_dasar.setText(get_bidang_dasar);

            edt_peninggi_pohon = viewas.findViewById(R.id.edit_pu_peninggi_pohon);
            edt_peninggi_pohon.setText(get_peninggi_pohon);

            edt_kwb = viewas.findViewById(R.id.edit_pu_kualitas_batang);

            alertDialogBuilder.setView(viewas);
//           final android.app.AlertDialog alert = alertDialogBuilder.create();

            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    try {

                        if (!vallidasi()) {
                            return;
                        }

                        PuPohonModel ubah = new PuPohonModel();
                        String get_keliling = edt_keliling_pohon.getText().toString();
                        final String get_bidang_dasar = db.getDataDetail(MstKonversiKeliling.TABLE_NAME, MstKonversiKeliling.KELILING,get_keliling,MstKonversiKeliling.BIDANG_DASAR);
                        ubah.setId(id);
                        ubah.setTs_id(get_ts_id);
                        ubah.setNo_pohon(edt_no_pohon.getText().toString());
                        ubah.setKeliling_pohon(get_keliling);
                        ubah.setBidang_dasar(get_bidang_dasar);
                        ubah.setPeninggi_pohon(edt_peninggi_pohon.getText().toString());
                        ubah.setKet9("2");

                        db.EditDataPengukuranPohon(ubah);

                        Toast.makeText(mContext, "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                        Fragment fragment = new TabDetailTallySheetFragment();
                        FragmentTransaction ft = manager.beginTransaction();
                        ft.replace(R.id.nav_host_fragment, fragment);
                        ft.commit();
                    }
                    catch (Exception e) {
                        AjnClass.showAlert(mContext, "error \n\n" + e.toString());
                    }

                }

            });
            alertDialogBuilder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }

    private boolean vallidasi() {
        if (edt_no_pohon.getText().toString().equals("") || edt_no_pohon.getText().toString().equals("0") || edt_no_pohon.getText().toString().equals(" ") || edt_no_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(mContext, "Nomor Pohon harus diisi");
            return false;

        }else if (edt_keliling_pohon.getText().toString().equals("") || edt_keliling_pohon.getText().toString().equals("0") || edt_keliling_pohon.getText().toString().equals(" ") || edt_keliling_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(mContext, "Keliling Pohon harus diisi");
            return false;

        }

        return true;
    }

}

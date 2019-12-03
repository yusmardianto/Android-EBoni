package id.co.perhutani.sisdhbukuobor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;

public class RegisterpcpAdapter extends RecyclerView.Adapter<RegisterpcpAdapter.RegisterpcpViewHolder>
{
    Context mContext;
    List<RegisterpcpModel> mData;
    SQLiteHandler db;

    public RegisterpcpAdapter(Context mContext, List<RegisterpcpModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RegisterpcpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vregisterpcp;
        db = new SQLiteHandler(mContext);
        vregisterpcp = LayoutInflater.from(mContext).inflate(R.layout.register_pcp_item_fragment,parent,false);
        RegisterpcpViewHolder vHolder = new RegisterpcpViewHolder(vregisterpcp);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterpcpViewHolder holder, final int position) {

        holder.tv_id.setText(mData.get(position).getID());
        holder.tv_nopcp.setText(mData.get(position).getNoPcp());
        holder.tv_petakid.setText(mData.get(position).getPetakId());
        holder.tv_tahunpcp.setText(mData.get(position).getTahun());
        holder.tv_bonitapcp.setText(mData.get(position).getBonita());
        holder.img_detailregisterpcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popup(mData.get(position).getID());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RegisterpcpViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private TextView tv_nopcp;
        private TextView tv_petakid;
        private TextView tv_tahunpcp;
        private TextView tv_bonitapcp;
        private LinearLayout img_detailregisterpcp;

        public RegisterpcpViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.name_idpcp);
            tv_nopcp = itemView.findViewById(R.id.name_nomerpcp);
            tv_petakid = itemView.findViewById(R.id.name_petakpcp);
            tv_tahunpcp = itemView.findViewById(R.id.name_tahunpcp);
            tv_bonitapcp = itemView.findViewById(R.id.name_bonitapcp);
            img_detailregisterpcp = itemView.findViewById(R.id.img_registerpcpdetail);
        }
    }

    public void popup (final String id){


//        AjnClass.showAlert(mContext,id);
        try {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            final View viewas = layoutInflater.inflate(R.layout.popup_detail_registerpcp, null);
            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
            alertDialogBuilder.setView(viewas);

            String get_nopcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NO_PCP);
            String get_petakid = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.PETAK_ID);
            String get_anakpetakid = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.ANAK_PETAK_ID);
            String get_tahunpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_PCP);
            String get_luasbaku = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BAKU);
            String get_luasblok = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BLOK);
            String get_umur = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UMUR);
            String get_ratarata = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.RATARATA_KELILING);
            String get_bonita = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.BONITA);
            String get_mpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.M_PCP);
            String get_normalpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NORMAL_PCP);
            String get_nmati = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_MATI);
            String get_tahunjarangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_JARANGAN);
            String get_keterangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KETERANGAN);

            TextView nopcp = viewas.findViewById(R.id.registerpcp_nopcpdetail);
            nopcp.setText(get_nopcp);

            TextView petakid = viewas.findViewById(R.id.registerpcp_petakiddetail);
            petakid.setText(get_petakid);

            TextView anakpetakid = viewas.findViewById(R.id.registerpcp_anakpetakiddetail);
            anakpetakid.setText(get_anakpetakid);

            TextView tahun = viewas.findViewById(R.id.registerpcp_tahunpcpdetail);
            tahun.setText(get_tahunpcp);

            TextView luasbaku = viewas.findViewById(R.id.registerpcp_luasbakudetail);
            luasbaku.setText(get_luasbaku);

            TextView luasblock = viewas.findViewById(R.id.registerpcp_luasblockdetail);
            luasblock.setText(get_luasblok);

            TextView umur = viewas.findViewById(R.id.registerpcp_umur);
            umur.setText(get_umur);

            TextView ratarata = viewas.findViewById(R.id.registerpcp_ratakelilingdetail);
            ratarata.setText(get_ratarata);

            TextView bonita = viewas.findViewById(R.id.registerpcp_bonitadetail);
            bonita.setText(get_bonita);

            TextView mpcp = viewas.findViewById(R.id.registerpcp_mpcpdetail);
            mpcp.setText(get_mpcp);

            TextView normalpcp = viewas.findViewById(R.id.registerpcp_normalpcpndetail);
            normalpcp.setText(get_normalpcp);

            TextView nmati = viewas.findViewById(R.id.registerpcp_nmatidetail);
            nmati.setText(get_nmati);

            TextView tahunjarangan = viewas.findViewById(R.id.registerpcp_tahunjarangandetail);
            tahunjarangan.setText(get_tahunjarangan);

            TextView keterangan = viewas.findViewById(R.id.registerpcp_keterangandetail);
            keterangan.setText(get_keterangan);


            alertDialogBuilder.setView(viewas);
//            alertDialogBuilder.setCancelable(false);


            final android.app.AlertDialog alert = alertDialogBuilder.create();
            alert.show();


        } catch (Exception ex) {
            AjnClass.showAlert(mContext,ex.toString());
        }
    }
}

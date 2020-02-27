package id.co.perhutani.sisdhbukuobor.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.LoginActivity;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;


public class ThreadSendToAPI extends Thread {


    private SQLiteHandler db;
    Context myContext;
    Boolean cek_feedback_api = false;
    String result_api = "";

    public ThreadSendToAPI(Context context) {
        db = new SQLiteHandler(context);
        myContext = context;
    }


    @Override
    public void run() {
        while (true) {
            result_api = "";
            try { ;
                if (!isOnline()) {
                    Log.i("JSON_BACKGROUND_SERVICE", "Tidak ada koneksi internet yeeeeee");
                } else {
                    Log.i("JSON_BACKGROUND_SERVICE", "Ada koneksi internet joshh " );

                    sendToServerTambah();
                    sendToServerPerubahanKelas();
                    sendToServerInteraksimdh();
                    sendToServerPemantauanSatwa();
                    sendToServerPAL();
                    sendToServerPCP();
                    sendToServerTenurial();

                    sendToServerEdit();
                    sendToServerEditPerubahanKelas();
                    sendToServerEditInteraksiMdh();
                    sendToServerEditPemantauanSatwa();
                    sendToServerEditPAL();
                    sendToServerEditPCP();
                    sendToServerEditTenurial();

                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Log.i("JSON_ERROR", e.toString());
                e.printStackTrace();
            }
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void sendToServerTambah() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_tambah_data_gangguanhutan_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerPerubahanKelas() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_PERUBAHAN_KELAS " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_perubahankelas_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerInteraksimdh() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_INTERAKSI_MDH " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_tambah_data_interaksimdh_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerPemantauanSatwa() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_PEMANTAUAN_SATWA " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_pemantauansatwa_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerPAL() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_LAPORAN_PAL " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_laporanlpalbatas_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerPCP() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_REGISTER_PCP " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_registerpcp_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    private void sendToServerTenurial() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
                    " WHERE  KET9=0 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_identifikasitenurial_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));


                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_tambah_data_gangguanhutan_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_tgl_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_KEJADIAN);
                        final String str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
                        final String str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL);
                        final String str_nomorA = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_A);
                        final String str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);
                        final String str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
                        final String str_jenis_tanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.JENIS_TANAMAN);
                        final String str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
                        final String str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
                        final String str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
                        final String str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
                        final String str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
                        final String str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET10);


                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("tahun", str_tgl_kejadian);
                        jsonParam.put("anakpetak_id", str_isipetak);
                        jsonParam.put("nomor_ha", str_nomorA);
                        jsonParam.put("tanggal_ha", str_tanggal);
                        jsonParam.put("jenistanaman", str_jenis_tanaman);
                        jsonParam.put("kejadian", str_isi_kejadian);
                        jsonParam.put("kerugian_luas", str_luas_lahan);
                        jsonParam.put("kerugian_pohon", str_jumlah_pohon);
                        jsonParam.put("kerugian_kyp", str_kerugian_kyp);
                        jsonParam.put("kerugian_kyb", str_kerugian_kyb);
                        jsonParam.put("kerugian_getah", str_kerugian_getah);
                        jsonParam.put("nilai_kerugian", str_nilai_kerugian);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        GangguanModel Aktifitasnya = new GangguanModel();
                        Aktifitasnya.setID_gangguan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataGangguanHutanfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEdit() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_gangguanhutan_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_gangguanhutan_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_tgl_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_KEJADIAN);
                        final String str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
                        final String str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL);
                        final String str_jenis_tanaman = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.JENIS_TANAMAN);
                        final String str_nomorA = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NOMOR_A);
                        final String str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);
                        final String str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
                        final String str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
                        final String str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
                        final String str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
                        final String str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
                        final String str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
                        final String str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("tahun", str_tgl_kejadian);
                        jsonParam.put("anakpetak_id", str_isipetak);
                        jsonParam.put("nomor_ha", str_nomorA);
                        jsonParam.put("tanggal_ha", str_tanggal);
                        jsonParam.put("jenistanaman", str_jenis_tanaman);
                        jsonParam.put("kejadian", str_isi_kejadian);
                        jsonParam.put("kerugian_luas", str_luas_lahan);
                        jsonParam.put("kerugian_pohon", str_jumlah_pohon);
                        jsonParam.put("kerugian_kyp", str_kerugian_kyp);
                        jsonParam.put("kerugian_kyb", str_kerugian_kyb);
                        jsonParam.put("kerugian_getah", str_kerugian_getah);
                        jsonParam.put("nilai_kerugian", str_nilai_kerugian);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        GangguanModel Aktifitasnya = new GangguanModel();
                        Aktifitasnya.setID_gangguan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataGangguanHutanfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_GANGGUAN_HUTAN_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    public void sync_data_perubahankelas_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_petak_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN);
                        final String str_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
                        final String str_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
                        final String str_jenis_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN);
                        final String str_kelas_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN);
                        final String str_luas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
                        final String str_jenis_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN);
                        final String str_kelas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN);
                        final String str_no_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
                        final String str_luas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
                        final String str_jenis_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF);
                        final String str_kelas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF);
                        final String str_keterangan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KETERANGAN_PERUBAHAN);
                        final String str_created_at = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("petak", str_petak_id);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("luas", str_luas);
                        jsonParam.put("jenistanaman", str_jenis_tanaman);
                        jsonParam.put("kelastanaman", str_kelas_tanaman);
                        jsonParam.put("luasperkiraan", str_luas_perkiraan);
                        jsonParam.put("jenisperkiraan", str_jenis_perkiraan);
                        jsonParam.put("kelasperkiraan", str_kelas_perkiraan);
                        jsonParam.put("nobappkh", str_no_bappkh);
                        jsonParam.put("luasdefinitif", str_luas_definitif);
                        jsonParam.put("jenisdefinitif", str_jenis_definitif);
                        jsonParam.put("kelasdefinitif", str_kelas_definitif);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PerubahankelasModel Aktifitasnya = new PerubahankelasModel();
                        Aktifitasnya.setID_Perubahan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataPerubahanKelasroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditPerubahanKelas() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_PERUBAHAN_KELAS " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_perubahankelas_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_perubahankelas_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_petak_id = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN);
                        final String str_tahun = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.TAHUN_PERUBAHAN);
                        final String str_luas = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERUBAHAN);
                        final String str_jenis_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN);
                        final String str_kelas_tanaman = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN);
                        final String str_luas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_PERKIRAAN);
                        final String str_jenis_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN);
                        final String str_kelas_perkiraan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN);
                        final String str_no_bappkh = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.NO_BAPPKH);
                        final String str_luas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.LUAS_DEFINITIF);
                        final String str_jenis_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF);
                        final String str_kelas_definitif = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF);
                        final String str_keterangan = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KETERANGAN_PERUBAHAN);
                        final String str_created_at = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnPerubahanKelas.TABLE_NAME, TrnPerubahanKelas._ID, id, TrnPerubahanKelas.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("petak", str_petak_id);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("luas", str_luas);
                        jsonParam.put("jenistanaman", str_jenis_tanaman);
                        jsonParam.put("kelastanaman", str_kelas_tanaman);
                        jsonParam.put("luasperkiraan", str_luas_perkiraan);
                        jsonParam.put("jenisperkiraan", str_jenis_perkiraan);
                        jsonParam.put("kelasperkiraan", str_kelas_perkiraan);
                        jsonParam.put("nobappkh", str_no_bappkh);
                        jsonParam.put("luasdefinitif", str_luas_definitif);
                        jsonParam.put("jenisdefinitif", str_jenis_definitif);
                        jsonParam.put("kelasdefinitif", str_kelas_definitif);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PerubahankelasModel Aktifitasnya = new PerubahankelasModel();
                        Aktifitasnya.setID_Perubahan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataPerubahanKelasroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();
    }

    public void sync_tambah_data_interaksimdh_v1(final String id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_anakpetak = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.ANAK_PETAK_ID);
                        final String str_tahun = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.TAHUN);
                        final String str_namadesa = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.NAMA_DESA);
                        final String str_bentukinteraksi = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.BENTUK_INTERAKSI);
                        final String str_status = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.STATUS);
                        final String str_keterangan = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("namadesa", str_namadesa);
                        jsonParam.put("bentukinteraksi", str_bentukinteraksi);
                        jsonParam.put("status", str_status);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        InteraksimdhModel Aktifitasnya = new InteraksimdhModel();
                        Aktifitasnya.setID_IMDH(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataInteraksimdhfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditInteraksiMdh() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_INTERAKSI_MDH " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_interaksimdh_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_interaksimdh_v1(final String id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_anakpetak = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.ANAK_PETAK_ID);
                        final String str_tahun = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.TAHUN);
                        final String str_namadesa = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.NAMA_DESA);
                        final String str_bentukinteraksi = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.BENTUK_INTERAKSI);
                        final String str_status = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.STATUS);
                        final String str_keterangan = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnInteraksimdh.TABLE_NAME, TrnInteraksimdh._ID, id, TrnInteraksimdh.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("namadesa", str_namadesa);
                        jsonParam.put("bentukinteraksi", str_bentukinteraksi);
                        jsonParam.put("status", str_status);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        InteraksimdhModel Aktifitasnya = new InteraksimdhModel();
                        Aktifitasnya.setID_IMDH(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataInteraksimdhfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_INTERAKSI_MDH_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    public void sync_data_pemantauansatwa_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
                        final String str_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
                        final String str_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
                        final String str_caralihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
                        final String str_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
                        final String str_tanggal = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.TANGGAL_PEMANTAUAN);
                        final String str_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("jenissatwa", str_jenissatwa);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("jumlahsatwa", str_jumlahsatwa);
                        jsonParam.put("caralihat", str_caralihat);
                        jsonParam.put("waktulihat", str_waktulihat);
                        jsonParam.put("tanggal", str_tanggal);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PemantauansatwaModel Aktifitasnya = new PemantauansatwaModel();
                        Aktifitasnya.setID_Pemantauan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataPemantauanSatwafroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditPemantauanSatwa() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_PEMANTAUAN_SATWA " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_pemantauansatwa_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_pemantauansatwa_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_jenissatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JENIS_SATWA);
                        final String str_anakpetak = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.ANAK_PETAK_ID);
                        final String str_jumlahsatwa = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.JUMLAH_SATWA);
                        final String str_caralihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CARA_LIHAT);
                        final String str_waktulihat = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.WAKTU_LIHAT);
                        final String str_tanggal = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.TANGGAL_PEMANTAUAN);
                        final String str_keterangan = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnPemantauanSatwa.TABLE_NAME, TrnPemantauanSatwa._ID, id, TrnPemantauanSatwa.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("jenissatwa", str_jenissatwa);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("jumlahsatwa", str_jumlahsatwa);
                        jsonParam.put("caralihat", str_caralihat);
                        jsonParam.put("waktulihat", str_waktulihat);
                        jsonParam.put("tanggal", str_tanggal);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PemantauansatwaModel Aktifitasnya = new PemantauansatwaModel();
                        Aktifitasnya.setID_Pemantauan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataPemantauanSatwafroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_PEMANTAUAN_SATWA);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    public void sync_data_laporanlpalbatas_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_bagianhutanpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.BAGIAN_HUTAN);
                        final String str_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
                        final String str_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
                        final String str_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JENIS_PAL);
                        final String str_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KONDISI_PAL);
                        final String str_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
                        final String str_keteranganpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);
                        final String str_created_at = db.getDataDetail_v2(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("bagianhutanpal", str_bagianhutanpal);
                        jsonParam.put("tanggalpal", str_tanggalpal);
                        jsonParam.put("nopal", str_nopal);
                        jsonParam.put("jenispal", str_jenispal);
                        jsonParam.put("kondisipal", str_kondisipal);
                        jsonParam.put("jumlahpal", str_jumlahpal);
                        jsonParam.put("keteranganpal", str_keteranganpal);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("created_at", str_created_at);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PelaporanpalbatasModel Aktifitasnya = new PelaporanpalbatasModel();
                        Aktifitasnya.setID_Laporan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataLaporanPalfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditPAL() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_LAPORAN_PAL " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_laporanlpalbatas_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_laporanlpalbatas_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_bagianhutanpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.BAGIAN_HUTAN);
                        final String str_tanggalpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.TANGGAL_PAL);
                        final String str_nopal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.NO_PAL);
                        final String str_jenispal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JENIS_PAL);
                        final String str_kondisipal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KONDISI_PAL);
                        final String str_jumlahpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.JUMLAH_PAL);
                        final String str_keteranganpal = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KETERANGAN_PAL);
                        final String str_created_at = db.getDataDetail_v2(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.CREATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnLaporanPalBatas.TABLE_NAME, TrnLaporanPalBatas._ID, id, TrnLaporanPalBatas.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("bagianhutanpal", str_bagianhutanpal);
                        jsonParam.put("tanggalpal", str_tanggalpal);
                        jsonParam.put("nopal", str_nopal);
                        jsonParam.put("jenispal", str_jenispal);
                        jsonParam.put("kondisipal", str_kondisipal);
                        jsonParam.put("jumlahpal", str_jumlahpal);
                        jsonParam.put("keteranganpal", str_keteranganpal);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("created_at", str_created_at);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        PelaporanpalbatasModel Aktifitasnya = new PelaporanpalbatasModel();
                        Aktifitasnya.setID_Laporan(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataLaporanPalfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_LAPORAN_PAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();
    }

    public void sync_data_registerpcp_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_nopcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NO_PCP);
                        final String str_anakpetak = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.ANAK_PETAK_ID);
                        final String str_tahunpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_PCP);
                        final String str_luasbaku = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BAKU);
                        final String str_luasblok = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BLOK);
                        final String str_umur = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UMUR);
                        final String str_rataratakeliling = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.RATARATA_KELILING);
                        final String str_bonita = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.BONITA);
                        final String str_nlapangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_LAPANGAN);
                        final String str_normalpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NORMAL_PCP);
                        final String str_nmati = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_MATI);
                        final String str_tahunjarangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_JARANGAN);
                        final String str_peninggi = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.PENIGGI);
                        final String str_keterangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("nopcp", str_nopcp);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("tahunpcp", str_tahunpcp);
                        jsonParam.put("luasbaku", str_luasbaku);
                        jsonParam.put("luasblok", str_luasblok);
                        jsonParam.put("umur", str_umur);
                        jsonParam.put("rataratakeliling", str_rataratakeliling);
                        jsonParam.put("bonita", str_bonita);
                        jsonParam.put("nlapangan", str_nlapangan);
                        jsonParam.put("normalpcp", str_normalpcp);
                        jsonParam.put("nmati", str_nmati);
                        jsonParam.put("tahunjarangan", str_tahunjarangan);
                        jsonParam.put("peninggi", str_peninggi);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        RegisterpcpModel Aktifitasnya = new RegisterpcpModel();
                        Aktifitasnya.setID_Registerpcp(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataRegisterPCPfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditPCP() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_REGISTER_PCP " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_registerpcp_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_registerpcp_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_nopcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NO_PCP);
                        final String str_anakpetak = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.ANAK_PETAK_ID);
                        final String str_tahunpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_PCP);
                        final String str_luasbaku = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BAKU);
                        final String str_luasblok = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.LUAS_BLOK);
                        final String str_umur = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UMUR);
                        final String str_rataratakeliling = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.RATARATA_KELILING);
                        final String str_bonita = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.BONITA);
                        final String str_nlapangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_LAPANGAN);
                        final String str_normalpcp = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.NORMAL_PCP);
                        final String str_nmati = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.N_MATI);
                        final String str_tahunjarangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.TAHUN_JARANGAN);
                        final String str_peninggi = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.PENIGGI);
                        final String str_keterangan = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KETERANGAN);
                        final String str_created_at = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnRegisterPcp.TABLE_NAME, TrnRegisterPcp._ID, id, TrnRegisterPcp.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("nopcp", str_nopcp);
                        jsonParam.put("anakpetak", str_anakpetak);
                        jsonParam.put("tahunpcp", str_tahunpcp);
                        jsonParam.put("luasbaku", str_luasbaku);
                        jsonParam.put("luasblok", str_luasblok);
                        jsonParam.put("umur", str_umur);
                        jsonParam.put("rataratakeliling", str_rataratakeliling);
                        jsonParam.put("bonita", str_bonita);
                        jsonParam.put("nlapangan", str_nlapangan);
                        jsonParam.put("normalpcp", str_normalpcp);
                        jsonParam.put("nmati", str_nmati);
                        jsonParam.put("tahunjarangan", str_tahunjarangan);
                        jsonParam.put("peninggi", str_peninggi);
                        jsonParam.put("keterangan", str_keterangan);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        RegisterpcpModel Aktifitasnya = new RegisterpcpModel();
                        Aktifitasnya.setID_Registerpcp(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataRegisterPCPfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_REGISTER_PCP_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();
    }

    public void sync_data_identifikasitenurial_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_jenis_permasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.JENIS_PERMASALAHAN);
                        final String str_tahun = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.TANGGAL);
                        final String str_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.ANAK_PETAK_ID);
                        final String str_strata = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STRATA);
                        final String str_kelas_hutan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KELAS_HUTAN_ID);
                        final String str_luas_baku = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_BAKU);
                        final String str_luas_tenurial = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_TENURIAL);
                        final String str_kondisi_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KONDISI_PETAK);
                        final String str_awal_konflik = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.AWAL_KONFLIK);
                        final String str_pihak_terlibat = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.PIHAK_TERLIBAT);
                        final String str_status_penyelesaian = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STATUS_PENYELESAIAN);
                        final String str_created_at = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET10);

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("jenispermasalahan", str_jenis_permasalahan);
                        jsonParam.put("petak", str_petak);
                        jsonParam.put("kelashutan", str_kelas_hutan);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("strata", str_strata);
                        jsonParam.put("luasbaku", str_luas_baku);
                        jsonParam.put("luastenurial", str_luas_tenurial);
                        jsonParam.put("kondisipetak", str_kondisi_petak);
                        jsonParam.put("awalkonflik", str_awal_konflik);
                        jsonParam.put("pihakterlibat", str_pihak_terlibat);
                        jsonParam.put("statuspenyelesaian", str_status_penyelesaian);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        IdentifikasiTenurialModel Aktifitasnya = new IdentifikasiTenurialModel();
                        Aktifitasnya.setID_Tenurial(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataIdentifikasiTenurialfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();

    }

    private void sendToServerEditTenurial() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_IDENTIFIKASI_KONFLIK_TENURIAL " +
                    " WHERE  KET9=2 "+
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_identifikasitenurial_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));

                } catch (Exception ex) {
                    Log.i("JSON_ERROR", ex.toString());
                }
                cur.moveToNext();
            }
            cur.close();
            db.close();

        } catch (Exception ex) {
            Log.i("JSON_ERROR", ex.toString());
        }
    }

    public void sync_data_edit_identifikasitenurial_v1(final String id) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String str_tes_data = "";
                try {
                    URL url = new URL(LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bearer " + db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    try {
                        final String str_jenis_permasalahan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.JENIS_PERMASALAHAN);
                        final String str_tahun = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.TANGGAL);
                        final String str_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.ANAK_PETAK_ID);
                        final String str_strata = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STRATA);
                        final String str_kelas_hutan = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KELAS_HUTAN_ID);
                        final String str_luas_baku = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_BAKU);
                        final String str_luas_tenurial = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.LUAS_TENURIAL);
                        final String str_kondisi_petak = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KONDISI_PETAK);
                        final String str_awal_konflik = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.AWAL_KONFLIK);
                        final String str_pihak_terlibat = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.PIHAK_TERLIBAT);
                        final String str_status_penyelesaian = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.STATUS_PENYELESAIAN);
                        final String str_created_at = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.CREATED_AT);
                        final String str_created_by = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.CREATED_BY);
                        final String str_updated_at = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.UPDATED_AT);
                        final String str_updated_by = db.getDataDetail_v2(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.UPDATED_BY);
                        final String str_ket10 = db.getDataDetail(TrnIdentifikasiTenurial.TABLE_NAME, TrnIdentifikasiTenurial._ID, id, TrnIdentifikasiTenurial.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("jenispermasalahan", str_jenis_permasalahan);
                        jsonParam.put("petak", str_petak);
                        jsonParam.put("kelashutan", str_kelas_hutan);
                        jsonParam.put("tahun", str_tahun);
                        jsonParam.put("strata", str_strata);
                        jsonParam.put("luasbaku", str_luas_baku);
                        jsonParam.put("luastenurial", str_luas_tenurial);
                        jsonParam.put("kondisipetak", str_kondisi_petak);
                        jsonParam.put("awalkonflik", str_awal_konflik);
                        jsonParam.put("pihakterlibat", str_pihak_terlibat);
                        jsonParam.put("statuspenyelesaian", str_status_penyelesaian);
                        jsonParam.put("created_at", str_created_at);
                        jsonParam.put("created_by", str_created_by);
                        jsonParam.put("updated_at", str_updated_at);
                        jsonParam.put("updated_by", str_updated_by);

                    } catch (JSONException ex) {
                        Log.i("JSON_ERROR", ex.toString());
                        ex.printStackTrace();
                    }

                    Log.i("JSON_SEND", jsonParam.toString());

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_BACKGROUND_SERVICE", "Sync to : " + LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    Log.i("JSON_FEEDBACK", myResponse.getString("status"));

                    os.flush();
                    os.close();
                    conn.disconnect();

                    if (myResponse.getString("status").equals("success")) {
                        IdentifikasiTenurialModel Aktifitasnya = new IdentifikasiTenurialModel();
                        Aktifitasnya.setID_Tenurial(Integer.parseInt(id));
                        Aktifitasnya.setKet9("1");
                        Aktifitasnya.setKet10(myResponse.getString("id"));
                        db.EditDataIdentifikasiTenurialfroApi(Aktifitasnya);
                    }

                    cek_feedback_api = true;

                } catch (Exception e) {
                    cek_feedback_api = false;
                    Log.i("JSON_MESSAGE", e.toString());
                    Log.i("JSON_LINK", LoginActivity.URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1);
                    Log.i("JSON_ID", id);
                    e.printStackTrace();
                }
                Log.i("JSON_TES", str_tes_data);
            }
        });
        thread.start();
    }
}

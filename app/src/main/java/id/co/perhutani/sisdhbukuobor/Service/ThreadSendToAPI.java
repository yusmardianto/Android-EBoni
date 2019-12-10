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
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
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
            try {
//                Log.i("JSON_BACKGROUND_SERVICE", "Start loop service");
                if (!isOnline()) {
                    Log.i("JSON_BACKGROUND_SERVICE", "Tidak ada koneksi internet yeeeeee");
                } else {
                    Log.i("JSON_BACKGROUND_SERVICE", "Ada koneksi internet joshh " );
                    // sync tambah
                    sendToServer();
                    // sync tambah
                    sendToServerEdit();
//
//                    FragmentManager manager = myContext.getSupportFragmentManager();
//                    Fragment fragment = new ListGangguanFragment();
//                    FragmentTransaction ft = manager.beginTransaction();
//                    ft.replace(R.id.nav_host_fragment, fragment);
//                    ft.commit();
                }
                //pause thread every 10 seconds
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
    private void sendToServer() {
        try {
            Log.i("JSON_BACKGROUND_SERVICE", "Try Send to server");
            SQLiteHandler DB_Helper = new SQLiteHandler(myContext);
            SQLiteDatabase db = DB_Helper.getReadableDatabase();
            Cursor cur;
            cur = db.rawQuery("SELECT *" +
                    " FROM TRN_GANGGUAN_HUTAN " +
                    " WHERE  KET9=0 "+
//                    " WHERE  CREATE_BY=\"" + username + "\"" +
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_gangguanhutan_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
//                    sync_persediaan_v3(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));


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

    public void sync_data_gangguanhutan_v1(final String id) {

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
                        final String str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
                        final String str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_HA);
                        final String str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);
                        final String str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
                        final String str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
                        final String str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
                        final String str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
                        final String str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
                        final String str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
                        final String str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);
                        final String str_ket10 = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET10);
//                        String aksi;
//                            aksi = "tambah";
//                        if ("tambah".equalsIgnoreCase(str_ket10)) {
//                            aksi = "tambah";
//                        }else {
//                            aksi = "ubah";
//                        }

                        jsonParam.put("aksi", "tambah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("tahun", "1111");
                        jsonParam.put("anakpetak_id", str_isipetak);
                        jsonParam.put("nomor_ha", "11111");
                        jsonParam.put("tanggal_ha", str_tanggal);
                        jsonParam.put("kejadian", str_isi_kejadian);
                        jsonParam.put("kerugian_luas", str_luas_lahan);
                        jsonParam.put("kerugian_pohon", str_jumlah_pohon);
                        jsonParam.put("kerugian_kyp", str_kerugian_kyp);
                        jsonParam.put("kerugian_kyb", str_kerugian_kyb);
                        jsonParam.put("kerugian_getah", str_kerugian_getah);
                        jsonParam.put("nilai_kerugian", str_nilai_kerugian);
                        jsonParam.put("keterangan", str_keterangan);

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
//                    Log.i("JSON_UPDATE_FLAG", str_isi_kejadian+" gagal ");
//                    Log.i("JSON_TOKET", db.getDataProfil(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN));
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
//                    " WHERE  CREATE_BY=\"" + username + "\"" +
                    " ORDER BY ID ASC", null);

            cur.moveToPosition(0);
            for (int i = 0; i < cur.getCount(); i++) {
                try {
                    Log.i("JSON_BACKGROUND_SERVICE", "Try Sync ID : " +String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
                    sync_data_edit_gangguanhutan_v1(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));
//                    sync_persediaan_v3(String.valueOf(cur.getInt(cur.getColumnIndex("ID"))));


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
                        final String str_isipetak = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.ANAK_PETAK_ID);
                        final String str_tanggal = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.TANGGAL_HA);
                        final String str_isi_kejadian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KEJADIAN);
                        final String str_luas_lahan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_LUAS);
                        final String str_jumlah_pohon = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_POHON);
                        final String str_kerugian_kyp = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYP);
                        final String str_kerugian_kyb = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_KYB);
                        final String str_kerugian_getah = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KERUGIAN_GETAH);
                        final String str_nilai_kerugian = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.NILAI_KERUGIAN);
                        final String str_keterangan = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KETERANGAN);
                        final String str_ket10 = db.getDataDetail(TrnGangguanKeamananHutan.TABLE_NAME, TrnGangguanKeamananHutan._ID, id, TrnGangguanKeamananHutan.KET10);

                        jsonParam.put("aksi", "ubah");
                        jsonParam.put("id", str_ket10);
                        jsonParam.put("tahun", "1111");
                        jsonParam.put("anakpetak_id", str_isipetak);
                        jsonParam.put("nomor_ha", "11111");
                        jsonParam.put("tanggal_ha", str_tanggal);
                        jsonParam.put("kejadian", str_isi_kejadian);
                        jsonParam.put("kerugian_luas", str_luas_lahan);
                        jsonParam.put("kerugian_pohon", str_jumlah_pohon);
                        jsonParam.put("kerugian_kyp", str_kerugian_kyp);
                        jsonParam.put("kerugian_kyb", str_kerugian_kyb);
                        jsonParam.put("kerugian_getah", str_kerugian_getah);
                        jsonParam.put("nilai_kerugian", str_nilai_kerugian);
                        jsonParam.put("keterangan", str_keterangan);

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

}

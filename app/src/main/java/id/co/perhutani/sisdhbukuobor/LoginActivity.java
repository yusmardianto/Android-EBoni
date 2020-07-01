package id.co.perhutani.sisdhbukuobor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstBagianHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstBentukInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaByRph;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstFungsiHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKondisiPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstPenggunaanHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstPihakTerlibatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStatusInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStrataSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstSubPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstWaktuLihatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnMonitoringKlsHtn;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;
import id.co.perhutani.sisdhbukuobor.Schema.TrnWorkOrder;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class LoginActivity extends AppCompatActivity {
    // server staging
//    private static final String address = "http://10.0.8.51:9393/";
    // server production
    //  private static final String address = "https://union-loket.perhutani.id/";
    private static final String address = "https://stg.sisdh.perhutani.id/";
    // server local
    //private static final String address = "http://127.0.0.1:8000/";
    // link api
    private static final String URL_FOR_LOGIN_V1 = address + "api/v1/loginv2";
    private static final String URL_FOR_PROFIL_V1 = address + "api/v1/get_user_details";

    private static final String URL_FOR_GET_ANAK_PETAK_V1 = address + "api/v1/getAnakPetak";
    private static final String URL_FOR_GET_DESA_BY_RPH_V1 = address + "api/v1/getDesaByRph";
    private static final String URL_FOR_GET_BAGIAN_HUTAN_V1 = address + "api/v1/get_bagian_hutan";
    private static final String URL_FOR_GET_KELAS_HUTAN_V1 = address + "api/v1/getKelasHutan";
    private static final String URL_FOR_GET_FUNGSI_HUTAN_V1 = address + "api/v1/getFungsiHutan";
    private static final String URL_FOR_GET_PENGGUNAAN_HUTAN_V1 = address + "api/v1/getPenggunaanHutan";
    private static final String URL_FOR_GET_JENIS_TANAMAN_V1 = address + "api/v1/getJenisTanaman";
    private static final String URL_FOR_GET_JENIS_PERMASALAHAN_V1 = address + "api/v1/getJenisPermasalahan";
    private static final String URL_FOR_GET_JENIS_PALL_V1 = address + "api/v1/getJenisPal";
    private static final String URL_FOR_GET_KONDISI_PAL_V1 = address + "api/v1/getKondisiPal";
    private static final String URL_FOR_GET_JENIS_GANGGUAN_HUTAN_V1 = address + "api/v1/getJenisGangguanHutan";
    private static final String URL_FOR_GET_JENIS_SATWA_V1 = address + "api/v1/getJenisSatwa";
    private static final String URL_FOR_GET_JENIS_TEMUAN_V1 = address + "api/v1/getJenisTemuan";
    private static final String URL_FOR_GET_WAKTU_LIHAT_V1 = address + "api/v1/getWaktuTerlihat";
    private static final String URL_FOR_GET_DESA_V1 = address + "api/v1/get_master_desa";
    private static final String URL_FOR_GET_BENTUK_INTERAKSI_V1 = address + "api/v1/get_master_interaksi";
    private static final String URL_FOR_GET_STATUS_INTERAKSI_V1 = address + "api/v1/get_master_status_interaksi";
    private static final String URL_FOR_GET_PIHAK_TERLIBAT_V1 = address + "api/v1/getPihakTerlibat";
    private static final String URL_FOR_GET_JENIS_KEGIATAN_PERSEMAIAN_V1 = address + "api/v1/getJenisKegiatanPersemaian";
    private static final String URL_FOR_GET_STRATA_V1 = address + "api/v1/getStrata";
    private static final String URL_FOR_GET_PEKERJAAN_V1 = address + "api/v1/get_master_pekerjaan";
    private static final String URL_FOR_SUB_PEKERJAAN_V1 = address + "api/v1/get_master_sub_pekerjaan";
    private static final String URL_FOR_GET_WORKORDER_V1 = address + "api/v1/getWorkOrder";
    private static final String URL_FOR_GET_SUSUN_RISALAH_V1 = address + "api/v1/getSusunRisalah";
    private static final String URL_FOR_GET_MONITORING_KLSHTN_V1 = address + "api/v1/getMonitoringKlsHtn";
    private static final String URL_FOR_GET_TALLY_SHEET_V1 = address + "api/v1/getTallySheet";

    public static final String URL_FOR_POST_GANGGUAN_HUTAN_V1 = address + "api/v1/postGukamhut";
    public static final String URL_FOR_POST_PERUBAHAN_KELAS_PAL_V1 = address + "api/v1/postPerubahan";
    public static final String URL_FOR_POST_INTERAKSI_MDH_V1 = address + "api/v1/postInteraksi";
    public static final String URL_FOR_POST_PEMANTAUAN_SATWA = address + "api/v1/postSatwa";
    public static final String URL_FOR_POST_LAPORAN_PAL_V1 = address + "api/v1/postPal";
    public static final String URL_FOR_POST_REGISTER_PCP_V1 = address + "api/v1/postPcp";
    public static final String URL_FOR_POST_IDENTIFIKASI_TENURIAL_V1 = address + "api/v1/postTenurial";
    public static final String URL_FOR_POST_TALLYSHEET_V1 = address + "api/v1/postTallySheet";

    private FirebaseAnalytics mFirebaseAnalytics;
    private ProgressDialog progressDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private EditText username, password;
    private EditText id, pass;
    private Button loginBtn;
//    private CheckBox ShowPass;
    Boolean status_message = false;
    Boolean cek_login_api = false;
    private int i = -1;

    String str_rolename = "";
    String error_message = "";
    String feedback_error_message = "";

    TextView txt_testview;
    private long exitTime = 0;

    private void askForPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();

    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txt_input_username);
        password = findViewById(R.id.txt_input_password);
        loginBtn = findViewById(R.id.loginBtn);
        txt_testview = findViewById(R.id.txt_testview);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(LoginActivity.this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(pass));
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Thread background = new Thread() {
            public void run() {
                try {
                    askForPermission();
                } catch (Exception e) {
                }
            }
        };
        background.start();
        AjnClass.pasang_sentry(this.getApplicationContext());

        username.setText("mandor_banjarharjo");
//        username.setText("kphw_pekalongan");
        password.setText("perhutani");

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        db = new SQLiteHandler(getApplicationContext());
        db.altertable_update_aplication();
        // Session manager
        session = new SessionManager(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
//            Intent intent = new Intent(this, DashboardV2Activity.class);
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
//
//                                        if(str_rolename.equals("mobile_rph"))
//                                        {
//                                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                                            startActivity(i);
//                                            finish();
//
//                                        } else if(str_rolename.equals("mobile_phw")){
//                                            Intent i = new Intent(LoginActivity.this, RoleKphwActivity.class);
//                                            startActivity(i);
//                                            finish();
//                                        }else{
//                                            AjnClass.showAlert(LoginActivity.this,"Siapa lu ?");
//
//                                        }
        }

        // mengaktifkan service sentry io
        AjnClass.pasang_sentry(this.getApplicationContext());
//        AjnClass.test_fungsi_sentry();

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
//        ShowPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ShowPass.isChecked()) {
//                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
//                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                } else {
//                    //Jika tidak, maka password akan di sembuyikan
//                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Manually checking internet connection
                checkConnection();
            }
        });

    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection() {
        if (!isOnline()) {
            AjnClass.showAlert(LoginActivity.this, "Tidak ada koneksi internet");
        } else {
            if (username.getText().toString().length() != 0 && password.getText().toString().length() != 0) {
                sendPost();

                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait..");
                pDialog.show();
                pDialog.setCancelable(false);
                new CountDownTimer(2500, 800) {
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis
                        i++;
                        switch (i) {
                            case 0:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        if (cek_login_api) {
                            if (status_message) {
                                pDialog.setTitleText("Success!")
                                        .setContentText(error_message)
                                        .setConfirmText("OK")
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();

                                        pDialog.cancel();
                                    }

                                }, 5000);
                            } else {
                                pDialog.setTitleText("Oops...!")
                                        .setContentText(error_message)
                                        .setConfirmText("OK!")
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            }
                        } else {

                            if (error_message.equals(null) || error_message.equals("")) {
                                error_message = "Tidak bisa terhubung ke server";
                            }
                            txt_testview.setText(feedback_error_message);
                            pDialog.setTitleText("Oops...!")
                                    .setContentText(error_message)
                                    .setConfirmText("OK!")
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        }
                    }
                }.start();

            } else {
                AjnClass.showAlert(LoginActivity.this, "Username dan Password tidak boleh kosong!");
            }
        }
    }

    public void sendPost() {

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_LOGIN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("username", username.getText().toString());
                    jsonParam.put("password", password.getText().toString());

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
                    //Read JSON response and print
                    JSONObject myResponse = new JSONObject(response.toString());
//                    Log.i("JSON_STATUS", myResponse.getString("status"));
//                    Log.i("JSON_MESSAGE", myResponse.getString("message"));
//                    Log.i("JSON_STATUS", String.valueOf(conn.getResponseCode()));
//                    Log.i("JSON_MESSAGE", conn.getResponseMessage());
//                    Log.i("JSON_TOKEN", myResponse.getString("access_token"));
                    if (myResponse.getString("status").equals("Success") || myResponse.getString("status").equals("success")) {
                        error_message = myResponse.getString("message");
                        status_message = true;
//                        Log.i("JSON_STATUS", String.valueOf(conn.getResponseCode()));
//                        Log.i("JSON_MESSAGE", conn.getResponseMessage());
//                        Log.i("JSON_TOKEN", myResponse.getString("access_token"));




                        // get data profil user
                        sync_profil_v1(myResponse.getString("access_token"), username.getText().toString());
                        // get data anak petak
                        sync_get_anak_petak_v1(myResponse.getString("access_token"), username.getText().toString());
                        // get desa anak petak
                        sync_get_desa_by_rph_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data kelas hutan
                        sync_get_kelas_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data fungsi hutan
                        sync_get_fungsi_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data Penggunaan hutan
                        sync_get_penggunaan_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
                        // get data jenis tanaman
                        sync_get_jenis_tanaman_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data jenis permasalahan
                        sync_get_jenis_permasalahan_v1(myResponse.getString("access_token"), username.getText().toString());
                        // get data jenis gangguan
                        sync_get_jenis_gangguan_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data jenis pal
                        sync_get_jenis_pal_v1(myResponse.getString("access_token"), username.getText().toString());
//                        //get data kondisi pal
                        sync_get_kondisi_pal_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data jenis satwa
                        sync_get_jenis_satwa_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data jenis temuan
                        sync_get_jenis_temuan_v1(myResponse.getString("access_token"), username.getText().toString());
//                        // get data bagian hutan
                        sync_get_bagian_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
//                        //get data waktu lihat
                        sync_get_waktu_terlihat_v1(myResponse.getString("access_token"), username.getText().toString());
//                        //get data desa
//                        sync_get_master_desa(myResponse.getString("access_token"), username.getText().toString());
//                        //get data interaksi
                        sync_get_master_interaksi(myResponse.getString("access_token"), username.getText().toString());
//                        //get data status interaksi
                        sync_get_master_status_interaksi(myResponse.getString("access_token"), username.getText().toString());
//                        // get data strata
                        sync_get_strata(myResponse.getString("access_token"), username.getText().toString());
//                        //get data pihak terlibat
                        sync_get_pihak_terlibat(myResponse.getString("access_token"), username.getText().toString());
//                        //get data pekerjaan
                        sync_get_sub_pekerjaan(myResponse.getString("access_token"), username.getText().toString());
//                        //get data sub pekerjaan
                        sync_get_pekerjaan(myResponse.getString("access_token"), username.getText().toString());
//                        //get data workorder
                        sync_get_workorder(myResponse.getString("access_token"), username.getText().toString());
//                        //get data susun risalah
                        sync_get_susunrisalah(myResponse.getString("access_token"), username.getText().toString());
//                        //get data monitoring kh & penampakan
                        sync_get_monitoring(myResponse.getString("access_token"), username.getText().toString());
                        //get data monitoring kh & penampakan
                        sync_get_tallysheet(myResponse.getString("access_token"), username.getText().toString());

                        session.setLogin(true);
                    } else if (myResponse.getString("status").equals("error")) {
//                        Log.i("JSON_STATUS", myResponse.getString("status"));
//                        Log.i("JSON_MESSAGE", myResponse.getString("message"));
                        error_message = myResponse.getString("message");
                        status_message = false;
                        cek_login_api = false;
                    }

                    os.flush();
                    os.close();
                    conn.disconnect();

                    cek_login_api = true;

                } catch (Exception e) {
                    cek_login_api = false;
                    feedback_error_message = e.toString();
                    error_message = "Terjadi kesalahan ketika login, silahkan ulang kembali!";
                    Log.i("JSON_STATUS_LOGIN", "gagal login");
                    Log.i("JSON_MESSAGE_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_profil_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_PROFIL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    //Read JSON response and print
                    JSONObject myResponse = new JSONObject(response.toString());
                    Log.i("JSON_ACTION", "================ API PROFIL ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    Log.i("JSON_DATA", myResponse.getString("data"));

                    ContentValues values = new ContentValues();
                    values.put(UserSchema._ID, 1);
                    values.put(UserSchema.USER_ID, myResponse.getJSONObject("data").getString("id"));
                    values.put(UserSchema.USER_NAME, myResponse.getJSONObject("data").getString("username"));
                    values.put(UserSchema.USER_NAME_DESCRIPTIONS, myResponse.getJSONObject("data").getString("name"));
                    values.put(UserSchema.USER_TOKEN, token);
                    values.put(UserSchema.USER_IMEI, "");
                    values.put(UserSchema.USER_ANDROID_ID, "");
                    values.put(UserSchema.KET1, myResponse.getJSONObject("data").getString("name_rph"));
                    values.put(UserSchema.KET2, myResponse.getJSONObject("data").getString("email"));
                    values.put(UserSchema.KET3, myResponse.getJSONObject("data").getString("company_id"));
                    values.put(UserSchema.KET4, myResponse.getJSONObject("data").getString("unit_kerja_id"));
                    values.put(UserSchema.KET5, "");
                    values.put(UserSchema.KET6, "");
                    values.put(UserSchema.KET7, "");
                    values.put(UserSchema.KET8, "");
                    values.put(UserSchema.KET9, "");
                    values.put(UserSchema.KET10, myResponse.getJSONObject("data").getString("role_name"));
                    db.create(UserSchema.TABLE_NAME, values);

                    str_rolename = myResponse.getJSONObject("data").getString("role_name");
                    conn.disconnect();

                } catch (Exception e) {
                    Log.i("JSON_username", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_anak_petak_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_ANAK_PETAK_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET ANAK PETAK ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    Log.i("JSON_DATA_PTK", result.getString("data"));


                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstAnakPetakSchema._ID, i + 1);
                        values.put(MstAnakPetakSchema.KODE_ANAKPETAK, json_projek.getString("kode_anakpetak"));
                        values.put(MstAnakPetakSchema.DIVISI, json_projek.getString("divisi"));
                        values.put(MstAnakPetakSchema.KPH, json_projek.getString("kph_name"));
                        values.put(MstAnakPetakSchema.BKPH, json_projek.getString("bkph_name"));
                        values.put(MstAnakPetakSchema.RPH, json_projek.getString("rph_name"));
                        values.put(MstAnakPetakSchema.BAGIAN_HUTAN, json_projek.getString("bagianhutan"));
                        values.put(MstAnakPetakSchema.KELAS_HUTAN, json_projek.getString("kelashutan"));
                        values.put(MstAnakPetakSchema.KELAS_PERUSAHAAN, json_projek.getString("kelasperusahaan"));
                        values.put(MstAnakPetakSchema.JENIS_TANAMAN, json_projek.getString("jenistanaman"));
                        values.put(MstAnakPetakSchema.PETAK_ID, json_projek.getString("petak_id"));
                        values.put(MstAnakPetakSchema.PETAK_NAME, json_projek.getString("nama_petak"));
                        values.put(MstAnakPetakSchema.ANAK_PETAK_ID, json_projek.getString("id"));
                        values.put(MstAnakPetakSchema.ANAK_PETAK_NAME, json_projek.getString("anakpetak"));
                        values.put(MstAnakPetakSchema.TAHUN, json_projek.getString("tahun"));
                        db.create(MstAnakPetakSchema.TABLE_NAME, values);

                    }


                    conn.disconnect();

                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_desa_by_rph_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_DESA_BY_RPH_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET DESA BY RPH ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstDesaByRph._ID, i + 1);
                        values.put(MstDesaByRph.DESA_ID, json_projek.getString("desa_id"));
                        values.put(MstDesaByRph.DESA_NAME, json_projek.getString("nama_desa"));
                        values.put(MstDesaByRph.BH_ID, json_projek.getString("bh_id"));
                        values.put(MstDesaByRph.RPH_ID, json_projek.getString("rph_id"));
                        values.put(MstDesaByRph.KPH_ID, json_projek.getString("kph_id"));
                        values.put(MstDesaByRph.BKPH_ID, json_projek.getString("bkph_id"));
                        db.create(MstDesaByRph.TABLE_NAME, values);
                    }

                    conn.disconnect();

                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_bagian_hutan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_BAGIAN_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET BAGIAN HUTAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstBagianHutan._ID, i + 1);
                        values.put(MstBagianHutan.BAGIAN_HUTAN_ID, json_projek.getString("id"));
                        values.put(MstBagianHutan.BAGIAN_HUTAN_KODE, json_projek.getString("kode"));
                        values.put(MstBagianHutan.BAGIAN_HUTAN_KPH_ID, json_projek.getString("kph_id"));
                        values.put(MstBagianHutan.BAGIAN_HUTAN_NAME, json_projek.getString("name"));
                        db.create(MstBagianHutan.TABLE_NAME, values);

                    }
                    conn.disconnect();

                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_kelas_hutan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_KELAS_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET KELAS HUTAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstKelasHutanSchema._ID, i + 1);
                        values.put(MstKelasHutanSchema.KELAS_HUTAN_ID, json_projek.getString("id"));
                        values.put(MstKelasHutanSchema.KELAS_HUTAN_NAME, json_projek.getString("name"));
                        values.put(MstKelasHutanSchema.STRUKTUR_ID, json_projek.getString("struktur_id"));
                        db.create(MstKelasHutanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_fungsi_hutan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_FUNGSI_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET FUNGSI HUTAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstFungsiHutanSchema._ID, i + 1);
                        values.put(MstFungsiHutanSchema.FUNGSI_HUTAN_ID, json_projek.getString("id"));
                        values.put(MstFungsiHutanSchema.FUNGSI_HUTAN_KODE, json_projek.getString("kode"));
                        values.put(MstFungsiHutanSchema.FUNGSI_HUTAN_NAME, json_projek.getString("name"));
                        values.put(MstFungsiHutanSchema.KETERANGAN, json_projek.getString("keterangan"));
                        db.create(MstFungsiHutanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_penggunaan_hutan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_PENGGUNAAN_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET PENGGUNAAN HUTAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstPenggunaanHutan._ID, i + 1);
                        values.put(MstPenggunaanHutan.PENGGUNAAN_HUTAN_ID, json_projek.getString("id"));
                        values.put(MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME, json_projek.getString("name"));
                        db.create(MstFungsiHutanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_tanaman_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_TANAMAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS TANAMAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisTanamanSchema._ID, i + 1);
                        values.put(MstJenisTanamanSchema.JENIS_TANAMAN_ID, json_projek.getString("id"));
                        values.put(MstJenisTanamanSchema.JENIS_TANAMAN_NAME, json_projek.getString("name"));
                        db.create(MstJenisTanamanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_permasalahan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_PERMASALAHAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS PERMASALAHAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisPermasalahanSchema._ID, i + 1);
                        values.put(MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_ID, json_projek.getString("id"));
                        values.put(MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME, json_projek.getString("name"));
                        db.create(MstJenisPermasalahanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_pal_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_PALL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS PALL ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisPalSchema._ID, i + 1);
                        values.put(MstJenisPalSchema.JENIS_PAL_ID, json_projek.getString("id"));
                        values.put(MstJenisPalSchema.JENIS_PAL_NAME, json_projek.getString("name"));
                        db.create(MstJenisPalSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_kondisi_pal_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_KONDISI_PAL_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET KONDISI PAL ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstKondisiPalSchema._ID, i + 1);
                        values.put(MstKondisiPalSchema.KONDISI_PAL_ID, json_projek.getString("id"));
                        values.put(MstKondisiPalSchema.KONDISI_PAL_NAME, json_projek.getString("name"));
                        db.create(MstKondisiPalSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_gangguan_hutan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_GANGGUAN_HUTAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS PERMASALAHAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisGangguanHutanSchema._ID, i + 1);
                        values.put(MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_ID, json_projek.getString("id"));
                        values.put(MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME, json_projek.getString("name"));
                        db.create(MstJenisGangguanHutanSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_satwa_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_SATWA_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS SATWA ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisSatwa._ID, i + 1);
                        values.put(MstJenisSatwa.JENIS_SATWA_ID, json_projek.getString("id"));
                        values.put(MstJenisSatwa.JENIS_SATWA_NAME, json_projek.getString("name"));
                        db.create(MstJenisSatwa.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_jenis_temuan_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_JENIS_TEMUAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET JENIS SATWA ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstJenisTemuan._ID, i + 1);
                        values.put(MstJenisTemuan.JENIS_TEMUAN_ID, json_projek.getString("id"));
                        values.put(MstJenisTemuan.JENIS_TEMUAN_NAME, json_projek.getString("name"));
                        db.create(MstJenisTemuan.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_waktu_terlihat_v1(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_WAKTU_LIHAT_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET WAKTU LIHAT ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstWaktuLihatSchema._ID, i + 1);
                        values.put(MstWaktuLihatSchema.WAKTU_LIHAT_ID, json_projek.getString("id"));
                        values.put(MstWaktuLihatSchema.WAKTU_LIHAT_NAME, json_projek.getString("name"));
                        db.create(MstWaktuLihatSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_master_desa(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_DESA_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER DESA ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstDesaSchema._ID, i + 1);
                        values.put(MstDesaSchema.ID_DESA, json_projek.getString("id"));
                        values.put(MstDesaSchema.KODE_DESA, json_projek.getString("kode"));
                        values.put(MstDesaSchema.KECAMATAN_ID, json_projek.getString("kecamatan_id"));
                        values.put(MstDesaSchema.NAMA_DESA, json_projek.getString("name"));
                        db.create(MstDesaSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_master_interaksi(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_BENTUK_INTERAKSI_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER BENTUK INTERAKSI ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstBentukInteraksiSchema._ID, i + 1);
                        values.put(MstBentukInteraksiSchema.ID_INTERAKSI, json_projek.getString("id"));
                        values.put(MstBentukInteraksiSchema.NAMA_INTERAKSI, json_projek.getString("name"));
                        db.create(MstBentukInteraksiSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_master_status_interaksi(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_STATUS_INTERAKSI_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER STATUS INTERAKSI ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstStatusInteraksiSchema._ID, i + 1);
                        values.put(MstStatusInteraksiSchema.ID_STATUS, json_projek.getString("id"));
                        values.put(MstStatusInteraksiSchema.NAMA_STATUS, json_projek.getString("name"));
                        db.create(MstStatusInteraksiSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_pihak_terlibat(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_PIHAK_TERLIBAT_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER PIHAK TERLIBAT ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstPihakTerlibatSchema._ID, i + 1);
                        values.put(MstPihakTerlibatSchema.PIHAK_TERLIBAT_ID, json_projek.getString("id"));
                        values.put(MstPihakTerlibatSchema.PIHAK_TERLIBAT_NAME, json_projek.getString("name"));
                        db.create(MstPihakTerlibatSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_strata(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_STRATA_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER STRATA ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstStrataSchema._ID, i + 1);
                        values.put(MstStrataSchema.STRATA_ID, json_projek.getString("id"));
                        values.put(MstStrataSchema.STRATA_NAME, json_projek.getString("name"));
                        db.create(MstStrataSchema.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_pekerjaan(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_PEKERJAAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER PEKERJAAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstPekerjaan._ID, i + 1);
                        values.put(MstPekerjaan.PEKERJAAN_ID, json_projek.getString("id"));
                        values.put(MstPekerjaan.PEKERJAAN_NAME, json_projek.getString("name"));
                        values.put(MstPekerjaan.PEKERJAAN_SATUAN, json_projek.getString("satuan"));
                        db.create(MstPekerjaan.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_sub_pekerjaan(final String token, final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_SUB_PEKERJAAN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER SUB PEKERJAAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstSubPekerjaan._ID, i + 1);
                        values.put(MstSubPekerjaan.SUB_PEKERJAAN_ID, json_projek.getString("id"));
                        values.put(MstSubPekerjaan.SUB_PEKERJAAN_NAME, json_projek.getString("name"));
                        db.create(MstSubPekerjaan.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_workorder(final String token,final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_WORKORDER_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER WORK ORDER ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(TrnWorkOrder._ID, i + 1);
                        values.put(TrnWorkOrder.WORKORDER, json_projek.getString("workorder"));
                        values.put(TrnWorkOrder.TANGGAL, json_projek.getString("tanggal"));
                        values.put(TrnWorkOrder.TANGGAL, json_projek.getString("tanggal"));
                        values.put(TrnWorkOrder.DARI, json_projek.getString("dari"));
                        values.put(TrnWorkOrder.UNTUK, json_projek.getString("untuk"));
                        values.put(TrnWorkOrder.JENISKEGIATAN, json_projek.getString("jeniskegiatan"));
                        values.put(TrnWorkOrder.TARGET, json_projek.getString("target"));
                        values.put(TrnWorkOrder.STATUS, json_projek.getString("status"));
                        values.put(TrnWorkOrder.KETERANGAN, json_projek.getString("keterangan"));
                        values.put(TrnWorkOrder.CREATED_AT, json_projek.getString("created_at"));
                        values.put(TrnWorkOrder.UPDATED_AT, json_projek.getString("updated_at"));
                        db.create(TrnWorkOrder.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_susunrisalah(final String token,final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_SUSUN_RISALAH_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER SUSUN RISALAH ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(TrnSusunRisalah._ID, i + 1);
                        values.put(TrnSusunRisalah.ANAK_PETAK_ID, json_projek.getString("anakpetak_id"));
                        values.put(TrnSusunRisalah.LUAS, json_projek.getString("luas"));
                        values.put(TrnSusunRisalah.JENIS_TANAMAM, json_projek.getString("jenis_tanaman"));
                        values.put(TrnSusunRisalah.PENINGGI, json_projek.getString("peninggi"));
                        values.put(TrnSusunRisalah.BONITA, json_projek.getString("bonita"));
                        values.put(TrnSusunRisalah.KBD, json_projek.getString("kbd"));
                        values.put(TrnSusunRisalah.DKN, json_projek.getString("dkn"));
                        values.put(TrnSusunRisalah.N_HA, json_projek.getString("n_ha"));
                        values.put(TrnSusunRisalah.STATUS, json_projek.getString("status_id"));
                        values.put(TrnSusunRisalah.TAHUN_TANAM, json_projek.getString("tahun_tanam"));
                        values.put(TrnSusunRisalah.TAHUN_TANAM_RATA, json_projek.getString("tahun_tanam_rata"));
                        values.put(TrnSusunRisalah.PERMUDAAN, json_projek.getString("permudaan"));
                        values.put(TrnSusunRisalah.TANAMAN_SELA, json_projek.getString("tanaman_sela"));
                        values.put(TrnSusunRisalah.BENTUK_LAPANGAN, json_projek.getString("bentuk_lapangan"));
                        values.put(TrnSusunRisalah.RISALAH_TANAH, json_projek.getString("risalah_tanah"));
                        values.put(TrnSusunRisalah.RISALAH_TEGAKAN_TANAMAN_BAWAH, json_projek.getString("risalah_tegakan_tanaman_bawah"));
                        values.put(TrnSusunRisalah.KETERANGAN, json_projek.getString("keterangan"));
                        values.put(TrnSusunRisalah.KELAS_HUTAN, json_projek.getString("kelashutan_id"));
                        values.put(TrnSusunRisalah.CREATED_AT, json_projek.getString("created_at"));
                        values.put(TrnSusunRisalah.UPDATED_AT, json_projek.getString("updated_at"));
                        db.create(TrnSusunRisalah.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
    public void sync_get_monitoring(final String token,final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_MONITORING_KLSHTN_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER MONITORING KH PENAMPAKAN ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(TrnMonitoringKlsHtn._ID, i + 1);
                        values.put(TrnMonitoringKlsHtn.SUSUN_RISALAH_ID, json_projek.getString("susun_risalah_id"));
                        values.put(TrnMonitoringKlsHtn.KBD, json_projek.getString("kbd"));
                        values.put(TrnMonitoringKlsHtn.N_HA, json_projek.getString("n_ha"));
                        values.put(TrnMonitoringKlsHtn.KELAS_HUTAN_ID, json_projek.getString("kelashutan_id"));
                        values.put(TrnMonitoringKlsHtn.TAHUN, json_projek.getString("tahun"));
                        values.put(TrnMonitoringKlsHtn.CATATAN_KHUSUS, json_projek.getString("catatan_khusus"));
                        values.put(TrnMonitoringKlsHtn.CREATED_AT, json_projek.getString("created_at"));
                        values.put(TrnMonitoringKlsHtn.UPDATED_AT, json_projek.getString("updated_at"));
                        db.create(TrnMonitoringKlsHtn.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void sync_get_tallysheet(final String token,final String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(URL_FOR_GET_TALLY_SHEET_V1);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Authorization", "Bearer " + token);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.i("JSON_ACTION", "================ API GET MASTER TALLY SHEET ========================");
                    Log.i("JSON_SEND_TOKEN", token);
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(TrnTallySheet._ID, i + 1);
                        values.put(TrnTallySheet.ID_TALLYSHEET, json_projek.getString("id"));
                        values.put(TrnTallySheet.BAGIAN_HUTAN, json_projek.getString("bh_id"));
                        values.put(TrnTallySheet.BAGIAN_HUTAN_NAME, json_projek.getString("bagianhutan_name"));
                        values.put(TrnTallySheet.KPH, json_projek.getString("kph_id"));
                        values.put(TrnTallySheet.KPH_NAME, json_projek.getString("kph_name"));
                        values.put(TrnTallySheet.BKPH, json_projek.getString("bkph_id"));
                        values.put(TrnTallySheet.BKPH_NAME, json_projek.getString("bkph_name"));
                        values.put(TrnTallySheet.RPH, json_projek.getString("rph_id"));
                        values.put(TrnTallySheet.RPH_NAME, json_projek.getString("rph_name"));
                        values.put(TrnTallySheet.PETAK, json_projek.getString("petak_id"));
                        values.put(TrnTallySheet.PETAK_NAME, json_projek.getString("petak_name"));
                        values.put(TrnTallySheet.ANAK_PETAK, json_projek.getString("anakpetak_id"));
                        values.put(TrnTallySheet.ANAK_PETAK_NAME, json_projek.getString("anakpetak_name"));
                        values.put(TrnTallySheet.DESA, json_projek.getString("desa_id"));
                        values.put(TrnTallySheet.DESA_NAME, json_projek.getString("desa_name"));
                        values.put(TrnTallySheet.JARAK_DESA, json_projek.getString("jarak"));
                        values.put(TrnTallySheet.KECAMATAN, json_projek.getString("kecamatan_id"));
                        values.put(TrnTallySheet.KECAMATAN_NAME, json_projek.getString("kecamatan_name"));
                        values.put(TrnTallySheet.KABUPATEN, json_projek.getString("kabupaten_id"));
                        values.put(TrnTallySheet.KABUPATEN_NAME, json_projek.getString("kabupaten_name"));
                        values.put(TrnTallySheet.TINGGI_PDL, json_projek.getString("tinggidpl"));
                        values.put(TrnTallySheet.IKLIM, json_projek.getString("iklim"));
                        values.put(TrnTallySheet.CURAH_HUJAN, json_projek.getString("curahhujan"));
                        values.put(TrnTallySheet.KELAS_HUTAN, json_projek.getString("kh_id"));
                        values.put(TrnTallySheet.KELAS_HUTAN_NAME, json_projek.getString("kelashutan_name"));
                        values.put(TrnTallySheet.FUNGSI_HUTAN, json_projek.getString("fh_id"));
                        values.put(TrnTallySheet.FUNGSI_HUTAN_NAME, json_projek.getString("fungsihutan_name"));
                        values.put(TrnTallySheet.PENGGUNAAN_HUTAN, json_projek.getString("ph_id"));
                        values.put(TrnTallySheet.PENGGUNAAN_HUTAN_NAME, json_projek.getString("penggunaan_name"));
                        values.put(TrnTallySheet.TAHUN_TANAM, json_projek.getString("tahuntanam"));
                        values.put(TrnTallySheet.BONITA_LALU, json_projek.getString("bonitalalu"));
                        values.put(TrnTallySheet.BONITA_BARU, json_projek.getString("bonita"));
                        values.put(TrnTallySheet.KBD, json_projek.getString("kbd"));
                        values.put(TrnTallySheet.DKN, json_projek.getString("dkn"));
                        values.put(TrnTallySheet.VOLUME, json_projek.getString("volume"));
                        values.put(TrnTallySheet.INTENSITAS_SAMPLING, json_projek.getString("intensitassampling"));
                        values.put(TrnTallySheet.CARA_SAMPLING, json_projek.getString("carasampling"));
                        values.put(TrnTallySheet.TGL_INVENTARISASI, json_projek.getString("tanggalinven"));
                        values.put(TrnTallySheet.PELAKSANA, json_projek.getString("pelaksana"));
                        values.put(TrnTallySheet.KEPALA_SEKSI, json_projek.getString("kepalaseksi"));
                        values.put(TrnTallySheet.NO_RAK, json_projek.getString("no_rak"));
                        values.put(TrnTallySheet.NO_LACI, json_projek.getString("no_laci"));
                        values.put(TrnTallySheet.TALLYSHEET_PLOT, json_projek.getString("tallysheetplot"));
                        values.put(TrnTallySheet.KETERANGAN, json_projek.getString("keterangan"));
                        values.put(TrnTallySheet.KET8, "0");
                        values.put(TrnTallySheet.KET9, "0");
                        db.create(TrnTallySheet.TABLE_NAME, values);
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    Log.i("JSON_ERROR", e.toString());
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}

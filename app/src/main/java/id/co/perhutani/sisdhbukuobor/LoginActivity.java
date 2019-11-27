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
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class LoginActivity extends AppCompatActivity {
    // server staging
    private static final String address = "http://10.0.8.51:9393/";
    // server production
//  private static final String address = "https://union-loket.perhutani.id/";
// server local
//private static final String address = "http://127.0.0.1:8000/";
// link api
    private static final String URL_FOR_LOGIN_V1 = address + "api/v1/login";
    private static final String URL_FOR_PROFIL_V1 = address + "api/v1/get_user_details";
    private static final String URL_FOR_GET_ANAK_PETAK_V1 = address + "api/v1/getAnakPetak";
    private static final String URL_FOR_GET_KELAS_HUTAN_V1 = address + "api/v1/getKelasHutan";
    private static final String URL_FOR_GET_JENIS_TANAMAN_V1 = address + "api/v1/getJenisTanaman";
    private static final String URL_FOR_GET_JENIS_PERMASALAHAN_V1 = address + "api/v1/getJenisPermasalahan";

    private ProgressDialog progressDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private EditText username, password;
    private Button loginBtn;
    private CheckBox ShowPass;

    Boolean cek_login_api = false;
    private int i = -1;

    String error_message = "";
    String feedback_error_message = "";

    TextView txt_testview;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        ShowPass = findViewById(R.id.showPass);
        loginBtn = findViewById(R.id.loginBtn);
        txt_testview = findViewById(R.id.txt_testview);

        Thread background = new Thread() {
            public void run() {
                try {
                    askForPermission();
                } catch (Exception e) {
                }
            }
        };
        background.start();

        username.setText("adhi_ganteng");
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // mengaktifkan service sentry io
        AjnClass.pasang_sentry(this.getApplicationContext());
//        AjnClass.test_fungsi_sentry();

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShowPass.isChecked()) {
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //Jika tidak, maka password akan di sembuyikan
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
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
                            pDialog.setTitleText("Success!")
                                    .setConfirmText("OK")
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    Intent i = new Intent(LoginActivity.this, SplashScreenActivity.class);
                                    startActivity(i);
                                    finish();
                                    pDialog.cancel();
                                }

                            }, 1000);

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
                    Log.i("JSON_STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("JSON_MESSAGE", conn.getResponseMessage());
                    Log.i("JSON_TOKEN", myResponse.getString("access_token"));

                    // get data profil user
                    sync_profil_v1(myResponse.getString("access_token"), username.getText().toString());
                    // get data anak petak
                    sync_get_anak_petak_v1(myResponse.getString("access_token"), username.getText().toString());
                    // get data anak petak
                    sync_get_kelas_hutan_v1(myResponse.getString("access_token"), username.getText().toString());
                    // get data anak petak
                    sync_get_jenis_tanaman_v1(myResponse.getString("access_token"), username.getText().toString());
                    // get data anak petak
                    sync_get_jenis_permasalahan_v1(myResponse.getString("access_token"), username.getText().toString());

                    session.setLogin(true);

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
                    values.put(UserSchema.KET10, "");
                    db.create(UserSchema.TABLE_NAME, values);

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
//                    Log.i("JSON_DATA", json_data.getString("data"));
//                    Log.i("JSON_DATA_JUMLAH", String.valueOf(jsonArray.length()));
                    JSONObject result = new JSONObject(response.toString());
                    JSONArray jsonArray = result.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_projek = jsonArray.getJSONObject(i);
                        ContentValues values = new ContentValues();
                        values.put(MstAnakPetakSchema._ID, i + 1);
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


}

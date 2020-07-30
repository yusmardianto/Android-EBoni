package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.PengukuranPohonAdapter;
import id.co.perhutani.sisdhbukuobor.Adapter.TallySheet.TallySheetAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.DetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.ListTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TabDetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PasangBatasPersemaian.TambahPasangBatasPersemaian;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.MstKonversiKeliling;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

import static android.app.Activity.RESULT_OK;


public class ListPengukuranPohonFragment extends Fragment {

    View list_pu,popup_tambah,popup_edit_header;
    private EditText no_pohon, keliling_pohon,peninggi_pohon,kualitas_batang, bidang_dasar;
    private AlertDialog.Builder builder;
    private RecyclerView rv_pengukuran_pohon;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<PuPohonModel> dataModels;
    private PengukuranPohonAdapter pengukuranPohonAdapter;
    private static final int VERTICAL_ITEM_SPACE = 0;
    private SessionManager session;
    private static String pu_id_tallysheet;

    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    double lattitude;
    double longitude;
    static final int REQUEST_PHONESTATS_PERMISSION = 0x1;
    static final int REQUEST_CAMERA_PERMISSION = 201;
    static final int REQUEST_LOCATION_PERMISSION = 101;
    static final int REQUEST_READ_EXTERNAL_STORAGE = 3;

    LinearLayout upload_photopohon;

    TextView header_anakpetak, header_luaspu, header_kodenomor;
    LinearLayout edit_header;

    EditText edt_header_anak_petak, edt_header_kodenomor;
    Spinner edt_luaspu;

    ImageView photoPohon;
    Bitmap bitmap;
    private boolean cek_photo1 = false;
    private String Document_img1 = "";

    public static String id_pu;
    private int color = 0;
    private SQLiteHandler db;
    private List<PuPohonModel> ls_pu_pohon;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (isOnline()) {
                    dataPengukuranPohon();
                }
            } catch (Exception ex) {
            }
            handler.postDelayed(this, 10000);
        }
    };
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    EditText id_pupu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getActivity());
        try {
            id_pu = session.getPreferences(getActivity(),"ses_id_tallysheet");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        AjnClass.showAlert(getActivity(), id_pu);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        list_pu = inflater.inflate(R.layout.fragment_list_pengukuran_pohon, container, false);
        db = new SQLiteHandler(getActivity());
        handler.postDelayed(runnable, 1000);
//        askForPermission();

        final String get_header_anakpetak = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.ANAK_PETAK_NAME);
        final String get_header_kodenomor = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.KODE_PU);
        final String get_header_getluaspu = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.LUAS_PU);

        header_anakpetak = list_pu.findViewById(R.id.header_ts_anakpetak);
        header_anakpetak.setText(get_header_anakpetak);
        header_luaspu = list_pu.findViewById(R.id.header_ts_luaspu);
        header_luaspu.setText(get_header_getluaspu);
        header_kodenomor = list_pu.findViewById(R.id.header_ts_kode_nomor);
        header_kodenomor.setText(get_header_kodenomor);
        edit_header = list_pu.findViewById(R.id.linear_edit_header_ts);
        edit_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                popup_edit_header = factory.inflate(R.layout.popup_edit_header_data_ts, null);
                db = new SQLiteHandler(getActivity());

                edt_header_anak_petak = popup_edit_header.findViewById(R.id.edit_header_ts_anakpetak);
                edt_header_kodenomor = popup_edit_header.findViewById(R.id.edit_header_ts_kode_nomor);
                edt_luaspu = popup_edit_header.findViewById(R.id.edit_header_ts_luaspu);

                String compareValue = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.LUAS_PU);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_luas_pu, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edt_luaspu.setAdapter(adapter);
                if (compareValue != null) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    edt_luaspu.setSelection(spinnerPosition);
                }
                edt_header_anak_petak.setText(get_header_anakpetak);
                edt_header_kodenomor.setText(get_header_kodenomor);

                builder.setView(popup_edit_header);
                builder.setCancelable(false)
                        .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                try {

                                    final String str_luaspu = edt_luaspu.getSelectedItem().toString();
                                    String ubah_luaspu = null;
                                    if (str_luaspu.equals("0.020")) {
                                        ubah_luaspu = "0.020";
                                    } else if (str_luaspu.equals("0.040")) {
                                        ubah_luaspu = "0.040";
                                    }else if (str_luaspu.equals("0.100")) {
                                        ubah_luaspu = "0.100";
                                    }

                                    final String ubah_anakpetak = edt_header_anak_petak.getText().toString();
                                    final String ubah_kodepu = edt_header_kodenomor.getText().toString();
                                    final String ubah_luaspetakukur = ubah_luaspu;

                                    TallySheetModel update_header_ts = new TallySheetModel();
                                    update_header_ts.setTs_id(id_pu);
                                    update_header_ts.setTs_kode_pu(ubah_kodepu);
                                    update_header_ts.setTs_luaspu(ubah_luaspetakukur);
                                    update_header_ts.setTs_anak_petak(ubah_anakpetak);
                                    update_header_ts.setTs_ket9("2");
                                    db.EditHeaderTallySheet(update_header_ts);

                                    Toast.makeText(getActivity(), "Data Berhasil Diubah! ", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                    Fragment fragment = new TabDetailTallySheetFragment();
                                    FragmentTransaction ft = manager.beginTransaction();
                                    ft.replace(R.id.nav_host_fragment, fragment);
                                    ft.commit();
                                }
                                catch (Exception e) {
                                    AjnClass.showAlert(getActivity(), "error \n\n" + e.toString());
                                }


                            }


                        })

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        FloatingActionButton btnTambah = list_pu.findViewById(R.id.btn_tambahpohonpu);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle data = new Bundle();
//                data.putString("message",id_pu);
//                FragmentManager manager = getFragmentManager();
//                Fragment fragment = new CreatePuPohonFragment();
//                fragment.setArguments(data);
//                FragmentTransaction ft = manager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();

                getLastLocation();

                builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater factory = LayoutInflater.from(getActivity());
                popup_tambah = factory.inflate(R.layout.fragment_create_pu_pohon, null);
                db = new SQLiteHandler(getActivity());

                upload_photopohon = popup_tambah.findViewById(R.id.linear_photo_selfie);

                photoPohon = popup_tambah.findViewById(R.id.iv_photo_pohon);
                final String cek_photo = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.CEK_PHOTO);
                if (cek_photo.equals("1")){
                    upload_photopohon.setVisibility(View.GONE);
                }
                else {
                    upload_photopohon.setVisibility(View.VISIBLE);
                }
                photoPohon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cek_photo1 = true;

                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        startActivityForResult(intent, 1);

//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
////                        Uri apkURI = FileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getPackageName() + ".provider", g);
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
////                            intent.putExtra(MediaStore.EXTRA_OUTPUT, apkURI);
////                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////                        } else {
////                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(g));
////                        }
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                        startActivityForResult(intent, 0);
                    }
                });

                final TextView no_pu = popup_tambah.findViewById(R.id.no_petak_ukur);
                no_pu.setText(id_pu);
                no_pohon = popup_tambah.findViewById(R.id.pu_no_pohon);
                keliling_pohon = popup_tambah.findViewById(R.id.pu_keliling_pohon);
                bidang_dasar = popup_tambah.findViewById(R.id.pu_bidang_dasar);
                peninggi_pohon = popup_tambah.findViewById(R.id.pu_peninggi_pohon);
                kualitas_batang = popup_tambah.findViewById(R.id.pu_kualitas_batang);

                final String cek_latlng = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.CEK_LATLNG);
                final String get_lat = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.LATTITUDE);
                final String get_lng = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.LONGITUDE);

                Calendar c = Calendar.getInstance();
//                SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                final String formattedDate = df.format(c.getTime());
                bidang_dasar.setText(getUUID());

                builder.setView(popup_tambah);
                builder.setCancelable(false)
                        .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                try {

                                    if (!vallidasi()) {
                                        return;
                                    }
                                        String input = keliling_pohon.getText().toString();
                                        final String get_bidang_dasar = db.getDataDetail(MstKonversiKeliling.TABLE_NAME, MstKonversiKeliling.KELILING,input,MstKonversiKeliling.BIDANG_DASAR);

                                    ContentValues xxxx = new ContentValues();
                                        xxxx.put(TrnPuPohon.TS_ID, id_pu);
                                        xxxx.put(TrnPuPohon.NO_POHON, no_pohon.getText().toString());
                                        xxxx.put(TrnPuPohon.KELILING_POHON, input);
                                        xxxx.put(TrnPuPohon.PENINGGI_POHON, peninggi_pohon.getText().toString());
                                        xxxx.put(TrnPuPohon.BIDANG_DASAR, get_bidang_dasar);
                                        xxxx.put(TrnPuPohon.UUID, getUUID());
                                        xxxx.put(TrnPuPohon.KET9, "0");
                                        db.create(TrnPuPohon.TABLE_NAME, xxxx);

                                        TallySheetModel update_ts = new TallySheetModel();
                                        update_ts.setTs_id(id_pu);

                                        if (cek_latlng.equals("1")){
                                            update_ts.setTs_lattitude(get_lat);
                                            update_ts.setTs_longitude(get_lng);
                                        }else {
                                            update_ts.setTs_lattitude(String.valueOf(lattitude));
                                            update_ts.setTs_longitude(String.valueOf(longitude));
                                        }

                                        final String cek_photo = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.CEK_PHOTO);
                                        final String get_photo = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,id_pu,TrnTallySheet.PHOTO_POHON);
                                        if (cek_photo.equals("1")){
//                                            byte[] decodedString = Base64.decode(get_photo, Base64.DEFAULT);
//                                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                            update_ts.setTs_photopohon(get_photo);
                                        }else {
                                            update_ts.setTs_photopohon(BitMapToString(bitmap));
                                        }

                                        update_ts.setTglinven(formattedDate);
                                        update_ts.setCek_photo("1");
                                        update_ts.setCek_latlng("1");
                                        db.EditDataTallySheet(update_ts);

                                        Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                        FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                        Fragment fragment = new TabDetailTallySheetFragment();
                                        FragmentTransaction ft = manager.beginTransaction();
                                        ft.replace(R.id.nav_host_fragment, fragment);
                                        ft.commit();
                                }
                                catch (Exception e) {
                                    AjnClass.showAlert(getActivity(), "error \n\n" + e.toString());
                                }


                            }


                        })

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        rv_pengukuran_pohon = list_pu.findViewById(R.id.rv_pengukuran_pohon);
        swipeRefreshLayout = list_pu.findViewById(R.id.swipe_refresh_layout_recycler_view_pengukuran_pohon);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (color > 4) {
                            color = 0;
                        }
                        ls_pu_pohon.clear();
                        try {
                            pengukuranPohonAdapter = new PengukuranPohonAdapter(getActivity(),ls_pu_pohon);
                            rv_pengukuran_pohon.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_pengukuran_pohon.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            rv_pengukuran_pohon.setAdapter(pengukuranPohonAdapter);

                            try {

                                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                                final Cursor cur = db.rawQuery("SELECT " +
                                        " ID" +
                                        " FROM TRN_PU_POHON " +
                                        " WHERE TS_ID = '"+ id_pu + "'" +
                                        " ORDER BY ID DESC", null);

                                cur.moveToPosition(0);
                                dataModels = new ArrayList<>();
                                for (int i = 0; i < cur.getCount(); i++) {
                                    ls_pu_pohon.add(new PuPohonModel(
                                            cur.getString(0)
                                    ));
                                    cur.moveToNext();
                                }

                                cur.close();
                                db.close();

                            } catch (Exception ex) {
                                AjnClass.showAlert(getActivity(), ex.toString());
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        pengukuranPohonAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

        final LinearLayout datakosong = list_pu.findViewById(R.id.layout_tidakadadata_pu);

        final int ceksampling = db.cek_jumlah_data(TrnPuPohon.TABLE_NAME);
        if(String.valueOf(ceksampling).equals("0"))
        {
            datakosong.setVisibility(View.VISIBLE);
            rv_pengukuran_pohon.setVisibility(View.GONE);
        }else {
            datakosong.setVisibility(View.GONE);
            rv_pengukuran_pohon.setVisibility(View.VISIBLE);
        }

        dataPengukuranPohon();

        return list_pu;
    }

    public void dataPengukuranPohon(){
        ls_pu_pohon = new ArrayList<>();
        try {
            pengukuranPohonAdapter = new PengukuranPohonAdapter(getActivity(),ls_pu_pohon);
            rv_pengukuran_pohon.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_pengukuran_pohon.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
            rv_pengukuran_pohon.setAdapter(pengukuranPohonAdapter);

            try {

                SQLiteHandler DB_Helper = new SQLiteHandler(getActivity());
                SQLiteDatabase db = DB_Helper.getReadableDatabase();
                final Cursor cur = db.rawQuery("SELECT " +
                        "ID" +
                        " FROM TRN_PU_POHON " +
                        " WHERE TS_ID="+id_pu+
                        " ORDER BY ID DESC", null);

                cur.moveToPosition(0);
                dataModels = new ArrayList<>();
                for (int i = 0; i < cur.getCount(); i++) {
                    ls_pu_pohon.add(new PuPohonModel(
                            cur.getString(0)
                    ));
                    cur.moveToNext();
                }

                cur.close();
                db.close();

            } catch (Exception ex) {
                AjnClass.showAlert(getActivity(), ex.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean vallidasi() {
        if (no_pohon.getText().toString().equals("") || no_pohon.getText().toString().equals("0") || no_pohon.getText().toString().equals(" ") || no_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Nomor Pohon harus diisi");
            return false;

        }else if (keliling_pohon.getText().toString().equals("") || keliling_pohon.getText().toString().equals("0") || keliling_pohon.getText().toString().equals(" ") || keliling_pohon.getText().toString().equals(null)) {
            AjnClass.showAlert(getActivity(), "Keliling Pohon harus diisi");
            return false;

        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lattitude = location.getLatitude();
                                    longitude = location.getLongitude();


//                                    latlngLocation.setText(location.getLatitude() + "" + location.getLongitude() + "");
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
//            latlngLocation.setText(mLastLocation.getLatitude() + "" + mLastLocation.getLongitude() + "");
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File f = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }

            if (requestCode == 1) {
                try {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    bitmap = getResizedBitmap(bitmap, 800);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    photoPohon.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 30, baos);
        byte[] b = baos.toByteArray();
        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
        return Document_img1;
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", ""); //UUID
    }

}
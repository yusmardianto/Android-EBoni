package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.AjnClass;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SessionManager;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.DetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.ListTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TabDetailTallySheetFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetFragment;
import id.co.perhutani.sisdhbukuobor.R;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;

import static android.app.Activity.RESULT_OK;

public class CreatePuPohonFragment extends Fragment {

    View create_pu_pohon;
    private static SQLiteHandler db;
    private static String pu_id_tallysheet;
    private EditText no_pohon, keliling_pohon,peninggi_pohon,kualitas_batang;
    private Button btn_simpan_pu;
    private SessionManager session;
    private static int data_jmlPengukuranpohon;

    ImageView photoPohon;
    Bitmap bitmap;
    private String Document_img1 = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        create_pu_pohon = inflater.inflate(R.layout.fragment_create_pu_pohon, container, false);

        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());
//        try {
//            String message = session.getPreferences(getActivity(),"ses_id_tallysheet");
//            if (message != null) {
//                pu_id_tallysheet=message;
//            } else {
//                pu_id_tallysheet="null";
//                AjnClass.showAlert(getActivity(), "Terjadi kesalahan dalam pengambilan data");
//                Fragment fragment = new ListTallySheetFragment();
//                FragmentManager frgManager = getFragmentManager();
//                FragmentTransaction ft = frgManager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        try {
            String message = getArguments().getString("message");
            if (message != null) {
                pu_id_tallysheet = message;
            } else {
                pu_id_tallysheet = "null";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }



        Toolbar toolbar = create_pu_pohon.findViewById(R.id.toolbar_create_petakukur);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetailTallySheetFragment();
                FragmentManager frgManager = getFragmentManager();
                FragmentTransaction ft = frgManager.beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        });

        TextView no_pu = create_pu_pohon.findViewById(R.id.no_petak_ukur);
        no_pu.setText(pu_id_tallysheet);

        photoPohon = create_pu_pohon.findViewById(R.id.iv_photo_pohon);
                final String cek_photo = db.getDataDetail(TrnTallySheet.TABLE_NAME, TrnTallySheet._ID,pu_id_tallysheet,TrnTallySheet.CEK_PHOTO);
                if (cek_photo.equals("1")){
                    photoPohon.setVisibility(View.GONE);
                }
                else {
                    photoPohon.setVisibility(View.VISIBLE);
                }
                photoPohon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File g = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(g));
                        startActivityForResult(intent, 2);
                    }
                });

        no_pohon = create_pu_pohon.findViewById(R.id.pu_no_pohon);
        keliling_pohon = create_pu_pohon.findViewById(R.id.pu_keliling_pohon);
        peninggi_pohon = create_pu_pohon.findViewById(R.id.pu_peninggi_pohon);
        kualitas_batang = create_pu_pohon.findViewById(R.id.pu_kualitas_batang);
//        btn_simpan_pu = create_pu_pohon.findViewById(R.id.pu_btnsubmit);
//
//        btn_simpan_pu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                simpanData();
//            }
//        });

        return create_pu_pohon;
    }

    public void simpanData(){

        final String save_ts_id = pu_id_tallysheet;
        final String save_no_pohon = no_pohon.getText().toString();
        final String save_keliling_pohon = keliling_pohon.getText().toString();
        final String save_peninggi_pohon = peninggi_pohon.getText().toString();
        final String save_kualitas_batang = kualitas_batang.getText().toString();

        if (save_no_pohon.equals("") || save_no_pohon.equals("0") || save_no_pohon.equals(" ") || save_no_pohon.equals(null)) {
            AjnClass.showAlert(getActivity(), "Nomor Pohon harus diisi");

        }else if (save_keliling_pohon.equals("") || save_keliling_pohon.equals("0") || save_keliling_pohon.equals(" ") || save_keliling_pohon.equals(null)) {
            AjnClass.showAlert(getActivity(), "Keliling Pohon harus diisi");

        }else if (save_peninggi_pohon.equals("") || save_peninggi_pohon.equals("0") || save_peninggi_pohon.equals(" ") || save_peninggi_pohon.equals(null)) {
            AjnClass.showAlert(getActivity(), "Peninggi Pohon harus diisi");

        }else if (save_kualitas_batang.equals("") || save_kualitas_batang.equals("0") || save_kualitas_batang.equals(" ") || save_kualitas_batang.equals(null)) {
            AjnClass.showAlert(getActivity(), "Kualitas Batang harus diisi");

        } else {

            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Simpan ?")
                    .setContentText("")
                    .setCancelText("Batal")
                    .setConfirmText("Simpan")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // reuse previous dialog instance, keep widget user state, reset them if you need
                            sDialog.setTitleText("Dibatalkan!")
                                    .setContentText("")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.setTitleText("Success!")
                                    .setContentText("")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setCancelClickListener(null)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    try {

//                                            String ambilKunci = "perhutani";
//                                            String ambilKata = isipetak.getText().toString();
//                                            String enKata = "";
//                                        data_jmlPengukuranpohon = db.cek_jumlah_data(TrnPuPohon.TABLE_NAME);
//                                        for (int i = 0; i < data_jmlPengukuranpohon; i++) {
                                            ContentValues values_petakukur = new ContentValues();
//                                            enKata = GenerateAESAdapter.encrypt(ambilKunci, ambilKata);
                                            values_petakukur.put(TrnPuPohon.TS_ID, save_ts_id);
                                            values_petakukur.put(TrnPuPohon.PU_POHON_ID, "1");
                                            values_petakukur.put(TrnPuPohon.NO_POHON, save_no_pohon);
                                            values_petakukur.put(TrnPuPohon.KELILING_POHON, save_keliling_pohon);
                                            values_petakukur.put(TrnPuPohon.PENINGGI_POHON, save_peninggi_pohon);
                                            values_petakukur.put(TrnPuPohon.KET9, "0");
                                            db.create(TrnPuPohon.TABLE_NAME, values_petakukur);
//                                        }
                                        Toast.makeText(getActivity(), "Data Berhasil Ditambah! ", Toast.LENGTH_SHORT).show();

//                                          // Move to fragment gangguan
                                        FragmentManager manager = (getActivity()).getSupportFragmentManager();
                                        Fragment fragment = new TabDetailTallySheetFragment();
                                        FragmentTransaction ft = manager.beginTransaction();
                                        ft.replace(R.id.nav_host_fragment, fragment);
                                        ft.commit();

                                    } catch (Exception e) {
                                        AjnClass.showAlert(getActivity(), e.toString());
                                        e.printStackTrace();
                                    }
                                }

                            }, 1000);
                        }
                    })
                    .show();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            File g = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : g.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    g = temp;
                    break;
                }
            }

            if (requestCode == 1) {
                try {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(g.getAbsolutePath(), bitmapOptions);
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

}
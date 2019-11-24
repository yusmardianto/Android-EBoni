package id.co.perhutani.sisdhbukuobor.ExtentionClass;
/**
 * Created by Adhi Joyo Negoro on 01-11-2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.provider.Settings;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AjnClass {


    public static void pasang_sentry(Context activity) {
        Context ctx = activity.getApplicationContext();
        // server Sentry.perhutani
        String sentryDsn = "https://335b0796dffb495cb28c3e7dd139e678:10ba2040cc494d76a464524600c612a9@sentry.perhutani.id/10";
        // server Sentry.io
//        String sentryDsn = "https://a4a8d7d748d345259c4fc37c7d22fcbd:3135365d037a45c893317eb8621e5f98@sentry.io/1807961";
//        Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));
    }

    public static void test_fungsi_sentry() {
        throw new UnsupportedOperationException("Masoookkk.. Sentry Perhutani");
    }


    public static String getAndroidId(Context context) {
        String androidid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidid;
    }


    public static String getUniqueId(Context context) {
        String uniqueID = null;
        String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String[] getDateTime() {
        SimpleDateFormat format_view = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format_view_1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat format_db = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat format_db_minnutes = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat format_api = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format_db2 = new SimpleDateFormat("ddMMyy");
        final Calendar c = Calendar.getInstance();
        String dateTime [] = new String[8];
        dateTime[0] = c.get(Calendar.DAY_OF_MONTH) +"/"+ c.get(Calendar.MONTH) +"/"+ c.get(Calendar.YEAR);
        dateTime[1] = c.get(Calendar.HOUR_OF_DAY) +":"+ c.get(Calendar.MINUTE)+":"+ c.get(Calendar.SECOND);
        dateTime[2] = format_view.format(new Date());
        dateTime[3] = format_db.format(new Date());
        dateTime[4] = format_db_minnutes.format(new Date());
        dateTime[5] = format_view_1.format(new Date());
        dateTime[6] = format_api.format(new Date());
        dateTime[7] = format_db2.format(new Date());
        return dateTime;
    }

    public static ProgressDialog getDialog(Context context, String title,
                                           String text) {
        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(text);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        return mProgressDialog;
    }

    public static void showAlert(Context activity, final String text){

        new SweetAlertDialog(activity)
                .setTitleText("Detail")
                .setContentText(text)
                .show();

//        LayoutInflater layoutInflater = LayoutInflater.from(activity);
//        View promptView = layoutInflater.inflate(R.layout.dialog_alert, null);
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
//        alertDialogBuilder.setView(promptView);
//        alertDialogBuilder.setTitle("Detail");
//        TextView desc =  promptView.findViewById(R.id.labebAlert);
//        desc.setText(text);
//        alertDialogBuilder.setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//
//                            }
//                        }
//                );
//        AlertDialog alert = alertDialogBuilder.create();
//        alert.show();
    }
    public static String toRibuan(String nominal){
        String hasil = "";
        if(nominal == null|| nominal=="")
        {
            nominal="0";
        }
        int val =  Integer.valueOf(nominal);
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###",dfs);
        hasil = formatter.format(val);
        return hasil;
    }

    public static String toRupiah(String nominal){
        String hasil = "";
        DecimalFormat toRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatAngka = new DecimalFormatSymbols();
        formatAngka.setCurrencySymbol("Rp. ");
        formatAngka.setMonetaryDecimalSeparator(',');
        formatAngka.setGroupingSeparator('.');
        toRupiah.setDecimalFormatSymbols(formatAngka);
        hasil = toRupiah.format(Double.valueOf(nominal));
        return hasil;
    }

    public static String leftRightAlign(String str1, String str2) {
        String ans;
        int n = (31 - (str1.length() + str2.length()));
        ans = str1 + new String(new char[n]).replace("\0", " ") + str2;
        return ans;
    }

    public static String leftRightAlign_forBT(String str1, String str2) {
        String ans;
//        int n = (31 - (str1.length() + str2.length()) + 8 );
        int n = (31 - (str1.length() + str2.length()));
        ans = str1 + new String(new char[n]).replace("\0", " ") + str2;
        return ans;
    }

}

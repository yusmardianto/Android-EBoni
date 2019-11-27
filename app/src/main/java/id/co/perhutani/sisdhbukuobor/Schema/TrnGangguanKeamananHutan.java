package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnGangguanKeamananHutan {
    public static final String TABLE_NAME = "TRN_GANGGUAN_HUTAN";
    public static final String _ID = "ID";
    public static final String TAHUN = "TAHUN";
    public static final String ANAK_PETAK_ID = "ANAKPETAK_ID";
    public static final String NOMOR_HA = "NOMOR_HA";
    public static final String TANGGAL_HA = "TANGGAL_HA";
    public static final String KEJADIAN = "KEJADIAN";
    public static final String KERUGIAN_LUAS = "KERUGIAN_LUAS";
    public static final String KERUGIAN_POHON = "KERUGIAN_POHON";
    public static final String KERUGIAN_KYP = "KERUGIAN_KYP";
    public static final String KERUGIAN_KYB = "KERUGIAN_KYB";
    public static final String KERUGIAN_GETAH = "KERUGIAN_GETAH";
    public static final String NILAI_KERUGIAN = "NILAI_KERUGIAN";
    public static final String KETERANGAN = "KETERANGAN";
    public static final String KET1 = "KET1";
    public static final String KET2 = "KET2";
    public static final String KET3 = "KET3";
    public static final String KET4 = "KET4";
    public static final String KET5 = "KET5";
    public static final String KET6 = "KET6";
    public static final String KET7 = "KET7";
    public static final String KET8 = "KET8";
    public static final String KET9 = "KET9";
    public static final String KET10 = "KET10";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            "IF NOT EXISTS "+ TABLE_NAME +" ( "+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NOMOR_HA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TANGGAL_HA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KEJADIAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KERUGIAN_LUAS + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KERUGIAN_POHON + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KERUGIAN_KYP + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KERUGIAN_KYB + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KERUGIAN_GETAH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NILAI_KERUGIAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KETERANGAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET1 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET2 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET3 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET4 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET5 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET6 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET7 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET8 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET9 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KET10 + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + Type.CREATED_AT_CURRENT_DATE + ", " +
            "" + Type.UPDATED_AT_CURRENT_DATE + " )";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_DELETE_ALL_ROWS = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " <> 0";
}
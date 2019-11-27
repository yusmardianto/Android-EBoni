package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnPemantauanSatwa {
    public static final String TABLE_NAME = "TRN_LAPORAN_SATWA";
    public static final String _ID = "ID";
    public static final String PETAK_ID = "PETAK_ID";
    public static final String ANAK_PETAK_ID = "ANAK_PETAK_ID";
    public static final String JENIS_SATWA = "JENIS_SATWA";
    public static final String JUMLAH_SATWA = "JUMLAH_SATWA";
    public static final String WAKTU_LIHAT =  "WAKTU_LIHAT";
    public static final String CARA_LIHAT = "CARA_LIHAT";
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
            "" + PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_SATWA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JUMLAH_SATWA+ " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + WAKTU_LIHAT + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + CARA_LIHAT + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

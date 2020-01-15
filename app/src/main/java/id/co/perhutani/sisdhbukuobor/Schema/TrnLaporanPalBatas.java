package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnLaporanPalBatas {
    public static final String TABLE_NAME = "TRN_LAPORAN_PAL";
    public static final String _ID = "ID";
    public static final String TANGGAL_PAL = "TANGGAL";
    public static final String JENIS_PAL = "JENIS_PAL";
    public static final String KONDISI_PAL = "KONDISI_PAL";
    public static final String NO_PAL = "NO_PAL";
    public static final String JUMLAH_PAL = "JUMLAH_PAL";
    public static final String KETERANGAN_PAL = "KETERANGAN_PAL";
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
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            "IF NOT EXISTS "+ TABLE_NAME +" ( "+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + TANGGAL_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KONDISI_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NO_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JUMLAH_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KETERANGAN_PAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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
            "" + Type.CREATED_BY + ", " +
            "" + Type.UPDATED_BY + ", " +
            "" + Type.CREATED_AT_CURRENT_DATE + ", " +
            "" + Type.UPDATED_AT_CURRENT_DATE + " )";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_DELETE_ALL_ROWS = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " <> 0";
}

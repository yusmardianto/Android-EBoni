package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnSusunRisalah {
    public static final String TABLE_NAME = "TRN_SUSUN_RISALAH";
    public static final String _ID = "ID";
    public static final String ANAK_PETAK_ID = "ANAK_PETAK_ID";
    public static final String LUAS = "LUAS";
    public static final String JENIS_TANAMAM = "JENIS_TANAMAM";
    public static final String PENINGGI = "PENINGGI";
    public static final String BONITA =  "BONITA";
    public static final String KBD =  "KBD";
    public static final String DKN =  "DKN";
    public static final String N_HA =  "N_HA";
    public static final String STATUS = "STATUS";
    public static final String TAHUN_TANAM = "TAHUN_TANAM";
    public static final String KELAS_HUTAN = "KELAS_HUTAN";
    public static final String TAHUN_TANAM_RATA = "TAHUN_TANAM_RATA";
    public static final String PERMUDAAN = "PERMUDAAN";
    public static final String TANAMAN_SELA = "TANAMAN_SELA";
    public static final String BENTUK_LAPANGAN = "BENTUK_LAPANGAN";
    public static final String RISALAH_TANAH = "RISALAH_TANAH";
    public static final String RISALAH_TEGAKAN_TANAMAN_BAWAH = "RISALAH_TEGAKAN_TANAMAN_BAWAH";
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
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String UPDATED_BY = "UPDATED_BY";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            "IF NOT EXISTS "+ TABLE_NAME +" ( "+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_TANAMAM + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PENINGGI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + BONITA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KBD + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + DKN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + N_HA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + STATUS + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN_TANAM + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN_TANAM_RATA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PERMUDAAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TANAMAN_SELA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + BENTUK_LAPANGAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + RISALAH_TANAH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + RISALAH_TEGAKAN_TANAMAN_BAWAH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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
            "" + Type.CREATED_BY + ", " +
            "" + Type.UPDATED_BY + ", " +
            "" + Type.CREATED_AT_CURRENT_DATE + ", " +
            "" + Type.UPDATED_AT_CURRENT_DATE + " )";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_DELETE_ALL_ROWS = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " <> 0";
}

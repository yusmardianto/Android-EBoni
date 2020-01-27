package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnPerubahanKelas {
    public static final String TABLE_NAME = "TRN_PERUBAHAN_KELAS";
    public static final String _ID = "ID";
    public static final String ANAK_PETAK_ID_PERUBAHAN = "PETAK_ID";
    public static final String TAHUN_PERUBAHAN = "TAHUN";
    public static final String LUAS_PERUBAHAN = "LUAS";
    public static final String JENIS_TANAMAN_PERUBAHAN = "JENIS_TANAMAN";
    public static final String KELAS_HUTAN_PERUBAHAN = "KELAS_HUTAN";
    public static final String LUAS_PERKIRAAN = "LUAS_PERKIRAAN";
    public static final String JENIS_TANAMAN_PERKIRAAN = "JENIS_TANAMAN_PERKIRAAN";
    public static final String KELAS_HUTAN_PERKIRAAN = "KELAS_HUTAN_PERKIRAAN";
    public static final String NO_BAPPKH = "NO_BAPPKH";
    public static final String LUAS_DEFINITIF = "LUAS_DEFINITIF";
    public static final String JENIS_TANAMAN_DEFINITIF = "JENIS_TANAMAN_DEFINITIF";
    public static final String KELAS_HUTAN_DEFINITIF = "KELAS_HUTAN_DEFINITIF";
    public static final String KETERANGAN_PERUBAHAN = "KETERANGAN";
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
            "" + ANAK_PETAK_ID_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_TANAMAN_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_HUTAN_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS_PERKIRAAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_TANAMAN_PERKIRAAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_HUTAN_PERKIRAAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NO_BAPPKH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS_DEFINITIF + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_TANAMAN_DEFINITIF + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_HUTAN_DEFINITIF + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KETERANGAN_PERUBAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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
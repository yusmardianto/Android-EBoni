package id.co.perhutani.sisdhbukuobor.Schema;

public class MstAnakPetakSchema {
    public static final String TABLE_NAME = "MST_ANAK_PETAK";
    public static final String _ID = "ID";
    public static final String BAGIAN_HUTAN = "BAGIAN_HUTAN";
    public static final String KELAS_HUTAN = "KELAS_HUTAN";
    public static final String KELAS_PERUSAHAAN = "KELAS_PERUSAHAAN";
    public static final String JENIS_TANAMAN = "JENIS_TANAMAN";
    public static final String PETAK_ID = "PETAK_ID";
    public static final String PETAK_NAME = "PETAK_NAME";
    public static final String ANAK_PETAK_ID = "ANAK_PETAK_ID";
    public static final String ANAK_PETAK_NAME = "ANAK_PETAK_NAME";
    public static final String TAHUN = "TAHUN";
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
            "" + BAGIAN_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELAS_PERUSAHAAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + JENIS_TANAMAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PETAK_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + ANAK_PETAK_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

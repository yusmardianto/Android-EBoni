package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnRegisterPcp {
    public static final String TABLE_NAME = "TRN_REGISTER_PCP";
    public static final String _ID = "ID";
    public static final String NO_PCP = "NOMOR_PCP";
    public static final String ANAK_PETAK_ID = "ANAK_PETAK_ID";
    public static final String TAHUN_PCP = "TAHUN";
    public static final String LUAS_BAKU = "LUAS_BAKU";
    public static final String LUAS_BLOK =  "LUAS_BLOK";
    public static final String UMUR =  "UMUR";
    public static final String RATARATA_KELILING = "RATARATA_KELILING";
    public static final String BONITA = "BONITA";
    public static final String N_LAPANGAN = "N_LAPANGAN";
    public static final String NORMAL_PCP = "NORMAL_DALAM_PCP";
    public static final String N_MATI = "N_MATI";
    public static final String TAHUN_JARANGAN = "TAHUN_JARANGAN";
    public static final String PENIGGI = "PENINGGI";
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
    public static final String CREATED_BY = "CREATED_BY";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String UPDATED_BY = "UPDATE_BY";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            "IF NOT EXISTS "+ TABLE_NAME +" ( "+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + NO_PCP + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN_PCP + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS_BAKU + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + LUAS_BLOK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + UMUR + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + RATARATA_KELILING + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + BONITA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + N_LAPANGAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NORMAL_PCP + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + N_MATI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + TAHUN_JARANGAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PENIGGI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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
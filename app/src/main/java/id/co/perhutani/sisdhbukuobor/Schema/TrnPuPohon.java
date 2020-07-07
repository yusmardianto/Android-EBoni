package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnPuPohon {

    public static final String TABLE_NAME = "TRN_PU_POHON";
    public static final String _ID = "ID";
    public static final String TS_ID = "TS_ID";
    public static final String PU_POHON_ID = "PU_POHON_ID";
    public static final String NO_POHON = "NO_POHON";
    public static final String KELILING_POHON = "KELILING_POHON";
    public static final String PENINGGI_POHON = "PENINGGI_POHON";
    public static final String KUALITAS_BATANG =  "KUALITAS_BATANG";
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
            "" + TS_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PU_POHON_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + NO_POHON + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KELILING_POHON + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + PENINGGI_POHON + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KUALITAS_BATANG + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

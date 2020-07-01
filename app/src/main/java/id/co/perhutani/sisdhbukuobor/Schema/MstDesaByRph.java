package id.co.perhutani.sisdhbukuobor.Schema;

public class MstDesaByRph {
    public static final String TABLE_NAME = "MST_DESA_BY_RPH";
    public static final String _ID = "ID";
    public static final String DESA_ID = "DESA_ID";
    public static final String DESA_NAME = "DESA_NAME";
    public static final String BH_ID = "BH_ID";
    public static final String RPH_ID = "RPH_ID";
    public static final String KPH_ID = "KPH_ID";
    public static final String BKPH_ID = "BKPH_ID";
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
            "" + DESA_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + DESA_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + BH_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + RPH_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + KPH_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + BKPH_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

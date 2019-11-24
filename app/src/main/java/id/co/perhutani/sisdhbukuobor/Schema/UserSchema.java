package id.co.perhutani.sisdhbukuobor.Schema;

public class UserSchema {
    public static final String TABLE_NAME = "MST_USER";
    public static final String _ID = "ID";
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_NAME_DESCRIPTIONS = "USER_NAME_DESCRIPTIONS";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String USER_IMEI = "USER_IMEI";
    public static final String USER_ANDROID_ID = "USER_ANDROID_ID";
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
            "" + USER_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_NAME_DESCRIPTIONS + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_PASSWORD + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_TOKEN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_IMEI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + USER_ANDROID_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

package id.co.perhutani.sisdhbukuobor.Schema;

public class MstJenisSatwa {
    public static final String TABLE_NAME = "MST_JENIS_SATWA";
    public static final String _ID = "ID";
    public static final String NAME = "NAME";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            "IF NOT EXISTS "+ TABLE_NAME +" ( "+ _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
            "" + Type.CREATED_AT_CURRENT_DATE + ", " +
            "" + Type.UPDATED_AT_CURRENT_DATE + " )";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_DELETE_ALL_ROWS = "DELETE FROM " + TABLE_NAME + " WHERE " + _ID + " <> 0";
}

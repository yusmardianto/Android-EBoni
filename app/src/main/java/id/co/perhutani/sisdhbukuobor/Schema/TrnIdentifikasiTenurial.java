package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnIdentifikasiTenurial
{

        public static final String TABLE_NAME = "TRN_IDENTIFIKASI_KONFLIK_TENURIAL";
        public static final String _ID = "ID";
        public static final String JENIS_PERMASALAHAN = "JENIS_PERMASALAHAN";
        public static final String ANAK_PETAK_ID = "ANAK_PETAK_ID";
        public static final String KELAS_HUTAN_ID = "KELAS_HUTAN_ID";
        public static final String TANGGAL = "TANGGAL";
        public static final String STRATA = "STRATA";
        public static final String LUAS_BAKU = "LUAS_BAKU";
        public static final String LUAS_TENURIAL = "LUAS_TENURIAL";
        public static final String KONDISI_PETAK = "KONDISI_PETAK";
        public static final String AWAL_KONFLIK = "AWAL_KONFLIK";
        public static final String PIHAK_TERLIBAT = "PIHAK_TERLIBAT";
        public static final String STATUS_PENYELESAIAN = "STATUS_PENYELESAIAN";
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
                "" + JENIS_PERMASALAHAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + ANAK_PETAK_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KELAS_HUTAN_ID + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + TANGGAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + STRATA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + LUAS_BAKU + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + LUAS_TENURIAL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KONDISI_PETAK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + AWAL_KONFLIK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PIHAK_TERLIBAT + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + STATUS_PENYELESAIAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

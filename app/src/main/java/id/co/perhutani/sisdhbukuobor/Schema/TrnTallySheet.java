package id.co.perhutani.sisdhbukuobor.Schema;

public class TrnTallySheet {
        public static final String TABLE_NAME = "TRN_TALLY_SHEET";
        public static final String _ID = "ID";
        public static final String ID_TALLYSHEET = "ID_TALLYSHEET";
        public static final String BAGIAN_HUTAN = "BAGIAN_HUTAN";
        public static final String BAGIAN_HUTAN_NAME = "BAGIAN_HUTAN_NAME";
        public static final String KPH = "KPH";
        public static final String KPH_NAME = "KPH_NAME";
        public static final String BKPH = "BKPH";
        public static final String BKPH_NAME = "BKPH_NAME";
        public static final String RPH = "RPH";
        public static final String RPH_NAME = "RPH_NAME";
        public static final String PETAK = "PETAK";
        public static final String PETAK_NAME = "PETAK_NAME";
        public static final String ANAK_PETAK = "ANAK_PETAK";
        public static final String ANAK_PETAK_NAME = "ANAK_PETAK_NAME";
        public static final String DESA = "DESA";
        public static final String DESA_NAME = "DESA_NAME";
        public static final String JARAK_DESA = "JARAK_DESA";
        public static final String KECAMATAN = "KECAMATAN";
        public static final String KECAMATAN_NAME = "KECAMATAN_NAME";
        public static final String KABUPATEN = "KABUPATEN";
        public static final String KABUPATEN_NAME = "KABUPATEN_NAME";
        public static final String TINGGI_PDL = "TINGGI_PDL";
        public static final String IKLIM = "IKLIM";
        public static final String CURAH_HUJAN = "CURAH_HUJAN";
        public static final String KELAS_HUTAN = "KELAS_HUTAN";
        public static final String KELAS_HUTAN_NAME = "KELAS_HUTAN_NAME";
        public static final String FUNGSI_HUTAN = "FUNGSI_HUTAN";
        public static final String FUNGSI_HUTAN_NAME = "FUNGSI_HUTAN_NAME";
        public static final String PENGGUNAAN_HUTAN = "PENGGUNAAN_HUTAN";
        public static final String PENGGUNAAN_HUTAN_NAME = "PENGGUNAAN_HUTAN_NAME";
        public static final String TAHUN_TANAM = "TAHUN_TANAM";
        public static final String BONITA_LALU = "BONITA_LALU";
        public static final String BONITA_BARU = "BONITA_BARU";
        public static final String KBD = "KBD";
        public static final String DKN = "DKN";
        public static final String VOLUME = "VOLUME";
        public static final String INTENSITAS_SAMPLING = "INTENSITAS_SAMPLING";
        public static final String CARA_SAMPLING = "CARA_SAMPLING";
        public static final String TGL_INVENTARISASI = "TGL_INVENTARISASI";
        public static final String PELAKSANA = "PELAKSANA";
        public static final String KEPALA_SEKSI = "KEPALA_SEKSI";
        public static final String NO_RAK = "NO_RAK";
        public static final String NO_LACI = "NO_LACI";
        public static final String TALLYSHEET_PLOT = "TALLYSHEET_PLOT";
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
                "IF NOT EXISTS " + TABLE_NAME + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + ID_TALLYSHEET + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BAGIAN_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BAGIAN_HUTAN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KPH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KPH_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BKPH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BKPH_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + RPH + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + RPH_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PETAK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PETAK_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + ANAK_PETAK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + ANAK_PETAK_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + DESA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + DESA_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + JARAK_DESA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KECAMATAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KECAMATAN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KABUPATEN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KABUPATEN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + TINGGI_PDL + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + IKLIM + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + CURAH_HUJAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KELAS_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KELAS_HUTAN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + FUNGSI_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + FUNGSI_HUTAN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PENGGUNAAN_HUTAN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PENGGUNAAN_HUTAN_NAME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + TAHUN_TANAM + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BONITA_LALU + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + BONITA_BARU + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KBD + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + DKN + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + VOLUME + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + INTENSITAS_SAMPLING + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + CARA_SAMPLING + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + TGL_INVENTARISASI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + PELAKSANA + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + KEPALA_SEKSI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + NO_RAK + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + NO_LACI + " " + Type.VARCHAR_NULLABLE_100 + ", " +
                "" + TALLYSHEET_PLOT + " " + Type.VARCHAR_NULLABLE_100 + ", " +
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

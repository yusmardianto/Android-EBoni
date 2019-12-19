package id.co.perhutani.sisdhbukuobor.ExtentionClass;

/**
 * Created by Adhi Joyo Negoro on 13-11-2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstBentukInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStatusInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "union-sisdh-bukuobor-v1.db";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_CREATE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_CREATE_ENTRIES);
        db.execSQL(TrnInteraksimdh.SQL_CREATE_ENTRIES);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        clear_database(db);
    }


    public void clear_database(SQLiteDatabase db){
        // Drop older table if existed
        db.execSQL(UserSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_DELETE_ENTRIES);
        db.execSQL(TrnInteraksimdh.SQL_DELETE_ENTRIES);
//        db.execSQL(MstTahunJarangan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_DELETE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_DELETE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_DELETE_ENTRIES);
        // Create tables again
        onCreate(db);
    }


    public void altertable() {
        SQLiteDatabase db = getReadableDatabase();
        //create table
        db.execSQL(UserSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_CREATE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_CREATE_ENTRIES);
    }

    public void query_builder() {
        SQLiteDatabase db = getReadableDatabase();
//        db.execSQL("UPDATE MST_BLANDONG  SET KET5='0'");
    }

    public void altertable_update_aplication() {
//        SQLiteDatabase db = getReadableDatabase();

    }

    public long create(String tableName, ContentValues values) {
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        long insertedId = 0;
        try {
            insertedId = db.insertOrThrow(tableName, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("Sql Error", e.getMessage());
        } finally {
            db.endTransaction();
//            db.close();
        }
        return insertedId;
    }

    public int cek_jumlah_data( String tabel) {
        String countQuery = "SELECT * FROM "+tabel+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public boolean deleteAllRow(String Sql) {
        SQLiteDatabase db = getReadableDatabase();
        boolean status = false;
        try {
            db.execSQL(Sql);
            status = true;
        } catch (Exception e) {
            Log.v("Sql Error", e.getMessage());
            status = false;
        } finally {
//            db.close();
        }
        return status;
    }

    public String getDataDetail(String tabel, String kolom_param, String param, String kolom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tabel, null, kolom_param + "=?", new String[]{param}, null, null, null);
        if (cursor.getCount() < 1) // NPK Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
//        String password = cursor.getString(0);
        String password = cursor.getString(cursor.getColumnIndex(kolom));
        cursor.close();
        return password;
    }


    public String getDataProfil(String tabel, String kolom) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tabel, null, null, null, null, null, null);
        if (cursor.getCount() < 1) // NPK Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(kolom));
        cursor.close();
        return password;
    }

    public String getDataDetail_v2(String tabel, String kolom_param, String param, String kolom) {
        String query = "SELECT "+kolom+" FROM " + tabel +
                " WHERE "+kolom_param +"="+param +
                " LIMIT 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        String coba1 = null;
        try {
            if (db == null) {db = getReadableDatabase();}
            cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            coba1 = cursor.getString(0);
        } catch (Exception e) {
            Log.d("TAG", "EXCEPTION! " + e);
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return coba1;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Gangguan Keamanan Hutan
    public void EditDataGangguanHutan(GangguanModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getID_gangguan();
        ContentValues args = new ContentValues();
        args.put(TrnGangguanKeamananHutan.ANAK_PETAK_ID, g.getPetak());
        args.put(TrnGangguanKeamananHutan.JENIS_TANAMAN, g.getJenisTanaman());
        args.put(TrnGangguanKeamananHutan.TANGGAL, g.getTanggal());
        args.put(TrnGangguanKeamananHutan.NOMOR_A, g.getNoA());
        args.put(TrnGangguanKeamananHutan.KEJADIAN, g.getIsi());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_LUAS, g.getLuas());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_POHON, g.getPohon());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_KYP, g.getKyp());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_KYB, g.getKyb());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_GETAH, g.getGetah());
        args.put(TrnGangguanKeamananHutan.NILAI_KERUGIAN, g.getNilai());
        args.put(TrnGangguanKeamananHutan.KETERANGAN, g.getKeterangan());
        args.put(TrnGangguanKeamananHutan.KET1, g.getKet1());
        args.put(TrnGangguanKeamananHutan.KET9, g.getKet9());
        db.update(TrnGangguanKeamananHutan.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataGangguanHutanfroApi(GangguanModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_gangguan();
        ContentValues args = new ContentValues();
        args.put(TrnGangguanKeamananHutan.KET9, b.getKet9());
        args.put(TrnGangguanKeamananHutan.KET10, b.getKet10());
        db.update(TrnGangguanKeamananHutan.TABLE_NAME, args, strFilter, null);
    }

    // Perubahan Kelas
    public void EditDataPerubahanKelas(PerubahankelasModel pk) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + pk.getID_Perubahan();
        ContentValues args = new ContentValues();
        args.put(TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN, pk.getPetakID());
        args.put(TrnPerubahanKelas.TAHUN_PERUBAHAN, pk.getTahun());
        args.put(TrnPerubahanKelas.LUAS_PERUBAHAN, pk.getLuas());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN, pk.getJenisTanaman());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN, pk.getKelasHutan());
        args.put(TrnPerubahanKelas.LUAS_PERKIRAAN, pk.getLuasPerkiraan());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN, pk.getJenisTanamanPerkiraan());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN, pk.getKelasHutanPerkiraan());
        args.put(TrnPerubahanKelas.NO_BAPPKH, pk.getNoBappkh());
        args.put(TrnPerubahanKelas.LUAS_DEFINITIF, pk.getLuasDefinitif());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF, pk.getJenisTanamanDefinitif());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF, pk.getKelasHutanDefinitif());
        args.put(TrnPerubahanKelas.KETERANGAN_PERUBAHAN, pk.getKeteranganPerubahan());
        db.update(TrnPerubahanKelas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPerubahanKelasroApi(PerubahankelasModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_Perubahan();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.KET9, b.getKet9());
        args.put(TrnRegisterPcp.KET10, b.getKet10());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }


    // Interaksi MDH
    public void EditDataInteraksiMDH(InteraksimdhModel imdh) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + imdh.getID_IMDH();
        ContentValues args = new ContentValues();
        args.put(TrnInteraksimdh.ANAK_PETAK_ID, imdh.getAnakpetakid());
        args.put(TrnInteraksimdh.TAHUN, imdh.getTahun());
        args.put(TrnInteraksimdh.NAMA_DESA, imdh.getDesaid());
        args.put(TrnInteraksimdh.BENTUK_INTERAKSI, imdh.getBentukinteraksi());
        args.put(TrnInteraksimdh.STATUS, imdh.getStatus());
        args.put(TrnInteraksimdh.KETERANGAN, imdh.getKeterangan());
        db.update(TrnInteraksimdh.TABLE_NAME, args, strFilter, null);
    }

    // Pemantauan Satwa
    public void EditDataPemantauanSatwa(PemantauansatwaModel ps) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + ps.getID_Pemantauan();
        ContentValues args = new ContentValues();
        args.put(TrnPemantauanSatwa.ANAK_PETAK_ID, ps.getAnakPetakId());
        args.put(TrnPemantauanSatwa.JENIS_SATWA, ps.getJenis());
        args.put(TrnPemantauanSatwa.JUMLAH_SATWA, ps.getJumlah());
        args.put(TrnPemantauanSatwa.WAKTU_LIHAT, ps.getWaktulihat());
        args.put(TrnPemantauanSatwa.CARA_LIHAT, ps.getCaralihat());
        args.put(TrnPemantauanSatwa.TANGGAL_PEMANTAUAN, ps.getTanggal());
        args.put(TrnPemantauanSatwa.KETERANGAN, ps.getKeteranganSatwa());
        db.update(TrnPemantauanSatwa.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPemantauanSatwafroApi(PemantauansatwaModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_Pemantauan();
        ContentValues args = new ContentValues();
        args.put(TrnPemantauanSatwa.KET9, b.getKet9());
        args.put(TrnPemantauanSatwa.KET10, b.getKet10());
        db.update(TrnPemantauanSatwa.TABLE_NAME, args, strFilter, null);
    }

    // Laporan Pal Batas
    public void EditDataLaporanPalBatas(PelaporanpalbatasModel lpb) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + lpb.getID_Laporan();
        ContentValues args = new ContentValues();
        args.put(TrnLaporanPalBatas.TANGGAL_PAL, lpb.getTanggalPal());
        args.put(TrnLaporanPalBatas.JENIS_PAL, lpb.getJenisPal());
        args.put(TrnLaporanPalBatas.KONDISI_PAL, lpb.getKondisiPal());
        args.put(TrnLaporanPalBatas.NO_PAL, lpb.getNomerPal());
        args.put(TrnLaporanPalBatas.JUMLAH_PAL, lpb.getJumlahPal());
        args.put(TrnLaporanPalBatas.KETERANGAN_PAL, lpb.getKeteranganPal());
        db.update(TrnLaporanPalBatas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataLaporanPalfroApi(PelaporanpalbatasModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_Laporan();
        ContentValues args = new ContentValues();
        args.put(TrnLaporanPalBatas.KET9, b.getKet9());
        args.put(TrnLaporanPalBatas.KET10, b.getKet10());
        db.update(TrnLaporanPalBatas.TABLE_NAME, args, strFilter, null);
    }

    // Register PCP
    public void EditDataRegisterPCP(RegisterpcpModel rp) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + rp.getID_Registerpcp();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.NO_PCP, rp.getNoPcp());
        args.put(TrnRegisterPcp.ANAK_PETAK_ID, rp.getAnakPetakId());
        args.put(TrnRegisterPcp.TAHUN_PCP, rp.getTahun());
        args.put(TrnRegisterPcp.UMUR, rp.getUmur());
        args.put(TrnRegisterPcp.LUAS_BAKU, rp.getLuasBaku());
        args.put(TrnRegisterPcp.LUAS_BLOK, rp.getLuasBlok());
        args.put(TrnRegisterPcp.RATARATA_KELILING, rp.getRataKeliling());
        args.put(TrnRegisterPcp.BONITA, rp.getBonita());
        args.put(TrnRegisterPcp.N_LAPANGAN, rp.getNLapangan());
        args.put(TrnRegisterPcp.NORMAL_PCP, rp.getNormalPcp());
        args.put(TrnRegisterPcp.N_MATI, rp.getNMati());
        args.put(TrnRegisterPcp.TAHUN_JARANGAN, rp.getTahunJarangan());
        args.put(TrnRegisterPcp.PENIGGI, rp.getPeninggi());
        args.put(TrnRegisterPcp.KETERANGAN, rp.getKeteranganPcp());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataRegisterPCPfroApi(RegisterpcpModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_Registerpcp();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.KET9, b.getKet9());
        args.put(TrnRegisterPcp.KET10, b.getKet10());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }

    // Identifikasi Tenurial
    public void EditDataIdentifikasiTenurial(IdentifikasiTenurialModel rp) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + rp.getID_Tenurial();
        ContentValues args = new ContentValues();
        args.put(TrnIdentifikasiTenurial.JENIS_PERMASALAHAN, rp.getJenisPermasalahan());
        args.put(TrnIdentifikasiTenurial.TANGGAL, rp.getTanggal());
        args.put(TrnIdentifikasiTenurial.ANAK_PETAK_ID, rp.getAnakPetak());
        args.put(TrnIdentifikasiTenurial.STRATA, rp.getStrata());
        args.put(TrnIdentifikasiTenurial.KELAS_HUTAN_ID, rp.getKelasHutan());
        args.put(TrnIdentifikasiTenurial.LUAS_BAKU, rp.getLuasBaku());
        args.put(TrnIdentifikasiTenurial.LUAS_TENURIAL, rp.getLuasTenurial());
        args.put(TrnIdentifikasiTenurial.KONDISI_PETAK, rp.getKondisiPetakSaatIdentifikasi());
        args.put(TrnIdentifikasiTenurial.AWAL_KONFLIK, rp.getAwalKonflik());
        args.put(TrnIdentifikasiTenurial.PIHAK_TERLIBAT, rp.getPihakTerlibat());
        args.put(TrnIdentifikasiTenurial.STATUS_PENYELESAIAN, rp.getStatusPenyelesaian());
        db.update(TrnIdentifikasiTenurial.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataIdentifikasiTenurialfroApi(IdentifikasiTenurialModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_Tenurial();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.KET9, b.getKet9());
        args.put(TrnRegisterPcp.KET10, b.getKet10());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }

    // Get Data
    public List<String> getJenisPal() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisPalSchema.JENIS_PAL_NAME + " FROM " + MstJenisPalSchema.TABLE_NAME +
                " a ORDER BY a."+MstJenisPalSchema.JENIS_PAL_NAME+", a."+MstJenisPalSchema.JENIS_PAL_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis PAL -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getJenisSatwa() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisSatwa.JENIS_SATWA_NAME+ " FROM " + MstJenisSatwa.TABLE_NAME +
                " a ORDER BY a."+MstJenisSatwa.JENIS_SATWA_NAME+", a."+MstJenisSatwa.JENIS_SATWA_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis Satwa -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getCaraMelihat() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisTemuan.JENIS_TEMUAN_NAME+ " FROM " + MstJenisTemuan.TABLE_NAME +
                " a ORDER BY a."+MstJenisTemuan.JENIS_TEMUAN_NAME+", a."+MstJenisTemuan.JENIS_TEMUAN_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis Temuan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getJenisTanaman() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisTanamanSchema.JENIS_TANAMAN_NAME + " FROM " + MstJenisTanamanSchema.TABLE_NAME +
                " a ORDER BY a." + MstJenisTanamanSchema.JENIS_TANAMAN_NAME + ", a." + MstJenisTanamanSchema.JENIS_TANAMAN_NAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis Tanaman -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getKelasHutan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstKelasHutanSchema.KELAS_HUTAN_NAME + " FROM " + MstKelasHutanSchema.TABLE_NAME +
                " a ORDER BY a." + MstKelasHutanSchema.KELAS_HUTAN_NAME + ", a." + MstKelasHutanSchema.KELAS_HUTAN_NAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Kelas Hutan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getJenisGangguan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME + " FROM " + MstJenisGangguanHutanSchema.TABLE_NAME +
                " a ORDER BY a."+MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME+", a."+MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis Gangguan-");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getAnakPetak() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstAnakPetakSchema.ANAK_PETAK_NAME + " FROM " + MstAnakPetakSchema.TABLE_NAME +
                " a ORDER BY a."+MstAnakPetakSchema.PETAK_NAME+", a."+MstAnakPetakSchema.ANAK_PETAK_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Anak Petak -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public void delete_one_date(String nama_tabel,String kolom_id, String id) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL( "DELETE FROM " + nama_tabel + " WHERE " + kolom_id + " = "+id);
    }

    public List<String> getJenisPermasalahan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME + " FROM " + MstJenisPermasalahanSchema.TABLE_NAME +
                " a ORDER BY a."+MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME+", a."+MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Jenis Permasalahan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getNamaDesa() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstDesaSchema.NAMA_DESA + " FROM " + MstDesaSchema.TABLE_NAME +
                " a ORDER BY a."+MstDesaSchema.NAMA_DESA+", a."+MstDesaSchema.ID_DESA+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Nama Desa -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getBentukInteraksi() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstBentukInteraksiSchema.NAMA_INTERAKSI + " FROM " + MstBentukInteraksiSchema.TABLE_NAME +
                " a ORDER BY a."+MstBentukInteraksiSchema.NAMA_INTERAKSI+", a."+MstBentukInteraksiSchema.ID_INTERAKSI+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Bentuk Interaksi -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getStatusInteraksi() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstStatusInteraksiSchema.NAMA_STATUS + " FROM " + MstStatusInteraksiSchema.TABLE_NAME +
                " a ORDER BY a."+MstStatusInteraksiSchema.NAMA_STATUS+", a."+MstStatusInteraksiSchema.ID_STATUS+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Status Interaksi -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

}
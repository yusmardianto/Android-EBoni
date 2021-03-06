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

import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon.PuPohonModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.TallySheetModel;
import id.co.perhutani.sisdhbukuobor.Model.GangguanModel;
import id.co.perhutani.sisdhbukuobor.Model.IdentifikasiTenurialModel;
import id.co.perhutani.sisdhbukuobor.Model.InteraksimdhModel;
import id.co.perhutani.sisdhbukuobor.Model.PelaporanpalbatasModel;
import id.co.perhutani.sisdhbukuobor.Model.PemantauansatwaModel;
import id.co.perhutani.sisdhbukuobor.Model.PengelolaanHutanModel;
import id.co.perhutani.sisdhbukuobor.Model.PerubahankelasModel;
import id.co.perhutani.sisdhbukuobor.Model.RegisterpcpModel;
import id.co.perhutani.sisdhbukuobor.Schema.MstAnakPetakSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstBagianHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstBentukInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaByRph;
import id.co.perhutani.sisdhbukuobor.Schema.MstDesaSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstFungsiHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisGangguanHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisPermasalahanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTanamanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstJenisTemuan;
import id.co.perhutani.sisdhbukuobor.Schema.MstKelasHutanSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKondisiPalSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstKonversiKeliling;
import id.co.perhutani.sisdhbukuobor.Schema.MstPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstPenggunaanHutan;
import id.co.perhutani.sisdhbukuobor.Schema.MstPihakTerlibatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStatusInteraksiSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstStrataSchema;
import id.co.perhutani.sisdhbukuobor.Schema.MstSubPekerjaan;
import id.co.perhutani.sisdhbukuobor.Schema.MstWaktuLihatSchema;
import id.co.perhutani.sisdhbukuobor.Schema.TrnGangguanKeamananHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnIdentifikasiTenurial;
import id.co.perhutani.sisdhbukuobor.Schema.TrnInteraksimdh;
import id.co.perhutani.sisdhbukuobor.Schema.TrnLaporanPalBatas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnMonitoringKlsHtn;
import id.co.perhutani.sisdhbukuobor.Schema.TrnOverSpin;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPasangBatasPersemaian;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPemantauanSatwa;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPersiapanSapra;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPembuatanBedengSapih;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPengelolaanHutan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPersiapanLahan;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPerubahanKelas;
import id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon;
import id.co.perhutani.sisdhbukuobor.Schema.TrnRegisterPcp;
import id.co.perhutani.sisdhbukuobor.Schema.TrnSusunRisalah;
import id.co.perhutani.sisdhbukuobor.Schema.TrnTallySheet;
import id.co.perhutani.sisdhbukuobor.Schema.TrnWorkOrder;
import id.co.perhutani.sisdhbukuobor.Schema.UserSchema;

import static id.co.perhutani.sisdhbukuobor.Schema.TrnPuPohon.TS_ID;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "union-sisdh-bukuobor-v1.db";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaByRph.SQL_CREATE_ENTRIES);
        db.execSQL(MstBagianHutan.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_CREATE_ENTRIES);
        db.execSQL(MstWaktuLihatSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKondisiPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstFungsiHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPenggunaanHutan.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStrataSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPihakTerlibatSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPekerjaan.SQL_CREATE_ENTRIES);
        db.execSQL(MstSubPekerjaan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_CREATE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_CREATE_ENTRIES);
        db.execSQL(TrnInteraksimdh.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPengelolaanHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPasangBatasPersemaian.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPersiapanSapra.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPersiapanLahan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPembuatanBedengSapih.SQL_CREATE_ENTRIES);
        db.execSQL(TrnWorkOrder.SQL_CREATE_ENTRIES);
        db.execSQL(TrnOverSpin.SQL_CREATE_ENTRIES);
        db.execSQL(TrnSusunRisalah.SQL_CREATE_ENTRIES);
        db.execSQL(TrnMonitoringKlsHtn.SQL_CREATE_ENTRIES);
        db.execSQL(TrnTallySheet.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPuPohon.SQL_CREATE_ENTRIES);
        db.execSQL(MstKonversiKeliling.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        clear_database(db);
    }


    public void clear_database(SQLiteDatabase db){
        db.execSQL(UserSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstDesaByRph.SQL_DELETE_ENTRIES);
        db.execSQL(MstBagianHutan.SQL_DELETE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstFungsiHutanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstPenggunaanHutan.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_DELETE_ENTRIES);
        db.execSQL(MstWaktuLihatSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstKondisiPalSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstStrataSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstPihakTerlibatSchema.SQL_DELETE_ENTRIES);
        db.execSQL(MstPekerjaan.SQL_DELETE_ENTRIES);
        db.execSQL(MstSubPekerjaan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnInteraksimdh.SQL_DELETE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_DELETE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_DELETE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPengelolaanHutan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPasangBatasPersemaian.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPersiapanSapra.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPersiapanLahan.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPembuatanBedengSapih.SQL_DELETE_ENTRIES);
        db.execSQL(TrnOverSpin.SQL_DELETE_ENTRIES);
        db.execSQL(TrnWorkOrder.SQL_DELETE_ENTRIES);
        db.execSQL(TrnSusunRisalah.SQL_DELETE_ENTRIES);
        db.execSQL(TrnMonitoringKlsHtn.SQL_DELETE_ENTRIES);
        db.execSQL(TrnTallySheet.SQL_DELETE_ENTRIES);
        db.execSQL(TrnPuPohon.SQL_DELETE_ENTRIES);
        db.execSQL(MstKonversiKeliling.SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    public void altertable() {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(UserSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstAnakPetakSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaByRph.SQL_CREATE_ENTRIES);
        db.execSQL(MstBagianHutan.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTanamanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPermasalahanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisGangguanHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisTemuan.SQL_CREATE_ENTRIES);
        db.execSQL(MstWaktuLihatSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstJenisPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKondisiPalSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstKelasHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstFungsiHutanSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPenggunaanHutan.SQL_CREATE_ENTRIES);
        db.execSQL(MstDesaSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstBentukInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStatusInteraksiSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstStrataSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPihakTerlibatSchema.SQL_CREATE_ENTRIES);
        db.execSQL(MstPekerjaan.SQL_CREATE_ENTRIES);
        db.execSQL(MstSubPekerjaan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnGangguanKeamananHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPerubahanKelas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnLaporanPalBatas.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPemantauanSatwa.SQL_CREATE_ENTRIES);
        db.execSQL(TrnRegisterPcp.SQL_CREATE_ENTRIES);
        db.execSQL(TrnIdentifikasiTenurial.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPengelolaanHutan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPasangBatasPersemaian.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPersiapanSapra.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPersiapanLahan.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPembuatanBedengSapih.SQL_CREATE_ENTRIES);
        db.execSQL(TrnOverSpin.SQL_CREATE_ENTRIES);
        db.execSQL(TrnWorkOrder.SQL_CREATE_ENTRIES);
        db.execSQL(TrnSusunRisalah.SQL_CREATE_ENTRIES);
        db.execSQL(TrnMonitoringKlsHtn.SQL_CREATE_ENTRIES);
        db.execSQL(TrnTallySheet.SQL_CREATE_ENTRIES);
        db.execSQL(TrnPuPohon.SQL_CREATE_ENTRIES);
        db.execSQL(MstKonversiKeliling.SQL_CREATE_ENTRIES);
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

    public void insert_manual(String ts_id) {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO TRN_PU_POHON (TS_ID) values (\"" + ts_id + "\")");
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

    public void EditDataGangguanHutan(GangguanModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getID_gangguan();
        ContentValues args = new ContentValues();
        args.put(TrnGangguanKeamananHutan.TANGGAL_KEJADIAN, g.getTgl_Kejadian());
        args.put(TrnGangguanKeamananHutan.ANAK_PETAK_ID, g.getPetak());
        args.put(TrnGangguanKeamananHutan.KET1, g.getKet1());
        args.put(TrnGangguanKeamananHutan.JENIS_TANAMAN, g.getJenisTanaman());
        args.put(TrnGangguanKeamananHutan.KET2, g.getKet2());
        args.put(TrnGangguanKeamananHutan.TANGGAL, g.getTanggal());
        args.put(TrnGangguanKeamananHutan.NOMOR_A, g.getNoA());
        args.put(TrnGangguanKeamananHutan.KEJADIAN, g.getIsi());
        args.put(TrnGangguanKeamananHutan.KET3, g.getKet3());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_LUAS, g.getLuas());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_POHON, g.getPohon());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_KYP, g.getKyp());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_KYB, g.getKyb());
        args.put(TrnGangguanKeamananHutan.KERUGIAN_GETAH, g.getGetah());
        args.put(TrnGangguanKeamananHutan.NILAI_KERUGIAN, g.getNilai());
        args.put(TrnGangguanKeamananHutan.KETERANGAN, g.getKeterangan());
        args.put(TrnGangguanKeamananHutan.HEXA, g.getKet11());
        args.put(TrnGangguanKeamananHutan.KET9, g.getKet9());
        db.update(TrnGangguanKeamananHutan.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataTallySheetForAPI(TallySheetModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getId();
        ContentValues args = new ContentValues();
        args.put(TrnTallySheet.KET9, g.getTs_ket9());
        args.put(TrnTallySheet.KET10, g.getTs_ket10());
        db.update(TrnTallySheet.TABLE_NAME, args, strFilter, null);
    }
    public void EditDataTallySheet(TallySheetModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID_TALLYSHEET=" + g.getTs_id();
        ContentValues args = new ContentValues();
        args.put(TrnTallySheet.LATTITUDE, g.getTs_lattitude());
        args.put(TrnTallySheet.LONGITUDE, g.getTs_longitude());
        args.put(TrnTallySheet.CEK_LATLNG, g.getCek_latlng());
        args.put(TrnTallySheet.CEK_PHOTO, g.getCek_photo());
        args.put(TrnTallySheet.PHOTO_POHON, g.getTs_photopohon());
        args.put(TrnTallySheet.TGL_INVENTARISASI, g.getTglinven());
        db.update(TrnTallySheet.TABLE_NAME, args, strFilter, null);
    }
    public void EditHeaderTallySheet(TallySheetModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID_TALLYSHEET=" + g.getTs_id();
        ContentValues args = new ContentValues();
        args.put(TrnTallySheet.ANAK_PETAK_NAME, g.getTs_anak_petak());
        args.put(TrnTallySheet.KODE_PU, g.getTs_kode_pu());
        args.put(TrnTallySheet.LUAS_PU, g.getTs_luaspu());
        args.put(TrnTallySheet.KET9, g.getTs_ket9());
        db.update(TrnTallySheet.TABLE_NAME, args, strFilter, null);
    }

    public void EditHeaderTallySheetForAPI(TallySheetModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getId();
        ContentValues args = new ContentValues();
        args.put(TrnTallySheet.KET9, g.getTs_ket9());
        args.put(TrnTallySheet.KET10, g.getTs_ket10());
        db.update(TrnTallySheet.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPengukuranPohon(PuPohonModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getId();
        ContentValues args = new ContentValues();
        args.put(TS_ID, g.getTs_id());
        args.put(TrnPuPohon.NO_POHON, g.getNo_pohon());
        args.put(TrnPuPohon.KELILING_POHON, g.getKeliling_pohon());
        args.put(TrnPuPohon.PENINGGI_POHON, g.getPeninggi_pohon());
        args.put(TrnPuPohon.BIDANG_DASAR, g.getBidang_dasar());
        args.put(TrnPuPohon.KET9, g.getKet9());
        db.update(TrnPuPohon.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataGangguanHutanfroApi(GangguanModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getID_gangguan();
        ContentValues args = new ContentValues();
        args.put(TrnGangguanKeamananHutan.KET9, b.getKet9());
        args.put(TrnGangguanKeamananHutan.KET10, b.getKet10());
        db.update(TrnGangguanKeamananHutan.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPerubahanKelas(PerubahankelasModel pk) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + pk.getID_Perubahan();
        ContentValues args = new ContentValues();
        args.put(TrnPerubahanKelas.KET1, pk.getKet1());
        args.put(TrnPerubahanKelas.ANAK_PETAK_ID_PERUBAHAN, pk.getPetakID());
        args.put(TrnPerubahanKelas.TAHUN_PERUBAHAN, pk.getTahun());
        args.put(TrnPerubahanKelas.LUAS_PERUBAHAN, pk.getLuas());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_PERUBAHAN, pk.getJenisTanaman());
        args.put(TrnPerubahanKelas.KET2, pk.getKet2());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_PERUBAHAN, pk.getKelasHutan());
        args.put(TrnPerubahanKelas.KET3, pk.getKet3());
        args.put(TrnPerubahanKelas.LUAS_PERKIRAAN, pk.getLuasPerkiraan());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_PERKIRAAN, pk.getJenisTanamanPerkiraan());
        args.put(TrnPerubahanKelas.KET4, pk.getKet4());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_PERKIRAAN, pk.getKelasHutanPerkiraan());
        args.put(TrnPerubahanKelas.KET5, pk.getKet5());
        args.put(TrnPerubahanKelas.NO_BAPPKH, pk.getNoBappkh());
        args.put(TrnPerubahanKelas.LUAS_DEFINITIF, pk.getLuasDefinitif());
        args.put(TrnPerubahanKelas.JENIS_TANAMAN_DEFINITIF, pk.getJenisTanamanDefinitif());
        args.put(TrnPerubahanKelas.KET6, pk.getKet6());
        args.put(TrnPerubahanKelas.KELAS_HUTAN_DEFINITIF, pk.getKelasHutanDefinitif());
        args.put(TrnPerubahanKelas.KET7, pk.getKet7());
        args.put(TrnPerubahanKelas.KETERANGAN_PERUBAHAN, pk.getKeteranganPerubahan());
        args.put(TrnPerubahanKelas.KET9, pk.getKet9());
        args.put(TrnPerubahanKelas.HEXA, pk.getKet11());
        db.update(TrnPerubahanKelas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPerubahanKelasroApi(PerubahankelasModel c) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + c.getID_Perubahan();
        ContentValues args = new ContentValues();
        args.put(TrnPerubahanKelas.KET9, c.getKet9());
        args.put(TrnPerubahanKelas.KET10, c.getKet10());
        db.update(TrnPerubahanKelas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataInteraksiMDH(InteraksimdhModel imdh) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + imdh.getID_IMDH();
        ContentValues args = new ContentValues();
        args.put(TrnInteraksimdh.ANAK_PETAK_ID, imdh.getAnakpetakid());
        args.put(TrnInteraksimdh.KET1, imdh.getKet1());
        args.put(TrnInteraksimdh.TAHUN, imdh.getTahun());
        args.put(TrnInteraksimdh.KET2, imdh.getKet2());
        args.put(TrnInteraksimdh.NAMA_DESA, imdh.getDesaid());
        args.put(TrnInteraksimdh.KET3, imdh.getKet3());
        args.put(TrnInteraksimdh.BENTUK_INTERAKSI, imdh.getBentukinteraksi());
        args.put(TrnInteraksimdh.KET4, imdh.getKet4());
        args.put(TrnInteraksimdh.STATUS, imdh.getStatus());
        args.put(TrnInteraksimdh.KET5, imdh.getKet5());
        args.put(TrnInteraksimdh.KETERANGAN, imdh.getKeterangan());
        args.put(TrnInteraksimdh.KET9, imdh.getKet9());
        args.put(TrnInteraksimdh.HEXA, imdh.getKet11());
        db.update(TrnInteraksimdh.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataInteraksimdhfroApi(InteraksimdhModel i) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + i.getID_IMDH();
        ContentValues args = new ContentValues();
        args.put(TrnInteraksimdh.KET9, i.getKet9());
        args.put(TrnInteraksimdh.KET10, i.getKet10());
        db.update(TrnInteraksimdh.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPemantauanSatwa(PemantauansatwaModel ps) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + ps.getID_Pemantauan();
        ContentValues args = new ContentValues();
        args.put(TrnPemantauanSatwa.ANAK_PETAK_ID, ps.getAnakPetakId());
        args.put(TrnPemantauanSatwa.KET1, ps.getKet1());
        args.put(TrnPemantauanSatwa.JENIS_SATWA, ps.getJenis());
        args.put(TrnPemantauanSatwa.KET2, ps.getKet2());
        args.put(TrnPemantauanSatwa.JUMLAH_SATWA, ps.getJumlah());
        args.put(TrnPemantauanSatwa.WAKTU_LIHAT, ps.getWaktulihat());
        args.put(TrnPemantauanSatwa.KET3, ps.getKet3());
        args.put(TrnPemantauanSatwa.CARA_LIHAT, ps.getCaralihat());
        args.put(TrnPemantauanSatwa.KET4, ps.getKet4());
        args.put(TrnPemantauanSatwa.TANGGAL_PEMANTAUAN, ps.getTanggal());
        args.put(TrnPemantauanSatwa.KETERANGAN, ps.getKeteranganSatwa());
        args.put(TrnPemantauanSatwa.KET9, ps.getKet9());
        args.put(TrnPemantauanSatwa.HEXA, ps.getKet11());
        db.update(TrnPemantauanSatwa.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPemantauanSatwafroApi(PemantauansatwaModel e) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + e.getID_Pemantauan();
        ContentValues args = new ContentValues();
        args.put(TrnPemantauanSatwa.KET9, e.getKet9());
        args.put(TrnPemantauanSatwa.KET10, e.getKet10());
        db.update(TrnPemantauanSatwa.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataLaporanPalBatas(PelaporanpalbatasModel lpb) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + lpb.getID_Laporan();
        ContentValues args = new ContentValues();
        args.put(TrnLaporanPalBatas.BAGIAN_HUTAN, lpb.getBagianHutanPal());
        args.put(TrnLaporanPalBatas.KET1, lpb.getKet1());
        args.put(TrnLaporanPalBatas.TANGGAL_PAL, lpb.getTanggalPal());
        args.put(TrnLaporanPalBatas.JENIS_PAL, lpb.getJenisPal());
        args.put(TrnLaporanPalBatas.KET2, lpb.getKet2());
        args.put(TrnLaporanPalBatas.KONDISI_PAL, lpb.getKondisiPal());
        args.put(TrnLaporanPalBatas.KET3, lpb.getKet3());
        args.put(TrnLaporanPalBatas.NO_PAL, lpb.getNomerPal());
        args.put(TrnLaporanPalBatas.JUMLAH_PAL, lpb.getJumlahPal());
        args.put(TrnLaporanPalBatas.KETERANGAN_PAL, lpb.getKeteranganPal());
        args.put(TrnLaporanPalBatas.KET9, lpb.getKet9());
        db.update(TrnLaporanPalBatas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataLaporanPalfroApi(PelaporanpalbatasModel f) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + f.getID_Laporan();
        ContentValues args = new ContentValues();
        args.put(TrnLaporanPalBatas.KET9, f.getKet9());
        args.put(TrnLaporanPalBatas.KET10, f.getKet10());
        db.update(TrnLaporanPalBatas.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataRegisterPCP(RegisterpcpModel rp) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + rp.getID_Registerpcp();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.NO_PCP, rp.getNoPcp());
        args.put(TrnRegisterPcp.ANAK_PETAK_ID, rp.getAnakPetakId());
        args.put(TrnRegisterPcp.KET1, rp.getKet1());
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
        args.put(TrnRegisterPcp.KET9, rp.getKet9());
        args.put(TrnRegisterPcp.HEXA, rp.getKet11());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataRegisterPCPfroApi(RegisterpcpModel g) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + g.getID_Registerpcp();
        ContentValues args = new ContentValues();
        args.put(TrnRegisterPcp.KET9, g.getKet9());
        args.put(TrnRegisterPcp.KET10, g.getKet10());
        db.update(TrnRegisterPcp.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataIdentifikasiTenurial(IdentifikasiTenurialModel it) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + it.getID_Tenurial();
        ContentValues args = new ContentValues();
        args.put(TrnIdentifikasiTenurial.JENIS_PERMASALAHAN, it.getJenisPermasalahan());
        args.put(TrnIdentifikasiTenurial.KET1, it.getKet1());
        args.put(TrnIdentifikasiTenurial.ANAK_PETAK_ID, it.getAnakPetak());
        args.put(TrnIdentifikasiTenurial.KET2, it.getKet2());
        args.put(TrnIdentifikasiTenurial.KELAS_HUTAN_ID, it.getKelasHutan());
        args.put(TrnIdentifikasiTenurial.KET3, it.getKet3());
        args.put(TrnIdentifikasiTenurial.TANGGAL, it.getTanggal());
        args.put(TrnIdentifikasiTenurial.STRATA, it.getStrata());
        args.put(TrnIdentifikasiTenurial.KET4, it.getKet4());
        args.put(TrnIdentifikasiTenurial.LUAS_BAKU, it.getLuasBaku());
        args.put(TrnIdentifikasiTenurial.LUAS_TENURIAL, it.getLuasTenurial());
        args.put(TrnIdentifikasiTenurial.KONDISI_PETAK, it.getKondisiPetakSaatIdentifikasi());
        args.put(TrnIdentifikasiTenurial.AWAL_KONFLIK, it.getAwalKonflik());
        args.put(TrnIdentifikasiTenurial.PIHAK_TERLIBAT, it.getPihakTerlibat());
        args.put(TrnIdentifikasiTenurial.KET5, it.getKet5());
        args.put(TrnIdentifikasiTenurial.STATUS_PENYELESAIAN, it.getStatusPenyelesaian());
        args.put(TrnIdentifikasiTenurial.KET9, it.getKet9());
        args.put(TrnIdentifikasiTenurial.HEXA, it.getKet11());
        db.update(TrnIdentifikasiTenurial.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataIdentifikasiTenurialfroApi(IdentifikasiTenurialModel h) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + h.getID_Tenurial();
        ContentValues args = new ContentValues();
        args.put(TrnIdentifikasiTenurial.KET9, h.getKet9());
        args.put(TrnIdentifikasiTenurial.KET10, h.getKet10());
        db.update(TrnIdentifikasiTenurial.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPengelolaanHutan(PengelolaanHutanModel ph) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + ph.getID_Pekerjaan();
        ContentValues args = new ContentValues();
        args.put(TrnPengelolaanHutan.KET1, ph.getKet1());
        args.put(TrnPengelolaanHutan.KET2, ph.getKet2());
        args.put(TrnPengelolaanHutan.KET3, ph.getKet3());
        args.put(TrnPengelolaanHutan.TANGGAL, ph.getTanggal());
        args.put(TrnPengelolaanHutan.RENCANA, ph.getRencana());
        args.put(TrnPengelolaanHutan.REALISASI, ph.getRealisasi());
        args.put(TrnPengelolaanHutan.STATUS, ph.getStatus());
        args.put(TrnPengelolaanHutan.KETERANGAN, ph.getKeterangan());
        args.put(TrnPengelolaanHutan.KET9, ph.getKet9());
        db.update(TrnPengelolaanHutan.TABLE_NAME, args, strFilter, null);
    }

    public void EditDataPengukuranPohonforApi(PuPohonModel b) {
        SQLiteDatabase db = getReadableDatabase();
        String strFilter = "ID=" + b.getPu_pohon_id();
        ContentValues args = new ContentValues();
        args.put(TrnPuPohon.KET9, b.getKet9());
        args.put(TrnPuPohon.KET10, b.getKet10());
        db.update(TrnPuPohon.TABLE_NAME, args, strFilter, null);
    }

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

    public List<String> getKondisiPal() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstKondisiPalSchema.KONDISI_PAL_NAME + " FROM " + MstKondisiPalSchema.TABLE_NAME +
                " a ORDER BY a."+MstKondisiPalSchema.KONDISI_PAL_NAME+", a."+MstKondisiPalSchema.KONDISI_PAL_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Kondisi PAL -");
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

    public List<String> getWaktuLihat() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT " + MstWaktuLihatSchema.WAKTU_LIHAT_NAME+ " FROM " + MstWaktuLihatSchema.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Waktu Lihat -");
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

    public List<String> getFungsiHutan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstFungsiHutanSchema.FUNGSI_HUTAN_NAME + " FROM " + MstFungsiHutanSchema.TABLE_NAME +
                " a ORDER BY a." + MstFungsiHutanSchema.FUNGSI_HUTAN_NAME + ", a." + MstFungsiHutanSchema.FUNGSI_HUTAN_NAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Fungsi Hutan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getPenggunaanHutan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME + " FROM " + MstPenggunaanHutan.TABLE_NAME +
                " a ORDER BY a." + MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME + ", a." + MstPenggunaanHutan.PENGGUNAAN_HUTAN_NAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Penggunaan Hutan -");
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
                " a ORDER BY a."+MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME+", a."+MstJenisGangguanHutanSchema.JENIS_GANGGUAN_HUTAN_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Kejadian -");
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
                " a ORDER BY a."+MstAnakPetakSchema.PETAK_NAME+" ASC";
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

    public List<String> getBagianHutan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstBagianHutan.BAGIAN_HUTAN_NAME + " FROM " + MstBagianHutan.TABLE_NAME +
                " a ORDER BY a."+MstBagianHutan.BAGIAN_HUTAN_NAME+", a."+MstBagianHutan.BAGIAN_HUTAN_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Bagian Hutan -");
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
                " a ORDER BY a."+MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME+", a."+MstJenisPermasalahanSchema.JENIS_PERMASALAHAN_NAME+" ASC";
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
        String selectQuery = "SELECT a." + MstDesaByRph.DESA_NAME + " FROM " + MstDesaByRph.TABLE_NAME +
                " a GROUP BY a."+ MstDesaByRph.DESA_NAME;
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
                " a ORDER BY a."+MstBentukInteraksiSchema.NAMA_INTERAKSI+", a."+MstBentukInteraksiSchema.NAMA_INTERAKSI+" ASC";
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
                " a ORDER BY a."+MstStatusInteraksiSchema.NAMA_STATUS+", a."+MstStatusInteraksiSchema.NAMA_STATUS+" ASC";
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

    public List<String> getStrata() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstStrataSchema.STRATA_NAME + " FROM " + MstStrataSchema.TABLE_NAME +
                " a ORDER BY a."+MstStrataSchema.STRATA_NAME+", a."+MstStrataSchema.STRATA_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Strata -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getPihakTerlibat() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstPihakTerlibatSchema.PIHAK_TERLIBAT_NAME + " FROM " + MstPihakTerlibatSchema.TABLE_NAME +
                " a ORDER BY a."+MstPihakTerlibatSchema.PIHAK_TERLIBAT_NAME+", a."+MstPihakTerlibatSchema.PIHAK_TERLIBAT_NAME+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Pihak Terlibat -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getPekerjaan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstPekerjaan.PEKERJAAN_NAME + " FROM " + MstPekerjaan.TABLE_NAME +
                " a ORDER BY a."+MstPekerjaan.PEKERJAAN_ID+", a."+MstPekerjaan.PEKERJAAN_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Pekerjaan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public List<String> getSubPekerjaan() {
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT a." + MstSubPekerjaan.SUB_PEKERJAAN_NAME + " FROM " + MstSubPekerjaan.TABLE_NAME +
                " a ORDER BY a."+MstSubPekerjaan.SUB_PEKERJAAN_ID+", a."+MstSubPekerjaan.SUB_PEKERJAAN_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        labels.add("- Pilih Sub Pekerjaan -");
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public int count_status_workorder(String status) {
        String countQuery = "SELECT * FROM TRN_WORK_ORDER WHERE "+TrnWorkOrder.STATUS+" =\""+status+"\" ;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
package id.co.perhutani.sisdhbukuobor.Model;

public class IdentifikasiTenurialModel {
    private String Id;
    private String Tanggal;
    private String JenisPermasalahan;
    private String Strata;
    private String AnakPetak;
    private String KelasHutan;
    private String LuasBaku;
    private String LuasTenurial;
    private String KondisiPetakSaatIdentifikasi;
    private String AwalKonflik;
    private String PihakTerlibat;
    private String StatusPenyelesaian;

    public IdentifikasiTenurialModel(String id, String tanggal, String jenisPermasalahan,
                                     String strata, String anakPetak, String kelasHutan,
                                     String luasBaku, String luasTenurial, String kondisiPetakSaatIdentifikasi,
                                     String awalKonflik, String pihakTerlibat, String statusPenyelesaian)
    {
        Id = id;
        Tanggal = tanggal;
        JenisPermasalahan = jenisPermasalahan;
        Strata = strata;
        AnakPetak = anakPetak;
        KelasHutan = kelasHutan;
        LuasBaku = luasBaku;
        LuasTenurial = luasTenurial;
        KondisiPetakSaatIdentifikasi = kondisiPetakSaatIdentifikasi;
        AwalKonflik = awalKonflik;
        PihakTerlibat = pihakTerlibat;
        StatusPenyelesaian = statusPenyelesaian;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getJenisPermasalahan() {
        return JenisPermasalahan;
    }

    public void setJenisPermasalahan(String jenisPermasalahan) {
        JenisPermasalahan = jenisPermasalahan;
    }

    public String getStrata() {
        return Strata;
    }

    public void setStrata(String strata) {
        Strata = strata;
    }

    public String getAnakPetak() {
        return AnakPetak;
    }

    public void setAnakPetak(String anakPetak) {
        AnakPetak = anakPetak;
    }

    public String getKelasHutan() {
        return KelasHutan;
    }

    public void setKelasHutan(String kelasHutan) {
        KelasHutan = kelasHutan;
    }

    public String getLuasBaku() {
        return LuasBaku;
    }

    public void setLuasBaku(String luasBaku) {
        LuasBaku = luasBaku;
    }

    public String getLuasTenurial() {
        return LuasTenurial;
    }

    public void setLuasTenurial(String luasTenurial) {
        LuasTenurial = luasTenurial;
    }

    public String getKondisiPetakSaatIdentifikasi() {
        return KondisiPetakSaatIdentifikasi;
    }

    public void setKondisiPetakSaatIdentifikasi(String kondisiPetakSaatIdentifikasi) {
        KondisiPetakSaatIdentifikasi = kondisiPetakSaatIdentifikasi;
    }

    public String getAwalKonflik() {
        return AwalKonflik;
    }

    public void setAwalKonflik(String awalKonflik) {
        AwalKonflik = awalKonflik;
    }

    public String getPihakTerlibat() {
        return PihakTerlibat;
    }

    public void setPihakTerlibat(String pihakTerlibat) {
        PihakTerlibat = pihakTerlibat;
    }

    public String getStatusPenyelesaian() {
        return StatusPenyelesaian;
    }

    public void setStatusPenyelesaian(String statusPenyelesaian) {
        StatusPenyelesaian = statusPenyelesaian;
    }
}

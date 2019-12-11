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
    private int ID_Tenurial;

    public IdentifikasiTenurialModel() {
    }

    public IdentifikasiTenurialModel(String id, String tanggal, String jenisPermasalahan,
                                     String strata, String anakPetak, String kelasHutan,
                                     String luasBaku, String luasTenurial, String kondisiPetakSaatIdentifikasi,
                                     String awalKonflik, String pihakTerlibat, String statusPenyelesaian, int idtenurial)
    {
        this.Id = id;
        this.Tanggal = tanggal;
        this.JenisPermasalahan = jenisPermasalahan;
        this.Strata = strata;
        this.AnakPetak = anakPetak;
        this.KelasHutan = kelasHutan;
        this.LuasBaku = luasBaku;
        this.LuasTenurial = luasTenurial;
        this.KondisiPetakSaatIdentifikasi = kondisiPetakSaatIdentifikasi;
        this.AwalKonflik = awalKonflik;
        this.PihakTerlibat = pihakTerlibat;
        this.StatusPenyelesaian = statusPenyelesaian;
        this.ID_Tenurial= idtenurial;
    }

    public int getID_Tenurial() {
        return ID_Tenurial;
    }

    public void setID_Tenurial(int ID_Tenurial) {
        this.ID_Tenurial = ID_Tenurial;
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

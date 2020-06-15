package id.co.perhutani.sisdhbukuobor.Model;

public class IdentifikasiTenurialModel {
    private String Id;
    private String JenisPermasalahan;
    private String AnakPetak;
    private String KelasHutan;
    private String Tanggal;
    private String Strata;
    private String LuasBaku;
    private String LuasTenurial;
    private String KondisiPetakSaatIdentifikasi;
    private String AwalKonflik;
    private String PihakTerlibat;
    private String StatusPenyelesaian;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket4;
    private String Ket5;
    private String Ket9;
    private String Ket10;
    private String Ket11;
    private int ID_Tenurial;

    public IdentifikasiTenurialModel() {
    }

    public IdentifikasiTenurialModel(String id, String jenisPermasalahan, String anakPetak, String kelasHutan,
                                     String tanggal, String strata, String luasBaku, String luasTenurial,
                                     String kondisiPetakSaatIdentifikasi, String awalKonflik, String pihakTerlibat,
                                     String statusPenyelesaian, int idtenurial, String ket1, String ket2,
                                     String ket3, String ket4, String ket5, String ket9, String ket10, String ket11)
    {
        this.Id = id;
        this.JenisPermasalahan = jenisPermasalahan;
        this.AnakPetak = anakPetak;
        this.KelasHutan = kelasHutan;
        this.Tanggal = tanggal;
        this.Strata = strata;
        this.LuasBaku = luasBaku;
        this.LuasTenurial = luasTenurial;
        this.KondisiPetakSaatIdentifikasi = kondisiPetakSaatIdentifikasi;
        this.AwalKonflik = awalKonflik;
        this.PihakTerlibat = pihakTerlibat;
        this.StatusPenyelesaian = statusPenyelesaian;
        this.ID_Tenurial= idtenurial;
        this.Ket1 = ket1;
        this.Ket2 = ket2;
        this.Ket3 = ket3;
        this.Ket4 = ket4;
        this.Ket5 = ket5;
        this.Ket1 = ket1;
        this.Ket9 = ket9;
        this.Ket10 = ket10;
        this.Ket11 = ket11;
    }

    public String getKet9() {
        return Ket9;
    }

    public void setKet9(String ket9) {
        Ket9 = ket9;
    }

    public String getKet10() {
        return Ket10;
    }

    public void setKet10(String ket10) {
        Ket10 = ket10;
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

    public String getKet1() {
        return Ket1;
    }

    public void setKet1(String ket1) {
        Ket1 = ket1;
    }

    public String getKet2() {
        return Ket2;
    }

    public void setKet2(String ket2) {
        Ket2 = ket2;
    }

    public String getKet3() {
        return Ket3;
    }

    public void setKet3(String ket3) {
        Ket3 = ket3;
    }

    public String getKet4() {
        return Ket4;
    }

    public void setKet4(String ket4) {
        Ket4 = ket4;
    }

    public String getKet5() {
        return Ket5;
    }

    public void setKet5(String ket5) {
        Ket5 = ket5;
    }

    public String getKet11() {
        return Ket11;
    }

    public void setKet11(String ket11) {
        Ket11 = ket11;
    }
}

package id.co.perhutani.sisdhbukuobor.Model;

public class RegisterpcpModel
{
    private String ID;
    private String NoPcp;
    private String PetakId;
    private String AnakPetakId;
    private String Tahun;
    private String LuasBaku;
    private String LuasBlok;
    private String Umur;
    private String RataKeliling;
    private String Bonita;
    private String MPcp;
    private String NormalPcp;
    private String NMati;
    private String TahunJarangan;
    private String KeteranganPcp;

    public RegisterpcpModel(String ID, String noPcp, String petakId, String anakPetakId,
                            String tahun, String luasBaku, String luasBlok, String umur,
                            String rataKeliling, String bonita, String MPcp, String normalPcp,
                            String NMati, String tahunJarangan, String keteranganPcp) {
        this.ID = ID;
        NoPcp = noPcp;
        PetakId = petakId;
        AnakPetakId = anakPetakId;
        Tahun = tahun;
        LuasBaku = luasBaku;
        LuasBlok = luasBlok;
        Umur = umur;
        RataKeliling = rataKeliling;
        Bonita = bonita;
        this.MPcp = MPcp;
        NormalPcp = normalPcp;
        this.NMati = NMati;
        TahunJarangan = tahunJarangan;
        KeteranganPcp = keteranganPcp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNoPcp() {
        return NoPcp;
    }

    public void setNoPcp(String noPcp) {
        NoPcp = noPcp;
    }

    public String getPetakId() {
        return PetakId;
    }

    public void setPetakId(String petakId) {
        PetakId = petakId;
    }

    public String getAnakPetakId() {
        return AnakPetakId;
    }

    public void setAnakPetakId(String anakPetakId) {
        AnakPetakId = anakPetakId;
    }

    public String getTahun() {
        return Tahun;
    }

    public void setTahun(String tahun) {
        Tahun = tahun;
    }

    public String getLuasBaku() {
        return LuasBaku;
    }

    public void setLuasBaku(String luasBaku) {
        LuasBaku = luasBaku;
    }

    public String getLuasBlok() {
        return LuasBlok;
    }

    public void setLuasBlok(String luasBlok) {
        LuasBlok = luasBlok;
    }

    public String getUmur() {
        return Umur;
    }

    public void setUmur(String umur) {
        Umur = umur;
    }

    public String getRataKeliling() {
        return RataKeliling;
    }

    public void setRataKeliling(String rataKeliling) {
        RataKeliling = rataKeliling;
    }

    public String getBonita() {
        return Bonita;
    }

    public void setBonita(String bonita) {
        Bonita = bonita;
    }

    public String getMPcp() {
        return MPcp;
    }

    public void setMPcp(String MPcp) {
        this.MPcp = MPcp;
    }

    public String getNormalPcp() {
        return NormalPcp;
    }

    public void setNormalPcp(String normalPcp) {
        NormalPcp = normalPcp;
    }

    public String getNMati() {
        return NMati;
    }

    public void setNMati(String NMati) {
        this.NMati = NMati;
    }

    public String getTahunJarangan() {
        return TahunJarangan;
    }

    public void setTahunJarangan(String tahunJarangan) {
        TahunJarangan = tahunJarangan;
    }

    public String getKeteranganPcp() {
        return KeteranganPcp;
    }

    public void setKeteranganPcp(String keteranganPcp) {
        KeteranganPcp = keteranganPcp;
    }
}



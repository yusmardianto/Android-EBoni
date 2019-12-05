package id.co.perhutani.sisdhbukuobor.Model;

public class RegisterpcpModel {
    private String ID;
    private String NoPcp;
    private String AnakPetakId;
    private String Tahun;
    private String LuasBaku;
    private String LuasBlok;
    private String Umur;
    private String RataKeliling;
    private String Bonita;
    private String NLapangan;
    private String NormalPcp;
    private String NMati;
    private String TahunJarangan;
    private String Peninggi;
    private String KeteranganPcp;
    private int ID_Registerpcp;

    public RegisterpcpModel(String ID, String noPcp, String anakPetakId, String tahun,
                            String luasBaku, String luasBlok, String umur, String rataKeliling,
                            String bonita, String NLapangan, String normalPcp, String NMati,
                            String tahunJarangan, String peninggi, String keteranganPcp,
                            int id_registerpcp) {
        this.ID = ID;
        this.NoPcp = noPcp;
        this.AnakPetakId = anakPetakId;
        this.Tahun = tahun;
        this.LuasBaku = luasBaku;
        this.LuasBlok = luasBlok;
        this.Umur = umur;
        this.RataKeliling = rataKeliling;
        this.Bonita = bonita;
        this.NLapangan = NLapangan;
        this.NormalPcp = normalPcp;
        this.NMati = NMati;
        this.TahunJarangan = tahunJarangan;
        this.Peninggi = peninggi;
        this.KeteranganPcp = keteranganPcp;
        this.ID_Registerpcp= id_registerpcp;
    }

    public RegisterpcpModel(){

    }

    public int getID_Registerpcp() {
        return ID_Registerpcp;
    }

    public void setID_Registerpcp(int ID_Registerpcp) {
        this.ID_Registerpcp = ID_Registerpcp;
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

    public String getNLapangan() {
        return NLapangan;
    }

    public void setNLapangan(String NLapangan) {
        this.NLapangan = NLapangan;
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

    public String getPeninggi() {
        return Peninggi;
    }

    public void setPeninggi(String peninggi) {
        Peninggi = peninggi;
    }

    public String getKeteranganPcp() {
        return KeteranganPcp;
    }

    public void setKeteranganPcp(String keteranganPcp) {
        KeteranganPcp = keteranganPcp;
    }
}

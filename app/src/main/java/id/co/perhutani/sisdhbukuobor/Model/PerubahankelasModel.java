package id.co.perhutani.sisdhbukuobor.Model;

public class PerubahankelasModel {

    private String ID;
    private String PetakID;
    private String Tahun;
    private String Luas;
    private String JenisTanaman;
    private String KelasHutan;
    private String LuasPerkiraan;
    private String JenisTanamanPerkiraan;
    private String KelasHutanPerkiraan;
    private String NoBappkh;
    private String LuasDefinitif;
    private String JenisTanamanDefinitif;
    private String KelasHutanDefinitif;
    private String KeteranganPerubahan;
    private int ID_Perubahan;

    public PerubahankelasModel(String ID, String petakID, String tahun, String luas, String jenisTanaman,
                               String kelasHutan, String luasPerkiraan, String jenisTanamanPerkiraan,
                               String kelasHutanPerkiraan, String noBappkh, String luasDefinitif,
                               String jenisTanamanDefinitif, String kelasHutanDefinitif, String keterangan,
                               int id_perubahan)
    {
        this.ID = ID;
        this.PetakID = petakID;
        this.Tahun = tahun;
        this.Luas = luas;
        this.JenisTanaman = jenisTanaman;
        this.KelasHutan = kelasHutan;
        this.LuasPerkiraan = luasPerkiraan;
        this.JenisTanamanPerkiraan = jenisTanamanPerkiraan;
        this.KelasHutanPerkiraan = kelasHutanPerkiraan;
        this.NoBappkh = noBappkh;
        this.LuasDefinitif = luasDefinitif;
        this.JenisTanamanDefinitif = jenisTanamanDefinitif;
        this.KelasHutanDefinitif = kelasHutanDefinitif;
        this.KeteranganPerubahan = keterangan;
        this.ID_Perubahan= id_perubahan;
    }

    public PerubahankelasModel() {

    }

    public int getID_Perubahan() {
        return ID_Perubahan;
    }

    public void setID_Perubahan(int ID_Perubahan) {
        this.ID_Perubahan = ID_Perubahan;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPetakID() {
        return PetakID;
    }

    public void setPetakID(String petakID) {
        PetakID = petakID;
    }

    public String getTahun() {
        return Tahun;
    }

    public void setTahun(String tahun) {
        Tahun = tahun;
    }

    public String getLuas() {
        return Luas;
    }

    public void setLuas(String luas) {
        Luas = luas;
    }

    public String getJenisTanaman() {
        return JenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        JenisTanaman = jenisTanaman;
    }

    public String getKelasHutan() {
        return KelasHutan;
    }

    public void setKelasHutan(String kelasHutan) {
        KelasHutan = kelasHutan;
    }

    public String getLuasPerkiraan() {
        return LuasPerkiraan;
    }

    public void setLuasPerkiraan(String luasPerkiraan) {
        LuasPerkiraan = luasPerkiraan;
    }

    public String getJenisTanamanPerkiraan() {
        return JenisTanamanPerkiraan;
    }

    public void setJenisTanamanPerkiraan(String jenisTanamanPerkiraan) {
        JenisTanamanPerkiraan = jenisTanamanPerkiraan;
    }

    public String getKelasHutanPerkiraan() {
        return KelasHutanPerkiraan;
    }

    public void setKelasHutanPerkiraan(String kelasHutanPerkiraan) {
        KelasHutanPerkiraan = kelasHutanPerkiraan;
    }

    public String getNoBappkh() {
        return NoBappkh;
    }

    public void setNoBappkh(String noBappkh) {
        NoBappkh = noBappkh;
    }

    public String getLuasDefinitif() {
        return LuasDefinitif;
    }

    public void setLuasDefinitif(String luasDefinitif) {
        LuasDefinitif = luasDefinitif;
    }

    public String getJenisTanamanDefinitif() {
        return JenisTanamanDefinitif;
    }

    public void setJenisTanamanDefinitif(String jenisTanamanDefinitif) {
        JenisTanamanDefinitif = jenisTanamanDefinitif;
    }

    public String getKelasHutanDefinitif() {
        return KelasHutanDefinitif;
    }

    public void setKelasHutanDefinitif(String kelasHutanDefinitif) {
        KelasHutanDefinitif = kelasHutanDefinitif;
    }

    public String getKeteranganPerubahan() {
        return KeteranganPerubahan;
    }

    public void setKeteranganPerubahan(String keterangan) {
        KeteranganPerubahan = keterangan;
    }
}

package id.co.perhutani.sisdhbukuobor.Model;

public class PelaporanpalbatasModel {
    private String ID;
    private String TanggalPal;
    private String JenisPal;
    private String KondisiPal;
    private String NomerPal;
    private String JumlahPal;
    private String KeteranganPal;

    public PelaporanpalbatasModel(String ID, String tanggalPal, String jenisPal, String kondisiPal,
                                  String nomerPal, String jumlahPal, String keteranganPal)
    {
        this.ID = ID;
        TanggalPal = tanggalPal;
        JenisPal = jenisPal;
        KondisiPal = kondisiPal;
        NomerPal = nomerPal;
        JumlahPal = jumlahPal;
        KeteranganPal = keteranganPal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTanggalPal() {
        return TanggalPal;
    }

    public void setTanggalPal(String tanggalPal) {
        TanggalPal = tanggalPal;
    }

    public String getJenisPal() {
        return JenisPal;
    }

    public void setJenisPal(String jenisPal) {
        JenisPal = jenisPal;
    }

    public String getKondisiPal() {
        return KondisiPal;
    }

    public void setKondisiPal(String kondisiPal) {
        KondisiPal = kondisiPal;
    }

    public String getNomerPal() {
        return NomerPal;
    }

    public void setNomerPal(String nomerPal) {
        NomerPal = nomerPal;
    }

    public String getJumlahPal() {
        return JumlahPal;
    }

    public void setJumlahPal(String jumlahPal) {
        JumlahPal = jumlahPal;
    }

    public String getKeteranganPal() {
        return KeteranganPal;
    }

    public void setKeteranganPal(String keteranganPal) {
        KeteranganPal = keteranganPal;
    }
}

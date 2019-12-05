package id.co.perhutani.sisdhbukuobor.Model;

public class PelaporanpalbatasModel {
    private String ID;
    private String TanggalPal;
    private String JenisPal;
    private String KondisiPal;
    private String NomerPal;
    private String JumlahPal;
    private String KeteranganPal;
    private int ID_Laporan;

    public PelaporanpalbatasModel(String ID, String tanggalPal, String jenisPal, String kondisiPal,
                                  String nomerPal, String jumlahPal, String keteranganPal, int id_laporan)
    {
        this.ID = ID;
        this.TanggalPal = tanggalPal;
        this.JenisPal = jenisPal;
        this.KondisiPal = kondisiPal;
        this.NomerPal = nomerPal;
        this.JumlahPal = jumlahPal;
        this.KeteranganPal = keteranganPal;
        this.ID_Laporan= id_laporan;
    }

    public PelaporanpalbatasModel(){

    }

    public int getID_Laporan() {
        return ID_Laporan;
    }

    public void setID_Laporan(int ID_Laporan) {
        this.ID_Laporan = ID_Laporan;
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

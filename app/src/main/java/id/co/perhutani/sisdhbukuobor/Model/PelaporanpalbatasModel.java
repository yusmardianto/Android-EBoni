package id.co.perhutani.sisdhbukuobor.Model;

public class PelaporanpalbatasModel {
    private String ID;
    private String BagianHutanPal;
    private String TanggalPal;
    private String NomerPal;
    private String JenisPal;
    private String KondisiPal;
    private String JumlahPal;
    private String KeteranganPal;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket9;
    private String Ket10;
    private int ID_Laporan;

    public PelaporanpalbatasModel(String ID, String bagianhutanPal, String tanggalPal, String nomerPal,
                                  String jenisPal, String kondisiPal, String jumlahPal, String keteranganPal,
                                  String ket1, String ket2, String ket3, int id_laporan, String ket9, String ket10)
    {
        this.ID = ID;
        this.BagianHutanPal = bagianhutanPal;
        this.TanggalPal = tanggalPal;
        this.NomerPal = nomerPal;
        this.JenisPal = jenisPal;
        this.KondisiPal = kondisiPal;
        this.JumlahPal = jumlahPal;
        this.KeteranganPal = keteranganPal;
        this.Ket1 = ket1;
        this.Ket2 = ket2;
        this.Ket3 = ket3;
        this.ID_Laporan= id_laporan;
        this.Ket9 = ket9;
        this.Ket10 = ket10;
    }

    public PelaporanpalbatasModel(){

    }

    public String getKet1() {
        return Ket1;
    }

    public void setKet1(String ket1) {
        Ket1 = ket1;
    }

    public String getKet2() { return Ket2; }

    public void setKet2(String ket2) { Ket2 = ket2; }

    public String getKet3() {
        return Ket3;
    }

    public void setKet3(String ket3) {
        Ket3 = ket3;
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

    public String getBagianHutanPal() {
        return BagianHutanPal;
    }

    public void setBagianHutanPal(String bagianhutanPal) {
        BagianHutanPal = bagianhutanPal ;
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

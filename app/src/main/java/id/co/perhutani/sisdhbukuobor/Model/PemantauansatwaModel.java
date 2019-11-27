package id.co.perhutani.sisdhbukuobor.Model;

public class PemantauansatwaModel {
    private String ID;
    private String Petak;
    private String Tanggal;
    private String Jenis;

    public PemantauansatwaModel(String ID, String petak, String tanggal, String jenis) {
        this.ID = ID;
        Petak = petak;
        Tanggal = tanggal;
        Jenis = jenis;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPetak() {
        return Petak;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }
}
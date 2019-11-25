package id.co.perhutani.sisdhbukuobor.Model;

public class GangguanModel {

    private String Isi;
    private String Petak;
    private String No;
    private String Tanggal;

    public GangguanModel() {
    }

    public GangguanModel(String isi, String petak, String no, String tanggal) {
        Isi = isi;
        Petak = petak;
        No = no;
        Tanggal = tanggal;
    }

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public String getPetak() {
        return Petak;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }
}

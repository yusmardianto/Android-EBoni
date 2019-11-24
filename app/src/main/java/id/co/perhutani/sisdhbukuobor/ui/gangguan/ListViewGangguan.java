package id.co.perhutani.sisdhbukuobor.ui.gangguan;

public class ListViewGangguan {

    private String Isi;
    private String Petak;
    private String No;
    private String Tanggal;

    public ListViewGangguan() {
    }

    public ListViewGangguan(String isi, String petak, String no, String tanggal) {
        Isi = isi;
        Petak = petak;
        No = no;
        Tanggal = tanggal;
    }

    //getter


    public String getIsi() {
        return Isi;
    }

    public String getPetak() {
        return Petak;
    }

    public String getNo() {
        return No;
    }

    public String getTanggal() {
        return Tanggal;
    }

    // setter


    public void setIsi(String isi) {
        Isi = isi;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public void setNo(String no) {
        No = no;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }
}

package id.co.perhutani.sisdhbukuobor.Model;

public class PelaporanpalbatasModel {
    private String ID;
    private String Petak;
    private String NoPal;
    private String Kondisi;


    public PelaporanpalbatasModel(String ID, String petak, String noPal, String kondisi) {
        this.ID = ID;
        Petak = petak;
        NoPal = noPal;
        Kondisi = kondisi;
    }

    public String getID() {
        return ID;
    }

    public String getPetak() {
        return Petak;
    }

    public String getNoPal() {
        return NoPal;
    }

    public String getKondisi() {
        return Kondisi;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public void setNoPal(String noPal) {
        NoPal = noPal;
    }

    public void setKondisi(String kondisi) {
        Kondisi = kondisi;
    }
}

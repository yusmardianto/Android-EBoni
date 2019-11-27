package id.co.perhutani.sisdhbukuobor.Model;

public class PerubahankelasModel {
    private String ID;
    private String NoPetak;
    private String Petak;
    private String Tanggal;
    private String Kelas;


    public PerubahankelasModel(String ID, String noPetak, String petak, String tanggal, String kelas) {
        this.ID = ID;
        NoPetak = noPetak;
        Petak = petak;
        Tanggal = tanggal;
        Kelas = kelas;
    }

    public String getID() {
        return ID;
    }

    public String getNoPetak() {
        return NoPetak;
    }

    public String getPetak() {
        return Petak;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public String getKelas() {
        return Kelas;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNoPetak(String noPetak) {
        NoPetak = noPetak;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public void setKelas(String kelas) {
        Kelas = kelas;
    }
}

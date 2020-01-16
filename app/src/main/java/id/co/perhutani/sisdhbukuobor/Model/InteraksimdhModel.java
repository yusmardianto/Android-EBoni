package id.co.perhutani.sisdhbukuobor.Model;

public class InteraksimdhModel {

    private String Id;
    private String Anakpetakid;
    private String Desaid;
    private String Bentukinteraksi;
    private String Tahun;
    private String Status;
    private String Keterangan;
    private int ID_IMDH;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket4;
    private String Ket5;
    private String Ket9;
    private String Ket10;

    public InteraksimdhModel(String id, String anakpetakid, String desaid, String bentukinteraksi,
                             String tahun, String status, String keterangan, String ket1, String ket2, String ket3,
                             String ket4, String ket5, int id_imdh,
                             String ket9, String ket10)

    {
        this.Id = id;
        this.Anakpetakid = anakpetakid;
        this.Desaid = desaid;
        this.Bentukinteraksi = bentukinteraksi;
        this.Tahun = tahun;
        this.Status = status;
        this.Keterangan = keterangan;
        this.Ket1 = ket1;
        this.Ket2 = ket2;
        this.Ket3 = ket3;
        this.Ket4 = ket4;
        this.Ket5 = ket5;
        this.Ket5 = ket9;
        this.Ket5 = ket10;
        this.ID_IMDH = id_imdh;
    }

    public InteraksimdhModel() {

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

    public int getID_IMDH() {
        return ID_IMDH;
    }

    public void setID_IMDH(int ID_IMDH) {
        this.ID_IMDH = ID_IMDH;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAnakpetakid() {
        return Anakpetakid;
    }

    public void setAnakpetakid(String anakpetakid) {
        Anakpetakid = anakpetakid;
    }

    public String getDesaid() {
        return Desaid;
    }

    public void setDesaid(String desaid) {
        Desaid = desaid;
    }

    public String getBentukinteraksi() {
        return Bentukinteraksi;
    }

    public void setBentukinteraksi(String bentukinteraksi) {
        Bentukinteraksi = bentukinteraksi;
    }

    public String getTahun() {
        return Tahun;
    }

    public void setTahun(String tahun) {
        Tahun = tahun;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getKet1() {
        return Ket1;
    }

    public void setKet1(String ket1) {
        Ket1 = ket1;
    }

    public String getKet2() {
        return Ket2;
    }

    public void setKet2(String ket2) {
        Ket2 = ket2;
    }

    public String getKet3() {
        return Ket3;
    }

    public void setKet3(String ket3) {
        Ket3 = ket3;
    }

    public String getKet4() {
        return Ket4;
    }

    public void setKet4(String ket4) {
        Ket4 = ket4;
    }

    public String getKet5() {
        return Ket5;
    }

    public void setKet5(String ket5) {
        Ket5 = ket5;
    }

}

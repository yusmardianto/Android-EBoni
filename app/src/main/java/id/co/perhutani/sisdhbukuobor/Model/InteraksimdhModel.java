package id.co.perhutani.sisdhbukuobor.Model;

public class InteraksimdhModel {

    private String Id;
    private String Anakpetakid;
    private String Desaid;
    private String Bentukinteraksi;
    private String Status;
    private String Keterangan;

    public InteraksimdhModel(String id, String anakpetakid, String desaid, String bentukinteraksi, String status, String keterangan) {
        Id = id;
        Anakpetakid = anakpetakid;
        Desaid = desaid;
        Bentukinteraksi = bentukinteraksi;
        Status = status;
        Keterangan = keterangan;
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
}

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

    public InteraksimdhModel(String id, String anakpetakid, String desaid, String bentukinteraksi,
                             String tahun, String status, String keterangan, int id_imdh)

    {
        this.Id = id;
        this.Anakpetakid = anakpetakid;
        this.Desaid = desaid;
        this.Bentukinteraksi = bentukinteraksi;
        this.Tahun = tahun;
        this.Status = status;
        this.Keterangan = keterangan;
        this.ID_IMDH = id_imdh;
    }

    public InteraksimdhModel() {

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
}

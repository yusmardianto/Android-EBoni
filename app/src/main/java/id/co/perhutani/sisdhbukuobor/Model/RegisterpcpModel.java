package id.co.perhutani.sisdhbukuobor.Model;

public class RegisterpcpModel
{
    private String Id;
    private String NoPcp;
    private String PetakPcP;
    private String Tahun;
    private String Keterangan;

    public RegisterpcpModel(String id, String noPcp, String petakPcP, String tahun, String keterangan) {
        Id = id;
        NoPcp = noPcp;
        PetakPcP = petakPcP;
        Tahun = tahun;
        Keterangan = keterangan;
    }

    public String getId() {
        return Id;
    }

    public String getNoPcp() {
        return NoPcp;
    }

    public void setNoPcp(String noPcp) {
        NoPcp = noPcp;
    }

    public String getPetakPcP() {
        return PetakPcP;
    }

    public void setPetakPcP(String petakPcP) {
        PetakPcP = petakPcP;
    }

    public String getTahun() {
        return Tahun;
    }

    public void setTahun(String tahun) {
        Tahun = tahun;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }
}



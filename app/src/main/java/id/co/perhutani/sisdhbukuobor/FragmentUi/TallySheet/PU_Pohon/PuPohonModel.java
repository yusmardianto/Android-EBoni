package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.PU_Pohon;

public class PuPohonModel {

    private String id, ts_id,  no_pohon, keliling_pohon, peninggi_pohon, kualitas_batang, ket9, ket10;
    private int pu_pohon_id;

    public PuPohonModel(String id) {
        this.id = id;
    }

    public PuPohonModel() {
    }

    public int getPu_pohon_id() {
        return pu_pohon_id;
    }

    public void setPu_pohon_id(int pu_pohon_id) {
        this.pu_pohon_id = pu_pohon_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTs_id() {
        return ts_id;
    }

    public void setTs_id(String ts_id) {
        this.ts_id = ts_id;
    }

    public String getNo_pohon() {
        return no_pohon;
    }

    public void setNo_pohon(String no_pohon) {
        this.no_pohon = no_pohon;
    }

    public String getKeliling_pohon() {
        return keliling_pohon;
    }

    public void setKeliling_pohon(String keliling_pohon) {
        this.keliling_pohon = keliling_pohon;
    }

    public String getPeninggi_pohon() {
        return peninggi_pohon;
    }

    public void setPeninggi_pohon(String peninggi_pohon) {
        this.peninggi_pohon = peninggi_pohon;
    }

    public String getKualitas_batang() {
        return kualitas_batang;
    }

    public void setKualitas_batang(String kualitas_batang) {
        this.kualitas_batang = kualitas_batang;
    }

    public String getKet9() {
        return ket9;
    }

    public void setKet9(String ket9) {
        this.ket9 = ket9;
    }

    public String getKet10() {
        return ket10;
    }

    public void setKet10(String ket10) {
        this.ket10 = ket10;
    }
}

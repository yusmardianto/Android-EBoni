package id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah;

public class SusunRisalahViewModel {

    private String id;
    private String kode_anakpetak;
    private String rph;
    private String anak_petak;

    public SusunRisalahViewModel(String id) {
        this.id = id;

    }

    public SusunRisalahViewModel() {
    }

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getAnak_petak() {
        return anak_petak;
    }

    public void setAnak_petak(String anak_petak) {
        this.anak_petak = anak_petak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode_anakpetak() {
        return kode_anakpetak;
    }

    public void setKode_anakpetak(String kode_anakpetak) {
        this.kode_anakpetak = kode_anakpetak;
    }
}

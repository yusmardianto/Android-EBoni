package id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan;

public class DetailMonitoringViewModel {

    private String id;
    private String kbd;
    private String nha;
    private String klshtn;
    private String tahun;
    private String catatan;

    public DetailMonitoringViewModel(String id) {
        this.id = id;
    }

    public DetailMonitoringViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKbd() {
        return kbd;
    }

    public void setKbd(String kbd) {
        this.kbd = kbd;
    }

    public String getNha() {
        return nha;
    }

    public void setNha(String nha) {
        this.nha = nha;
    }

    public String getKlshtn() {
        return klshtn;
    }

    public void setKlshtn(String klshtn) {
        this.klshtn = klshtn;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}

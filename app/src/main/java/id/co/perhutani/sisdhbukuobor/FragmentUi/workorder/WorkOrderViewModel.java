package id.co.perhutani.sisdhbukuobor.FragmentUi.workorder;

public class WorkOrderViewModel {

    private String id;
    private String workorder;
    private String jeniskegiatan;
    private String tanggal;
    private String dari;

    public WorkOrderViewModel() {
    }

    public WorkOrderViewModel(String id, String workorder, String jeniskegiatan, String tanggal, String dari) {
        this.id = id;
        this.workorder = workorder;
        this.jeniskegiatan = jeniskegiatan;
        this.tanggal = tanggal;
        this.dari = dari;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkorder() {
        return workorder;
    }

    public void setWorkorder(String workorder) {
        this.workorder = workorder;
    }

    public String getJeniskegiatan() {
        return jeniskegiatan;
    }

    public void setJeniskegiatan(String jeniskegiatan) {
        this.jeniskegiatan = jeniskegiatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}

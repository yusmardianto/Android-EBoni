package id.co.perhutani.sisdhbukuobor.Model;

public class PersemaianModel {

    private String Id;
    private String Tanggal;
    private String Target;
    private String Realisasi;
    private String TargetLuas;
    private String TargetBibit;
    private String PersiapanPatok;
    private String PersiapanBedeng;
    private String PersiapanBenih;
    private String PersiapanBenihSapih;
    private String PelaksanaanPenaburan;
    private String PelaksanaanPenyapih;
    private String PelaksanaanPenyapihBibit;
    private String PelaksanaanSiapTanam;

    public int ID_Persemaian;

    public PersemaianModel(String id, String tanggal, String target, String realisasi, String targetLuas,
                           String targetBibit, String persiapanPatok, String persiapanBedeng, String persiapanBenih,
                           String persiapanBenihSapih, String pelaksanaanPenaburan, String pelaksanaanPenyapih,
                           String pelaksanaanPenyapihBibit, String pelaksanaanSiapTanam, int ID_Persemaian)
    {
        this.Id = id;
        this.Tanggal = tanggal;
        this.Target = target;
        this.Realisasi = realisasi;
        this.TargetLuas = targetLuas;
        this.TargetBibit = targetBibit;
        this.PersiapanPatok = persiapanPatok;
        this.PersiapanBedeng = persiapanBedeng;
        this.PersiapanBenih = persiapanBenih;
        this.PersiapanBenihSapih = persiapanBenihSapih;
        this.PelaksanaanPenaburan = pelaksanaanPenaburan;
        this.PelaksanaanPenyapih = pelaksanaanPenyapih;
        this.PelaksanaanPenyapihBibit = pelaksanaanPenyapihBibit;
        this.PelaksanaanSiapTanam = pelaksanaanSiapTanam;
        this.ID_Persemaian = ID_Persemaian;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public String getRealisasi() {
        return Realisasi;
    }

    public void setRealisasi(String realisasi) {
        Realisasi = realisasi;
    }

    public String getTargetLuas() {
        return TargetLuas;
    }

    public void setTargetLuas(String targetLuas) {
        TargetLuas = targetLuas;
    }

    public String getTargetBibit() {
        return TargetBibit;
    }

    public void setTargetBibit(String targetBibit) {
        TargetBibit = targetBibit;
    }

    public String getPersiapanPatok() {
        return PersiapanPatok;
    }

    public void setPersiapanPatok(String persiapanPatok) {
        PersiapanPatok = persiapanPatok;
    }

    public String getPersiapanBedeng() {
        return PersiapanBedeng;
    }

    public void setPersiapanBedeng(String persiapanBedeng) {
        PersiapanBedeng = persiapanBedeng;
    }

    public String getPersiapanBenih() {
        return PersiapanBenih;
    }

    public void setPersiapanBenih(String persiapanBenih) {
        PersiapanBenih = persiapanBenih;
    }

    public String getPersiapanBenihSapih() {
        return PersiapanBenihSapih;
    }

    public void setPersiapanBenihSapih(String persiapanBenihSapih) {
        PersiapanBenihSapih = persiapanBenihSapih;
    }

    public String getPelaksanaanPenaburan() {
        return PelaksanaanPenaburan;
    }

    public void setPelaksanaanPenaburan(String pelaksanaanPenaburan) {
        PelaksanaanPenaburan = pelaksanaanPenaburan;
    }

    public String getPelaksanaanPenyapih() {
        return PelaksanaanPenyapih;
    }

    public void setPelaksanaanPenyapih(String pelaksanaanPenyapih) {
        PelaksanaanPenyapih = pelaksanaanPenyapih;
    }

    public String getPelaksanaanPenyapihBibit() {
        return PelaksanaanPenyapihBibit;
    }

    public void setPelaksanaanPenyapihBibit(String pelaksanaanPenyapihBibit) {
        PelaksanaanPenyapihBibit = pelaksanaanPenyapihBibit;
    }

    public String getPelaksanaanSiapTanam() {
        return PelaksanaanSiapTanam;
    }

    public void setPelaksanaanSiapTanam(String pelaksanaanSiapTanam) {
        PelaksanaanSiapTanam = pelaksanaanSiapTanam;
    }

    public int getID_Persemaian() {
        return ID_Persemaian;
    }

    public void setID_Persemaian(int ID_Persemaian) {
        this.ID_Persemaian = ID_Persemaian;
    }
}

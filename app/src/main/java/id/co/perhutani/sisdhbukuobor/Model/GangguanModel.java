package id.co.perhutani.sisdhbukuobor.Model;

public class GangguanModel {

    private String Id;
    private String TanggalKejadian;
    private String Isi;
    private String Petak;
    private String JenisTanaman;
    private String NoA;
    private String Tanggal;
    private String Luas;
    private String Pohon;
    private String Kyp;
    private String Kyb;
    private String Getah;
    private String Nilai;
    private String Keterangan;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket9;
    private String Ket10;

    public int ID_gangguan;


    public GangguanModel(String id, String tgl_kejadian, String isi, String petak, String jenistanaman,
                         String noA, String tanggal, String luas, String pohon, String kyp, String kyb,
                         String getah, String nilai, String keterangan, String ket1, String ket2,
                         String ket3, int id_gangguan, String ket9, String ket10

    ) {

        this.Id = id;
        this.TanggalKejadian = tgl_kejadian;
        this.Isi = isi;
        this.Petak = petak;
        this.JenisTanaman = jenistanaman;
        this.NoA = noA;
        this.Tanggal = tanggal;
        this.Luas = luas;
        this.Pohon = pohon;
        this.Kyp = kyp;
        this.Kyb = kyb;
        this.Getah = getah;
        this.Nilai = nilai;
        this.Keterangan = keterangan;
        this.Ket1 = ket1;
        this.Ket2 = ket2;
        this.Ket3 = ket3;
        this.ID_gangguan= id_gangguan;
        this.Ket9 = ket9;
        this.Ket10 = ket10;

    }

    public GangguanModel() {

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

    public int getID_gangguan() {
        return ID_gangguan;
    }

    public void setID_gangguan(int ID_gangguan) {
        this.ID_gangguan = ID_gangguan;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTgl_Kejadian() {
        return TanggalKejadian;
    }

    public void setTgl_Kejadian(String tgl_kejadian) {
        TanggalKejadian = tgl_kejadian;
    }

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public String getJenisTanaman() {
        return JenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        JenisTanaman = jenisTanaman;
    }

    public String getPetak() {
        return Petak;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public String getNoA() {
        return NoA;
    }

    public void setNoA(String noA) {
        NoA = noA;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }


    public String getLuas() {
        return Luas;
    }

    public void setLuas(String luas) {
        Luas = luas;
    }

    public String getPohon() {
        return Pohon;
    }

    public void setPohon(String pohon) {
        Pohon = pohon;
    }

    public String getKyp() {
        return Kyp;
    }

    public void setKyp(String kyp) {
        Kyp = kyp;
    }

    public String getKyb() {
        return Kyb;
    }

    public void setKyb(String kyb) {
        Kyb = kyb;
    }

    public String getGetah() {
        return Getah;
    }

    public void setGetah(String getah) {
        Getah = getah;
    }

    public String getNilai() {
        return Nilai;
    }

    public void setNilai(String nilai) {
        Nilai = nilai;
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
}

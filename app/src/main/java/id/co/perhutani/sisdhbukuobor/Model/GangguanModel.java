package id.co.perhutani.sisdhbukuobor.Model;

public class GangguanModel {

    private String Id;
    private String Isi;
    private String Petak;
    private String No;
    private String Tanggal;
    private String Luas;
    private String Pohon;
    private String Kyp;
    private String Kyb;
    private String Getah;
    private String Nilai;
    private String Keterangan;
    private String Ket1;

    public int ID_gangguan;


    public GangguanModel(String id, String isi, String petak, String no, String tanggal,
                         String luas, String pohon, String kyp, String kyb, String getah,
                         String nilai, String keterangan, String ket1, int id_gangguan

    ) {

        this.Id = id;
        this.Isi = isi;
        this.Petak = petak;
        this.No = no;
        this.Tanggal = tanggal;
        this.Luas = luas;
        this.Pohon = pohon;
        this.Kyp = kyp;
        this.Kyb = kyb;
        this.Getah = getah;
        this.Nilai = nilai;
        this.Keterangan = keterangan;
        this.Ket1 = ket1;
        this.ID_gangguan= id_gangguan;
    }

    public GangguanModel() {

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

    public String getIsi() {
        return Isi;
    }

    public void setIsi(String isi) {
        Isi = isi;
    }

    public String getPetak() {
        return Petak;
    }

    public void setPetak(String petak) {
        Petak = petak;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
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
}

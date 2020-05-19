package id.co.perhutani.sisdhbukuobor.Model;

public class PengelolaanHutanModel {
    private String Id;
    private String JenisPekerjaan;
    private String SubPekerjaan;
    private String Tanggal;
    private String AnakPetak;
    private String Rencana;
    private String Realisasi;
    private String Status;
    private String Keterangan;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket4;
    private String Ket9;
    private String Ket10;
    public int ID_Pekerjaan;

    public PengelolaanHutanModel(String id, String jenisPekerjaan, String subPekerjaan, String tanggal,
                                 String anakPetak, String rencana, String realisasi, String status,
                                 String keterangan, String ket1, String ket2, String ket3, String ket4,
                                 String ket9, String ket10, int ID_Pekerjaan) {
        this.Id = id;
        this.JenisPekerjaan = jenisPekerjaan;
        this.SubPekerjaan = subPekerjaan;
        this.Tanggal = tanggal;
        this.AnakPetak = anakPetak;
        this.Rencana = rencana;
        this.Realisasi = realisasi;
        this.Status = status;
        this.Keterangan = keterangan;
        this.Ket1 = ket1;
        this. Ket2 = ket2;
        this. Ket3 = ket3;
        this. Ket4 = ket4;
        this.Ket9 = ket9;
        this.Ket10 = ket10;
        this.ID_Pekerjaan = ID_Pekerjaan;
    }

    public PengelolaanHutanModel() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getJenisPekerjaan() {
        return JenisPekerjaan;
    }

    public void setJenisPekerjaan(String jenisPekerjaan) {
        JenisPekerjaan = jenisPekerjaan;
    }

    public String getSubPekerjaan() {
        return SubPekerjaan;
    }

    public void setSubPekerjaan(String subPekerjaan) {
        SubPekerjaan = subPekerjaan;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getAnakPetak() {
        return AnakPetak;
    }

    public void setAnakPetak(String anakPetak) {
        AnakPetak = anakPetak;
    }

    public String getRencana() {
        return Rencana;
    }

    public void setRencana(String rencana) {
        Rencana = rencana;
    }

    public String getRealisasi() {
        return Realisasi;
    }

    public void setRealisasi(String realisasi) {
        Realisasi = realisasi;
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

    public String getKet4() {
        return Ket4;
    }

    public void setKet4(String ket4) {
        Ket4 = ket4;
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

    public int getID_Pekerjaan() {
        return ID_Pekerjaan;
    }

    public void setID_Pekerjaan(int ID_Pekerjaan) {
        this.ID_Pekerjaan = ID_Pekerjaan;
    }
}

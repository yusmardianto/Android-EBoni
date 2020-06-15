package id.co.perhutani.sisdhbukuobor.Model;

public class PemantauansatwaModel {
    private String ID;
    private String AnakPetakId;
    private String Jenis;
    private String Jumlah;
    private String Waktulihat;
    private String Caralihat;
    private String Tanggal;
    private String KeteranganSatwa;
    private int ID_Pemantauan;
    private String Ket1;
    private String Ket2;
    private String Ket3;
    private String Ket4;
    private String Ket9;
    private String Ket10;
    private String Ket11;

    public PemantauansatwaModel(String ID, String anakPetakId, String jenis,
                                String jumlah, String waktulihat, String caralihat, String tanggal,
                                String keteranganSatwa, String ket1, String ket2, String ket3, String ket4, String ket9,
                                String ket10, String ket11, int id_pemantauan)
    {
        this.ID = ID;
        this.AnakPetakId = anakPetakId;
        this.Jenis = jenis;
        this.Jumlah = jumlah;
        this.Waktulihat = waktulihat;
        this.Caralihat = caralihat;
        this.Tanggal = tanggal;
        this.KeteranganSatwa = keteranganSatwa;
        this.ID_Pemantauan= id_pemantauan;
        this.Ket1 = ket1;
        this.Ket2 = ket2;
        this.Ket3 = ket3;
        this.Ket4 = ket4;
        this.Ket9 = ket9;
        this.Ket10 = ket10;
        this.Ket10 = ket11;

    }

    public PemantauansatwaModel(){

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

    public int getID_Pemantauan() {
        return ID_Pemantauan;
    }

    public void setID_Pemantauan(int ID_Pemantauan) {
        this.ID_Pemantauan = ID_Pemantauan;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAnakPetakId() {
        return AnakPetakId;
    }

    public void setAnakPetakId(String anakPetakId) {
        AnakPetakId = anakPetakId;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        Jumlah = jumlah;
    }

    public String getWaktulihat() {
        return Waktulihat;
    }

    public void setWaktulihat(String waktulihat) {
        Waktulihat = waktulihat;
    }

    public String getCaralihat() {
        return Caralihat;
    }

    public void setCaralihat(String caralihat) {
        Caralihat = caralihat;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getKeteranganSatwa() {
        return KeteranganSatwa;
    }

    public void setKeteranganSatwa(String keteranganSatwa) {
        KeteranganSatwa = keteranganSatwa;
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

    public String getKet11() {
        return Ket11;
    }

    public void setKet11(String ket11) {
        Ket11 = ket11;
    }
}
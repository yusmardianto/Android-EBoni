package id.co.perhutani.sisdhbukuobor.Model;

public class PemantauansatwaModel {
    private String ID;
    private String AnakPetakId;
    private String Jenis;
    private String Jumlah;
    private String Waktulihat;
    private String Caralihat;
    private String KeteranganSatwa;
    private int ID_Pemantauan;

    public PemantauansatwaModel(String ID, String anakPetakId, String jenis,
                                String jumlah, String waktulihat, String caralihat, String keteranganSatwa,
                                int id_pemantauan)
    {
        this.ID = ID;
        this.AnakPetakId = anakPetakId;
        this.Jenis = jenis;
        this.Jumlah = jumlah;
        this.Waktulihat = waktulihat;
        this.Caralihat = caralihat;
        this.KeteranganSatwa = keteranganSatwa;
        this.ID_Pemantauan= id_pemantauan;
    }

    public PemantauansatwaModel(){

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

    public String getKeteranganSatwa() {
        return KeteranganSatwa;
    }

    public void setKeteranganSatwa(String keteranganSatwa) {
        KeteranganSatwa = keteranganSatwa;
    }
}
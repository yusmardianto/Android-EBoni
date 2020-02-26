package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian;

import androidx.lifecycle.ViewModel;

public class PersemaianViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String id;
    private String aktifitas_persemaian;

    public PersemaianViewModel(String id, String aktifitas_persemaian) {
        this.id = id;
        this.aktifitas_persemaian = aktifitas_persemaian;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAktifitas_persemaian() {
        return aktifitas_persemaian;
    }

    public void setAktifitas_persemaian(String aktifitas_persemaian) {
        this.aktifitas_persemaian = aktifitas_persemaian;
    }
}

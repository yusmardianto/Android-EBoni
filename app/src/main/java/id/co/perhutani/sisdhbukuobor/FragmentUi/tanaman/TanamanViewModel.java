package id.co.perhutani.sisdhbukuobor.FragmentUi.tanaman;

import androidx.lifecycle.ViewModel;

public class TanamanViewModel extends ViewModel {

    private String id;
    private String persiapan_tanaman;
    // TODO: Implement the ViewModel

    public TanamanViewModel(String id, String persiapan_tanaman)
    {
        this.id = id;
        this.persiapan_tanaman = persiapan_tanaman;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAktifitas_persemaian() {
        return persiapan_tanaman;
    }

    public void setAktifitas_persemaian(String persiapan_tanaman) {
        this.persiapan_tanaman = persiapan_tanaman;
    }
}

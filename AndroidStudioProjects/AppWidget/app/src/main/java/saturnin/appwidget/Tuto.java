package saturnin.appwidget;

        import android.net.Uri;

public class Tuto {
    private String intitule = null;
    private Uri adresse = null;

    public Tuto(String intitule, String adresse) {
        this.intitule = intitule;
        this.adresse = Uri.parse(adresse);
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Uri getAdresse() {
        return adresse;
    }

    public void setAdresse(Uri adresse) {
        this.adresse = adresse;
    }
}

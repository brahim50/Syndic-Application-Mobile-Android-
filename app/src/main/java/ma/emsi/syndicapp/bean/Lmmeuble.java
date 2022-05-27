package ma.emsi.syndicapp.bean;

public class Lmmeuble {

    private int id;
    private int numero;
    private String nom;
    private int etage;
    private String addresse;
    private String ville;
    private String photo;

    public Lmmeuble(int id, int numero, String nom, int etage, String addresse, String ville, String photo) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.etage = etage;
        this.addresse = addresse;
        this.ville = ville;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Lmmeuble{" +
                "id=" + id +
                ", numero=" + numero +
                ", nom='" + nom + '\'' +
                ", etage=" + etage +
                ", addresse='" + addresse + '\'' +
                ", ville='" + ville + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Lmmeuble() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

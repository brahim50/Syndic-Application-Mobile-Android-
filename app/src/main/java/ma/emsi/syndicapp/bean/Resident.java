package ma.emsi.syndicapp.bean;

public class Resident {

    private int id;
    private String lmmeuble;
    private String appartement;
    private String nom;
    private String prenom;
    private String ville;
    private String email;
    private String tele;
    private String photo;

    public Resident() {

    }

    public Resident(int id, String lmmeuble, String appartement, String nom, String prenom, String ville, String email, String tele, String photo) {
        this.id = id;
        this.lmmeuble = lmmeuble;
        this.appartement = appartement;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.email = email;
        this.tele = tele;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLmmeuble() {
        return lmmeuble;
    }

    public void setLmmeuble(String lmmeuble) {
        this.lmmeuble = lmmeuble;
    }

    public String getAppartement() {
        return appartement;
    }

    public void setAppartement(String appartement) {
        this.appartement = appartement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

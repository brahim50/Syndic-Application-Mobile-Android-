package ma.emsi.syndicapp.bean;

public class Revenu {

    private int id;
    private String lmmeuble;
    private int appartement;
    private int somme;
    private String date;

    public Revenu() {
    }

    public Revenu(int id, String lmmeuble, int appartement, int somme, String date) {
        this.id = id;
        this.lmmeuble = lmmeuble;
        this.appartement = appartement;
        this.somme = somme;
        this.date = date;
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

    public int getAppartement() {
        return appartement;
    }

    public void setAppartement(int appartement) {
        this.appartement = appartement;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

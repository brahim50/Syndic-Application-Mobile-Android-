package ma.emsi.syndicapp.bean;

public class Appartement {
    private int id;
    private String lmmeuble;
    private int numero;
    private int etage;
    private int surface;

    public Appartement() {
    }

    public Appartement(int id, String lmmeuble, int numero, int etage, int surface) {
        this.id = id;
        this.lmmeuble = lmmeuble;
        this.numero = numero;
        this.etage = etage;
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "Appartement{" +
                "id=" + id +
                ", lmmeuble='" + lmmeuble + '\'' +
                ", numero=" + numero +
                ", etage=" + etage +
                ", surface=" + surface +
                '}';
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }
}

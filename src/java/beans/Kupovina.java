
package beans;


public class Kupovina {
    private int idK;
    private String komentar;
    private int ocena;

    public Kupovina(int idK, String komentar, int ocena) {
        this.idK = idK;
        this.komentar = komentar;
        this.ocena = ocena;
    }

    public Kupovina() {
    }

    public int getIdK() {
        return idK;
    }

    public void setIdK(int idK) {
        this.idK = idK;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    
    
}

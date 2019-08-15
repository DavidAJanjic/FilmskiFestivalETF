
package beans;

import java.util.Date;


public class Rezervacija {
    private int idR;
    private int jedinsteveniKod;
    private String status;
    private Date datumRezervacije;
    private Date datumIsteka;
    private int brojUlaznica;

    public Rezervacija(int idR, int jedinsteveniKod, String status, Date datumRezervacije, Date datumIsteka, int brojUlaznica) {
        this.idR = idR;
        this.jedinsteveniKod = jedinsteveniKod;
        this.status = status;
        this.datumRezervacije = datumRezervacije;
        this.datumIsteka = datumIsteka;
        this.brojUlaznica = brojUlaznica;
    }

    public Rezervacija() {
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getJedinsteveniKod() {
        return jedinsteveniKod;
    }

    public void setJedinsteveniKod(int jedinsteveniKod) {
        this.jedinsteveniKod = jedinsteveniKod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public int getBrojUlaznica() {
        return brojUlaznica;
    }

    public void setBrojUlaznica(int brojUlaznica) {
        this.brojUlaznica = brojUlaznica;
    }
    
    
}


package beans;

import DAO.RezervacijaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Rezervacija implements Serializable {
    private int idRezervacija;
    private String jedinsteveniKod;
    private String status;
    private Date datumRezervacije;
    private Date datumIsteka;
    private int brojUlaznica;
    private int idProjekcija;

    
    
    public Rezervacija(int idR, String jedinsteveniKod, String status, Date datumRezervacije, Date datumIsteka, int brojUlaznica) {
        this.idRezervacija = idR;
        this.jedinsteveniKod = jedinsteveniKod;
        this.status = status;
        this.datumRezervacije = datumRezervacije;
        this.datumIsteka = datumIsteka;
        this.brojUlaznica = brojUlaznica;
    }

    public Rezervacija() {
    }

    public int getIdProjekcija() {
        return idProjekcija;
    }

    public void setIdProjekcija(int idProjekcija) {
        this.idProjekcija = idProjekcija;
    }
    
    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public String getJedinsteveniKod() {
        return jedinsteveniKod;
    }

    public void setJedinsteveniKod(String jedinsteveniKod) {
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
    
    public String izvrsiRezervaciju(int idKorisnik, int idProjekcija, int brojUlaznica) throws SQLException {
        java.sql.Date datumRezervacije1 = java.sql.Date.valueOf(LocalDate.now());
        RezervacijaDAO.izvrsiRezervaciju(idKorisnik, idProjekcija, "ASDFGHJKLA", 1, datumRezervacije1, brojUlaznica);
        return "rezervacijaNaCekanju";
    }
}

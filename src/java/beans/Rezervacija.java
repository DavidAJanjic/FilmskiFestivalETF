
package beans;

import DAO.RezervacijaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
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
    String msgZaBrojKarataRezervacije;

    public String getMsgZaBrojKarataRezervacije() {
        return msgZaBrojKarataRezervacije;
    }

    public void setMsgZaBrojKarataRezervacije(String msgZaBrojKarataRezervacije) {
        this.msgZaBrojKarataRezervacije = msgZaBrojKarataRezervacije;
    }
    
    public String izvrsiRezervaciju(int idKorisnik, int idProjekcija, int brojUlaznica) throws SQLException {
        msgZaBrojKarataRezervacije = null;
        
        if(brojUlaznica <= 0 || brojUlaznica >=100){
        
            msgZaBrojKarataRezervacije = "Broj ulaznica mora biti minimalno 1, a maksimalno 100";
            return "rezervacijaIzOpisa";
        }
        java.sql.Date datumRezervacije1 = java.sql.Date.valueOf(LocalDate.now());
        
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();        
        
        RezervacijaDAO.izvrsiRezervaciju(idKorisnik, idProjekcija, saltStr, 1, datumRezervacije1, brojUlaznica);
        return "rezervacijaNaCekanju";
    }
}

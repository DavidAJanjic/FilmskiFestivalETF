package beans;

import DAO.KorisnikDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "korisnik")
@javax.faces.bean.SessionScoped
public class Korisnik implements Serializable {

    private int idKorisnik;
    private String username;
    private String password;
    private String newPassword;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String kontaktMob;
    private String email;
    private int tipKorisnikaInt;
    private int tipKorisnikaIntPromena;
    private String tipKorisnikaString;
    private int brojPrekrsaja;

    private String msgLogIn;

    public Korisnik(int idKorisnik, String username, String password, String ime, String prezime, Date datumRodjenja, String kontaktMob, String email, String tipKorisnikaString, int brojPrekrsaja) {
        this.idKorisnik = idKorisnik;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kontaktMob = kontaktMob;
        this.email = email;
        this.tipKorisnikaString = tipKorisnikaString;
        this.brojPrekrsaja = brojPrekrsaja;
    }

    public int getTipKorisnikaIntPromena() {
        return tipKorisnikaIntPromena;
    }

    public void setTipKorisnikaIntPromena(int tipKorisnikaIntPromena) {
        this.tipKorisnikaIntPromena = tipKorisnikaIntPromena;
    }

    public String getTipKorisnikaString() {
        return tipKorisnikaString;
    }

    public void setTipKorisnikaString(String tipKorisnikaString) {
        this.tipKorisnikaString = tipKorisnikaString;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Korisnik loginKorisnik;

    public Korisnik getLoginKorisnik() {
        return loginKorisnik;
    }

    public void setLoginKorisnik(Korisnik loginKorisnik) {
        this.loginKorisnik = loginKorisnik;
    }

    public String getMsgLogIn() {
        return msgLogIn;
    }

    public void setMsgLogIn(String msgLogIn) {
        this.msgLogIn = msgLogIn;
    }

    List<Korisnik> sviKorisnici = new ArrayList<>();
    
    
    
    public Korisnik() {
    }

    public Korisnik(String username, String password, String ime, String prezime, Date datumRodjenja, String kontaktMob, String email) {

        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kontaktMob = kontaktMob;
        this.email = email;
    }

    public Korisnik(String username, String password, String ime, String prezime, Date datumRodjenja, String kontaktMob, String email, int status, int brojPrekrsaja) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kontaktMob = kontaktMob;
        this.email = email;
        this.tipKorisnikaInt = status;
        this.brojPrekrsaja = brojPrekrsaja;
    }

    public Korisnik(int idKorisnik, String username, String password, String ime, String prezime, Date datumRodjenja, String kontaktMob, String email, int status, int brojPrekrsaja) {
        this.idKorisnik = idKorisnik;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kontaktMob = kontaktMob;
        this.email = email;
        this.tipKorisnikaInt = status;
        this.brojPrekrsaja = brojPrekrsaja;
    }

    
    
    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKontaktMob() {
        return kontaktMob;
    }

    public void setKontaktMob(String kontaktMob) {
        this.kontaktMob = kontaktMob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipKorisnikaInt() {
        return tipKorisnikaInt;
    }

    public void setTipKorisnikaInt(int tipKorisnikaInt) {
        this.tipKorisnikaInt = tipKorisnikaInt;
    }

    public int getBrojPrekrsaja() {
        return brojPrekrsaja;
    }

    public void setBrojPrekrsaja(int brojPrekrsaja) {
        this.brojPrekrsaja = brojPrekrsaja;
    }

    public List<Korisnik> getSviKorisnici() {
        return sviKorisnici;
    }

    public void setSviKorisnici(List<Korisnik> sviKorisnici) {
        this.sviKorisnici = sviKorisnici;
    }

    public String dodajKorisnika() throws SQLException {
        if (password.equals(newPassword)) {
            String result = KorisnikDAO.dodajKorisnika(username, password, ime, prezime, kontaktMob, email, datumRodjenja);
            if (result != "notOk") {
                return "index";
            }
        }
        msgLogIn = "Korisnicko ime je zauzeto ili ste pogresno ponovili password, pokusajte ponovo!";
        return "registracija";
    }

    public String nazad() {
        return "index";
    }

    public String film() {
        return "filmTemp";
    }

    public String loginKorisnik() {
        loginKorisnik = KorisnikDAO.dohvatiKorisnika(username, password);
        if (loginKorisnik.getTipKorisnikaInt() == 2) {
            return "regKorisnik";
        } else if (loginKorisnik.getTipKorisnikaInt() == 3) {
            return "prodavac";
        } else if (loginKorisnik.getTipKorisnikaInt() == 4) {
            return "admin";
        } else if (loginKorisnik.getTipKorisnikaInt() == 5) {
            return "banovan";
        } else {
            return "neRegKorisnik";
        }
    }

    public String login() {
        Korisnik k = KorisnikDAO.dohvatiKorisnika(username, password);
        idKorisnik = k.idKorisnik;
        msgLogIn = null;
        if (k != null) {
            if (k.getTipKorisnikaInt() == 1) {
                return "neRegKorisnik";
            } else if (k.getTipKorisnikaInt() == 2) {
                return "regKorisnik";
            } else if (k.getTipKorisnikaInt() == 3) {
                return "prodavac";
            } else if (k.getTipKorisnikaInt() == 4) {
                return "admin";
            } else if (k.getTipKorisnikaInt() == 5) {
                return "banovan";
            }
        }
        msgLogIn = "Uneti podatci nisu ispravni! Molimo pokusajte ponovo!";
        return "login";
    }

    public String logOut() {
        username = null;
        password = null;
        tipKorisnikaInt = 1;

        return "index";
    }

    public List<Korisnik> sviKorisnici() throws SQLException {
        return this.sviKorisnici = KorisnikDAO.sviKorisnici();
    }

    public String adminInfo(String username, String password) {
        loginKorisnik = KorisnikDAO.dohvatiKorisnika1(username, password);
        return "adminInfo";
    }

    public String promeniTipKorisnika(String username, int tipKorisnikaIntPromena) throws SQLException {
        KorisnikDAO.setTipKorisnika(username, tipKorisnikaIntPromena);
        return "admin";
    }

    public String odbaciZahtev(String username, String password) throws SQLException {
        KorisnikDAO.odbaciZahtev(username, password);
        return "admin";
    }
}

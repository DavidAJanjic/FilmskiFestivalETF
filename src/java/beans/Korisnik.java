package beans;

import DAO.KorisnikDAO;
import DAO.KupovinaDAO;
import DAO.RezervacijaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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

    
    private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    
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
        String encryptedText = "";
        if (password.equals(newPassword)) {

            
            try {
                Cipher cipher = Cipher.getInstance(cipherTransformation);
                byte[] key = encryptionKey.getBytes(characterEncoding);
                SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
                IvParameterSpec ivparameterspec = new IvParameterSpec(key);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
                byte[] cipherText = cipher.doFinal(password.getBytes("UTF8"));
                Base64.Encoder encoder = Base64.getEncoder();
                encryptedText = encoder.encodeToString(cipherText);

            } catch (Exception E) {
                System.err.println("Encrypt Exception : " + E.getMessage());
            }
            
        }

        String result = KorisnikDAO.dodajKorisnika(username, encryptedText, ime, prezime, kontaktMob, email, datumRodjenja);
        if (result != "notOk") {
            return "index";
        }
    
    msgLogIn  = "Korisnicko ime je zauzeto ili ste pogresno ponovili password, pokusajte ponovo!";

return "registracija";
    }

    public String nazad() {
        return "index";
    }

    public String film() {
        return "filmTemp";
    }

    public String login() {
        
        String encryptedText = "";
        try {
                Cipher cipher = Cipher.getInstance(cipherTransformation);
                byte[] key = encryptionKey.getBytes(characterEncoding);
                SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
                IvParameterSpec ivparameterspec = new IvParameterSpec(key);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
                byte[] cipherText = cipher.doFinal(password.getBytes("UTF8"));
                Base64.Encoder encoder = Base64.getEncoder();
                encryptedText = encoder.encodeToString(cipherText);

            } catch (Exception E) {
                System.err.println("Encrypt Exception : " + E.getMessage());
            }
        
        
        Korisnik k = KorisnikDAO.dohvatiKorisnika(username, encryptedText);
        idKorisnik = k.getIdKorisnik();
        ime = k.getIme();
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
    
    
    
        // kod za pregled svih postojecih rezervacija
    private int idRezervacija;
    private String jedinstveniKod;
    private int idStatusRezervacije;
    private Date datumRezervacije;
    private Date datumIsteka;
    private int brojUlaznica;
    private int idProjekcija;
    private int statusRezIntPromena;
    private int cena;
    
    private List rezervacijeIspis;

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public String getJedinstveniKod() {
        return jedinstveniKod;
    }

    public void setJedinstveniKod(String jedinstveniKod) {
        this.jedinstveniKod = jedinstveniKod;
    }

    public int getIdStatusRezervacije() {
        return idStatusRezervacije;
    }

    public void setIdStatusRezervacije(int idStatusRezervacije) {
        this.idStatusRezervacije = idStatusRezervacije;
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

    public int getIdProjekcija() {
        return idProjekcija;
    }

    public void setIdProjekcija(int idProjekcija) {
        this.idProjekcija = idProjekcija;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getStatusRezIntPromena() {
        return statusRezIntPromena;
    }

    public void setStatusRezIntPromena(int statusRezIntPromena) {
        this.statusRezIntPromena = statusRezIntPromena;
    }

    public List<Rezervacija> sveRezervacije() throws SQLException {
        return RezervacijaDAO.dohvatiSveRezervacije();
    }
    
    public List<Rezervacija> sveRezervacijeZaKorisnika1() throws SQLException {
        return RezervacijaDAO.dohvatiRezervacijePoIdKorisnika1(idKorisnik);
    }
    
    public List<Rezervacija> sveRezervacijeZaKorisnika2() throws SQLException {
        return RezervacijaDAO.dohvatiRezervacijePoIdKorisnika2(idKorisnik);
    }
    
    public List<Rezervacija> sveRezervacijeZaKorisnika3() throws SQLException {
        return RezervacijaDAO.dohvatiRezervacijePoIdKorisnika3(idKorisnik);
    }
    
    public String izvrsiKupovinu(int idRezervacije) throws SQLException {
        return KupovinaDAO.izvrsiKupovinu(idRezervacije);
    }
    
    public String obrisiKupovinu(int idRezervacije) throws SQLException {
        return KupovinaDAO.otkaziRezervaciju(idRezervacije);
    }
    
    

    public String promeniStatusRezervacije(int idRezervacija, int statusRezIntPromena, String jedinstveniKod) throws SQLException {
        RezervacijaDAO.setStatusRezervacije(idRezervacija, statusRezIntPromena, jedinstveniKod);
        return "prodavac";
    }
    
    List<Rezervacija> rezervacijePoNazivu = new ArrayList<>();

    public List getRezervacijeIspis() {
        return rezervacijeIspis;
    }

    public void setRezervacijeIspis(List rezervacijeIspis) {
        this.rezervacijeIspis = rezervacijeIspis;
    }

    public List<Rezervacija> getRezervacijePoNazivu() {
        return rezervacijePoNazivu;
    }

    public void setRezervacijePoNazivu(List<Rezervacija> rezervacijePoNazivu) {
        this.rezervacijePoNazivu = rezervacijePoNazivu;
    }

    public String dohvatiRezervacijePoNazivu() throws SQLException {
        rezervacijePoNazivu = RezervacijaDAO.dohvatiRezervacijePoImenuIPrezimenu(ime, prezime);
        return "prodavacPretraga";
    }
}

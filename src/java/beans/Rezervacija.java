package beans;

import DAO.RezervacijaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rezervacija {

    private int idRezervacija;
    private String jedinstveniKod;
    private int idStatusRezervacije;
    private Date datumRezervacije;
    private Date datumIsteka;
    private int brojUlaznica;
    private int cena;

    private int idProjekcija;
    private String ime;
    private String prezime;
    private int statusRezIntPromena;

    public Rezervacija(int idRezervacija, String jedinsteveniKod, int idStatusRezervacije, Date datumRezervacije, Date datumIsteka, int brojUlaznica) {
        this.idRezervacija = idRezervacija;
        this.jedinstveniKod = jedinstveniKod;
        this.idStatusRezervacije = idStatusRezervacije;
        this.datumRezervacije = datumRezervacije;
        this.datumIsteka = datumIsteka;
        this.brojUlaznica = brojUlaznica;
    }

    public Rezervacija() {
    }

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

    public int getStatusRezIntPromena() {
        return statusRezIntPromena;
    }

    public void setStatusRezIntPromena(int statusRezIntPromena) {
        this.statusRezIntPromena = statusRezIntPromena;
    }

    public List<Rezervacija> sveRezervacije = new ArrayList<>();

    public List<Rezervacija> getSveRezervacije() {
        return sveRezervacije;
    }

    public void setSveRezervacije(List<Rezervacija> sveRezervacije) {
        this.sveRezervacije = sveRezervacije;
    }

    public List<Rezervacija> prikaziSveRezervacije() throws SQLException {
        this.sveRezervacije = RezervacijaDAO.dohvatiSveRezervacije();
        return sveRezervacije;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }


    
//        public String dohvatiRezervacijePoImenuIPrezimenu() throws SQLException {
//
//        this.sveRezervacije = RezervacijaDAO.dohvatiRezervacijePoKorisniku(ime, prezime);
//
//        if (sveRezervacije.isEmpty()) {
//            return "prodavac";
//        }
//        return "prodavac";
//    }

    
}

package beans;

import DAO.FestivalDAO;
import DAO.ProjekcijaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.resource.spi.AuthenticationMechanism;

@ManagedBean(name = "festival")
@javax.faces.bean.SessionScoped
public class Festival implements Serializable {

    private int idFestival;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    private String festivalOpis;
    private int maxUlaznicaF;
    private int idMesto;
    private String mesto;
    private String opis;
    private String sala;
    
    private String imeLokacije;
    private String nazivSale;

    private int poslednjiIdFestival;
    public List<Festival> odabraniFestivalIndex = new ArrayList<>();
    public Festival odabraniFestival;
    public List<Festival> odabraniFestivali = new ArrayList<>();

    public Festival(int idFestival, String naziv, Date datumOd, Date datumDo, String opis, int maxUlaznica, String mesto) {
        this.idFestival = idFestival;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.festivalOpis = opis;
        this.maxUlaznicaF = maxUlaznica;
        this.mesto = mesto;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public List<Festival> getOdabraniFestivali() {
        return odabraniFestivali;
    }

    public void setOdabraniFestivali(List<Festival> odabraniFestivali) {
        this.odabraniFestivali = odabraniFestivali;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Festival> getOdabraniFestivalIndex() {
        return odabraniFestivalIndex;
    }

    public void setOdabraniFestivalIndex(List<Festival> odabraniFestivalIndex) {
        this.odabraniFestivalIndex = odabraniFestivalIndex;
    }

    public Festival getOdabraniFestival() {
        return odabraniFestival;
    }

    public void setOdabraniFestival(Festival odabraniFestival) {
        this.odabraniFestival = odabraniFestival;
    }

    public Festival() {
    }

    public int getIdFestival() {
        return idFestival;
    }

    public void setIdFestival(int idFestival) {
        this.idFestival = idFestival;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getFestivalOpis() {
        return festivalOpis;
    }

    public void setFestivalOpis(String festivalOpis) {
        this.festivalOpis = festivalOpis;
    }

    public int getMaxUlaznicaF() {
        return maxUlaznicaF;
    }

    public void setMaxUlaznicaF(int maxUlaznicaF) {
        this.maxUlaznicaF = maxUlaznicaF;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public int getPoslednjiIdFestival() {
        return poslednjiIdFestival;
    }

    public void setPoslednjiIdFestival(int poslednjiIdFestival) {
        this.poslednjiIdFestival = poslednjiIdFestival;
    }

    public String getImeLokacije() {
        return imeLokacije;
    }

    public void setImeLokacije(String imeLokacije) {
        this.imeLokacije = imeLokacije;
    }

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }
    
    

    public String prikaziDatiFestival(String nazivFestivala) throws SQLException {
        this.odabraniFestival = FestivalDAO.dohvatiInfoFestival(nazivFestivala);
        return "opisFestival";
    }

    public void prikaziDatiFestival1(String nazivFestivala) throws SQLException {

        this.odabraniFestivali = FestivalDAO.dohvatiInfoFestivale(nazivFestivala);

    }

    public String dohvatiFestivaleZaIndex() throws SQLException {
        this.odabraniFestivalIndex = FestivalDAO.dohvatiFestivaleZaIndex(naziv, datumOd, datumDo);
        return "ispisFestivalaZaNeReg";
    }

    //DODAVANJE NOVOG FESTIVALA
    public String ubaciNoviFestival() {
        this.poslednjiIdFestival = FestivalDAO.dodajFestival(naziv, idMesto, datumOd, datumDo, festivalOpis, maxUlaznicaF);
        return "dodavanjeProjekcije";

    }
    //DODAVANJE NOVE PROJEKCIJE ZA DATI FESTIVAL
    public String dodajProjekciju(int idFilm, int cena, int idLokacija, Date datumProjekcije, Date vremeProjekcije, int maxUlaznicaP) throws SQLException {
        ProjekcijaDAO.dodajProjekciju(poslednjiIdFestival, idFilm, cena, idLokacija, datumProjekcije, vremeProjekcije, maxUlaznicaP);
        return "dodavanjeProjekcije";
    }

    public List<Projekcija> sveProjekcije = new ArrayList<>();

    public List<Projekcija> getSveProjekcije() throws SQLException {
        return ProjekcijaDAO.listaProjekcijaZaFestival(poslednjiIdFestival);
    }

    public void setSveProjekcije(List<Projekcija> sveProjekcije) {
        this.sveProjekcije = sveProjekcije;
    }

    public void init() throws SQLException {
        if (sveProjekcije == null) {
            this.sveProjekcije = ProjekcijaDAO.listaProjekcijaZaFestival(poslednjiIdFestival);
        }
    }

}

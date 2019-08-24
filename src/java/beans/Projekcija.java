
package beans;

import DAO.LokacijaDAO;
import DAO.ProjekcijaDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean (name = "projekcija")
@SessionScoped

public class Projekcija implements Serializable{
    private int idP;
    private int cena;
    private String lokacija;
    private String sala;
    private String nazivFestivalaZaProjekciju;
    private String nazviLokacijeZaProjekciju;
    private Date datumProjekcije;
    private String nazivMestaZaProjekciju;
    private String nazivFilmaNaSrpskomIliStraniNazivFilma;
    private Date FestivaldatumOd;
    private Date FestivaldatumDo;
    List<Projekcija> IspisRegKorisnika;
    private Date vremeProjekcije;
    private String nazivSale;
    private int brojRezervisanihUlaznicaZaProjekciju;
    private int maxUlaznicaP;
    
    private int idFilm;
    private String originalniNazivFilma;
    private String nazivFilmaNaSrpskom; 
    
    private int idLokacija;
    private String imeLokacija;
    
    private String ImeLokacijaNazivSale;
    
    private int idMesto;

    public int getBrojRezervisanihUlaznicaZaProjekciju() {
        return brojRezervisanihUlaznicaZaProjekciju;
    }

    public void setBrojRezervisanihUlaznicaZaProjekciju(int brojRezervisanihUlaznicaZaProjekciju) {
        this.brojRezervisanihUlaznicaZaProjekciju = brojRezervisanihUlaznicaZaProjekciju;
    }
    
    
    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }

    
    
    public Date getVremeProjekcije() {
        return vremeProjekcije;
    }

    public void setVremeProjekcije(Date vremeProjekcije) {
        this.vremeProjekcije = vremeProjekcije;
    }

    
    public List<Projekcija> getIspisRegKorisnika() {
        return IspisRegKorisnika;
    }

    public void setIspisRegKorisnika(List<Projekcija> IspisRegKorisnika) {
        this.IspisRegKorisnika = IspisRegKorisnika;
    }

    public String getNazivFilmaNaSrpskomIliStraniNazivFilma() {
        return nazivFilmaNaSrpskomIliStraniNazivFilma;
    }

    public void setNazivFilmaNaSrpskomIliStraniNazivFilma(String nazivFilmaNaSrpskomIliStraniNazivFilma) {
        this.nazivFilmaNaSrpskomIliStraniNazivFilma = nazivFilmaNaSrpskomIliStraniNazivFilma;
    }
   
    public Projekcija() {
    }

    public Date getFestivaldatumOd() {
        return FestivaldatumOd;
    }

    public void setFestivaldatumOd(Date FestivaldatumOd) {
        this.FestivaldatumOd = FestivaldatumOd;
    }

    public Date getFestivaldatumDo() {
        return FestivaldatumDo;
    }

    public void setFestivaldatumDo(Date FestivaldatumDo) {
        this.FestivaldatumDo = FestivaldatumDo;
    }

    public String getNazivFestivalaZaProjekciju() {
        return nazivFestivalaZaProjekciju;
    }

    public void setNazivFestivalaZaProjekciju(String nazivFestivalaZaProjekciju) {
        this.nazivFestivalaZaProjekciju = nazivFestivalaZaProjekciju;
    }

    public String getNazviLokacijeZaProjekciju() {
        return nazviLokacijeZaProjekciju;
    }

    public void setNazviLokacijeZaProjekciju(String nazviLokacijeZaProjekciju) {
        this.nazviLokacijeZaProjekciju = nazviLokacijeZaProjekciju;
    }

    public Date getDatumProjekcije() {
        return datumProjekcije;
    }

    public void setDatumProjekcije(Date datumProjekcije) {
        this.datumProjekcije = datumProjekcije;
    }

    public String getNazivMestaZaProjekciju() {
        return nazivMestaZaProjekciju;
    }

    public void setNazivMestaZaProjekciju(String nazivMestaZaProjekciju) {
        this.nazivMestaZaProjekciju = nazivMestaZaProjekciju;
    }
     
    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
        this.imeLokacija = lokacija;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
        this.nazivSale = sala;
    }

    public int getMaxUlaznicaP() {
        return maxUlaznicaP;
    }

    public void setMaxUlaznicaP(int maxUlaznicaP) {
        this.maxUlaznicaP = maxUlaznicaP;
    }

    public int getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(int idLokacija) {
        this.idLokacija = idLokacija;
    }

    public String getImeLokacija() {
        return imeLokacija;
    }

    public void setImeLokacija(String imeLokacija) {
        this.imeLokacija = imeLokacija;
    }

    public String getImeLokacijaNazivSale() {
        return ImeLokacijaNazivSale;
    }

    public void setImeLokacijaNazivSale(String ImeLokacijaNazivSale) {
        this.ImeLokacijaNazivSale = ImeLokacijaNazivSale;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getOriginalniNazivFilma() {
        return originalniNazivFilma;
    }

    public void setOriginalniNazivFilma(String originalniNazivFilma) {
        this.originalniNazivFilma = originalniNazivFilma;
    }

    public String getNazivFilmaNaSrpskom() {
        return nazivFilmaNaSrpskom;
    }

    public void setNazivFilmaNaSrpskom(String nazivFilmaNaSrpskom) {
        this.nazivFilmaNaSrpskom = nazivFilmaNaSrpskom;
    }

    public String ispisNaZahtevRegKosinika() throws SQLException{
        
        this.IspisRegKorisnika = ProjekcijaDAO.ispisNaZahtevRegKosinika(nazivFestivalaZaProjekciju,nazivFilmaNaSrpskomIliStraniNazivFilma,FestivaldatumOd,FestivaldatumDo);
        
        return "ispisZaRegKorisnika";
    }
    
        public List<SelectItem> dohvatiIdLokacijeZaProjekciju(int idMesto) {
        return LokacijaDAO.dohvatiLokacijeZaProjekcije(idMesto).stream().map(x -> new SelectItem(x.getIdLokacija(), x.getImeLokacijaNazivSale())).collect(Collectors.toList());
    }

    
}

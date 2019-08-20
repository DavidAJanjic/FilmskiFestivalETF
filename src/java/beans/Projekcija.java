
package beans;

import DAO.ProjekcijaDAO;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class Projekcija {
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
    private Time vremeProjekcije;
    private String nazivSale;
    private int brojRezervisanihUlaznicaZaProjekciju;
    private int maxUlaznicaP;
    private int idLokacija;
    private String NazivFilmaNaSrpskom;
    private String OriginalniNazivFilma;

    public List<Projekcija> projekcijeZaIspis;
    public List<Projekcija> projekcijeZaOpisFilma;

    public List<Projekcija> getProjekicjeZaOpisFilma() {
        return projekcijeZaOpisFilma;
    }

    public void setProjekicjeZaOpisFilma(List<Projekcija> projekicjeZaOpisFilma) {
        this.projekcijeZaOpisFilma = projekicjeZaOpisFilma;
    }

    public String getNazivFilmaNaSrpskom() {
        return NazivFilmaNaSrpskom;
    }

    public void setNazivFilmaNaSrpskom(String NazivFilmaNaSrpskom) {
        this.NazivFilmaNaSrpskom = NazivFilmaNaSrpskom;
    }

    public String getOriginalniNazivFilma() {
        return OriginalniNazivFilma;
    }

    public void setOriginalniNazivFilma(String OriginalniNazivFilma) {
        this.OriginalniNazivFilma = OriginalniNazivFilma;
    }

    
   

    public List<Projekcija> getProjekcijeZaIspis() {
        return projekcijeZaIspis;
    }

    public void setProjekcijeZaIspis(List<Projekcija> projekcijeZaIspis) {
        this.projekcijeZaIspis = projekcijeZaIspis;
    }
    
    
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

    
    
    public Time getVremeProjekcije() {
        return vremeProjekcije;
    }

    public void setVremeProjekcije(Time vremeProjekcije) {
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
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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

    
    public String ispisNaZahtevRegKosinika() throws SQLException{
        
        this.IspisRegKorisnika = ProjekcijaDAO.ispisNaZahtevRegKosinika(nazivFestivalaZaProjekciju,nazivFilmaNaSrpskomIliStraniNazivFilma,FestivaldatumOd,FestivaldatumDo);
        if ((nazivFilmaNaSrpskomIliStraniNazivFilma.trim().length() != 0)) {
            return "ispisZaRegKorisnikaSaOrgNazivom";
        }
        return "ispisZaRegKorisnikaBezOrgNaziva";
    }
    public String ispisProjekcija(String nazivFestivalaZaProjekciju) throws SQLException{
        this.projekcijeZaIspis = ProjekcijaDAO.listaProjekcija1(nazivFestivalaZaProjekciju);
        return "projekcijeP";
    }
    
    public void ispisNaZahtevKosinika(String originalniNazivFilmaZaOpisFilma) throws SQLException {
        this.projekcijeZaOpisFilma = ProjekcijaDAO.ispisNaZahtevKorisnika(originalniNazivFilmaZaOpisFilma);
        
    }
    
}

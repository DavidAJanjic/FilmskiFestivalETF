package beans;

import DAO.LokacijaDAO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "lokacija")
@javax.faces.bean.SessionScoped
public class Lokacija {

    private int idLokacija;
    private int idMesto;
    private String imeLokacija;
    private String nazivSale;
    
    private String imeLokacijaNazivSale;

    public Lokacija() {
    }

    public Lokacija(int idLokacija, int idMesto, String imeLokacija, String nazivSale) {
        this.idLokacija = idLokacija;
        this.idMesto = idMesto;
        this.imeLokacija = imeLokacija;
        this.nazivSale = nazivSale;
    }

    public int getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(int idLokacija) {
        this.idLokacija = idLokacija;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getImeLokacija() {
        return imeLokacija;
    }

    public void setImeLokacija(String imeLokacija) {
        this.imeLokacija = imeLokacija;
    }

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }

    public String getImeLokacijaNazivSale() {
        return imeLokacijaNazivSale;
    }

    public void setImeLokacijaNazivSale(String imeLokacijaNazivSale) {
        this.imeLokacijaNazivSale = imeLokacijaNazivSale;
    }
    

    public List<SelectItem> dohvatiLokacije() {
        return LokacijaDAO.dohvatiSveLokacije().stream().map(x -> new SelectItem(x.getIdLokacija(), x.getImeLokacija())).collect(Collectors.toList());

    }

}

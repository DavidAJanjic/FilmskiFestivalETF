package beans;

import DAO.FilmDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "film")
@SessionScoped
public class Film implements Serializable {

    private int idFilm;
    private String originalniNaziv;
    private String nazivNaSrpskom;
    private int godinaIzdanja;
    private String filmOpis;
    private int idReziser;
    private String nazivReziser;
    private int idZemljePorekla;
    private int trajanjeFilma;
    private String imdbLink;
    private String poster;
    private int ocenaSUM;
    private int ocenaCOUNT;
    private int idGlumac;
    private int idGlumac1;
    private int idGlumac2;
    private int idGlumac3;
    private int idGlumac4;
    private String zemljaPorekla;
    private String sviGlumciFilma;
    private String nazivFestivalaZaDatiFilm;
    private int prosecnaOcena;
    private int poslednjiIdFilm;
    private Film film;
    private String imeReziseri;

    public Film() {
    }
    
    public void onLoad() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String nazivFilma = request.getParameter("naziv_filma");
        String nazivFestivalaZaProjekciju = request.getParameter("naziv_festivala_za_projekciju");
        
    }
    
    public Film(int idFilm, String originalniNaziv, String nazivNaSrpskom, int godIzdanja, String filmOpis, int idReziser, int idZemljePorekla, int trajanjeFilma, String imdbLink, String poster) {
        this.idFilm = idFilm;
        this.originalniNaziv = originalniNaziv;
        this.nazivNaSrpskom = nazivNaSrpskom;
        this.godinaIzdanja = godIzdanja;
        this.filmOpis = filmOpis;
        this.idReziser = idReziser;
        this.idZemljePorekla = idZemljePorekla;
        this.trajanjeFilma = trajanjeFilma;
        this.imdbLink = imdbLink;
        this.poster = poster;
    }

    public Film(int idFilm, String originalniNaziv, String nazivNaSrpskom, int godIzdanja, String filmOpis, int idReziser, int idZemljePorekla, int trajanjeFilma, String imdbLink, String poster, int ocenaSUM, int ocenaCOUNT) {
        this.idFilm = idFilm;
        this.originalniNaziv = originalniNaziv;
        this.nazivNaSrpskom = nazivNaSrpskom;
        this.godinaIzdanja = godIzdanja;
        this.filmOpis = filmOpis;
        this.idReziser = idReziser;
        this.idZemljePorekla = idZemljePorekla;
        this.trajanjeFilma = trajanjeFilma;
        this.imdbLink = imdbLink;
        this.poster = poster;
        this.ocenaSUM = ocenaSUM;
        this.ocenaCOUNT = ocenaCOUNT;
    }

    public Film(String originalniNaziv, String nazivNaSrpskom, int godinaIzdanja, String filmOpis, int idReziser, int idZemljePorekla, int trajanjeFilma, String imdbLink, String poster, int idGlumac) {
        this.originalniNaziv = originalniNaziv;
        this.nazivNaSrpskom = nazivNaSrpskom;
        this.godinaIzdanja = godinaIzdanja;
        this.filmOpis = filmOpis;
        this.idReziser = idReziser;
        this.idZemljePorekla = idZemljePorekla;
        this.trajanjeFilma = trajanjeFilma;
        this.imdbLink = imdbLink;
        this.poster = poster;
        this.idGlumac = idGlumac;
    }

    public int getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(int prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getNazivFestivalaZaDatiFilm() {
        return nazivFestivalaZaDatiFilm;
    }

    public void setNazivFestivalaZaDatiFilm(String nazivFestivalaZaDatiFilm) {
        this.nazivFestivalaZaDatiFilm = nazivFestivalaZaDatiFilm;
    }

    public String getSviGlumciFilma() {
        return sviGlumciFilma;
    }

    public void setSviGlumciFilma(String sviGlumciFilma) {
        this.sviGlumciFilma = sviGlumciFilma;
    }

    public String getZemljaPorekla() {
        return zemljaPorekla;
    }

    public void setZemljaPorekla(String zemljaPorekla) {
        this.zemljaPorekla = zemljaPorekla;
    }

    public String getNazivReziser() {
        return nazivReziser;
    }

    public void setNazivReziser(String nazivReziser) {
        this.nazivReziser = nazivReziser;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getOriginalniNaziv() {
        return originalniNaziv;
    }

    public void setOriginalniNaziv(String originalniNaziv) {
        this.originalniNaziv = originalniNaziv;
    }

    public String getNazivNaSrpskom() {
        return nazivNaSrpskom;
    }

    public void setNazivNaSrpskom(String nazivNaSrpskom) {
        this.nazivNaSrpskom = nazivNaSrpskom;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getFilmOpis() {
        return filmOpis;
    }

    public void setFilmOpis(String filmOpis) {
        this.filmOpis = filmOpis;
    }

    public int getIdReziser() {
        return idReziser;
    }

    public void setIdReziser(int idReziser) {
        this.idReziser = idReziser;
    }

    public int getIdZemljePorekla() {
        return idZemljePorekla;
    }

    public void setIdZemljePorekla(int idZemljePorekla) {
        this.idZemljePorekla = idZemljePorekla;
    }

    public int getTrajanjeFilma() {
        return trajanjeFilma;
    }

    public void setTrajanjeFilma(int trajanjeFilma) {
        this.trajanjeFilma = trajanjeFilma;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public int getOcenaSUM() {
        return ocenaSUM;
    }

    public void setOcenaSUM(int ocenaSUM) {
        this.ocenaSUM = ocenaSUM;
    }

    public int getOcenaCOUNT() {
        return ocenaCOUNT;
    }

    public void setOcenaCOUNT(int ocenaCOUNT) {
        this.ocenaCOUNT = ocenaCOUNT;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getIdGlumac() {
        return idGlumac;
    }

    public void setIdGlumac(int idGlumac) {
        this.idGlumac = idGlumac;
    }

    public int getPoslednjiIdFilm() {
        return poslednjiIdFilm;
    }

    public void setPoslednjiIdFilm(int poslednjiIdFilm) {
        this.poslednjiIdFilm = poslednjiIdFilm;
    }

    public int getIdGlumac1() {
        return idGlumac1;
    }

    public void setIdGlumac1(int idGlumac1) {
        this.idGlumac1 = idGlumac1;
    }

    public int getIdGlumac2() {
        return idGlumac2;
    }

    public void setIdGlumac2(int idGlumac2) {
        this.idGlumac2 = idGlumac2;
    }

    public int getIdGlumac3() {
        return idGlumac3;
    }

    public void setIdGlumac3(int idGlumac3) {
        this.idGlumac3 = idGlumac3;
    }

    public int getIdGlumac4() {
        return idGlumac4;
    }

    public void setIdGlumac4(int idGlumac4) {
        this.idGlumac4 = idGlumac4;
    }

    public String getImeReziseri() {
        return imeReziseri;
    }

    public void setImeReziseri(String imeReziseri) {
        this.imeReziseri = imeReziseri;
    }
    
    

    public void ubaciNoviFilm() {
        FilmDAO.dodajFilm(originalniNaziv, nazivNaSrpskom, godinaIzdanja, 
                filmOpis, idReziser, idZemljePorekla, trajanjeFilma, imdbLink, 
                poster, idGlumac, idGlumac1, idGlumac2, idGlumac3, idGlumac4);
    }

    public String ispisFilmZaKorisnika(String originalniNazivFilma, String nazivFestivalaZaProjekciju) throws SQLException {
        this.film = FilmDAO.ispisFilmaZaKorisnika(originalniNazivFilma, nazivFestivalaZaProjekciju);
        return "opisFilm_1";
    }

    public List<SelectItem> dodajFilmZaProjekciju() {
        return FilmDAO.dohvatiSveFilmove().stream().map(x -> new SelectItem(x.getIdFilm(), x.getOriginalniNaziv())).collect(Collectors.toList());

    }
}

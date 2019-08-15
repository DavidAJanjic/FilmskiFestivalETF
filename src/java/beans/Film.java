package beans;

import DAO.FilmDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "film")
@SessionScoped
public class Film {

    private int idFilm;
    private String originalniNaziv;
    private String nazivNaSrpskom;
    private int godinaIzdanja;
    private String filmOpis;
    private int idReziser;
    private int idZemljePorekla;
    private int trajanjeFilma;
    private String imdbLink;
    private String poster;
    private int ocenaSUM;
    private int ocenaCOUNT;
    private int idGlumac;

    private int poslednjiIdFilm;

    public Film() {
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

    public void ubaciNoviFilm() {
        FilmDAO.dodajFilm(originalniNaziv, nazivNaSrpskom, godinaIzdanja, filmOpis, idReziser, idZemljePorekla, trajanjeFilma, imdbLink, poster, idGlumac);

    }

}

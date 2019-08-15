package DAO;

import beans.Projekcija;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.DB;

@ManagedBean
@SessionScoped
public class ProjekcijaDAO implements Serializable {

    private String nazivFilmaNaSrpskom;
    private String originalniNazivFilma;
    private String lokacija;
    private String sala;
    private Date datumProjekcije;
    private Time vremeProjekcije;
    private int cena;
    private int maxUlaznicaP;

    public ProjekcijaDAO() {
    }

    public ProjekcijaDAO(String nazivFilmaNaSrpskom, String originalniNazivFilma, String lokacija, String sala, Date datumProjekcije, Time vremeProjekcije, int cena, int maxUlaznica) {
        this.nazivFilmaNaSrpskom = nazivFilmaNaSrpskom;
        this.originalniNazivFilma = originalniNazivFilma;
        this.lokacija = lokacija;
        this.sala = sala;
        this.datumProjekcije = datumProjekcije;
        this.vremeProjekcije = vremeProjekcije;
        this.cena = cena;
        this.maxUlaznicaP = maxUlaznica;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Date getDatumProjekcije() {
        return datumProjekcije;
    }

    public void setDatumProjekcije(Date datumProjekcije) {
        this.datumProjekcije = datumProjekcije;
    }

    public Time getVremeProjekcije() {
        return vremeProjekcije;
    }

    public void setVremeProjekcije(Time vremeProjekcije) {
        this.vremeProjekcije = vremeProjekcije;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getMaxUlaznicaP() {
        return maxUlaznicaP;
    }

    public void setMaxUlaznicaP(int maxUlaznicaP) {
        this.maxUlaznicaP = maxUlaznicaP;
    }

    public int dodajProjekciju(int idFestival, int idFilm, int cena, int idLokacija, Date datumProjekcije, Time vremeProjekcije, int maxUlaznicaP) throws SQLException {

        String festivalSql = "insert into projekcija(idFestival, idFilm, cena, idLokacija, datumProjekcije, vremeProjekcije, maxUlaznicaP) values(?, ?, ?, ?, ?, ?, ?)";

        int poslednjiIdFilm = -1;

        try (
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(festivalSql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setInt(1, idFestival);
            ps.setInt(2, idFilm);
            ps.setInt(3, cena);
            ps.setInt(4, idLokacija);
            ps.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(datumProjekcije));
            ps.setTime(6, new java.sql.Time(vremeProjekcije.getTime()));
            ps.setInt(7, maxUlaznicaP);

            ps.executeUpdate();

            ResultSet rsId = ps.getGeneratedKeys();
            if (rsId.next()) {
                poslednjiIdFilm = rsId.getInt(1);

            }

            ps.close();

            connection.close();

        } 
        return poslednjiIdFilm;
    }

    public List<ProjekcijaDAO> listaProjekcija(int idFestival) throws SQLException {
        List<ProjekcijaDAO> projekcije = new ArrayList<>();
        String sql = "select projekcija.cena, projekcija.datumProjekcije,projekcija.vremeProjekcije,"
                + "projekcija.maxUlaznica,lokacija.imeLokacija,lokacija.nazivSale,film.nazivNaSrpskom,film.originalniNaziv "
                + "from projekcija,film,lokacija "
                + "where projekcija.idFestival = ? and projekcija.idFilm = film.idFilm and projekcija.idLokacija = lokacija.idLokacija;";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idFestival);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProjekcijaDAO projekcija = new ProjekcijaDAO();
                projekcija.setNazivFilmaNaSrpskom(rs.getString("nazivNaSrpskom"));
                projekcija.setOriginalniNazivFilma(rs.getString("originalniNaziv"));
                projekcija.setLokacija(rs.getString("imeLokacija"));
                projekcija.setSala(rs.getString("nazivSale"));
                projekcija.setDatumProjekcije(rs.getDate("datumProjekcije"));
                projekcija.setCena(rs.getInt("cena"));
                projekcija.setMaxUlaznicaP(rs.getInt("maxUlaznica"));
                projekcija.setVremeProjekcije(rs.getTime("vremeProjekcije"));

                projekcije.add(projekcija);
            }
        }
        return projekcije;
    }

    public static List<Projekcija> ispisNaZahtevRegKosinika(String nazivFestivala, String nazivFilma, Date datumOd, Date datumDo) throws SQLException {
        List<Projekcija> listaProjekcija = new ArrayList<>();
        int i = 1;
        String sql = "select * from projekcija p, festival fe, film fi, lokacija l,"
                + " mesto m where p.idFestival = fe.idFestival and p.idFilm = fi.idFilm"
                + " and p.idLokacija = l.idLokacija and l.idMesto = m.idMesto";
        if (nazivFestivala.trim().length() != 0) {
            sql += " and fe.naziv = ?";
        }
        if ((nazivFilma.trim().length() != 0)) {
            sql += " and fi.originalniNaziv = ? or fi.nazivNaSrpskom = ?";
        }
        if (datumOd != null) {
            sql += " and fe.datumOd > ?";
        }
        if (datumDo != null) {
            sql += " and fe.datumDo < ?";
        }

        try (Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {

            if (nazivFestivala.trim().length() != 0) {
                ps.setString(i, nazivFestivala);
                i++;
            }
            if ((nazivFilma.trim().length() != 0)) {
                ps.setString(i, nazivFilma);
                i++;
                ps.setString(i, nazivFilma);
                i++;
            }
            if (datumOd != null) {
                ps.setString(i, (new SimpleDateFormat("yyyy-MM-dd")).format(datumOd));
                i++;
            }
            if (datumDo != null) {
                ps.setString(i, (new SimpleDateFormat("yyyy-MM-dd")).format(datumDo));
            }

            ResultSet rs = ps.executeQuery();

            if (nazivFilma.trim().length() != 0) {
                while (rs.next()) {
                    Projekcija projekcija = new Projekcija();
                    projekcija.setNazivFestivalaZaProjekciju(rs.getString("naziv"));
                    projekcija.setDatumProjekcije(rs.getDate("datumProjekcije"));
                    projekcija.setNazivMestaZaProjekciju(rs.getString("imeMesto"));
                    projekcija.setLokacija(rs.getString("imeLokacija"));
                    projekcija.setVremeProjekcije(rs.getTime("vremeProjekcije"));
                    projekcija.setNazivSale(rs.getString("nazivSale"));
                    listaProjekcija.add(projekcija);

                }
            } else {
                while (rs.next()) {
                    Projekcija projekcija = new Projekcija();
                    projekcija.setNazivFestivalaZaProjekciju(rs.getString("naziv"));
                    projekcija.setFestivaldatumOd(rs.getDate("datumOd"));
                    projekcija.setFestivaldatumDo(rs.getDate("datumDo"));
                    projekcija.setNazivMestaZaProjekciju(rs.getString("imeMesto"));
                    listaProjekcija.add(projekcija);

                }
            }

            return listaProjekcija;
        }
    }
}

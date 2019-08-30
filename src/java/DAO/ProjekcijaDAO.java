package DAO;

import beans.Projekcija;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public ProjekcijaDAO() {
    }

    public static void dodajProjekciju(int idFestival, int idFilm, int cena, int idLokacija, Date datumProjekcije, Date vremeProjekcije, int maxUlaznicaP) throws SQLException {
        String festivalSql = "insert into projekcija(idFestival, idFilm, cena, idLokacija, datumProjekcije, vremeProjekcije, maxUlaznicaP) values(?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(festivalSql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setInt(1, idFestival);
            ps.setInt(2, idFilm);
            ps.setInt(3, cena);
            ps.setInt(4, idLokacija);
            ps.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(datumProjekcije));
            ps.setString(6, (new SimpleDateFormat("HH:mm")).format(vremeProjekcije));
            ps.setInt(7, maxUlaznicaP);

            ps.executeUpdate();
            ps.close();
            connection.close();

        } 
        
    }
    
    public static List<Projekcija> listaProjekcija(int idFestivala) throws SQLException {
        List<Projekcija> projekcije = new ArrayList<>();
        String sql = "select p.cena, p.datumProjekcije, p.vremeProjekcije,"
                + "p.idProjekcija, p.maxUlaznicaP, l.imeLokacija, l.nazivSale,"
                + "f.nazivNaSrpskom, f.originalniNaziv, fe.naziv "
                + "from projekcija p, film f, lokacija l, festival fe "
                + "where p.idFestival = ? and p.idFilm = f.idFilm and p.idLokacija = l.idLokacija "
                + "and p.idFestival = f.idFestival;";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idFestivala);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Projekcija projekcija = new Projekcija();
                projekcija.setNazivFilmaNaSrpskom(rs.getString("nazivNaSrpskom"));
                projekcija.setOriginalniNazivFilma(rs.getString("originalniNaziv"));
                projekcija.setLokacija(rs.getString("imeLokacija"));
                projekcija.setSala(rs.getString("nazivSale"));
                projekcija.setDatumProjekcije(rs.getDate("datumProjekcije"));
                projekcija.setCena(rs.getInt("cena"));
                projekcija.setMaxUlaznicaP(rs.getInt("maxUlaznicaP"));
                projekcija.setVremeProjekcije(rs.getTime("vremeProjekcije"));
                projekcija.setIdP(rs.getInt("idProjekcija"));
                projekcija.setNazivFestivalaZaProjekciju(rs.getString("naziv"));

                projekcije.add(projekcija);
            }
        }
        return projekcije;
    }
    
    public static List<Projekcija> listaProjekcija1(String nazivFestivala) throws SQLException {
        int idFestivala = 1;
        String sql1 = "select * from festival where naziv = ?";
        try (
                Connection c1 = DB.otvoriKonekciju();
                PreparedStatement ps1 = c1.prepareStatement(sql1);) {
                ps1.setString(1, nazivFestivala);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()){
                idFestivala = rs1.getInt("idFestival");}
                
        }
        
        List<Projekcija> projekcije = new ArrayList<>();
        
        String sql = "select projekcija.cena, projekcija.datumProjekcije,projekcija.vremeProjekcije,projekcija.idProjekcija,"
                + "projekcija.maxUlaznicaP,lokacija.imeLokacija,lokacija.nazivSale,film.nazivNaSrpskom,film.originalniNaziv "
                + "from projekcija,film,lokacija "
                + "where projekcija.idFestival = ? and projekcija.idFilm = film.idFilm and projekcija.idLokacija = lokacija.idLokacija;";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idFestivala);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Projekcija projekcija = new Projekcija();
                projekcija.setNazivFilmaNaSrpskom(rs.getString("nazivNaSrpskom"));
                projekcija.setOriginalniNazivFilma(rs.getString("originalniNaziv"));
                projekcija.setLokacija(rs.getString("imeLokacija"));
                projekcija.setSala(rs.getString("nazivSale"));
                projekcija.setDatumProjekcije(rs.getDate("datumProjekcije"));
                projekcija.setCena(rs.getInt("cena"));
                projekcija.setMaxUlaznicaP(rs.getInt("maxUlaznicaP"));
                projekcija.setVremeProjekcije(rs.getTime("vremeProjekcije"));
                projekcija.setIdP(rs.getInt("idProjekcija"));

                projekcije.add(projekcija);
            }
        }
        return projekcije;
    }
        

    public static List<Projekcija> ispisNaZahtevRegKosinika(String nazivFestivala, String nazivFilma, Date datumOd, Date datumDo) throws SQLException {
        List<Projekcija> listaProjekcija = new ArrayList<>();
        int i = 1;
        String sql = "select * from projekcija p, festival fe, film fi, lokacija l, mesto m "
                + "where p.idFestival = fe.idFestival and p.idFilm = fi.idFilm "
                + "and p.idLokacija = l.idLokacija and fe.idMesto = m.idMesto";
        if (nazivFestivala.trim().length() != 0) {
            sql += " and fe.naziv = ?";
        }
        if ((nazivFilma.trim().length() != 0)) {
            sql += " and (fi.nazivNaSrpskom = ? or fi.originalniNaziv = ? )";
        }
        if (datumOd != null) {
            sql += " and fe.datumOd > ?";
        }
        if (datumDo != null) {
            sql += " and fe.datumDo < ?";
        }
        sql += " group by fe.idFestival";
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
                    projekcija.setOriginalniNazivFilma(rs.getString("originalniNaziv"));
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
    
    public static List <Projekcija> ispisNaZahtevKorisnika(String originalniNazivFilmaZaOpisFilma) throws SQLException{
        List<Projekcija> listaProjekcijaZaOpisFilma = new ArrayList<>();
        String sql = "select * from festival fe, film fi, projekcija p, lokacija l, mesto m "
                + "where fe.idMesto = m.idMesto and p.idFestival = fe.idFestival "
                + "and p.idFilm = fi.idFilm and p.idLokacija = l.idLokacija and originalniNaziv = ?";
        
        try(
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                ){
            ps.setString(1, originalniNazivFilmaZaOpisFilma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Projekcija p = new Projekcija();
                p.setNazivFestivalaZaProjekciju(rs.getString("naziv"));
                p.setDatumProjekcije(rs.getDate("datumProjekcije"));
                p.setLokacija(rs.getString("imeLokacija"));
                p.setCena(rs.getInt("cena"));
                p.setIdP(rs.getInt("idProjekcija"));
                listaProjekcijaZaOpisFilma.add(p);
            }
        }
            
        return listaProjekcijaZaOpisFilma;
    }
    
    public static List<Projekcija> listaProjekcijaZaFestival(int idFestival) throws SQLException {
        List<Projekcija> projekcije = new ArrayList<>();
        String sql = "SELECT p.cena, p.datumProjekcije, p.vremeProjekcije,"
                + "p.maxUlaznicaP, l.imeLokacija, l.nazivSale, f.nazivNaSrpskom, f.originalniNaziv "
                + "from projekcija p JOIN film f JOIN lokacija l JOIN festival fe "
                + "where p.idFestival = ? and p.idFilm = f.idFilm and p.idLokacija = l.idLokacija and fe.idFestival = p.idFestival";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idFestival);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Projekcija projekcija = new Projekcija();
                projekcija.setNazivFilmaNaSrpskom(rs.getString("nazivNaSrpskom"));
                projekcija.setOriginalniNazivFilma(rs.getString("originalniNaziv"));
                projekcija.setImeLokacija(rs.getString("imeLokacija"));
                projekcija.setNazivSale(rs.getString("nazivSale"));
                projekcija.setDatumProjekcije(rs.getDate("datumProjekcije"));
                projekcija.setCena(rs.getInt("cena"));
                projekcija.setMaxUlaznicaP(rs.getInt("maxUlaznicaP"));
                projekcija.setVremeProjekcije(rs.getTime("vremeProjekcije"));

                projekcije.add(projekcija);
            }
        }
        return projekcije;
    }
}

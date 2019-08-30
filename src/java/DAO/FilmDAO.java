package DAO;

import beans.Film;
import beans.Uloge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class FilmDAO {

    public static boolean dodajFilm(String originalniNaziv, String nazivNaSrpskom,
            int godinaIzdanja, String filmOpis, int idReziser, int idZemljePorekla,
            int trajanjeFilma, String imdbLink, String poster, int idGlumac, int idGlumac1, int idGlumac2, int idGlumac3, int idGlumac4) {

        String filmSql = "insert into film(originalniNaziv, nazivNaSrpskom, godinaIzdanja, filmOpis, idReziser, idZemljePorekla, trajanjeFilma, imdbLink, poster) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String ulogeSql = "insert into uloge(idFilm, idGlumac) values (?, ?)";
        try {
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(filmSql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, originalniNaziv);
            ps.setString(2, nazivNaSrpskom);
            ps.setInt(3, godinaIzdanja);
            ps.setString(4, filmOpis);
            ps.setInt(5, idReziser);
            ps.setInt(6, idZemljePorekla);
            ps.setInt(7, trajanjeFilma);
            ps.setString(8, imdbLink);
            ps.setString(9, poster);

            ps.executeUpdate();

            int idFilm = -1;
            ResultSet rsId = ps.getGeneratedKeys();
            if (rsId.next()) {
                idFilm = rsId.getInt(1);
            }

            PreparedStatement ps2 = connection.prepareStatement(ulogeSql);

            ps2.setInt(1, idFilm);
            ps2.setInt(2, idGlumac);
            
            ps2.executeUpdate();
            
            PreparedStatement ps3 = connection.prepareStatement(ulogeSql);

            ps3.setInt(1, idFilm);
            ps3.setInt(2, idGlumac1);
            
            ps3.executeUpdate();

            PreparedStatement ps4 = connection.prepareStatement(ulogeSql);

            ps4.setInt(1, idFilm);
            ps4.setInt(2, idGlumac2);
            
            ps4.executeUpdate();
            
            PreparedStatement ps5 = connection.prepareStatement(ulogeSql);

            ps5.setInt(1, idFilm);
            ps5.setInt(2, idGlumac3);
            
            ps5.executeUpdate();
            
            PreparedStatement ps6 = connection.prepareStatement(ulogeSql);

            ps6.setInt(1, idFilm);
            ps6.setInt(2, idGlumac4);
            
            ps6.executeUpdate();
            
            
            
            ps.close();
            ps2.close();
            connection.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(FilmDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Film ispisFilmaZaKorisnika(String originalniNazivFilma, String nazivFestivalaZaProjekciju) throws SQLException {

        String glumci = "";
        String sql1 = "select * from film f, uloge u, glumci g "
                + "where f.idFilm = u.idFilm and u.idGlumac = g.idGlumac and f.originalniNaziv = ?;";
        try (Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql1);) {
            ps.setString(1, originalniNazivFilma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                glumci += rs.getString("imeGlumci") + ",";
            }
        }
        glumci = glumci.substring(0, glumci.length() - 1);
        Film film1 = new Film();
        String sql = "select * from film f, reziseri r, zemlje_porekla z where f.idReziser = r.idReziser "
                + "and f.idZemljePorekla = z.idZemljePorekla and f.originalniNaziv = ?";
        try (Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, originalniNazivFilma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //Film film = new Film();
                film1.setNazivNaSrpskom(rs.getString("nazivNaSrpskom"));
                film1.setOriginalniNaziv(rs.getString("originalniNaziv"));
                film1.setPoster(rs.getString("Poster"));
                film1.setGodinaIzdanja(rs.getInt("godinaIzdanja"));
                film1.setFilmOpis(rs.getString("filmOpis"));
                film1.setNazivReziser(rs.getString("imeReziseri"));
                film1.setTrajanjeFilma(rs.getInt("trajanjeFilma"));
                film1.setZemljaPorekla(rs.getString("imeZemljaPorekla"));
                film1.setImdbLink(rs.getString("imdbLink"));
                film1.setSviGlumciFilma(glumci);
                film1.setNazivFestivalaZaDatiFilm(nazivFestivalaZaProjekciju);
                film1.setOcenaCOUNT(rs.getInt("ocenaCount"));
                film1.setOcenaSUM(rs.getInt("ocenaSUM"));
                film1.setProsecnaOcena(rs.getInt("ocenaSUM") / rs.getInt("ocenaCount"));

            }
        }

        return film1;
    }

    public static List<Film> dohvatiSveFilmove() {
        List<Film> sviFilmoviLista = new ArrayList<>();

        String sql = "select * from film";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idFilm = rs.getInt("idFilm");
                String originalniNaziv = rs.getString("originalniNaziv");
                String nazivNaSrpskom = rs.getString("nazivNaSrpskom");

                Film film = new Film();
                film.setIdFilm(idFilm);
                film.setOriginalniNaziv(originalniNaziv);
                film.setNazivNaSrpskom(nazivNaSrpskom);

                sviFilmoviLista.add(film);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReziseriDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sviFilmoviLista;

    }
}

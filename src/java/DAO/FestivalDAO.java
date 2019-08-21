package DAO;

import beans.Festival;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.DB;

@ManagedBean
@SessionScoped
public class FestivalDAO implements Serializable {

    public static String dohvatiSveFestivaleZaNeregistrovanogKorisnikaPoNazivu() {

        String sql1 = "select f.naziv, m.ime from festival f, mesto m where m.idMesto = f.idMesto and f.datumOd = ? and f.naziv = ?";
        return null;

    }

    public static List<Festival> dohvatiFestivaleZaRegKorisnike() throws SQLException {
        String sql = "select * from festival f join mesto m where f.idMesto = m.idMesto and f.datumDo > ? order by datumOd LIMIT 5;";
        List<Festival> festivali = new ArrayList<>();
        
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
            ps.setDate(1, sqlDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Festival festival = new Festival();
                festival.setIdFestival(rs.getInt("idFestival"));
                festival.setNaziv(rs.getString("naziv"));
                festival.setDatumOd(rs.getDate("datumOd"));
                festival.setDatumDo(rs.getDate("datumDo"));
                festival.setFestivalOpis(rs.getString("festivalOpis"));
                festival.setMaxUlaznicaF(rs.getInt("maxUlaznicaF"));
                festival.setMesto(rs.getString("imeMesto"));

                festivali.add(festival);
            }
        }
        return festivali;
    }

    public static Festival dohvatiInfoFestival(String nazivFestivala) throws SQLException {
        String sql = "select * from festival f, mesto m where f.idMesto = m.idMesto and naziv = ?";
        Festival festival2 = new Festival();
        try (Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, nazivFestivala);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                festival2.setIdFestival(rs.getInt("idFestival"));
                festival2.setNaziv(rs.getString("naziv"));
                festival2.setDatumOd(rs.getDate("datumOd"));
                festival2.setDatumDo(rs.getDate("datumDo"));
                festival2.setFestivalOpis(rs.getString("festivalOpis"));
                festival2.setMaxUlaznicaF(rs.getInt("maxUlaznicaF"));
                festival2.setMesto(rs.getString("imeMesto"));
                festival2.setOpis(rs.getString("festivalOpis"));

            }

        }
        return festival2;
    }

    public static List<Festival> dohvatiFestivaleZaIndex(String naziv, Date datumOd, Date datumDo) throws SQLException {
        String sql = "";
        List<Festival> festivaliZaIspis = new ArrayList<>();
        
        if (naziv.trim().length() != 0 && datumOd != null && datumDo != null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.naziv = ? and f.datumOd > ? and f.datumDo < ?";

            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, naziv);
                ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd")).format(datumOd));
                ps.setString(3, (new SimpleDateFormat("yyyy-MM-dd")).format(datumDo));
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }

            }

        } else if (naziv.trim().length() == 0 && datumOd != null && datumDo != null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.datumOd > ? and f.datumDo < ?";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, (new SimpleDateFormat("yyyy-MM-dd")).format(datumOd));
                ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd")).format(datumDo));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }
            }

        } else if (naziv.trim().length() == 0 && datumOd == null && datumDo != null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.datumDo < ?";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {

                ps.setString(1, (new SimpleDateFormat("yyyy-MM-dd")).format(datumDo));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }
            }

        } else if (naziv.trim().length() == 0 && datumOd != null && datumDo == null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.datumOd > ?";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {

                ps.setString(1, (new SimpleDateFormat("yyyy-MM-dd")).format(datumOd));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }
            }

        } else if (naziv.trim().length() != 0 && datumOd == null && datumDo == null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.naziv = ?";

            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, naziv);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }

            }

        } else if (naziv.trim().length() != 0 && datumOd != null && datumDo == null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.naziv = ? and f.datumOd > ? ";

            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, naziv);
                ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd")).format(datumOd));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }

            }

        } else if (naziv.trim().length() != 0 && datumOd == null && datumDo != null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto and f.naziv = ? and f.datumDo < ?";

            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, naziv);
                ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd")).format(datumDo));
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }

            }

        } else if (naziv.trim().length() == 0 && datumOd == null && datumDo == null) {
            sql = "select f.naziv, f.datumOd, f.datumDo, m.imeMesto from festival f, mesto m where f.idMesto = m.idMesto";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Festival festival = new Festival();
                    festival.setNaziv(rs.getString("naziv"));
                    festival.setDatumOd(rs.getDate("datumOd"));
                    festival.setDatumDo(rs.getDate("datumDo"));
                    festival.setMesto(rs.getString("imeMesto"));
                    festivaliZaIspis.add(festival);
                }
                
            }
        }
        return festivaliZaIspis;
    }
        public static int dodajFestival(String naziv, int idMesto,
            Date datumOd, Date datumDo, String festivalOpis, int maxUlaznicaF) {

        String festivalSql = "insert into festival(naziv, idMesto, datumOd, datumDo, fesitvalOpis, maxUlaznicaF) values(?, ?, ?, ?, ?, ?)";

        int poslednjiIdFilm = -1;
                
        try {
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(festivalSql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, naziv);
            ps.setInt(2, idMesto);
            ps.setDate(3, new java.sql.Date(datumOd.getTime()));
            ps.setDate(4, new java.sql.Date(datumDo.getTime()));
            ps.setString(5, festivalOpis);
            ps.setInt(6, maxUlaznicaF);

            ps.executeUpdate();
            
            ResultSet rsId = ps.getGeneratedKeys();
            if (rsId.next()){
                poslednjiIdFilm = rsId.getInt(1);
                
            }

            ps.close();

            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(FilmDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return poslednjiIdFilm;
    }
}

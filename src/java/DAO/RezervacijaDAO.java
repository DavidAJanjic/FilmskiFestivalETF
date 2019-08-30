
package DAO;

import beans.Rezervacija;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;
import util.DB;


public class RezervacijaDAO {
    
    public static void izvrsiRezervaciju(int idKorisnika, int idProjekcija, String jedinstveniKod,
            int idStatusRezervacije, Date datumRezervacije, int brojUlaznica) throws SQLException {
        String sql = "insert into rezervacija(idProjekcija, jedinstveniKod, idStatusRezervacije, "
                + "datumRezervacije,datumIsteka,brojUlaznica,idKorisnika) values (?,?,?,?,?,?,?);";
        try(
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                ){
            LocalDate l;
            l = LocalDate.now().plusDays(7);
            Date l1 = java.sql.Date.valueOf(l);
            ps.setInt(1, idProjekcija);
            ps.setString(2, jedinstveniKod);
            ps.setInt(3, idStatusRezervacije);
            ps.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(datumRezervacije));
            ps.setString(5, ((new SimpleDateFormat("yyyy-MM-dd")).format(l1)));
            ps.setInt(6, brojUlaznica);
            ps.setInt(7, idKorisnika);
            
            ps.executeUpdate();
            
        }
        
    }
    
        public static List<Rezervacija> dohvatiSveRezervacije() throws SQLException {
        String sql = "select r.idRezervacija, r.idProjekcija, r.jedinstveniKod, r.idStatusRezervacije,"
                + "r.datumRezervacije, r.datumIsteka, r.brojUlaznica, k.ime, k.prezime, p.cena "
                + "from rezervacija r, korisnik k, projekcija p WHERE r.idKorisnika = k.idKorisnik and p.idProjekcija = r.idProjekcija;";
        List<Rezervacija> sveRezervacije = new ArrayList<>();

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacija(rs.getInt("idRezervacija"));
                rezervacija.setIdProjekcija(rs.getInt("idProjekcija"));
                rezervacija.setJedinstveniKod(rs.getString("jedinstveniKod"));
                rezervacija.setIdStatusRezervacije(rs.getInt("idStatusRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije"));
                rezervacija.setDatumIsteka(rs.getDate("datumIsteka"));
                rezervacija.setBrojUlaznica(rs.getInt("brojUlaznica"));
                rezervacija.setIme(rs.getString("ime"));
                rezervacija.setPrezime(rs.getString("prezime"));
                rezervacija.setCena(rs.getInt("cena"));

                sveRezervacije.add(rezervacija);
            }
        }
        return sveRezervacije;
    }



    public static void setStatusRezervacije(int idRezervacija, int idStatusRezervacije) throws SQLException {
        String sql = "";

        if (idStatusRezervacije == 2) {
            sql = "UPDATE rezervacija SET idStatusRezervacije = ? WHERE idRezervacija = ?";

            try (
                    Connection connection = DB.otvoriKonekciju();
                    PreparedStatement ps = connection.prepareStatement(sql);) {

                ps.setInt(1, idStatusRezervacije);
                ps.setInt(2, idRezervacija);

                ps.executeUpdate();
            }
        } 
    }
    
    public static List<Rezervacija> dohvatiRezervacijePoImenuIPrezimenu(String ime, String prezime) throws SQLException {
        String sql = "";
        List<Rezervacija> rezervacijeIspis = new ArrayList<>();
        
        if (ime.trim().length() != 0 && prezime.trim().length() != 0) {
            sql = "select r.idRezervacija, r.idProjekcija, r.jedinstveniKod, r.idStatusRezervacije,"
                + "r.datumRezervacije, r.datumIsteka, r.brojUlaznica, k.ime, k.prezime, p.cena "
                + "from rezervacija r, korisnik k, projekcija p WHERE r.idKorisnika = k.idKorisnik and p.idProjekcija = r.idProjekcija"
                    + " and k.ime = ? and k.prezime = ?;";

            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, ime);
                ps.setString(2, prezime);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    
                    Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacija(rs.getInt("idRezervacija"));
                rezervacija.setIdProjekcija(rs.getInt("idProjekcija"));
                rezervacija.setJedinstveniKod(rs.getString("jedinstveniKod"));
                rezervacija.setIdStatusRezervacije(rs.getInt("idStatusRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije"));
                rezervacija.setDatumIsteka(rs.getDate("datumIsteka"));
                rezervacija.setBrojUlaznica(rs.getInt("brojUlaznica"));
                rezervacija.setIme(rs.getString("ime"));
                rezervacija.setPrezime(rs.getString("prezime"));
                rezervacija.setCena(rs.getInt("cena"));

                rezervacijeIspis.add(rezervacija);
                }
            }

        } else if (ime.trim().length() == 0 && prezime.trim().length() != 0) {
            sql = "SELECT r.idRezervacija, r.idProjekcija, r.jedinstveniKod, r.idStatusRezervacije,"
                + "r.datumRezervacije, r.datumIsteka, r.brojUlaznica, k.ime, k.prezime, p.cena "
                + "FROM rezervacija r, korisnik k, projekcija p WHERE r.idKorisnika = k.idKorisnik and p.idProjekcija = r.idProjekcija"
                    + " AND k.prezime = ?;";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, prezime);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacija(rs.getInt("idRezervacija"));
                rezervacija.setIdProjekcija(rs.getInt("idProjekcija"));
                rezervacija.setJedinstveniKod(rs.getString("jedinstveniKod"));
                rezervacija.setIdStatusRezervacije(rs.getInt("idStatusRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije"));
                rezervacija.setDatumIsteka(rs.getDate("datumIsteka"));
                rezervacija.setBrojUlaznica(rs.getInt("brojUlaznica"));
                rezervacija.setIme(rs.getString("ime"));
                rezervacija.setPrezime(rs.getString("prezime"));
                rezervacija.setCena(rs.getInt("cena"));

                rezervacijeIspis.add(rezervacija);
                }  
            }
            
        } else if (ime.trim().length() != 0 && prezime.trim().length() == 0) {
            sql = "SELECT r.idRezervacija, r.idProjekcija, r.jedinstveniKod, r.idStatusRezervacije,"
                + "r.datumRezervacije, r.datumIsteka, r.brojUlaznica, k.ime, k.prezime, p.cena "
                + "FROM rezervacija r, korisnik k, projekcija p WHERE r.idKorisnika = k.idKorisnik AND p.idProjekcija = r.idProjekcija"
                    + " AND k.ime = ?;";
            try (
                    Connection c = DB.otvoriKonekciju();
                    PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, ime);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Rezervacija rezervacija = new Rezervacija();
                rezervacija.setIdRezervacija(rs.getInt("idRezervacija"));
                rezervacija.setIdProjekcija(rs.getInt("idProjekcija"));
                rezervacija.setJedinstveniKod(rs.getString("jedinstveniKod"));
                rezervacija.setIdStatusRezervacije(rs.getInt("idStatusRezervacije"));
                rezervacija.setDatumRezervacije(rs.getDate("datumRezervacije"));
                rezervacija.setDatumIsteka(rs.getDate("datumIsteka"));
                rezervacija.setBrojUlaznica(rs.getInt("brojUlaznica"));
                rezervacija.setIme(rs.getString("ime"));
                rezervacija.setPrezime(rs.getString("prezime"));
                rezervacija.setCena(rs.getInt("cena"));

                rezervacijeIspis.add(rezervacija);
                }
            }
        }
        return rezervacijeIspis;
    }
    
    
    public static List<Rezervacija> dohvatiSveStatuse() {
        List<Rezervacija> sviStatusi = new ArrayList<>();

        String sql = "select * from status_rezervacija";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idStatusaRezervacije = rs.getInt("idStatusaRezervacije");
                String status = rs.getString("status");
                
                Rezervacija statusi = new Rezervacija();
                statusi.setIdStatusaRezervacije(idStatusaRezervacije);
                statusi.setStatus(status);
                
                sviStatusi.add(statusi);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RezervacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sviStatusi;
    }
}

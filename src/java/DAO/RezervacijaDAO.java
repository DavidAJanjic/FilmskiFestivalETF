package DAO;

import beans.Rezervacija;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class RezervacijaDAO {

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

    public static String getKod() {
        String slova = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder kod = new StringBuilder();
        Random random = new Random();
        while (kod.length() < 10) {
            int index = (int) (random.nextFloat() * slova.length());
            kod.append(slova.charAt(index));
        }
        String kod1 = kod.toString();
        return kod1;
    }

    public static void setStatusRezervacije(int idRezervacija, int statusRezIntPromena, String jedinstveniKod) throws SQLException {
        jedinstveniKod = getKod();

        if (statusRezIntPromena == 2) {
            String sql = "UPDATE rezervacija SET idStatusRezervacije = ? and jedinstveniKod = ? where idRezervacija = ?";

            try (
                    Connection connection = DB.otvoriKonekciju();
                    PreparedStatement ps = connection.prepareStatement(sql);) {

                ps.setInt(1, statusRezIntPromena);
                
                ps.setString(2, jedinstveniKod);
                ps.setInt(3, idRezervacija);

                ps.executeUpdate();
            }
        } else {
            String sql = "UPDATE rezervacija SET idStatusRezervacije = ? where idRezervacija = ?";
            try (
                    Connection connection = DB.otvoriKonekciju();
                    PreparedStatement ps = connection.prepareStatement(sql);) {

                ps.setInt(1, statusRezIntPromena);
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
}

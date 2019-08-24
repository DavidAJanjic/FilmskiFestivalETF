/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.Korisnik;
;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author DJ
 */


public class KorisnikDAO {

    public static String dodajKorisnika(String username, String password, String ime, String prezime, String kontaktMob, String email, Date datumRodjenja) throws SQLException {
        String username1 = null;
        String daLiPostojiSql = "select * from korisnik where username = ?";
        try (Connection connection1 = DB.otvoriKonekciju();
                PreparedStatement ps1 = connection1.prepareStatement(daLiPostojiSql);) {

            ps1.setString(1, username);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                username1 = rs1.getString("username");
            }
        }
        if (username1 == null) {

            String noviKorisnikSql = "insert into korisnik(username, password, ime, prezime, kontaktMob, email, datumRodjenja) values(?, ?, ?, ?, ?, ?, ?)";
            try {
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(noviKorisnikSql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, ime);
                ps.setString(4, prezime);
                ps.setString(5, kontaktMob);
                ps.setString(6, email);
                ps.setDate(7, new java.sql.Date(datumRodjenja.getTime()));

                ps.executeUpdate();

                ps.close();
                connection.close();
                return "Ok";
            } catch (SQLException ex) {
                Logger.getLogger(KorisnikDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "notOk";
    }

    public static Korisnik dohvatiKorisnika(String username, String password) {

        String sql = "select * from korisnik where username = ? and password = ? ";

        try (
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                Date datumRodjenja = rs.getDate("datumRodjenja");
                String kontaktMob = rs.getString("kontaktMob");
                int tipKorisnika = rs.getInt("idTipKorisnika");
                int idKorisnik = rs.getInt("idKorisnik");
                

                Korisnik korisnik = new Korisnik();
                korisnik.setIdKorisnik(idKorisnik);
                korisnik.setUsername(username);
                korisnik.setPassword(password);
                korisnik.setIme(ime);
                korisnik.setPrezime(prezime);
                korisnik.setDatumRodjenja(datumRodjenja);//ako pukne treba konverzija
                korisnik.setKontaktMob(kontaktMob);
                korisnik.setEmail(email);
                korisnik.setTipKorisnikaInt(tipKorisnika);

                ps.close();
                connection.close();

                return korisnik;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String passwordUpdate(String username, String password, String newPasswrd) throws SQLException {
        String sqlUpit = "select password from korisnik where username = ?";
        try (
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sqlUpit);) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String passwordProvera = rs.getString("password");
                String newPasswod = newPasswrd;
                if (passwordProvera.equals(newPasswod)) {
                    return "istaSifra";
                }
                if (passwordProvera.trim().length() == 0) {
                    return "neostojeciKorisnik";
                }
            }

            String sql = "UPDATE korisnik SET password= ? WHERE username = ? and password = ?;";
            try (
                    Connection connection1 = DB.otvoriKonekciju();
                    PreparedStatement ps1 = connection.prepareStatement(sql);) {
                ps1.setString(1, newPasswrd);
                ps1.setString(2, username);
                ps1.setString(3, password);
                ps1.executeUpdate();
            }
        }
        return "uspesno";
    }

    public static List<Korisnik> sviKorisnici() throws SQLException {
        List<Korisnik> korisnici = new ArrayList<>();
        String sql = "select k.*,tp.nazivTipa from korisnik k,tip_korisnika tp where k.idTipKorisnika = tp.idTipKorisnika order by k.idKorisnik;";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setIdKorisnik(rs.getInt("idKorisnik"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setEmail(rs.getString("email"));
                korisnik.setKontaktMob(rs.getString("kontaktMob"));
                korisnik.setDatumRodjenja(rs.getDate("datumRodjenja"));
                korisnik.setTipKorisnikaString(rs.getString("nazivTipa"));

                korisnici.add(korisnik);
            }
        }
        return korisnici;
    }

    public static Korisnik dohvatiKorisnika1(String username, String password) {

        String sql = "select k.*,tp.nazivTipa from korisnik k,tip_korisnika tp where k.idTipKorisnika = tp.idTipKorisnika and username = ? and password = ?;";

        try (
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                Date datumRodjenja = rs.getDate("datumRodjenja");
                String kontaktMob = rs.getString("kontaktMob");
                String tipKorisnika = rs.getString("nazivTipa");

                Korisnik korisnik = new Korisnik();
                korisnik.setUsername(username);
                korisnik.setPassword(password);
                korisnik.setIme(ime);
                korisnik.setPrezime(prezime);
                korisnik.setDatumRodjenja(datumRodjenja);//ako pukne treba konverzija
                korisnik.setKontaktMob(kontaktMob);
                korisnik.setEmail(email);
                korisnik.setTipKorisnikaString(tipKorisnika);

                return korisnik;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void setTipKorisnika(String username, int tipKorisnikaIntPromena) throws SQLException {
        String sql = "update korisnik set idTipKorisnika = ? where username = ?";

        try (
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, tipKorisnikaIntPromena);
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    public static void odbaciZahtev(String username, String password) throws SQLException {
        String sql = "delete from korisnik where username = ? and password = ?;";
        try (
                Connection connection = DB.otvoriKonekciju();
                PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        }

    }
}

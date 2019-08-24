package DAO;

import beans.Lokacija;
import beans.MestoFestivala;
import beans.Projekcija;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class LokacijaDAO {

    public static List<String> dohvatiSveLokacije() {
        List<String> sveLokacije = new ArrayList<>();

        String sql = "select imeLokacija from lokacija";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                String imeLokacija = rs.getString("imeLokacija");
                
                sveLokacije.add(imeLokacija);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LokacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sveLokacije;
    }

    
    //vraca sve lokacije na strani za dodavanje projekcija, a kao argument uzima idMesto dobijeno kod kreiranja festivala!
        public static List<Lokacija> dohvatiLokacijeZaProjekcije(int idMesto) {
        List<Lokacija> sveLokacije = new ArrayList<>();

        String sql = "select * from lokacija where idMesto = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, idMesto);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idLokacija = rs.getInt("idLokacija");
                String imeLokacijaNazivSale = rs.getString("imeLokacijaNazivSale");

                Lokacija lokacija = new Lokacija();
                lokacija.setIdLokacija(idLokacija);
                lokacija.setImeLokacijaNazivSale(imeLokacijaNazivSale);

                sveLokacije.add(lokacija);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LokacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sveLokacije;
    }
        
            //vraca sve lokacije na strani za dodavanje projekcija, a kao argument uzima idMesto dobijeno kod kreiranja festivala!
        public static String ispisLokacijaZaProjekciju(int idLokacija) {
        
        String ispis="";

        String sql = "select imeLokacija, nazivSale from lokacija where idLokacija = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, idLokacija);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String imeLokacija = rs.getString("imeLokacija");
                String nazivSale = rs.getString("nazivSale");

                Lokacija lokacija = new Lokacija();
                lokacija.setImeLokacija(imeLokacija);
                lokacija.setNazivSale(nazivSale);

                ispis += imeLokacija + " " + nazivSale;

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LokacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ispis;
    }

}


 
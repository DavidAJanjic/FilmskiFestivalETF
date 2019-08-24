package DAO;

import beans.Lokacija;
import beans.MestoFestivala;
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

    public static List<Lokacija> dohvatiSveLokacije() {
        List<Lokacija> sveLokacije = new ArrayList<>();

        String sql = "select * from lokacija";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idLokacija = rs.getInt("idLokacija");
                int idMesto = rs.getInt("idMesto");
                String imeLokacija = rs.getString("imeLokacija");
                String nazivSale = rs.getString("nazivSale");

                Lokacija lokacija = new Lokacija();
                lokacija.setIdLokacija(idLokacija);
                lokacija.setIdMesto(idMesto);
                lokacija.setImeLokacija(imeLokacija);
                lokacija.setNazivSale(nazivSale);

                sveLokacije.add(lokacija);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LokacijaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sveLokacije;
    }

}


 
package DAO;

import beans.Reziseri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class ReziseriDAO {
    
        public static List<Reziseri> dohvatiSveRezisere() {
        List<Reziseri> sviReziseriLista = new ArrayList<>();

        String sql = "select * from reziseri";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idReziser = rs.getInt("idReziser");
                String imeReziseri = rs.getString("imeReziseri");
                
                Reziseri reziser = new Reziseri();
                reziser.setIdReziser(idReziser);
                reziser.setImeReziseri(imeReziseri);
                
                sviReziseriLista.add(reziser);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReziseriDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sviReziseriLista;
    }
}


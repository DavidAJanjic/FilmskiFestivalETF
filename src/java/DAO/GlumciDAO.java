package DAO;

import beans.Glumci;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class GlumciDAO {

    public static List<Glumci> dohvatiSveGlumce() {
        List<Glumci> sviGlumciLista = new ArrayList<>();

        String sql = "select * from glumci";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idGlumac = rs.getInt("idGlumac");
                String imeGlumac = rs.getString("imeGlumac");
                
                Glumci glumci = new Glumci();
                glumci.setIdGlumac(idGlumac);
                glumci.setImeGlumac(imeGlumac);
                
                sviGlumciLista.add(glumci);
  
            }

            ps.close();
            connection.close();
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GlumciDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sviGlumciLista;
    }
    

}


package DAO;

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

public class MestoFestivalaDAO {

    public static List<MestoFestivala> dohvatiSvaMesta() {
        List<MestoFestivala> svaMestaFestivala = new ArrayList<>();

        String sql = "select * from mesto";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idMesto = rs.getInt("idMesto");
                String imeMesto = rs.getString("imeMesto");

                MestoFestivala mestoFestivala = new MestoFestivala();
                mestoFestivala.setIdMesto(idMesto);
                mestoFestivala.setImeMesto(imeMesto);

                svaMestaFestivala.add(mestoFestivala);

            }

            ps.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MestoFestivalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return svaMestaFestivala;
    }
}

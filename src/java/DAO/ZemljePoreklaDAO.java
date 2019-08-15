
package DAO;

import beans.ZemljePorekla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class ZemljePoreklaDAO {
    
    
    public static List<ZemljePorekla> dohvatiZemljePorekla(){
        List<ZemljePorekla> zemljePoreklaLista = new ArrayList<>();
        
        String sql = "select * from zemlje_porekla";
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int idZemljePorekla = rs.getInt("idZemljePorekla");
                String imeZemljePorekla = rs.getString("imeZemljePorekla");
                
                ZemljePorekla zemljaPorekla = new ZemljePorekla();
                zemljaPorekla.setIdZemljePorekla(idZemljePorekla);
                zemljaPorekla.setImeZemljePorekla(imeZemljePorekla);
                
                zemljePoreklaLista.add(zemljaPorekla);
 
            }
            
            ps.close();
                connection.close();
                
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(KorisnikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return zemljePoreklaLista;
    }
}


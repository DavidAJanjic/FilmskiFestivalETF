
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DB;


public class KupovinaDAO {
    
    public static String izvrsiKupovinu(int idRezervacija) throws SQLException {
        String sql= "select rezervacija.idStatusRezervacije from rezervacija where idRezervacija = ?;";
        String result ="";
        try(
            Connection c = DB.otvoriKonekciju();
            PreparedStatement ps = c.prepareStatement(sql);
                ) {
            ps.setInt(1, idRezervacija);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int broj = rs.getInt("idStatusRezervacije");
            
            
            if(broj==2){
                String sql1 = "insert into kupovina(idRezervacija) values(?);";
                String sql2 = "update rezervacija set rezervacija.idStatusRezervacije = 4 where rezervacija.idRezervacija = ?;";
                try(
                    Connection c1 = DB.otvoriKonekciju();
                    PreparedStatement ps1 = c1.prepareStatement(sql1);
                    PreparedStatement ps2 = c1.prepareStatement(sql2);
                        ) {
                    ps1.setInt(1, idRezervacija);
                    ps1.executeUpdate();
                    
                    ps2.setInt(1, idRezervacija);
                    ps2.executeUpdate();
            
                }
                    result = "rezervacija";
                } else if(broj == 1) {
                    result = "rezervacijaCekaOdobrenje";
                } else if(broj == 3) {
                    result = "rezervacijaOdbijena";
                } else if(broj == 4) {
                    result = "rezervacijaKupljena";
                } else if(broj == 5) {
                    result = "rezervacijaOtkazana";
                }
            return result;
        }      
            
    }
    
        public static String otkaziRezervaciju(int idRezervacija) throws SQLException{
            
            String sql= "select rezervacija.idStatusRezervacije from rezervacija where idRezervacija = ?;";
        
        try(
            Connection c = DB.otvoriKonekciju();
            PreparedStatement ps = c.prepareStatement(sql);
                ) {
            ps.setInt(1, idRezervacija);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int broj = rs.getInt("idStatusRezervacije");
            
            if(broj !=4) {
            
            
            String sql1 = "delete from rezervacija where idRezervacija = ?;";
            String sql2 = "update rezervacija set rezervacija.idStatusRezervacije = 5 where rezervacija.idRezervacija = ?;";
            try (
                    Connection c1 = DB.otvoriKonekciju();
                    PreparedStatement ps1 = c1.prepareStatement(sql1);
                    PreparedStatement ps2 = c1.prepareStatement(sql2);
                    ) {
            
                ps1.setInt(1, idRezervacija);
                ps1.executeUpdate();
                
                ps2.setInt(1, idRezervacija);
                ps2.executeUpdate();
                }
            return "rezervacija";
            }
            else {
                return "rezervacijaKupljena";
            }
        }
    }    
}
    


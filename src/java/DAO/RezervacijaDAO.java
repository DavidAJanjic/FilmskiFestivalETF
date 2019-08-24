
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    
}

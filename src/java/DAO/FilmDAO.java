
package DAO;

import beans.Film;
import beans.Uloge;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

public class FilmDAO {
    
    public static boolean dodajFilm(String originalniNaziv, String nazivNaSrpskom, 
            int godinaIzdanja, String filmOpis, int idReziser, int idZemljePorekla, 
            int trajanjeFilma, String imdbLink, String poster, int idGlumac) {
        
        String filmSql = "insert into film(originalniNaziv, nazivNaSrpskom, godinaIzdanja, filmOpis, idReziser, idZemljePorekla, trajanjeFilma, imdbLink, poster) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String ulogeSql = "insert into uloge(idFilm, idGlumac) values (?, ?)";
        try {
            Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(filmSql, Statement.RETURN_GENERATED_KEYS);
               
            ps.setString(1, originalniNaziv);
            ps.setString(2, nazivNaSrpskom);
            ps.setInt(3, godinaIzdanja);
            ps.setString(4, filmOpis);
            ps.setInt(5, idReziser);
            ps.setInt(6, idZemljePorekla);
            ps.setInt(7, trajanjeFilma);
            ps.setString(8, imdbLink);
            ps.setString(9, poster);
            
            ps.executeUpdate();
            
            int idFilm = -1;
            ResultSet rsId = ps.getGeneratedKeys();
            if (rsId.next()){
                idFilm=rsId.getInt(1);
            }
            
            PreparedStatement ps2 = connection.prepareStatement(ulogeSql);
            
            ps2.setInt(1,idFilm);
            ps2.setInt(2,idGlumac);
            
            ps2.executeUpdate();
            
            
            
            ps.close();
            ps2.close();
            connection.close();
                
            return true;
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(FilmDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static Film ispisFilmaZaKorisnika(String originalniNazivFilma,String nazivFestivalaZaProjekciju) throws SQLException{
        
        String glumci = "";
        String sql1 = "select * from film f, uloge u, glumci g "
                + "where f.idFilm = u.idFilm and u.idGlumac = g.idGlumac and f.originalniNaziv = ?;";
        try (Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql1);){
            ps.setString(1, originalniNazivFilma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                glumci += rs.getString("imeGlumci") + ",";
            }
        }
        glumci = glumci.substring(0, glumci.length()-1);
        Film film1 = new Film();
        String sql = "select * from film f, reziseri r, zemlje_porekla z where f.idReziser = r.idReziser "
                + "and f.idZemljePorekla = z.idZemljePorekla and f.originalniNaziv = ?";
        try (Connection connection = DB.otvoriKonekciju();
            PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, originalniNazivFilma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                //Film film = new Film();
                film1.setNazivNaSrpskom(rs.getString("nazivNaSrpskom"));
                film1.setOriginalniNaziv(rs.getString("originalniNaziv"));
                film1.setPoster(rs.getString("Poster"));
                film1.setGodinaIzdanja(rs.getInt("godinaIzdanja"));
                film1.setFilmOpis(rs.getString("filmOpis"));
                film1.setNazivReziser(rs.getString("imeReziseri"));
                film1.setTrajanjeFilma(rs.getInt("trajanjeFilma"));
                film1.setZemljaPorekla(rs.getString("imeZemljaPorekla"));
                film1.setImdbLink(rs.getString("imdbLink"));
                film1.setSviGlumciFilma(glumci);
                film1.setNazivFestivalaZaDatiFilm(nazivFestivalaZaProjekciju);
                
            }
        }
            
        
        return film1;
    }
}

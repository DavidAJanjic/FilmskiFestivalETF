


package controller;

import DAO.FestivalDAO;
import DAO.KorisnikDAO;
import beans.Festival;
import beans.Korisnik;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Controller implements Serializable {
    
    public List<Festival> sviFestivali() throws SQLException{
       return FestivalDAO.dohvatiFestivaleZaRegKorisnike();
       
    }
    
    public String passwordUpdate(String username, String password, String newPasswrd) throws SQLException{
        
        String result = KorisnikDAO.passwordUpdate(username, password, newPasswrd);
        if(result == "vecPostoji"){
            return"changingPasswordAlert";
        }
        return "index";
    }

    
}

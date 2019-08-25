


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
    String msgChangingPass = null;

    public String getMsgChangingPass() {
        return msgChangingPass;
    }

    public void setMsgChangingPass(String msgChangingPass) {
        this.msgChangingPass = msgChangingPass;
    }
    public List<Festival> sviFestivali() throws SQLException{
       return FestivalDAO.dohvatiFestivaleZaRegKorisnike();
       
    }
    
    public String passwordUpdate(String username, String password, String newPasswrd) throws SQLException{
        if(username.trim().length() == 0 || password.trim().length() == 0 || newPasswrd.trim().length() == 0){
            msgChangingPass = "Morate uneti sve podatke!";
            return"changingPassword";
        }
        String result = KorisnikDAO.passwordUpdate(username, password, newPasswrd);
        if(result == "istaSifra"){
            msgChangingPass = "Vasa nova sifra ne moze biti ista kao stara!";
            return"changingPassword";
        }
        if(result == "neostojeciKorisnik"){
            msgChangingPass = "Vasi podatci nisu validni, molimo vas pokusajte ponovo!";
            return"changingPassword";
        }
        return "index";
    }

    
}

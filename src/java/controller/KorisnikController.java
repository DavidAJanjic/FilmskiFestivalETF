
package controller;

import DAO.KorisnikDAO;
import beans.Korisnik;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@SessionScoped
@Named(value = "korisnikController")
public class KorisnikController implements Serializable {

  
    public String nazad() {
        return "index";
    }
    Korisnik k = new Korisnik();

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }
    
    
   
    

}
